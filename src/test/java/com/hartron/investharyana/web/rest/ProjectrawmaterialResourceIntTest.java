package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectrawmaterial;
import com.hartron.investharyana.repository.ProjectrawmaterialRepository;
import com.hartron.investharyana.service.ProjectrawmaterialService;
import com.hartron.investharyana.service.dto.ProjectrawmaterialDTO;
import com.hartron.investharyana.service.mapper.ProjectrawmaterialMapper;
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
 * Test class for the ProjectrawmaterialResource REST controller.
 *
 * @see ProjectrawmaterialResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectrawmaterialResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_RAWMATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAWMATERIAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_UNITS = "AAAAAAAAAA";
    private static final String UPDATED_UNITS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    @Autowired
    private ProjectrawmaterialRepository projectrawmaterialRepository;

    @Autowired
    private ProjectrawmaterialMapper projectrawmaterialMapper;

    @Autowired
    private ProjectrawmaterialService projectrawmaterialService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectrawmaterialMockMvc;

    private Projectrawmaterial projectrawmaterial;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectrawmaterialResource projectrawmaterialResource = new ProjectrawmaterialResource(projectrawmaterialService);
        this.restProjectrawmaterialMockMvc = MockMvcBuilders.standaloneSetup(projectrawmaterialResource)
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
    public static Projectrawmaterial createEntity() {
        Projectrawmaterial projectrawmaterial = new Projectrawmaterial()
                .rawmaterial(DEFAULT_RAWMATERIAL)
                .quantity(DEFAULT_QUANTITY)
                .units(DEFAULT_UNITS)
                .createdate(DEFAULT_CREATEDATE)
                .updatedate(DEFAULT_UPDATEDATE)
                .projectid(DEFAULT_PROJECTID);
        return projectrawmaterial;
    }

    @Before
    public void initTest() {
        projectrawmaterialRepository.deleteAll();
        projectrawmaterial = createEntity();
    }

    @Test
    public void createProjectrawmaterial() throws Exception {
        int databaseSizeBeforeCreate = projectrawmaterialRepository.findAll().size();

        // Create the Projectrawmaterial
        ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(projectrawmaterial);

        restProjectrawmaterialMockMvc.perform(post("/api/projectrawmaterials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectrawmaterialDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectrawmaterial in the database
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeCreate + 1);
        Projectrawmaterial testProjectrawmaterial = projectrawmaterialList.get(projectrawmaterialList.size() - 1);
        assertThat(testProjectrawmaterial.getRawmaterial()).isEqualTo(DEFAULT_RAWMATERIAL);
        assertThat(testProjectrawmaterial.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testProjectrawmaterial.getUnits()).isEqualTo(DEFAULT_UNITS);
        assertThat(testProjectrawmaterial.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testProjectrawmaterial.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
        assertThat(testProjectrawmaterial.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
    }

    @Test
    public void createProjectrawmaterialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectrawmaterialRepository.findAll().size();

        // Create the Projectrawmaterial with an existing ID
        Projectrawmaterial existingProjectrawmaterial = new Projectrawmaterial();
        existingProjectrawmaterial.setId(UUID.randomUUID());
        ProjectrawmaterialDTO existingProjectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(existingProjectrawmaterial);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectrawmaterialMockMvc.perform(post("/api/projectrawmaterials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectrawmaterialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectrawmaterials() throws Exception {
        // Initialize the database
        projectrawmaterialRepository.save(projectrawmaterial);

        // Get all the projectrawmaterialList
        restProjectrawmaterialMockMvc.perform(get("/api/projectrawmaterials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectrawmaterial.getId().toString())))
            .andExpect(jsonPath("$.[*].rawmaterial").value(hasItem(DEFAULT_RAWMATERIAL.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].units").value(hasItem(DEFAULT_UNITS.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(sameInstant(DEFAULT_UPDATEDATE))))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())));
    }

    @Test
    public void getProjectrawmaterial() throws Exception {
        // Initialize the database
        projectrawmaterialRepository.save(projectrawmaterial);

        // Get the projectrawmaterial
        restProjectrawmaterialMockMvc.perform(get("/api/projectrawmaterials/{id}", projectrawmaterial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectrawmaterial.getId().toString()))
            .andExpect(jsonPath("$.rawmaterial").value(DEFAULT_RAWMATERIAL.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.units").value(DEFAULT_UNITS.toString()))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.updatedate").value(sameInstant(DEFAULT_UPDATEDATE)))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()));
    }

    @Test
    public void getNonExistingProjectrawmaterial() throws Exception {
        // Get the projectrawmaterial
        restProjectrawmaterialMockMvc.perform(get("/api/projectrawmaterials/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectrawmaterial() throws Exception {
        // Initialize the database
        projectrawmaterialRepository.save(projectrawmaterial);
        int databaseSizeBeforeUpdate = projectrawmaterialRepository.findAll().size();

        // Update the projectrawmaterial
        Projectrawmaterial updatedProjectrawmaterial = projectrawmaterialRepository.findOne(projectrawmaterial.getId());
        updatedProjectrawmaterial
                .rawmaterial(UPDATED_RAWMATERIAL)
                .quantity(UPDATED_QUANTITY)
                .units(UPDATED_UNITS)
                .createdate(UPDATED_CREATEDATE)
                .updatedate(UPDATED_UPDATEDATE)
                .projectid(UPDATED_PROJECTID);
        ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(updatedProjectrawmaterial);

        restProjectrawmaterialMockMvc.perform(put("/api/projectrawmaterials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectrawmaterialDTO)))
            .andExpect(status().isOk());

        // Validate the Projectrawmaterial in the database
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeUpdate);
        Projectrawmaterial testProjectrawmaterial = projectrawmaterialList.get(projectrawmaterialList.size() - 1);
        assertThat(testProjectrawmaterial.getRawmaterial()).isEqualTo(UPDATED_RAWMATERIAL);
        assertThat(testProjectrawmaterial.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testProjectrawmaterial.getUnits()).isEqualTo(UPDATED_UNITS);
        assertThat(testProjectrawmaterial.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testProjectrawmaterial.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
        assertThat(testProjectrawmaterial.getProjectid()).isEqualTo(UPDATED_PROJECTID);
    }

    @Test
    public void updateNonExistingProjectrawmaterial() throws Exception {
        int databaseSizeBeforeUpdate = projectrawmaterialRepository.findAll().size();

        // Create the Projectrawmaterial
        ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(projectrawmaterial);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectrawmaterialMockMvc.perform(put("/api/projectrawmaterials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectrawmaterialDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectrawmaterial in the database
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectrawmaterial() throws Exception {
        // Initialize the database
        projectrawmaterialRepository.save(projectrawmaterial);
        int databaseSizeBeforeDelete = projectrawmaterialRepository.findAll().size();

        // Get the projectrawmaterial
        restProjectrawmaterialMockMvc.perform(delete("/api/projectrawmaterials/{id}", projectrawmaterial.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectrawmaterial> projectrawmaterialList = projectrawmaterialRepository.findAll();
        assertThat(projectrawmaterialList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectrawmaterial.class);
    }
}
