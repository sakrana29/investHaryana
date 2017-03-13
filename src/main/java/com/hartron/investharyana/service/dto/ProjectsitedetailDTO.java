package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectsitedetail entity.
 */
public class ProjectsitedetailDTO implements Serializable {

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

    private ByteBuffer khasra_document;
    private String khasra_documentContentType;

    private Boolean liesunder_mc;

    private Integer distance_from_mc;

    private Boolean islocated_in_urban;

    private ByteBuffer revenu_shajra_document;
    private String revenu_shajra_documentContentType;

    private ByteBuffer jamabandi;
    private String jamabandiContentType;

    private ByteBuffer nonencumbrance_certificate;
    private String nonencumbrance_certificateContentType;

    private BigDecimal totalproposedprojectarea;

    private BigDecimal proposedbuilt_up_area;

    private Boolean certifiedownership;

    private ByteBuffer ownership_document;
    private String ownership_documentContentType;

    private Boolean leaseapplicable;

    private ByteBuffer lease_document;
    private String lease_documentContentType;

    private Boolean landagreementapplicable;

    private ByteBuffer landagreement_document;
    private String landagreement_documentContentType;

    private ByteBuffer sitelayoutplan;
    private String sitelayoutplanContentType;

    private ByteBuffer locationplan;
    private String locationplanContentType;

    private ByteBuffer linearstripplan;
    private String linearstripplanContentType;

    private UUID connectingroad;

    private Boolean intersectiondistance;

    private Boolean railwaydistance;

    private Boolean confirmitylanduse;

    private UUID landzoneuse_type;

    private ByteBuffer sitesituated_document;
    private String sitesituated_documentContentType;

    private UUID buildingexisted;

    private Boolean existing_building_applicable;

    private ByteBuffer buildingplan_document;
    private String buildingplan_documentContentType;

    private Boolean site_situated_in_controlled_area;

    private ByteBuffer controlledarea_document;
    private String controlledarea_documentContentType;

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
    public String getSiteaddress() {
        return siteaddress;
    }

