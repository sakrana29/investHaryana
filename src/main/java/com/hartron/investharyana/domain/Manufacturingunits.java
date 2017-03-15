package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Manufacturingunits.
 */

@Table(name = "manufacturingunits")
public class Manufacturingunits implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String unittypes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUnittypes() {
        return unittypes;
    }

    public Manufacturingunits unittypes(String unittypes) {
        this.unittypes = unittypes;
        return this;
    }

    public void setUnittypes(String unittypes) {
        this.unittypes = unittypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manufacturingunits manufacturingunits = (Manufacturingunits) o;
        if (manufacturingunits.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, manufacturingunits.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Manufacturingunits{" +
            "id=" + id +
            ", unittypes='" + unittypes + "'" +
            '}';
    }
}
