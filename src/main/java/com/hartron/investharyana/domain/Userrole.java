package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Userrole.
 */

@Table(name = "userrole")
public class Userrole implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private String userrole;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserrole() {
        return userrole;
    }

    public Userrole userrole(String userrole) {
        this.userrole = userrole;
        return this;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Userrole userrole = (Userrole) o;
        if (userrole.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userrole.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Userrole{" +
            "id=" + id +
            ", userrole='" + userrole + "'" +
            '}';
    }
}
