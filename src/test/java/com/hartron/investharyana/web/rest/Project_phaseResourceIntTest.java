package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Project_phase;
import com.hartron.investharyana.repository.Project_phaseRepository;
import com.hartron.investharyana.service.Project_phaseService;
import com.hartron.investharyana.service.dto.Project_phaseDTO;
import com.hartron.investharyana.service.mapper.Project_phaseMapper;
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
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Project_phaseResource REST controller.
 *
 * @see Project_phaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Project_phaseResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_PHASE = "AAAAAAAAAA";
    private static final String UPDATED_PHASE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTCATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTCATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_FCI = "AAAAAAAAAA";
    private static final String UPDATED_FCI = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_IMPLEMENTATIONDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_IMPLEMENTATIONDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    @Autowired
    private Project_phaseRepository project_phaseRepository;

    @Autowired
    private Project_phaseMapper project_phaseMapper;

    @Autowired
    private Project_phaseService project_phaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProject_phaseMockMvc;

    private Project_phase project_phase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Project_phaseResource project_phaseResource = new Project_phaseResource(project_phaseService);
        this.restProject_phaseMockMvc = MockMvcBuilders.standaloneSetup(project_phaseResource)
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
    public static Project_phase createEntity() {
        Project_phase project_phase = new Project_phase()
                .projectid(DEFAULT_PROJECTID)
                .phase(DEFAULT_PHASE)
                .productcategory(DEFAULT_PRODUCTCATEGORY)
                .fci(DEFAULT_FCI)
                .implementationdate(DEFAULT_IMPLEMENTATIONDATE)
                .projectname(DEFAULT_PROJECTNAME);
        return project_phase;
    }

    @Before
    public void initTest() {
        project_phaseRepository.deleteAll();
        project_phase = createEntity();
    }

    @Test
    public void createProject_phase() throws Exception {
        int databaseSizeBeforeCreate = project_phaseRepository.findAll().size();

        // Create the Project_phase
        Project_phaseDTO project_phaseDTO = project_phaseMapper.project_phaseToProject_phaseDTO(project_phase);

        restProject_phaseMockMvc.perform(post("/api/project-phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_phaseDTO)))
            .andExpect(status().isCreated());

        // Validate the Project_phase in the database
        List<Project_phase> project_phaseList = project_phaseRepository.findAll();
        assertThat(project_phaseList).hasSize(databaseSizeBeforeCreate + 1);
        Project_phase testProject_phase = project_phaseList.get(project_phaseList.size() - 1);
        assertThat(testProject_phase.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProject_phase.getPhase()).isEqualTo(DEFAULT_PHASE);
        assertThat(testProject_phase.getProductcategory()).isEqualTo(DEFAULT_PRODUCTCATEGORY);
        assertThat(testProject_phase.getFci()).isEqualTo(DEFAULT_FCI);
        assertThat(testProject_phase.getImplementationdate()).isEqualTo(DEFAULT_IMPLEMENTATIONDATE);
        assertThat(testProject_phase.getProjectname()).isEqualTo(DEFAULT_PROJECTNAME);
    }

    @Test
    public void createProject_phaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = project_phaseRepository.findAll().size();

        // Create the Project_phase with an existing ID
        Project_phase existingProject_phase = new Project_phase();
        existingProject_phase.setId(UUID.randomUUID());
        Project_phaseDTO existingProject_phaseDTO = project_phaseMapper.project_phaseToProject_phaseDTO(existingProject_phase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProject_phaseMockMvc.perform(post("/api/project-phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProject_phaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Project_phase> project_phaseList = project_phaseRepository.findAll();
        assertThat(project_phaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProject_phases() throws Exception {
        // Initialize the database
        project_phaseRepository.save(project_phase);

        // Get all the project_phaseList
        restProject_phaseMockMvc.perform(get("/api/project-phases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project_phase.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].phase").value(hasItem(DEFAULT_PHASE.toString())))
            .andExpect(jsonPath("$.[*].productcategory").value(hasItem(DEFAULT_PRODUCTCATEGORY.toString())))
            .andExpect(jsonPath("$.[*].fci").value(hasItem(DEFAULT_FCI.toString())))
            .andExpect(jsonPath("$.[*].implementationdate").value(hasItem(sameInstant(DEFAULT_IMPLEMENTATIONDATE))))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME.toString())));
    }

    @Test
    public void getProject_phase() throws Exception {
        // Initialize the database
        project_phaseRepository.save(project_phase);

        // Get the project_phase
        restProject_phaseMockMvc.perform(get("/api/project-phases/{id}", project_phase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(project_phase.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.phase").value(DEFAULT_PHASE.toString()))
            .andExpect(jsonPath("$.productcategory").value(DEFAULT_PRODUCTCATEGORY.toString()))
            .andExpect(jsonPath("$.fci").value(DEFAULT_FCI.toString()))
            .andExpect(jsonPath("$.implementationdate").value(sameInstant(DEFAULT_IMPLEMENTATIONDATE)))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME.toString()));
    }

    @Test
    public void getNonExistingProject_phase() throws Exception {
        // Get the project_phase
        restProject_phaseMockMvc.perform(get("/api/project-phases/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProject_phase() throws Exception {
        // Initialize the database
        project_phaseRepository.save(project_phase);
        int databaseSizeBeforeUpdate = project_phaseRepository.findAll().size();

        // Update the project_phase
        Project_phase updatedProject_phase = project_phaseRepository.findOne(project_phase.getId());
        updatedProject_phase
                .projectid(UPDATED_PROJECTID)
                .phase(UPDATED_PHASE)
                .productcategory(UPDATED_PRODUCTCATEGORY)
                .fci(UPDATED_FCI)
                .implementationdate(UPDATED_IMPLEMENTATIONDATE)
                .projectname(UPDATED_PROJECTNAME);
        Project_phaseDTO project_phaseDTO = project_phaseMapper.project_phaseToProject_phaseDTO(updatedProject_phase);

        restProject_phaseMockMvc.perform(put("/api/project-phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_phaseDTO)))
            .andExpect(status().isOk());

        // Validate the Project_phase in the database
        List<Project_phase> project_phaseList = project_phaseRepository.findAll();
        assertThat(project_phaseList).hasSize(databaseSizeBeforeUpdate);
        Project_phase testProject_phase = project_phaseList.get(project_phaseList.size() - 1);
        assertThat(testProject_phase.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProject_phase.getPhase()).isEqualTo(UPDATED_PHASE);
        assertThat(testProject_phase.getProductcategory()).isEqualTo(UPDATED_PRODUCTCATEGORY);
        assertThat(testProject_phase.getFci()).isEqualTo(UPDATED_FCI);
        assertThat(testProject_phase.getImplementationdate()).isEqualTo(UPDATED_IMPLEMENTATIONDATE);
        assertThat(testProject_phase.getProjectname()).isEqualTo(UPDATED_PROJECTNAME);
    }

    @Test
    public void updateNonExistingProject_phase() throws Exception {
        int databaseSizeBeforeUpdate = project_phaseRepository.findAll().size();

        // Create the Project_phase
        Project_phaseDTO project_phaseDTO = project_phaseMapper.project_phaseToProject_phaseDTO(project_phase);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProject_phaseMockMvc.perform(put("/api/project-phases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(project_phaseDTO)))
            .andExpect(status().isCreated());

        // Validate the Project_phase in the database
        List<Project_phase> project_phaseList = project_phaseRepository.findAll();
        assertThat(project_phaseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProject_phase() throws Exception {
        // Initialize the database
        project_phaseRepository.save(project_phase);
        int databaseSizeBeforeDelete = project_phaseRepository.findAll().size();

        // Get the project_phase
        restProject_phaseMockMvc.perform(delete("/api/project-phases/{id}", project_phase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Project_phase> project_phaseList = project_phaseRepository.findAll();
        assertThat(project_phaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project_phase.class);
    }
}
