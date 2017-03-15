package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Regular_electrict_load_type.
 */

@Table(name = "regular_electrict_load_type")
public class Regular_electrict_load_type implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String typeofload;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTypeofload() {
        return typeofload;
    }

    public Regular_electrict_load_type typeofload(String typeofload) {
        this.typeofload = typeofload;
        return this;
    }

    public void setTypeofload(String typeofload) {
        this.typeofload = typeofload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Regular_electrict_load_type regular_electrict_load_type = (Regular_electrict_load_type) o;
        if (regular_electrict_load_type.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, regular_electrict_load_type.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Regular_electrict_load_type{" +
            "id=" + id +
            ", typeofload='" + typeofload + "'" +
            '}';
    }
}
