package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Modeofdisposalfor_discharge.
 */

@Table(name = "modeofdisposalfor_discharge")
public class Modeofdisposalfor_discharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

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

    public Modeofdisposalfor_discharge disposal_for_discharge(String disposal_for_discharge) {
        this.disposal_for_discharge = disposal_for_discharge;
        return this;
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
        Modeofdisposalfor_discharge modeofdisposalfor_discharge = (Modeofdisposalfor_discharge) o;
        if (modeofdisposalfor_discharge.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, modeofdisposalfor_discharge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Modeofdisposalfor_discharge{" +
            "id=" + id +
            ", disposal_for_discharge='" + disposal_for_discharge + "'" +
            '}';
    }
}
