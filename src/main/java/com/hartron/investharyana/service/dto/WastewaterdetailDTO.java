package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Wastewaterdetail entity.
 */
public class WastewaterdetailDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private String source_of_generation;

    private UUID naturetype;

    private Integer quantity;

    private UUID mode_of_disposal;

    private String description;

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
    public String getSource_of_generation() {
        return source_of_generation;
    }

    public void setSource_of_generation(String source_of_generation) {
        this.source_of_generation = source_of_generation;
    }
    public UUID getNaturetype() {
        return naturetype;
    }

    public void setNaturetype(UUID naturetype) {
        this.naturetype = naturetype;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public UUID getMode_of_disposal() {
        return mode_of_disposal;
    }

    public void setMode_of_disposal(UUID mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WastewaterdetailDTO wastewaterdetailDTO = (WastewaterdetailDTO) o;

        if ( ! Objects.equals(id, wastewaterdetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WastewaterdetailDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", source_of_generation='" + source_of_generation + "'" +
            ", naturetype='" + naturetype + "'" +
            ", quantity='" + quantity + "'" +
            ", mode_of_disposal='" + mode_of_disposal + "'" +
            ", description='" + description + "'" +
            '}';
    }
}