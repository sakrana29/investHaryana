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
                projectservicedetail.setIsRequired(row.getBool("isRequired"));
                projectservicedetail.setRequireMarkedOnDate(row.get("requireMarkedOnDate", ZonedDateTime.class));
                projectservicedetail.setRequireMarkedBy(row.getString("requireMarkedBy"));
                projectservicedetail.setIsAssigned(row.getBool("isAssigned"));
                projectservicedetail.setAssigOnDate(row.get("assigOnDate", ZonedDateTime.class));
                projectservicedetail.setAssignBy(row.getString("assignBy"));
                projectservicedetail.setFormFilledStatus(row.getBool("formFilledStatus"));
                projectservicedetail.setIsPaymentMade(row.getBool("isPaymentMade"));
                projectservicedetail.setIsPaymentVerified(row.getBool("isPaymentVerified"));
                projectservicedetail.setFormFilledOnDate(row.get("formFilledOnDate", ZonedDateTime.class));
                projectservicedetail.setFormFilledBy(row.getString("formFilledBy"));
                projectservicedetail.setPaymentMadeOnDate(row.get("paymentMadeOnDate", ZonedDateTime.class));
                projectservicedetail.setStatus(row.getString("status"));
                projectservicedetail.setLatestComments(row.getString("latestComments"));
                projectservicedetail.setServiceFee(row.getDecimal("serviceFee"));
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
