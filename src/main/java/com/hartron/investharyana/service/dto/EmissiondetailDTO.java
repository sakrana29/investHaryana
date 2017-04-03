package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Emissiondetail entity.
 */
public class EmissiondetailDTO implements Serializable {

    private UUID id;

    private String capacity;

    private String particulars;

    private String type_of_fuel;

    private String air_pollution_control_device;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    private UUID projectid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }
    public String getType_of_fuel() {
        return type_of_fuel;
    }

    public void setType_of_fuel(String type_of_fuel) {
        this.type_of_fuel = type_of_fuel;
    }
    public String getAir_pollution_control_device() {
        return air_pollution_control_device;
    }

    public void setAir_pollution_control_device(String air_pollution_control_device) {
        this.air_pollution_control_device = air_pollution_control_device;
    }
    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }
    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
    }
    public UUID getProjectid() {
        return projectid;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmissiondetailDTO emissiondetailDTO = (EmissiondetailDTO) o;

        if ( ! Objects.equals(id, emissiondetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EmissiondetailDTO{" +
            "id=" + id +
            ", capacity='" + capacity + "'" +
            ", particulars='" + particulars + "'" +
            ", type_of_fuel='" + type_of_fuel + "'" +
            ", air_pollution_control_device='" + air_pollution_control_device + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            ", projectid='" + projectid + "'" +
            '}';
    }
}
