package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Departmentservice;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Departmentservice entity.
 */
@Repository
public class DepartmentserviceRepository {

    private final Session session;

    private Mapper<Departmentservice> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public DepartmentserviceRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Departmentservice.class);
        this.findAllStmt = session.prepare("SELECT * FROM departmentservice");
        this.truncateStmt = session.prepare("TRUNCATE departmentservice");
    }

    public List<Departmentservice> findAll() {
        List<Departmentservice> departmentservicesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Departmentservice departmentservice = new Departmentservice();
                departmentservice.setId(row.getUUID("id"));
                departmentservice.setDuration(row.getString("duration"));
                departmentservice.setStage(row.getString("stage"));
                return departmentservice;
            }
        ).forEach(departmentservicesList::add);
        return departmentservicesList;
    }

    public Departmentservice findOne(UUID id) {
        return mapper.get(id);
    }

    public Departmentservice save(Departmentservice departmentservice) {
        if (departmentservice.getId() == null) {
            departmentservice.setId(UUID.randomUUID());
        }
        mapper.save(departmentservice);
        return departmentservice;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
