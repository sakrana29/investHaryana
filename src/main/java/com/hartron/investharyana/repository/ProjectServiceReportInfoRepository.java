package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.ProjectServiceReportInfo;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the ProjectServiceReportInfo entity.
 */
@Repository
public class ProjectServiceReportInfoRepository {

    private final Session session;

    private Mapper<ProjectServiceReportInfo> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectServiceReportInfoRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ProjectServiceReportInfo.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectServiceReportInfo");
        this.truncateStmt = session.prepare("TRUNCATE projectServiceReportInfo");
    }

    public List<ProjectServiceReportInfo> findAll() {
        List<ProjectServiceReportInfo> projectServiceReportInfosList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                ProjectServiceReportInfo projectServiceReportInfo = new ProjectServiceReportInfo();
                projectServiceReportInfo.setId(row.getUUID("id"));
                projectServiceReportInfo.setProjectid(row.getUUID("projectid"));
                projectServiceReportInfo.setProjecttype(row.getString("projecttype"));
                projectServiceReportInfo.setDepartmentname(row.getString("departmentname"));
                projectServiceReportInfo.setServicename(row.getString("servicename"));
                projectServiceReportInfo.setAssignDate(row.get("assignDate", ZonedDateTime.class));
                projectServiceReportInfo.setRequireDate(row.get("requireDate", ZonedDateTime.class));
                projectServiceReportInfo.setStatus(row.getString("status"));
                projectServiceReportInfo.setStage(row.getString("stage"));
                projectServiceReportInfo.setInvestorName(row.getString("investorName"));
                projectServiceReportInfo.setCafPin(row.getString("cafPin"));
                projectServiceReportInfo.setFinalAction(row.getString("finalAction"));
                projectServiceReportInfo.setFinalActionDate(row.get("finalActionDate", ZonedDateTime.class));
                projectServiceReportInfo.setProjectInvestment(row.getDouble("projectInvestment"));
                projectServiceReportInfo.setProjectEmployment(row.getString("projectEmployment"));
                return projectServiceReportInfo;
            }
        ).forEach(projectServiceReportInfosList::add);
        return projectServiceReportInfosList;
    }

    public ProjectServiceReportInfo findOne(UUID id) {
        return mapper.get(id);
    }

    public ProjectServiceReportInfo save(ProjectServiceReportInfo projectServiceReportInfo) {
        if (projectServiceReportInfo.getId() == null) {
            projectServiceReportInfo.setId(UUID.randomUUID());
        }
        mapper.save(projectServiceReportInfo);
        return projectServiceReportInfo;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
