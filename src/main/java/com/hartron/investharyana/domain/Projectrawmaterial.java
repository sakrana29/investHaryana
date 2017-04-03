package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
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

    private String rawmaterial;

    private Integer quantity;

    private String units;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    private UUID projectid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Projectrawmaterial createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Projectrawmaterial updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
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
            ", rawmaterial='" + rawmaterial + "'" +
            ", quantity='" + quantity + "'" +
            ", units='" + units + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            ", projectid='" + projectid + "'" +
            '}';
    }
}
