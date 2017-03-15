package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Landusezoneclassification;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Landusezoneclassification entity.
 */
@Repository
public class LandusezoneclassificationRepository {

    private final Session session;

    private Mapper<Landusezoneclassification> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public LandusezoneclassificationRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Landusezoneclassification.class);
        this.findAllStmt = session.prepare("SELECT * FROM landusezoneclassification");
        this.truncateStmt = session.prepare("TRUNCATE landusezoneclassification");
    }

    public List<Landusezoneclassification> findAll() {
        List<Landusezoneclassification> landusezoneclassificationsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Landusezoneclassification landusezoneclassification = new Landusezoneclassification();
                landusezoneclassification.setId(row.getUUID("id"));
                landusezoneclassification.setLandzoneclassificationtype(row.getString("landzoneclassificationtype"));
                return landusezoneclassification;
            }
        ).forEach(landusezoneclassificationsList::add);
        return landusezoneclassificationsList;
    }

    public Landusezoneclassification findOne(UUID id) {
        return mapper.get(id);
    }

    public Landusezoneclassification save(Landusezoneclassification landusezoneclassification) {
        if (landusezoneclassification.getId() == null) {
            landusezoneclassification.setId(UUID.randomUUID());
        }
        mapper.save(landusezoneclassification);
        return landusezoneclassification;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
