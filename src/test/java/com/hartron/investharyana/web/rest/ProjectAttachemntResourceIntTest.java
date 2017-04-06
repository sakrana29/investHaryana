package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.ProjectAttachemnt;
import com.hartron.investharyana.repository.ProjectAttachemntRepository;
import com.hartron.investharyana.service.ProjectAttachemntService;
import com.hartron.investharyana.service.dto.ProjectAttachemntDTO;
import com.hartron.investharyana.service.mapper.ProjectAttachemntMapper;
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
 * Test class for the ProjectAttachemntResource REST controller.
 *
 * @see ProjectAttachemntResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectAttachemntResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_EXTENSION = "AAAAAAAAAA";
    private static final String UPDATED_FILE_EXTENSION = "BBBBBBBBBB";

    private static final String DEFAULT_SERVER_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVER_FILE_NAME = "BBBBBBBBBB";

    @Autowired
    private ProjectAttachemntRepository projectAttachemntRepository;

    @Autowired
    private ProjectAttachemntMapper projectAttachemntMapper;

    @Autowired
    private ProjectAttachemntService projectAttachemntService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectAttachemntMockMvc;

    private ProjectAttachemnt projectAttachemnt;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectAttachemntResource projectAttachemntResource = new ProjectAttachemntResource(projectAttachemntService);
        this.restProjectAttachemntMockMvc = MockMvcBuilders.standaloneSetup(projectAttachemntResource)
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
    public static ProjectAttachemnt createEntity() {
        ProjectAttachemnt projectAttachemnt = new ProjectAttachemnt()
                .fileName(DEFAULT_FILE_NAME)
                .description(DEFAULT_DESCRIPTION)
                .fileExtension(DEFAULT_FILE_EXTENSION)
                .serverFileName(DEFAULT_SERVER_FILE_NAME);
        return projectAttachemnt;
    }

    @Before
    public void initTest() {
        projectAttachemntRepository.deleteAll();
        projectAttachemnt = createEntity();
    }

    @Test
    public void createProjectAttachemnt() throws Exception {
        int databaseSizeBeforeCreate = projectAttachemntRepository.findAll().size();

        // Create the ProjectAttachemnt
        ProjectAttachemntDTO projectAttachemntDTO = projectAttachemntMapper.projectAttachemntToProjectAttachemntDTO(projectAttachemnt);

        restProjectAttachemntMockMvc.perform(post("/api/project-attachemnts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachemntDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectAttachemnt in the database
        List<ProjectAttachemnt> projectAttachemntList = projectAttachemntRepository.findAll();
        assertThat(projectAttachemntList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectAttachemnt testProjectAttachemnt = projectAttachemntList.get(projectAttachemntList.size() - 1);
        assertThat(testProjectAttachemnt.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testProjectAttachemnt.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProjectAttachemnt.getFileExtension()).isEqualTo(DEFAULT_FILE_EXTENSION);
        assertThat(testProjectAttachemnt.getServerFileName()).isEqualTo(DEFAULT_SERVER_FILE_NAME);
    }

    @Test
    public void createProjectAttachemntWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectAttachemntRepository.findAll().size();

        // Create the ProjectAttachemnt with an existing ID
        ProjectAttachemnt existingProjectAttachemnt = new ProjectAttachemnt();
        existingProjectAttachemnt.setId(UUID.randomUUID());
        ProjectAttachemntDTO existingProjectAttachemntDTO = projectAttachemntMapper.projectAttachemntToProjectAttachemntDTO(existingProjectAttachemnt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectAttachemntMockMvc.perform(post("/api/project-attachemnts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectAttachemntDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ProjectAttachemnt> projectAttachemntList = projectAttachemntRepository.findAll();
        assertThat(projectAttachemntList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectAttachemnts() throws Exception {
        // Initialize the database
        projectAttachemntRepository.save(projectAttachemnt);

        // Get all the projectAttachemntList
        restProjectAttachemntMockMvc.perform(get("/api/project-attachemnts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectAttachemnt.getId().toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].fileExtension").value(hasItem(DEFAULT_FILE_EXTENSION.toString())))
            .andExpect(jsonPath("$.[*].serverFileName").value(hasItem(DEFAULT_SERVER_FILE_NAME.toString())));
    }

    @Test
    public void getProjectAttachemnt() throws Exception {
        // Initialize the database
        projectAttachemntRepository.save(projectAttachemnt);

        // Get the projectAttachemnt
        restProjectAttachemntMockMvc.perform(get("/api/project-attachemnts/{id}", projectAttachemnt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectAttachemnt.getId().toString()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.fileExtension").value(DEFAULT_FILE_EXTENSION.toString()))
            .andExpect(jsonPath("$.serverFileName").value(DEFAULT_SERVER_FILE_NAME.toString()));
    }

    @Test
    public void getNonExistingProjectAttachemnt() throws Exception {
        // Get the projectAttachemnt
        restProjectAttachemntMockMvc.perform(get("/api/project-attachemnts/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectAttachemnt() throws Exception {
        // Initialize the database
        projectAttachemntRepository.save(projectAttachemnt);
        int databaseSizeBeforeUpdate = projectAttachemntRepository.findAll().size();

        // Update the projectAttachemnt
        ProjectAttachemnt updatedProjectAttachemnt = projectAttachemntRepository.findOne(projectAttachemnt.getId());
        updatedProjectAttachemnt
                .fileName(UPDATED_FILE_NAME)
                .description(UPDATED_DESCRIPTION)
                .fileExtension(UPDATED_FILE_EXTENSION)
                .serverFileName(UPDATED_SERVER_FILE_NAME);
        ProjectAttachemntDTO projectAttachemntDTO = projectAttachemntMapper.projectAttachemntToProjectAttachemntDTO(updatedProjectAttachemnt);

        restProjectAttachemntMockMvc.perform(put("/api/project-attachemnts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachemntDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectAttachemnt in the database
        List<ProjectAttachemnt> projectAttachemntList = projectAttachemntRepository.findAll();
        assertThat(projectAttachemntList).hasSize(databaseSizeBeforeUpdate);
        ProjectAttachemnt testProjectAttachemnt = projectAttachemntList.get(projectAttachemntList.size() - 1);
        assertThat(testProjectAttachemnt.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testProjectAttachemnt.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProjectAttachemnt.getFileExtension()).isEqualTo(UPDATED_FILE_EXTENSION);
        assertThat(testProjectAttachemnt.getServerFileName()).isEqualTo(UPDATED_SERVER_FILE_NAME);
    }

    @Test
    public void updateNonExistingProjectAttachemnt() throws Exception {
        int databaseSizeBeforeUpdate = projectAttachemntRepository.findAll().size();

        // Create the ProjectAttachemnt
        ProjectAttachemntDTO projectAttachemntDTO = projectAttachemntMapper.projectAttachemntToProjectAttachemntDTO(projectAttachemnt);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectAttachemntMockMvc.perform(put("/api/project-attachemnts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachemntDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectAttachemnt in the database
        List<ProjectAttachemnt> projectAttachemntList = projectAttachemntRepository.findAll();
        assertThat(projectAttachemntList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectAttachemnt() throws Exception {
        // Initialize the database
        projectAttachemntRepository.save(projectAttachemnt);
        int databaseSizeBeforeDelete = projectAttachemntRepository.findAll().size();

        // Get the projectAttachemnt
        restProjectAttachemntMockMvc.perform(delete("/api/project-attachemnts/{id}", projectAttachemnt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProjectAttachemnt> projectAttachemntList = projectAttachemntRepository.findAll();
        assertThat(projectAttachemntList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectAttachemnt.class);
    }
}
