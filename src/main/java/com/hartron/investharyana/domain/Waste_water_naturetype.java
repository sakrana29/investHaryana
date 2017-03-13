package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Waste_water_naturetype.
 */

@Table(name = "waste_water_naturetype")
public class Waste_water_naturetype implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String naturetype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNaturetype() {
        return naturetype;
    }

    public Waste_water_naturetype naturetype(String naturetype) {
        this.naturetype = naturetype;
        return this;
    }

    public void setNaturetype(String naturetype) {
        this.naturetype = naturetype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Waste_water_naturetype waste_water_naturetype = (Waste_water_naturetype) o;
        if (waste_water_naturetype.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, waste_water_naturetype.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_naturetype{" +
            "id=" + id +
            ", naturetype='" + naturetype + "'" +
            '}';
    }
}
