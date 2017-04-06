package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A ProjectServicePaymentDetails.
 */

@Table(name = "projectServicePaymentDetails")
public class ProjectServicePaymentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private UUID serviceid;

    private BigDecimal paymentMade;

    private String paymentMadeBy;

    private ZonedDateTime paymentDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public ProjectServicePaymentDetails projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public UUID getServiceid() {
        return serviceid;
    }

    public ProjectServicePaymentDetails serviceid(UUID serviceid) {
        this.serviceid = serviceid;
        return this;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }

    public BigDecimal getPaymentMade() {
        return paymentMade;
    }

    public ProjectServicePaymentDetails paymentMade(BigDecimal paymentMade) {
        this.paymentMade = paymentMade;
        return this;
    }

    public void setPaymentMade(BigDecimal paymentMade) {
        this.paymentMade = paymentMade;
    }

    public String getPaymentMadeBy() {
        return paymentMadeBy;
    }

    public ProjectServicePaymentDetails paymentMadeBy(String paymentMadeBy) {
        this.paymentMadeBy = paymentMadeBy;
        return this;
    }

    public void setPaymentMadeBy(String paymentMadeBy) {
        this.paymentMadeBy = paymentMadeBy;
    }

    public ZonedDateTime getPaymentDate() {
        return paymentDate;
    }

    public ProjectServicePaymentDetails paymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectServicePaymentDetails projectServicePaymentDetails = (ProjectServicePaymentDetails) o;
        if (projectServicePaymentDetails.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectServicePaymentDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectServicePaymentDetails{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", serviceid='" + serviceid + "'" +
            ", paymentMade='" + paymentMade + "'" +
            ", paymentMadeBy='" + paymentMadeBy + "'" +
            ", paymentDate='" + paymentDate + "'" +
            '}';
    }
}
