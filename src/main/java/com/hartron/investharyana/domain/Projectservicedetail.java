package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectservicedetail.
 */

@Table(name = "projectservicedetail")
public class Projectservicedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private UUID serviceid;

    private Boolean isRequired;

    private ZonedDateTime requireMarkedOnDate;

    private String requireMarkedBy;

    private Boolean isAssigned;

    private ZonedDateTime assigOnDate;

    private String assignBy;

    private Boolean formFilledStatus;

    private Boolean isPaymentMade;

    private Boolean isPaymentVerified;

    private ZonedDateTime formFilledOnDate;

    private String formFilledBy;

    private ZonedDateTime paymentMadeOnDate;

    private String status;

    private String latestComments;

    private BigDecimal serviceFee;

    private String departmentName;

    private String serviceName;

    private String serviceStage;

    private Boolean isDimmed;

    private Integer serviceDuration;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Projectservicedetail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public UUID getServiceid() {
        return serviceid;
    }

    public Projectservicedetail serviceid(UUID serviceid) {
        this.serviceid = serviceid;
        return this;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }

    public Boolean isIsRequired() {
        return isRequired;
    }

    public Projectservicedetail isRequired(Boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public ZonedDateTime getRequireMarkedOnDate() {
        return requireMarkedOnDate;
    }

    public Projectservicedetail requireMarkedOnDate(ZonedDateTime requireMarkedOnDate) {
        this.requireMarkedOnDate = requireMarkedOnDate;
        return this;
    }

    public void setRequireMarkedOnDate(ZonedDateTime requireMarkedOnDate) {
        this.requireMarkedOnDate = requireMarkedOnDate;
    }

    public String getRequireMarkedBy() {
        return requireMarkedBy;
    }

    public Projectservicedetail requireMarkedBy(String requireMarkedBy) {
        this.requireMarkedBy = requireMarkedBy;
        return this;
    }

    public void setRequireMarkedBy(String requireMarkedBy) {
        this.requireMarkedBy = requireMarkedBy;
    }

    public Boolean isIsAssigned() {
        return isAssigned;
    }

    public Projectservicedetail isAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
        return this;
    }

    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public ZonedDateTime getAssigOnDate() {
        return assigOnDate;
    }

    public Projectservicedetail assigOnDate(ZonedDateTime assigOnDate) {
        this.assigOnDate = assigOnDate;
        return this;
    }

    public void setAssigOnDate(ZonedDateTime assigOnDate) {
        this.assigOnDate = assigOnDate;
    }

    public String getAssignBy() {
        return assignBy;
    }

    public Projectservicedetail assignBy(String assignBy) {
        this.assignBy = assignBy;
        return this;
    }

    public void setAssignBy(String assignBy) {
        this.assignBy = assignBy;
    }

    public Boolean isFormFilledStatus() {
        return formFilledStatus;
    }

    public Projectservicedetail formFilledStatus(Boolean formFilledStatus) {
        this.formFilledStatus = formFilledStatus;
        return this;
    }

    public void setFormFilledStatus(Boolean formFilledStatus) {
        this.formFilledStatus = formFilledStatus;
    }

    public Boolean isIsPaymentMade() {
        return isPaymentMade;
    }

    public Projectservicedetail isPaymentMade(Boolean isPaymentMade) {
        this.isPaymentMade = isPaymentMade;
        return this;
    }

    public void setIsPaymentMade(Boolean isPaymentMade) {
        this.isPaymentMade = isPaymentMade;
    }

    public Boolean isIsPaymentVerified() {
        return isPaymentVerified;
    }

    public Projectservicedetail isPaymentVerified(Boolean isPaymentVerified) {
        this.isPaymentVerified = isPaymentVerified;
        return this;
    }

    public void setIsPaymentVerified(Boolean isPaymentVerified) {
        this.isPaymentVerified = isPaymentVerified;
    }

    public ZonedDateTime getFormFilledOnDate() {
        return formFilledOnDate;
    }

    public Projectservicedetail formFilledOnDate(ZonedDateTime formFilledOnDate) {
        this.formFilledOnDate = formFilledOnDate;
        return this;
    }

    public void setFormFilledOnDate(ZonedDateTime formFilledOnDate) {
        this.formFilledOnDate = formFilledOnDate;
    }

    public String getFormFilledBy() {
        return formFilledBy;
    }

    public Projectservicedetail formFilledBy(String formFilledBy) {
        this.formFilledBy = formFilledBy;
        return this;
    }

    public void setFormFilledBy(String formFilledBy) {
        this.formFilledBy = formFilledBy;
    }

    public ZonedDateTime getPaymentMadeOnDate() {
        return paymentMadeOnDate;
    }

    public Projectservicedetail paymentMadeOnDate(ZonedDateTime paymentMadeOnDate) {
        this.paymentMadeOnDate = paymentMadeOnDate;
        return this;
    }

    public void setPaymentMadeOnDate(ZonedDateTime paymentMadeOnDate) {
        this.paymentMadeOnDate = paymentMadeOnDate;
    }

    public String getStatus() {
        return status;
    }

    public Projectservicedetail status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatestComments() {
        return latestComments;
    }

    public Projectservicedetail latestComments(String latestComments) {
        this.latestComments = latestComments;
        return this;
    }

    public void setLatestComments(String latestComments) {
        this.latestComments = latestComments;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public Projectservicedetail serviceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Projectservicedetail departmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Projectservicedetail serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceStage() {
        return serviceStage;
    }

    public Projectservicedetail serviceStage(String serviceStage) {
        this.serviceStage = serviceStage;
        return this;
    }

    public void setServiceStage(String serviceStage) {
        this.serviceStage = serviceStage;
    }

    public Boolean isIsDimmed() {
        return isDimmed;
    }

    public Projectservicedetail isDimmed(Boolean isDimmed) {
        this.isDimmed = isDimmed;
        return this;
    }

    public void setIsDimmed(Boolean isDimmed) {
        this.isDimmed = isDimmed;
    }

    public Integer getServiceDuration() {
        return serviceDuration;
    }

    public Projectservicedetail serviceDuration(Integer serviceDuration) {
        this.serviceDuration = serviceDuration;
        return this;
    }

    public void setServiceDuration(Integer serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectservicedetail projectservicedetail = (Projectservicedetail) o;
        if (projectservicedetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectservicedetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectservicedetail{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", serviceid='" + serviceid + "'" +
            ", isRequired='" + isRequired + "'" +
            ", requireMarkedOnDate='" + requireMarkedOnDate + "'" +
            ", requireMarkedBy='" + requireMarkedBy + "'" +
            ", isAssigned='" + isAssigned + "'" +
            ", assigOnDate='" + assigOnDate + "'" +
            ", assignBy='" + assignBy + "'" +
            ", formFilledStatus='" + formFilledStatus + "'" +
            ", isPaymentMade='" + isPaymentMade + "'" +
            ", isPaymentVerified='" + isPaymentVerified + "'" +
            ", formFilledOnDate='" + formFilledOnDate + "'" +
            ", formFilledBy='" + formFilledBy + "'" +
            ", paymentMadeOnDate='" + paymentMadeOnDate + "'" +
            ", status='" + status + "'" +
            ", latestComments='" + latestComments + "'" +
            ", serviceFee='" + serviceFee + "'" +
            ", departmentName='" + departmentName + "'" +
            ", serviceName='" + serviceName + "'" +
            ", serviceStage='" + serviceStage + "'" +
            ", isDimmed='" + isDimmed + "'" +
            ", serviceDuration='" + serviceDuration + "'" +
            '}';
    }
}
