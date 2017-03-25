package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Wwtreatmentthree;
import com.hartron.investharyana.repository.WwtreatmentthreeRepository;
import com.hartron.investharyana.service.WwtreatmentthreeService;
import com.hartron.investharyana.service.dto.WwtreatmentthreeDTO;
import com.hartron.investharyana.service.mapper.WwtreatmentthreeMapper;
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
 * Test class for the WwtreatmentthreeResource REST controller.
 *
 * @see WwtreatmentthreeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class WwtreatmentthreeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_TREATMENT_3 = "AAAAAAAAAA";
    private static final String UPDATED_TREATMENT_3 = "BBBBBBBBBB";

    @Autowired
    private WwtreatmentthreeRepository wwtreatmentthreeRepository;

    @Autowired
    private WwtreatmentthreeMapper wwtreatmentthreeMapper;

    @Autowired
    private WwtreatmentthreeService wwtreatmentthreeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWwtreatmentthreeMockMvc;

    private Wwtreatmentthree wwtreatmentthree;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WwtreatmentthreeResource wwtreatmentthreeResource = new WwtreatmentthreeResource(wwtreatmentthreeService);
        this.restWwtreatmentthreeMockMvc = MockMvcBuilders.standaloneSetup(wwtreatmentthreeResource)
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
    public static Wwtreatmentthree createEntity() {
        Wwtreatmentthree wwtreatmentthree = new Wwtreatmentthree()
                .treatment3(DEFAULT_TREATMENT_3);
        return wwtreatmentthree;
    }

    @Before
    public void initTest() {
        wwtreatmentthreeRepository.deleteAll();
        wwtreatmentthree = createEntity();
    }

    @Test
    public void createWwtreatmentthree() throws Exception {
        int databaseSizeBeforeCreate = wwtreatmentthreeRepository.findAll().size();

        // Create the Wwtreatmentthree
        WwtreatmentthreeDTO wwtreatmentthreeDTO = wwtreatmentthreeMapper.wwtreatmentthreeToWwtreatmentthreeDTO(wwtreatmentthree);

        restWwtreatmentthreeMockMvc.perform(post("/api/wwtreatmentthrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmentthreeDTO)))
            .andExpect(status().isCreated());

        // Validate the Wwtreatmentthree in the database
        List<Wwtreatmentthree> wwtreatmentthreeList = wwtreatmentthreeRepository.findAll();
        assertThat(wwtreatmentthreeList).hasSize(databaseSizeBeforeCreate + 1);
        Wwtreatmentthree testWwtreatmentthree = wwtreatmentthreeList.get(wwtreatmentthreeList.size() - 1);
        assertThat(testWwtreatmentthree.getTreatment3()).isEqualTo(DEFAULT_TREATMENT_3);
    }

    @Test
    public void createWwtreatmentthreeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wwtreatmentthreeRepository.findAll().size();

        // Create the Wwtreatmentthree with an existing ID
        Wwtreatmentthree existingWwtreatmentthree = new Wwtreatmentthree();
        existingWwtreatmentthree.setId(UUID.randomUUID());
        WwtreatmentthreeDTO existingWwtreatmentthreeDTO = wwtreatmentthreeMapper.wwtreatmentthreeToWwtreatmentthreeDTO(existingWwtreatmentthree);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWwtreatmentthreeMockMvc.perform(post("/api/wwtreatmentthrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWwtreatmentthreeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Wwtreatmentthree> wwtreatmentthreeList = wwtreatmentthreeRepository.findAll();
        assertThat(wwtreatmentthreeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWwtreatmentthrees() throws Exception {
        // Initialize the database
        wwtreatmentthreeRepository.save(wwtreatmentthree);

        // Get all the wwtreatmentthreeList
        restWwtreatmentthreeMockMvc.perform(get("/api/wwtreatmentthrees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wwtreatmentthree.getId().toString())))
            .andExpect(jsonPath("$.[*].treatment3").value(hasItem(DEFAULT_TREATMENT_3.toString())));
    }

    @Test
    public void getWwtreatmentthree() throws Exception {
        // Initialize the database
        wwtreatmentthreeRepository.save(wwtreatmentthree);

        // Get the wwtreatmentthree
        restWwtreatmentthreeMockMvc.perform(get("/api/wwtreatmentthrees/{id}", wwtreatmentthree.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wwtreatmentthree.getId().toString()))
            .andExpect(jsonPath("$.treatment3").value(DEFAULT_TREATMENT_3.toString()));
    }

    @Test
    public void getNonExistingWwtreatmentthree() throws Exception {
        // Get the wwtreatmentthree
        restWwtreatmentthreeMockMvc.perform(get("/api/wwtreatmentthrees/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWwtreatmentthree() throws Exception {
        // Initialize the database
        wwtreatmentthreeRepository.save(wwtreatmentthree);
        int databaseSizeBeforeUpdate = wwtreatmentthreeRepository.findAll().size();

        // Update the wwtreatmentthree
        Wwtreatmentthree updatedWwtreatmentthree = wwtreatmentthreeRepository.findOne(wwtreatmentthree.getId());
        updatedWwtreatmentthree
                .treatment3(UPDATED_TREATMENT_3);
        WwtreatmentthreeDTO wwtreatmentthreeDTO = wwtreatmentthreeMapper.wwtreatmentthreeToWwtreatmentthreeDTO(updatedWwtreatmentthree);

        restWwtreatmentthreeMockMvc.perform(put("/api/wwtreatmentthrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmentthreeDTO)))
            .andExpect(status().isOk());

        // Validate the Wwtreatmentthree in the database
        List<Wwtreatmentthree> wwtreatmentthreeList = wwtreatmentthreeRepository.findAll();
        assertThat(wwtreatmentthreeList).hasSize(databaseSizeBeforeUpdate);
        Wwtreatmentthree testWwtreatmentthree = wwtreatmentthreeList.get(wwtreatmentthreeList.size() - 1);
        assertThat(testWwtreatmentthree.getTreatment3()).isEqualTo(UPDATED_TREATMENT_3);
    }

    @Test
    public void updateNonExistingWwtreatmentthree() throws Exception {
        int databaseSizeBeforeUpdate = wwtreatmentthreeRepository.findAll().size();

        // Create the Wwtreatmentthree
        WwtreatmentthreeDTO wwtreatmentthreeDTO = wwtreatmentthreeMapper.wwtreatmentthreeToWwtreatmentthreeDTO(wwtreatmentthree);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWwtreatmentthreeMockMvc.perform(put("/api/wwtreatmentthrees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wwtreatmentthreeDTO)))
            .andExpect(status().isCreated());

        // Validate the Wwtreatmentthree in the database
        List<Wwtreatmentthree> wwtreatmentthreeList = wwtreatmentthreeRepository.findAll();
        assertThat(wwtreatmentthreeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWwtreatmentthree() throws Exception {
        // Initialize the database
        wwtreatmentthreeRepository.save(wwtreatmentthree);
        int databaseSizeBeforeDelete = wwtreatmentthreeRepository.findAll().size();

        // Get the wwtreatmentthree
        restWwtreatmentthreeMockMvc.perform(delete("/api/wwtreatmentthrees/{id}", wwtreatmentthree.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Wwtreatmentthree> wwtreatmentthreeList = wwtreatmentthreeRepository.findAll();
        assertThat(wwtreatmentthreeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wwtreatmentthree.class);
    }
}
