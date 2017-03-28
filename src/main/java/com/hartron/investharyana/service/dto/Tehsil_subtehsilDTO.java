package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Tehsil_subtehsil entity.
 */
public class Tehsil_subtehsilDTO implements Serializable {

    private UUID id;

    @NotNull
    private String tehsil_subtehsilname;

    private String districtname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getTehsil_subtehsilname() {
        return tehsil_subtehsilname;
    }

    public void setTehsil_subtehsilname(String tehsil_subtehsilname) {
        this.tehsil_subtehsilname = tehsil_subtehsilname;
    }
    public String getDistrictname() {
        return districtname;
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

        Tehsil_subtehsilDTO tehsil_subtehsilDTO = (Tehsil_subtehsilDTO) o;

        if ( ! Objects.equals(id, tehsil_subtehsilDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tehsil_subtehsilDTO{" +
            "id=" + id +
            ", tehsil_subtehsilname='" + tehsil_subtehsilname + "'" +
            ", districtname='" + districtname + "'" +
            '}';
    }
}
