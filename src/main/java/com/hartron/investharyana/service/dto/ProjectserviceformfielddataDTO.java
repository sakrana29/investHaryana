package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectserviceformfielddata entity.
 */
public class ProjectserviceformfielddataDTO implements Serializable {

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

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }
    public String getFormfieldvalue() {
        return formfieldvalue;
    }

    public void setFormfieldvalue(String formfieldvalue) {
        this.formfieldvalue = formfieldvalue;
    }
    public UUID getProjectserviceformfieldvalue() {
        return projectserviceformfieldvalue;
    }

    public void setProjectserviceformfieldvalue(UUID projectserviceformfieldvalue) {
        this.projectserviceformfieldvalue = projectserviceformfieldvalue;
    }
    public UUID getProjectid() {
        return projectid;
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

        ProjectserviceformfielddataDTO projectserviceformfielddataDTO = (ProjectserviceformfielddataDTO) o;

        if ( ! Objects.equals(id, projectserviceformfielddataDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectserviceformfielddataDTO{" +
            "id=" + id +
            ", serviceid='" + serviceid + "'" +
            ", formfieldvalue='" + formfieldvalue + "'" +
            ", projectserviceformfieldvalue='" + projectserviceformfieldvalue + "'" +
            ", projectid='" + projectid + "'" +
            '}';
    }
}
