package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectproduct.
 */

@Table(name = "projectproduct")
public class Projectproduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private String mainproduct;

    private Integer quantity;

    private UUID units;

    private String projectname;

    private String unitsname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Projectproduct projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getMainproduct() {
        return mainproduct;
    }

    public Projectproduct mainproduct(String mainproduct) {
        this.mainproduct = mainproduct;
        return this;
    }

    public void setMainproduct(String mainproduct) {
        this.mainproduct = mainproduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Projectproduct quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UUID getUnits() {
        return units;
    }

    public Projectproduct units(UUID units) {
        this.units = units;
        return this;
    }

    public void setUnits(UUID units) {
        this.units = units;
    }

    public String getProjectname() {
        return projectname;
    }

    public Projectproduct projectname(String projectname) {
        this.projectname = projectname;
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getUnitsname() {
        return unitsname;
    }

    public Projectproduct unitsname(String unitsname) {
        this.unitsname = unitsname;
        return this;
    }

    public void setUnitsname(String unitsname) {
        this.unitsname = unitsname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectproduct projectproduct = (Projectproduct) o;
        if (projectproduct.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectproduct.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectproduct{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", mainproduct='" + mainproduct + "'" +
            ", quantity='" + quantity + "'" +
            ", units='" + units + "'" +
            ", projectname='" + projectname + "'" +
            ", unitsname='" + unitsname + "'" +
            '}';
    }
}
