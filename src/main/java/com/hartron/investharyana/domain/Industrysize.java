package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Industrysize.
 */

@Table(name = "industrysize")
public class Industrysize implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String sizeofindustry;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSizeofindustry() {
        return sizeofindustry;
    }

    public Industrysize sizeofindustry(String sizeofindustry) {
        this.sizeofindustry = sizeofindustry;
        return this;
    }

    public void setSizeofindustry(String sizeofindustry) {
        this.sizeofindustry = sizeofindustry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Industrysize industrysize = (Industrysize) o;
        if (industrysize.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, industrysize.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Industrysize{" +
            "id=" + id +
            ", sizeofindustry='" + sizeofindustry + "'" +
            '}';
    }
}
