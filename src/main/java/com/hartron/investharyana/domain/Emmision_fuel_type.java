package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Emmision_fuel_type.
 */

@Table(name = "emmision_fuel_type")
public class Emmision_fuel_type implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String typeoffuel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTypeoffuel() {
        return typeoffuel;
    }

    public Emmision_fuel_type typeoffuel(String typeoffuel) {
        this.typeoffuel = typeoffuel;
        return this;
    }

    public void setTypeoffuel(String typeoffuel) {
        this.typeoffuel = typeoffuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emmision_fuel_type emmision_fuel_type = (Emmision_fuel_type) o;
        if (emmision_fuel_type.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, emmision_fuel_type.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emmision_fuel_type{" +
            "id=" + id +
            ", typeoffuel='" + typeoffuel + "'" +
            '}';
    }
}
