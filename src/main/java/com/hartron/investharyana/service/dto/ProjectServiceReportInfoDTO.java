package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the ProjectServiceReportInfo entity.
 */
public class ProjectServiceReportInfoDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private String projecttype;

    private String departmentname;

    private String servicename;

    private ZonedDateTime assignDate;

    private ZonedDateTime requireDate;

    private String status;

    private String stage;

    private String investorName;

    private String cafPin;

    private String finalAction;

    private ZonedDateTime finalActionDate;

    private Double projectInvestment;

    private String projectEmployment;

    private String proposedprojectarea;

    private Boolean confirmitylanduse;

    private String landzoneusetype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getProjectid() {
        return projectid;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }
    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }
    public ZonedDateTime getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(ZonedDateTime assignDate) {
        this.assignDate = assignDate;
    }
    public ZonedDateTime getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(ZonedDateTime requireDate) {
        this.requireDate = requireDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }
    public String getCafPin() {
        return cafPin;
    }

    public void setCafPin(String cafPin) {
        this.cafPin = cafPin;
    }
    public String getFinalAction() {
        return finalAction;
    }

    public void setFinalAction(String finalAction) {
        this.finalAction = finalAction;
    }
    public ZonedDateTime getFinalActionDate() {
        return finalActionDate;
    }

    public void setFinalActionDate(ZonedDateTime finalActionDate) {
        this.finalActionDate = finalActionDate;
    }
    public Double getProjectInvestment() {
        return projectInvestment;
    }

    public void setProjectInvestment(Double projectInvestment) {
        this.projectInvestment = projectInvestment;
    }
    public String getProjectEmployment() {
        return projectEmployment;
    }

    public void setProjectEmployment(String projectEmployment) {
        this.projectEmployment = projectEmployment;
    }
    public String getProposedprojectarea() {
        return proposedprojectarea;
    }

    public void setProposedprojectarea(String proposedprojectarea) {
        this.proposedprojectarea = proposedprojectarea;
    }
    public Boolean getConfirmitylanduse() {
        return confirmitylanduse;
    }

    public void setConfirmitylanduse(Boolean confirmitylanduse) {
        this.confirmitylanduse = confirmitylanduse;
    }
    public String getLandzoneusetype() {
        return landzoneusetype;
    }

    public void setLandzoneusetype(String landzoneusetype) {
        this.landzoneusetype = landzoneusetype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = (ProjectServiceReportInfoDTO) o;

        if ( ! Objects.equals(id, projectServiceReportInfoDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectServiceReportInfoDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", projecttype='" + projecttype + "'" +
            ", departmentname='" + departmentname + "'" +
            ", servicename='" + servicename + "'" +
            ", assignDate='" + assignDate + "'" +
            ", requireDate='" + requireDate + "'" +
            ", status='" + status + "'" +
            ", stage='" + stage + "'" +
            ", investorName='" + investorName + "'" +
            ", cafPin='" + cafPin + "'" +
            ", finalAction='" + finalAction + "'" +
            ", finalActionDate='" + finalActionDate + "'" +
            ", projectInvestment='" + projectInvestment + "'" +
            ", projectEmployment='" + projectEmployment + "'" +
            ", proposedprojectarea='" + proposedprojectarea + "'" +
            ", confirmitylanduse='" + confirmitylanduse + "'" +
            ", landzoneusetype='" + landzoneusetype + "'" +
            '}';
    }
}
