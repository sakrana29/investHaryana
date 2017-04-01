package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Project_phase;

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
 * Cassandra repository for the Project_phase entity.
 */
@Repository
public class Project_phaseRepository {

    private final Session session;

    private Mapper<Project_phase> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findAllByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public Project_phaseRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Project_phase.class);
        this.findAllStmt = session.prepare("SELECT * FROM project_phase");
        this.truncateStmt = session.prepare("TRUNCATE project_phase");
        this.findAllByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM projectphase_by_projectid " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO projectphase_by_projectid (projectid, id) " +
                "VALUES (:projectid, :id)");

        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM projectphase_by_projectid " +
                "WHERE projectid = :projectid");
    }

    public List<Project_phase> findAll() {
        List<Project_phase> project_phasesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Project_phase project_phase = new Project_phase();
                project_phase.setId(row.getUUID("id"));
                project_phase.setProjectid(row.getUUID("projectid"));
                project_phase.setPhase(row.getString("phase"));
                project_phase.setProductcategory(row.getString("productcategory"));
                project_phase.setFci(row.getString("fci"));
                project_phase.setImplementationdate(row.get("implementationdate", ZonedDateTime.class));
                project_phase.setCreatedate(row.get("createdate", ZonedDateTime.class));
                project_phase.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                return project_phase;
            }
        ).forEach(project_phasesList::add);
        return project_phasesList;
    }

    public Project_phase findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Project_phase> findAllByProjectid(UUID projectid) {
        BoundStatement stmt = findAllByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndex(stmt);
    }
    private List<Project_phase> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Project_phase> project_phaseList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Project_phase project_phase=new Project_phase();
            project_phase= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            project_phaseList.add(project_phase);
        }
        return project_phaseList;
    }


    public Project_phase save(Project_phase project_phase) {
        if (project_phase.getId() == null) {
            project_phase.setId(UUID.randomUUID());
        }
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(project_phase));
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", project_phase.getProjectid())
            .setUUID("id", project_phase.getId()));
        session.execute(batch);
        return project_phase;
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
