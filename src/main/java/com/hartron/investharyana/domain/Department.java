package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Department.
 */

@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Department departmentname(String departmentname) {
        this.departmentname = departmentname;
        return this;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDescription() {
        return description;
    }

    public Department description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHod() {
        return hod;
    }

    public Department hod(String hod) {
        this.hod = hod;
        return this;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }

    public String getEmail() {
        return email;
    }

    public Department email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHodmobile() {
        return hodmobile;
    }

    public Department hodmobile(String hodmobile) {
        this.hodmobile = hodmobile;
        return this;
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
        Department department = (Department) o;
        if (department.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, department.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + id +
            ", departmentname='" + departmentname + "'" +
            ", description='" + description + "'" +
            ", hod='" + hod + "'" +
            ", email='" + email + "'" +
            ", hodmobile='" + hodmobile + "'" +
            '}';
    }
}
