package com.hartron.investharyana.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
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
}
