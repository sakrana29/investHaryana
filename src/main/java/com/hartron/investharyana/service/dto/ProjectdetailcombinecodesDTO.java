package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectdetailcombinecodes entity.
 */
public class ProjectdetailcombinecodesDTO implements Serializable {

    private UUID id;

    private UUID investorid;

    private UUID companydetailid;

    private UUID projectsitedetailid;

    private UUID projectfinanceid;

    private UUID manufacturingid;

    private UUID electricityrequirementid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getInvestorid() {
        return investorid;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }
    public UUID getCompanydetailid() {
        return companydetailid;
    }

    public void setCompanydetailid(UUID companydetailid) {
        this.companydetailid = companydetailid;
    }
    public UUID getProjectsitedetailid() {
        return projectsitedetailid;
    }

    public void setProjectsitedetailid(UUID projectsitedetailid) {
        this.projectsitedetailid = projectsitedetailid;
    }
    public UUID getProjectfinanceid() {
        return projectfinanceid;
    }

    public void setProjectfinanceid(UUID projectfinanceid) {
        this.projectfinanceid = projectfinanceid;
    }
    public UUID getManufacturingid() {
        return manufacturingid;
    }

    public void setManufacturingid(UUID manufacturingid) {
        this.manufacturingid = manufacturingid;
    }
    public UUID getElectricityrequirementid() {
        return electricityrequirementid;
    }

    public void setElectricityrequirementid(UUID electricityrequirementid) {
        this.electricityrequirementid = electricityrequirementid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO = (ProjectdetailcombinecodesDTO) o;

        if ( ! Objects.equals(id, projectdetailcombinecodesDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectdetailcombinecodesDTO{" +
            "id=" + id +
            ", investorid='" + investorid + "'" +
            ", companydetailid='" + companydetailid + "'" +
            ", projectsitedetailid='" + projectsitedetailid + "'" +
            ", projectfinanceid='" + projectfinanceid + "'" +
            ", manufacturingid='" + manufacturingid + "'" +
            ", electricityrequirementid='" + electricityrequirementid + "'" +
            '}';
    }
}
