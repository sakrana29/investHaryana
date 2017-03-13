package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectdetail.
 */

@Table(name = "projectdetail")
public class Projectdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID investorid;

    private UUID sector;

    private String projectpurpose;

    private UUID size_of_industry;

    private UUID projectype;

    private String niccode;

    private UUID category_of_project;

    private UUID collaboration_with_foreign_country;

    private ByteBuffer detail_project_report;

    @Column(name = "detail_project_report_content_type")
    private String detail_project_reportContentType;

    private Boolean existing_regulatory_approval;

    private UUID approval_application_form;

    private ByteBuffer approval_document;

    @Column(name = "approval_document_content_type")
    private String approval_documentContentType;

    private ByteBuffer edc_sif_clu_fee_paid_document;

    @Column(name = "edc_sif_clu_fee_paid_document_content_type")
    private String edc_sif_clu_fee_paid_documentContentType;

    private Boolean edc_sif_clu_fee_paid_applicable;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInvestorid() {
        return investorid;
    }

    public Projectdetail investorid(UUID investorid) {
        this.investorid = investorid;
        return this;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }

    public UUID getSector() {
        return sector;
    }

    public Projectdetail sector(UUID sector) {
        this.sector = sector;
        return this;
    }

    public void setSector(UUID sector) {
        this.sector = sector;
    }

    public String getProjectpurpose() {
        return projectpurpose;
    }

    public Projectdetail projectpurpose(String projectpurpose) {
        this.projectpurpose = projectpurpose;
        return this;
    }

    public void setProjectpurpose(String projectpurpose) {
        this.projectpurpose = projectpurpose;
    }

    public UUID getSize_of_industry() {
        return size_of_industry;
    }

    public Projectdetail size_of_industry(UUID size_of_industry) {
        this.size_of_industry = size_of_industry;
        return this;
    }

    public void setSize_of_industry(UUID size_of_industry) {
        this.size_of_industry = size_of_industry;
    }

    public UUID getProjectype() {
        return projectype;
    }

    public Projectdetail projectype(UUID projectype) {
        this.projectype = projectype;
        return this;
    }

    public void setProjectype(UUID projectype) {
        this.projectype = projectype;
    }

    public String getNiccode() {
        return niccode;
    }

    public Projectdetail niccode(String niccode) {
        this.niccode = niccode;
        return this;
    }

    public void setNiccode(String niccode) {
        this.niccode = niccode;
    }

    public UUID getCategory_of_project() {
        return category_of_project;
    }

    public Projectdetail category_of_project(UUID category_of_project) {
        this.category_of_project = category_of_project;
        return this;
    }

    public void setCategory_of_project(UUID category_of_project) {
        this.category_of_project = category_of_project;
    }

    public UUID getCollaboration_with_foreign_country() {
        return collaboration_with_foreign_country;
    }

    public Projectdetail collaboration_with_foreign_country(UUID collaboration_with_foreign_country) {
        this.collaboration_with_foreign_country = collaboration_with_foreign_country;
        return this;
    }

    public void setCollaboration_with_foreign_country(UUID collaboration_with_foreign_country) {
        this.collaboration_with_foreign_country = collaboration_with_foreign_country;
    }

    public ByteBuffer getDetail_project_report() {
        return detail_project_report;
    }

    public Projectdetail detail_project_report(ByteBuffer detail_project_report) {
        this.detail_project_report = detail_project_report;
        return this;
    }

    public void setDetail_project_report(ByteBuffer detail_project_report) {
        this.detail_project_report = detail_project_report;
    }

    public String getDetail_project_reportContentType() {
        return detail_project_reportContentType;
    }

    public Projectdetail detail_project_reportContentType(String detail_project_reportContentType) {
        this.detail_project_reportContentType = detail_project_reportContentType;
        return this;
    }

    public void setDetail_project_reportContentType(String detail_project_reportContentType) {
        this.detail_project_reportContentType = detail_project_reportContentType;
    }

    public Boolean isExisting_regulatory_approval() {
        return existing_regulatory_approval;
    }

    public Projectdetail existing_regulatory_approval(Boolean existing_regulatory_approval) {
        this.existing_regulatory_approval = existing_regulatory_approval;
        return this;
    }

    public void setExisting_regulatory_approval(Boolean existing_regulatory_approval) {
        this.existing_regulatory_approval = existing_regulatory_approval;
    }

    public UUID getApproval_application_form() {
        return approval_application_form;
    }

    public Projectdetail approval_application_form(UUID approval_application_form) {
        this.approval_application_form = approval_application_form;
        return this;
    }

    public void setApproval_application_form(UUID approval_application_form) {
        this.approval_application_form = approval_application_form;
    }

    public ByteBuffer getApproval_document() {
        return approval_document;
    }

    public Projectdetail approval_document(ByteBuffer approval_document) {
        this.approval_document = approval_document;
        return this;
    }

    public void setApproval_document(ByteBuffer approval_document) {
        this.approval_document = approval_document;
    }

    public String getApproval_documentContentType() {
        return approval_documentContentType;
    }

    public Projectdetail approval_documentContentType(String approval_documentContentType) {
        this.approval_documentContentType = approval_documentContentType;
        return this;
    }

    public void setApproval_documentContentType(String approval_documentContentType) {
        this.approval_documentContentType = approval_documentContentType;
    }

    public ByteBuffer getEdc_sif_clu_fee_paid_document() {
        return edc_sif_clu_fee_paid_document;
    }

    public Projectdetail edc_sif_clu_fee_paid_document(ByteBuffer edc_sif_clu_fee_paid_document) {
        this.edc_sif_clu_fee_paid_document = edc_sif_clu_fee_paid_document;
        return this;
    }

    public void setEdc_sif_clu_fee_paid_document(ByteBuffer edc_sif_clu_fee_paid_document) {
        this.edc_sif_clu_fee_paid_document = edc_sif_clu_fee_paid_document;
    }

    public String getEdc_sif_clu_fee_paid_documentContentType() {
        return edc_sif_clu_fee_paid_documentContentType;
    }

    public Projectdetail edc_sif_clu_fee_paid_documentContentType(String edc_sif_clu_fee_paid_documentContentType) {
        this.edc_sif_clu_fee_paid_documentContentType = edc_sif_clu_fee_paid_documentContentType;
        return this;
    }

    public void setEdc_sif_clu_fee_paid_documentContentType(String edc_sif_clu_fee_paid_documentContentType) {
        this.edc_sif_clu_fee_paid_documentContentType = edc_sif_clu_fee_paid_documentContentType;
    }

    public Boolean isEdc_sif_clu_fee_paid_applicable() {
        return edc_sif_clu_fee_paid_applicable;
    }

    public Projectdetail edc_sif_clu_fee_paid_applicable(Boolean edc_sif_clu_fee_paid_applicable) {
        this.edc_sif_clu_fee_paid_applicable = edc_sif_clu_fee_paid_applicable;
        return this;
    }

    public void setEdc_sif_clu_fee_paid_applicable(Boolean edc_sif_clu_fee_paid_applicable) {
        this.edc_sif_clu_fee_paid_applicable = edc_sif_clu_fee_paid_applicable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectdetail projectdetail = (Projectdetail) o;
        if (projectdetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectdetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectdetail{" +
            "id=" + id +
            ", investorid='" + investorid + "'" +
            ", sector='" + sector + "'" +
            ", projectpurpose='" + projectpurpose + "'" +
            ", size_of_industry='" + size_of_industry + "'" +
            ", projectype='" + projectype + "'" +
            ", niccode='" + niccode + "'" +
            ", category_of_project='" + category_of_project + "'" +
            ", collaboration_with_foreign_country='" + collaboration_with_foreign_country + "'" +
            ", detail_project_report='" + detail_project_report + "'" +
            ", detail_project_reportContentType='" + detail_project_reportContentType + "'" +
            ", existing_regulatory_approval='" + existing_regulatory_approval + "'" +
            ", approval_application_form='" + approval_application_form + "'" +
            ", approval_document='" + approval_document + "'" +
            ", approval_documentContentType='" + approval_documentContentType + "'" +
            ", edc_sif_clu_fee_paid_document='" + edc_sif_clu_fee_paid_document + "'" +
            ", edc_sif_clu_fee_paid_documentContentType='" + edc_sif_clu_fee_paid_documentContentType + "'" +
            ", edc_sif_clu_fee_paid_applicable='" + edc_sif_clu_fee_paid_applicable + "'" +
            '}';
    }
}
