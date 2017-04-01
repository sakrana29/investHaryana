package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Project_finance_investment.
 */

@Table(name = "project_finance_investment")
public class Project_finance_investment implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private BigDecimal land_cost;

    private BigDecimal building_cost;

    private BigDecimal machinery_cost;

    private BigDecimal misc_assests;

    private BigDecimal total_project_cost;

    private Boolean isfdi;

    private BigDecimal fdivalue;

    private ZonedDateTime project_construction_start_date;

    private ZonedDateTime commercial_activity_start_date;

    private String fdi_country;

    private String foreign_funding_source;

    private Integer totalpurposedemployment;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getLand_cost() {
        return land_cost;
    }

    public Project_finance_investment land_cost(BigDecimal land_cost) {
        this.land_cost = land_cost;
        return this;
    }

    public void setLand_cost(BigDecimal land_cost) {
        this.land_cost = land_cost;
    }

    public BigDecimal getBuilding_cost() {
        return building_cost;
    }

    public Project_finance_investment building_cost(BigDecimal building_cost) {
        this.building_cost = building_cost;
        return this;
    }

    public void setBuilding_cost(BigDecimal building_cost) {
        this.building_cost = building_cost;
    }

    public BigDecimal getMachinery_cost() {
        return machinery_cost;
    }

    public Project_finance_investment machinery_cost(BigDecimal machinery_cost) {
        this.machinery_cost = machinery_cost;
        return this;
    }

    public void setMachinery_cost(BigDecimal machinery_cost) {
        this.machinery_cost = machinery_cost;
    }

    public BigDecimal getMisc_assests() {
        return misc_assests;
    }

    public Project_finance_investment misc_assests(BigDecimal misc_assests) {
        this.misc_assests = misc_assests;
        return this;
    }

    public void setMisc_assests(BigDecimal misc_assests) {
        this.misc_assests = misc_assests;
    }

    public BigDecimal getTotal_project_cost() {
        return total_project_cost;
    }

    public Project_finance_investment total_project_cost(BigDecimal total_project_cost) {
        this.total_project_cost = total_project_cost;
        return this;
    }

    public void setTotal_project_cost(BigDecimal total_project_cost) {
        this.total_project_cost = total_project_cost;
    }

    public Boolean isIsfdi() {
        return isfdi;
    }

    public Project_finance_investment isfdi(Boolean isfdi) {
        this.isfdi = isfdi;
        return this;
    }

    public void setIsfdi(Boolean isfdi) {
        this.isfdi = isfdi;
    }

    public BigDecimal getFdivalue() {
        return fdivalue;
    }

    public Project_finance_investment fdivalue(BigDecimal fdivalue) {
        this.fdivalue = fdivalue;
        return this;
    }

    public void setFdivalue(BigDecimal fdivalue) {
        this.fdivalue = fdivalue;
    }

    public ZonedDateTime getProject_construction_start_date() {
        return project_construction_start_date;
    }

    public Project_finance_investment project_construction_start_date(ZonedDateTime project_construction_start_date) {
        this.project_construction_start_date = project_construction_start_date;
        return this;
    }

    public void setProject_construction_start_date(ZonedDateTime project_construction_start_date) {
        this.project_construction_start_date = project_construction_start_date;
    }

    public ZonedDateTime getCommercial_activity_start_date() {
        return commercial_activity_start_date;
    }

    public Project_finance_investment commercial_activity_start_date(ZonedDateTime commercial_activity_start_date) {
        this.commercial_activity_start_date = commercial_activity_start_date;
        return this;
    }

    public void setCommercial_activity_start_date(ZonedDateTime commercial_activity_start_date) {
        this.commercial_activity_start_date = commercial_activity_start_date;
    }

    public String getFdi_country() {
        return fdi_country;
    }

    public Project_finance_investment fdi_country(String fdi_country) {
        this.fdi_country = fdi_country;
        return this;
    }

    public void setFdi_country(String fdi_country) {
        this.fdi_country = fdi_country;
    }

    public String getForeign_funding_source() {
        return foreign_funding_source;
    }

    public Project_finance_investment foreign_funding_source(String foreign_funding_source) {
        this.foreign_funding_source = foreign_funding_source;
        return this;
    }

    public void setForeign_funding_source(String foreign_funding_source) {
        this.foreign_funding_source = foreign_funding_source;
    }

    public Integer getTotalpurposedemployment() {
        return totalpurposedemployment;
    }

    public Project_finance_investment totalpurposedemployment(Integer totalpurposedemployment) {
        this.totalpurposedemployment = totalpurposedemployment;
        return this;
    }

    public void setTotalpurposedemployment(Integer totalpurposedemployment) {
        this.totalpurposedemployment = totalpurposedemployment;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Project_finance_investment createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Project_finance_investment updatedate(ZonedDateTime updatedate) {
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
        Project_finance_investment project_finance_investment = (Project_finance_investment) o;
        if (project_finance_investment.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, project_finance_investment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project_finance_investment{" +
            "id=" + id +
            ", land_cost='" + land_cost + "'" +
            ", building_cost='" + building_cost + "'" +
            ", machinery_cost='" + machinery_cost + "'" +
            ", misc_assests='" + misc_assests + "'" +
            ", total_project_cost='" + total_project_cost + "'" +
            ", isfdi='" + isfdi + "'" +
            ", fdivalue='" + fdivalue + "'" +
            ", project_construction_start_date='" + project_construction_start_date + "'" +
            ", commercial_activity_start_date='" + commercial_activity_start_date + "'" +
            ", fdi_country='" + fdi_country + "'" +
            ", foreign_funding_source='" + foreign_funding_source + "'" +
            ", totalpurposedemployment='" + totalpurposedemployment + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
