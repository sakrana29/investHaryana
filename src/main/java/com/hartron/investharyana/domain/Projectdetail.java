package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
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

    private String projectpurpose;

    private String niccode;

    private Boolean existing_regulatory_approval;

    private Boolean edc_sif_clu_fee_paid_applicable;

    private String approval_application_form;

    private String category_of_project;

    private String collaboration_with_foreign_country;

    private String projectype;

    private String sectorname;

    private String size_of_industry;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    private String sectorother;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getApproval_application_form() {
        return approval_application_form;
    }

    public Projectdetail approval_application_form(String approval_application_form) {
        this.approval_application_form = approval_application_form;
        return this;
    }

    public void setApproval_application_form(String approval_application_form) {
        this.approval_application_form = approval_application_form;
    }

    public String getCategory_of_project() {
        return category_of_project;
    }

    public Projectdetail category_of_project(String category_of_project) {
        this.category_of_project = category_of_project;
        return this;
    }

    public void setCategory_of_project(String category_of_project) {
        this.category_of_project = category_of_project;
    }

    public String getCollaboration_with_foreign_country() {
        return collaboration_with_foreign_country;
    }

    public Projectdetail collaboration_with_foreign_country(String collaboration_with_foreign_country) {
        this.collaboration_with_foreign_country = collaboration_with_foreign_country;
        return this;
    }

    public void setCollaboration_with_foreign_country(String collaboration_with_foreign_country) {
        this.collaboration_with_foreign_country = collaboration_with_foreign_country;
    }

    public String getProjectype() {
        return projectype;
    }

    public Projectdetail projectype(String projectype) {
        this.projectype = projectype;
        return this;
    }

    public void setProjectype(String projectype) {
        this.projectype = projectype;
    }

    public String getSectorname() {
        return sectorname;
    }

    public Projectdetail sectorname(String sectorname) {
        this.sectorname = sectorname;
        return this;
    }

    public void setSectorname(String sectorname) {
        this.sectorname = sectorname;
    }

    public String getSize_of_industry() {
        return size_of_industry;
    }

    public Projectdetail size_of_industry(String size_of_industry) {
        this.size_of_industry = size_of_industry;
        return this;
    }

    public void setSize_of_industry(String size_of_industry) {
        this.size_of_industry = size_of_industry;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Projectdetail createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Projectdetail updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
    }

    public String getSectorother() {
        return sectorother;
    }

    public Projectdetail sectorother(String sectorother) {
        this.sectorother = sectorother;
        return this;
    }

    public void setSectorother(String sectorother) {
        this.sectorother = sectorother;
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
            ", projectpurpose='" + projectpurpose + "'" +
            ", niccode='" + niccode + "'" +
            ", existing_regulatory_approval='" + existing_regulatory_approval + "'" +
            ", edc_sif_clu_fee_paid_applicable='" + edc_sif_clu_fee_paid_applicable + "'" +
            ", approval_application_form='" + approval_application_form + "'" +
            ", category_of_project='" + category_of_project + "'" +
            ", collaboration_with_foreign_country='" + collaboration_with_foreign_country + "'" +
            ", projectype='" + projectype + "'" +
            ", sectorname='" + sectorname + "'" +
            ", size_of_industry='" + size_of_industry + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            ", sectorother='" + sectorother + "'" +
            '}';
    }
}
