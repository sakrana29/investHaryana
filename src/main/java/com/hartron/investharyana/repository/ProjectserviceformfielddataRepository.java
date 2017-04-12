package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectserviceformfielddata;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.hartron.investharyana.domain.ServiceFormField;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the Projectserviceformfielddata entity.
 */
@Repository
public class ProjectserviceformfielddataRepository {

    private final Session session;

    private Mapper<Projectserviceformfielddata> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;
    private PreparedStatement findAllByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public ProjectserviceformfielddataRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectserviceformfielddata.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectserviceformfielddata");
        this.truncateStmt = session.prepare("TRUNCATE projectserviceformfielddata");
        this.findAllByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM projectserviceformfielddata_by_project " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO projectserviceformfielddata_by_project (projectid, id) " +
                "VALUES (:projectid, :id)");

        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM projectserviceformfielddata_by_project " +
                "WHERE projectid = :projectid");

    }

    public List<Projectserviceformfielddata> findAll() {
        List<Projectserviceformfielddata> projectserviceformfielddataList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectserviceformfielddata projectserviceformfielddata = new Projectserviceformfielddata();
                projectserviceformfielddata.setId(row.getUUID("id"));
                projectserviceformfielddata.setServiceid(row.getUUID("serviceid"));
                projectserviceformfielddata.setFormfieldvalue(row.getString("formfieldvalue"));
                projectserviceformfielddata.setProjectid(row.getUUID("projectid"));
                projectserviceformfielddata.setFormfieldName(row.getString("formfieldName"));
                projectserviceformfielddata.setServiceformfieldid(row.getUUID("serviceformfieldid"));
                projectserviceformfielddata.setFormfieldtype(row.getString("formfieldtype"));
                projectserviceformfielddata.setFormfieldOrder(row.getInt("formfieldOrder"));
                projectserviceformfielddata.setFormtypeOption(row.getString("formtypeOption"));
                return projectserviceformfielddata;
            }
        ).forEach(projectserviceformfielddataList::add);
        return projectserviceformfielddataList;
    }

    public List<Projectserviceformfielddata> findAllByProjectid(UUID projectid) {
        BoundStatement stmt = findAllByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndex(stmt);
    }
    private List<Projectserviceformfielddata> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Projectserviceformfielddata> projectserviceformfielddataArrayList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Projectserviceformfielddata projectserviceformfielddata=new Projectserviceformfielddata();
            projectserviceformfielddata= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            projectserviceformfielddataArrayList.add(projectserviceformfielddata);
        }
        return projectserviceformfielddataArrayList;
    }

    public Projectserviceformfielddata findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectserviceformfielddata save(Projectserviceformfielddata projectserviceformfielddata) {
        if (projectserviceformfielddata.getId() == null) {
            projectserviceformfielddata.setId(UUID.randomUUID());
        }
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(projectserviceformfielddata));
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", projectserviceformfielddata.getProjectid())
            .setUUID("id", projectserviceformfielddata.getId()));
        session.execute(batch);
        return projectserviceformfielddata;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
