package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Sector entity.
 */
public class SectorDTO implements Serializable {

    private UUID id;

    @NotNull
    private String sectortype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getSectortype() {
        return sectortype;
    }

    public void setSectortype(String sectortype) {
        this.sectortype = sectortype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SectorDTO sectorDTO = (SectorDTO) o;

        if ( ! Objects.equals(id, sectorDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SectorDTO{" +
            "id=" + id +
            ", sectortype='" + sectortype + "'" +
            '}';
    }
}
