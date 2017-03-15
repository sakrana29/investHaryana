package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Manufacturingunits entity.
 */
public class ManufacturingunitsDTO implements Serializable {

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

        ManufacturingunitsDTO manufacturingunitsDTO = (ManufacturingunitsDTO) o;

        if ( ! Objects.equals(id, manufacturingunitsDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ManufacturingunitsDTO{" +
            "id=" + id +
            ", unittypes='" + unittypes + "'" +
            '}';
    }
}
