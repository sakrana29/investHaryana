package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectprocessflowsteps entity.
 */
public class ProjectprocessflowstepsDTO implements Serializable {

    private UUID id;

    private String steps;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }
    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
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
            ", steps='" + steps + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
