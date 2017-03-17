package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectcategory.
 */

@Table(name = "projectcategory")
public class Projectcategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private String categorytype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCategorytype() {
        return categorytype;
    }

    public Projectcategory categorytype(String categorytype) {
        this.categorytype = categorytype;
        return this;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectcategory projectcategory = (Projectcategory) o;
        if (projectcategory.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectcategory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectcategory{" +
            "id=" + id +
            ", categorytype='" + categorytype + "'" +
            '}';
    }
}
