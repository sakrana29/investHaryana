package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Wwtreatmenttwo entity.
 */
public class WwtreatmenttwoDTO implements Serializable {

    private UUID id;

    private String treatment2;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getTreatment2() {
        return treatment2;
    }

    public void setTreatment2(String treatment2) {
        this.treatment2 = treatment2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WwtreatmenttwoDTO wwtreatmenttwoDTO = (WwtreatmenttwoDTO) o;

        if ( ! Objects.equals(id, wwtreatmenttwoDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WwtreatmenttwoDTO{" +
            "id=" + id +
            ", treatment2='" + treatment2 + "'" +
            '}';
    }
}
