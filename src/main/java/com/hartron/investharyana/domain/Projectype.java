package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectype.
 */

@Table(name = "projectype")
public class Projectype implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Projectype projectypes(String projectypes) {
        this.projectypes = projectypes;
        return this;
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
        Projectype projectype = (Projectype) o;
        if (projectype.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectype.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectype{" +
            "id=" + id +
            ", projectypes='" + projectypes + "'" +
            '}';
    }
}
