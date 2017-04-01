package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Project_finance_investment;
import com.hartron.investharyana.repository.Project_finance_investmentRepository;
import com.hartron.investharyana.service.Project_finance_investmentService;
import com.hartron.investharyana.service.dto.Project_finance_investmentDTO;
import com.hartron.investharyana.service.mapper.Project_finance_investmentMapper;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Project_finance_investmentResource REST controller.
 *
 * @see Project_finance_investmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Project_finance_investmentResourceIntTest extends AbstractCassandraTest {

    private static final BigDecimal DEFAULT_LAND_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_LAND_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BUILDING_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUILDING_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MACHINERY_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_MACHINERY_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MISC_ASSESTS = new BigDecimal(1);
    private static final BigDecimal UPDATED_MISC_ASSESTS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTAL_PROJECT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL_PROJECT_COST = new BigDecimal(2);

    private static final Boolean DEFAULT_ISFDI = false;
    private static final Boolean UPDATED_ISFDI = true;

    private static final BigDecimal DEFAULT_FDIVALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FDIVALUE = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_PROJECT_CONSTRUCTION_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PROJECT_CONSTRUCTION_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_COMMERCIAL_ACTIVITY_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_COMMERCIAL_ACTIVITY_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_FDI_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_FDI_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_FOREIGN_FUNDING_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_FOREIGN_FUNDING_SOURCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTALPURPOSEDEMPLOYMENT = 1;
    private static final Integer UPDATED_TOTALPURPOSEDEMPLOYMENT = 2;

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private Project_finance_investmentRepository project_finance_investmentRepository;

    @Autowired
    private Project_finance_investmentMapper project_finance_investmentMapper;

    @Autowired
    private Project_finance_investmentService project_finance_investmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProject_finance_investmentMockMvc;

    private Project_finance_investment project_finance_investment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Project_finance_investmentResource project_finance_investmentResource = new Project_finance_investmentResource(project_finance_investmentService);
        this.restProject_finance_investmentMockMvc = MockMvcBuilders.standaloneSetup(project_finance_investmentResource)
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
    public static Project_finance_investment createEntity() {
        Project_finance_investment project_finance_investment = new Project_finance_investment()
                .land_cost(DEFAULT_LAND_COST)
                .building_cost(DEFAULT_BUILDING_COST)
                .machinery_cost(DEFAULT_MACHINERY_COST)
                .misc_assests(DEFAULT_MISC_ASSESTS)
                .total_project_cost(DEFAULT_TOTAL_PROJECT_COST)
                .isfdi(DEFAULT_ISFDI)
                .fdivalue(DEFAULT_FDIVALUE)
                .project_construction_start_date(DEFAULT_PROJECT_CONSTRUCTION_START_DATE)
                .commercial_activity_start_date(DEFAULT_COMMERCIAL_ACTIVITY_START_DATE)
                .fdi_country(DEFAULT_FDI_COUNTRY)
                .foreign_funding_source(DEFAULT_FOREIGN_FUNDING_SOURCE)
                .totalpurposedemployment(DEFAULT_TOTALPURPOSEDEMPLOYMENT)
                .createdate(DEFAULT_CREATEDATE)
                .updatedate(DEFAULT_UPDATEDATE);
        return project_finance_investment;
    }

    @Before
    public void initTest() {
        project_finance_investmentRepository.deleteAll();
        project_finance_investment = createEntity();
    }

    @Test
    public void createProject_finance_investment() throws Exception {
        int databaseSizeBeforeCreate = project_finance_investmentRepository.findAll().size();

        // Create the Project_finance_investment
        Project_finance_investmentDTO project_finance_investmentDTO = project_finance_investmentMapper.project_finance_investmentToProject_finance_investmentDTO(project_finance_investment);

        restProject_finance_investmentMockMvc.perform(post("/api/project-finance-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_finance_investmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Project_finance_investment in the database
        List<Project_finance_investment> project_finance_investmentList = project_finance_investmentRepository.findAll();
        assertThat(project_finance_investmentList).hasSize(databaseSizeBeforeCreate + 1);
        Project_finance_investment testProject_finance_investment = project_finance_investmentList.get(project_finance_investmentList.size() - 1);
        assertThat(testProject_finance_investment.getLand_cost()).isEqualTo(DEFAULT_LAND_COST);
        assertThat(testProject_finance_investment.getBuilding_cost()).isEqualTo(DEFAULT_BUILDING_COST);
        assertThat(testProject_finance_investment.getMachinery_cost()).isEqualTo(DEFAULT_MACHINERY_COST);
        assertThat(testProject_finance_investment.getMisc_assests()).isEqualTo(DEFAULT_MISC_ASSESTS);
        assertThat(testProject_finance_investment.getTotal_project_cost()).isEqualTo(DEFAULT_TOTAL_PROJECT_COST);
        assertThat(testProject_finance_investment.isIsfdi()).isEqualTo(DEFAULT_ISFDI);
        assertThat(testProject_finance_investment.getFdivalue()).isEqualTo(DEFAULT_FDIVALUE);
        assertThat(testProject_finance_investment.getProject_construction_start_date()).isEqualTo(DEFAULT_PROJECT_CONSTRUCTION_START_DATE);
        assertThat(testProject_finance_investment.getCommercial_activity_start_date()).isEqualTo(DEFAULT_COMMERCIAL_ACTIVITY_START_DATE);
        assertThat(testProject_finance_investment.getFdi_country()).isEqualTo(DEFAULT_FDI_COUNTRY);
        assertThat(testProject_finance_investment.getForeign_funding_source()).isEqualTo(DEFAULT_FOREIGN_FUNDING_SOURCE);
        assertThat(testProject_finance_investment.getTotalpurposedemployment()).isEqualTo(DEFAULT_TOTALPURPOSEDEMPLOYMENT);
        assertThat(testProject_finance_investment.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testProject_finance_investment.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
    }

    @Test
    public void createProject_finance_investmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = project_finance_investmentRepository.findAll().size();

        // Create the Project_finance_investment with an existing ID
        Project_finance_investment existingProject_finance_investment = new Project_finance_investment();
        existingProject_finance_investment.setId(UUID.randomUUID());
        Project_finance_investmentDTO existingProject_finance_investmentDTO = project_finance_investmentMapper.project_finance_investmentToProject_finance_investmentDTO(existingProject_finance_investment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProject_finance_investmentMockMvc.perform(post("/api/project-finance-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProject_finance_investmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Project_finance_investment> project_finance_investmentList = project_finance_investmentRepository.findAll();
        assertThat(project_finance_investmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProject_finance_investments() throws Exception {
        // Initialize the database
        project_finance_investmentRepository.save(project_finance_investment);

        // Get all the project_finance_investmentList
        restProject_finance_investmentMockMvc.perform(get("/api/project-finance-investments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project_finance_investment.getId().toString())))
            .andExpect(jsonPath("$.[*].land_cost").value(hasItem(DEFAULT_LAND_COST.intValue())))
            .andExpect(jsonPath("$.[*].building_cost").value(hasItem(DEFAULT_BUILDING_COST.intValue())))
            .andExpect(jsonPath("$.[*].machinery_cost").value(hasItem(DEFAULT_MACHINERY_COST.intValue())))
            .andExpect(jsonPath("$.[*].misc_assests").value(hasItem(DEFAULT_MISC_ASSESTS.intValue())))
            .andExpect(jsonPath("$.[*].total_project_cost").value(hasItem(DEFAULT_TOTAL_PROJECT_COST.intValue())))
            .andExpect(jsonPath("$.[*].isfdi").value(hasItem(DEFAULT_ISFDI.booleanValue())))
            .andExpect(jsonPath("$.[*].fdivalue").value(hasItem(DEFAULT_FDIVALUE.intValue())))
            .andExpect(jsonPath("$.[*].project_construction_start_date").value(hasItem(sameInstant(DEFAULT_PROJECT_CONSTRUCTION_START_DATE))))
            .andExpect(jsonPath("$.[*].commercial_activity_start_date").value(hasItem(sameInstant(DEFAULT_COMMERCIAL_ACTIVITY_START_DATE))))
            .andExpect(jsonPath("$.[*].fdi_country").value(hasItem(DEFAULT_FDI_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].foreign_funding_source").value(hasItem(DEFAULT_FOREIGN_FUNDING_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].totalpurposedemployment").value(hasItem(DEFAULT_TOTALPURPOSEDEMPLOYMENT)))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(sameInstant(DEFAULT_UPDATEDATE))));
    }

    @Test
    public void getProject_finance_investment() throws Exception {
        // Initialize the database
        project_finance_investmentRepository.save(project_finance_investment);

        // Get the project_finance_investment
        restProject_finance_investmentMockMvc.perform(get("/api/project-finance-investments/{id}", project_finance_investment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(project_finance_investment.getId().toString()))
            .andExpect(jsonPath("$.land_cost").value(DEFAULT_LAND_COST.intValue()))
            .andExpect(jsonPath("$.building_cost").value(DEFAULT_BUILDING_COST.intValue()))
            .andExpect(jsonPath("$.machinery_cost").value(DEFAULT_MACHINERY_COST.intValue()))
            .andExpect(jsonPath("$.misc_assests").value(DEFAULT_MISC_ASSESTS.intValue()))
            .andExpect(jsonPath("$.total_project_cost").value(DEFAULT_TOTAL_PROJECT_COST.intValue()))
            .andExpect(jsonPath("$.isfdi").value(DEFAULT_ISFDI.booleanValue()))
            .andExpect(jsonPath("$.fdivalue").value(DEFAULT_FDIVALUE.intValue()))
            .andExpect(jsonPath("$.project_construction_start_date").value(sameInstant(DEFAULT_PROJECT_CONSTRUCTION_START_DATE)))
            .andExpect(jsonPath("$.commercial_activity_start_date").value(sameInstant(DEFAULT_COMMERCIAL_ACTIVITY_START_DATE)))
            .andExpect(jsonPath("$.fdi_country").value(DEFAULT_FDI_COUNTRY.toString()))
            .andExpect(jsonPath("$.foreign_funding_source").value(DEFAULT_FOREIGN_FUNDING_SOURCE.toString()))
            .andExpect(jsonPath("$.totalpurposedemployment").value(DEFAULT_TOTALPURPOSEDEMPLOYMENT))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.updatedate").value(sameInstant(DEFAULT_UPDATEDATE)));
    }

    @Test
    public void getNonExistingProject_finance_investment() throws Exception {
        // Get the project_finance_investment
        restProject_finance_investmentMockMvc.perform(get("/api/project-finance-investments/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProject_finance_investment() throws Exception {
        // Initialize the database
        project_finance_investmentRepository.save(project_finance_investment);
        int databaseSizeBeforeUpdate = project_finance_investmentRepository.findAll().size();

        // Update the project_finance_investment
        Project_finance_investment updatedProject_finance_investment = project_finance_investmentRepository.findOne(project_finance_investment.getId());
        updatedProject_finance_investment
                .land_cost(UPDATED_LAND_COST)
                .building_cost(UPDATED_BUILDING_COST)
                .machinery_cost(UPDATED_MACHINERY_COST)
                .misc_assests(UPDATED_MISC_ASSESTS)
                .total_project_cost(UPDATED_TOTAL_PROJECT_COST)
                .isfdi(UPDATED_ISFDI)
                .fdivalue(UPDATED_FDIVALUE)
                .project_construction_start_date(UPDATED_PROJECT_CONSTRUCTION_START_DATE)
                .commercial_activity_start_date(UPDATED_COMMERCIAL_ACTIVITY_START_DATE)
                .fdi_country(UPDATED_FDI_COUNTRY)
                .foreign_funding_source(UPDATED_FOREIGN_FUNDING_SOURCE)
                .totalpurposedemployment(UPDATED_TOTALPURPOSEDEMPLOYMENT)
                .createdate(UPDATED_CREATEDATE)
                .updatedate(UPDATED_UPDATEDATE);
        Project_finance_investmentDTO project_finance_investmentDTO = project_finance_investmentMapper.project_finance_investmentToProject_finance_investmentDTO(updatedProject_finance_investment);

        restProject_finance_investmentMockMvc.perform(put("/api/project-finance-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_finance_investmentDTO)))
            .andExpect(status().isOk());

        // Validate the Project_finance_investment in the database
        List<Project_finance_investment> project_finance_investmentList = project_finance_investmentRepository.findAll();
        assertThat(project_finance_investmentList).hasSize(databaseSizeBeforeUpdate);
        Project_finance_investment testProject_finance_investment = project_finance_investmentList.get(project_finance_investmentList.size() - 1);
        assertThat(testProject_finance_investment.getLand_cost()).isEqualTo(UPDATED_LAND_COST);
        assertThat(testProject_finance_investment.getBuilding_cost()).isEqualTo(UPDATED_BUILDING_COST);
        assertThat(testProject_finance_investment.getMachinery_cost()).isEqualTo(UPDATED_MACHINERY_COST);
        assertThat(testProject_finance_investment.getMisc_assests()).isEqualTo(UPDATED_MISC_ASSESTS);
        assertThat(testProject_finance_investment.getTotal_project_cost()).isEqualTo(UPDATED_TOTAL_PROJECT_COST);
        assertThat(testProject_finance_investment.isIsfdi()).isEqualTo(UPDATED_ISFDI);
        assertThat(testProject_finance_investment.getFdivalue()).isEqualTo(UPDATED_FDIVALUE);
        assertThat(testProject_finance_investment.getProject_construction_start_date()).isEqualTo(UPDATED_PROJECT_CONSTRUCTION_START_DATE);
        assertThat(testProject_finance_investment.getCommercial_activity_start_date()).isEqualTo(UPDATED_COMMERCIAL_ACTIVITY_START_DATE);
        assertThat(testProject_finance_investment.getFdi_country()).isEqualTo(UPDATED_FDI_COUNTRY);
        assertThat(testProject_finance_investment.getForeign_funding_source()).isEqualTo(UPDATED_FOREIGN_FUNDING_SOURCE);
        assertThat(testProject_finance_investment.getTotalpurposedemployment()).isEqualTo(UPDATED_TOTALPURPOSEDEMPLOYMENT);
        assertThat(testProject_finance_investment.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testProject_finance_investment.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
    }

    @Test
    public void updateNonExistingProject_finance_investment() throws Exception {
        int databaseSizeBeforeUpdate = project_finance_investmentRepository.findAll().size();

        // Create the Project_finance_investment
        Project_finance_investmentDTO project_finance_investmentDTO = project_finance_investmentMapper.project_finance_investmentToProject_finance_investmentDTO(project_finance_investment);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProject_finance_investmentMockMvc.perform(put("/api/project-finance-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_finance_investmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Project_finance_investment in the database
        List<Project_finance_investment> project_finance_investmentList = project_finance_investmentRepository.findAll();
        assertThat(project_finance_investmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProject_finance_investment() throws Exception {
        // Initialize the database
        project_finance_investmentRepository.save(project_finance_investment);
        int databaseSizeBeforeDelete = project_finance_investmentRepository.findAll().size();

        // Get the project_finance_investment
        restProject_finance_investmentMockMvc.perform(delete("/api/project-finance-investments/{id}", project_finance_investment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Project_finance_investment> project_finance_investmentList = project_finance_investmentRepository.findAll();
        assertThat(project_finance_investmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project_finance_investment.class);
    }
}
