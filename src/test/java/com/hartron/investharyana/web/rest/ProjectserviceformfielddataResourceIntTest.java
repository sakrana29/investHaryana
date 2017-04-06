package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectserviceformfielddata;
import com.hartron.investharyana.repository.ProjectserviceformfielddataRepository;
import com.hartron.investharyana.service.ProjectserviceformfielddataService;
import com.hartron.investharyana.service.dto.ProjectserviceformfielddataDTO;
import com.hartron.investharyana.service.mapper.ProjectserviceformfielddataMapper;
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
 * Test class for the ProjectserviceformfielddataResource REST controller.
 *
 * @see ProjectserviceformfielddataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectserviceformfielddataResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_SERVICEID = UUID.randomUUID();
    private static final UUID UPDATED_SERVICEID = UUID.randomUUID();

    private static final String DEFAULT_FORMFIELDVALUE = "AAAAAAAAAA";
    private static final String UPDATED_FORMFIELDVALUE = "BBBBBBBBBB";

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_FORMFIELD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FORMFIELD_NAME = "BBBBBBBBBB";

    @Autowired
    private ProjectserviceformfielddataRepository projectserviceformfielddataRepository;

    @Autowired
    private ProjectserviceformfielddataMapper projectserviceformfielddataMapper;

    @Autowired
    private ProjectserviceformfielddataService projectserviceformfielddataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectserviceformfielddataMockMvc;

    private Projectserviceformfielddata projectserviceformfielddata;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectserviceformfielddataResource projectserviceformfielddataResource = new ProjectserviceformfielddataResource(projectserviceformfielddataService);
        this.restProjectserviceformfielddataMockMvc = MockMvcBuilders.standaloneSetup(projectserviceformfielddataResource)
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
    public static Projectserviceformfielddata createEntity() {
        Projectserviceformfielddata projectserviceformfielddata = new Projectserviceformfielddata()
                .serviceid(DEFAULT_SERVICEID)
                .formfieldvalue(DEFAULT_FORMFIELDVALUE)
                .projectid(DEFAULT_PROJECTID)
                .formfieldName(DEFAULT_FORMFIELD_NAME);
        return projectserviceformfielddata;
    }

    @Before
    public void initTest() {
        projectserviceformfielddataRepository.deleteAll();
        projectserviceformfielddata = createEntity();
    }

    @Test
    public void createProjectserviceformfielddata() throws Exception {
        int databaseSizeBeforeCreate = projectserviceformfielddataRepository.findAll().size();

        // Create the Projectserviceformfielddata
        ProjectserviceformfielddataDTO projectserviceformfielddataDTO = projectserviceformfielddataMapper.projectserviceformfielddataToProjectserviceformfielddataDTO(projectserviceformfielddata);

        restProjectserviceformfielddataMockMvc.perform(post("/api/projectserviceformfielddata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectserviceformfielddataDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectserviceformfielddata in the database
        List<Projectserviceformfielddata> projectserviceformfielddataList = projectserviceformfielddataRepository.findAll();
        assertThat(projectserviceformfielddataList).hasSize(databaseSizeBeforeCreate + 1);
        Projectserviceformfielddata testProjectserviceformfielddata = projectserviceformfielddataList.get(projectserviceformfielddataList.size() - 1);
        assertThat(testProjectserviceformfielddata.getServiceid()).isEqualTo(DEFAULT_SERVICEID);
        assertThat(testProjectserviceformfielddata.getFormfieldvalue()).isEqualTo(DEFAULT_FORMFIELDVALUE);
        assertThat(testProjectserviceformfielddata.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectserviceformfielddata.getFormfieldName()).isEqualTo(DEFAULT_FORMFIELD_NAME);
    }

    @Test
    public void createProjectserviceformfielddataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectserviceformfielddataRepository.findAll().size();

        // Create the Projectserviceformfielddata with an existing ID
        Projectserviceformfielddata existingProjectserviceformfielddata = new Projectserviceformfielddata();
        existingProjectserviceformfielddata.setId(UUID.randomUUID());
        ProjectserviceformfielddataDTO existingProjectserviceformfielddataDTO = projectserviceformfielddataMapper.projectserviceformfielddataToProjectserviceformfielddataDTO(existingProjectserviceformfielddata);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectserviceformfielddataMockMvc.perform(post("/api/projectserviceformfielddata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectserviceformfielddataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectserviceformfielddata> projectserviceformfielddataList = projectserviceformfielddataRepository.findAll();
        assertThat(projectserviceformfielddataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkServiceidIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectserviceformfielddataRepository.findAll().size();
        // set the field null
        projectserviceformfielddata.setServiceid(null);

        // Create the Projectserviceformfielddata, which fails.
        ProjectserviceformfielddataDTO projectserviceformfielddataDTO = projectserviceformfielddataMapper.projectserviceformfielddataToProjectserviceformfielddataDTO(projectserviceformfielddata);

        restProjectserviceformfielddataMockMvc.perform(post("/api/projectserviceformfielddata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectserviceformfielddataDTO)))
            .andExpect(status().isBadRequest());

        List<Projectserviceformfielddata> projectserviceformfielddataList = projectserviceformfielddataRepository.findAll();
        assertThat(projectserviceformfielddataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkProjectidIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectserviceformfielddataRepository.findAll().size();
        // set the field null
        projectserviceformfielddata.setProjectid(null);

        // Create the Projectserviceformfielddata, which fails.
        ProjectserviceformfielddataDTO projectserviceformfielddataDTO = projectserviceformfielddataMapper.projectserviceformfielddataToProjectserviceformfielddataDTO(projectserviceformfielddata);

        restProjectserviceformfielddataMockMvc.perform(post("/api/projectserviceformfielddata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectserviceformfielddataDTO)))
            .andExpect(status().isBadRequest());

        List<Projectserviceformfielddata> projectserviceformfielddataList = projectserviceformfielddataRepository.findAll();
        assertThat(projectserviceformfielddataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProjectserviceformfielddata() throws Exception {
        // Initialize the database
        projectserviceformfielddataRepository.save(projectserviceformfielddata);

        // Get all the projectserviceformfielddataList
        restProjectserviceformfielddataMockMvc.perform(get("/api/projectserviceformfielddata?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectserviceformfielddata.getId().toString())))
            .andExpect(jsonPath("$.[*].serviceid").value(hasItem(DEFAULT_SERVICEID.toString())))
            .andExpect(jsonPath("$.[*].formfieldvalue").value(hasItem(DEFAULT_FORMFIELDVALUE.toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].formfieldName").value(hasItem(DEFAULT_FORMFIELD_NAME.toString())));
    }

    @Test
    public void getProjectserviceformfielddata() throws Exception {
        // Initialize the database
        projectserviceformfielddataRepository.save(projectserviceformfielddata);

        // Get the projectserviceformfielddata
        restProjectserviceformfielddataMockMvc.perform(get("/api/projectserviceformfielddata/{id}", projectserviceformfielddata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectserviceformfielddata.getId().toString()))
            .andExpect(jsonPath("$.serviceid").value(DEFAULT_SERVICEID.toString()))
            .andExpect(jsonPath("$.formfieldvalue").value(DEFAULT_FORMFIELDVALUE.toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.formfieldName").value(DEFAULT_FORMFIELD_NAME.toString()));
    }

    @Test
    public void getNonExistingProjectserviceformfielddata() throws Exception {
        // Get the projectserviceformfielddata
        restProjectserviceformfielddataMockMvc.perform(get("/api/projectserviceformfielddata/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectserviceformfielddata() throws Exception {
        // Initialize the database
        projectserviceformfielddataRepository.save(projectserviceformfielddata);
        int databaseSizeBeforeUpdate = projectserviceformfielddataRepository.findAll().size();

        // Update the projectserviceformfielddata
        Projectserviceformfielddata updatedProjectserviceformfielddata = projectserviceformfielddataRepository.findOne(projectserviceformfielddata.getId());
        updatedProjectserviceformfielddata
                .serviceid(UPDATED_SERVICEID)
                .formfieldvalue(UPDATED_FORMFIELDVALUE)
                .projectid(UPDATED_PROJECTID)
                .formfieldName(UPDATED_FORMFIELD_NAME);
        ProjectserviceformfielddataDTO projectserviceformfielddataDTO = projectserviceformfielddataMapper.projectserviceformfielddataToProjectserviceformfielddataDTO(updatedProjectserviceformfielddata);

        restProjectserviceformfielddataMockMvc.perform(put("/api/projectserviceformfielddata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectserviceformfielddataDTO)))
            .andExpect(status().isOk());

        // Validate the Projectserviceformfielddata in the database
        List<Projectserviceformfielddata> projectserviceformfielddataList = projectserviceformfielddataRepository.findAll();
        assertThat(projectserviceformfielddataList).hasSize(databaseSizeBeforeUpdate);
        Projectserviceformfielddata testProjectserviceformfielddata = projectserviceformfielddataList.get(projectserviceformfielddataList.size() - 1);
        assertThat(testProjectserviceformfielddata.getServiceid()).isEqualTo(UPDATED_SERVICEID);
        assertThat(testProjectserviceformfielddata.getFormfieldvalue()).isEqualTo(UPDATED_FORMFIELDVALUE);
        assertThat(testProjectserviceformfielddata.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectserviceformfielddata.getFormfieldName()).isEqualTo(UPDATED_FORMFIELD_NAME);
    }

    @Test
    public void updateNonExistingProjectserviceformfielddata() throws Exception {
        int databaseSizeBeforeUpdate = projectserviceformfielddataRepository.findAll().size();

        // Create the Projectserviceformfielddata
        ProjectserviceformfielddataDTO projectserviceformfielddataDTO = projectserviceformfielddataMapper.projectserviceformfielddataToProjectserviceformfielddataDTO(projectserviceformfielddata);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectserviceformfielddataMockMvc.perform(put("/api/projectserviceformfielddata")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectserviceformfielddataDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectserviceformfielddata in the database
        List<Projectserviceformfielddata> projectserviceformfielddataList = projectserviceformfielddataRepository.findAll();
        assertThat(projectserviceformfielddataList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectserviceformfielddata() throws Exception {
        // Initialize the database
        projectserviceformfielddataRepository.save(projectserviceformfielddata);
        int databaseSizeBeforeDelete = projectserviceformfielddataRepository.findAll().size();

        // Get the projectserviceformfielddata
        restProjectserviceformfielddataMockMvc.perform(delete("/api/projectserviceformfielddata/{id}", projectserviceformfielddata.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectserviceformfielddata> projectserviceformfielddataList = projectserviceformfielddataRepository.findAll();
        assertThat(projectserviceformfielddataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectserviceformfielddata.class);
    }
}
