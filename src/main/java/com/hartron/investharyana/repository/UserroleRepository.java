package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Userrole;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Userrole entity.
 */
@Repository
public class UserroleRepository {

    private final Session session;

    private Mapper<Userrole> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public UserroleRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Userrole.class);
        this.findAllStmt = session.prepare("SELECT * FROM userrole");
        this.truncateStmt = session.prepare("TRUNCATE userrole");
    }

    public List<Userrole> findAll() {
        List<Userrole> userrolesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Userrole userrole = new Userrole();
                userrole.setId(row.getUUID("id"));
                userrole.setUserrole(row.getString("userrole"));
                return userrole;
            }
        ).forEach(userrolesList::add);
        return userrolesList;
    }

    public Userrole findOne(UUID id) {
        return mapper.get(id);
    }

    public Userrole save(Userrole userrole) {
        if (userrole.getId() == null) {
            userrole.setId(UUID.randomUUID());
        }
        mapper.save(userrole);
        return userrole;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
