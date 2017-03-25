package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Wwtreatmentone;
import com.hartron.investharyana.repository.WwtreatmentoneRepository;
import com.hartron.investharyana.service.WwtreatmentoneService;
import com.hartron.investharyana.service.dto.WwtreatmentoneDTO;
import com.hartron.investharyana.service.mapper.WwtreatmentoneMapper;
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
 * Test class for the WwtreatmentoneResource REST controller.
 *
 * @see WwtreatmentoneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class WwtreatmentoneResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_TREATMENT_1 = "AAAAAAAAAA";
    private static final String UPDATED_TREATMENT_1 = "BBBBBBBBBB";

    @Autowired
    private WwtreatmentoneRepository wwtreatmentoneRepository;

    @Autowired
    private WwtreatmentoneMapper wwtreatmentoneMapper;

    @Autowired
    private WwtreatmentoneService wwtreatmentoneService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWwtreatmentoneMockMvc;

    private Wwtreatmentone wwtreatmentone;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WwtreatmentoneResource wwtreatmentoneResource = new WwtreatmentoneResource(wwtreatmentoneService);
        this.restWwtreatmentoneMockMvc = MockMvcBuilders.standaloneSetup(wwtreatmentoneResource)
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
    public static Wwtreatmentone createEntity() {
        Wwtreatmentone wwtreatmentone = new Wwtreatmentone()
                .treatment1(DEFAULT_TREATMENT_1);
        return wwtreatmentone;
    }

    @Before
    public void initTest() {
        wwtreatmentoneRepository.deleteAll();
        wwtreatmentone = createEntity();
    }

    @Test
    public void createWwtreatmentone() throws Exception {
        int databaseSizeBeforeCreate = wwtreatmentoneRepository.findAll().size();

        // Create the Wwtreatmentone
        WwtreatmentoneDTO wwtreatmentoneDTO = wwtreatmentoneMapper.wwtreatmentoneToWwtreatmentoneDTO(wwtreatmentone);

        restWwtreatmentoneMockMvc.perform(post("/api/wwtreatmentones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmentoneDTO)))
            .andExpect(status().isCreated());

        // Validate the Wwtreatmentone in the database
        List<Wwtreatmentone> wwtreatmentoneList = wwtreatmentoneRepository.findAll();
        assertThat(wwtreatmentoneList).hasSize(databaseSizeBeforeCreate + 1);
        Wwtreatmentone testWwtreatmentone = wwtreatmentoneList.get(wwtreatmentoneList.size() - 1);
        assertThat(testWwtreatmentone.getTreatment1()).isEqualTo(DEFAULT_TREATMENT_1);
    }

    @Test
    public void createWwtreatmentoneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wwtreatmentoneRepository.findAll().size();

        // Create the Wwtreatmentone with an existing ID
        Wwtreatmentone existingWwtreatmentone = new Wwtreatmentone();
        existingWwtreatmentone.setId(UUID.randomUUID());
        WwtreatmentoneDTO existingWwtreatmentoneDTO = wwtreatmentoneMapper.wwtreatmentoneToWwtreatmentoneDTO(existingWwtreatmentone);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWwtreatmentoneMockMvc.perform(post("/api/wwtreatmentones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWwtreatmentoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Wwtreatmentone> wwtreatmentoneList = wwtreatmentoneRepository.findAll();
        assertThat(wwtreatmentoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWwtreatmentones() throws Exception {
        // Initialize the database
        wwtreatmentoneRepository.save(wwtreatmentone);

        // Get all the wwtreatmentoneList
        restWwtreatmentoneMockMvc.perform(get("/api/wwtreatmentones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wwtreatmentone.getId().toString())))
            .andExpect(jsonPath("$.[*].treatment1").value(hasItem(DEFAULT_TREATMENT_1.toString())));
    }

    @Test
    public void getWwtreatmentone() throws Exception {
        // Initialize the database
        wwtreatmentoneRepository.save(wwtreatmentone);

        // Get the wwtreatmentone
        restWwtreatmentoneMockMvc.perform(get("/api/wwtreatmentones/{id}", wwtreatmentone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wwtreatmentone.getId().toString()))
            .andExpect(jsonPath("$.treatment1").value(DEFAULT_TREATMENT_1.toString()));
    }

    @Test
    public void getNonExistingWwtreatmentone() throws Exception {
        // Get the wwtreatmentone
        restWwtreatmentoneMockMvc.perform(get("/api/wwtreatmentones/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWwtreatmentone() throws Exception {
        // Initialize the database
        wwtreatmentoneRepository.save(wwtreatmentone);
        int databaseSizeBeforeUpdate = wwtreatmentoneRepository.findAll().size();

        // Update the wwtreatmentone
        Wwtreatmentone updatedWwtreatmentone = wwtreatmentoneRepository.findOne(wwtreatmentone.getId());
        updatedWwtreatmentone
                .treatment1(UPDATED_TREATMENT_1);
        WwtreatmentoneDTO wwtreatmentoneDTO = wwtreatmentoneMapper.wwtreatmentoneToWwtreatmentoneDTO(updatedWwtreatmentone);

        restWwtreatmentoneMockMvc.perform(put("/api/wwtreatmentones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmentoneDTO)))
            .andExpect(status().isOk());

        // Validate the Wwtreatmentone in the database
        List<Wwtreatmentone> wwtreatmentoneList = wwtreatmentoneRepository.findAll();
        assertThat(wwtreatmentoneList).hasSize(databaseSizeBeforeUpdate);
        Wwtreatmentone testWwtreatmentone = wwtreatmentoneList.get(wwtreatmentoneList.size() - 1);
        assertThat(testWwtreatmentone.getTreatment1()).isEqualTo(UPDATED_TREATMENT_1);
    }

    @Test
    public void updateNonExistingWwtreatmentone() throws Exception {
        int databaseSizeBeforeUpdate = wwtreatmentoneRepository.findAll().size();

        // Create the Wwtreatmentone
        WwtreatmentoneDTO wwtreatmentoneDTO = wwtreatmentoneMapper.wwtreatmentoneToWwtreatmentoneDTO(wwtreatmentone);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWwtreatmentoneMockMvc.perform(put("/api/wwtreatmentones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmentoneDTO)))
            .andExpect(status().isCreated());

        // Validate the Wwtreatmentone in the database
        List<Wwtreatmentone> wwtreatmentoneList = wwtreatmentoneRepository.findAll();
        assertThat(wwtreatmentoneList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWwtreatmentone() throws Exception {
        // Initialize the database
        wwtreatmentoneRepository.save(wwtreatmentone);
        int databaseSizeBeforeDelete = wwtreatmentoneRepository.findAll().size();

        // Get the wwtreatmentone
        restWwtreatmentoneMockMvc.perform(delete("/api/wwtreatmentones/{id}", wwtreatmentone.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Wwtreatmentone> wwtreatmentoneList = wwtreatmentoneRepository.findAll();
        assertThat(wwtreatmentoneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wwtreatmentone.class);
    }
}
