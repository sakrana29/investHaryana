package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A District.
 */

@Table(name = "district")
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private String districtname;

    private String statename;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDistrictname() {
        return districtname;
    }

    public District districtname(String districtname) {
        this.districtname = districtname;
        return this;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getStatename() {
        return statename;
    }

    public District statename(String statename) {
        this.statename = statename;
        return this;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        District district = (District) o;
        if (district.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, district.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "District{" +
            "id=" + id +
            ", districtname='" + districtname + "'" +
            ", statename='" + statename + "'" +
            '}';
    }
}
