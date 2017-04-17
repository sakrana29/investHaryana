package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the ProjectServicePaymentDetails entity.
 */
public class ProjectServicePaymentDetailsDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private UUID serviceid;

    private BigDecimal paymentMade;

    private String paymentMadeBy;

    private ZonedDateTime paymentDate;

    private String transactionId;

    private String paymentResponse;

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
    public BigDecimal getPaymentMade() {
        return paymentMade;
    }

    public void setPaymentMade(BigDecimal paymentMade) {
        this.paymentMade = paymentMade;
    }
    public String getPaymentMadeBy() {
        return paymentMadeBy;
    }

    public void setPaymentMadeBy(String paymentMadeBy) {
        this.paymentMadeBy = paymentMadeBy;
    }
    public ZonedDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getPaymentResponse() {
        return paymentResponse;
    }

    public void setPaymentResponse(String paymentResponse) {
        this.paymentResponse = paymentResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO = (ProjectServicePaymentDetailsDTO) o;

        if ( ! Objects.equals(id, projectServicePaymentDetailsDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectServicePaymentDetailsDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", serviceid='" + serviceid + "'" +
            ", paymentMade='" + paymentMade + "'" +
            ", paymentMadeBy='" + paymentMadeBy + "'" +
            ", paymentDate='" + paymentDate + "'" +
            ", transactionId='" + transactionId + "'" +
            ", paymentResponse='" + paymentResponse + "'" +
            '}';
    }
}
