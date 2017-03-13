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

    @NotNull
    private UUID departmentID;

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
    public UUID getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(UUID departmentID) {
        this.departmentID = departmentID;
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
            ", departmentID='" + departmentID + "'" +
            '}';
    }
}
