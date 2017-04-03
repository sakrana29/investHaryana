package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Wastewaterdetail;

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
 * Cassandra repository for the Wastewaterdetail entity.
 */
@Repository
public class WastewaterdetailRepository {

    private final Session session;

    private Mapper<Wastewaterdetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findAllByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public WastewaterdetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Wastewaterdetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM wastewaterdetail");
        this.truncateStmt = session.prepare("TRUNCATE wastewaterdetail");

        this.findAllByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM wastewaterdetail_by_projectid " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO wastewaterdetail_by_projectid (projectid, id) " +
                "VALUES (:projectid, :id)");

        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM wastewaterdetail_by_projectid " +
                "WHERE projectid = :projectid");
    }

    public List<Wastewaterdetail> findAll() {
        List<Wastewaterdetail> wastewaterdetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Wastewaterdetail wastewaterdetail = new Wastewaterdetail();
                wastewaterdetail.setId(row.getUUID("id"));
                wastewaterdetail.setSource_of_generation(row.getString("source_of_generation"));
                wastewaterdetail.setQuantity(row.getInt("quantity"));
                wastewaterdetail.setNaturetype(row.getString("naturetype"));
                wastewaterdetail.setMode_of_disposal(row.getString("mode_of_disposal"));
                wastewaterdetail.setCreatedate(row.get("createdate", ZonedDateTime.class));
                wastewaterdetail.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                wastewaterdetail.setProjectid(row.getUUID("projectid"));
                return wastewaterdetail;
            }
        ).forEach(wastewaterdetailsList::add);
        return wastewaterdetailsList;
    }

    public Wastewaterdetail findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Wastewaterdetail> findAllByProjectid(UUID projectid) {
        BoundStatement stmt = findAllByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndex(stmt);
    }
    private List<Wastewaterdetail> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Wastewaterdetail> wastewaterdetailList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Wastewaterdetail wastewaterdetail=new Wastewaterdetail();
            wastewaterdetail= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            wastewaterdetailList.add(wastewaterdetail);
        }
        return wastewaterdetailList;
    }

    public Wastewaterdetail save(Wastewaterdetail wastewaterdetail) {
        if (wastewaterdetail.getId() == null) {
            wastewaterdetail.setId(UUID.randomUUID());
        }
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(wastewaterdetail));
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", wastewaterdetail.getProjectid())
            .setUUID("id", wastewaterdetail.getId()));
        session.execute(batch);

        return wastewaterdetail;
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
