package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Watersupplysource.
 */

@Table(name = "watersupplysource")
public class Watersupplysource implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String watersupplysourcetype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWatersupplysourcetype() {
        return watersupplysourcetype;
    }

    public Watersupplysource watersupplysourcetype(String watersupplysourcetype) {
        this.watersupplysourcetype = watersupplysourcetype;
        return this;
    }

    public void setWatersupplysourcetype(String watersupplysourcetype) {
        this.watersupplysourcetype = watersupplysourcetype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Watersupplysource watersupplysource = (Watersupplysource) o;
        if (watersupplysource.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, watersupplysource.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Watersupplysource{" +
            "id=" + id +
            ", watersupplysourcetype='" + watersupplysourcetype + "'" +
            '}';
    }
}
