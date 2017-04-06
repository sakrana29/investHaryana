package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the ServiceFormField entity.
 */
public class ServiceFormFieldDTO implements Serializable {

    private UUID id;

    @NotNull
    private String fieldName;

    @NotNull
    private String fieldType;

    @NotNull
    private UUID serviceID;

    private String option;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
    public UUID getServiceID() {
        return serviceID;
    }

    public void setServiceID(UUID serviceID) {
        this.serviceID = serviceID;
    }
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceFormFieldDTO serviceFormFieldDTO = (ServiceFormFieldDTO) o;

        if ( ! Objects.equals(id, serviceFormFieldDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ServiceFormFieldDTO{" +
            "id=" + id +
            ", fieldName='" + fieldName + "'" +
            ", fieldType='" + fieldType + "'" +
            ", serviceID='" + serviceID + "'" +
            ", option='" + option + "'" +
            '}';
    }
}
