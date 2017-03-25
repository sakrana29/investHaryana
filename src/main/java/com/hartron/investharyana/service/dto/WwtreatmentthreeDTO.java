package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Wwtreatmentthree entity.
 */
public class WwtreatmentthreeDTO implements Serializable {

    private UUID id;

    private String treatment3;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getTreatment3() {
        return treatment3;
    }

    public void setTreatment3(String treatment3) {
        this.treatment3 = treatment3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WwtreatmentthreeDTO wwtreatmentthreeDTO = (WwtreatmentthreeDTO) o;

        if ( ! Objects.equals(id, wwtreatmentthreeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WwtreatmentthreeDTO{" +
            "id=" + id +
            ", treatment3='" + treatment3 + "'" +
            '}';
    }
}
