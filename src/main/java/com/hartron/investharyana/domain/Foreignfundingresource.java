package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Foreignfundingresource.
 */

@Table(name = "foreignfundingresource")
public class Foreignfundingresource implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private String foreignfundingtypes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getForeignfundingtypes() {
        return foreignfundingtypes;
    }

    public Foreignfundingresource foreignfundingtypes(String foreignfundingtypes) {
        this.foreignfundingtypes = foreignfundingtypes;
        return this;
    }

    public void setForeignfundingtypes(String foreignfundingtypes) {
        this.foreignfundingtypes = foreignfundingtypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Foreignfundingresource foreignfundingresource = (Foreignfundingresource) o;
        if (foreignfundingresource.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, foreignfundingresource.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Foreignfundingresource{" +
            "id=" + id +
            ", foreignfundingtypes='" + foreignfundingtypes + "'" +
            '}';
    }
}
