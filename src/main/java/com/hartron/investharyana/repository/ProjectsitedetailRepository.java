package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectsitedetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectsitedetail entity.
 */
@Repository
public class ProjectsitedetailRepository {

    private final Session session;

    private Mapper<Projectsitedetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectsitedetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectsitedetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectsitedetail");
        this.truncateStmt = session.prepare("TRUNCATE projectsitedetail");
    }

    public List<Projectsitedetail> findAll() {
        List<Projectsitedetail> projectsitedetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectsitedetail projectsitedetail = new Projectsitedetail();
                projectsitedetail.setId(row.getUUID("id"));
                projectsitedetail.setProjectid(row.getUUID("projectid"));
                projectsitedetail.setSiteaddress(row.getString("siteaddress"));
                projectsitedetail.setDistrict(row.getUUID("district"));
                projectsitedetail.setBlock(row.getUUID("block"));
                projectsitedetail.setCity_town_village(row.getUUID("city_town_village"));
                projectsitedetail.setTehsil_subtehsil(row.getUUID("tehsil_subtehsil"));
                projectsitedetail.setMultyvillageinvolved(row.getBool("multyvillageinvolved"));
                projectsitedetail.setVillageinvolved(row.getString("villageinvolved"));
                projectsitedetail.setFalls_in_aravalli(row.getBool("falls_in_aravalli"));
                projectsitedetail.setIslandprocured(row.getBool("islandprocured"));
                projectsitedetail.setAllotedbyhsiidc(row.getBool("allotedbyhsiidc"));
                projectsitedetail.setEstate(row.getString("estate"));
                projectsitedetail.setCluster(row.getString("cluster"));
                projectsitedetail.setPhase(row.getString("phase"));
                projectsitedetail.setSector(row.getString("sector"));
                projectsitedetail.setPlotno(row.getString("plotno"));
                projectsitedetail.setHadbastno(row.getString("hadbastno"));
                projectsitedetail.setKhasra_document(row.getBytes("khasra_document"));
                projectsitedetail.setKhasra_documentContentType(row.getString("khasra_document_content_type"));

                projectsitedetail.setLiesunder_mc(row.getBool("liesunder_mc"));
                projectsitedetail.setDistance_from_mc(row.getInt("distance_from_mc"));
                projectsitedetail.setIslocated_in_urban(row.getBool("islocated_in_urban"));
                projectsitedetail.setRevenu_shajra_document(row.getBytes("revenu_shajra_document"));
                projectsitedetail.setRevenu_shajra_documentContentType(row.getString("revenu_shajra_document_content_type"));

                projectsitedetail.setJamabandi(row.getBytes("jamabandi"));
                projectsitedetail.setJamabandiContentType(row.getString("jamabandi_content_type"));

                projectsitedetail.setNonencumbrance_certificate(row.getBytes("nonencumbrance_certificate"));
                projectsitedetail.setNonencumbrance_certificateContentType(row.getString("nonencumbrance_certificate_content_type"));

                projectsitedetail.setTotalproposedprojectarea(row.getDecimal("totalproposedprojectarea"));
                projectsitedetail.setProposedbuilt_up_area(row.getDecimal("proposedbuilt_up_area"));
                projectsitedetail.setCertifiedownership(row.getBool("certifiedownership"));
                projectsitedetail.setOwnership_document(row.getBytes("ownership_document"));
                projectsitedetail.setOwnership_documentContentType(row.getString("ownership_document_content_type"));

                projectsitedetail.setLeaseapplicable(row.getBool("leaseapplicable"));
                projectsitedetail.setLease_document(row.getBytes("lease_document"));
                projectsitedetail.setLease_documentContentType(row.getString("lease_document_content_type"));

                projectsitedetail.setLandagreementapplicable(row.getBool("landagreementapplicable"));
                projectsitedetail.setLandagreement_document(row.getBytes("landagreement_document"));
                projectsitedetail.setLandagreement_documentContentType(row.getString("landagreement_document_content_type"));

                projectsitedetail.setSitelayoutplan(row.getBytes("sitelayoutplan"));
                projectsitedetail.setSitelayoutplanContentType(row.getString("sitelayoutplan_content_type"));

                projectsitedetail.setLocationplan(row.getBytes("locationplan"));
                projectsitedetail.setLocationplanContentType(row.getString("locationplan_content_type"));

                projectsitedetail.setLinearstripplan(row.getBytes("linearstripplan"));
                projectsitedetail.setLinearstripplanContentType(row.getString("linearstripplan_content_type"));

                projectsitedetail.setConnectingroad(row.getUUID("connectingroad"));
                projectsitedetail.setIntersectiondistance(row.getBool("intersectiondistance"));
                projectsitedetail.setRailwaydistance(row.getBool("railwaydistance"));
                projectsitedetail.setConfirmitylanduse(row.getBool("confirmitylanduse"));
                projectsitedetail.setLandzoneuse_type(row.getUUID("landzoneuse_type"));
                projectsitedetail.setSitesituated_document(row.getBytes("sitesituated_document"));
                projectsitedetail.setSitesituated_documentContentType(row.getString("sitesituated_document_content_type"));

                projectsitedetail.setBuildingexisted(row.getUUID("buildingexisted"));
                projectsitedetail.setExisting_building_applicable(row.getBool("existing_building_applicable"));
                projectsitedetail.setBuildingplan_document(row.getBytes("buildingplan_document"));
                projectsitedetail.setBuildingplan_documentContentType(row.getString("buildingplan_document_content_type"));

                projectsitedetail.setSite_situated_in_controlled_area(row.getBool("site_situated_in_controlled_area"));
                projectsitedetail.setControlledarea_document(row.getBytes("controlledarea_document"));
                projectsitedetail.setControlledarea_documentContentType(row.getString("controlledarea_document_content_type"));

                return projectsitedetail;
            }
        ).forEach(projectsitedetailsList::add);
        return projectsitedetailsList;
    }

    public Projectsitedetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectsitedetail save(Projectsitedetail projectsitedetail) {
        if (projectsitedetail.getId() == null) {
            projectsitedetail.setId(UUID.randomUUID());
        }
        mapper.save(projectsitedetail);
        return projectsitedetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
