package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Emissiondetail;

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
 * Cassandra repository for the Emissiondetail entity.
 */
@Repository
public class EmissiondetailRepository {

    private final Session session;

    private Mapper<Emissiondetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findAllByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public EmissiondetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Emissiondetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM emissiondetail");
        this.truncateStmt = session.prepare("TRUNCATE emissiondetail");
        this.findAllByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM emissiondetail_by_projectid " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO emissiondetail_by_projectid (projectid, id) " +
                "VALUES (:projectid, :id)");

        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM emissiondetail_by_projectid " +
                "WHERE projectid = :projectid");
    }

    public List<Emissiondetail> findAll() {
        List<Emissiondetail> emissiondetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Emissiondetail emissiondetail = new Emissiondetail();
                emissiondetail.setId(row.getUUID("id"));
                emissiondetail.setCapacity(row.getString("capacity"));
                emissiondetail.setParticulars(row.getString("particulars"));
                emissiondetail.setType_of_fuel(row.getString("type_of_fuel"));
                emissiondetail.setAir_pollution_control_device(row.getString("air_pollution_control_device"));
                emissiondetail.setCreatedate(row.get("createdate", ZonedDateTime.class));
                emissiondetail.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                emissiondetail.setProjectid(row.getUUID("projectid"));
                return emissiondetail;
            }
        ).forEach(emissiondetailsList::add);
        return emissiondetailsList;
    }

    public Emissiondetail findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Emissiondetail> findAllByProjectid(UUID projectid) {
        BoundStatement stmt = findAllByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndex(stmt);
    }
    private List<Emissiondetail> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Emissiondetail> emissiondetailList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Emissiondetail emissiondetail=new Emissiondetail();
            emissiondetail= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            emissiondetailList.add(emissiondetail);
        }
        return emissiondetailList;
    }

    public Emissiondetail save(Emissiondetail emissiondetail) {
        if (emissiondetail.getId() == null) {
            emissiondetail.setId(UUID.randomUUID());
        }

        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(emissiondetail));
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", emissiondetail.getProjectid())
            .setUUID("id", emissiondetail.getId()));
        session.execute(batch);

        return emissiondetail;
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
