package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Emmision_pollution_controll entity.
 */
public class Emmision_pollution_controllDTO implements Serializable {

    private UUID id;

    private String airpollutioncontroldevice;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getAirpollutioncontroldevice() {
        return airpollutioncontroldevice;
    }

    public void setAirpollutioncontroldevice(String airpollutioncontroldevice) {
        this.airpollutioncontroldevice = airpollutioncontroldevice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Emmision_pollution_controllDTO emmision_pollution_controllDTO = (Emmision_pollution_controllDTO) o;

        if ( ! Objects.equals(id, emmision_pollution_controllDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emmision_pollution_controllDTO{" +
            "id=" + id +
            ", airpollutioncontroldevice='" + airpollutioncontroldevice + "'" +
            '}';
    }
}
