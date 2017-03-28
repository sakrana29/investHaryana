package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Project_phase;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public Project_phaseRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Project_phase.class);
        this.findAllStmt = session.prepare("SELECT * FROM project_phase");
        this.truncateStmt = session.prepare("TRUNCATE project_phase");
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
                project_phase.setProjectname(row.getString("projectname"));
                return project_phase;
            }
        ).forEach(project_phasesList::add);
        return project_phasesList;
    }

    public Project_phase findOne(UUID id) {
        return mapper.get(id);
    }

    public Project_phase save(Project_phase project_phase) {
        if (project_phase.getId() == null) {
            project_phase.setId(UUID.randomUUID());
        }
        mapper.save(project_phase);
        return project_phase;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
