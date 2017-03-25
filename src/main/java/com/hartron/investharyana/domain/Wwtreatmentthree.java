package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Wwtreatmentthree.
 */

@Table(name = "wwtreatmentthree")
public class Wwtreatmentthree implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Wwtreatmentthree treatment3(String treatment3) {
        this.treatment3 = treatment3;
        return this;
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
        Wwtreatmentthree wwtreatmentthree = (Wwtreatmentthree) o;
        if (wwtreatmentthree.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, wwtreatmentthree.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Wwtreatmentthree{" +
            "id=" + id +
            ", treatment3='" + treatment3 + "'" +
            '}';
    }
}
