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

    private String departmentname;

    private String servicename;

    private Boolean isrequired;

    private ZonedDateTime markrequiredondate;

    private String markrequiredby;

    private Boolean isassigned;

    private String markassignedby;

    private Integer feerequired;

    private String status;

    private String comment;

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
    public Boolean getIsrequired() {
        return isrequired;
    }

    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }
    public ZonedDateTime getMarkrequiredondate() {
        return markrequiredondate;
    }

    public void setMarkrequiredondate(ZonedDateTime markrequiredondate) {
        this.markrequiredondate = markrequiredondate;
    }
    public String getMarkrequiredby() {
        return markrequiredby;
    }

    public void setMarkrequiredby(String markrequiredby) {
        this.markrequiredby = markrequiredby;
    }
    public Boolean getIsassigned() {
        return isassigned;
    }

    public void setIsassigned(Boolean isassigned) {
        this.isassigned = isassigned;
    }
    public String getMarkassignedby() {
        return markassignedby;
    }

    public void setMarkassignedby(String markassignedby) {
        this.markassignedby = markassignedby;
    }
    public Integer getFeerequired() {
        return feerequired;
    }

    public void setFeerequired(Integer feerequired) {
        this.feerequired = feerequired;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
            ", departmentname='" + departmentname + "'" +
            ", servicename='" + servicename + "'" +
            ", isrequired='" + isrequired + "'" +
            ", markrequiredondate='" + markrequiredondate + "'" +
            ", markrequiredby='" + markrequiredby + "'" +
            ", isassigned='" + isassigned + "'" +
            ", markassignedby='" + markassignedby + "'" +
            ", feerequired='" + feerequired + "'" +
            ", status='" + status + "'" +
            ", comment='" + comment + "'" +
            '}';
    }
}
