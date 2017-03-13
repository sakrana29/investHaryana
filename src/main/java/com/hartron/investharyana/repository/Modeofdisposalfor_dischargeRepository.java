package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Modeofdisposalfor_discharge;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Modeofdisposalfor_discharge entity.
 */
@Repository
public class Modeofdisposalfor_dischargeRepository {

    private final Session session;

    private Mapper<Modeofdisposalfor_discharge> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Modeofdisposalfor_dischargeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Modeofdisposalfor_discharge.class);
        this.findAllStmt = session.prepare("SELECT * FROM modeofdisposalfor_discharge");
        this.truncateStmt = session.prepare("TRUNCATE modeofdisposalfor_discharge");
    }

    public List<Modeofdisposalfor_discharge> findAll() {
        List<Modeofdisposalfor_discharge> modeofdisposalfor_dischargesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Modeofdisposalfor_discharge modeofdisposalfor_discharge = new Modeofdisposalfor_discharge();
                modeofdisposalfor_discharge.setId(row.getUUID("id"));
                modeofdisposalfor_discharge.setDisposal_for_discharge(row.getString("disposal_for_discharge"));
                return modeofdisposalfor_discharge;
            }
        ).forEach(modeofdisposalfor_dischargesList::add);
        return modeofdisposalfor_dischargesList;
    }

    public Modeofdisposalfor_discharge findOne(UUID id) {
        return mapper.get(id);
    }

    public Modeofdisposalfor_discharge save(Modeofdisposalfor_discharge modeofdisposalfor_discharge) {
        if (modeofdisposalfor_discharge.getId() == null) {
            modeofdisposalfor_discharge.setId(UUID.randomUUID());
        }
        mapper.save(modeofdisposalfor_discharge);
        return modeofdisposalfor_discharge;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
