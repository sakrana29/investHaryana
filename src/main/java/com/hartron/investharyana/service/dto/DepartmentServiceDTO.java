package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the DepartmentService entity.
 */
public class DepartmentServiceDTO implements Serializable {

    private UUID id;

    @NotNull
    private String serviceName;

    @NotNull
    private String serviceDescription;

    private Integer duration;

    private String stage;

    private String departmentname;

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

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
    public UUID getDepartmentid() {
        return departmentid;
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

        DepartmentServiceDTO departmentServiceDTO = (DepartmentServiceDTO) o;

        if ( ! Objects.equals(id, departmentServiceDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DepartmentServiceDTO{" +
            "id=" + id +
            ", serviceName='" + serviceName + "'" +
            ", serviceDescription='" + serviceDescription + "'" +
            ", duration='" + duration + "'" +
            ", stage='" + stage + "'" +
            ", departmentname='" + departmentname + "'" +
            ", departmentid='" + departmentid + "'" +
            '}';
    }
}
