package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Watersupplysource;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Watersupplysource entity.
 */
@Repository
public class WatersupplysourceRepository {

    private final Session session;

    private Mapper<Watersupplysource> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public WatersupplysourceRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Watersupplysource.class);
        this.findAllStmt = session.prepare("SELECT * FROM watersupplysource");
        this.truncateStmt = session.prepare("TRUNCATE watersupplysource");
    }

    public List<Watersupplysource> findAll() {
        List<Watersupplysource> watersupplysourcesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Watersupplysource watersupplysource = new Watersupplysource();
                watersupplysource.setId(row.getUUID("id"));
                watersupplysource.setWatersupplysourcetype(row.getString("watersupplysourcetype"));
                return watersupplysource;
            }
        ).forEach(watersupplysourcesList::add);
        return watersupplysourcesList;
    }

    public Watersupplysource findOne(UUID id) {
        return mapper.get(id);
    }

    public Watersupplysource save(Watersupplysource watersupplysource) {
        if (watersupplysource.getId() == null) {
            watersupplysource.setId(UUID.randomUUID());
        }
        mapper.save(watersupplysource);
        return watersupplysource;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
