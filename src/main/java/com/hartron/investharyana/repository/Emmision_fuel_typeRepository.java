package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Emmision_fuel_type;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Emmision_fuel_type entity.
 */
@Repository
public class Emmision_fuel_typeRepository {

    private final Session session;

    private Mapper<Emmision_fuel_type> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Emmision_fuel_typeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Emmision_fuel_type.class);
        this.findAllStmt = session.prepare("SELECT * FROM emmision_fuel_type");
        this.truncateStmt = session.prepare("TRUNCATE emmision_fuel_type");
    }

    public List<Emmision_fuel_type> findAll() {
        List<Emmision_fuel_type> emmision_fuel_typesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Emmision_fuel_type emmision_fuel_type = new Emmision_fuel_type();
                emmision_fuel_type.setId(row.getUUID("id"));
                emmision_fuel_type.setTypeoffuel(row.getString("typeoffuel"));
                return emmision_fuel_type;
            }
        ).forEach(emmision_fuel_typesList::add);
        return emmision_fuel_typesList;
    }

    public Emmision_fuel_type findOne(UUID id) {
        return mapper.get(id);
    }

    public Emmision_fuel_type save(Emmision_fuel_type emmision_fuel_type) {
        if (emmision_fuel_type.getId() == null) {
            emmision_fuel_type.setId(UUID.randomUUID());
        }
        mapper.save(emmision_fuel_type);
        return emmision_fuel_type;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
