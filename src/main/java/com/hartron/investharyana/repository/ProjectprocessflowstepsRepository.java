package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectprocessflowsteps;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public ProjectprocessflowstepsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectprocessflowsteps.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectprocessflowsteps");
        this.truncateStmt = session.prepare("TRUNCATE projectprocessflowsteps");
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
                return projectprocessflowsteps;
            }
        ).forEach(projectprocessflowstepsList::add);
        return projectprocessflowstepsList;
    }

    public Projectprocessflowsteps findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectprocessflowsteps save(Projectprocessflowsteps projectprocessflowsteps) {
        if (projectprocessflowsteps.getId() == null) {
            projectprocessflowsteps.setId(UUID.randomUUID());
        }
        mapper.save(projectprocessflowsteps);
        return projectprocessflowsteps;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
