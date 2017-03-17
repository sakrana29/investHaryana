package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectcategory entity.
 */
public class ProjectcategoryDTO implements Serializable {

    private UUID id;

    @NotNull
    private String categorytype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectcategoryDTO projectcategoryDTO = (ProjectcategoryDTO) o;

        if ( ! Objects.equals(id, projectcategoryDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectcategoryDTO{" +
            "id=" + id +
            ", categorytype='" + categorytype + "'" +
            '}';
    }
}
