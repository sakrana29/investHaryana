package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Businessentitys;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Businessentitys entity.
 */
@Repository
public class BusinessentitysRepository {

    private final Session session;

    private Mapper<Businessentitys> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public BusinessentitysRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Businessentitys.class);
        this.findAllStmt = session.prepare("SELECT * FROM businessentitys");
        this.truncateStmt = session.prepare("TRUNCATE businessentitys");
    }

    public List<Businessentitys> findAll() {
        List<Businessentitys> businessentitysList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Businessentitys businessentitys = new Businessentitys();
                businessentitys.setId(row.getUUID("id"));
                businessentitys.setBusinessentitytype(row.getString("businessentitytype"));
                return businessentitys;
            }
        ).forEach(businessentitysList::add);
        return businessentitysList;
    }

    public Businessentitys findOne(UUID id) {
        return mapper.get(id);
    }

    public Businessentitys save(Businessentitys businessentitys) {
        if (businessentitys.getId() == null) {
            businessentitys.setId(UUID.randomUUID());
        }
        mapper.save(businessentitys);
        return businessentitys;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
