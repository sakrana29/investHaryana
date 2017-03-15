package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A City_town_village.
 */

@Table(name = "city_town_village")
public class City_town_village implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID disctrictid;

    private UUID blockid;

    private String city_town_village_name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDisctrictid() {
        return disctrictid;
    }

    public City_town_village disctrictid(UUID disctrictid) {
        this.disctrictid = disctrictid;
        return this;
    }

    public void setDisctrictid(UUID disctrictid) {
        this.disctrictid = disctrictid;
    }

    public UUID getBlockid() {
        return blockid;
    }

    public City_town_village blockid(UUID blockid) {
        this.blockid = blockid;
        return this;
    }

    public void setBlockid(UUID blockid) {
        this.blockid = blockid;
    }

    public String getCity_town_village_name() {
        return city_town_village_name;
    }

    public City_town_village city_town_village_name(String city_town_village_name) {
        this.city_town_village_name = city_town_village_name;
        return this;
    }

    public void setCity_town_village_name(String city_town_village_name) {
        this.city_town_village_name = city_town_village_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City_town_village city_town_village = (City_town_village) o;
        if (city_town_village.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, city_town_village.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "City_town_village{" +
            "id=" + id +
            ", disctrictid='" + disctrictid + "'" +
            ", blockid='" + blockid + "'" +
            ", city_town_village_name='" + city_town_village_name + "'" +
            '}';
    }
}
