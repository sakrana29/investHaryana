package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
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

    public Projectprocessflowsteps projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
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
            ", projectid='" + projectid + "'" +
            ", steps='" + steps + "'" +
            '}';
    }
}
