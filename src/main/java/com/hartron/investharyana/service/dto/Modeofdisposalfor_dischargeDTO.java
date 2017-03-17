package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Modeofdisposalfor_discharge entity.
 */
public class Modeofdisposalfor_dischargeDTO implements Serializable {

    private UUID id;

    @NotNull
    private String disposal_for_discharge;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getDisposal_for_discharge() {
        return disposal_for_discharge;
    }

    public void setDisposal_for_discharge(String disposal_for_discharge) {
        this.disposal_for_discharge = disposal_for_discharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO = (Modeofdisposalfor_dischargeDTO) o;

        if ( ! Objects.equals(id, modeofdisposalfor_dischargeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Modeofdisposalfor_dischargeDTO{" +
            "id=" + id +
            ", disposal_for_discharge='" + disposal_for_discharge + "'" +
            '}';
    }
}
