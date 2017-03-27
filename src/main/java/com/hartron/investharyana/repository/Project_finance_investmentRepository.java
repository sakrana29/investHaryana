package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Project_finance_investment;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Project_finance_investment entity.
 */
@Repository
public class Project_finance_investmentRepository {

    private final Session session;

    private Mapper<Project_finance_investment> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Project_finance_investmentRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Project_finance_investment.class);
        this.findAllStmt = session.prepare("SELECT * FROM project_finance_investment");
        this.truncateStmt = session.prepare("TRUNCATE project_finance_investment");
    }

    public List<Project_finance_investment> findAll() {
        List<Project_finance_investment> project_finance_investmentsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Project_finance_investment project_finance_investment = new Project_finance_investment();
                project_finance_investment.setId(row.getUUID("id"));
                project_finance_investment.setProjectid(row.getUUID("projectid"));
                project_finance_investment.setLand_cost(row.getDecimal("land_cost"));
                project_finance_investment.setBuilding_cost(row.getDecimal("building_cost"));
                project_finance_investment.setMachinery_cost(row.getDecimal("machinery_cost"));
                project_finance_investment.setMisc_assests(row.getDecimal("misc_assests"));
                project_finance_investment.setTotal_project_cost(row.getDecimal("total_project_cost"));
                project_finance_investment.setIsfdi(row.getBool("isfdi"));
                project_finance_investment.setFdivalue(row.getDecimal("fdivalue"));
                project_finance_investment.setProject_construction_start_date(row.get("project_construction_start_date", ZonedDateTime.class));
                project_finance_investment.setCommercial_activity_start_date(row.get("commercial_activity_start_date", ZonedDateTime.class));
                project_finance_investment.setProposedproject_scheduleid(row.getUUID("proposedproject_scheduleid"));
                project_finance_investment.setFdi_country(row.getString("fdi_country"));
                project_finance_investment.setForeign_funding_source(row.getString("foreign_funding_source"));
                return project_finance_investment;
            }
        ).forEach(project_finance_investmentsList::add);
        return project_finance_investmentsList;
    }

    public Project_finance_investment findOne(UUID id) {
        return mapper.get(id);
    }

    public Project_finance_investment save(Project_finance_investment project_finance_investment) {
        if (project_finance_investment.getId() == null) {
            project_finance_investment.setId(UUID.randomUUID());
        }
        mapper.save(project_finance_investment);
        return project_finance_investment;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
