package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectprocessflowsteps.
 */

@Table(name = "projectprocessflowsteps")
public class Projectprocessflowsteps implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Projectprocessflowsteps steps(String steps) {
        this.steps = steps;
        return this;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Projectprocessflowsteps createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Projectprocessflowsteps updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
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
        Projectprocessflowsteps projectprocessflowsteps = (Projectprocessflowsteps) o;
        if (projectprocessflowsteps.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectprocessflowsteps.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectprocessflowsteps{" +
            "id=" + id +
            ", steps='" + steps + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
