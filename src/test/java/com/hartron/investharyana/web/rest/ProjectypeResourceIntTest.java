package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectype;
import com.hartron.investharyana.repository.ProjectypeRepository;
import com.hartron.investharyana.service.ProjectypeService;
import com.hartron.investharyana.service.dto.ProjectypeDTO;
import com.hartron.investharyana.service.mapper.ProjectypeMapper;
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
 * Test class for the ProjectypeResource REST controller.
 *
 * @see ProjectypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectypeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_PROJECTYPES = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTYPES = "BBBBBBBBBB";

    @Autowired
    private ProjectypeRepository projectypeRepository;

    @Autowired
    private ProjectypeMapper projectypeMapper;

    @Autowired
    private ProjectypeService projectypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectypeMockMvc;

    private Projectype projectype;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectypeResource projectypeResource = new ProjectypeResource(projectypeService);
        this.restProjectypeMockMvc = MockMvcBuilders.standaloneSetup(projectypeResource)
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
    public static Projectype createEntity() {
        Projectype projectype = new Projectype()
                .projectypes(DEFAULT_PROJECTYPES);
        return projectype;
    }

    @Before
    public void initTest() {
        projectypeRepository.deleteAll();
        projectype = createEntity();
    }

    @Test
    public void createProjectype() throws Exception {
        int databaseSizeBeforeCreate = projectypeRepository.findAll().size();

        // Create the Projectype
        ProjectypeDTO projectypeDTO = projectypeMapper.projectypeToProjectypeDTO(projectype);

        restProjectypeMockMvc.perform(post("/api/projectypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectypeDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectype in the database
        List<Projectype> projectypeList = projectypeRepository.findAll();
        assertThat(projectypeList).hasSize(databaseSizeBeforeCreate + 1);
        Projectype testProjectype = projectypeList.get(projectypeList.size() - 1);
        assertThat(testProjectype.getProjectypes()).isEqualTo(DEFAULT_PROJECTYPES);
    }

    @Test
    public void createProjectypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectypeRepository.findAll().size();

        // Create the Projectype with an existing ID
        Projectype existingProjectype = new Projectype();
        existingProjectype.setId(UUID.randomUUID());
        ProjectypeDTO existingProjectypeDTO = projectypeMapper.projectypeToProjectypeDTO(existingProjectype);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectypeMockMvc.perform(post("/api/projectypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectype> projectypeList = projectypeRepository.findAll();
        assertThat(projectypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkProjectypesIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectypeRepository.findAll().size();
        // set the field null
        projectype.setProjectypes(null);

        // Create the Projectype, which fails.
        ProjectypeDTO projectypeDTO = projectypeMapper.projectypeToProjectypeDTO(projectype);

        restProjectypeMockMvc.perform(post("/api/projectypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectypeDTO)))
            .andExpect(status().isBadRequest());

        List<Projectype> projectypeList = projectypeRepository.findAll();
        assertThat(projectypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProjectypes() throws Exception {
        // Initialize the database
        projectypeRepository.save(projectype);

        // Get all the projectypeList
        restProjectypeMockMvc.perform(get("/api/projectypes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectype.getId().toString())))
            .andExpect(jsonPath("$.[*].projectypes").value(hasItem(DEFAULT_PROJECTYPES.toString())));
    }

    @Test
    public void getProjectype() throws Exception {
        // Initialize the database
        projectypeRepository.save(projectype);

        // Get the projectype
        restProjectypeMockMvc.perform(get("/api/projectypes/{id}", projectype.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectype.getId().toString()))
            .andExpect(jsonPath("$.projectypes").value(DEFAULT_PROJECTYPES.toString()));
    }

    @Test
    public void getNonExistingProjectype() throws Exception {
        // Get the projectype
        restProjectypeMockMvc.perform(get("/api/projectypes/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectype() throws Exception {
        // Initialize the database
        projectypeRepository.save(projectype);
        int databaseSizeBeforeUpdate = projectypeRepository.findAll().size();

        // Update the projectype
        Projectype updatedProjectype = projectypeRepository.findOne(projectype.getId());
        updatedProjectype
                .projectypes(UPDATED_PROJECTYPES);
        ProjectypeDTO projectypeDTO = projectypeMapper.projectypeToProjectypeDTO(updatedProjectype);

        restProjectypeMockMvc.perform(put("/api/projectypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectypeDTO)))
            .andExpect(status().isOk());

        // Validate the Projectype in the database
        List<Projectype> projectypeList = projectypeRepository.findAll();
        assertThat(projectypeList).hasSize(databaseSizeBeforeUpdate);
        Projectype testProjectype = projectypeList.get(projectypeList.size() - 1);
        assertThat(testProjectype.getProjectypes()).isEqualTo(UPDATED_PROJECTYPES);
    }

    @Test
    public void updateNonExistingProjectype() throws Exception {
        int databaseSizeBeforeUpdate = projectypeRepository.findAll().size();

        // Create the Projectype
        ProjectypeDTO projectypeDTO = projectypeMapper.projectypeToProjectypeDTO(projectype);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectypeMockMvc.perform(put("/api/projectypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectypeDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectype in the database
        List<Projectype> projectypeList = projectypeRepository.findAll();
        assertThat(projectypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectype() throws Exception {
        // Initialize the database
        projectypeRepository.save(projectype);
        int databaseSizeBeforeDelete = projectypeRepository.findAll().size();

        // Get the projectype
        restProjectypeMockMvc.perform(delete("/api/projectypes/{id}", projectype.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectype> projectypeList = projectypeRepository.findAll();
        assertThat(projectypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectype.class);
    }
}
