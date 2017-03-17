package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Approvalforms entity.
 */
public class ApprovalformsDTO implements Serializable {

    private UUID id;

    @NotNull
    private String existingapprovalforms;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getExistingapprovalforms() {
        return existingapprovalforms;
    }

    public void setExistingapprovalforms(String existingapprovalforms) {
        this.existingapprovalforms = existingapprovalforms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApprovalformsDTO approvalformsDTO = (ApprovalformsDTO) o;

        if ( ! Objects.equals(id, approvalformsDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ApprovalformsDTO{" +
            "id=" + id +
            ", existingapprovalforms='" + existingapprovalforms + "'" +
            '}';
    }
}
