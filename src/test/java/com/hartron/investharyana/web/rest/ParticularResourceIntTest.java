package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Particular;
import com.hartron.investharyana.repository.ParticularRepository;
import com.hartron.investharyana.service.ParticularService;
import com.hartron.investharyana.service.dto.ParticularDTO;
import com.hartron.investharyana.service.mapper.ParticularMapper;
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
 * Test class for the ParticularResource REST controller.
 *
 * @see ParticularResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ParticularResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_PARTICULARS = "AAAAAAAAAA";
    private static final String UPDATED_PARTICULARS = "BBBBBBBBBB";

    @Autowired
    private ParticularRepository particularRepository;

    @Autowired
    private ParticularMapper particularMapper;

    @Autowired
    private ParticularService particularService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restParticularMockMvc;

    private Particular particular;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ParticularResource particularResource = new ParticularResource(particularService);
        this.restParticularMockMvc = MockMvcBuilders.standaloneSetup(particularResource)
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
    public static Particular createEntity() {
        Particular particular = new Particular()
                .particulars(DEFAULT_PARTICULARS);
        return particular;
    }

    @Before
    public void initTest() {
        particularRepository.deleteAll();
        particular = createEntity();
    }

    @Test
    public void createParticular() throws Exception {
        int databaseSizeBeforeCreate = particularRepository.findAll().size();

        // Create the Particular
        ParticularDTO particularDTO = particularMapper.particularToParticularDTO(particular);

        restParticularMockMvc.perform(post("/api/particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(particularDTO)))
            .andExpect(status().isCreated());

        // Validate the Particular in the database
        List<Particular> particularList = particularRepository.findAll();
        assertThat(particularList).hasSize(databaseSizeBeforeCreate + 1);
        Particular testParticular = particularList.get(particularList.size() - 1);
        assertThat(testParticular.getParticulars()).isEqualTo(DEFAULT_PARTICULARS);
    }

    @Test
    public void createParticularWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = particularRepository.findAll().size();

        // Create the Particular with an existing ID
        Particular existingParticular = new Particular();
        existingParticular.setId(UUID.randomUUID());
        ParticularDTO existingParticularDTO = particularMapper.particularToParticularDTO(existingParticular);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParticularMockMvc.perform(post("/api/particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingParticularDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Particular> particularList = particularRepository.findAll();
        assertThat(particularList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkParticularsIsRequired() throws Exception {
        int databaseSizeBeforeTest = particularRepository.findAll().size();
        // set the field null
        particular.setParticulars(null);

        // Create the Particular, which fails.
        ParticularDTO particularDTO = particularMapper.particularToParticularDTO(particular);

        restParticularMockMvc.perform(post("/api/particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(particularDTO)))
            .andExpect(status().isBadRequest());

        List<Particular> particularList = particularRepository.findAll();
        assertThat(particularList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllParticulars() throws Exception {
        // Initialize the database
        particularRepository.save(particular);

        // Get all the particularList
        restParticularMockMvc.perform(get("/api/particulars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(particular.getId().toString())))
            .andExpect(jsonPath("$.[*].particulars").value(hasItem(DEFAULT_PARTICULARS.toString())));
    }

    @Test
    public void getParticular() throws Exception {
        // Initialize the database
        particularRepository.save(particular);

        // Get the particular
        restParticularMockMvc.perform(get("/api/particulars/{id}", particular.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(particular.getId().toString()))
            .andExpect(jsonPath("$.particulars").value(DEFAULT_PARTICULARS.toString()));
    }

    @Test
    public void getNonExistingParticular() throws Exception {
        // Get the particular
        restParticularMockMvc.perform(get("/api/particulars/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateParticular() throws Exception {
        // Initialize the database
        particularRepository.save(particular);
        int databaseSizeBeforeUpdate = particularRepository.findAll().size();

        // Update the particular
        Particular updatedParticular = particularRepository.findOne(particular.getId());
        updatedParticular
                .particulars(UPDATED_PARTICULARS);
        ParticularDTO particularDTO = particularMapper.particularToParticularDTO(updatedParticular);

        restParticularMockMvc.perform(put("/api/particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(particularDTO)))
            .andExpect(status().isOk());

        // Validate the Particular in the database
        List<Particular> particularList = particularRepository.findAll();
        assertThat(particularList).hasSize(databaseSizeBeforeUpdate);
        Particular testParticular = particularList.get(particularList.size() - 1);
        assertThat(testParticular.getParticulars()).isEqualTo(UPDATED_PARTICULARS);
    }

    @Test
    public void updateNonExistingParticular() throws Exception {
        int databaseSizeBeforeUpdate = particularRepository.findAll().size();

        // Create the Particular
        ParticularDTO particularDTO = particularMapper.particularToParticularDTO(particular);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restParticularMockMvc.perform(put("/api/particulars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(particularDTO)))
            .andExpect(status().isCreated());

        // Validate the Particular in the database
        List<Particular> particularList = particularRepository.findAll();
        assertThat(particularList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteParticular() throws Exception {
        // Initialize the database
        particularRepository.save(particular);
        int databaseSizeBeforeDelete = particularRepository.findAll().size();

        // Get the particular
        restParticularMockMvc.perform(delete("/api/particulars/{id}", particular.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Particular> particularList = particularRepository.findAll();
        assertThat(particularList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Particular.class);
    }
}
