package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Approvalforms.
 */

@Table(name = "approvalforms")
public class Approvalforms implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

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

    public Approvalforms existingapprovalforms(String existingapprovalforms) {
        this.existingapprovalforms = existingapprovalforms;
        return this;
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
        Approvalforms approvalforms = (Approvalforms) o;
        if (approvalforms.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, approvalforms.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Approvalforms{" +
            "id=" + id +
            ", existingapprovalforms='" + existingapprovalforms + "'" +
            '}';
    }
}
