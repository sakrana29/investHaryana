package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.DepartmentService;
import com.hartron.investharyana.domain.Projectservicedetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the Projectservicedetail entity.
 */
@Repository
public class ProjectservicedetailRepository {

    private final Session session;

    private Mapper<Projectservicedetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public ProjectservicedetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectservicedetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectservicedetail");
        this.truncateStmt = session.prepare("TRUNCATE projectservicedetail");
        this.findByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM projectservicedetail_by_project " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO projectservicedetail_by_project (projectid, id) " +
                "VALUES (:projectid, :id)");
        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM projectservicedetail_by_project " +
                "WHERE projectid = :projectid");
    }

    public List<Projectservicedetail> findServiceByProject(UUID projectid) {
        BoundStatement stmt = findByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndexByProject(stmt);
    }
    private List<Projectservicedetail> findAllFromIndexByProject(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Projectservicedetail> projectservicedetailList=new ArrayList<>();

        while(!(rs.isExhausted())) {

            Projectservicedetail deptService=new Projectservicedetail();
            deptService=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            projectservicedetailList.add(deptService);
        }

        return projectservicedetailList;

    }

//    private List<Projectservicedetail> findAllFromIndexByProject(BoundStatement stmt) {
//        ResultSet rs = session.execute(stmt);
//        List<Projectservicedetail> projectservicedetailList=new ArrayList<>();
//        while(!(rs.isExhausted())) {
//            Projectservicedetail projectService=new Projectservicedetail();
//            projectService=(Optional.ofNullable(rs.one().getUUID("id"))
//                .map(id -> Optional.ofNullable(mapper.get(id)))
//                .get()).get();
//            System.out.print(projectService);
//            projectservicedetailList.add(projectService);
//        }
//        return projectservicedetailList;
//
//    }

    public List<Projectservicedetail> findAll() {
        List<Projectservicedetail> projectservicedetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectservicedetail projectservicedetail = new Projectservicedetail();
                projectservicedetail.setId(row.getUUID("id"));
                projectservicedetail.setProjectid(row.getUUID("projectid"));
                projectservicedetail.setServiceid(row.getUUID("serviceid"));
                projectservicedetail.setDepartmentname(row.getString("departmentname"));
                projectservicedetail.setServicename(row.getString("servicename"));
                projectservicedetail.setIsrequired(row.getBool("isrequired"));
                projectservicedetail.setMarkrequiredondate(row.get("markrequiredondate", ZonedDateTime.class));
                projectservicedetail.setMarkrequiredby(row.getString("markrequiredby"));
                projectservicedetail.setIsassigned(row.getBool("isassigned"));
                projectservicedetail.setMarkassignedby(row.getString("markassignedby"));
                projectservicedetail.setFeerequired(row.getInt("feerequired"));
                projectservicedetail.setStatus(row.getString("status"));
                projectservicedetail.setComment(row.getString("comment"));
                return projectservicedetail;
            }
        ).forEach(projectservicedetailsList::add);
        return projectservicedetailsList;
    }

    public Projectservicedetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectservicedetail save(Projectservicedetail projectservicedetail) {
        if (projectservicedetail.getId() == null) {
            projectservicedetail.setId(UUID.randomUUID());
        }
        mapper.save(projectservicedetail);
        BatchStatement batch = new BatchStatement();
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", projectservicedetail.getProjectid())
            .setUUID("id", projectservicedetail.getId()));
        session.execute(batch);
        return projectservicedetail;
    }

    public void delete(UUID id) {

        mapper.delete(id);
//        BatchStatement batch = new BatchStatement();
//        batch.add(deleteByProjectStmt.bind().setUUID("id", id));
//        session.execute(batch);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
