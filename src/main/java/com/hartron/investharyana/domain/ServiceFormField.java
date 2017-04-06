package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A ServiceFormField.
 */

@Table(name = "serviceFormField")
public class ServiceFormField implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public ServiceFormField fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public ServiceFormField fieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public UUID getServiceID() {
        return serviceID;
    }

    public ServiceFormField serviceID(UUID serviceID) {
        this.serviceID = serviceID;
        return this;
    }

    public void setServiceID(UUID serviceID) {
        this.serviceID = serviceID;
    }

    public String getOption() {
        return option;
    }

    public ServiceFormField option(String option) {
        this.option = option;
        return this;
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
        ServiceFormField serviceFormField = (ServiceFormField) o;
        if (serviceFormField.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, serviceFormField.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ServiceFormField{" +
            "id=" + id +
            ", fieldName='" + fieldName + "'" +
            ", fieldType='" + fieldType + "'" +
            ", serviceID='" + serviceID + "'" +
            ", option='" + option + "'" +
            '}';
    }
}
