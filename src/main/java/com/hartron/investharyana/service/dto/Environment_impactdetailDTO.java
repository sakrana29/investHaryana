package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Environment_impactdetail entity.
 */
public class Environment_impactdetailDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private UUID source_of_water_supply;

    private Integer water_process;

    private Integer water_cooling;

    private Integer water_domestic;

    private Integer water_other;

    private Integer waste_water_process;

    private Integer waste_water_cooling;

    private Integer waste_water_domesting;

    private Integer waste_water_other;

    private String waste_water_treatment;

    private UUID mode_of_disposal_for_discharge;

    private UUID emissionid;

    private UUID wastewaterdetailid;

    private String document_attached;

    private String other;

    private String projectname;

    private String sourceofwatersupply;

    private String modeofdisposalfordischarge;

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
    public UUID getSource_of_water_supply() {
        return source_of_water_supply;
    }

    public void setSource_of_water_supply(UUID source_of_water_supply) {
        this.source_of_water_supply = source_of_water_supply;
    }
    public Integer getWater_process() {
        return water_process;
    }

    public void setWater_process(Integer water_process) {
        this.water_process = water_process;
    }
    public Integer getWater_cooling() {
        return water_cooling;
    }

    public void setWater_cooling(Integer water_cooling) {
        this.water_cooling = water_cooling;
    }
    public Integer getWater_domestic() {
        return water_domestic;
    }

    public void setWater_domestic(Integer water_domestic) {
        this.water_domestic = water_domestic;
    }
    public Integer getWater_other() {
        return water_other;
    }

    public void setWater_other(Integer water_other) {
        this.water_other = water_other;
    }
    public Integer getWaste_water_process() {
        return waste_water_process;
    }

    public void setWaste_water_process(Integer waste_water_process) {
        this.waste_water_process = waste_water_process;
    }
    public Integer getWaste_water_cooling() {
        return waste_water_cooling;
    }

    public void setWaste_water_cooling(Integer waste_water_cooling) {
        this.waste_water_cooling = waste_water_cooling;
    }
    public Integer getWaste_water_domesting() {
        return waste_water_domesting;
    }

    public void setWaste_water_domesting(Integer waste_water_domesting) {
        this.waste_water_domesting = waste_water_domesting;
    }
    public Integer getWaste_water_other() {
        return waste_water_other;
    }

    public void setWaste_water_other(Integer waste_water_other) {
        this.waste_water_other = waste_water_other;
    }
    public String getWaste_water_treatment() {
        return waste_water_treatment;
    }

    public void setWaste_water_treatment(String waste_water_treatment) {
        this.waste_water_treatment = waste_water_treatment;
    }
    public UUID getMode_of_disposal_for_discharge() {
        return mode_of_disposal_for_discharge;
    }

    public void setMode_of_disposal_for_discharge(UUID mode_of_disposal_for_discharge) {
        this.mode_of_disposal_for_discharge = mode_of_disposal_for_discharge;
    }
    public UUID getEmissionid() {
        return emissionid;
    }

    public void setEmissionid(UUID emissionid) {
        this.emissionid = emissionid;
    }
    public UUID getWastewaterdetailid() {
        return wastewaterdetailid;
    }

    public void setWastewaterdetailid(UUID wastewaterdetailid) {
        this.wastewaterdetailid = wastewaterdetailid;
    }
    public String getDocument_attached() {
        return document_attached;
    }

    public void setDocument_attached(String document_attached) {
        this.document_attached = document_attached;
    }
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
    public String getSourceofwatersupply() {
        return sourceofwatersupply;
    }

    public void setSourceofwatersupply(String sourceofwatersupply) {
        this.sourceofwatersupply = sourceofwatersupply;
    }
    public String getModeofdisposalfordischarge() {
        return modeofdisposalfordischarge;
    }

    public void setModeofdisposalfordischarge(String modeofdisposalfordischarge) {
        this.modeofdisposalfordischarge = modeofdisposalfordischarge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Environment_impactdetailDTO environment_impactdetailDTO = (Environment_impactdetailDTO) o;

        if ( ! Objects.equals(id, environment_impactdetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Environment_impactdetailDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", source_of_water_supply='" + source_of_water_supply + "'" +
            ", water_process='" + water_process + "'" +
            ", water_cooling='" + water_cooling + "'" +
            ", water_domestic='" + water_domestic + "'" +
            ", water_other='" + water_other + "'" +
            ", waste_water_process='" + waste_water_process + "'" +
            ", waste_water_cooling='" + waste_water_cooling + "'" +
            ", waste_water_domesting='" + waste_water_domesting + "'" +
            ", waste_water_other='" + waste_water_other + "'" +
            ", waste_water_treatment='" + waste_water_treatment + "'" +
            ", mode_of_disposal_for_discharge='" + mode_of_disposal_for_discharge + "'" +
            ", emissionid='" + emissionid + "'" +
            ", wastewaterdetailid='" + wastewaterdetailid + "'" +
            ", document_attached='" + document_attached + "'" +
            ", other='" + other + "'" +
            ", projectname='" + projectname + "'" +
            ", sourceofwatersupply='" + sourceofwatersupply + "'" +
            ", modeofdisposalfordischarge='" + modeofdisposalfordischarge + "'" +
            '}';
    }
}
