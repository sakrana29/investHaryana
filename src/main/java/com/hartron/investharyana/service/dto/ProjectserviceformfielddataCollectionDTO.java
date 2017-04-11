package com.hartron.investharyana.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectserviceformfielddata entity.
 */
public class ProjectserviceformfielddataCollectionDTO implements Serializable {

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

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }
    public String getFormfieldvalue() {
        return formfieldvalue;
    }

    public void setFormfieldvalue(String formfieldvalue) {
        this.formfieldvalue = formfieldvalue;
    }
    public UUID getProjectid() {
        return projectid;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public String getFormfieldName() {
        return formfieldName;
    }

    public void setFormfieldName(String formfieldName) {
        this.formfieldName = formfieldName;
    }
    public UUID getServiceformfieldid() {
        return serviceformfieldid;
    }

    public void setServiceformfieldid(UUID serviceformfieldid) {
        this.serviceformfieldid = serviceformfieldid;
    }
    public String getFormfieldtype() {
        return formfieldtype;
    }

    public void setFormfieldtype(String formfieldtype) {
        this.formfieldtype = formfieldtype;
    }
    public Integer getFormfieldOrder() {
        return formfieldOrder;
    }

    public void setFormfieldOrder(Integer formfieldOrder) {
        this.formfieldOrder = formfieldOrder;
    }
    public String getFormtypeOption() {
        return formtypeOption;
    }

    public void setFormtypeOption(String formtypeOption) {
        this.formtypeOption = formtypeOption;
    }
}
