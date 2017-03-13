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
                projectdetail.setInvestorid(row.getUUID("investorid"));
                projectdetail.setSector(row.getUUID("sector"));
                projectdetail.setProjectpurpose(row.getString("projectpurpose"));
                projectdetail.setSize_of_industry(row.getUUID("size_of_industry"));
                projectdetail.setProjectype(row.getUUID("projectype"));
                projectdetail.setNiccode(row.getString("niccode"));
                projectdetail.setCategory_of_project(row.getUUID("category_of_project"));
                projectdetail.setCollaboration_with_foreign_country(row.getUUID("collaboration_with_foreign_country"));
                projectdetail.setDetail_project_report(row.getBytes("detail_project_report"));
                projectdetail.setDetail_project_reportContentType(row.getString("detail_project_report_content_type"));

                projectdetail.setExisting_regulatory_approval(row.getBool("existing_regulatory_approval"));
                projectdetail.setApproval_application_form(row.getUUID("approval_application_form"));
                projectdetail.setApproval_document(row.getBytes("approval_document"));
                projectdetail.setApproval_documentContentType(row.getString("approval_document_content_type"));

                projectdetail.setEdc_sif_clu_fee_paid_document(row.getBytes("edc_sif_clu_fee_paid_document"));
                projectdetail.setEdc_sif_clu_fee_paid_documentContentType(row.getString("edc_sif_clu_fee_paid_document_content_type"));

                projectdetail.setEdc_sif_clu_fee_paid_applicable(row.getBool("edc_sif_clu_fee_paid_applicable"));
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