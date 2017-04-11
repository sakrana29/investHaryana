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
    private UUID projectid;

    private String formfieldName;

    private UUID serviceformfieldid;

    private String formfieldtype;

    private Integer formfieldOrder;

    private String formtypeOption;

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

    public String getFormfieldName() {
        return formfieldName;
    }

    public Projectserviceformfielddata formfieldName(String formfieldName) {
        this.formfieldName = formfieldName;
        return this;
    }

    public void setFormfieldName(String formfieldName) {
        this.formfieldName = formfieldName;
    }

    public UUID getServiceformfieldid() {
        return serviceformfieldid;
    }

    public Projectserviceformfielddata serviceformfieldid(UUID serviceformfieldid) {
        this.serviceformfieldid = serviceformfieldid;
        return this;
    }

    public void setServiceformfieldid(UUID serviceformfieldid) {
        this.serviceformfieldid = serviceformfieldid;
    }

    public String getFormfieldtype() {
        return formfieldtype;
    }

    public Projectserviceformfielddata formfieldtype(String formfieldtype) {
        this.formfieldtype = formfieldtype;
        return this;
    }

    public void setFormfieldtype(String formfieldtype) {
        this.formfieldtype = formfieldtype;
    }

    public Integer getFormfieldOrder() {
        return formfieldOrder;
    }

    public Projectserviceformfielddata formfieldOrder(Integer formfieldOrder) {
        this.formfieldOrder = formfieldOrder;
        return this;
    }

    public void setFormfieldOrder(Integer formfieldOrder) {
        this.formfieldOrder = formfieldOrder;
    }

    public String getFormtypeOption() {
        return formtypeOption;
    }

    public Projectserviceformfielddata formtypeOption(String formtypeOption) {
        this.formtypeOption = formtypeOption;
        return this;
    }

    public void setFormtypeOption(String formtypeOption) {
        this.formtypeOption = formtypeOption;
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
            ", projectid='" + projectid + "'" +
            ", formfieldName='" + formfieldName + "'" +
            ", serviceformfieldid='" + serviceformfieldid + "'" +
            ", formfieldtype='" + formfieldtype + "'" +
            ", formfieldOrder='" + formfieldOrder + "'" +
            ", formtypeOption='" + formtypeOption + "'" +
            '}';
    }
}
