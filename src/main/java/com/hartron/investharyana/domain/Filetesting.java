package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Filetesting.
 */

@Table(name = "filetesting")
public class Filetesting implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String filename;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public Filetesting filename(String filename) {
        this.filename = filename;
        return this;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Filetesting filetesting = (Filetesting) o;
        if (filetesting.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, filetesting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Filetesting{" +
            "id=" + id +
            ", filename='" + filename + "'" +
            '}';
    }
}
