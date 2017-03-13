package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Electricrequirement entity.
 */
public class ElectricrequirementDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private ByteBuffer temporaryconnection;
    private String temporaryconnectionContentType;

    private Boolean temporaryrequired;

    private Boolean tem_load_existing;

    private String tem_account_number;

    private BigDecimal temp_existing_load_demand_kw;

    private BigDecimal temp_existing_load_demand_kva;

    private BigDecimal temp_new_load_demand_kw;

    private BigDecimal temp_new_load_demand_kva;

    private ZonedDateTime temp_load_demand_date;

    private ByteBuffer regular_connection_doc;
    private String regular_connection_docContentType;

    private Boolean regular_load_required;

    private Boolean regular_existing_connection;

    private UUID customertype;

    private String regular_account_number;

    private BigDecimal regular_existing_load_ifany_kw;

    private BigDecimal regular_existing_load_ifany_kva;

    private BigDecimal regular_new_load_demand_kw;

    private BigDecimal regular_new_load_demand_kva;

    private ZonedDateTime regular_load_demand_date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getProjectid() {
        return projectid;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public ByteBuffer getTemporaryconnection() {
        return temporaryconnection;
    }

    public void setTemporaryconnection(ByteBuffer temporaryconnection) {
        this.temporaryconnection = temporaryconnection;
    }

    public String getTemporaryconnectionContentType() {
        return temporaryconnectionContentType;
    }

    public void setTemporaryconnectionContentType(String temporaryconnectionContentType) {
        this.temporaryconnectionContentType = temporaryconnectionContentType;
    }
    public Boolean getTemporaryrequired() {
        return temporaryrequired;
    }

    public void setTemporaryrequired(Boolean temporaryrequired) {
        this.temporaryrequired = temporaryrequired;
    }
    public Boolean getTem_load_existing() {
        return tem_load_existing;
    }

    public void setTem_load_existing(Boolean tem_load_existing) {
        this.tem_load_existing = tem_load_existing;
    }
    public String getTem_account_number() {
        return tem_account_number;
    }

    public void setTem_account_number(String tem_account_number) {
        this.tem_account_number = tem_account_number;
    }
    public BigDecimal getTemp_existing_load_demand_kw() {
        return temp_existing_load_demand_kw;
    }

    public void setTemp_existing_load_demand_kw(BigDecimal temp_existing_load_demand_kw) {
        this.temp_existing_load_demand_kw = temp_existing_load_demand_kw;
    }
    public BigDecimal getTemp_existing_load_demand_kva() {
        return temp_existing_load_demand_kva;
    }

    public void setTemp_existing_load_demand_kva(BigDecimal temp_existing_load_demand_kva) {
        this.temp_existing_load_demand_kva = temp_existing_load_demand_kva;
    }
    public BigDecimal getTemp_new_load_demand_kw() {
        return temp_new_load_demand_kw;
    }

    public void setTemp_new_load_demand_kw(BigDecimal temp_new_load_demand_kw) {
        this.temp_new_load_demand_kw = temp_new_load_demand_kw;
    }
    public BigDecimal getTemp_new_load_demand_kva() {
        return temp_new_load_demand_kva;
    }

    public void setTemp_new_load_demand_kva(BigDecimal temp_new_load_demand_kva) {
        this.temp_new_load_demand_kva = temp_new_load_demand_kva;
    }
    public ZonedDateTime getTemp_load_demand_date() {
        return temp_load_demand_date;
    }

    public void setTemp_load_demand_date(ZonedDateTime temp_load_demand_date) {
        this.temp_load_demand_date = temp_load_demand_date;
    }
    public ByteBuffer getRegular_connection_doc() {
        return regular_connection_doc;
    }

    public void setRegular_connection_doc(ByteBuffer regular_connection_doc) {
        this.regular_connection_doc = regular_connection_doc;
    }

    public String getRegular_connection_docContentType() {
        return regular_connection_docContentType;
    }

    public void setRegular_connection_docContentType(String regular_connection_docContentType) {
        this.regular_connection_docContentType = regular_connection_docContentType;
    }
    public Boolean getRegular_load_required() {
        return regular_load_required;
    }

    public void setRegular_load_required(Boolean regular_load_required) {
        this.regular_load_required = regular_load_required;
    }
    public Boolean getRegular_existing_connection() {
        return regular_existing_connection;
    }

    public void setRegular_existing_connection(Boolean regular_existing_connection) {
        this.regular_existing_connection = regular_existing_connection;
    }
    public UUID getCustomertype() {
        return customertype;
    }

    public void setCustomertype(UUID customertype) {
        this.customertype = customertype;
    }
    public String getRegular_account_number() {
        return regular_account_number;
    }

    public void setRegular_account_number(String regular_account_number) {
        this.regular_account_number = regular_account_number;
    }
    public BigDecimal getRegular_existing_load_ifany_kw() {
        return regular_existing_load_ifany_kw;
    }

    public void setRegular_existing_load_ifany_kw(BigDecimal regular_existing_load_ifany_kw) {
        this.regular_existing_load_ifany_kw = regular_existing_load_ifany_kw;
    }
    public BigDecimal getRegular_existing_load_ifany_kva() {
        return regular_existing_load_ifany_kva;
    }

    public void setRegular_existing_load_ifany_kva(BigDecimal regular_existing_load_ifany_kva) {
        this.regular_existing_load_ifany_kva = regular_existing_load_ifany_kva;
    }
    public BigDecimal getRegular_new_load_demand_kw() {
        return regular_new_load_demand_kw;
    }

    public void setRegular_new_load_demand_kw(BigDecimal regular_new_load_demand_kw) {
        this.regular_new_load_demand_kw = regular_new_load_demand_kw;
    }
    public BigDecimal getRegular_new_load_demand_kva() {
        return regular_new_load_demand_kva;
    }

    public void setRegular_new_load_demand_kva(BigDecimal regular_new_load_demand_kva) {
        this.regular_new_load_demand_kva = regular_new_load_demand_kva;
    }
    public ZonedDateTime getRegular_load_demand_date() {
        return regular_load_demand_date;
    }

    public void setRegular_load_demand_date(ZonedDateTime regular_load_demand_date) {
        this.regular_load_demand_date = regular_load_demand_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ElectricrequirementDTO electricrequirementDTO = (ElectricrequirementDTO) o;

        if ( ! Objects.equals(id, electricrequirementDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ElectricrequirementDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", temporaryconnection='" + temporaryconnection + "'" +
            ", temporaryrequired='" + temporaryrequired + "'" +
            ", tem_load_existing='" + tem_load_existing + "'" +
            ", tem_account_number='" + tem_account_number + "'" +
            ", temp_existing_load_demand_kw='" + temp_existing_load_demand_kw + "'" +
            ", temp_existing_load_demand_kva='" + temp_existing_load_demand_kva + "'" +
            ", temp_new_load_demand_kw='" + temp_new_load_demand_kw + "'" +
            ", temp_new_load_demand_kva='" + temp_new_load_demand_kva + "'" +
            ", temp_load_demand_date='" + temp_load_demand_date + "'" +
            ", regular_connection_doc='" + regular_connection_doc + "'" +
            ", regular_load_required='" + regular_load_required + "'" +
            ", regular_existing_connection='" + regular_existing_connection + "'" +
            ", customertype='" + customertype + "'" +
            ", regular_account_number='" + regular_account_number + "'" +
            ", regular_existing_load_ifany_kw='" + regular_existing_load_ifany_kw + "'" +
            ", regular_existing_load_ifany_kva='" + regular_existing_load_ifany_kva + "'" +
            ", regular_new_load_demand_kw='" + regular_new_load_demand_kw + "'" +
            ", regular_new_load_demand_kva='" + regular_new_load_demand_kva + "'" +
            ", regular_load_demand_date='" + regular_load_demand_date + "'" +
            '}';
    }
}
