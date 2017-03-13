package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.DepartmentService;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the DepartmentService entity.
 */
@Repository
public class DepartmentServiceRepository {

    private final Session session;

    private Mapper<DepartmentService> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement insertByDepartmentStmt;

    private PreparedStatement findServicesByDepartmentIdStmt;


    public DepartmentServiceRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(DepartmentService.class);
        this.findAllStmt = session.prepare("SELECT * FROM departmentService");
        this.truncateStmt = session.prepare("TRUNCATE departmentService");

        insertByDepartmentStmt = session.prepare(
            "INSERT INTO departmentService_by_department (departmentID, id) " +
                "VALUES (:departmentID, :id)");

        findServicesByDepartmentIdStmt = session.prepare(
            "SELECT id " +
                "FROM departmentService_by_department " +
                "WHERE departmentID = :departmentID");
    }

    public List<DepartmentService> findOneByDepartmentId(UUID departmentID) {
        BoundStatement stmt = findServicesByDepartmentIdStmt.bind();
        stmt.setUUID("departmentID", departmentID);
        return findServiceFromIndex(stmt);
    }

    private List<DepartmentService> findServiceFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<DepartmentService> departmentServiceList=new ArrayList<>();

        while(!(rs.isExhausted())){
            DepartmentService service=new DepartmentService();
            service=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            departmentServiceList.add(service);
        }
        return departmentServiceList;

    }

    public List<DepartmentService> findAll() {
        List<DepartmentService> departmentServicesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                DepartmentService departmentService = new DepartmentService();
                departmentService.setId(row.getUUID("id"));
                departmentService.setServiceName(row.getString("serviceName"));
                departmentService.setServiceDescription(row.getString("serviceDescription"));
                departmentService.setDepartmentID(row.getUUID("departmentID"));
                return departmentService;
            }
        ).forEach(departmentServicesList::add);
        return departmentServicesList;
    }

    public DepartmentService findOne(UUID id) {
        return mapper.get(id);
    }

    public DepartmentService save(DepartmentService departmentService) {
        UUID departmentServiceid = UUID.randomUUID();
        if (departmentService.getId() == null) {
            departmentService.setId(departmentServiceid);
        }
        mapper.save(departmentService);

        BatchStatement batch = new BatchStatement();
        batch.add(insertByDepartmentStmt.bind()
            .setUUID("departmentID", departmentService.getDepartmentID())
            .setUUID("id", departmentService.getId()));
        session.execute(batch);
        return departmentService;
    }

    public void delete(UUID id) {

        mapper.delete(id);

    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
