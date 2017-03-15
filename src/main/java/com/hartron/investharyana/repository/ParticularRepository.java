package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Particular;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Particular entity.
 */
@Repository
public class ParticularRepository {

    private final Session session;

    private Mapper<Particular> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ParticularRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Particular.class);
        this.findAllStmt = session.prepare("SELECT * FROM particular");
        this.truncateStmt = session.prepare("TRUNCATE particular");
    }

    public List<Particular> findAll() {
        List<Particular> particularsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Particular particular = new Particular();
                particular.setId(row.getUUID("id"));
                particular.setParticulars(row.getString("particulars"));
                particular.setDescription(row.getString("description"));
                return particular;
            }
        ).forEach(particularsList::add);
        return particularsList;
    }

    public Particular findOne(UUID id) {
        return mapper.get(id);
    }

    public Particular save(Particular particular) {
        if (particular.getId() == null) {
            particular.setId(UUID.randomUUID());
        }
        mapper.save(particular);
        return particular;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
