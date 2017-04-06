package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectservicedetail.
 */

@Table(name = "projectservicedetail")
public class Projectservicedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Projectservicedetail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public UUID getServiceid() {
        return serviceid;
    }

    public Projectservicedetail serviceid(UUID serviceid) {
        this.serviceid = serviceid;
        return this;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public Projectservicedetail departmentname(String departmentname) {
        this.departmentname = departmentname;
        return this;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getServicename() {
        return servicename;
    }

    public Projectservicedetail servicename(String servicename) {
        this.servicename = servicename;
        return this;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public Boolean isIsrequired() {
        return isrequired;
    }

    public Projectservicedetail isrequired(Boolean isrequired) {
        this.isrequired = isrequired;
        return this;
    }

    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    public ZonedDateTime getMarkrequiredondate() {
        return markrequiredondate;
    }

    public Projectservicedetail markrequiredondate(ZonedDateTime markrequiredondate) {
        this.markrequiredondate = markrequiredondate;
        return this;
    }

    public void setMarkrequiredondate(ZonedDateTime markrequiredondate) {
        this.markrequiredondate = markrequiredondate;
    }

    public String getMarkrequiredby() {
        return markrequiredby;
    }

    public Projectservicedetail markrequiredby(String markrequiredby) {
        this.markrequiredby = markrequiredby;
        return this;
    }

    public void setMarkrequiredby(String markrequiredby) {
        this.markrequiredby = markrequiredby;
    }

    public Boolean isIsassigned() {
        return isassigned;
    }

    public Projectservicedetail isassigned(Boolean isassigned) {
        this.isassigned = isassigned;
        return this;
    }

    public void setIsassigned(Boolean isassigned) {
        this.isassigned = isassigned;
    }

    public String getMarkassignedby() {
        return markassignedby;
    }

    public Projectservicedetail markassignedby(String markassignedby) {
        this.markassignedby = markassignedby;
        return this;
    }

    public void setMarkassignedby(String markassignedby) {
        this.markassignedby = markassignedby;
    }

    public Integer getFeerequired() {
        return feerequired;
    }

    public Projectservicedetail feerequired(Integer feerequired) {
        this.feerequired = feerequired;
        return this;
    }

    public void setFeerequired(Integer feerequired) {
        this.feerequired = feerequired;
    }

    public String getStatus() {
        return status;
    }

    public Projectservicedetail status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public Projectservicedetail comment(String comment) {
        this.comment = comment;
        return this;
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
        Projectservicedetail projectservicedetail = (Projectservicedetail) o;
        if (projectservicedetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectservicedetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectservicedetail{" +
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
