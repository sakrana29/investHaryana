package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.ProjectServicePaymentDetails;

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
 * Cassandra repository for the ProjectServicePaymentDetails entity.
 */
@Repository
public class ProjectServicePaymentDetailsRepository {

    private final Session session;

    private Mapper<ProjectServicePaymentDetails> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findAllByProjectServiceStmt;

    private PreparedStatement insertByProjectServiceStmt;

    private PreparedStatement deleteByProjectServiceStmt;

    public ProjectServicePaymentDetailsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ProjectServicePaymentDetails.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectServicePaymentDetails");
        this.truncateStmt = session.prepare("TRUNCATE projectServicePaymentDetails");
        this.findAllByProjectServiceStmt = session.prepare(
            "SELECT id " +
                "FROM projectServicePaymentDetailsByProjectAndService " +
                "WHERE projectid = :projectid and serviceid = :serviceid");

        this.insertByProjectServiceStmt = session.prepare(
            "INSERT INTO projectServicePaymentDetailsByProjectAndService (projectid,serviceid,id) " +
                "VALUES (:projectid, :serviceid, :id)");

        this.deleteByProjectServiceStmt = session.prepare(
            "DELETE FROM projectServicePaymentDetailsByProjectAndService " +
                "WHERE projectid = :projectid and serviceid = :serviceid");
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

    public List<ProjectServicePaymentDetails> findAllByProjectAndServiceid(UUID projectid,UUID serviceid) {
        BoundStatement stmt = findAllByProjectServiceStmt.bind();
        stmt.setUUID("projectid", projectid);
        stmt.setUUID("serviceid", serviceid);
        return findAllFromIndex(stmt);
    }
    private List<ProjectServicePaymentDetails> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<ProjectServicePaymentDetails> projectServicePaymentDetailsList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            ProjectServicePaymentDetails projectServicePaymentDetails=new ProjectServicePaymentDetails();
            projectServicePaymentDetails= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            projectServicePaymentDetailsList.add(projectServicePaymentDetails);
        }
        return projectServicePaymentDetailsList;
    }

    public ProjectServicePaymentDetails save(ProjectServicePaymentDetails projectServicePaymentDetails) {
        if (projectServicePaymentDetails.getId() == null) {
            projectServicePaymentDetails.setId(UUID.randomUUID());
        }
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(projectServicePaymentDetails));
        batch.add(insertByProjectServiceStmt.bind()
            .setUUID("projectid", projectServicePaymentDetails.getProjectid())
            .setUUID("serviceid", projectServicePaymentDetails.getServiceid())
            .setUUID("id", projectServicePaymentDetails.getId()));
        session.execute(batch);
        return projectServicePaymentDetails;
    }

    public void delete(ProjectServicePaymentDetails projectServicePaymentDetails)
    {
        mapper.delete(projectServicePaymentDetails.getId());
        BatchStatement batch = new BatchStatement();
        batch.add(deleteByProjectServiceStmt.bind()
            .setUUID("projectid", projectServicePaymentDetails.getProjectid())
            .setUUID("serviceid", projectServicePaymentDetails.getServiceid()));
        session.execute(batch);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