    public void setSiteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
    }
    public UUID getDistrict() {
        return district;
    }

    public void setDistrict(UUID district) {
        this.district = district;
    }
    public UUID getBlock() {
        return block;
    }

    public void setBlock(UUID block) {
        this.block = block;
    }
    public UUID getCity_town_village() {
        return city_town_village;
    }

    public void setCity_town_village(UUID city_town_village) {
        this.city_town_village = city_town_village;
    }
    public UUID getTehsil_subtehsil() {
        return tehsil_subtehsil;
    }

    public void setTehsil_subtehsil(UUID tehsil_subtehsil) {
        this.tehsil_subtehsil = tehsil_subtehsil;
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
    public ByteBuffer getKhasra_document() {
        return khasra_document;
    }

    public void setKhasra_document(ByteBuffer khasra_document) {
        this.khasra_document = khasra_document;
    }

    public String getKhasra_documentContentType() {
        return khasra_documentContentType;
    }

    public void setKhasra_documentContentType(String khasra_documentContentType) {
        this.khasra_documentContentType = khasra_documentContentType;
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
    public ByteBuffer getRevenu_shajra_document() {
        return revenu_shajra_document;
    }

    public void setRevenu_shajra_document(ByteBuffer revenu_shajra_document) {
        this.revenu_shajra_document = revenu_shajra_document;
    }

    public String getRevenu_shajra_documentContentType() {
        return revenu_shajra_documentContentType;
    }

    public void setRevenu_shajra_documentContentType(String revenu_shajra_documentContentType) {
        this.revenu_shajra_documentContentType = revenu_shajra_documentContentType;
    }
    public ByteBuffer getJamabandi() {
        return jamabandi;
    }

    public void setJamabandi(ByteBuffer jamabandi) {
        this.jamabandi = jamabandi;
    }

    public String getJamabandiContentType() {
        return jamabandiContentType;
    }

    public void setJamabandiContentType(String jamabandiContentType) {
        this.jamabandiContentType = jamabandiContentType;
    }
    public ByteBuffer getNonencumbrance_certificate() {
        return nonencumbrance_certificate;
    }

    public void setNonencumbrance_certificate(ByteBuffer nonencumbrance_certificate) {
        this.nonencumbrance_certificate = nonencumbrance_certificate;
    }

    public String getNonencumbrance_certificateContentType() {
        return nonencumbrance_certificateContentType;
    }

    public void setNonencumbrance_certificateContentType(String nonencumbrance_certificateContentType) {
        this.nonencumbrance_certificateContentType = nonencumbrance_certificateContentType;
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
    public ByteBuffer getOwnership_document() {
        return ownership_document;
    }

    public void setOwnership_document(ByteBuffer ownership_document) {
        this.ownership_document = ownership_document;
    }

    public String getOwnership_documentContentType() {
        return ownership_documentContentType;
    }

    public void setOwnership_documentContentType(String ownership_documentContentType) {
        this.ownership_documentContentType = ownership_documentContentType;
    }
    public Boolean getLeaseapplicable() {
        return leaseapplicable;
    }

    public void setLeaseapplicable(Boolean leaseapplicable) {
        this.leaseapplicable = leaseapplicable;
    }
    public ByteBuffer getLease_document() {
        return lease_document;
    }

    public void setLease_document(ByteBuffer lease_document) {
        this.lease_document = lease_document;
    }

    public String getLease_documentContentType() {
        return lease_documentContentType;
    }

    public void setLease_documentContentType(String lease_documentContentType) {
        this.lease_documentContentType = lease_documentContentType;
    }
    public Boolean getLandagreementapplicable() {
        return landagreementapplicable;
    }

    public void setLandagreementapplicable(Boolean landagreementapplicable) {
        this.landagreementapplicable = landagreementapplicable;
    }
    public ByteBuffer getLandagreement_document() {
        return landagreement_document;
    }

    public void setLandagreement_document(ByteBuffer landagreement_document) {
        this.landagreement_document = landagreement_document;
    }

    public String getLandagreement_documentContentType() {
        return landagreement_documentContentType;
    }

    public void setLandagreement_documentContentType(String landagreement_documentContentType) {
        this.landagreement_documentContentType = landagreement_documentContentType;
    }
    public ByteBuffer getSitelayoutplan() {
        return sitelayoutplan;
    }

    public void setSitelayoutplan(ByteBuffer sitelayoutplan) {
        this.sitelayoutplan = sitelayoutplan;
    }

    public String getSitelayoutplanContentType() {
        return sitelayoutplanContentType;
    }

    public void setSitelayoutplanContentType(String sitelayoutplanContentType) {
        this.sitelayoutplanContentType = sitelayoutplanContentType;
    }
    public ByteBuffer getLocationplan() {
        return locationplan;
    }

    public void setLocationplan(ByteBuffer locationplan) {
        this.locationplan = locationplan;
    }

    public String getLocationplanContentType() {
        return locationplanContentType;
    }

    public void setLocationplanContentType(String locationplanContentType) {
        this.locationplanContentType = locationplanContentType;
    }
    public ByteBuffer getLinearstripplan() {
        return linearstripplan;
    }

    public void setLinearstripplan(ByteBuffer linearstripplan) {
        this.linearstripplan = linearstripplan;
    }

    public String getLinearstripplanContentType() {
        return linearstripplanContentType;
    }

    public void setLinearstripplanContentType(String linearstripplanContentType) {
        this.linearstripplanContentType = linearstripplanContentType;
    }
    public UUID getConnectingroad() {
        return connectingroad;
    }

    public void setConnectingroad(UUID connectingroad) {
        this.connectingroad = connectingroad;
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
    public UUID getLandzoneuse_type() {
        return landzoneuse_type;
    }

    public void setLandzoneuse_type(UUID landzoneuse_type) {
        this.landzoneuse_type = landzoneuse_type;
    }
    public ByteBuffer getSitesituated_document() {
        return sitesituated_document;
    }

    public void setSitesituated_document(ByteBuffer sitesituated_document) {
        this.sitesituated_document = sitesituated_document;
    }

    public String getSitesituated_documentContentType() {
        return sitesituated_documentContentType;
    }

    public void setSitesituated_documentContentType(String sitesituated_documentContentType) {
        this.sitesituated_documentContentType = sitesituated_documentContentType;
    }
    public UUID getBuildingexisted() {
        return buildingexisted;
    }

    public void setBuildingexisted(UUID buildingexisted) {
        this.buildingexisted = buildingexisted;
    }
    public Boolean getExisting_building_applicable() {
        return existing_building_applicable;
    }

    public void setExisting_building_applicable(Boolean existing_building_applicable) {
        this.existing_building_applicable = existing_building_applicable;
    }
    public ByteBuffer getBuildingplan_document() {
        return buildingplan_document;
    }

    public void setBuildingplan_document(ByteBuffer buildingplan_document) {
        this.buildingplan_document = buildingplan_document;
    }

    public String getBuildingplan_documentContentType() {
        return buildingplan_documentContentType;
    }

    public void setBuildingplan_documentContentType(String buildingplan_documentContentType) {
        this.buildingplan_documentContentType = buildingplan_documentContentType;
    }
    public Boolean getSite_situated_in_controlled_area() {
        return site_situated_in_controlled_area;
    }

    public void setSite_situated_in_controlled_area(Boolean site_situated_in_controlled_area) {
        this.site_situated_in_controlled_area = site_situated_in_controlled_area;
    }
    public ByteBuffer getControlledarea_document() {
        return controlledarea_document;
    }

    public void setControlledarea_document(ByteBuffer controlledarea_document) {
        this.controlledarea_document = controlledarea_document;
    }

    public String getControlledarea_documentContentType() {
        return controlledarea_documentContentType;
    }

    public void setControlledarea_documentContentType(String controlledarea_documentContentType) {
        this.controlledarea_documentContentType = controlledarea_documentContentType;
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
            ", khasra_document='" + khasra_document + "'" +
            ", liesunder_mc='" + liesunder_mc + "'" +
            ", distance_from_mc='" + distance_from_mc + "'" +
            ", islocated_in_urban='" + islocated_in_urban + "'" +
            ", revenu_shajra_document='" + revenu_shajra_document + "'" +
            ", jamabandi='" + jamabandi + "'" +
            ", nonencumbrance_certificate='" + nonencumbrance_certificate + "'" +
            ", totalproposedprojectarea='" + totalproposedprojectarea + "'" +
            ", proposedbuilt_up_area='" + proposedbuilt_up_area + "'" +
            ", certifiedownership='" + certifiedownership + "'" +
            ", ownership_document='" + ownership_document + "'" +
            ", leaseapplicable='" + leaseapplicable + "'" +
            ", lease_document='" + lease_document + "'" +
            ", landagreementapplicable='" + landagreementapplicable + "'" +
            ", landagreement_document='" + landagreement_document + "'" +
            ", sitelayoutplan='" + sitelayoutplan + "'" +
            ", locationplan='" + locationplan + "'" +
            ", linearstripplan='" + linearstripplan + "'" +
            ", connectingroad='" + connectingroad + "'" +
            ", intersectiondistance='" + intersectiondistance + "'" +
            ", railwaydistance='" + railwaydistance + "'" +
            ", confirmitylanduse='" + confirmitylanduse + "'" +
            ", landzoneuse_type='" + landzoneuse_type + "'" +
            ", sitesituated_document='" + sitesituated_document + "'" +
            ", buildingexisted='" + buildingexisted + "'" +
            ", existing_building_applicable='" + existing_building_applicable + "'" +
            ", buildingplan_document='" + buildingplan_document + "'" +
            ", site_situated_in_controlled_area='" + site_situated_in_controlled_area + "'" +
            ", controlledarea_document='" + controlledarea_document + "'" +
            '}';
    }
}
