package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectdetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectdetail entity.
 */
@Repository
public class ProjectdetailRepository {

    private final Session session;

    private Mapper<Projectdetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectdetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectdetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectdetail");
        this.truncateStmt = session.prepare("TRUNCATE projectdetail");
    }

    public List<Projectdetail> findAll() {
        List<Projectdetail> projectdetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectdetail projectdetail = new Projectdetail();
                projectdetail.setId(row.getUUID("id"));
                projectdetail.setProjectpurpose(row.getString("projectpurpose"));
                projectdetail.setNiccode(row.getString("niccode"));
                projectdetail.setExisting_regulatory_approval(row.getBool("existing_regulatory_approval"));
                projectdetail.setEdc_sif_clu_fee_paid_applicable(row.getBool("edc_sif_clu_fee_paid_applicable"));
                projectdetail.setDetail_project_report(row.getString("detail_project_report"));
                projectdetail.setApproval_document(row.getString("approval_document"));
                projectdetail.setEdc_sif_clu_fee_paid_document(row.getString("edc_sif_clu_fee_paid_document"));
                projectdetail.setInvestorid(row.getUUID("investorid"));
                projectdetail.setApproval_application_form(row.getString("approval_application_form"));
                projectdetail.setCategory_of_project(row.getString("category_of_project"));
                projectdetail.setCollaboration_with_foreign_country(row.getString("collaboration_with_foreign_country"));
                projectdetail.setProjectype(row.getString("projectype"));
                projectdetail.setSectorname(row.getString("sectorname"));
                projectdetail.setSize_of_industry(row.getString("size_of_industry"));
                projectdetail.setCafPIN(row.getString("cafPIN"));
                return projectdetail;
            }
        ).forEach(projectdetailsList::add);
        return projectdetailsList;
    }

    public Projectdetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectdetail save(Projectdetail projectdetail) {
        if (projectdetail.getId() == null) {
            projectdetail.setId(UUID.randomUUID());
        }
        mapper.save(projectdetail);
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
