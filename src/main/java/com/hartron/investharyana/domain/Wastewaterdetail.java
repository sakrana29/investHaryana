package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
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

    private UUID projectid;

    private String source_of_generation;

    private UUID naturetype;

    private Integer quantity;

    private UUID mode_of_disposal;

    private String description;

    private String projectname;

    private String naturetypename;

    private String modeofdisposal;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Wastewaterdetail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
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

    public UUID getNaturetype() {
        return naturetype;
    }

    public Wastewaterdetail naturetype(UUID naturetype) {
        this.naturetype = naturetype;
        return this;
    }

    public void setNaturetype(UUID naturetype) {
        this.naturetype = naturetype;
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

    public UUID getMode_of_disposal() {
        return mode_of_disposal;
    }

    public Wastewaterdetail mode_of_disposal(UUID mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
        return this;
    }

    public void setMode_of_disposal(UUID mode_of_disposal) {
        this.mode_of_disposal = mode_of_disposal;
    }

    public String getDescription() {
        return description;
    }

    public Wastewaterdetail description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectname() {
        return projectname;
    }

    public Wastewaterdetail projectname(String projectname) {
        this.projectname = projectname;
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getNaturetypename() {
        return naturetypename;
    }

    public Wastewaterdetail naturetypename(String naturetypename) {
        this.naturetypename = naturetypename;
        return this;
    }

    public void setNaturetypename(String naturetypename) {
        this.naturetypename = naturetypename;
    }

    public String getModeofdisposal() {
        return modeofdisposal;
    }

    public Wastewaterdetail modeofdisposal(String modeofdisposal) {
        this.modeofdisposal = modeofdisposal;
        return this;
    }

    public void setModeofdisposal(String modeofdisposal) {
        this.modeofdisposal = modeofdisposal;
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
            ", projectid='" + projectid + "'" +
            ", source_of_generation='" + source_of_generation + "'" +
            ", naturetype='" + naturetype + "'" +
            ", quantity='" + quantity + "'" +
            ", mode_of_disposal='" + mode_of_disposal + "'" +
            ", description='" + description + "'" +
            ", projectname='" + projectname + "'" +
            ", naturetypename='" + naturetypename + "'" +
            ", modeofdisposal='" + modeofdisposal + "'" +
            '}';
    }
}
