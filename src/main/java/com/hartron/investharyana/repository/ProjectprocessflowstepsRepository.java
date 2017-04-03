package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectprocessflowsteps;

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
 * Cassandra repository for the Projectprocessflowsteps entity.
 */
@Repository
public class ProjectprocessflowstepsRepository {

    private final Session session;

    private Mapper<Projectprocessflowsteps> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findAllByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public ProjectprocessflowstepsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectprocessflowsteps.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectprocessflowsteps");
        this.truncateStmt = session.prepare("TRUNCATE projectprocessflowsteps");
        this.findAllByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM projectprocessflowsteps_by_projectid " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO projectprocessflowsteps_by_projectid (projectid, id) " +
                "VALUES (:projectid, :id)");

        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM projectprocessflowsteps_by_projectid " +
                "WHERE projectid = :projectid");
    }

    public List<Projectprocessflowsteps> findAll() {
        List<Projectprocessflowsteps> projectprocessflowstepsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectprocessflowsteps projectprocessflowsteps = new Projectprocessflowsteps();
                projectprocessflowsteps.setId(row.getUUID("id"));
                projectprocessflowsteps.setSteps(row.getString("steps"));
                projectprocessflowsteps.setCreatedate(row.get("createdate", ZonedDateTime.class));
                projectprocessflowsteps.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                projectprocessflowsteps.setProjectid(row.getUUID("projectid"));
                return projectprocessflowsteps;
            }
        ).forEach(projectprocessflowstepsList::add);
        return projectprocessflowstepsList;
    }

    public Projectprocessflowsteps findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Projectprocessflowsteps> findAllByProjectid(UUID projectid) {
        BoundStatement stmt = findAllByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndex(stmt);
    }
    private List<Projectprocessflowsteps> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Projectprocessflowsteps> projectprocessflowstepsList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Projectprocessflowsteps projectprocessflowsteps=new Projectprocessflowsteps();
            projectprocessflowsteps= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            projectprocessflowstepsList.add(projectprocessflowsteps);
        }
        return projectprocessflowstepsList;
    }

    public Projectprocessflowsteps save(Projectprocessflowsteps projectprocessflowsteps) {
        if (projectprocessflowsteps.getId() == null) {
            projectprocessflowsteps.setId(UUID.randomUUID());
        }

        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(projectprocessflowsteps));
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", projectprocessflowsteps.getProjectid())
            .setUUID("id", projectprocessflowsteps.getId()));
        session.execute(batch);

        return projectprocessflowsteps;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }

    public void deleteByProject(UUID projectid) {
        BatchStatement batch = new BatchStatement();
        batch.add(deleteByProjectStmt.bind()
            .setUUID("projectid", projectid));
        session.execute(batch);
    }
}
