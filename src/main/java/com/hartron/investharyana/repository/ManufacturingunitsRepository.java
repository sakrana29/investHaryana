package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Manufacturingunits;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Manufacturingunits entity.
 */
@Repository
public class ManufacturingunitsRepository {

    private final Session session;

    private Mapper<Manufacturingunits> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ManufacturingunitsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Manufacturingunits.class);
        this.findAllStmt = session.prepare("SELECT * FROM manufacturingunits");
        this.truncateStmt = session.prepare("TRUNCATE manufacturingunits");
    }

    public List<Manufacturingunits> findAll() {
        List<Manufacturingunits> manufacturingunitsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Manufacturingunits manufacturingunits = new Manufacturingunits();
                manufacturingunits.setId(row.getUUID("id"));
                manufacturingunits.setUnittypes(row.getString("unittypes"));
                return manufacturingunits;
            }
        ).forEach(manufacturingunitsList::add);
        return manufacturingunitsList;
    }

    public Manufacturingunits findOne(UUID id) {
        return mapper.get(id);
    }

    public Manufacturingunits save(Manufacturingunits manufacturingunits) {
        if (manufacturingunits.getId() == null) {
            manufacturingunits.setId(UUID.randomUUID());
        }
        mapper.save(manufacturingunits);
        return manufacturingunits;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
