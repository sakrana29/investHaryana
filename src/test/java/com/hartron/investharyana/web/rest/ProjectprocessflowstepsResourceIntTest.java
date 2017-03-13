package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectprocessflowsteps;
import com.hartron.investharyana.repository.ProjectprocessflowstepsRepository;
import com.hartron.investharyana.service.ProjectprocessflowstepsService;
import com.hartron.investharyana.service.dto.ProjectprocessflowstepsDTO;
import com.hartron.investharyana.service.mapper.ProjectprocessflowstepsMapper;
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
 * Test class for the ProjectprocessflowstepsResource REST controller.
 *
 * @see ProjectprocessflowstepsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectprocessflowstepsResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_STEPS = "AAAAAAAAAA";
    private static final String UPDATED_STEPS = "BBBBBBBBBB";

    @Autowired
    private ProjectprocessflowstepsRepository projectprocessflowstepsRepository;

    @Autowired
    private ProjectprocessflowstepsMapper projectprocessflowstepsMapper;

    @Autowired
    private ProjectprocessflowstepsService projectprocessflowstepsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectprocessflowstepsMockMvc;

    private Projectprocessflowsteps projectprocessflowsteps;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectprocessflowstepsResource projectprocessflowstepsResource = new ProjectprocessflowstepsResource(projectprocessflowstepsService);
        this.restProjectprocessflowstepsMockMvc = MockMvcBuilders.standaloneSetup(projectprocessflowstepsResource)
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
    public static Projectprocessflowsteps createEntity() {
        Projectprocessflowsteps projectprocessflowsteps = new Projectprocessflowsteps()
                .projectid(DEFAULT_PROJECTID)
                .steps(DEFAULT_STEPS);
        return projectprocessflowsteps;
    }

    @Before
    public void initTest() {
        projectprocessflowstepsRepository.deleteAll();
        projectprocessflowsteps = createEntity();
    }

    @Test
    public void createProjectprocessflowsteps() throws Exception {
        int databaseSizeBeforeCreate = projectprocessflowstepsRepository.findAll().size();

        // Create the Projectprocessflowsteps
        ProjectprocessflowstepsDTO projectprocessflowstepsDTO = projectprocessflowstepsMapper.projectprocessflowstepsToProjectprocessflowstepsDTO(projectprocessflowsteps);

        restProjectprocessflowstepsMockMvc.perform(post("/api/projectprocessflowsteps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectprocessflowstepsDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectprocessflowsteps in the database
        List<Projectprocessflowsteps> projectprocessflowstepsList = projectprocessflowstepsRepository.findAll();
        assertThat(projectprocessflowstepsList).hasSize(databaseSizeBeforeCreate + 1);
        Projectprocessflowsteps testProjectprocessflowsteps = projectprocessflowstepsList.get(projectprocessflowstepsList.size() - 1);
        assertThat(testProjectprocessflowsteps.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectprocessflowsteps.getSteps()).isEqualTo(DEFAULT_STEPS);
    }

    @Test
    public void createProjectprocessflowstepsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectprocessflowstepsRepository.findAll().size();

        // Create the Projectprocessflowsteps with an existing ID
        Projectprocessflowsteps existingProjectprocessflowsteps = new Projectprocessflowsteps();
        existingProjectprocessflowsteps.setId(UUID.randomUUID());
        ProjectprocessflowstepsDTO existingProjectprocessflowstepsDTO = projectprocessflowstepsMapper.projectprocessflowstepsToProjectprocessflowstepsDTO(existingProjectprocessflowsteps);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectprocessflowstepsMockMvc.perform(post("/api/projectprocessflowsteps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectprocessflowstepsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectprocessflowsteps> projectprocessflowstepsList = projectprocessflowstepsRepository.findAll();
        assertThat(projectprocessflowstepsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectprocessflowsteps() throws Exception {
        // Initialize the database
        projectprocessflowstepsRepository.save(projectprocessflowsteps);

        // Get all the projectprocessflowstepsList
        restProjectprocessflowstepsMockMvc.perform(get("/api/projectprocessflowsteps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectprocessflowsteps.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].steps").value(hasItem(DEFAULT_STEPS.toString())));
    }

    @Test
    public void getProjectprocessflowsteps() throws Exception {
        // Initialize the database
        projectprocessflowstepsRepository.save(projectprocessflowsteps);

        // Get the projectprocessflowsteps
        restProjectprocessflowstepsMockMvc.perform(get("/api/projectprocessflowsteps/{id}", projectprocessflowsteps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectprocessflowsteps.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.steps").value(DEFAULT_STEPS.toString()));
    }

    @Test
    public void getNonExistingProjectprocessflowsteps() throws Exception {
        // Get the projectprocessflowsteps
        restProjectprocessflowstepsMockMvc.perform(get("/api/projectprocessflowsteps/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectprocessflowsteps() throws Exception {
        // Initialize the database
        projectprocessflowstepsRepository.save(projectprocessflowsteps);
        int databaseSizeBeforeUpdate = projectprocessflowstepsRepository.findAll().size();

        // Update the projectprocessflowsteps
        Projectprocessflowsteps updatedProjectprocessflowsteps = projectprocessflowstepsRepository.findOne(projectprocessflowsteps.getId());
        updatedProjectprocessflowsteps
                .projectid(UPDATED_PROJECTID)
                .steps(UPDATED_STEPS);
        ProjectprocessflowstepsDTO projectprocessflowstepsDTO = projectprocessflowstepsMapper.projectprocessflowstepsToProjectprocessflowstepsDTO(updatedProjectprocessflowsteps);

        restProjectprocessflowstepsMockMvc.perform(put("/api/projectprocessflowsteps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectprocessflowstepsDTO)))
            .andExpect(status().isOk());

        // Validate the Projectprocessflowsteps in the database
        List<Projectprocessflowsteps> projectprocessflowstepsList = projectprocessflowstepsRepository.findAll();
        assertThat(projectprocessflowstepsList).hasSize(databaseSizeBeforeUpdate);
        Projectprocessflowsteps testProjectprocessflowsteps = projectprocessflowstepsList.get(projectprocessflowstepsList.size() - 1);
        assertThat(testProjectprocessflowsteps.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectprocessflowsteps.getSteps()).isEqualTo(UPDATED_STEPS);
    }

    @Test
    public void updateNonExistingProjectprocessflowsteps() throws Exception {
        int databaseSizeBeforeUpdate = projectprocessflowstepsRepository.findAll().size();

        // Create the Projectprocessflowsteps
        ProjectprocessflowstepsDTO projectprocessflowstepsDTO = projectprocessflowstepsMapper.projectprocessflowstepsToProjectprocessflowstepsDTO(projectprocessflowsteps);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectprocessflowstepsMockMvc.perform(put("/api/projectprocessflowsteps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectprocessflowstepsDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectprocessflowsteps in the database
        List<Projectprocessflowsteps> projectprocessflowstepsList = projectprocessflowstepsRepository.findAll();
        assertThat(projectprocessflowstepsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectprocessflowsteps() throws Exception {
        // Initialize the database
        projectprocessflowstepsRepository.save(projectprocessflowsteps);
        int databaseSizeBeforeDelete = projectprocessflowstepsRepository.findAll().size();

        // Get the projectprocessflowsteps
        restProjectprocessflowstepsMockMvc.perform(delete("/api/projectprocessflowsteps/{id}", projectprocessflowsteps.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectprocessflowsteps> projectprocessflowstepsList = projectprocessflowstepsRepository.findAll();
        assertThat(projectprocessflowstepsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectprocessflowsteps.class);
    }
}
