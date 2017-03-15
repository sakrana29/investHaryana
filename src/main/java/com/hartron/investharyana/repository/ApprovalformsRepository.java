package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Approvalforms;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Approvalforms entity.
 */
@Repository
public class ApprovalformsRepository {

    private final Session session;

    private Mapper<Approvalforms> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ApprovalformsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Approvalforms.class);
        this.findAllStmt = session.prepare("SELECT * FROM approvalforms");
        this.truncateStmt = session.prepare("TRUNCATE approvalforms");
    }

    public List<Approvalforms> findAll() {
        List<Approvalforms> approvalformsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Approvalforms approvalforms = new Approvalforms();
                approvalforms.setId(row.getUUID("id"));
                approvalforms.setExistingapprovalforms(row.getString("existingapprovalforms"));
                return approvalforms;
            }
        ).forEach(approvalformsList::add);
        return approvalformsList;
    }

    public Approvalforms findOne(UUID id) {
        return mapper.get(id);
    }

    public Approvalforms save(Approvalforms approvalforms) {
        if (approvalforms.getId() == null) {
            approvalforms.setId(UUID.randomUUID());
        }
        mapper.save(approvalforms);
        return approvalforms;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
