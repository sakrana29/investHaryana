package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectproduct entity.
 */
public class ProjectproductDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private String mainproduct;

    private Integer quantity;

    private UUID units;

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
    public String getMainproduct() {
        return mainproduct;
    }

    public void setMainproduct(String mainproduct) {
        this.mainproduct = mainproduct;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public UUID getUnits() {
        return units;
    }

    public void setUnits(UUID units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectproductDTO projectproductDTO = (ProjectproductDTO) o;

        if ( ! Objects.equals(id, projectproductDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectproductDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", mainproduct='" + mainproduct + "'" +
            ", quantity='" + quantity + "'" +
            ", units='" + units + "'" +
            '}';
    }
}
