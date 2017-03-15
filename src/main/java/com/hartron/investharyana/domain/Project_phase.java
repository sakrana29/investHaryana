package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Project_phase.
 */

@Table(name = "project_phase")
public class Project_phase implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private String phase;

    private String productcategory;

    private String fci;

    private ZonedDateTime implementationdate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Project_phase projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getPhase() {
        return phase;
    }

    public Project_phase phase(String phase) {
        this.phase = phase;
        return this;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public Project_phase productcategory(String productcategory) {
        this.productcategory = productcategory;
        return this;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public String getFci() {
        return fci;
    }

    public Project_phase fci(String fci) {
        this.fci = fci;
        return this;
    }

    public void setFci(String fci) {
        this.fci = fci;
    }

    public ZonedDateTime getImplementationdate() {
        return implementationdate;
    }

    public Project_phase implementationdate(ZonedDateTime implementationdate) {
        this.implementationdate = implementationdate;
        return this;
    }

    public void setImplementationdate(ZonedDateTime implementationdate) {
        this.implementationdate = implementationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project_phase project_phase = (Project_phase) o;
        if (project_phase.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, project_phase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project_phase{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", phase='" + phase + "'" +
            ", productcategory='" + productcategory + "'" +
            ", fci='" + fci + "'" +
            ", implementationdate='" + implementationdate + "'" +
            '}';
    }
}
