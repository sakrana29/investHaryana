package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectserviceformfielddata;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

    public ProjectserviceformfielddataRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectserviceformfielddata.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectserviceformfielddata");
        this.truncateStmt = session.prepare("TRUNCATE projectserviceformfielddata");
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
                projectserviceformfielddata.setProjectserviceformfieldvalue(row.getUUID("projectserviceformfieldvalue"));
                projectserviceformfielddata.setProjectid(row.getUUID("projectid"));
                return projectserviceformfielddata;
            }
        ).forEach(projectserviceformfielddataList::add);
        return projectserviceformfielddataList;
    }

    public Projectserviceformfielddata findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectserviceformfielddata save(Projectserviceformfielddata projectserviceformfielddata) {
        if (projectserviceformfielddata.getId() == null) {
            projectserviceformfielddata.setId(UUID.randomUUID());
        }
        mapper.save(projectserviceformfielddata);
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
