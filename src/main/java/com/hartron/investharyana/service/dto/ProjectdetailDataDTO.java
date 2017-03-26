package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectdetail entity.
 */
public class ProjectdetailDataDTO implements Serializable {

    private UUID id;

    private String projectpurpose;

    private String niccode;

    private Boolean existing_regulatory_approval;

    private Boolean edc_sif_clu_fee_paid_applicable;

    private String detail_project_report;

    private String approval_document;

    private String edc_sif_clu_fee_paid_document;

    private UUID approval_application_form;

    private UUID category_of_project;

    private UUID collaboration_with_foreign_country;

    private UUID investorid;

    private UUID projectype;

    private UUID sectorid;

    private UUID size_of_industry;

    private String sectorname;

    private String size_of_industryname;

    private String projectypename;

    private String category_of_projectname;

    private String collaboration_with_foreign_countryname;

    private String approval_application_formname;

    public String getSectorname() {
        return sectorname;
    }
    public void setSectorname(String sectorname) {
        this.sectorname = sectorname;
    }

    public String getSize_of_industryname() {
        return size_of_industryname;
    }
    public void setSize_of_industryname(String size_of_industryname) {
        this.size_of_industryname= size_of_industryname;
    }

    public String getProjectypename() {
        return projectypename;
    }
    public void setProjectypename(String projectypename) {
        this.projectypename= projectypename;
    }

    public String getCategory_of_projectname() {
        return category_of_projectname;
    }
    public void setCategory_of_projectname(String category_of_projectname) {
        this.category_of_projectname= category_of_projectname;
    }

    public String getCollaboration_with_foreign_countryname() {
        return collaboration_with_foreign_countryname;
    }
    public void setCollaboration_with_foreign_countryname(String collaboration_with_foreign_countryname) {
        this.collaboration_with_foreign_countryname= collaboration_with_foreign_countryname;
    }

    public String getApproval_application_formname() {
        return approval_application_formname;
    }
    public void setApproval_application_formname(String approval_application_formname) {
        this.approval_application_formname = approval_application_formname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getProjectpurpose() {
        return projectpurpose;
    }

    public void setProjectpurpose(String projectpurpose) {
        this.projectpurpose = projectpurpose;
    }
    public String getNiccode() {
        return niccode;
    }

    public void setNiccode(String niccode) {
        this.niccode = niccode;
    }
    public Boolean getExisting_regulatory_approval() {
        return existing_regulatory_approval;
    }

    public void setExisting_regulatory_approval(Boolean existing_regulatory_approval) {
        this.existing_regulatory_approval = existing_regulatory_approval;
    }
    public Boolean getEdc_sif_clu_fee_paid_applicable() {
        return edc_sif_clu_fee_paid_applicable;
    }

    public void setEdc_sif_clu_fee_paid_applicable(Boolean edc_sif_clu_fee_paid_applicable) {
        this.edc_sif_clu_fee_paid_applicable = edc_sif_clu_fee_paid_applicable;
    }
    public String getDetail_project_report() {
        return detail_project_report;
    }

    public void setDetail_project_report(String detail_project_report) {
        this.detail_project_report = detail_project_report;
    }
    public String getApproval_document() {
        return approval_document;
    }

    public void setApproval_document(String approval_document) {
        this.approval_document = approval_document;
    }
    public String getEdc_sif_clu_fee_paid_document() {
        return edc_sif_clu_fee_paid_document;
    }

    public void setEdc_sif_clu_fee_paid_document(String edc_sif_clu_fee_paid_document) {
        this.edc_sif_clu_fee_paid_document = edc_sif_clu_fee_paid_document;
    }
    public UUID getApproval_application_form() {
        return approval_application_form;
    }

    public void setApproval_application_form(UUID approval_application_form) {
        this.approval_application_form = approval_application_form;
    }
    public UUID getCategory_of_project() {
        return category_of_project;
    }

    public void setCategory_of_project(UUID category_of_project) {
        this.category_of_project = category_of_project;
    }
    public UUID getCollaboration_with_foreign_country() {
        return collaboration_with_foreign_country;
    }

    public void setCollaboration_with_foreign_country(UUID collaboration_with_foreign_country) {
        this.collaboration_with_foreign_country = collaboration_with_foreign_country;
    }
    public UUID getInvestorid() {
        return investorid;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }
    public UUID getProjectype() {
        return projectype;
    }

    public void setProjectype(UUID projectype) {
        this.projectype = projectype;
    }
    public UUID getSectorid() {
        return sectorid;
    }

    public void setSectorid(UUID sectorid) {
        this.sectorid = sectorid;
    }
    public UUID getSize_of_industry() {
        return size_of_industry;
    }

    public void setSize_of_industry(UUID size_of_industry) {
        this.size_of_industry = size_of_industry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectdetailDataDTO projectdetailDTO = (ProjectdetailDataDTO) o;

        if ( ! Objects.equals(id, projectdetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectdetailDTO{" +
            "id=" + id +
            ", projectpurpose='" + projectpurpose + "'" +
            ", niccode='" + niccode + "'" +
            ", existing_regulatory_approval='" + existing_regulatory_approval + "'" +
            ", edc_sif_clu_fee_paid_applicable='" + edc_sif_clu_fee_paid_applicable + "'" +
            ", detail_project_report='" + detail_project_report + "'" +
            ", approval_document='" + approval_document + "'" +
            ", edc_sif_clu_fee_paid_document='" + edc_sif_clu_fee_paid_document + "'" +
            ", approval_application_form='" + approval_application_form + "'" +
            ", category_of_project='" + category_of_project + "'" +
            ", collaboration_with_foreign_country='" + collaboration_with_foreign_country + "'" +
            ", investorid='" + investorid + "'" +
            ", projectype='" + projectype + "'" +
            ", sectorid='" + sectorid + "'" +
            ", size_of_industry='" + size_of_industry + "'" +
            '}';
    }
}
