package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DepartmentService.
 */

@Table(name = "departmentService")
public class DepartmentService implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private String serviceName;

    @NotNull
    private String serviceDescription;

    private Integer duration;

    private String stage;

    private UUID departmentid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public DepartmentService serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public DepartmentService serviceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
        return this;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Integer getDuration() {
        return duration;
    }

    public DepartmentService duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStage() {
        return stage;
    }

    public DepartmentService stage(String stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public UUID getDepartmentid() {
        return departmentid;
    }

    public DepartmentService departmentid(UUID departmentid) {
        this.departmentid = departmentid;
        return this;
    }

    public void setDepartmentid(UUID departmentid) {
        this.departmentid = departmentid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DepartmentService departmentService = (DepartmentService) o;
        if (departmentService.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, departmentService.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DepartmentService{" +
            "id=" + id +
            ", serviceName='" + serviceName + "'" +
            ", serviceDescription='" + serviceDescription + "'" +
            ", duration='" + duration + "'" +
            ", stage='" + stage + "'" +
            ", departmentid='" + departmentid + "'" +
            '}';
    }
}
