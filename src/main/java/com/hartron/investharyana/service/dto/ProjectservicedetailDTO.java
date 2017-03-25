package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectservicedetail entity.
 */
public class ProjectservicedetailDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private UUID serviceid;

    private String userlogin;

    private Boolean servicerequired;

    private String servicestatus;

    private ZonedDateTime assigndate;

    private Double servicefee;

    private String remarks;

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
    public UUID getServiceid() {
        return serviceid;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }
    public String getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }
    public Boolean getServicerequired() {
        return servicerequired;
    }

    public void setServicerequired(Boolean servicerequired) {
        this.servicerequired = servicerequired;
    }
    public String getServicestatus() {
        return servicestatus;
    }

    public void setServicestatus(String servicestatus) {
        this.servicestatus = servicestatus;
    }
    public ZonedDateTime getAssigndate() {
        return assigndate;
    }

    public void setAssigndate(ZonedDateTime assigndate) {
        this.assigndate = assigndate;
    }
    public Double getServicefee() {
        return servicefee;
    }

    public void setServicefee(Double servicefee) {
        this.servicefee = servicefee;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectservicedetailDTO projectservicedetailDTO = (ProjectservicedetailDTO) o;

        if ( ! Objects.equals(id, projectservicedetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectservicedetailDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", serviceid='" + serviceid + "'" +
            ", userlogin='" + userlogin + "'" +
            ", servicerequired='" + servicerequired + "'" +
            ", servicestatus='" + servicestatus + "'" +
            ", assigndate='" + assigndate + "'" +
            ", servicefee='" + servicefee + "'" +
            ", remarks='" + remarks + "'" +
            '}';
    }
}
