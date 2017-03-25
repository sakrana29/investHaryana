package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Wwtreatmenttwo.
 */

@Table(name = "wwtreatmenttwo")
public class Wwtreatmenttwo implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Wwtreatmenttwo treatment2(String treatment2) {
        this.treatment2 = treatment2;
        return this;
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
        Wwtreatmenttwo wwtreatmenttwo = (Wwtreatmenttwo) o;
        if (wwtreatmenttwo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, wwtreatmenttwo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Wwtreatmenttwo{" +
            "id=" + id +
            ", treatment2='" + treatment2 + "'" +
            '}';
    }
}
