package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectype entity.
 */
public class ProjectypeDTO implements Serializable {

    private UUID id;

    private String projectypes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getProjectypes() {
        return projectypes;
    }

    public void setProjectypes(String projectypes) {
        this.projectypes = projectypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectypeDTO projectypeDTO = (ProjectypeDTO) o;

        if ( ! Objects.equals(id, projectypeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectypeDTO{" +
            "id=" + id +
            ", projectypes='" + projectypes + "'" +
            '}';
    }
}
