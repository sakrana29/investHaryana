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
    private UUID projectid;

    private String formfieldName;

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
            ", projectid='" + projectid + "'" +
            ", formfieldName='" + formfieldName + "'" +
            '}';
    }
}
