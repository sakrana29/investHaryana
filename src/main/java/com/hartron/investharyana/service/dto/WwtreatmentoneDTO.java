package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Wwtreatmentone entity.
 */
public class WwtreatmentoneDTO implements Serializable {

    private UUID id;

    private String treatment1;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getTreatment1() {
        return treatment1;
    }

    public void setTreatment1(String treatment1) {
        this.treatment1 = treatment1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WwtreatmentoneDTO wwtreatmentoneDTO = (WwtreatmentoneDTO) o;

        if ( ! Objects.equals(id, wwtreatmentoneDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WwtreatmentoneDTO{" +
            "id=" + id +
            ", treatment1='" + treatment1 + "'" +
            '}';
    }
}
