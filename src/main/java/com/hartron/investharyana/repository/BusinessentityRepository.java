package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Businessentity;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Businessentity entity.
 */
@Repository
public class BusinessentityRepository {

    private final Session session;

    private Mapper<Businessentity> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public BusinessentityRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Businessentity.class);
        this.findAllStmt = session.prepare("SELECT * FROM businessentity");
        this.truncateStmt = session.prepare("TRUNCATE businessentity");
    }

    public List<Businessentity> findAll() {
        List<Businessentity> businessentitiesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Businessentity businessentity = new Businessentity();
                businessentity.setId(row.getUUID("id"));
                businessentity.setBusinessentitytype(row.getString("businessentitytype"));
                return businessentity;
            }
        ).forEach(businessentitiesList::add);
        return businessentitiesList;
    }

    public Businessentity findOne(UUID id) {
        return mapper.get(id);
    }

    public Businessentity save(Businessentity businessentity) {
        if (businessentity.getId() == null) {
            businessentity.setId(UUID.randomUUID());
        }
        mapper.save(businessentity);
        return businessentity;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
