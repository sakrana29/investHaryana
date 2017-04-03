package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectproduct;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private PreparedStatement findAllByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public ProjectproductRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectproduct.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectproduct");
        this.truncateStmt = session.prepare("TRUNCATE projectproduct");

        this.findAllByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM projectproduct_by_projectid " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO projectproduct_by_projectid (projectid, id) " +
                "VALUES (:projectid, :id)");

        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM projectproduct_by_projectid " +
                "WHERE projectid = :projectid");
    }

    public List<Projectproduct> findAll() {
        List<Projectproduct> projectproductsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectproduct projectproduct = new Projectproduct();
                projectproduct.setId(row.getUUID("id"));
                projectproduct.setMainproduct(row.getString("mainproduct"));
                projectproduct.setQuantity(row.getInt("quantity"));
                projectproduct.setUnits(row.getString("units"));
                projectproduct.setCreatedate(row.get("createdate", ZonedDateTime.class));
                projectproduct.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                projectproduct.setProjectid(row.getUUID("projectid"));
                return projectproduct;
            }
        ).forEach(projectproductsList::add);
        return projectproductsList;
    }

    public Projectproduct findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Projectproduct> findAllByProjectid(UUID projectid) {
        BoundStatement stmt = findAllByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndex(stmt);
    }
    private List<Projectproduct> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Projectproduct> projectproductList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Projectproduct projectproduct=new Projectproduct();
            projectproduct= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            projectproductList.add(projectproduct);
        }
        return projectproductList;
    }

    public Projectproduct save(Projectproduct projectproduct) {
        if (projectproduct.getId() == null) {
            projectproduct.setId(UUID.randomUUID());
        }
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(projectproduct));
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", projectproduct.getProjectid())
            .setUUID("id", projectproduct.getId()));
        session.execute(batch);

        return projectproduct;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
    public void deleteByProject(UUID projectid) {
        BatchStatement batch = new BatchStatement();
        batch.add(deleteByProjectStmt.bind()
            .setUUID("projectid", projectid));
        session.execute(batch);
    }
}
