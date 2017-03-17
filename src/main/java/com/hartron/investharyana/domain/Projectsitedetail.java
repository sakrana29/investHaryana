package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectsitedetail.
 */

@Table(name = "projectsitedetail")
public class Projectsitedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private String siteaddress;

    private UUID district;

    private UUID block;

    private UUID city_town_village;

    private UUID tehsil_subtehsil;

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

    private UUID connectingroad;

    private Boolean intersectiondistance;

    private Boolean railwaydistance;

    private Boolean confirmitylanduse;

    private UUID landzoneuse_type;

    private UUID buildingexisted;

    private Boolean existing_building_applicable;

    private Boolean site_situated_in_controlled_area;

    private String khasra_document;

    private String revenu_shajra_document;

    private String jamabandi;

    private String nonencumbrance_certificate;

    private String ownership_document;

    private String lease_document;

    private String landagreement_document;

    private String sitelayoutplan;

    private String locationplan;

    private String linearstripplan;

    private String sitesituated_document;

    private String controlledarea_document;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Projectsitedetail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getSiteaddress() {
        return siteaddress;
    }

    public Projectsitedetail siteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
        return this;
    }

    public void setSiteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
    }

    public UUID getDistrict() {
        return district;
    }

    public Projectsitedetail district(UUID district) {
        this.district = district;
        return this;
    }

    public void setDistrict(UUID district) {
        this.district = district;
    }

    public UUID getBlock() {
        return block;
    }

    public Projectsitedetail block(UUID block) {
        this.block = block;
        return this;
    }

    public void setBlock(UUID block) {
        this.block = block;
    }

    public UUID getCity_town_village() {
        return city_town_village;
    }

    public Projectsitedetail city_town_village(UUID city_town_village) {
        this.city_town_village = city_town_village;
        return this;
    }

    public void setCity_town_village(UUID city_town_village) {
        this.city_town_village = city_town_village;
    }

    public UUID getTehsil_subtehsil() {
        return tehsil_subtehsil;
    }

    public Projectsitedetail tehsil_subtehsil(UUID tehsil_subtehsil) {
        this.tehsil_subtehsil = tehsil_subtehsil;
        return this;
    }

    public void setTehsil_subtehsil(UUID tehsil_subtehsil) {
        this.tehsil_subtehsil = tehsil_subtehsil;
    }

    public Boolean isMultyvillageinvolved() {
        return multyvillageinvolved;
    }

    public Projectsitedetail multyvillageinvolved(Boolean multyvillageinvolved) {
        this.multyvillageinvolved = multyvillageinvolved;
        return this;
    }

    public void setMultyvillageinvolved(Boolean multyvillageinvolved) {
        this.multyvillageinvolved = multyvillageinvolved;
    }

    public String getVillageinvolved() {
        return villageinvolved;
    }

    public Projectsitedetail villageinvolved(String villageinvolved) {
        this.villageinvolved = villageinvolved;
        return this;
    }

    public void setVillageinvolved(String villageinvolved) {
        this.villageinvolved = villageinvolved;
    }

    public Boolean isFalls_in_aravalli() {
        return falls_in_aravalli;
    }

    public Projectsitedetail falls_in_aravalli(Boolean falls_in_aravalli) {
        this.falls_in_aravalli = falls_in_aravalli;
        return this;
    }

    public void setFalls_in_aravalli(Boolean falls_in_aravalli) {
        this.falls_in_aravalli = falls_in_aravalli;
    }

    public Boolean isIslandprocured() {
        return islandprocured;
    }

    public Projectsitedetail islandprocured(Boolean islandprocured) {
        this.islandprocured = islandprocured;
        return this;
    }

    public void setIslandprocured(Boolean islandprocured) {
        this.islandprocured = islandprocured;
    }

    public Boolean isAllotedbyhsiidc() {
        return allotedbyhsiidc;
    }

    public Projectsitedetail allotedbyhsiidc(Boolean allotedbyhsiidc) {
        this.allotedbyhsiidc = allotedbyhsiidc;
        return this;
    }

    public void setAllotedbyhsiidc(Boolean allotedbyhsiidc) {
        this.allotedbyhsiidc = allotedbyhsiidc;
    }

    public String getEstate() {
        return estate;
    }

    public Projectsitedetail estate(String estate) {
        this.estate = estate;
        return this;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getCluster() {
        return cluster;
    }

    public Projectsitedetail cluster(String cluster) {
        this.cluster = cluster;
        return this;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getPhase() {
        return phase;
    }

    public Projectsitedetail phase(String phase) {
        this.phase = phase;
        return this;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getSector() {
        return sector;
    }

    public Projectsitedetail sector(String sector) {
        this.sector = sector;
        return this;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPlotno() {
        return plotno;
    }

    public Projectsitedetail plotno(String plotno) {
        this.plotno = plotno;
        return this;
    }

    public void setPlotno(String plotno) {
        this.plotno = plotno;
    }

    public String getHadbastno() {
        return hadbastno;
    }

    public Projectsitedetail hadbastno(String hadbastno) {
        this.hadbastno = hadbastno;
        return this;
    }

    public void setHadbastno(String hadbastno) {
        this.hadbastno = hadbastno;
    }

    public Boolean isLiesunder_mc() {
        return liesunder_mc;
    }

    public Projectsitedetail liesunder_mc(Boolean liesunder_mc) {
        this.liesunder_mc = liesunder_mc;
        return this;
    }

    public void setLiesunder_mc(Boolean liesunder_mc) {
        this.liesunder_mc = liesunder_mc;
    }

    public Integer getDistance_from_mc() {
        return distance_from_mc;
    }

    public Projectsitedetail distance_from_mc(Integer distance_from_mc) {
        this.distance_from_mc = distance_from_mc;
        return this;
    }

    public void setDistance_from_mc(Integer distance_from_mc) {
        this.distance_from_mc = distance_from_mc;
    }

    public Boolean isIslocated_in_urban() {
        return islocated_in_urban;
    }

    public Projectsitedetail islocated_in_urban(Boolean islocated_in_urban) {
        this.islocated_in_urban = islocated_in_urban;
        return this;
    }

    public void setIslocated_in_urban(Boolean islocated_in_urban) {
        this.islocated_in_urban = islocated_in_urban;
    }

    public BigDecimal getTotalproposedprojectarea() {
        return totalproposedprojectarea;
    }

    public Projectsitedetail totalproposedprojectarea(BigDecimal totalproposedprojectarea) {
        this.totalproposedprojectarea = totalproposedprojectarea;
        return this;
    }

    public void setTotalproposedprojectarea(BigDecimal totalproposedprojectarea) {
        this.totalproposedprojectarea = totalproposedprojectarea;
    }

    public BigDecimal getProposedbuilt_up_area() {
        return proposedbuilt_up_area;
    }

    public Projectsitedetail proposedbuilt_up_area(BigDecimal proposedbuilt_up_area) {
        this.proposedbuilt_up_area = proposedbuilt_up_area;
        return this;
    }

    public void setProposedbuilt_up_area(BigDecimal proposedbuilt_up_area) {
        this.proposedbuilt_up_area = proposedbuilt_up_area;
    }

    public Boolean isCertifiedownership() {
        return certifiedownership;
    }

    public Projectsitedetail certifiedownership(Boolean certifiedownership) {
        this.certifiedownership = certifiedownership;
        return this;
    }

    public void setCertifiedownership(Boolean certifiedownership) {
        this.certifiedownership = certifiedownership;
    }

    public Boolean isLeaseapplicable() {
        return leaseapplicable;
    }

    public Projectsitedetail leaseapplicable(Boolean leaseapplicable) {
        this.leaseapplicable = leaseapplicable;
        return this;
    }

    public void setLeaseapplicable(Boolean leaseapplicable) {
        this.leaseapplicable = leaseapplicable;
    }

    public Boolean isLandagreementapplicable() {
        return landagreementapplicable;
    }

    public Projectsitedetail landagreementapplicable(Boolean landagreementapplicable) {
        this.landagreementapplicable = landagreementapplicable;
        return this;
    }

    public void setLandagreementapplicable(Boolean landagreementapplicable) {
        this.landagreementapplicable = landagreementapplicable;
    }

    public UUID getConnectingroad() {
        return connectingroad;
    }

    public Projectsitedetail connectingroad(UUID connectingroad) {
        this.connectingroad = connectingroad;
        return this;
    }

    public void setConnectingroad(UUID connectingroad) {
        this.connectingroad = connectingroad;
    }

    public Boolean isIntersectiondistance() {
        return intersectiondistance;
    }

    public Projectsitedetail intersectiondistance(Boolean intersectiondistance) {
        this.intersectiondistance = intersectiondistance;
        return this;
    }

    public void setIntersectiondistance(Boolean intersectiondistance) {
        this.intersectiondistance = intersectiondistance;
    }

    public Boolean isRailwaydistance() {
        return railwaydistance;
    }

    public Projectsitedetail railwaydistance(Boolean railwaydistance) {
        this.railwaydistance = railwaydistance;
        return this;
    }

    public void setRailwaydistance(Boolean railwaydistance) {
        this.railwaydistance = railwaydistance;
    }

    public Boolean isConfirmitylanduse() {
        return confirmitylanduse;
    }

    public Projectsitedetail confirmitylanduse(Boolean confirmitylanduse) {
        this.confirmitylanduse = confirmitylanduse;
        return this;
    }

    public void setConfirmitylanduse(Boolean confirmitylanduse) {
        this.confirmitylanduse = confirmitylanduse;
    }

    public UUID getLandzoneuse_type() {
        return landzoneuse_type;
    }

    public Projectsitedetail landzoneuse_type(UUID landzoneuse_type) {
        this.landzoneuse_type = landzoneuse_type;
        return this;
    }

    public void setLandzoneuse_type(UUID landzoneuse_type) {
        this.landzoneuse_type = landzoneuse_type;
    }

    public UUID getBuildingexisted() {
        return buildingexisted;
    }

    public Projectsitedetail buildingexisted(UUID buildingexisted) {
        this.buildingexisted = buildingexisted;
        return this;
    }

    public void setBuildingexisted(UUID buildingexisted) {
        this.buildingexisted = buildingexisted;
    }

    public Boolean isExisting_building_applicable() {
        return existing_building_applicable;
    }

    public Projectsitedetail existing_building_applicable(Boolean existing_building_applicable) {
        this.existing_building_applicable = existing_building_applicable;
        return this;
    }

    public void setExisting_building_applicable(Boolean existing_building_applicable) {
        this.existing_building_applicable = existing_building_applicable;
    }

    public Boolean isSite_situated_in_controlled_area() {
        return site_situated_in_controlled_area;
    }

    public Projectsitedetail site_situated_in_controlled_area(Boolean site_situated_in_controlled_area) {
        this.site_situated_in_controlled_area = site_situated_in_controlled_area;
        return this;
    }

    public void setSite_situated_in_controlled_area(Boolean site_situated_in_controlled_area) {
        this.site_situated_in_controlled_area = site_situated_in_controlled_area;
    }

    public String getKhasra_document() {
        return khasra_document;
    }

    public Projectsitedetail khasra_document(String khasra_document) {
        this.khasra_document = khasra_document;
        return this;
    }

    public void setKhasra_document(String khasra_document) {
        this.khasra_document = khasra_document;
    }

    public String getRevenu_shajra_document() {
        return revenu_shajra_document;
    }

    public Projectsitedetail revenu_shajra_document(String revenu_shajra_document) {
        this.revenu_shajra_document = revenu_shajra_document;
        return this;
    }

    public void setRevenu_shajra_document(String revenu_shajra_document) {
        this.revenu_shajra_document = revenu_shajra_document;
    }

    public String getJamabandi() {
        return jamabandi;
    }

    public Projectsitedetail jamabandi(String jamabandi) {
        this.jamabandi = jamabandi;
        return this;
    }

    public void setJamabandi(String jamabandi) {
        this.jamabandi = jamabandi;
    }

    public String getNonencumbrance_certificate() {
        return nonencumbrance_certificate;
    }

    public Projectsitedetail nonencumbrance_certificate(String nonencumbrance_certificate) {
        this.nonencumbrance_certificate = nonencumbrance_certificate;
        return this;
    }

    public void setNonencumbrance_certificate(String nonencumbrance_certificate) {
        this.nonencumbrance_certificate = nonencumbrance_certificate;
    }

    public String getOwnership_document() {
        return ownership_document;
    }

    public Projectsitedetail ownership_document(String ownership_document) {
        this.ownership_document = ownership_document;
        return this;
    }

    public void setOwnership_document(String ownership_document) {
        this.ownership_document = ownership_document;
    }

    public String getLease_document() {
        return lease_document;
    }

    public Projectsitedetail lease_document(String lease_document) {
        this.lease_document = lease_document;
        return this;
    }

    public void setLease_document(String lease_document) {
        this.lease_document = lease_document;
    }

    public String getLandagreement_document() {
        return landagreement_document;
    }

    public Projectsitedetail landagreement_document(String landagreement_document) {
        this.landagreement_document = landagreement_document;
        return this;
    }

    public void setLandagreement_document(String landagreement_document) {
        this.landagreement_document = landagreement_document;
    }

    public String getSitelayoutplan() {
        return sitelayoutplan;
    }

    public Projectsitedetail sitelayoutplan(String sitelayoutplan) {
        this.sitelayoutplan = sitelayoutplan;
        return this;
    }

    public void setSitelayoutplan(String sitelayoutplan) {
        this.sitelayoutplan = sitelayoutplan;
    }

    public String getLocationplan() {
        return locationplan;
    }

    public Projectsitedetail locationplan(String locationplan) {
        this.locationplan = locationplan;
        return this;
    }

    public void setLocationplan(String locationplan) {
        this.locationplan = locationplan;
    }

    public String getLinearstripplan() {
        return linearstripplan;
    }

    public Projectsitedetail linearstripplan(String linearstripplan) {
        this.linearstripplan = linearstripplan;
        return this;
    }

    public void setLinearstripplan(String linearstripplan) {
        this.linearstripplan = linearstripplan;
    }

    public String getSitesituated_document() {
        return sitesituated_document;
    }

    public Projectsitedetail sitesituated_document(String sitesituated_document) {
        this.sitesituated_document = sitesituated_document;
        return this;
    }

    public void setSitesituated_document(String sitesituated_document) {
        this.sitesituated_document = sitesituated_document;
    }

    public String getControlledarea_document() {
        return controlledarea_document;
    }

    public Projectsitedetail controlledarea_document(String controlledarea_document) {
        this.controlledarea_document = controlledarea_document;
        return this;
    }

    public void setControlledarea_document(String controlledarea_document) {
        this.controlledarea_document = controlledarea_document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectsitedetail projectsitedetail = (Projectsitedetail) o;
        if (projectsitedetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectsitedetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectsitedetail{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", siteaddress='" + siteaddress + "'" +
            ", district='" + district + "'" +
            ", block='" + block + "'" +
            ", city_town_village='" + city_town_village + "'" +
            ", tehsil_subtehsil='" + tehsil_subtehsil + "'" +
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
            ", connectingroad='" + connectingroad + "'" +
            ", intersectiondistance='" + intersectiondistance + "'" +
            ", railwaydistance='" + railwaydistance + "'" +
            ", confirmitylanduse='" + confirmitylanduse + "'" +
            ", landzoneuse_type='" + landzoneuse_type + "'" +
            ", buildingexisted='" + buildingexisted + "'" +
            ", existing_building_applicable='" + existing_building_applicable + "'" +
            ", site_situated_in_controlled_area='" + site_situated_in_controlled_area + "'" +
            ", khasra_document='" + khasra_document + "'" +
            ", revenu_shajra_document='" + revenu_shajra_document + "'" +
            ", jamabandi='" + jamabandi + "'" +
            ", nonencumbrance_certificate='" + nonencumbrance_certificate + "'" +
            ", ownership_document='" + ownership_document + "'" +
            ", lease_document='" + lease_document + "'" +
            ", landagreement_document='" + landagreement_document + "'" +
            ", sitelayoutplan='" + sitelayoutplan + "'" +
            ", locationplan='" + locationplan + "'" +
            ", linearstripplan='" + linearstripplan + "'" +
            ", sitesituated_document='" + sitesituated_document + "'" +
            ", controlledarea_document='" + controlledarea_document + "'" +
            '}';
    }
}
