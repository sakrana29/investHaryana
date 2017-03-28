package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Tehsil_subtehsil.
 */

@Table(name = "tehsil_subtehsil")
public class Tehsil_subtehsil implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private UUID districtid;

    @NotNull
    private String tehsil_subtehsilname;

    @NotNull
    private String districtname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDistrictid() {
        return districtid;
    }

    public Tehsil_subtehsil districtid(UUID districtid) {
        this.districtid = districtid;
        return this;
    }

    public void setDistrictid(UUID districtid) {
        this.districtid = districtid;
    }

    public String getTehsil_subtehsilname() {
        return tehsil_subtehsilname;
    }

    public Tehsil_subtehsil tehsil_subtehsilname(String tehsil_subtehsilname) {
        this.tehsil_subtehsilname = tehsil_subtehsilname;
        return this;
    }

    public void setTehsil_subtehsilname(String tehsil_subtehsilname) {
        this.tehsil_subtehsilname = tehsil_subtehsilname;
    }

    public String getDistrictname() {
        return districtname;
    }

    public Tehsil_subtehsil districtname(String districtname) {
        this.districtname = districtname;
        return this;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tehsil_subtehsil tehsil_subtehsil = (Tehsil_subtehsil) o;
        if (tehsil_subtehsil.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tehsil_subtehsil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tehsil_subtehsil{" +
            "id=" + id +
            ", districtid='" + districtid + "'" +
            ", tehsil_subtehsilname='" + tehsil_subtehsilname + "'" +
            ", districtname='" + districtname + "'" +
            '}';
    }
}
