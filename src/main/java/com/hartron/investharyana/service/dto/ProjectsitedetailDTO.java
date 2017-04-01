package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectsitedetail entity.
 */
public class ProjectsitedetailDTO implements Serializable {

    private UUID id;

    private String siteaddress;

    private Boolean multyvillageinvolved;

    private String villageinvolved;

    private Boolean falls_in_aravalli;

    private Boolean islandprocured;

    private Boolean allotedbyhsiidc;

    private String estate;

    private String cluster;

    private String phase;

    private String sector;

    private String plotno;

    private String hadbastno;

    private Boolean liesunder_mc;

    private Integer distance_from_mc;

    private Boolean islocated_in_urban;

    private BigDecimal totalproposedprojectarea;

    private BigDecimal proposedbuilt_up_area;

    private Boolean certifiedownership;

    private Boolean leaseapplicable;

    private Boolean landagreementapplicable;

    private Boolean intersectiondistance;

    private Boolean railwaydistance;

    private Boolean confirmitylanduse;

    private Boolean existing_building_applicable;

    private Boolean site_situated_in_controlled_area;

    private String buildingexisted;

    private String district;

    private String block;

    private String city_town_village;

    private String connectingroad;

    private String landzoneuse_type;

    private String tehsil_subtehsil;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getSiteaddress() {
        return siteaddress;
    }

