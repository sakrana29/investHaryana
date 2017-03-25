package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Wwtreatmenttwo;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Wwtreatmenttwo entity.
 */
@Repository
public class WwtreatmenttwoRepository {

    private final Session session;

    private Mapper<Wwtreatmenttwo> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public WwtreatmenttwoRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Wwtreatmenttwo.class);
        this.findAllStmt = session.prepare("SELECT * FROM wwtreatmenttwo");
        this.truncateStmt = session.prepare("TRUNCATE wwtreatmenttwo");
    }

    public List<Wwtreatmenttwo> findAll() {
        List<Wwtreatmenttwo> wwtreatmenttwosList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Wwtreatmenttwo wwtreatmenttwo = new Wwtreatmenttwo();
                wwtreatmenttwo.setId(row.getUUID("id"));
                wwtreatmenttwo.setTreatment2(row.getString("treatment2"));
                return wwtreatmenttwo;
            }
        ).forEach(wwtreatmenttwosList::add);
        return wwtreatmenttwosList;
    }

    public Wwtreatmenttwo findOne(UUID id) {
        return mapper.get(id);
    }

    public Wwtreatmenttwo save(Wwtreatmenttwo wwtreatmenttwo) {
        if (wwtreatmenttwo.getId() == null) {
            wwtreatmenttwo.setId(UUID.randomUUID());
        }
        mapper.save(wwtreatmenttwo);
        return wwtreatmenttwo;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
