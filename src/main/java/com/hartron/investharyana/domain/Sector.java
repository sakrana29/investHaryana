package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Sector.
 */

@Table(name = "sector")
public class Sector implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Sector sectortype(String sectortype) {
        this.sectortype = sectortype;
        return this;
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
        Sector sector = (Sector) o;
        if (sector.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, sector.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Sector{" +
            "id=" + id +
            ", sectortype='" + sectortype + "'" +
            '}';
    }
}
