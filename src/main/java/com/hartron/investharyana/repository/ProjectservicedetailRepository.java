package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectservicedetail;

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
 * Cassandra repository for the Projectservicedetail entity.
 */
@Repository
public class ProjectservicedetailRepository {

    private final Session session;

    private Mapper<Projectservicedetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findAllByProjectStmt;

    private PreparedStatement insertByProjectStmt;

    private PreparedStatement deleteByProjectStmt;

    public ProjectservicedetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectservicedetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectservicedetail");
        this.truncateStmt = session.prepare("TRUNCATE projectservicedetail");
        this.findAllByProjectStmt = session.prepare(
            "SELECT id " +
                "FROM projectservicedetail_by_projectid " +
                "WHERE projectid = :projectid");

        this.insertByProjectStmt = session.prepare(
            "INSERT INTO projectservicedetail_by_projectid (projectid, id) " +
                "VALUES (:projectid, :id)");

        this.deleteByProjectStmt = session.prepare(
            "DELETE FROM projectservicedetail_by_projectid " +
                "WHERE projectid = :projectid");
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
                projectservicedetail.setDepartmentName(row.getString("departmentName"));
                projectservicedetail.setServiceName(row.getString("serviceName"));
                projectservicedetail.setServiceStage(row.getString("serviceStage"));
                projectservicedetail.setIsDimmed(row.getBool("isDimmed"));
                projectservicedetail.setServiceDuration(row.getInt("serviceDuration"));
                return projectservicedetail;
            }
        ).forEach(projectservicedetailsList::add);
        return projectservicedetailsList;
    }

    public Projectservicedetail findOne(UUID id) {
        return mapper.get(id);
    }


    public List<Projectservicedetail> findAllByProjectid(UUID projectid) {
        BoundStatement stmt = findAllByProjectStmt.bind();
        stmt.setUUID("projectid", projectid);
        return findAllFromIndex(stmt);
    }
    private List<Projectservicedetail> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Projectservicedetail> projectservicedetailList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Projectservicedetail projectservicedetail=new Projectservicedetail();
            projectservicedetail= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            projectservicedetailList.add(projectservicedetail);
        }
        return projectservicedetailList;
    }

    public Projectservicedetail save(Projectservicedetail projectservicedetail) {
        if (projectservicedetail.getId() == null) {
            projectservicedetail.setId(UUID.randomUUID());
        }

        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(projectservicedetail));
        batch.add(insertByProjectStmt.bind()
            .setUUID("projectid", projectservicedetail.getProjectid())
            .setUUID("id", projectservicedetail.getId()));
        session.execute(batch);
        return projectservicedetail;
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
