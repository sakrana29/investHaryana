package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Filetesting entity.
 */
public class FiletestingDTO implements Serializable {

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

        FiletestingDTO filetestingDTO = (FiletestingDTO) o;

        if ( ! Objects.equals(id, filetestingDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FiletestingDTO{" +
            "id=" + id +
            ", filename='" + filename + "'" +
            '}';
    }
}
