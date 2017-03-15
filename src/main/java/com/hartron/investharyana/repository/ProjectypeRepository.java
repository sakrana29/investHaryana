package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectype;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectype entity.
 */
@Repository
public class ProjectypeRepository {

    private final Session session;

    private Mapper<Projectype> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectypeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectype.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectype");
        this.truncateStmt = session.prepare("TRUNCATE projectype");
    }

    public List<Projectype> findAll() {
        List<Projectype> projectypesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectype projectype = new Projectype();
                projectype.setId(row.getUUID("id"));
                projectype.setProjectypes(row.getString("projectypes"));
                return projectype;
            }
        ).forEach(projectypesList::add);
        return projectypesList;
    }

    public Projectype findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectype save(Projectype projectype) {
        if (projectype.getId() == null) {
            projectype.setId(UUID.randomUUID());
        }
        mapper.save(projectype);
        return projectype;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
