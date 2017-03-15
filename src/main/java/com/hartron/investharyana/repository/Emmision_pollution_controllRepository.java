package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Emmision_pollution_controll;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Emmision_pollution_controll entity.
 */
@Repository
public class Emmision_pollution_controllRepository {

    private final Session session;

    private Mapper<Emmision_pollution_controll> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Emmision_pollution_controllRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Emmision_pollution_controll.class);
        this.findAllStmt = session.prepare("SELECT * FROM emmision_pollution_controll");
        this.truncateStmt = session.prepare("TRUNCATE emmision_pollution_controll");
    }

    public List<Emmision_pollution_controll> findAll() {
        List<Emmision_pollution_controll> emmision_pollution_controllsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Emmision_pollution_controll emmision_pollution_controll = new Emmision_pollution_controll();
                emmision_pollution_controll.setId(row.getUUID("id"));
                emmision_pollution_controll.setAirpollutioncontroldevice(row.getString("airpollutioncontroldevice"));
                return emmision_pollution_controll;
            }
        ).forEach(emmision_pollution_controllsList::add);
        return emmision_pollution_controllsList;
    }

    public Emmision_pollution_controll findOne(UUID id) {
        return mapper.get(id);
    }

    public Emmision_pollution_controll save(Emmision_pollution_controll emmision_pollution_controll) {
        if (emmision_pollution_controll.getId() == null) {
            emmision_pollution_controll.setId(UUID.randomUUID());
        }
        mapper.save(emmision_pollution_controll);
        return emmision_pollution_controll;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
