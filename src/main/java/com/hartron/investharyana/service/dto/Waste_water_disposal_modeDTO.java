package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Waste_water_disposal_mode entity.
 */
public class Waste_water_disposal_modeDTO implements Serializable {

    private UUID id;

    @NotNull
    private String mode_of_disposal;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getMode_of_disposal() {
        return mode_of_disposal;
    }

    public void setMode_of_disposal(String mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Waste_water_disposal_modeDTO waste_water_disposal_modeDTO = (Waste_water_disposal_modeDTO) o;

        if ( ! Objects.equals(id, waste_water_disposal_modeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Waste_water_disposal_modeDTO{" +
            "id=" + id +
            ", mode_of_disposal='" + mode_of_disposal + "'" +
            '}';
    }
}
