package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Waste_water_naturetype entity.
 */
public class Waste_water_naturetypeDTO implements Serializable {

    private UUID id;

    @NotNull
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

        Waste_water_naturetypeDTO waste_water_naturetypeDTO = (Waste_water_naturetypeDTO) o;

        if ( ! Objects.equals(id, waste_water_naturetypeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_naturetypeDTO{" +
            "id=" + id +
            ", naturetype='" + naturetype + "'" +
            '}';
    }
}
