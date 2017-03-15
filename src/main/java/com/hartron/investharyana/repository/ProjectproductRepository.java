package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectproduct;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectproduct entity.
 */
@Repository
public class ProjectproductRepository {

    private final Session session;

    private Mapper<Projectproduct> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectproductRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectproduct.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectproduct");
        this.truncateStmt = session.prepare("TRUNCATE projectproduct");
    }

    public List<Projectproduct> findAll() {
        List<Projectproduct> projectproductsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectproduct projectproduct = new Projectproduct();
                projectproduct.setId(row.getUUID("id"));
                projectproduct.setProjectid(row.getUUID("projectid"));
                projectproduct.setMainproduct(row.getString("mainproduct"));
                projectproduct.setQuantity(row.getInt("quantity"));
                projectproduct.setUnits(row.getUUID("units"));
                return projectproduct;
            }
        ).forEach(projectproductsList::add);
        return projectproductsList;
    }

    public Projectproduct findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectproduct save(Projectproduct projectproduct) {
        if (projectproduct.getId() == null) {
            projectproduct.setId(UUID.randomUUID());
        }
        mapper.save(projectproduct);
        return projectproduct;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
