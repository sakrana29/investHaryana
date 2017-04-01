package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Environment_impactdetail.
 */

@Table(name = "environment_impactdetail")
public class Environment_impactdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private Integer water_process;

    private Integer water_cooling;

    private Integer water_domestic;

    private Integer water_other;

    private Integer waste_water_process;

    private Integer waste_water_cooling;

    private Integer waste_water_domesting;

    private Integer waste_water_other;

    private String source_of_water_supply;

    private String mode_of_disposal_for_discharge;

    private String recycling_process;

    private String recycling_cooling;

    private String recycling_domestic;

    private String recycling_other;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    private String sourcewatersupplyother;

    private String modedisposalother;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getWater_process() {
        return water_process;
    }

    public Environment_impactdetail water_process(Integer water_process) {
        this.water_process = water_process;
        return this;
    }

    public void setWater_process(Integer water_process) {
        this.water_process = water_process;
    }

    public Integer getWater_cooling() {
        return water_cooling;
    }

    public Environment_impactdetail water_cooling(Integer water_cooling) {
        this.water_cooling = water_cooling;
        return this;
    }

    public void setWater_cooling(Integer water_cooling) {
        this.water_cooling = water_cooling;
    }

    public Integer getWater_domestic() {
        return water_domestic;
    }

    public Environment_impactdetail water_domestic(Integer water_domestic) {
        this.water_domestic = water_domestic;
        return this;
    }

    public void setWater_domestic(Integer water_domestic) {
        this.water_domestic = water_domestic;
    }

    public Integer getWater_other() {
        return water_other;
    }

    public Environment_impactdetail water_other(Integer water_other) {
        this.water_other = water_other;
        return this;
    }

    public void setWater_other(Integer water_other) {
        this.water_other = water_other;
    }

    public Integer getWaste_water_process() {
        return waste_water_process;
    }

    public Environment_impactdetail waste_water_process(Integer waste_water_process) {
        this.waste_water_process = waste_water_process;
        return this;
    }

    public void setWaste_water_process(Integer waste_water_process) {
        this.waste_water_process = waste_water_process;
    }

    public Integer getWaste_water_cooling() {
        return waste_water_cooling;
    }

    public Environment_impactdetail waste_water_cooling(Integer waste_water_cooling) {
        this.waste_water_cooling = waste_water_cooling;
        return this;
    }

    public void setWaste_water_cooling(Integer waste_water_cooling) {
        this.waste_water_cooling = waste_water_cooling;
    }

    public Integer getWaste_water_domesting() {
        return waste_water_domesting;
    }

    public Environment_impactdetail waste_water_domesting(Integer waste_water_domesting) {
        this.waste_water_domesting = waste_water_domesting;
        return this;
    }

    public void setWaste_water_domesting(Integer waste_water_domesting) {
        this.waste_water_domesting = waste_water_domesting;
    }

    public Integer getWaste_water_other() {
        return waste_water_other;
    }

    public Environment_impactdetail waste_water_other(Integer waste_water_other) {
        this.waste_water_other = waste_water_other;
        return this;
    }

    public void setWaste_water_other(Integer waste_water_other) {
        this.waste_water_other = waste_water_other;
    }

    public String getSource_of_water_supply() {
        return source_of_water_supply;
    }

    public Environment_impactdetail source_of_water_supply(String source_of_water_supply) {
        this.source_of_water_supply = source_of_water_supply;
        return this;
    }

    public void setSource_of_water_supply(String source_of_water_supply) {
        this.source_of_water_supply = source_of_water_supply;
    }

    public String getMode_of_disposal_for_discharge() {
        return mode_of_disposal_for_discharge;
    }

    public Environment_impactdetail mode_of_disposal_for_discharge(String mode_of_disposal_for_discharge) {
        this.mode_of_disposal_for_discharge = mode_of_disposal_for_discharge;
        return this;
    }

    public void setMode_of_disposal_for_discharge(String mode_of_disposal_for_discharge) {
        this.mode_of_disposal_for_discharge = mode_of_disposal_for_discharge;
    }

    public String getRecycling_process() {
        return recycling_process;
    }

    public Environment_impactdetail recycling_process(String recycling_process) {
        this.recycling_process = recycling_process;
        return this;
    }

    public void setRecycling_process(String recycling_process) {
        this.recycling_process = recycling_process;
    }

    public String getRecycling_cooling() {
        return recycling_cooling;
    }

    public Environment_impactdetail recycling_cooling(String recycling_cooling) {
        this.recycling_cooling = recycling_cooling;
        return this;
    }

    public void setRecycling_cooling(String recycling_cooling) {
        this.recycling_cooling = recycling_cooling;
    }

    public String getRecycling_domestic() {
        return recycling_domestic;
    }

    public Environment_impactdetail recycling_domestic(String recycling_domestic) {
        this.recycling_domestic = recycling_domestic;
        return this;
    }

    public void setRecycling_domestic(String recycling_domestic) {
        this.recycling_domestic = recycling_domestic;
    }

    public String getRecycling_other() {
        return recycling_other;
    }

    public Environment_impactdetail recycling_other(String recycling_other) {
        this.recycling_other = recycling_other;
        return this;
    }

    public void setRecycling_other(String recycling_other) {
        this.recycling_other = recycling_other;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Environment_impactdetail createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Environment_impactdetail updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
    }

    public String getSourcewatersupplyother() {
        return sourcewatersupplyother;
    }

    public Environment_impactdetail sourcewatersupplyother(String sourcewatersupplyother) {
        this.sourcewatersupplyother = sourcewatersupplyother;
        return this;
    }

    public void setSourcewatersupplyother(String sourcewatersupplyother) {
        this.sourcewatersupplyother = sourcewatersupplyother;
    }

    public String getModedisposalother() {
        return modedisposalother;
    }

    public Environment_impactdetail modedisposalother(String modedisposalother) {
        this.modedisposalother = modedisposalother;
        return this;
    }

    public void setModedisposalother(String modedisposalother) {
        this.modedisposalother = modedisposalother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Environment_impactdetail environment_impactdetail = (Environment_impactdetail) o;
        if (environment_impactdetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, environment_impactdetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Environment_impactdetail{" +
            "id=" + id +
            ", water_process='" + water_process + "'" +
            ", water_cooling='" + water_cooling + "'" +
            ", water_domestic='" + water_domestic + "'" +
            ", water_other='" + water_other + "'" +
            ", waste_water_process='" + waste_water_process + "'" +
            ", waste_water_cooling='" + waste_water_cooling + "'" +
            ", waste_water_domesting='" + waste_water_domesting + "'" +
            ", waste_water_other='" + waste_water_other + "'" +
            ", source_of_water_supply='" + source_of_water_supply + "'" +
            ", mode_of_disposal_for_discharge='" + mode_of_disposal_for_discharge + "'" +
            ", recycling_process='" + recycling_process + "'" +
            ", recycling_cooling='" + recycling_cooling + "'" +
            ", recycling_domestic='" + recycling_domestic + "'" +
            ", recycling_other='" + recycling_other + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            ", sourcewatersupplyother='" + sourcewatersupplyother + "'" +
            ", modedisposalother='" + modedisposalother + "'" +
            '}';
    }
}
