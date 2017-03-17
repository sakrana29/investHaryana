package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Businessentity.
 */

@Table(name = "businessentity")
public class Businessentity implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private String businessentitytype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBusinessentitytype() {
        return businessentitytype;
    }

    public Businessentity businessentitytype(String businessentitytype) {
        this.businessentitytype = businessentitytype;
        return this;
    }

    public void setBusinessentitytype(String businessentitytype) {
        this.businessentitytype = businessentitytype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Businessentity businessentity = (Businessentity) o;
        if (businessentity.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, businessentity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Businessentity{" +
            "id=" + id +
            ", businessentitytype='" + businessentitytype + "'" +
            '}';
    }
}
