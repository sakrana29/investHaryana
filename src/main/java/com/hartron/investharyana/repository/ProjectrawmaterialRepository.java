package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectrawmaterial;

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
 * Cassandra repository for the Projectrawmaterial entity.
 */
@Repository
public class ProjectrawmaterialRepository {

    private final Session session;

    private Mapper<Projectrawmaterial> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findAllByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public ProjectrawmaterialRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectrawmaterial.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectrawmaterial");
        this.truncateStmt = session.prepare("TRUNCATE projectrawmaterial");
        this.findAllByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM projectrawmaterial_by_projectid " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO projectrawmaterial_by_projectid (projectid, id) " +
                "VALUES (:projectid, :id)");

        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM projectrawmaterial_by_projectid " +
                "WHERE projectid = :projectid");
    }

    public List<Projectrawmaterial> findAll() {
        List<Projectrawmaterial> projectrawmaterialsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectrawmaterial projectrawmaterial = new Projectrawmaterial();
                projectrawmaterial.setId(row.getUUID("id"));
                projectrawmaterial.setRawmaterial(row.getString("rawmaterial"));
                projectrawmaterial.setQuantity(row.getInt("quantity"));
                projectrawmaterial.setUnits(row.getString("units"));
                projectrawmaterial.setCreatedate(row.get("createdate", ZonedDateTime.class));
                projectrawmaterial.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                projectrawmaterial.setProjectid(row.getUUID("projectid"));
                return projectrawmaterial;
            }
        ).forEach(projectrawmaterialsList::add);
        return projectrawmaterialsList;
    }

    public Projectrawmaterial findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Projectrawmaterial> findAllByProjectid(UUID projectid) {
        BoundStatement stmt = findAllByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndex(stmt);
    }
    private List<Projectrawmaterial> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Projectrawmaterial> projectrawmaterials=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Projectrawmaterial projectrawmaterial=new Projectrawmaterial();
            projectrawmaterial= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            projectrawmaterials.add(projectrawmaterial);
        }
        return projectrawmaterials;
    }


    public Projectrawmaterial save(Projectrawmaterial projectrawmaterial) {
        if (projectrawmaterial.getId() == null) {
            projectrawmaterial.setId(UUID.randomUUID());
        }
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(projectrawmaterial));
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", projectrawmaterial.getProjectid())
            .setUUID("id", projectrawmaterial.getId()));
        session.execute(batch);
        return projectrawmaterial;
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
