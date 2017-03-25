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

    public String getUserlogin() {
        return userlogin;
    }

    public Projectservicedetail userlogin(String userlogin) {
        this.userlogin = userlogin;
        return this;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    public Boolean isServicerequired() {
        return servicerequired;
    }

    public Projectservicedetail servicerequired(Boolean servicerequired) {
        this.servicerequired = servicerequired;
        return this;
    }

    public void setServicerequired(Boolean servicerequired) {
        this.servicerequired = servicerequired;
    }

    public String getServicestatus() {
        return servicestatus;
    }

    public Projectservicedetail servicestatus(String servicestatus) {
        this.servicestatus = servicestatus;
        return this;
    }

    public void setServicestatus(String servicestatus) {
        this.servicestatus = servicestatus;
    }

    public ZonedDateTime getAssigndate() {
        return assigndate;
    }

    public Projectservicedetail assigndate(ZonedDateTime assigndate) {
        this.assigndate = assigndate;
        return this;
    }

    public void setAssigndate(ZonedDateTime assigndate) {
        this.assigndate = assigndate;
    }

    public Double getServicefee() {
        return servicefee;
    }

    public Projectservicedetail servicefee(Double servicefee) {
        this.servicefee = servicefee;
        return this;
    }

    public void setServicefee(Double servicefee) {
        this.servicefee = servicefee;
    }

    public String getRemarks() {
        return remarks;
    }

    public Projectservicedetail remarks(String remarks) {
        this.remarks = remarks;
        return this;
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
            ", userlogin='" + userlogin + "'" +
            ", servicerequired='" + servicerequired + "'" +
            ", servicestatus='" + servicestatus + "'" +
            ", assigndate='" + assigndate + "'" +
            ", servicefee='" + servicefee + "'" +
            ", remarks='" + remarks + "'" +
            '}';
    }
}
