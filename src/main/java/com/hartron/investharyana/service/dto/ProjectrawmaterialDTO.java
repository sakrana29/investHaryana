package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectrawmaterial entity.
 */
public class ProjectrawmaterialDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private String rawmaterial;

    private Integer quantity;

    private String units;

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
    public String getRawmaterial() {
        return rawmaterial;
    }

    public void setRawmaterial(String rawmaterial) {
        this.rawmaterial = rawmaterial;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
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

        ProjectrawmaterialDTO projectrawmaterialDTO = (ProjectrawmaterialDTO) o;

        if ( ! Objects.equals(id, projectrawmaterialDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectrawmaterialDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", rawmaterial='" + rawmaterial + "'" +
            ", quantity='" + quantity + "'" +
            ", units='" + units + "'" +
            '}';
    }
}
