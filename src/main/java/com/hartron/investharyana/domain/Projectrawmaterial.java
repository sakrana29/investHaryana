package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectrawmaterial.
 */

@Table(name = "projectrawmaterial")
public class Projectrawmaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Projectrawmaterial projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getRawmaterial() {
        return rawmaterial;
    }

    public Projectrawmaterial rawmaterial(String rawmaterial) {
        this.rawmaterial = rawmaterial;
        return this;
    }

    public void setRawmaterial(String rawmaterial) {
        this.rawmaterial = rawmaterial;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Projectrawmaterial quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public Projectrawmaterial units(String units) {
        this.units = units;
        return this;
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
        Projectrawmaterial projectrawmaterial = (Projectrawmaterial) o;
        if (projectrawmaterial.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectrawmaterial.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectrawmaterial{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", rawmaterial='" + rawmaterial + "'" +
            ", quantity='" + quantity + "'" +
            ", units='" + units + "'" +
            '}';
    }
}
