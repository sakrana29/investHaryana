package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Wastewaterdetail.
 */

@Table(name = "wastewaterdetail")
public class Wastewaterdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String source_of_generation;

    private Integer quantity;

    private String naturetype;

    private String mode_of_disposal;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSource_of_generation() {
        return source_of_generation;
    }

    public Wastewaterdetail source_of_generation(String source_of_generation) {
        this.source_of_generation = source_of_generation;
        return this;
    }

    public void setSource_of_generation(String source_of_generation) {
        this.source_of_generation = source_of_generation;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Wastewaterdetail quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNaturetype() {
        return naturetype;
    }

    public Wastewaterdetail naturetype(String naturetype) {
        this.naturetype = naturetype;
        return this;
    }

    public void setNaturetype(String naturetype) {
        this.naturetype = naturetype;
    }

    public String getMode_of_disposal() {
        return mode_of_disposal;
    }

    public Wastewaterdetail mode_of_disposal(String mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
        return this;
    }

    public void setMode_of_disposal(String mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Wastewaterdetail createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Wastewaterdetail updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wastewaterdetail wastewaterdetail = (Wastewaterdetail) o;
        if (wastewaterdetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, wastewaterdetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Wastewaterdetail{" +
            "id=" + id +
            ", source_of_generation='" + source_of_generation + "'" +
            ", quantity='" + quantity + "'" +
            ", naturetype='" + naturetype + "'" +
            ", mode_of_disposal='" + mode_of_disposal + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
