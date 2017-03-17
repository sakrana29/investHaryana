package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Industrysize entity.
 */
public class IndustrysizeDTO implements Serializable {

    private UUID id;

    @NotNull
    private String sizeofindustry;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getSizeofindustry() {
        return sizeofindustry;
    }

    public void setSizeofindustry(String sizeofindustry) {
        this.sizeofindustry = sizeofindustry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IndustrysizeDTO industrysizeDTO = (IndustrysizeDTO) o;

        if ( ! Objects.equals(id, industrysizeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "IndustrysizeDTO{" +
            "id=" + id +
            ", sizeofindustry='" + sizeofindustry + "'" +
            '}';
    }
}
