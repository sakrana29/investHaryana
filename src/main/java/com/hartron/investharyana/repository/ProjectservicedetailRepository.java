package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectservicedetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectservicedetail entity.
 */
@Repository
public class ProjectservicedetailRepository {

    private final Session session;

    private Mapper<Projectservicedetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectservicedetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectservicedetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectservicedetail");
        this.truncateStmt = session.prepare("TRUNCATE projectservicedetail");
    }

    public List<Projectservicedetail> findAll() {
        List<Projectservicedetail> projectservicedetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectservicedetail projectservicedetail = new Projectservicedetail();
                projectservicedetail.setId(row.getUUID("id"));
                projectservicedetail.setProjectid(row.getUUID("projectid"));
                projectservicedetail.setServiceid(row.getUUID("serviceid"));
                projectservicedetail.setUserlogin(row.getString("userlogin"));
                projectservicedetail.setServicerequired(row.getBool("servicerequired"));
                projectservicedetail.setServicestatus(row.getString("servicestatus"));
                projectservicedetail.setAssigndate(row.get("assigndate", ZonedDateTime.class));
                projectservicedetail.setServicefee(row.getDouble("servicefee"));
                projectservicedetail.setRemarks(row.getString("remarks"));
                return projectservicedetail;
            }
        ).forEach(projectservicedetailsList::add);
        return projectservicedetailsList;
    }

    public Projectservicedetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectservicedetail save(Projectservicedetail projectservicedetail) {
        if (projectservicedetail.getId() == null) {
            projectservicedetail.setId(UUID.randomUUID());
        }
        mapper.save(projectservicedetail);
        return projectservicedetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
