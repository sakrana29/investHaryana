package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Project_finance_investment entity.
 */
public class Project_finance_investmentDTO implements Serializable {

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

    public void setLand_cost(BigDecimal land_cost) {
        this.land_cost = land_cost;
    }
    public BigDecimal getBuilding_cost() {
        return building_cost;
    }

    public void setBuilding_cost(BigDecimal building_cost) {
        this.building_cost = building_cost;
    }
    public BigDecimal getMachinery_cost() {
        return machinery_cost;
    }

    public void setMachinery_cost(BigDecimal machinery_cost) {
        this.machinery_cost = machinery_cost;
    }
    public BigDecimal getMisc_assests() {
        return misc_assests;
    }

    public void setMisc_assests(BigDecimal misc_assests) {
        this.misc_assests = misc_assests;
    }
    public BigDecimal getTotal_project_cost() {
        return total_project_cost;
    }

    public void setTotal_project_cost(BigDecimal total_project_cost) {
        this.total_project_cost = total_project_cost;
    }
    public Boolean getIsfdi() {
        return isfdi;
    }

    public void setIsfdi(Boolean isfdi) {
        this.isfdi = isfdi;
    }
    public BigDecimal getFdivalue() {
        return fdivalue;
    }

    public void setFdivalue(BigDecimal fdivalue) {
        this.fdivalue = fdivalue;
    }
    public ZonedDateTime getProject_construction_start_date() {
        return project_construction_start_date;
    }

    public void setProject_construction_start_date(ZonedDateTime project_construction_start_date) {
        this.project_construction_start_date = project_construction_start_date;
    }
    public ZonedDateTime getCommercial_activity_start_date() {
        return commercial_activity_start_date;
    }

    public void setCommercial_activity_start_date(ZonedDateTime commercial_activity_start_date) {
        this.commercial_activity_start_date = commercial_activity_start_date;
    }
    public String getFdi_country() {
        return fdi_country;
    }

    public void setFdi_country(String fdi_country) {
        this.fdi_country = fdi_country;
    }
    public String getForeign_funding_source() {
        return foreign_funding_source;
    }

    public void setForeign_funding_source(String foreign_funding_source) {
        this.foreign_funding_source = foreign_funding_source;
    }
    public Integer getTotalpurposedemployment() {
        return totalpurposedemployment;
    }

    public void setTotalpurposedemployment(Integer totalpurposedemployment) {
        this.totalpurposedemployment = totalpurposedemployment;
    }
    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }
    public ZonedDateTime getUpdatedate() {
        return updatedate;
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

        Project_finance_investmentDTO project_finance_investmentDTO = (Project_finance_investmentDTO) o;

        if ( ! Objects.equals(id, project_finance_investmentDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project_finance_investmentDTO{" +
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
