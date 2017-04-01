package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Electricrequirement.
 */

@Table(name = "electricrequirement")
public class Electricrequirement implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private Boolean temporaryrequired;

    private Boolean tem_load_existing;

    private String tem_account_number;

    private BigDecimal temp_existing_load_demand_kw;

    private BigDecimal temp_existing_load_demand_kva;

    private BigDecimal temp_new_load_demand_kw;

    private BigDecimal temp_new_load_demand_kva;

    private ZonedDateTime temp_load_demand_date;

    private Boolean regular_load_required;

    private Boolean regular_existing_connection;

    private String regular_account_number;

    private BigDecimal regular_existing_load_ifany_kw;

    private BigDecimal regular_existing_load_ifany_kva;

    private BigDecimal regular_new_load_demand_kw;

    private BigDecimal regular_new_load_demand_kva;

    private ZonedDateTime regular_load_demand_date;

    private String customertype;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean isTemporaryrequired() {
        return temporaryrequired;
    }

    public Electricrequirement temporaryrequired(Boolean temporaryrequired) {
        this.temporaryrequired = temporaryrequired;
        return this;
    }

    public void setTemporaryrequired(Boolean temporaryrequired) {
        this.temporaryrequired = temporaryrequired;
    }

    public Boolean isTem_load_existing() {
        return tem_load_existing;
    }

    public Electricrequirement tem_load_existing(Boolean tem_load_existing) {
        this.tem_load_existing = tem_load_existing;
        return this;
    }

    public void setTem_load_existing(Boolean tem_load_existing) {
        this.tem_load_existing = tem_load_existing;
    }

    public String getTem_account_number() {
        return tem_account_number;
    }

    public Electricrequirement tem_account_number(String tem_account_number) {
        this.tem_account_number = tem_account_number;
        return this;
    }

    public void setTem_account_number(String tem_account_number) {
        this.tem_account_number = tem_account_number;
    }

    public BigDecimal getTemp_existing_load_demand_kw() {
        return temp_existing_load_demand_kw;
    }

    public Electricrequirement temp_existing_load_demand_kw(BigDecimal temp_existing_load_demand_kw) {
        this.temp_existing_load_demand_kw = temp_existing_load_demand_kw;
        return this;
    }

    public void setTemp_existing_load_demand_kw(BigDecimal temp_existing_load_demand_kw) {
        this.temp_existing_load_demand_kw = temp_existing_load_demand_kw;
    }

    public BigDecimal getTemp_existing_load_demand_kva() {
        return temp_existing_load_demand_kva;
    }

    public Electricrequirement temp_existing_load_demand_kva(BigDecimal temp_existing_load_demand_kva) {
        this.temp_existing_load_demand_kva = temp_existing_load_demand_kva;
        return this;
    }

    public void setTemp_existing_load_demand_kva(BigDecimal temp_existing_load_demand_kva) {
        this.temp_existing_load_demand_kva = temp_existing_load_demand_kva;
    }

    public BigDecimal getTemp_new_load_demand_kw() {
        return temp_new_load_demand_kw;
    }

    public Electricrequirement temp_new_load_demand_kw(BigDecimal temp_new_load_demand_kw) {
        this.temp_new_load_demand_kw = temp_new_load_demand_kw;
        return this;
    }

    public void setTemp_new_load_demand_kw(BigDecimal temp_new_load_demand_kw) {
        this.temp_new_load_demand_kw = temp_new_load_demand_kw;
    }

    public BigDecimal getTemp_new_load_demand_kva() {
        return temp_new_load_demand_kva;
    }

    public Electricrequirement temp_new_load_demand_kva(BigDecimal temp_new_load_demand_kva) {
        this.temp_new_load_demand_kva = temp_new_load_demand_kva;
        return this;
    }

    public void setTemp_new_load_demand_kva(BigDecimal temp_new_load_demand_kva) {
        this.temp_new_load_demand_kva = temp_new_load_demand_kva;
    }

    public ZonedDateTime getTemp_load_demand_date() {
        return temp_load_demand_date;
    }

    public Electricrequirement temp_load_demand_date(ZonedDateTime temp_load_demand_date) {
        this.temp_load_demand_date = temp_load_demand_date;
        return this;
    }

    public void setTemp_load_demand_date(ZonedDateTime temp_load_demand_date) {
        this.temp_load_demand_date = temp_load_demand_date;
    }

    public Boolean isRegular_load_required() {
        return regular_load_required;
    }

    public Electricrequirement regular_load_required(Boolean regular_load_required) {
        this.regular_load_required = regular_load_required;
        return this;
    }

    public void setRegular_load_required(Boolean regular_load_required) {
        this.regular_load_required = regular_load_required;
    }

    public Boolean isRegular_existing_connection() {
        return regular_existing_connection;
    }

    public Electricrequirement regular_existing_connection(Boolean regular_existing_connection) {
        this.regular_existing_connection = regular_existing_connection;
        return this;
    }

    public void setRegular_existing_connection(Boolean regular_existing_connection) {
        this.regular_existing_connection = regular_existing_connection;
    }

    public String getRegular_account_number() {
        return regular_account_number;
    }

    public Electricrequirement regular_account_number(String regular_account_number) {
        this.regular_account_number = regular_account_number;
        return this;
    }

    public void setRegular_account_number(String regular_account_number) {
        this.regular_account_number = regular_account_number;
    }

    public BigDecimal getRegular_existing_load_ifany_kw() {
        return regular_existing_load_ifany_kw;
    }

    public Electricrequirement regular_existing_load_ifany_kw(BigDecimal regular_existing_load_ifany_kw) {
        this.regular_existing_load_ifany_kw = regular_existing_load_ifany_kw;
        return this;
    }

    public void setRegular_existing_load_ifany_kw(BigDecimal regular_existing_load_ifany_kw) {
        this.regular_existing_load_ifany_kw = regular_existing_load_ifany_kw;
    }

    public BigDecimal getRegular_existing_load_ifany_kva() {
        return regular_existing_load_ifany_kva;
    }

    public Electricrequirement regular_existing_load_ifany_kva(BigDecimal regular_existing_load_ifany_kva) {
        this.regular_existing_load_ifany_kva = regular_existing_load_ifany_kva;
        return this;
    }

    public void setRegular_existing_load_ifany_kva(BigDecimal regular_existing_load_ifany_kva) {
        this.regular_existing_load_ifany_kva = regular_existing_load_ifany_kva;
    }

    public BigDecimal getRegular_new_load_demand_kw() {
        return regular_new_load_demand_kw;
    }

    public Electricrequirement regular_new_load_demand_kw(BigDecimal regular_new_load_demand_kw) {
        this.regular_new_load_demand_kw = regular_new_load_demand_kw;
        return this;
    }

    public void setRegular_new_load_demand_kw(BigDecimal regular_new_load_demand_kw) {
        this.regular_new_load_demand_kw = regular_new_load_demand_kw;
    }

    public BigDecimal getRegular_new_load_demand_kva() {
        return regular_new_load_demand_kva;
    }

    public Electricrequirement regular_new_load_demand_kva(BigDecimal regular_new_load_demand_kva) {
        this.regular_new_load_demand_kva = regular_new_load_demand_kva;
        return this;
    }

    public void setRegular_new_load_demand_kva(BigDecimal regular_new_load_demand_kva) {
        this.regular_new_load_demand_kva = regular_new_load_demand_kva;
    }

    public ZonedDateTime getRegular_load_demand_date() {
        return regular_load_demand_date;
    }

    public Electricrequirement regular_load_demand_date(ZonedDateTime regular_load_demand_date) {
        this.regular_load_demand_date = regular_load_demand_date;
        return this;
    }

    public void setRegular_load_demand_date(ZonedDateTime regular_load_demand_date) {
        this.regular_load_demand_date = regular_load_demand_date;
    }

    public String getCustomertype() {
        return customertype;
    }

    public Electricrequirement customertype(String customertype) {
        this.customertype = customertype;
        return this;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Electricrequirement createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Electricrequirement updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Electricrequirement electricrequirement = (Electricrequirement) o;
        if (electricrequirement.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, electricrequirement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Electricrequirement{" +
            "id=" + id +
            ", temporaryrequired='" + temporaryrequired + "'" +
            ", tem_load_existing='" + tem_load_existing + "'" +
            ", tem_account_number='" + tem_account_number + "'" +
            ", temp_existing_load_demand_kw='" + temp_existing_load_demand_kw + "'" +
            ", temp_existing_load_demand_kva='" + temp_existing_load_demand_kva + "'" +
            ", temp_new_load_demand_kw='" + temp_new_load_demand_kw + "'" +
            ", temp_new_load_demand_kva='" + temp_new_load_demand_kva + "'" +
            ", temp_load_demand_date='" + temp_load_demand_date + "'" +
            ", regular_load_required='" + regular_load_required + "'" +
            ", regular_existing_connection='" + regular_existing_connection + "'" +
            ", regular_account_number='" + regular_account_number + "'" +
            ", regular_existing_load_ifany_kw='" + regular_existing_load_ifany_kw + "'" +
            ", regular_existing_load_ifany_kva='" + regular_existing_load_ifany_kva + "'" +
            ", regular_new_load_demand_kw='" + regular_new_load_demand_kw + "'" +
            ", regular_new_load_demand_kva='" + regular_new_load_demand_kva + "'" +
            ", regular_load_demand_date='" + regular_load_demand_date + "'" +
            ", customertype='" + customertype + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
