package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Filetesting;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Filetesting entity.
 */
@Repository
public class FiletestingRepository {

    private final Session session;

    private Mapper<Filetesting> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public FiletestingRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Filetesting.class);
        this.findAllStmt = session.prepare("SELECT * FROM filetesting");
        this.truncateStmt = session.prepare("TRUNCATE filetesting");
    }

    public List<Filetesting> findAll() {
        List<Filetesting> filetestingsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Filetesting filetesting = new Filetesting();
                filetesting.setId(row.getUUID("id"));
                filetesting.setFilename(row.getString("filename"));
                return filetesting;
            }
        ).forEach(filetestingsList::add);
        return filetestingsList;
    }

    public Filetesting findOne(UUID id) {
        return mapper.get(id);
    }

    public Filetesting save(Filetesting filetesting) {
        if (filetesting.getId() == null) {
            filetesting.setId(UUID.randomUUID());
        }
        mapper.save(filetesting);
        return filetesting;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
