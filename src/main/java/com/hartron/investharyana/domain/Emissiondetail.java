package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Emissiondetail.
 */

@Table(name = "emissiondetail")
public class Emissiondetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String capacity;

    private String particulars;

    private String type_of_fuel;

    private String air_pollution_control_device;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCapacity() {
        return capacity;
    }

    public Emissiondetail capacity(String capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getParticulars() {
        return particulars;
    }

    public Emissiondetail particulars(String particulars) {
        this.particulars = particulars;
        return this;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getType_of_fuel() {
        return type_of_fuel;
    }

    public Emissiondetail type_of_fuel(String type_of_fuel) {
        this.type_of_fuel = type_of_fuel;
        return this;
    }

    public void setType_of_fuel(String type_of_fuel) {
        this.type_of_fuel = type_of_fuel;
    }

    public String getAir_pollution_control_device() {
        return air_pollution_control_device;
    }

    public Emissiondetail air_pollution_control_device(String air_pollution_control_device) {
        this.air_pollution_control_device = air_pollution_control_device;
        return this;
    }

    public void setAir_pollution_control_device(String air_pollution_control_device) {
        this.air_pollution_control_device = air_pollution_control_device;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Emissiondetail createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Emissiondetail updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Emissiondetail emissiondetail = (Emissiondetail) o;
        if (emissiondetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, emissiondetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emissiondetail{" +
            "id=" + id +
            ", capacity='" + capacity + "'" +
            ", particulars='" + particulars + "'" +
            ", type_of_fuel='" + type_of_fuel + "'" +
            ", air_pollution_control_device='" + air_pollution_control_device + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
