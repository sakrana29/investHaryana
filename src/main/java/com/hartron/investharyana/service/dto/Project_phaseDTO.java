package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Project_phase entity.
 */
public class Project_phaseDTO implements Serializable {

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

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }
    public String getFci() {
        return fci;
    }

    public void setFci(String fci) {
        this.fci = fci;
    }
    public ZonedDateTime getImplementationdate() {
        return implementationdate;
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

        Project_phaseDTO project_phaseDTO = (Project_phaseDTO) o;

        if ( ! Objects.equals(id, project_phaseDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project_phaseDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", phase='" + phase + "'" +
            ", productcategory='" + productcategory + "'" +
            ", fci='" + fci + "'" +
            ", implementationdate='" + implementationdate + "'" +
            '}';
    }
}
