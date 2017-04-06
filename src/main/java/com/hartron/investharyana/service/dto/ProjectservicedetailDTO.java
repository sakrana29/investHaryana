package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectservicedetail entity.
 */
public class ProjectservicedetailDTO implements Serializable {

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
    public UUID getServiceid() {
        return serviceid;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }
    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }
    public ZonedDateTime getRequireMarkedOnDate() {
        return requireMarkedOnDate;
    }

    public void setRequireMarkedOnDate(ZonedDateTime requireMarkedOnDate) {
        this.requireMarkedOnDate = requireMarkedOnDate;
    }
    public String getRequireMarkedBy() {
        return requireMarkedBy;
    }

    public void setRequireMarkedBy(String requireMarkedBy) {
        this.requireMarkedBy = requireMarkedBy;
    }
    public Boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }
    public ZonedDateTime getAssigOnDate() {
        return assigOnDate;
    }

    public void setAssigOnDate(ZonedDateTime assigOnDate) {
        this.assigOnDate = assigOnDate;
    }
    public String getAssignBy() {
        return assignBy;
    }

    public void setAssignBy(String assignBy) {
        this.assignBy = assignBy;
    }
    public Boolean getFormFilledStatus() {
        return formFilledStatus;
    }

    public void setFormFilledStatus(Boolean formFilledStatus) {
        this.formFilledStatus = formFilledStatus;
    }
    public Boolean getIsPaymentMade() {
        return isPaymentMade;
    }

    public void setIsPaymentMade(Boolean isPaymentMade) {
        this.isPaymentMade = isPaymentMade;
    }
    public Boolean getIsPaymentVerified() {
        return isPaymentVerified;
    }

    public void setIsPaymentVerified(Boolean isPaymentVerified) {
        this.isPaymentVerified = isPaymentVerified;
    }
    public ZonedDateTime getFormFilledOnDate() {
        return formFilledOnDate;
    }

    public void setFormFilledOnDate(ZonedDateTime formFilledOnDate) {
        this.formFilledOnDate = formFilledOnDate;
    }
    public String getFormFilledBy() {
        return formFilledBy;
    }

    public void setFormFilledBy(String formFilledBy) {
        this.formFilledBy = formFilledBy;
    }
    public ZonedDateTime getPaymentMadeOnDate() {
        return paymentMadeOnDate;
    }

    public void setPaymentMadeOnDate(ZonedDateTime paymentMadeOnDate) {
        this.paymentMadeOnDate = paymentMadeOnDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getLatestComments() {
        return latestComments;
    }

    public void setLatestComments(String latestComments) {
        this.latestComments = latestComments;
    }
    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectservicedetailDTO projectservicedetailDTO = (ProjectservicedetailDTO) o;

        if ( ! Objects.equals(id, projectservicedetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectservicedetailDTO{" +
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
            '}';
    }
}
