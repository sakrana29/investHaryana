package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.ProjectServicePaymentDetails;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the ProjectServicePaymentDetails entity.
 */
@Repository
public class ProjectServicePaymentDetailsRepository {

    private final Session session;

    private Mapper<ProjectServicePaymentDetails> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectServicePaymentDetailsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ProjectServicePaymentDetails.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectServicePaymentDetails");
        this.truncateStmt = session.prepare("TRUNCATE projectServicePaymentDetails");
    }

    public List<ProjectServicePaymentDetails> findAll() {
        List<ProjectServicePaymentDetails> projectServicePaymentDetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                ProjectServicePaymentDetails projectServicePaymentDetails = new ProjectServicePaymentDetails();
                projectServicePaymentDetails.setId(row.getUUID("id"));
                projectServicePaymentDetails.setProjectid(row.getUUID("projectid"));
                projectServicePaymentDetails.setServiceid(row.getUUID("serviceid"));
                projectServicePaymentDetails.setPaymentMade(row.getDecimal("paymentMade"));
                projectServicePaymentDetails.setPaymentMadeBy(row.getString("paymentMadeBy"));
                projectServicePaymentDetails.setPaymentDate(row.get("paymentDate", ZonedDateTime.class));
                projectServicePaymentDetails.setTransactionId(row.getString("transactionId"));
                projectServicePaymentDetails.setPaymentResponse(row.getString("paymentResponse"));
                return projectServicePaymentDetails;
            }
        ).forEach(projectServicePaymentDetailsList::add);
        return projectServicePaymentDetailsList;
    }

    public ProjectServicePaymentDetails findOne(UUID id) {
        return mapper.get(id);
    }

    public ProjectServicePaymentDetails save(ProjectServicePaymentDetails projectServicePaymentDetails) {
        if (projectServicePaymentDetails.getId() == null) {
            projectServicePaymentDetails.setId(UUID.randomUUID());
        }
        mapper.save(projectServicePaymentDetails);
        return projectServicePaymentDetails;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
