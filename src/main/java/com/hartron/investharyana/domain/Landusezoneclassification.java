package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Landusezoneclassification.
 */

@Table(name = "landusezoneclassification")
public class Landusezoneclassification implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String landzoneclassificationtype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLandzoneclassificationtype() {
        return landzoneclassificationtype;
    }

    public Landusezoneclassification landzoneclassificationtype(String landzoneclassificationtype) {
        this.landzoneclassificationtype = landzoneclassificationtype;
        return this;
    }

    public void setLandzoneclassificationtype(String landzoneclassificationtype) {
        this.landzoneclassificationtype = landzoneclassificationtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Landusezoneclassification landusezoneclassification = (Landusezoneclassification) o;
        if (landusezoneclassification.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, landusezoneclassification.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Landusezoneclassification{" +
            "id=" + id +
            ", landzoneclassificationtype='" + landzoneclassificationtype + "'" +
            '}';
    }
}
