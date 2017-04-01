package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.ProjectServiceReportInfo;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private PreparedStatement findAllByDeptStmt;

    private PreparedStatement insertByDeptStmt;

    public ProjectServiceReportInfoRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ProjectServiceReportInfo.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectServiceReportInfo");
        this.truncateStmt = session.prepare("TRUNCATE projectServiceReportInfo");

        this.findAllByDeptStmt = session.prepare(
            "SELECT id " +
                "FROM projectServiceReportInfo_By_DepartmentName " +
                "WHERE departmentname = :departmentname");

        insertByDeptStmt = session.prepare(
            "INSERT INTO projectServiceReportInfo_By_DepartmentName (departmentname, id) " +
                "VALUES (:departmentname, :id)");
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

    public List<ProjectServiceReportInfo> findAllByDept(String departmentname) {
        BoundStatement stmt = findAllByDeptStmt.bind();
        stmt.setString("departmentname", departmentname);
        return findAllFromIndex(stmt);
    }
    private List<ProjectServiceReportInfo> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<ProjectServiceReportInfo> projectServiceReportInfoList=new ArrayList<>();
        if (!(rs.isExhausted())) {
            ProjectServiceReportInfo projectServiceReportInfo = new ProjectServiceReportInfo();
            projectServiceReportInfo = Optional.ofNullable(rs.one().getUUID("id"))
            .map(id -> Optional.ofNullable(mapper.get(id)))
            .get().get();
            projectServiceReportInfoList.add(projectServiceReportInfo);
        }
        return projectServiceReportInfoList;
    }

    public ProjectServiceReportInfo save(ProjectServiceReportInfo projectServiceReportInfo) {
        if (projectServiceReportInfo.getId() == null) {
            projectServiceReportInfo.setId(UUID.randomUUID());
        }
        mapper.save(projectServiceReportInfo);

        BatchStatement batch = new BatchStatement();

        batch.add(insertByDeptStmt.bind()
        .setString("departmentname", projectServiceReportInfo.getDepartmentname())
        .setUUID("id", projectServiceReportInfo.getId()));

        session.execute(batch);
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
