package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Regular_electrict_load_type entity.
 */
public class Regular_electrict_load_typeDTO implements Serializable {

    private UUID id;

    @NotNull
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

        Regular_electrict_load_typeDTO regular_electrict_load_typeDTO = (Regular_electrict_load_typeDTO) o;

        if ( ! Objects.equals(id, regular_electrict_load_typeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Regular_electrict_load_typeDTO{" +
            "id=" + id +
            ", typeofload='" + typeofload + "'" +
            '}';
    }
}
