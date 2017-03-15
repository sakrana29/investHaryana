package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Waste_water_naturetype;
import com.hartron.investharyana.repository.Waste_water_naturetypeRepository;
import com.hartron.investharyana.service.Waste_water_naturetypeService;
import com.hartron.investharyana.service.dto.Waste_water_naturetypeDTO;
import com.hartron.investharyana.service.mapper.Waste_water_naturetypeMapper;
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
 * Test class for the Waste_water_naturetypeResource REST controller.
 *
 * @see Waste_water_naturetypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Waste_water_naturetypeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_NATURETYPE = "AAAAAAAAAA";
    private static final String UPDATED_NATURETYPE = "BBBBBBBBBB";

    @Autowired
    private Waste_water_naturetypeRepository waste_water_naturetypeRepository;

    @Autowired
    private Waste_water_naturetypeMapper waste_water_naturetypeMapper;

    @Autowired
    private Waste_water_naturetypeService waste_water_naturetypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWaste_water_naturetypeMockMvc;

    private Waste_water_naturetype waste_water_naturetype;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Waste_water_naturetypeResource waste_water_naturetypeResource = new Waste_water_naturetypeResource(waste_water_naturetypeService);
        this.restWaste_water_naturetypeMockMvc = MockMvcBuilders.standaloneSetup(waste_water_naturetypeResource)
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
    public static Waste_water_naturetype createEntity() {
        Waste_water_naturetype waste_water_naturetype = new Waste_water_naturetype()
                .naturetype(DEFAULT_NATURETYPE);
        return waste_water_naturetype;
    }

    @Before
    public void initTest() {
        waste_water_naturetypeRepository.deleteAll();
        waste_water_naturetype = createEntity();
    }

    @Test
    public void createWaste_water_naturetype() throws Exception {
        int databaseSizeBeforeCreate = waste_water_naturetypeRepository.findAll().size();

        // Create the Waste_water_naturetype
        Waste_water_naturetypeDTO waste_water_naturetypeDTO = waste_water_naturetypeMapper.waste_water_naturetypeToWaste_water_naturetypeDTO(waste_water_naturetype);

        restWaste_water_naturetypeMockMvc.perform(post("/api/waste-water-naturetypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_naturetypeDTO)))
            .andExpect(status().isCreated());

        // Validate the Waste_water_naturetype in the database
        List<Waste_water_naturetype> waste_water_naturetypeList = waste_water_naturetypeRepository.findAll();
        assertThat(waste_water_naturetypeList).hasSize(databaseSizeBeforeCreate + 1);
        Waste_water_naturetype testWaste_water_naturetype = waste_water_naturetypeList.get(waste_water_naturetypeList.size() - 1);
        assertThat(testWaste_water_naturetype.getNaturetype()).isEqualTo(DEFAULT_NATURETYPE);
    }

    @Test
    public void createWaste_water_naturetypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waste_water_naturetypeRepository.findAll().size();

        // Create the Waste_water_naturetype with an existing ID
        Waste_water_naturetype existingWaste_water_naturetype = new Waste_water_naturetype();
        existingWaste_water_naturetype.setId(UUID.randomUUID());
        Waste_water_naturetypeDTO existingWaste_water_naturetypeDTO = waste_water_naturetypeMapper.waste_water_naturetypeToWaste_water_naturetypeDTO(existingWaste_water_naturetype);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaste_water_naturetypeMockMvc.perform(post("/api/waste-water-naturetypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWaste_water_naturetypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Waste_water_naturetype> waste_water_naturetypeList = waste_water_naturetypeRepository.findAll();
        assertThat(waste_water_naturetypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWaste_water_naturetypes() throws Exception {
        // Initialize the database
        waste_water_naturetypeRepository.save(waste_water_naturetype);

        // Get all the waste_water_naturetypeList
        restWaste_water_naturetypeMockMvc.perform(get("/api/waste-water-naturetypes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waste_water_naturetype.getId().toString())))
            .andExpect(jsonPath("$.[*].naturetype").value(hasItem(DEFAULT_NATURETYPE.toString())));
    }

    @Test
    public void getWaste_water_naturetype() throws Exception {
        // Initialize the database
        waste_water_naturetypeRepository.save(waste_water_naturetype);

        // Get the waste_water_naturetype
        restWaste_water_naturetypeMockMvc.perform(get("/api/waste-water-naturetypes/{id}", waste_water_naturetype.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(waste_water_naturetype.getId().toString()))
            .andExpect(jsonPath("$.naturetype").value(DEFAULT_NATURETYPE.toString()));
    }

    @Test
    public void getNonExistingWaste_water_naturetype() throws Exception {
        // Get the waste_water_naturetype
        restWaste_water_naturetypeMockMvc.perform(get("/api/waste-water-naturetypes/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWaste_water_naturetype() throws Exception {
        // Initialize the database
        waste_water_naturetypeRepository.save(waste_water_naturetype);
        int databaseSizeBeforeUpdate = waste_water_naturetypeRepository.findAll().size();

        // Update the waste_water_naturetype
        Waste_water_naturetype updatedWaste_water_naturetype = waste_water_naturetypeRepository.findOne(waste_water_naturetype.getId());
        updatedWaste_water_naturetype
                .naturetype(UPDATED_NATURETYPE);
        Waste_water_naturetypeDTO waste_water_naturetypeDTO = waste_water_naturetypeMapper.waste_water_naturetypeToWaste_water_naturetypeDTO(updatedWaste_water_naturetype);

        restWaste_water_naturetypeMockMvc.perform(put("/api/waste-water-naturetypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_naturetypeDTO)))
            .andExpect(status().isOk());

        // Validate the Waste_water_naturetype in the database
        List<Waste_water_naturetype> waste_water_naturetypeList = waste_water_naturetypeRepository.findAll();
        assertThat(waste_water_naturetypeList).hasSize(databaseSizeBeforeUpdate);
        Waste_water_naturetype testWaste_water_naturetype = waste_water_naturetypeList.get(waste_water_naturetypeList.size() - 1);
        assertThat(testWaste_water_naturetype.getNaturetype()).isEqualTo(UPDATED_NATURETYPE);
    }

    @Test
    public void updateNonExistingWaste_water_naturetype() throws Exception {
        int databaseSizeBeforeUpdate = waste_water_naturetypeRepository.findAll().size();

        // Create the Waste_water_naturetype
        Waste_water_naturetypeDTO waste_water_naturetypeDTO = waste_water_naturetypeMapper.waste_water_naturetypeToWaste_water_naturetypeDTO(waste_water_naturetype);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWaste_water_naturetypeMockMvc.perform(put("/api/waste-water-naturetypes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(waste_water_naturetypeDTO)))
            .andExpect(status().isCreated());

        // Validate the Waste_water_naturetype in the database
        List<Waste_water_naturetype> waste_water_naturetypeList = waste_water_naturetypeRepository.findAll();
        assertThat(waste_water_naturetypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWaste_water_naturetype() throws Exception {
        // Initialize the database
        waste_water_naturetypeRepository.save(waste_water_naturetype);
        int databaseSizeBeforeDelete = waste_water_naturetypeRepository.findAll().size();

        // Get the waste_water_naturetype
        restWaste_water_naturetypeMockMvc.perform(delete("/api/waste-water-naturetypes/{id}", waste_water_naturetype.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Waste_water_naturetype> waste_water_naturetypeList = waste_water_naturetypeRepository.findAll();
        assertThat(waste_water_naturetypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Waste_water_naturetype.class);
    }
}
