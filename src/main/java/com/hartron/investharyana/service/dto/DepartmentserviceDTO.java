package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Departmentservice entity.
 */
public class DepartmentserviceDTO implements Serializable {

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

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getStage() {
        return stage;
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

        DepartmentserviceDTO departmentserviceDTO = (DepartmentserviceDTO) o;

        if ( ! Objects.equals(id, departmentserviceDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DepartmentserviceDTO{" +
            "id=" + id +
            ", duration='" + duration + "'" +
            ", stage='" + stage + "'" +
            '}';
    }
}
