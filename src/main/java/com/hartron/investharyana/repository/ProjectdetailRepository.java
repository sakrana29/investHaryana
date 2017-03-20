package com.hartron.investharyana.repository;

import com.google.common.util.concurrent.ListenableFuture;
import com.hartron.investharyana.domain.Investor;
import com.hartron.investharyana.domain.Projectdetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.hartron.investharyana.security.SecurityUtils;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Cassandra repository for the Projectdetail entity.
 */
@Repository
public class ProjectdetailRepository {

    private final Session session;

    private Mapper<Projectdetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement insertByInvestorStmt;
    private PreparedStatement findByInvestorStmt;

    public ProjectdetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectdetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectdetail");
        this.truncateStmt = session.prepare("TRUNCATE projectdetail");

        this.insertByInvestorStmt = session.prepare(
            "INSERT INTO projectdetail_by_investor (investorid, id) " +
                "VALUES (:investorid, :id)");

        this.findByInvestorStmt = session.prepare(
            "SELECT id " +
                "FROM projectdetail_by_investor " +
                "WHERE investorid = :investorid");

    }

    public List<Projectdetail> findAll() {
        List<Projectdetail> projectdetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectdetail projectdetail = new Projectdetail();
                projectdetail.setId(row.getUUID("id"));
                projectdetail.setInvestorid(row.getUUID("investorid"));
                projectdetail.setSector(row.getUUID("sector"));
                projectdetail.setProjectpurpose(row.getString("projectpurpose"));
                projectdetail.setSize_of_industry(row.getUUID("size_of_industry"));
                projectdetail.setProjectype(row.getUUID("projectype"));
                projectdetail.setNiccode(row.getString("niccode"));
                projectdetail.setCategory_of_project(row.getUUID("category_of_project"));
                projectdetail.setCollaboration_with_foreign_country(row.getUUID("collaboration_with_foreign_country"));
                projectdetail.setExisting_regulatory_approval(row.getBool("existing_regulatory_approval"));
                projectdetail.setApproval_application_form(row.getUUID("approval_application_form"));
                projectdetail.setEdc_sif_clu_fee_paid_applicable(row.getBool("edc_sif_clu_fee_paid_applicable"));
                projectdetail.setDetail_project_report(row.getString("detail_project_report"));
                projectdetail.setApproval_document(row.getString("approval_document"));
                projectdetail.setEdc_sif_clu_fee_paid_document(row.getString("edc_sif_clu_fee_paid_document"));
                return projectdetail;
            }
        ).forEach(projectdetailsList::add);
        return projectdetailsList;
    }

    public Projectdetail findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Projectdetail> findProjectbyInvestorId(UUID investorid) {
        BoundStatement stmt = findByInvestorStmt.bind();
        stmt.setUUID("investorid", investorid);
        return findprojectFromIndex(stmt);
    }

    private List<Projectdetail> findprojectFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Projectdetail> projectList=new ArrayList<>();

        while(!(rs.isExhausted())){
            Projectdetail project=new Projectdetail();
            project=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            projectList.add(project);
        }
        return projectList;

    }

    public List<Projectdetail> findProjectbyUserLogin() {
        List<Projectdetail> projectList=new ArrayList<>();
        List<Projectdetail> projectListForOne=new ArrayList<>();
        List<Investor> investorList=new ArrayList<>();

        InvestorRepository repo=new InvestorRepository(session);
        investorList=repo.findInvestorbyUserLogin();

        for(int i=0;i<investorList.size()-1;i++)
        {
            UUID investorid=investorList.get(i).getId();
            projectListForOne= findProjectbyInvestorId(investorid);
            if(projectListForOne.size()>0)
            {
                projectList.add(projectListForOne.get(0));
            }
        }

        return projectList;
    }

    public Projectdetail save(Projectdetail projectdetail) {
        if (projectdetail.getId() == null) {
            projectdetail.setId(UUID.randomUUID());
        }
        mapper.save(projectdetail);

        BatchStatement batch = new BatchStatement();
        batch.add(insertByInvestorStmt.bind()
            .setUUID("investorid", projectdetail.getInvestorid())
            .setUUID("id", projectdetail.getId()));
        session.execute(batch);

        return projectdetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
