package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Department entity.
 */
public class DepartmentDTO implements Serializable {

    private UUID id;

    @NotNull
    private String departmentname;

    @NotNull
    private String description;

    private String hod;

    private String email;

    private String hodmobile;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getHodmobile() {
        return hodmobile;
    }

    public void setHodmobile(String hodmobile) {
        this.hodmobile = hodmobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepartmentDTO departmentDTO = (DepartmentDTO) o;

        if ( ! Objects.equals(id, departmentDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
            "id=" + id +
            ", departmentname='" + departmentname + "'" +
            ", description='" + description + "'" +
            ", hod='" + hod + "'" +
            ", email='" + email + "'" +
            ", hodmobile='" + hodmobile + "'" +
            '}';
    }
}
