package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Wwtreatmentone;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Wwtreatmentone entity.
 */
@Repository
public class WwtreatmentoneRepository {

    private final Session session;

    private Mapper<Wwtreatmentone> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public WwtreatmentoneRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Wwtreatmentone.class);
        this.findAllStmt = session.prepare("SELECT * FROM wwtreatmentone");
        this.truncateStmt = session.prepare("TRUNCATE wwtreatmentone");
    }

    public List<Wwtreatmentone> findAll() {
        List<Wwtreatmentone> wwtreatmentonesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Wwtreatmentone wwtreatmentone = new Wwtreatmentone();
                wwtreatmentone.setId(row.getUUID("id"));
                wwtreatmentone.setTreatment1(row.getString("treatment1"));
                return wwtreatmentone;
            }
        ).forEach(wwtreatmentonesList::add);
        return wwtreatmentonesList;
    }

    public Wwtreatmentone findOne(UUID id) {
        return mapper.get(id);
    }

    public Wwtreatmentone save(Wwtreatmentone wwtreatmentone) {
        if (wwtreatmentone.getId() == null) {
            wwtreatmentone.setId(UUID.randomUUID());
        }
        mapper.save(wwtreatmentone);
        return wwtreatmentone;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
