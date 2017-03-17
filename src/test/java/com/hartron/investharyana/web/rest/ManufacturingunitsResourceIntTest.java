package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Manufacturingunits;
import com.hartron.investharyana.repository.ManufacturingunitsRepository;
import com.hartron.investharyana.service.ManufacturingunitsService;
import com.hartron.investharyana.service.dto.ManufacturingunitsDTO;
import com.hartron.investharyana.service.mapper.ManufacturingunitsMapper;
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
 * Test class for the ManufacturingunitsResource REST controller.
 *
 * @see ManufacturingunitsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ManufacturingunitsResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_UNITTYPES = "AAAAAAAAAA";
    private static final String UPDATED_UNITTYPES = "BBBBBBBBBB";

    @Autowired
    private ManufacturingunitsRepository manufacturingunitsRepository;

    @Autowired
    private ManufacturingunitsMapper manufacturingunitsMapper;

    @Autowired
    private ManufacturingunitsService manufacturingunitsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restManufacturingunitsMockMvc;

    private Manufacturingunits manufacturingunits;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ManufacturingunitsResource manufacturingunitsResource = new ManufacturingunitsResource(manufacturingunitsService);
        this.restManufacturingunitsMockMvc = MockMvcBuilders.standaloneSetup(manufacturingunitsResource)
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
    public static Manufacturingunits createEntity() {
        Manufacturingunits manufacturingunits = new Manufacturingunits()
                .unittypes(DEFAULT_UNITTYPES);
        return manufacturingunits;
    }

    @Before
    public void initTest() {
        manufacturingunitsRepository.deleteAll();
        manufacturingunits = createEntity();
    }

    @Test
    public void createManufacturingunits() throws Exception {
        int databaseSizeBeforeCreate = manufacturingunitsRepository.findAll().size();

        // Create the Manufacturingunits
        ManufacturingunitsDTO manufacturingunitsDTO = manufacturingunitsMapper.manufacturingunitsToManufacturingunitsDTO(manufacturingunits);

        restManufacturingunitsMockMvc.perform(post("/api/manufacturingunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturingunitsDTO)))
            .andExpect(status().isCreated());

        // Validate the Manufacturingunits in the database
        List<Manufacturingunits> manufacturingunitsList = manufacturingunitsRepository.findAll();
        assertThat(manufacturingunitsList).hasSize(databaseSizeBeforeCreate + 1);
        Manufacturingunits testManufacturingunits = manufacturingunitsList.get(manufacturingunitsList.size() - 1);
        assertThat(testManufacturingunits.getUnittypes()).isEqualTo(DEFAULT_UNITTYPES);
    }

    @Test
    public void createManufacturingunitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manufacturingunitsRepository.findAll().size();

        // Create the Manufacturingunits with an existing ID
        Manufacturingunits existingManufacturingunits = new Manufacturingunits();
        existingManufacturingunits.setId(UUID.randomUUID());
        ManufacturingunitsDTO existingManufacturingunitsDTO = manufacturingunitsMapper.manufacturingunitsToManufacturingunitsDTO(existingManufacturingunits);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufacturingunitsMockMvc.perform(post("/api/manufacturingunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingManufacturingunitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Manufacturingunits> manufacturingunitsList = manufacturingunitsRepository.findAll();
        assertThat(manufacturingunitsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkUnittypesIsRequired() throws Exception {
        int databaseSizeBeforeTest = manufacturingunitsRepository.findAll().size();
        // set the field null
        manufacturingunits.setUnittypes(null);

        // Create the Manufacturingunits, which fails.
        ManufacturingunitsDTO manufacturingunitsDTO = manufacturingunitsMapper.manufacturingunitsToManufacturingunitsDTO(manufacturingunits);

        restManufacturingunitsMockMvc.perform(post("/api/manufacturingunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturingunitsDTO)))
            .andExpect(status().isBadRequest());

        List<Manufacturingunits> manufacturingunitsList = manufacturingunitsRepository.findAll();
        assertThat(manufacturingunitsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllManufacturingunits() throws Exception {
        // Initialize the database
        manufacturingunitsRepository.save(manufacturingunits);

        // Get all the manufacturingunitsList
        restManufacturingunitsMockMvc.perform(get("/api/manufacturingunits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufacturingunits.getId().toString())))
            .andExpect(jsonPath("$.[*].unittypes").value(hasItem(DEFAULT_UNITTYPES.toString())));
    }

    @Test
    public void getManufacturingunits() throws Exception {
        // Initialize the database
        manufacturingunitsRepository.save(manufacturingunits);

        // Get the manufacturingunits
        restManufacturingunitsMockMvc.perform(get("/api/manufacturingunits/{id}", manufacturingunits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manufacturingunits.getId().toString()))
            .andExpect(jsonPath("$.unittypes").value(DEFAULT_UNITTYPES.toString()));
    }

    @Test
    public void getNonExistingManufacturingunits() throws Exception {
        // Get the manufacturingunits
        restManufacturingunitsMockMvc.perform(get("/api/manufacturingunits/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateManufacturingunits() throws Exception {
        // Initialize the database
        manufacturingunitsRepository.save(manufacturingunits);
        int databaseSizeBeforeUpdate = manufacturingunitsRepository.findAll().size();

        // Update the manufacturingunits
        Manufacturingunits updatedManufacturingunits = manufacturingunitsRepository.findOne(manufacturingunits.getId());
        updatedManufacturingunits
                .unittypes(UPDATED_UNITTYPES);
        ManufacturingunitsDTO manufacturingunitsDTO = manufacturingunitsMapper.manufacturingunitsToManufacturingunitsDTO(updatedManufacturingunits);

        restManufacturingunitsMockMvc.perform(put("/api/manufacturingunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturingunitsDTO)))
            .andExpect(status().isOk());

        // Validate the Manufacturingunits in the database
        List<Manufacturingunits> manufacturingunitsList = manufacturingunitsRepository.findAll();
        assertThat(manufacturingunitsList).hasSize(databaseSizeBeforeUpdate);
        Manufacturingunits testManufacturingunits = manufacturingunitsList.get(manufacturingunitsList.size() - 1);
        assertThat(testManufacturingunits.getUnittypes()).isEqualTo(UPDATED_UNITTYPES);
    }

    @Test
    public void updateNonExistingManufacturingunits() throws Exception {
        int databaseSizeBeforeUpdate = manufacturingunitsRepository.findAll().size();

        // Create the Manufacturingunits
        ManufacturingunitsDTO manufacturingunitsDTO = manufacturingunitsMapper.manufacturingunitsToManufacturingunitsDTO(manufacturingunits);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restManufacturingunitsMockMvc.perform(put("/api/manufacturingunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturingunitsDTO)))
            .andExpect(status().isCreated());

        // Validate the Manufacturingunits in the database
        List<Manufacturingunits> manufacturingunitsList = manufacturingunitsRepository.findAll();
        assertThat(manufacturingunitsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteManufacturingunits() throws Exception {
        // Initialize the database
        manufacturingunitsRepository.save(manufacturingunits);
        int databaseSizeBeforeDelete = manufacturingunitsRepository.findAll().size();

        // Get the manufacturingunits
        restManufacturingunitsMockMvc.perform(delete("/api/manufacturingunits/{id}", manufacturingunits.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Manufacturingunits> manufacturingunitsList = manufacturingunitsRepository.findAll();
        assertThat(manufacturingunitsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manufacturingunits.class);
    }
}
