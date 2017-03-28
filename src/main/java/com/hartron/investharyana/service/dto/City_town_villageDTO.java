package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the City_town_village entity.
 */
public class City_town_villageDTO implements Serializable {

    private UUID id;

    @NotNull
    private String city_town_village_name;

    private String blockname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getCity_town_village_name() {
        return city_town_village_name;
    }

    public void setCity_town_village_name(String city_town_village_name) {
        this.city_town_village_name = city_town_village_name;
    }
    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        City_town_villageDTO city_town_villageDTO = (City_town_villageDTO) o;

        if ( ! Objects.equals(id, city_town_villageDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "City_town_villageDTO{" +
            "id=" + id +
            ", city_town_village_name='" + city_town_village_name + "'" +
            ", blockname='" + blockname + "'" +
            '}';
    }
}
