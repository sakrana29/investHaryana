package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Businessentitys entity.
 */
public class BusinessentitysDTO implements Serializable {

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

        BusinessentitysDTO businessentitysDTO = (BusinessentitysDTO) o;

        if ( ! Objects.equals(id, businessentitysDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BusinessentitysDTO{" +
            "id=" + id +
            ", businessentitytype='" + businessentitytype + "'" +
            '}';
    }
}
