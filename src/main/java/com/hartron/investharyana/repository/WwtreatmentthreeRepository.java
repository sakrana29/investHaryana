package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Wwtreatmentthree;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Wwtreatmentthree entity.
 */
@Repository
public class WwtreatmentthreeRepository {

    private final Session session;

    private Mapper<Wwtreatmentthree> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public WwtreatmentthreeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Wwtreatmentthree.class);
        this.findAllStmt = session.prepare("SELECT * FROM wwtreatmentthree");
        this.truncateStmt = session.prepare("TRUNCATE wwtreatmentthree");
    }

    public List<Wwtreatmentthree> findAll() {
        List<Wwtreatmentthree> wwtreatmentthreesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Wwtreatmentthree wwtreatmentthree = new Wwtreatmentthree();
                wwtreatmentthree.setId(row.getUUID("id"));
                wwtreatmentthree.setTreatment3(row.getString("treatment3"));
                return wwtreatmentthree;
            }
        ).forEach(wwtreatmentthreesList::add);
        return wwtreatmentthreesList;
    }

    public Wwtreatmentthree findOne(UUID id) {
        return mapper.get(id);
    }

    public Wwtreatmentthree save(Wwtreatmentthree wwtreatmentthree) {
        if (wwtreatmentthree.getId() == null) {
            wwtreatmentthree.setId(UUID.randomUUID());
        }
        mapper.save(wwtreatmentthree);
        return wwtreatmentthree;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
