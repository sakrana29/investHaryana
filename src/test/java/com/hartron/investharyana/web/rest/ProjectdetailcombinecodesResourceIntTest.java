package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectdetailcombinecodes;
import com.hartron.investharyana.repository.ProjectdetailcombinecodesRepository;
import com.hartron.investharyana.service.ProjectdetailcombinecodesService;
import com.hartron.investharyana.service.dto.ProjectdetailcombinecodesDTO;
import com.hartron.investharyana.service.mapper.ProjectdetailcombinecodesMapper;
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

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProjectdetailcombinecodesResource REST controller.
 *
 * @see ProjectdetailcombinecodesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectdetailcombinecodesResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_INVESTORID = UUID.randomUUID();
    private static final UUID UPDATED_INVESTORID = UUID.randomUUID();

    private static final UUID DEFAULT_COMPANYDETAILID = UUID.randomUUID();
    private static final UUID UPDATED_COMPANYDETAILID = UUID.randomUUID();

    private static final UUID DEFAULT_PROJECTSITEDETAILID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTSITEDETAILID = UUID.randomUUID();

    private static final UUID DEFAULT_PROJECTFINANCEID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTFINANCEID = UUID.randomUUID();

    private static final UUID DEFAULT_MANUFACTURINGID = UUID.randomUUID();
    private static final UUID UPDATED_MANUFACTURINGID = UUID.randomUUID();

    private static final UUID DEFAULT_ELECTRICITYREQUIREMENTID = UUID.randomUUID();
    private static final UUID UPDATED_ELECTRICITYREQUIREMENTID = UUID.randomUUID();

    @Autowired
    private ProjectdetailcombinecodesRepository projectdetailcombinecodesRepository;

    @Autowired
    private ProjectdetailcombinecodesMapper projectdetailcombinecodesMapper;

    @Autowired
    private ProjectdetailcombinecodesService projectdetailcombinecodesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectdetailcombinecodesMockMvc;

    private Projectdetailcombinecodes projectdetailcombinecodes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectdetailcombinecodesResource projectdetailcombinecodesResource = new ProjectdetailcombinecodesResource(projectdetailcombinecodesService);
        this.restProjectdetailcombinecodesMockMvc = MockMvcBuilders.standaloneSetup(projectdetailcombinecodesResource)
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
    public static Projectdetailcombinecodes createEntity() {
        Projectdetailcombinecodes projectdetailcombinecodes = new Projectdetailcombinecodes()
                .investorid(DEFAULT_INVESTORID)
                .companydetailid(DEFAULT_COMPANYDETAILID)
                .projectsitedetailid(DEFAULT_PROJECTSITEDETAILID)
                .projectfinanceid(DEFAULT_PROJECTFINANCEID)
                .manufacturingid(DEFAULT_MANUFACTURINGID)
                .electricityrequirementid(DEFAULT_ELECTRICITYREQUIREMENTID);
        return projectdetailcombinecodes;
    }

    @Before
    public void initTest() {
        projectdetailcombinecodesRepository.deleteAll();
        projectdetailcombinecodes = createEntity();
    }

    @Test
    public void createProjectdetailcombinecodes() throws Exception {
        int databaseSizeBeforeCreate = projectdetailcombinecodesRepository.findAll().size();

        // Create the Projectdetailcombinecodes
        ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO = projectdetailcombinecodesMapper.projectdetailcombinecodesToProjectdetailcombinecodesDTO(projectdetailcombinecodes);

        restProjectdetailcombinecodesMockMvc.perform(post("/api/projectdetailcombinecodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailcombinecodesDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectdetailcombinecodes in the database
        List<Projectdetailcombinecodes> projectdetailcombinecodesList = projectdetailcombinecodesRepository.findAll();
        assertThat(projectdetailcombinecodesList).hasSize(databaseSizeBeforeCreate + 1);
        Projectdetailcombinecodes testProjectdetailcombinecodes = projectdetailcombinecodesList.get(projectdetailcombinecodesList.size() - 1);
        assertThat(testProjectdetailcombinecodes.getInvestorid()).isEqualTo(DEFAULT_INVESTORID);
        assertThat(testProjectdetailcombinecodes.getCompanydetailid()).isEqualTo(DEFAULT_COMPANYDETAILID);
        assertThat(testProjectdetailcombinecodes.getProjectsitedetailid()).isEqualTo(DEFAULT_PROJECTSITEDETAILID);
        assertThat(testProjectdetailcombinecodes.getProjectfinanceid()).isEqualTo(DEFAULT_PROJECTFINANCEID);
        assertThat(testProjectdetailcombinecodes.getManufacturingid()).isEqualTo(DEFAULT_MANUFACTURINGID);
        assertThat(testProjectdetailcombinecodes.getElectricityrequirementid()).isEqualTo(DEFAULT_ELECTRICITYREQUIREMENTID);
    }

    @Test
    public void createProjectdetailcombinecodesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectdetailcombinecodesRepository.findAll().size();

        // Create the Projectdetailcombinecodes with an existing ID
        Projectdetailcombinecodes existingProjectdetailcombinecodes = new Projectdetailcombinecodes();
        existingProjectdetailcombinecodes.setId(UUID.randomUUID());
        ProjectdetailcombinecodesDTO existingProjectdetailcombinecodesDTO = projectdetailcombinecodesMapper.projectdetailcombinecodesToProjectdetailcombinecodesDTO(existingProjectdetailcombinecodes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectdetailcombinecodesMockMvc.perform(post("/api/projectdetailcombinecodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectdetailcombinecodesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectdetailcombinecodes> projectdetailcombinecodesList = projectdetailcombinecodesRepository.findAll();
        assertThat(projectdetailcombinecodesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectdetailcombinecodes() throws Exception {
        // Initialize the database
        projectdetailcombinecodesRepository.save(projectdetailcombinecodes);

        // Get all the projectdetailcombinecodesList
        restProjectdetailcombinecodesMockMvc.perform(get("/api/projectdetailcombinecodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectdetailcombinecodes.getId().toString())))
            .andExpect(jsonPath("$.[*].investorid").value(hasItem(DEFAULT_INVESTORID.toString())))
            .andExpect(jsonPath("$.[*].companydetailid").value(hasItem(DEFAULT_COMPANYDETAILID.toString())))
            .andExpect(jsonPath("$.[*].projectsitedetailid").value(hasItem(DEFAULT_PROJECTSITEDETAILID.toString())))
            .andExpect(jsonPath("$.[*].projectfinanceid").value(hasItem(DEFAULT_PROJECTFINANCEID.toString())))
            .andExpect(jsonPath("$.[*].manufacturingid").value(hasItem(DEFAULT_MANUFACTURINGID.toString())))
            .andExpect(jsonPath("$.[*].electricityrequirementid").value(hasItem(DEFAULT_ELECTRICITYREQUIREMENTID.toString())));
    }

    @Test
    public void getProjectdetailcombinecodes() throws Exception {
        // Initialize the database
        projectdetailcombinecodesRepository.save(projectdetailcombinecodes);

        // Get the projectdetailcombinecodes
        restProjectdetailcombinecodesMockMvc.perform(get("/api/projectdetailcombinecodes/{id}", projectdetailcombinecodes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectdetailcombinecodes.getId().toString()))
            .andExpect(jsonPath("$.investorid").value(DEFAULT_INVESTORID.toString()))
            .andExpect(jsonPath("$.companydetailid").value(DEFAULT_COMPANYDETAILID.toString()))
            .andExpect(jsonPath("$.projectsitedetailid").value(DEFAULT_PROJECTSITEDETAILID.toString()))
            .andExpect(jsonPath("$.projectfinanceid").value(DEFAULT_PROJECTFINANCEID.toString()))
            .andExpect(jsonPath("$.manufacturingid").value(DEFAULT_MANUFACTURINGID.toString()))
            .andExpect(jsonPath("$.electricityrequirementid").value(DEFAULT_ELECTRICITYREQUIREMENTID.toString()));
    }

    @Test
    public void getNonExistingProjectdetailcombinecodes() throws Exception {
        // Get the projectdetailcombinecodes
        restProjectdetailcombinecodesMockMvc.perform(get("/api/projectdetailcombinecodes/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectdetailcombinecodes() throws Exception {
        // Initialize the database
        projectdetailcombinecodesRepository.save(projectdetailcombinecodes);
        int databaseSizeBeforeUpdate = projectdetailcombinecodesRepository.findAll().size();

        // Update the projectdetailcombinecodes
        Projectdetailcombinecodes updatedProjectdetailcombinecodes = projectdetailcombinecodesRepository.findOne(projectdetailcombinecodes.getId());
        updatedProjectdetailcombinecodes
                .investorid(UPDATED_INVESTORID)
                .companydetailid(UPDATED_COMPANYDETAILID)
                .projectsitedetailid(UPDATED_PROJECTSITEDETAILID)
                .projectfinanceid(UPDATED_PROJECTFINANCEID)
                .manufacturingid(UPDATED_MANUFACTURINGID)
                .electricityrequirementid(UPDATED_ELECTRICITYREQUIREMENTID);
        ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO = projectdetailcombinecodesMapper.projectdetailcombinecodesToProjectdetailcombinecodesDTO(updatedProjectdetailcombinecodes);

        restProjectdetailcombinecodesMockMvc.perform(put("/api/projectdetailcombinecodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailcombinecodesDTO)))
            .andExpect(status().isOk());

        // Validate the Projectdetailcombinecodes in the database
        List<Projectdetailcombinecodes> projectdetailcombinecodesList = projectdetailcombinecodesRepository.findAll();
        assertThat(projectdetailcombinecodesList).hasSize(databaseSizeBeforeUpdate);
        Projectdetailcombinecodes testProjectdetailcombinecodes = projectdetailcombinecodesList.get(projectdetailcombinecodesList.size() - 1);
        assertThat(testProjectdetailcombinecodes.getInvestorid()).isEqualTo(UPDATED_INVESTORID);
        assertThat(testProjectdetailcombinecodes.getCompanydetailid()).isEqualTo(UPDATED_COMPANYDETAILID);
        assertThat(testProjectdetailcombinecodes.getProjectsitedetailid()).isEqualTo(UPDATED_PROJECTSITEDETAILID);
        assertThat(testProjectdetailcombinecodes.getProjectfinanceid()).isEqualTo(UPDATED_PROJECTFINANCEID);
        assertThat(testProjectdetailcombinecodes.getManufacturingid()).isEqualTo(UPDATED_MANUFACTURINGID);
        assertThat(testProjectdetailcombinecodes.getElectricityrequirementid()).isEqualTo(UPDATED_ELECTRICITYREQUIREMENTID);
    }

    @Test
    public void updateNonExistingProjectdetailcombinecodes() throws Exception {
        int databaseSizeBeforeUpdate = projectdetailcombinecodesRepository.findAll().size();

        // Create the Projectdetailcombinecodes
        ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO = projectdetailcombinecodesMapper.projectdetailcombinecodesToProjectdetailcombinecodesDTO(projectdetailcombinecodes);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectdetailcombinecodesMockMvc.perform(put("/api/projectdetailcombinecodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailcombinecodesDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectdetailcombinecodes in the database
        List<Projectdetailcombinecodes> projectdetailcombinecodesList = projectdetailcombinecodesRepository.findAll();
        assertThat(projectdetailcombinecodesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectdetailcombinecodes() throws Exception {
        // Initialize the database
        projectdetailcombinecodesRepository.save(projectdetailcombinecodes);
        int databaseSizeBeforeDelete = projectdetailcombinecodesRepository.findAll().size();

        // Get the projectdetailcombinecodes
        restProjectdetailcombinecodesMockMvc.perform(delete("/api/projectdetailcombinecodes/{id}", projectdetailcombinecodes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectdetailcombinecodes> projectdetailcombinecodesList = projectdetailcombinecodesRepository.findAll();
        assertThat(projectdetailcombinecodesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectdetailcombinecodes.class);
    }
}
