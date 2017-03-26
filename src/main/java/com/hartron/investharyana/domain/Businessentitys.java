package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Businessentitys.
 */

@Table(name = "businessentitys")
public class Businessentitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

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

    public Businessentitys businessentitytype(String businessentitytype) {
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
        Businessentitys businessentitys = (Businessentitys) o;
        if (businessentitys.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, businessentitys.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Businessentitys{" +
            "id=" + id +
            ", businessentitytype='" + businessentitytype + "'" +
            '}';
    }
}