    public void setSiteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
    }
    public Boolean getMultyvillageinvolved() {
        return multyvillageinvolved;
    }

    public void setMultyvillageinvolved(Boolean multyvillageinvolved) {
        this.multyvillageinvolved = multyvillageinvolved;
    }
    public String getVillageinvolved() {
        return villageinvolved;
    }

    public void setVillageinvolved(String villageinvolved) {
        this.villageinvolved = villageinvolved;
    }
    public Boolean getFalls_in_aravalli() {
        return falls_in_aravalli;
    }

    public void setFalls_in_aravalli(Boolean falls_in_aravalli) {
        this.falls_in_aravalli = falls_in_aravalli;
    }
    public Boolean getIslandprocured() {
        return islandprocured;
    }

    public void setIslandprocured(Boolean islandprocured) {
        this.islandprocured = islandprocured;
    }
    public Boolean getAllotedbyhsiidc() {
        return allotedbyhsiidc;
    }

    public void setAllotedbyhsiidc(Boolean allotedbyhsiidc) {
        this.allotedbyhsiidc = allotedbyhsiidc;
    }
    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }
    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
    public String getPlotno() {
        return plotno;
    }

    public void setPlotno(String plotno) {
        this.plotno = plotno;
    }
    public String getHadbastno() {
        return hadbastno;
    }

    public void setHadbastno(String hadbastno) {
        this.hadbastno = hadbastno;
    }
    public Boolean getLiesunder_mc() {
        return liesunder_mc;
    }

    public void setLiesunder_mc(Boolean liesunder_mc) {
        this.liesunder_mc = liesunder_mc;
    }
    public Integer getDistance_from_mc() {
        return distance_from_mc;
    }

    public void setDistance_from_mc(Integer distance_from_mc) {
        this.distance_from_mc = distance_from_mc;
    }
    public Boolean getIslocated_in_urban() {
        return islocated_in_urban;
    }

    public void setIslocated_in_urban(Boolean islocated_in_urban) {
        this.islocated_in_urban = islocated_in_urban;
    }
    public BigDecimal getTotalproposedprojectarea() {
        return totalproposedprojectarea;
    }

    public void setTotalproposedprojectarea(BigDecimal totalproposedprojectarea) {
        this.totalproposedprojectarea = totalproposedprojectarea;
    }
    public BigDecimal getProposedbuilt_up_area() {
        return proposedbuilt_up_area;
    }

    public void setProposedbuilt_up_area(BigDecimal proposedbuilt_up_area) {
        this.proposedbuilt_up_area = proposedbuilt_up_area;
    }
    public Boolean getCertifiedownership() {
        return certifiedownership;
    }

    public void setCertifiedownership(Boolean certifiedownership) {
        this.certifiedownership = certifiedownership;
    }
    public Boolean getLeaseapplicable() {
        return leaseapplicable;
    }

    public void setLeaseapplicable(Boolean leaseapplicable) {
        this.leaseapplicable = leaseapplicable;
    }
    public Boolean getLandagreementapplicable() {
        return landagreementapplicable;
    }

    public void setLandagreementapplicable(Boolean landagreementapplicable) {
        this.landagreementapplicable = landagreementapplicable;
    }
    public Boolean getIntersectiondistance() {
        return intersectiondistance;
    }

    public void setIntersectiondistance(Boolean intersectiondistance) {
        this.intersectiondistance = intersectiondistance;
    }
    public Boolean getRailwaydistance() {
        return railwaydistance;
    }

    public void setRailwaydistance(Boolean railwaydistance) {
        this.railwaydistance = railwaydistance;
    }
    public Boolean getConfirmitylanduse() {
        return confirmitylanduse;
    }

    public void setConfirmitylanduse(Boolean confirmitylanduse) {
        this.confirmitylanduse = confirmitylanduse;
    }
    public Boolean getExisting_building_applicable() {
        return existing_building_applicable;
    }

    public void setExisting_building_applicable(Boolean existing_building_applicable) {
        this.existing_building_applicable = existing_building_applicable;
    }
    public Boolean getSite_situated_in_controlled_area() {
        return site_situated_in_controlled_area;
    }

    public void setSite_situated_in_controlled_area(Boolean site_situated_in_controlled_area) {
        this.site_situated_in_controlled_area = site_situated_in_controlled_area;
    }
    public String getBuildingexisted() {
        return buildingexisted;
    }

    public void setBuildingexisted(String buildingexisted) {
        this.buildingexisted = buildingexisted;
    }
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
    public String getCity_town_village() {
        return city_town_village;
    }

    public void setCity_town_village(String city_town_village) {
        this.city_town_village = city_town_village;
    }
    public String getConnectingroad() {
        return connectingroad;
    }

    public void setConnectingroad(String connectingroad) {
        this.connectingroad = connectingroad;
    }
    public String getLandzoneuse_type() {
        return landzoneuse_type;
    }

    public void setLandzoneuse_type(String landzoneuse_type) {
        this.landzoneuse_type = landzoneuse_type;
    }
    public String getTehsil_subtehsil() {
        return tehsil_subtehsil;
    }

    public void setTehsil_subtehsil(String tehsil_subtehsil) {
        this.tehsil_subtehsil = tehsil_subtehsil;
    }
    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }
    public ZonedDateTime getUpdatedate() {
        return updatedate;
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

        ProjectsitedetailDTO projectsitedetailDTO = (ProjectsitedetailDTO) o;

        if ( ! Objects.equals(id, projectsitedetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectsitedetailDTO{" +
            "id=" + id +
            ", siteaddress='" + siteaddress + "'" +
            ", multyvillageinvolved='" + multyvillageinvolved + "'" +
            ", villageinvolved='" + villageinvolved + "'" +
            ", falls_in_aravalli='" + falls_in_aravalli + "'" +
            ", islandprocured='" + islandprocured + "'" +
            ", allotedbyhsiidc='" + allotedbyhsiidc + "'" +
            ", estate='" + estate + "'" +
            ", cluster='" + cluster + "'" +
            ", phase='" + phase + "'" +
            ", sector='" + sector + "'" +
            ", plotno='" + plotno + "'" +
            ", hadbastno='" + hadbastno + "'" +
            ", liesunder_mc='" + liesunder_mc + "'" +
            ", distance_from_mc='" + distance_from_mc + "'" +
            ", islocated_in_urban='" + islocated_in_urban + "'" +
            ", totalproposedprojectarea='" + totalproposedprojectarea + "'" +
            ", proposedbuilt_up_area='" + proposedbuilt_up_area + "'" +
            ", certifiedownership='" + certifiedownership + "'" +
            ", leaseapplicable='" + leaseapplicable + "'" +
            ", landagreementapplicable='" + landagreementapplicable + "'" +
            ", intersectiondistance='" + intersectiondistance + "'" +
            ", railwaydistance='" + railwaydistance + "'" +
            ", confirmitylanduse='" + confirmitylanduse + "'" +
            ", existing_building_applicable='" + existing_building_applicable + "'" +
            ", site_situated_in_controlled_area='" + site_situated_in_controlled_area + "'" +
            ", buildingexisted='" + buildingexisted + "'" +
            ", district='" + district + "'" +
            ", block='" + block + "'" +
            ", city_town_village='" + city_town_village + "'" +
            ", connectingroad='" + connectingroad + "'" +
            ", landzoneuse_type='" + landzoneuse_type + "'" +
            ", tehsil_subtehsil='" + tehsil_subtehsil + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
