package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectsitedetail;
import com.hartron.investharyana.repository.ProjectsitedetailRepository;
import com.hartron.investharyana.service.ProjectsitedetailService;
import com.hartron.investharyana.service.dto.ProjectsitedetailDTO;
import com.hartron.investharyana.service.mapper.ProjectsitedetailMapper;
import com.hartron.investharyana.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectsitedetailResource REST controller.
 *
 * @see ProjectsitedetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectsitedetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_SITEADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SITEADDRESS = "BBBBBBBBBB";

    private static final UUID DEFAULT_DISTRICT = UUID.randomUUID();
    private static final UUID UPDATED_DISTRICT = UUID.randomUUID();

    private static final UUID DEFAULT_BLOCK = UUID.randomUUID();
    private static final UUID UPDATED_BLOCK = UUID.randomUUID();

    private static final UUID DEFAULT_CITY_TOWN_VILLAGE = UUID.randomUUID();
    private static final UUID UPDATED_CITY_TOWN_VILLAGE = UUID.randomUUID();

    private static final UUID DEFAULT_TEHSIL_SUBTEHSIL = UUID.randomUUID();
    private static final UUID UPDATED_TEHSIL_SUBTEHSIL = UUID.randomUUID();

    private static final Boolean DEFAULT_MULTYVILLAGEINVOLVED = false;
    private static final Boolean UPDATED_MULTYVILLAGEINVOLVED = true;

    private static final String DEFAULT_VILLAGEINVOLVED = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGEINVOLVED = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FALLS_IN_ARAVALLI = false;
    private static final Boolean UPDATED_FALLS_IN_ARAVALLI = true;

    private static final Boolean DEFAULT_ISLANDPROCURED = false;
    private static final Boolean UPDATED_ISLANDPROCURED = true;

    private static final Boolean DEFAULT_ALLOTEDBYHSIIDC = false;
    private static final Boolean UPDATED_ALLOTEDBYHSIIDC = true;

    private static final String DEFAULT_ESTATE = "AAAAAAAAAA";
    private static final String UPDATED_ESTATE = "BBBBBBBBBB";

    private static final String DEFAULT_CLUSTER = "AAAAAAAAAA";
    private static final String UPDATED_CLUSTER = "BBBBBBBBBB";

    private static final String DEFAULT_PHASE = "AAAAAAAAAA";
    private static final String UPDATED_PHASE = "BBBBBBBBBB";

    private static final String DEFAULT_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_SECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_PLOTNO = "AAAAAAAAAA";
    private static final String UPDATED_PLOTNO = "BBBBBBBBBB";

    private static final String DEFAULT_HADBASTNO = "AAAAAAAAAA";
    private static final String UPDATED_HADBASTNO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LIESUNDER_MC = false;
    private static final Boolean UPDATED_LIESUNDER_MC = true;

    private static final Integer DEFAULT_DISTANCE_FROM_MC = 1;
    private static final Integer UPDATED_DISTANCE_FROM_MC = 2;

    private static final Boolean DEFAULT_ISLOCATED_IN_URBAN = false;
    private static final Boolean UPDATED_ISLOCATED_IN_URBAN = true;

    private static final BigDecimal DEFAULT_TOTALPROPOSEDPROJECTAREA = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALPROPOSEDPROJECTAREA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PROPOSEDBUILT_UP_AREA = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROPOSEDBUILT_UP_AREA = new BigDecimal(2);

    private static final Boolean DEFAULT_CERTIFIEDOWNERSHIP = false;
    private static final Boolean UPDATED_CERTIFIEDOWNERSHIP = true;

    private static final Boolean DEFAULT_LEASEAPPLICABLE = false;
    private static final Boolean UPDATED_LEASEAPPLICABLE = true;

    private static final Boolean DEFAULT_LANDAGREEMENTAPPLICABLE = false;
    private static final Boolean UPDATED_LANDAGREEMENTAPPLICABLE = true;

    private static final UUID DEFAULT_CONNECTINGROAD = UUID.randomUUID();
    private static final UUID UPDATED_CONNECTINGROAD = UUID.randomUUID();

    private static final Boolean DEFAULT_INTERSECTIONDISTANCE = false;
    private static final Boolean UPDATED_INTERSECTIONDISTANCE = true;

    private static final Boolean DEFAULT_RAILWAYDISTANCE = false;
    private static final Boolean UPDATED_RAILWAYDISTANCE = true;

    private static final Boolean DEFAULT_CONFIRMITYLANDUSE = false;
    private static final Boolean UPDATED_CONFIRMITYLANDUSE = true;

    private static final UUID DEFAULT_LANDZONEUSE_TYPE = UUID.randomUUID();
    private static final UUID UPDATED_LANDZONEUSE_TYPE = UUID.randomUUID();

    private static final UUID DEFAULT_BUILDINGEXISTED = UUID.randomUUID();
    private static final UUID UPDATED_BUILDINGEXISTED = UUID.randomUUID();

    private static final Boolean DEFAULT_EXISTING_BUILDING_APPLICABLE = false;
    private static final Boolean UPDATED_EXISTING_BUILDING_APPLICABLE = true;

    private static final Boolean DEFAULT_SITE_SITUATED_IN_CONTROLLED_AREA = false;
    private static final Boolean UPDATED_SITE_SITUATED_IN_CONTROLLED_AREA = true;

    private static final String DEFAULT_KHASRA_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_KHASRA_DOCUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_REVENU_SHAJRA_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_REVENU_SHAJRA_DOCUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_JAMABANDI = "AAAAAAAAAA";
    private static final String UPDATED_JAMABANDI = "BBBBBBBBBB";

    private static final String DEFAULT_NONENCUMBRANCE_CERTIFICATE = "AAAAAAAAAA";
    private static final String UPDATED_NONENCUMBRANCE_CERTIFICATE = "BBBBBBBBBB";

    private static final String DEFAULT_OWNERSHIP_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_OWNERSHIP_DOCUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_LEASE_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_LEASE_DOCUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_LANDAGREEMENT_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_LANDAGREEMENT_DOCUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_SITELAYOUTPLAN = "AAAAAAAAAA";
    private static final String UPDATED_SITELAYOUTPLAN = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATIONPLAN = "AAAAAAAAAA";
    private static final String UPDATED_LOCATIONPLAN = "BBBBBBBBBB";

    private static final String DEFAULT_LINEARSTRIPPLAN = "AAAAAAAAAA";
    private static final String UPDATED_LINEARSTRIPPLAN = "BBBBBBBBBB";

    private static final String DEFAULT_SITESITUATED_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_SITESITUATED_DOCUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTROLLEDAREA_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTROLLEDAREA_DOCUMENT = "BBBBBBBBBB";

    @Autowired
    private ProjectsitedetailRepository projectsitedetailRepository;

    @Autowired
    private ProjectsitedetailMapper projectsitedetailMapper;

    @Autowired
    private ProjectsitedetailService projectsitedetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectsitedetailMockMvc;

    private Projectsitedetail projectsitedetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectsitedetailResource projectsitedetailResource = new ProjectsitedetailResource(projectsitedetailService);
        this.restProjectsitedetailMockMvc = MockMvcBuilders.standaloneSetup(projectsitedetailResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectsitedetail createEntity() {
        Projectsitedetail projectsitedetail = new Projectsitedetail()
                .projectid(DEFAULT_PROJECTID)
                .siteaddress(DEFAULT_SITEADDRESS)
                .district(DEFAULT_DISTRICT)
                .block(DEFAULT_BLOCK)
                .city_town_village(DEFAULT_CITY_TOWN_VILLAGE)
                .tehsil_subtehsil(DEFAULT_TEHSIL_SUBTEHSIL)
                .multyvillageinvolved(DEFAULT_MULTYVILLAGEINVOLVED)
                .villageinvolved(DEFAULT_VILLAGEINVOLVED)
                .falls_in_aravalli(DEFAULT_FALLS_IN_ARAVALLI)
                .islandprocured(DEFAULT_ISLANDPROCURED)
                .allotedbyhsiidc(DEFAULT_ALLOTEDBYHSIIDC)
                .estate(DEFAULT_ESTATE)
                .cluster(DEFAULT_CLUSTER)
                .phase(DEFAULT_PHASE)
                .sector(DEFAULT_SECTOR)
                .plotno(DEFAULT_PLOTNO)
                .hadbastno(DEFAULT_HADBASTNO)
                .liesunder_mc(DEFAULT_LIESUNDER_MC)
                .distance_from_mc(DEFAULT_DISTANCE_FROM_MC)
                .islocated_in_urban(DEFAULT_ISLOCATED_IN_URBAN)
                .totalproposedprojectarea(DEFAULT_TOTALPROPOSEDPROJECTAREA)
                .proposedbuilt_up_area(DEFAULT_PROPOSEDBUILT_UP_AREA)
                .certifiedownership(DEFAULT_CERTIFIEDOWNERSHIP)
                .leaseapplicable(DEFAULT_LEASEAPPLICABLE)
                .landagreementapplicable(DEFAULT_LANDAGREEMENTAPPLICABLE)
                .connectingroad(DEFAULT_CONNECTINGROAD)
                .intersectiondistance(DEFAULT_INTERSECTIONDISTANCE)
                .railwaydistance(DEFAULT_RAILWAYDISTANCE)
                .confirmitylanduse(DEFAULT_CONFIRMITYLANDUSE)
                .landzoneuse_type(DEFAULT_LANDZONEUSE_TYPE)
                .buildingexisted(DEFAULT_BUILDINGEXISTED)
                .existing_building_applicable(DEFAULT_EXISTING_BUILDING_APPLICABLE)
                .site_situated_in_controlled_area(DEFAULT_SITE_SITUATED_IN_CONTROLLED_AREA)
                .khasra_document(DEFAULT_KHASRA_DOCUMENT)
                .revenu_shajra_document(DEFAULT_REVENU_SHAJRA_DOCUMENT)
                .jamabandi(DEFAULT_JAMABANDI)
                .nonencumbrance_certificate(DEFAULT_NONENCUMBRANCE_CERTIFICATE)
                .ownership_document(DEFAULT_OWNERSHIP_DOCUMENT)
                .lease_document(DEFAULT_LEASE_DOCUMENT)
                .landagreement_document(DEFAULT_LANDAGREEMENT_DOCUMENT)
                .sitelayoutplan(DEFAULT_SITELAYOUTPLAN)
                .locationplan(DEFAULT_LOCATIONPLAN)
                .linearstripplan(DEFAULT_LINEARSTRIPPLAN)
                .sitesituated_document(DEFAULT_SITESITUATED_DOCUMENT)
                .controlledarea_document(DEFAULT_CONTROLLEDAREA_DOCUMENT);
        return projectsitedetail;
    }

    @Before
    public void initTest() {
        projectsitedetailRepository.deleteAll();
        projectsitedetail = createEntity();
    }

    @Test
    public void createProjectsitedetail() throws Exception {
        int databaseSizeBeforeCreate = projectsitedetailRepository.findAll().size();

        // Create the Projectsitedetail
        ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(projectsitedetail);

        restProjectsitedetailMockMvc.perform(post("/api/projectsitedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectsitedetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectsitedetail in the database
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeCreate + 1);
        Projectsitedetail testProjectsitedetail = projectsitedetailList.get(projectsitedetailList.size() - 1);
        assertThat(testProjectsitedetail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectsitedetail.getSiteaddress()).isEqualTo(DEFAULT_SITEADDRESS);
        assertThat(testProjectsitedetail.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testProjectsitedetail.getBlock()).isEqualTo(DEFAULT_BLOCK);
        assertThat(testProjectsitedetail.getCity_town_village()).isEqualTo(DEFAULT_CITY_TOWN_VILLAGE);
        assertThat(testProjectsitedetail.getTehsil_subtehsil()).isEqualTo(DEFAULT_TEHSIL_SUBTEHSIL);
        assertThat(testProjectsitedetail.isMultyvillageinvolved()).isEqualTo(DEFAULT_MULTYVILLAGEINVOLVED);
        assertThat(testProjectsitedetail.getVillageinvolved()).isEqualTo(DEFAULT_VILLAGEINVOLVED);
        assertThat(testProjectsitedetail.isFalls_in_aravalli()).isEqualTo(DEFAULT_FALLS_IN_ARAVALLI);
        assertThat(testProjectsitedetail.isIslandprocured()).isEqualTo(DEFAULT_ISLANDPROCURED);
        assertThat(testProjectsitedetail.isAllotedbyhsiidc()).isEqualTo(DEFAULT_ALLOTEDBYHSIIDC);
        assertThat(testProjectsitedetail.getEstate()).isEqualTo(DEFAULT_ESTATE);
        assertThat(testProjectsitedetail.getCluster()).isEqualTo(DEFAULT_CLUSTER);
        assertThat(testProjectsitedetail.getPhase()).isEqualTo(DEFAULT_PHASE);
        assertThat(testProjectsitedetail.getSector()).isEqualTo(DEFAULT_SECTOR);
        assertThat(testProjectsitedetail.getPlotno()).isEqualTo(DEFAULT_PLOTNO);
        assertThat(testProjectsitedetail.getHadbastno()).isEqualTo(DEFAULT_HADBASTNO);
        assertThat(testProjectsitedetail.isLiesunder_mc()).isEqualTo(DEFAULT_LIESUNDER_MC);
        assertThat(testProjectsitedetail.getDistance_from_mc()).isEqualTo(DEFAULT_DISTANCE_FROM_MC);
        assertThat(testProjectsitedetail.isIslocated_in_urban()).isEqualTo(DEFAULT_ISLOCATED_IN_URBAN);
        assertThat(testProjectsitedetail.getTotalproposedprojectarea()).isEqualTo(DEFAULT_TOTALPROPOSEDPROJECTAREA);
        assertThat(testProjectsitedetail.getProposedbuilt_up_area()).isEqualTo(DEFAULT_PROPOSEDBUILT_UP_AREA);
        assertThat(testProjectsitedetail.isCertifiedownership()).isEqualTo(DEFAULT_CERTIFIEDOWNERSHIP);
        assertThat(testProjectsitedetail.isLeaseapplicable()).isEqualTo(DEFAULT_LEASEAPPLICABLE);
        assertThat(testProjectsitedetail.isLandagreementapplicable()).isEqualTo(DEFAULT_LANDAGREEMENTAPPLICABLE);
        assertThat(testProjectsitedetail.getConnectingroad()).isEqualTo(DEFAULT_CONNECTINGROAD);
        assertThat(testProjectsitedetail.isIntersectiondistance()).isEqualTo(DEFAULT_INTERSECTIONDISTANCE);
        assertThat(testProjectsitedetail.isRailwaydistance()).isEqualTo(DEFAULT_RAILWAYDISTANCE);
        assertThat(testProjectsitedetail.isConfirmitylanduse()).isEqualTo(DEFAULT_CONFIRMITYLANDUSE);
        assertThat(testProjectsitedetail.getLandzoneuse_type()).isEqualTo(DEFAULT_LANDZONEUSE_TYPE);
        assertThat(testProjectsitedetail.getBuildingexisted()).isEqualTo(DEFAULT_BUILDINGEXISTED);
        assertThat(testProjectsitedetail.isExisting_building_applicable()).isEqualTo(DEFAULT_EXISTING_BUILDING_APPLICABLE);
        assertThat(testProjectsitedetail.isSite_situated_in_controlled_area()).isEqualTo(DEFAULT_SITE_SITUATED_IN_CONTROLLED_AREA);
        assertThat(testProjectsitedetail.getKhasra_document()).isEqualTo(DEFAULT_KHASRA_DOCUMENT);
        assertThat(testProjectsitedetail.getRevenu_shajra_document()).isEqualTo(DEFAULT_REVENU_SHAJRA_DOCUMENT);
        assertThat(testProjectsitedetail.getJamabandi()).isEqualTo(DEFAULT_JAMABANDI);
        assertThat(testProjectsitedetail.getNonencumbrance_certificate()).isEqualTo(DEFAULT_NONENCUMBRANCE_CERTIFICATE);
        assertThat(testProjectsitedetail.getOwnership_document()).isEqualTo(DEFAULT_OWNERSHIP_DOCUMENT);
        assertThat(testProjectsitedetail.getLease_document()).isEqualTo(DEFAULT_LEASE_DOCUMENT);
        assertThat(testProjectsitedetail.getLandagreement_document()).isEqualTo(DEFAULT_LANDAGREEMENT_DOCUMENT);
        assertThat(testProjectsitedetail.getSitelayoutplan()).isEqualTo(DEFAULT_SITELAYOUTPLAN);
        assertThat(testProjectsitedetail.getLocationplan()).isEqualTo(DEFAULT_LOCATIONPLAN);
        assertThat(testProjectsitedetail.getLinearstripplan()).isEqualTo(DEFAULT_LINEARSTRIPPLAN);
        assertThat(testProjectsitedetail.getSitesituated_document()).isEqualTo(DEFAULT_SITESITUATED_DOCUMENT);
        assertThat(testProjectsitedetail.getControlledarea_document()).isEqualTo(DEFAULT_CONTROLLEDAREA_DOCUMENT);
    }

    @Test
    public void createProjectsitedetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectsitedetailRepository.findAll().size();

        // Create the Projectsitedetail with an existing ID
        Projectsitedetail existingProjectsitedetail = new Projectsitedetail();
        existingProjectsitedetail.setId(UUID.randomUUID());
        ProjectsitedetailDTO existingProjectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(existingProjectsitedetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectsitedetailMockMvc.perform(post("/api/projectsitedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectsitedetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectsitedetails() throws Exception {
        // Initialize the database
        projectsitedetailRepository.save(projectsitedetail);

        // Get all the projectsitedetailList
        restProjectsitedetailMockMvc.perform(get("/api/projectsitedetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectsitedetail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].siteaddress").value(hasItem(DEFAULT_SITEADDRESS.toString())))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT.toString())))
            .andExpect(jsonPath("$.[*].block").value(hasItem(DEFAULT_BLOCK.toString())))
            .andExpect(jsonPath("$.[*].city_town_village").value(hasItem(DEFAULT_CITY_TOWN_VILLAGE.toString())))
            .andExpect(jsonPath("$.[*].tehsil_subtehsil").value(hasItem(DEFAULT_TEHSIL_SUBTEHSIL.toString())))
            .andExpect(jsonPath("$.[*].multyvillageinvolved").value(hasItem(DEFAULT_MULTYVILLAGEINVOLVED.booleanValue())))
            .andExpect(jsonPath("$.[*].villageinvolved").value(hasItem(DEFAULT_VILLAGEINVOLVED.toString())))
            .andExpect(jsonPath("$.[*].falls_in_aravalli").value(hasItem(DEFAULT_FALLS_IN_ARAVALLI.booleanValue())))
            .andExpect(jsonPath("$.[*].islandprocured").value(hasItem(DEFAULT_ISLANDPROCURED.booleanValue())))
            .andExpect(jsonPath("$.[*].allotedbyhsiidc").value(hasItem(DEFAULT_ALLOTEDBYHSIIDC.booleanValue())))
            .andExpect(jsonPath("$.[*].estate").value(hasItem(DEFAULT_ESTATE.toString())))
            .andExpect(jsonPath("$.[*].cluster").value(hasItem(DEFAULT_CLUSTER.toString())))
            .andExpect(jsonPath("$.[*].phase").value(hasItem(DEFAULT_PHASE.toString())))
            .andExpect(jsonPath("$.[*].sector").value(hasItem(DEFAULT_SECTOR.toString())))
            .andExpect(jsonPath("$.[*].plotno").value(hasItem(DEFAULT_PLOTNO.toString())))
            .andExpect(jsonPath("$.[*].hadbastno").value(hasItem(DEFAULT_HADBASTNO.toString())))
            .andExpect(jsonPath("$.[*].liesunder_mc").value(hasItem(DEFAULT_LIESUNDER_MC.booleanValue())))
            .andExpect(jsonPath("$.[*].distance_from_mc").value(hasItem(DEFAULT_DISTANCE_FROM_MC)))
            .andExpect(jsonPath("$.[*].islocated_in_urban").value(hasItem(DEFAULT_ISLOCATED_IN_URBAN.booleanValue())))
            .andExpect(jsonPath("$.[*].totalproposedprojectarea").value(hasItem(DEFAULT_TOTALPROPOSEDPROJECTAREA.intValue())))
            .andExpect(jsonPath("$.[*].proposedbuilt_up_area").value(hasItem(DEFAULT_PROPOSEDBUILT_UP_AREA.intValue())))
            .andExpect(jsonPath("$.[*].certifiedownership").value(hasItem(DEFAULT_CERTIFIEDOWNERSHIP.booleanValue())))
            .andExpect(jsonPath("$.[*].leaseapplicable").value(hasItem(DEFAULT_LEASEAPPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].landagreementapplicable").value(hasItem(DEFAULT_LANDAGREEMENTAPPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].connectingroad").value(hasItem(DEFAULT_CONNECTINGROAD.toString())))
            .andExpect(jsonPath("$.[*].intersectiondistance").value(hasItem(DEFAULT_INTERSECTIONDISTANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].railwaydistance").value(hasItem(DEFAULT_RAILWAYDISTANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].confirmitylanduse").value(hasItem(DEFAULT_CONFIRMITYLANDUSE.booleanValue())))
            .andExpect(jsonPath("$.[*].landzoneuse_type").value(hasItem(DEFAULT_LANDZONEUSE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].buildingexisted").value(hasItem(DEFAULT_BUILDINGEXISTED.toString())))
            .andExpect(jsonPath("$.[*].existing_building_applicable").value(hasItem(DEFAULT_EXISTING_BUILDING_APPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].site_situated_in_controlled_area").value(hasItem(DEFAULT_SITE_SITUATED_IN_CONTROLLED_AREA.booleanValue())))
            .andExpect(jsonPath("$.[*].khasra_document").value(hasItem(DEFAULT_KHASRA_DOCUMENT.toString())))
            .andExpect(jsonPath("$.[*].revenu_shajra_document").value(hasItem(DEFAULT_REVENU_SHAJRA_DOCUMENT.toString())))
            .andExpect(jsonPath("$.[*].jamabandi").value(hasItem(DEFAULT_JAMABANDI.toString())))
            .andExpect(jsonPath("$.[*].nonencumbrance_certificate").value(hasItem(DEFAULT_NONENCUMBRANCE_CERTIFICATE.toString())))
            .andExpect(jsonPath("$.[*].ownership_document").value(hasItem(DEFAULT_OWNERSHIP_DOCUMENT.toString())))
            .andExpect(jsonPath("$.[*].lease_document").value(hasItem(DEFAULT_LEASE_DOCUMENT.toString())))
            .andExpect(jsonPath("$.[*].landagreement_document").value(hasItem(DEFAULT_LANDAGREEMENT_DOCUMENT.toString())))
            .andExpect(jsonPath("$.[*].sitelayoutplan").value(hasItem(DEFAULT_SITELAYOUTPLAN.toString())))
            .andExpect(jsonPath("$.[*].locationplan").value(hasItem(DEFAULT_LOCATIONPLAN.toString())))
            .andExpect(jsonPath("$.[*].linearstripplan").value(hasItem(DEFAULT_LINEARSTRIPPLAN.toString())))
            .andExpect(jsonPath("$.[*].sitesituated_document").value(hasItem(DEFAULT_SITESITUATED_DOCUMENT.toString())))
            .andExpect(jsonPath("$.[*].controlledarea_document").value(hasItem(DEFAULT_CONTROLLEDAREA_DOCUMENT.toString())));
    }

    @Test
    public void getProjectsitedetail() throws Exception {
        // Initialize the database
        projectsitedetailRepository.save(projectsitedetail);

        // Get the projectsitedetail
        restProjectsitedetailMockMvc.perform(get("/api/projectsitedetails/{id}", projectsitedetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectsitedetail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.siteaddress").value(DEFAULT_SITEADDRESS.toString()))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT.toString()))
            .andExpect(jsonPath("$.block").value(DEFAULT_BLOCK.toString()))
            .andExpect(jsonPath("$.city_town_village").value(DEFAULT_CITY_TOWN_VILLAGE.toString()))
            .andExpect(jsonPath("$.tehsil_subtehsil").value(DEFAULT_TEHSIL_SUBTEHSIL.toString()))
            .andExpect(jsonPath("$.multyvillageinvolved").value(DEFAULT_MULTYVILLAGEINVOLVED.booleanValue()))
            .andExpect(jsonPath("$.villageinvolved").value(DEFAULT_VILLAGEINVOLVED.toString()))
            .andExpect(jsonPath("$.falls_in_aravalli").value(DEFAULT_FALLS_IN_ARAVALLI.booleanValue()))
            .andExpect(jsonPath("$.islandprocured").value(DEFAULT_ISLANDPROCURED.booleanValue()))
            .andExpect(jsonPath("$.allotedbyhsiidc").value(DEFAULT_ALLOTEDBYHSIIDC.booleanValue()))
            .andExpect(jsonPath("$.estate").value(DEFAULT_ESTATE.toString()))
            .andExpect(jsonPath("$.cluster").value(DEFAULT_CLUSTER.toString()))
            .andExpect(jsonPath("$.phase").value(DEFAULT_PHASE.toString()))
            .andExpect(jsonPath("$.sector").value(DEFAULT_SECTOR.toString()))
            .andExpect(jsonPath("$.plotno").value(DEFAULT_PLOTNO.toString()))
            .andExpect(jsonPath("$.hadbastno").value(DEFAULT_HADBASTNO.toString()))
            .andExpect(jsonPath("$.liesunder_mc").value(DEFAULT_LIESUNDER_MC.booleanValue()))
            .andExpect(jsonPath("$.distance_from_mc").value(DEFAULT_DISTANCE_FROM_MC))
            .andExpect(jsonPath("$.islocated_in_urban").value(DEFAULT_ISLOCATED_IN_URBAN.booleanValue()))
            .andExpect(jsonPath("$.totalproposedprojectarea").value(DEFAULT_TOTALPROPOSEDPROJECTAREA.intValue()))
            .andExpect(jsonPath("$.proposedbuilt_up_area").value(DEFAULT_PROPOSEDBUILT_UP_AREA.intValue()))
            .andExpect(jsonPath("$.certifiedownership").value(DEFAULT_CERTIFIEDOWNERSHIP.booleanValue()))
            .andExpect(jsonPath("$.leaseapplicable").value(DEFAULT_LEASEAPPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.landagreementapplicable").value(DEFAULT_LANDAGREEMENTAPPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.connectingroad").value(DEFAULT_CONNECTINGROAD.toString()))
            .andExpect(jsonPath("$.intersectiondistance").value(DEFAULT_INTERSECTIONDISTANCE.booleanValue()))
            .andExpect(jsonPath("$.railwaydistance").value(DEFAULT_RAILWAYDISTANCE.booleanValue()))
            .andExpect(jsonPath("$.confirmitylanduse").value(DEFAULT_CONFIRMITYLANDUSE.booleanValue()))
            .andExpect(jsonPath("$.landzoneuse_type").value(DEFAULT_LANDZONEUSE_TYPE.toString()))
            .andExpect(jsonPath("$.buildingexisted").value(DEFAULT_BUILDINGEXISTED.toString()))
            .andExpect(jsonPath("$.existing_building_applicable").value(DEFAULT_EXISTING_BUILDING_APPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.site_situated_in_controlled_area").value(DEFAULT_SITE_SITUATED_IN_CONTROLLED_AREA.booleanValue()))
            .andExpect(jsonPath("$.khasra_document").value(DEFAULT_KHASRA_DOCUMENT.toString()))
            .andExpect(jsonPath("$.revenu_shajra_document").value(DEFAULT_REVENU_SHAJRA_DOCUMENT.toString()))
            .andExpect(jsonPath("$.jamabandi").value(DEFAULT_JAMABANDI.toString()))
            .andExpect(jsonPath("$.nonencumbrance_certificate").value(DEFAULT_NONENCUMBRANCE_CERTIFICATE.toString()))
            .andExpect(jsonPath("$.ownership_document").value(DEFAULT_OWNERSHIP_DOCUMENT.toString()))
            .andExpect(jsonPath("$.lease_document").value(DEFAULT_LEASE_DOCUMENT.toString()))
            .andExpect(jsonPath("$.landagreement_document").value(DEFAULT_LANDAGREEMENT_DOCUMENT.toString()))
            .andExpect(jsonPath("$.sitelayoutplan").value(DEFAULT_SITELAYOUTPLAN.toString()))
            .andExpect(jsonPath("$.locationplan").value(DEFAULT_LOCATIONPLAN.toString()))
            .andExpect(jsonPath("$.linearstripplan").value(DEFAULT_LINEARSTRIPPLAN.toString()))
            .andExpect(jsonPath("$.sitesituated_document").value(DEFAULT_SITESITUATED_DOCUMENT.toString()))
            .andExpect(jsonPath("$.controlledarea_document").value(DEFAULT_CONTROLLEDAREA_DOCUMENT.toString()));
    }

    @Test
    public void getNonExistingProjectsitedetail() throws Exception {
        // Get the projectsitedetail
        restProjectsitedetailMockMvc.perform(get("/api/projectsitedetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectsitedetail() throws Exception {
        // Initialize the database
        projectsitedetailRepository.save(projectsitedetail);
        int databaseSizeBeforeUpdate = projectsitedetailRepository.findAll().size();

        // Update the projectsitedetail
        Projectsitedetail updatedProjectsitedetail = projectsitedetailRepository.findOne(projectsitedetail.getId());
        updatedProjectsitedetail
                .projectid(UPDATED_PROJECTID)
                .siteaddress(UPDATED_SITEADDRESS)
                .district(UPDATED_DISTRICT)
                .block(UPDATED_BLOCK)
                .city_town_village(UPDATED_CITY_TOWN_VILLAGE)
                .tehsil_subtehsil(UPDATED_TEHSIL_SUBTEHSIL)
                .multyvillageinvolved(UPDATED_MULTYVILLAGEINVOLVED)
                .villageinvolved(UPDATED_VILLAGEINVOLVED)
                .falls_in_aravalli(UPDATED_FALLS_IN_ARAVALLI)
                .islandprocured(UPDATED_ISLANDPROCURED)
                .allotedbyhsiidc(UPDATED_ALLOTEDBYHSIIDC)
                .estate(UPDATED_ESTATE)
                .cluster(UPDATED_CLUSTER)
                .phase(UPDATED_PHASE)
                .sector(UPDATED_SECTOR)
                .plotno(UPDATED_PLOTNO)
                .hadbastno(UPDATED_HADBASTNO)
                .liesunder_mc(UPDATED_LIESUNDER_MC)
                .distance_from_mc(UPDATED_DISTANCE_FROM_MC)
                .islocated_in_urban(UPDATED_ISLOCATED_IN_URBAN)
                .totalproposedprojectarea(UPDATED_TOTALPROPOSEDPROJECTAREA)
                .proposedbuilt_up_area(UPDATED_PROPOSEDBUILT_UP_AREA)
                .certifiedownership(UPDATED_CERTIFIEDOWNERSHIP)
                .leaseapplicable(UPDATED_LEASEAPPLICABLE)
                .landagreementapplicable(UPDATED_LANDAGREEMENTAPPLICABLE)
                .connectingroad(UPDATED_CONNECTINGROAD)
                .intersectiondistance(UPDATED_INTERSECTIONDISTANCE)
                .railwaydistance(UPDATED_RAILWAYDISTANCE)
                .confirmitylanduse(UPDATED_CONFIRMITYLANDUSE)
                .landzoneuse_type(UPDATED_LANDZONEUSE_TYPE)
                .buildingexisted(UPDATED_BUILDINGEXISTED)
                .existing_building_applicable(UPDATED_EXISTING_BUILDING_APPLICABLE)
                .site_situated_in_controlled_area(UPDATED_SITE_SITUATED_IN_CONTROLLED_AREA)
                .khasra_document(UPDATED_KHASRA_DOCUMENT)
                .revenu_shajra_document(UPDATED_REVENU_SHAJRA_DOCUMENT)
                .jamabandi(UPDATED_JAMABANDI)
                .nonencumbrance_certificate(UPDATED_NONENCUMBRANCE_CERTIFICATE)
                .ownership_document(UPDATED_OWNERSHIP_DOCUMENT)
                .lease_document(UPDATED_LEASE_DOCUMENT)
                .landagreement_document(UPDATED_LANDAGREEMENT_DOCUMENT)
                .sitelayoutplan(UPDATED_SITELAYOUTPLAN)
                .locationplan(UPDATED_LOCATIONPLAN)
                .linearstripplan(UPDATED_LINEARSTRIPPLAN)
                .sitesituated_document(UPDATED_SITESITUATED_DOCUMENT)
                .controlledarea_document(UPDATED_CONTROLLEDAREA_DOCUMENT);
        ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(updatedProjectsitedetail);

        restProjectsitedetailMockMvc.perform(put("/api/projectsitedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectsitedetailDTO)))
            .andExpect(status().isOk());

        // Validate the Projectsitedetail in the database
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeUpdate);
        Projectsitedetail testProjectsitedetail = projectsitedetailList.get(projectsitedetailList.size() - 1);
        assertThat(testProjectsitedetail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectsitedetail.getSiteaddress()).isEqualTo(UPDATED_SITEADDRESS);
        assertThat(testProjectsitedetail.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testProjectsitedetail.getBlock()).isEqualTo(UPDATED_BLOCK);
        assertThat(testProjectsitedetail.getCity_town_village()).isEqualTo(UPDATED_CITY_TOWN_VILLAGE);
        assertThat(testProjectsitedetail.getTehsil_subtehsil()).isEqualTo(UPDATED_TEHSIL_SUBTEHSIL);
        assertThat(testProjectsitedetail.isMultyvillageinvolved()).isEqualTo(UPDATED_MULTYVILLAGEINVOLVED);
        assertThat(testProjectsitedetail.getVillageinvolved()).isEqualTo(UPDATED_VILLAGEINVOLVED);
        assertThat(testProjectsitedetail.isFalls_in_aravalli()).isEqualTo(UPDATED_FALLS_IN_ARAVALLI);
        assertThat(testProjectsitedetail.isIslandprocured()).isEqualTo(UPDATED_ISLANDPROCURED);
        assertThat(testProjectsitedetail.isAllotedbyhsiidc()).isEqualTo(UPDATED_ALLOTEDBYHSIIDC);
        assertThat(testProjectsitedetail.getEstate()).isEqualTo(UPDATED_ESTATE);
        assertThat(testProjectsitedetail.getCluster()).isEqualTo(UPDATED_CLUSTER);
        assertThat(testProjectsitedetail.getPhase()).isEqualTo(UPDATED_PHASE);
        assertThat(testProjectsitedetail.getSector()).isEqualTo(UPDATED_SECTOR);
        assertThat(testProjectsitedetail.getPlotno()).isEqualTo(UPDATED_PLOTNO);
        assertThat(testProjectsitedetail.getHadbastno()).isEqualTo(UPDATED_HADBASTNO);
        assertThat(testProjectsitedetail.isLiesunder_mc()).isEqualTo(UPDATED_LIESUNDER_MC);
        assertThat(testProjectsitedetail.getDistance_from_mc()).isEqualTo(UPDATED_DISTANCE_FROM_MC);
        assertThat(testProjectsitedetail.isIslocated_in_urban()).isEqualTo(UPDATED_ISLOCATED_IN_URBAN);
        assertThat(testProjectsitedetail.getTotalproposedprojectarea()).isEqualTo(UPDATED_TOTALPROPOSEDPROJECTAREA);
        assertThat(testProjectsitedetail.getProposedbuilt_up_area()).isEqualTo(UPDATED_PROPOSEDBUILT_UP_AREA);
        assertThat(testProjectsitedetail.isCertifiedownership()).isEqualTo(UPDATED_CERTIFIEDOWNERSHIP);
        assertThat(testProjectsitedetail.isLeaseapplicable()).isEqualTo(UPDATED_LEASEAPPLICABLE);
        assertThat(testProjectsitedetail.isLandagreementapplicable()).isEqualTo(UPDATED_LANDAGREEMENTAPPLICABLE);
        assertThat(testProjectsitedetail.getConnectingroad()).isEqualTo(UPDATED_CONNECTINGROAD);
        assertThat(testProjectsitedetail.isIntersectiondistance()).isEqualTo(UPDATED_INTERSECTIONDISTANCE);
        assertThat(testProjectsitedetail.isRailwaydistance()).isEqualTo(UPDATED_RAILWAYDISTANCE);
        assertThat(testProjectsitedetail.isConfirmitylanduse()).isEqualTo(UPDATED_CONFIRMITYLANDUSE);
        assertThat(testProjectsitedetail.getLandzoneuse_type()).isEqualTo(UPDATED_LANDZONEUSE_TYPE);
        assertThat(testProjectsitedetail.getBuildingexisted()).isEqualTo(UPDATED_BUILDINGEXISTED);
        assertThat(testProjectsitedetail.isExisting_building_applicable()).isEqualTo(UPDATED_EXISTING_BUILDING_APPLICABLE);
        assertThat(testProjectsitedetail.isSite_situated_in_controlled_area()).isEqualTo(UPDATED_SITE_SITUATED_IN_CONTROLLED_AREA);
        assertThat(testProjectsitedetail.getKhasra_document()).isEqualTo(UPDATED_KHASRA_DOCUMENT);
        assertThat(testProjectsitedetail.getRevenu_shajra_document()).isEqualTo(UPDATED_REVENU_SHAJRA_DOCUMENT);
        assertThat(testProjectsitedetail.getJamabandi()).isEqualTo(UPDATED_JAMABANDI);
        assertThat(testProjectsitedetail.getNonencumbrance_certificate()).isEqualTo(UPDATED_NONENCUMBRANCE_CERTIFICATE);
        assertThat(testProjectsitedetail.getOwnership_document()).isEqualTo(UPDATED_OWNERSHIP_DOCUMENT);
        assertThat(testProjectsitedetail.getLease_document()).isEqualTo(UPDATED_LEASE_DOCUMENT);
        assertThat(testProjectsitedetail.getLandagreement_document()).isEqualTo(UPDATED_LANDAGREEMENT_DOCUMENT);
        assertThat(testProjectsitedetail.getSitelayoutplan()).isEqualTo(UPDATED_SITELAYOUTPLAN);
        assertThat(testProjectsitedetail.getLocationplan()).isEqualTo(UPDATED_LOCATIONPLAN);
        assertThat(testProjectsitedetail.getLinearstripplan()).isEqualTo(UPDATED_LINEARSTRIPPLAN);
        assertThat(testProjectsitedetail.getSitesituated_document()).isEqualTo(UPDATED_SITESITUATED_DOCUMENT);
        assertThat(testProjectsitedetail.getControlledarea_document()).isEqualTo(UPDATED_CONTROLLEDAREA_DOCUMENT);
    }

    @Test
    public void updateNonExistingProjectsitedetail() throws Exception {
        int databaseSizeBeforeUpdate = projectsitedetailRepository.findAll().size();

        // Create the Projectsitedetail
        ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(projectsitedetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectsitedetailMockMvc.perform(put("/api/projectsitedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectsitedetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectsitedetail in the database
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectsitedetail() throws Exception {
        // Initialize the database
        projectsitedetailRepository.save(projectsitedetail);
        int databaseSizeBeforeDelete = projectsitedetailRepository.findAll().size();

        // Get the projectsitedetail
        restProjectsitedetailMockMvc.perform(delete("/api/projectsitedetails/{id}", projectsitedetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectsitedetail> projectsitedetailList = projectsitedetailRepository.findAll();
        assertThat(projectsitedetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectsitedetail.class);
    }
}
