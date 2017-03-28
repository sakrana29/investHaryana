package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A State.
 */

@Table(name = "state")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private UUID countryid;

    @NotNull
    private String statename;

    private String countryname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCountryid() {
        return countryid;
    }

    public State countryid(UUID countryid) {
        this.countryid = countryid;
        return this;
    }

    public void setCountryid(UUID countryid) {
        this.countryid = countryid;
    }

    public String getStatename() {
        return statename;
    }

    public State statename(String statename) {
        this.statename = statename;
        return this;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getCountryname() {
        return countryname;
    }

    public State countryname(String countryname) {
        this.countryname = countryname;
        return this;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        if (state.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, state.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "State{" +
            "id=" + id +
            ", countryid='" + countryid + "'" +
            ", statename='" + statename + "'" +
            ", countryname='" + countryname + "'" +
            '}';
    }
}
