package com.hartron.investharyana.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the List of Project.
 */
public class ListofProjectsDTO implements Serializable {

    private Double CAFPin;
    private String InvestorName;
    private String ProjectType;
    private BigDecimal ProjectTotalCost;
    private Integer TotalEmployement;
    private String projectid;

    private Integer MouYear;
    private String SectorName;
    private String BuisnessEntity;

    private ZonedDateTime ApplicationDate;

    private String siteaddress;
    private String district;
    private String block;





    public Double getCAFPin() {
        return CAFPin;
    }

    public void setCAFPin(Double CAFPin) {
        this.CAFPin = CAFPin;
    }

    public String getInvestorName() {
        return InvestorName;
    }

    public void setInvestorName(String investorName) {
        InvestorName = investorName;
    }

    public String getProjectType() {
        return ProjectType;
    }

    public void setProjectType(String projectType) {
        ProjectType = projectType;
    }

    public BigDecimal getProjectTotalCost() {
        return ProjectTotalCost;
    }

    public void setProjectTotalCost(BigDecimal projectTotalCost) {
        ProjectTotalCost = projectTotalCost;
    }

    public Integer getTotalEmployement() {
        return TotalEmployement;
    }

    public void setTotalEmployement(Integer totalEmployement) {
        TotalEmployement = totalEmployement;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public Integer getMouYear() {
        return MouYear;
    }

    public void setMouYear(Integer mouYear) {
        MouYear = mouYear;
    }

    public String getSectorName() {
        return SectorName;
    }

    public void setSectorName(String sectorName) {
        SectorName = sectorName;
    }

    public String getBuisnessEntity() {
        return BuisnessEntity;
    }

    public void setBuisnessEntity(String buisnessEntity) {
        BuisnessEntity = buisnessEntity;
    }

    public ZonedDateTime getApplicationDate() {
        return ApplicationDate;
    }

    public void setApplicationDate(ZonedDateTime applicationDate) {
        ApplicationDate = applicationDate;
    }

    public String getSiteaddress() {
        return siteaddress;
    }

    public void setSiteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
