package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Foreignfundingresource entity.
 */
public class ForeignfundingresourceDTO implements Serializable {

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

        ForeignfundingresourceDTO foreignfundingresourceDTO = (ForeignfundingresourceDTO) o;

        if ( ! Objects.equals(id, foreignfundingresourceDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ForeignfundingresourceDTO{" +
            "id=" + id +
            ", foreignfundingtypes='" + foreignfundingtypes + "'" +
            '}';
    }
}
