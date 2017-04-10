package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A ProjectServiceReportInfo.
 */

@Table(name = "projectServiceReportInfo")
public class ProjectServiceReportInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public ProjectServiceReportInfo projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public ProjectServiceReportInfo projecttype(String projecttype) {
        this.projecttype = projecttype;
        return this;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public ProjectServiceReportInfo departmentname(String departmentname) {
        this.departmentname = departmentname;
        return this;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getServicename() {
        return servicename;
    }

    public ProjectServiceReportInfo servicename(String servicename) {
        this.servicename = servicename;
        return this;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public ZonedDateTime getAssignDate() {
        return assignDate;
    }

    public ProjectServiceReportInfo assignDate(ZonedDateTime assignDate) {
        this.assignDate = assignDate;
        return this;
    }

    public void setAssignDate(ZonedDateTime assignDate) {
        this.assignDate = assignDate;
    }

    public ZonedDateTime getRequireDate() {
        return requireDate;
    }

    public ProjectServiceReportInfo requireDate(ZonedDateTime requireDate) {
        this.requireDate = requireDate;
        return this;
    }

    public void setRequireDate(ZonedDateTime requireDate) {
        this.requireDate = requireDate;
    }

    public String getStatus() {
        return status;
    }

    public ProjectServiceReportInfo status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStage() {
        return stage;
    }

    public ProjectServiceReportInfo stage(String stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getInvestorName() {
        return investorName;
    }

    public ProjectServiceReportInfo investorName(String investorName) {
        this.investorName = investorName;
        return this;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getCafPin() {
        return cafPin;
    }

    public ProjectServiceReportInfo cafPin(String cafPin) {
        this.cafPin = cafPin;
        return this;
    }

    public void setCafPin(String cafPin) {
        this.cafPin = cafPin;
    }

    public String getFinalAction() {
        return finalAction;
    }

    public ProjectServiceReportInfo finalAction(String finalAction) {
        this.finalAction = finalAction;
        return this;
    }

    public void setFinalAction(String finalAction) {
        this.finalAction = finalAction;
    }

    public ZonedDateTime getFinalActionDate() {
        return finalActionDate;
    }

    public ProjectServiceReportInfo finalActionDate(ZonedDateTime finalActionDate) {
        this.finalActionDate = finalActionDate;
        return this;
    }

    public void setFinalActionDate(ZonedDateTime finalActionDate) {
        this.finalActionDate = finalActionDate;
    }

    public Double getProjectInvestment() {
        return projectInvestment;
    }

    public ProjectServiceReportInfo projectInvestment(Double projectInvestment) {
        this.projectInvestment = projectInvestment;
        return this;
    }

    public void setProjectInvestment(Double projectInvestment) {
        this.projectInvestment = projectInvestment;
    }

    public String getProjectEmployment() {
        return projectEmployment;
    }

    public ProjectServiceReportInfo projectEmployment(String projectEmployment) {
        this.projectEmployment = projectEmployment;
        return this;
    }

    public void setProjectEmployment(String projectEmployment) {
        this.projectEmployment = projectEmployment;
    }

    public String getProposedprojectarea() {
        return proposedprojectarea;
    }

    public ProjectServiceReportInfo proposedprojectarea(String proposedprojectarea) {
        this.proposedprojectarea = proposedprojectarea;
        return this;
    }

    public void setProposedprojectarea(String proposedprojectarea) {
        this.proposedprojectarea = proposedprojectarea;
    }

    public Boolean isConfirmitylanduse() {
        return confirmitylanduse;
    }

    public ProjectServiceReportInfo confirmitylanduse(Boolean confirmitylanduse) {
        this.confirmitylanduse = confirmitylanduse;
        return this;
    }

    public void setConfirmitylanduse(Boolean confirmitylanduse) {
        this.confirmitylanduse = confirmitylanduse;
    }

    public String getLandzoneusetype() {
        return landzoneusetype;
    }

    public ProjectServiceReportInfo landzoneusetype(String landzoneusetype) {
        this.landzoneusetype = landzoneusetype;
        return this;
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
        ProjectServiceReportInfo projectServiceReportInfo = (ProjectServiceReportInfo) o;
        if (projectServiceReportInfo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectServiceReportInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectServiceReportInfo{" +
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
