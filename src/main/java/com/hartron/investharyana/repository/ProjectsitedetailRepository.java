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
                projectsitedetail.setLiesunder_mc(row.getBool("liesunder_mc"));
                projectsitedetail.setDistance_from_mc(row.getInt("distance_from_mc"));
                projectsitedetail.setIslocated_in_urban(row.getBool("islocated_in_urban"));
                projectsitedetail.setTotalproposedprojectarea(row.getDecimal("totalproposedprojectarea"));
                projectsitedetail.setProposedbuilt_up_area(row.getDecimal("proposedbuilt_up_area"));
                projectsitedetail.setCertifiedownership(row.getBool("certifiedownership"));
                projectsitedetail.setLeaseapplicable(row.getBool("leaseapplicable"));
                projectsitedetail.setLandagreementapplicable(row.getBool("landagreementapplicable"));
                projectsitedetail.setIntersectiondistance(row.getBool("intersectiondistance"));
                projectsitedetail.setRailwaydistance(row.getBool("railwaydistance"));
                projectsitedetail.setConfirmitylanduse(row.getBool("confirmitylanduse"));
                projectsitedetail.setExisting_building_applicable(row.getBool("existing_building_applicable"));
                projectsitedetail.setSite_situated_in_controlled_area(row.getBool("site_situated_in_controlled_area"));
                projectsitedetail.setKhasra_document(row.getString("khasra_document"));
                projectsitedetail.setRevenu_shajra_document(row.getString("revenu_shajra_document"));
                projectsitedetail.setJamabandi(row.getString("jamabandi"));
                projectsitedetail.setNonencumbrance_certificate(row.getString("nonencumbrance_certificate"));
                projectsitedetail.setOwnership_document(row.getString("ownership_document"));
                projectsitedetail.setLease_document(row.getString("lease_document"));
                projectsitedetail.setLandagreement_document(row.getString("landagreement_document"));
                projectsitedetail.setSitelayoutplan(row.getString("sitelayoutplan"));
                projectsitedetail.setLocationplan(row.getString("locationplan"));
                projectsitedetail.setLinearstripplan(row.getString("linearstripplan"));
                projectsitedetail.setSitesituated_document(row.getString("sitesituated_document"));
                projectsitedetail.setControlledarea_document(row.getString("controlledarea_document"));
                projectsitedetail.setBuildingexisted(row.getString("buildingexisted"));
                projectsitedetail.setDistrict(row.getString("district"));
                projectsitedetail.setBlock(row.getString("block"));
                projectsitedetail.setCity_town_village(row.getString("city_town_village"));
                projectsitedetail.setConnectingroad(row.getString("connectingroad"));
                projectsitedetail.setLandzoneuse_type(row.getString("landzoneuse_type"));
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
