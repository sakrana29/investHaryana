package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Departmentservice.
 */

@Table(name = "departmentservice")
public class Departmentservice implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String duration;

    private String stage;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public Departmentservice duration(String duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStage() {
        return stage;
    }

    public Departmentservice stage(String stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Departmentservice departmentservice = (Departmentservice) o;
        if (departmentservice.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, departmentservice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Departmentservice{" +
            "id=" + id +
            ", duration='" + duration + "'" +
            ", stage='" + stage + "'" +
            '}';
    }
}
