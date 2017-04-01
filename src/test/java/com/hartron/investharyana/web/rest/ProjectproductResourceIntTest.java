package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectproduct;
import com.hartron.investharyana.repository.ProjectproductRepository;
import com.hartron.investharyana.service.ProjectproductService;
import com.hartron.investharyana.service.dto.ProjectproductDTO;
import com.hartron.investharyana.service.mapper.ProjectproductMapper;
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
 * Test class for the ProjectproductResource REST controller.
 *
 * @see ProjectproductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectproductResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_MAINPRODUCT = "AAAAAAAAAA";
    private static final String UPDATED_MAINPRODUCT = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_UNITS = "AAAAAAAAAA";
    private static final String UPDATED_UNITS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ProjectproductRepository projectproductRepository;

    @Autowired
    private ProjectproductMapper projectproductMapper;

    @Autowired
    private ProjectproductService projectproductService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectproductMockMvc;

    private Projectproduct projectproduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectproductResource projectproductResource = new ProjectproductResource(projectproductService);
        this.restProjectproductMockMvc = MockMvcBuilders.standaloneSetup(projectproductResource)
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
    public static Projectproduct createEntity() {
        Projectproduct projectproduct = new Projectproduct()
                .mainproduct(DEFAULT_MAINPRODUCT)
                .quantity(DEFAULT_QUANTITY)
                .units(DEFAULT_UNITS)
                .createdate(DEFAULT_CREATEDATE)
                .updatedate(DEFAULT_UPDATEDATE);
        return projectproduct;
    }

    @Before
    public void initTest() {
        projectproductRepository.deleteAll();
        projectproduct = createEntity();
    }

    @Test
    public void createProjectproduct() throws Exception {
        int databaseSizeBeforeCreate = projectproductRepository.findAll().size();

        // Create the Projectproduct
        ProjectproductDTO projectproductDTO = projectproductMapper.projectproductToProjectproductDTO(projectproduct);

        restProjectproductMockMvc.perform(post("/api/projectproducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectproductDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectproduct in the database
        List<Projectproduct> projectproductList = projectproductRepository.findAll();
        assertThat(projectproductList).hasSize(databaseSizeBeforeCreate + 1);
        Projectproduct testProjectproduct = projectproductList.get(projectproductList.size() - 1);
        assertThat(testProjectproduct.getMainproduct()).isEqualTo(DEFAULT_MAINPRODUCT);
        assertThat(testProjectproduct.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testProjectproduct.getUnits()).isEqualTo(DEFAULT_UNITS);
        assertThat(testProjectproduct.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testProjectproduct.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
    }

    @Test
    public void createProjectproductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectproductRepository.findAll().size();

        // Create the Projectproduct with an existing ID
        Projectproduct existingProjectproduct = new Projectproduct();
        existingProjectproduct.setId(UUID.randomUUID());
        ProjectproductDTO existingProjectproductDTO = projectproductMapper.projectproductToProjectproductDTO(existingProjectproduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectproductMockMvc.perform(post("/api/projectproducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectproductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectproduct> projectproductList = projectproductRepository.findAll();
        assertThat(projectproductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectproducts() throws Exception {
        // Initialize the database
        projectproductRepository.save(projectproduct);

        // Get all the projectproductList
        restProjectproductMockMvc.perform(get("/api/projectproducts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectproduct.getId().toString())))
            .andExpect(jsonPath("$.[*].mainproduct").value(hasItem(DEFAULT_MAINPRODUCT.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].units").value(hasItem(DEFAULT_UNITS.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(sameInstant(DEFAULT_UPDATEDATE))));
    }

    @Test
    public void getProjectproduct() throws Exception {
        // Initialize the database
        projectproductRepository.save(projectproduct);

        // Get the projectproduct
        restProjectproductMockMvc.perform(get("/api/projectproducts/{id}", projectproduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectproduct.getId().toString()))
            .andExpect(jsonPath("$.mainproduct").value(DEFAULT_MAINPRODUCT.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.units").value(DEFAULT_UNITS.toString()))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.updatedate").value(sameInstant(DEFAULT_UPDATEDATE)));
    }

    @Test
    public void getNonExistingProjectproduct() throws Exception {
        // Get the projectproduct
        restProjectproductMockMvc.perform(get("/api/projectproducts/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectproduct() throws Exception {
        // Initialize the database
        projectproductRepository.save(projectproduct);
        int databaseSizeBeforeUpdate = projectproductRepository.findAll().size();

        // Update the projectproduct
        Projectproduct updatedProjectproduct = projectproductRepository.findOne(projectproduct.getId());
        updatedProjectproduct
                .mainproduct(UPDATED_MAINPRODUCT)
                .quantity(UPDATED_QUANTITY)
                .units(UPDATED_UNITS)
                .createdate(UPDATED_CREATEDATE)
                .updatedate(UPDATED_UPDATEDATE);
        ProjectproductDTO projectproductDTO = projectproductMapper.projectproductToProjectproductDTO(updatedProjectproduct);

        restProjectproductMockMvc.perform(put("/api/projectproducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectproductDTO)))
            .andExpect(status().isOk());

        // Validate the Projectproduct in the database
        List<Projectproduct> projectproductList = projectproductRepository.findAll();
        assertThat(projectproductList).hasSize(databaseSizeBeforeUpdate);
        Projectproduct testProjectproduct = projectproductList.get(projectproductList.size() - 1);
        assertThat(testProjectproduct.getMainproduct()).isEqualTo(UPDATED_MAINPRODUCT);
        assertThat(testProjectproduct.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testProjectproduct.getUnits()).isEqualTo(UPDATED_UNITS);
        assertThat(testProjectproduct.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testProjectproduct.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
    }

    @Test
    public void updateNonExistingProjectproduct() throws Exception {
        int databaseSizeBeforeUpdate = projectproductRepository.findAll().size();

        // Create the Projectproduct
        ProjectproductDTO projectproductDTO = projectproductMapper.projectproductToProjectproductDTO(projectproduct);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectproductMockMvc.perform(put("/api/projectproducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectproductDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectproduct in the database
        List<Projectproduct> projectproductList = projectproductRepository.findAll();
        assertThat(projectproductList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectproduct() throws Exception {
        // Initialize the database
        projectproductRepository.save(projectproduct);
        int databaseSizeBeforeDelete = projectproductRepository.findAll().size();

        // Get the projectproduct
        restProjectproductMockMvc.perform(delete("/api/projectproducts/{id}", projectproduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectproduct> projectproductList = projectproductRepository.findAll();
        assertThat(projectproductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectproduct.class);
    }
}
