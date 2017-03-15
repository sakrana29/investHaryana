package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Regular_electrict_load_type;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Regular_electrict_load_type entity.
 */
@Repository
public class Regular_electrict_load_typeRepository {

    private final Session session;

    private Mapper<Regular_electrict_load_type> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Regular_electrict_load_typeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Regular_electrict_load_type.class);
        this.findAllStmt = session.prepare("SELECT * FROM regular_electrict_load_type");
        this.truncateStmt = session.prepare("TRUNCATE regular_electrict_load_type");
    }

    public List<Regular_electrict_load_type> findAll() {
        List<Regular_electrict_load_type> regular_electrict_load_typesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Regular_electrict_load_type regular_electrict_load_type = new Regular_electrict_load_type();
                regular_electrict_load_type.setId(row.getUUID("id"));
                regular_electrict_load_type.setTypeofload(row.getString("typeofload"));
                return regular_electrict_load_type;
            }
        ).forEach(regular_electrict_load_typesList::add);
        return regular_electrict_load_typesList;
    }

    public Regular_electrict_load_type findOne(UUID id) {
        return mapper.get(id);
    }

    public Regular_electrict_load_type save(Regular_electrict_load_type regular_electrict_load_type) {
        if (regular_electrict_load_type.getId() == null) {
            regular_electrict_load_type.setId(UUID.randomUUID());
        }
        mapper.save(regular_electrict_load_type);
        return regular_electrict_load_type;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
