package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Wwtreatmenttwo;
import com.hartron.investharyana.repository.WwtreatmenttwoRepository;
import com.hartron.investharyana.service.WwtreatmenttwoService;
import com.hartron.investharyana.service.dto.WwtreatmenttwoDTO;
import com.hartron.investharyana.service.mapper.WwtreatmenttwoMapper;
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
 * Test class for the WwtreatmenttwoResource REST controller.
 *
 * @see WwtreatmenttwoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class WwtreatmenttwoResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_TREATMENT_2 = "AAAAAAAAAA";
    private static final String UPDATED_TREATMENT_2 = "BBBBBBBBBB";

    @Autowired
    private WwtreatmenttwoRepository wwtreatmenttwoRepository;

    @Autowired
    private WwtreatmenttwoMapper wwtreatmenttwoMapper;

    @Autowired
    private WwtreatmenttwoService wwtreatmenttwoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWwtreatmenttwoMockMvc;

    private Wwtreatmenttwo wwtreatmenttwo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WwtreatmenttwoResource wwtreatmenttwoResource = new WwtreatmenttwoResource(wwtreatmenttwoService);
        this.restWwtreatmenttwoMockMvc = MockMvcBuilders.standaloneSetup(wwtreatmenttwoResource)
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
    public static Wwtreatmenttwo createEntity() {
        Wwtreatmenttwo wwtreatmenttwo = new Wwtreatmenttwo()
                .treatment2(DEFAULT_TREATMENT_2);
        return wwtreatmenttwo;
    }

    @Before
    public void initTest() {
        wwtreatmenttwoRepository.deleteAll();
        wwtreatmenttwo = createEntity();
    }

    @Test
    public void createWwtreatmenttwo() throws Exception {
        int databaseSizeBeforeCreate = wwtreatmenttwoRepository.findAll().size();

        // Create the Wwtreatmenttwo
        WwtreatmenttwoDTO wwtreatmenttwoDTO = wwtreatmenttwoMapper.wwtreatmenttwoToWwtreatmenttwoDTO(wwtreatmenttwo);

        restWwtreatmenttwoMockMvc.perform(post("/api/wwtreatmenttwos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmenttwoDTO)))
            .andExpect(status().isCreated());

        // Validate the Wwtreatmenttwo in the database
        List<Wwtreatmenttwo> wwtreatmenttwoList = wwtreatmenttwoRepository.findAll();
        assertThat(wwtreatmenttwoList).hasSize(databaseSizeBeforeCreate + 1);
        Wwtreatmenttwo testWwtreatmenttwo = wwtreatmenttwoList.get(wwtreatmenttwoList.size() - 1);
        assertThat(testWwtreatmenttwo.getTreatment2()).isEqualTo(DEFAULT_TREATMENT_2);
    }

    @Test
    public void createWwtreatmenttwoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wwtreatmenttwoRepository.findAll().size();

        // Create the Wwtreatmenttwo with an existing ID
        Wwtreatmenttwo existingWwtreatmenttwo = new Wwtreatmenttwo();
        existingWwtreatmenttwo.setId(UUID.randomUUID());
        WwtreatmenttwoDTO existingWwtreatmenttwoDTO = wwtreatmenttwoMapper.wwtreatmenttwoToWwtreatmenttwoDTO(existingWwtreatmenttwo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWwtreatmenttwoMockMvc.perform(post("/api/wwtreatmenttwos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWwtreatmenttwoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Wwtreatmenttwo> wwtreatmenttwoList = wwtreatmenttwoRepository.findAll();
        assertThat(wwtreatmenttwoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWwtreatmenttwos() throws Exception {
        // Initialize the database
        wwtreatmenttwoRepository.save(wwtreatmenttwo);

        // Get all the wwtreatmenttwoList
        restWwtreatmenttwoMockMvc.perform(get("/api/wwtreatmenttwos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wwtreatmenttwo.getId().toString())))
            .andExpect(jsonPath("$.[*].treatment2").value(hasItem(DEFAULT_TREATMENT_2.toString())));
    }

    @Test
    public void getWwtreatmenttwo() throws Exception {
        // Initialize the database
        wwtreatmenttwoRepository.save(wwtreatmenttwo);

        // Get the wwtreatmenttwo
        restWwtreatmenttwoMockMvc.perform(get("/api/wwtreatmenttwos/{id}", wwtreatmenttwo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wwtreatmenttwo.getId().toString()))
            .andExpect(jsonPath("$.treatment2").value(DEFAULT_TREATMENT_2.toString()));
    }

    @Test
    public void getNonExistingWwtreatmenttwo() throws Exception {
        // Get the wwtreatmenttwo
        restWwtreatmenttwoMockMvc.perform(get("/api/wwtreatmenttwos/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWwtreatmenttwo() throws Exception {
        // Initialize the database
        wwtreatmenttwoRepository.save(wwtreatmenttwo);
        int databaseSizeBeforeUpdate = wwtreatmenttwoRepository.findAll().size();

        // Update the wwtreatmenttwo
        Wwtreatmenttwo updatedWwtreatmenttwo = wwtreatmenttwoRepository.findOne(wwtreatmenttwo.getId());
        updatedWwtreatmenttwo
                .treatment2(UPDATED_TREATMENT_2);
        WwtreatmenttwoDTO wwtreatmenttwoDTO = wwtreatmenttwoMapper.wwtreatmenttwoToWwtreatmenttwoDTO(updatedWwtreatmenttwo);

        restWwtreatmenttwoMockMvc.perform(put("/api/wwtreatmenttwos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmenttwoDTO)))
            .andExpect(status().isOk());

        // Validate the Wwtreatmenttwo in the database
        List<Wwtreatmenttwo> wwtreatmenttwoList = wwtreatmenttwoRepository.findAll();
        assertThat(wwtreatmenttwoList).hasSize(databaseSizeBeforeUpdate);
        Wwtreatmenttwo testWwtreatmenttwo = wwtreatmenttwoList.get(wwtreatmenttwoList.size() - 1);
        assertThat(testWwtreatmenttwo.getTreatment2()).isEqualTo(UPDATED_TREATMENT_2);
    }

    @Test
    public void updateNonExistingWwtreatmenttwo() throws Exception {
        int databaseSizeBeforeUpdate = wwtreatmenttwoRepository.findAll().size();

        // Create the Wwtreatmenttwo
        WwtreatmenttwoDTO wwtreatmenttwoDTO = wwtreatmenttwoMapper.wwtreatmenttwoToWwtreatmenttwoDTO(wwtreatmenttwo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWwtreatmenttwoMockMvc.perform(put("/api/wwtreatmenttwos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmenttwoDTO)))
            .andExpect(status().isCreated());

        // Validate the Wwtreatmenttwo in the database
        List<Wwtreatmenttwo> wwtreatmenttwoList = wwtreatmenttwoRepository.findAll();
        assertThat(wwtreatmenttwoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWwtreatmenttwo() throws Exception {
        // Initialize the database
        wwtreatmenttwoRepository.save(wwtreatmenttwo);
        int databaseSizeBeforeDelete = wwtreatmenttwoRepository.findAll().size();

        // Get the wwtreatmenttwo
        restWwtreatmenttwoMockMvc.perform(delete("/api/wwtreatmenttwos/{id}", wwtreatmenttwo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Wwtreatmenttwo> wwtreatmenttwoList = wwtreatmenttwoRepository.findAll();
        assertThat(wwtreatmenttwoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wwtreatmenttwo.class);
    }
}
