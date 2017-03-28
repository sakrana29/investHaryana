package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectserviceformfielddata.
 */

@Table(name = "projectserviceformfielddata")
public class Projectserviceformfielddata implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private UUID serviceid;

    private String formfieldvalue;

    @NotNull
    private UUID projectserviceformfieldvalue;

    @NotNull
    private UUID projectid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getServiceid() {
        return serviceid;
    }

    public Projectserviceformfielddata serviceid(UUID serviceid) {
        this.serviceid = serviceid;
        return this;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }

    public String getFormfieldvalue() {
        return formfieldvalue;
    }

    public Projectserviceformfielddata formfieldvalue(String formfieldvalue) {
        this.formfieldvalue = formfieldvalue;
        return this;
    }

    public void setFormfieldvalue(String formfieldvalue) {
        this.formfieldvalue = formfieldvalue;
    }

    public UUID getProjectserviceformfieldvalue() {
        return projectserviceformfieldvalue;
    }

    public Projectserviceformfielddata projectserviceformfieldvalue(UUID projectserviceformfieldvalue) {
        this.projectserviceformfieldvalue = projectserviceformfieldvalue;
        return this;
    }

    public void setProjectserviceformfieldvalue(UUID projectserviceformfieldvalue) {
        this.projectserviceformfieldvalue = projectserviceformfieldvalue;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Projectserviceformfielddata projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectserviceformfielddata projectserviceformfielddata = (Projectserviceformfielddata) o;
        if (projectserviceformfielddata.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectserviceformfielddata.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectserviceformfielddata{" +
            "id=" + id +
            ", serviceid='" + serviceid + "'" +
            ", formfieldvalue='" + formfieldvalue + "'" +
            ", projectserviceformfieldvalue='" + projectserviceformfieldvalue + "'" +
            ", projectid='" + projectid + "'" +
            '}';
    }
}
