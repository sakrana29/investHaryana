package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Waste_water_naturetype;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Waste_water_naturetype entity.
 */
@Repository
public class Waste_water_naturetypeRepository {

    private final Session session;

    private Mapper<Waste_water_naturetype> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Waste_water_naturetypeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Waste_water_naturetype.class);
        this.findAllStmt = session.prepare("SELECT * FROM waste_water_naturetype");
        this.truncateStmt = session.prepare("TRUNCATE waste_water_naturetype");
    }

    public List<Waste_water_naturetype> findAll() {
        List<Waste_water_naturetype> waste_water_naturetypesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Waste_water_naturetype waste_water_naturetype = new Waste_water_naturetype();
                waste_water_naturetype.setId(row.getUUID("id"));
                waste_water_naturetype.setNaturetype(row.getString("naturetype"));
                return waste_water_naturetype;
            }
        ).forEach(waste_water_naturetypesList::add);
        return waste_water_naturetypesList;
    }

    public Waste_water_naturetype findOne(UUID id) {
        return mapper.get(id);
    }

    public Waste_water_naturetype save(Waste_water_naturetype waste_water_naturetype) {
        if (waste_water_naturetype.getId() == null) {
            waste_water_naturetype.setId(UUID.randomUUID());
        }
        mapper.save(waste_water_naturetype);
        return waste_water_naturetype;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
