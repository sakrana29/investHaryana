package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Emmision_fuel_type entity.
 */
public class Emmision_fuel_typeDTO implements Serializable {

    private UUID id;

    @NotNull
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

        Emmision_fuel_typeDTO emmision_fuel_typeDTO = (Emmision_fuel_typeDTO) o;

        if ( ! Objects.equals(id, emmision_fuel_typeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emmision_fuel_typeDTO{" +
            "id=" + id +
            ", typeoffuel='" + typeoffuel + "'" +
            '}';
    }
}
