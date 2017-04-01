package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Term_declaration_accept;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Term_declaration_accept entity.
 */
@Repository
public class Term_declaration_acceptRepository {

    private final Session session;

    private Mapper<Term_declaration_accept> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Term_declaration_acceptRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Term_declaration_accept.class);
        this.findAllStmt = session.prepare("SELECT * FROM term_declaration_accept");
        this.truncateStmt = session.prepare("TRUNCATE term_declaration_accept");
    }

    public List<Term_declaration_accept> findAll() {
        List<Term_declaration_accept> term_declaration_acceptsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Term_declaration_accept term_declaration_accept = new Term_declaration_accept();
                term_declaration_accept.setId(row.getUUID("id"));
                term_declaration_accept.setAcceptance(row.getBool("acceptance"));
                term_declaration_accept.setApplicationdate(row.get("applicationdate", ZonedDateTime.class));
                term_declaration_accept.setPlace(row.getString("place"));
                term_declaration_accept.setCreatedate(row.get("createdate", ZonedDateTime.class));
                term_declaration_accept.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                return term_declaration_accept;
            }
        ).forEach(term_declaration_acceptsList::add);
        return term_declaration_acceptsList;
    }

    public Term_declaration_accept findOne(UUID id) {
        return mapper.get(id);
    }

    public Term_declaration_accept save(Term_declaration_accept term_declaration_accept) {
        if (term_declaration_accept.getId() == null) {
            term_declaration_accept.setId(UUID.randomUUID());
        }
        mapper.save(term_declaration_accept);
        return term_declaration_accept;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
