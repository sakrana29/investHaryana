package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectprocessflowsteps entity.
 */
public class ProjectprocessflowstepsDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private String steps;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getProjectid() {
        return projectid;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectprocessflowstepsDTO projectprocessflowstepsDTO = (ProjectprocessflowstepsDTO) o;

        if ( ! Objects.equals(id, projectprocessflowstepsDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectprocessflowstepsDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", steps='" + steps + "'" +
            '}';
    }
}
