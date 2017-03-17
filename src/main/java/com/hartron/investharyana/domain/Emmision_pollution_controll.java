package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Emmision_pollution_controll.
 */

@Table(name = "emmision_pollution_controll")
public class Emmision_pollution_controll implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
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

    public Emmision_pollution_controll airpollutioncontroldevice(String airpollutioncontroldevice) {
        this.airpollutioncontroldevice = airpollutioncontroldevice;
        return this;
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
        Emmision_pollution_controll emmision_pollution_controll = (Emmision_pollution_controll) o;
        if (emmision_pollution_controll.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, emmision_pollution_controll.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Emmision_pollution_controll{" +
            "id=" + id +
            ", airpollutioncontroldevice='" + airpollutioncontroldevice + "'" +
            '}';
    }
}
