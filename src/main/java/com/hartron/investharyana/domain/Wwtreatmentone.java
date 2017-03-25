package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Wwtreatmentone.
 */

@Table(name = "wwtreatmentone")
public class Wwtreatmentone implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Wwtreatmentone treatment1(String treatment1) {
        this.treatment1 = treatment1;
        return this;
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
        Wwtreatmentone wwtreatmentone = (Wwtreatmentone) o;
        if (wwtreatmentone.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, wwtreatmentone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Wwtreatmentone{" +
            "id=" + id +
            ", treatment1='" + treatment1 + "'" +
            '}';
    }
}
