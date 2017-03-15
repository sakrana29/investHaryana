package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Emmision_fuel_type;
import com.hartron.investharyana.repository.Emmision_fuel_typeRepository;
import com.hartron.investharyana.service.Emmision_fuel_typeService;
import com.hartron.investharyana.service.dto.Emmision_fuel_typeDTO;
import com.hartron.investharyana.service.mapper.Emmision_fuel_typeMapper;
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
 * Test class for the Emmision_fuel_typeResource REST controller.
 *
 * @see Emmision_fuel_typeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Emmision_fuel_typeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_TYPEOFFUEL = "AAAAAAAAAA";
    private static final String UPDATED_TYPEOFFUEL = "BBBBBBBBBB";

    @Autowired
    private Emmision_fuel_typeRepository emmision_fuel_typeRepository;

    @Autowired
    private Emmision_fuel_typeMapper emmision_fuel_typeMapper;

    @Autowired
    private Emmision_fuel_typeService emmision_fuel_typeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEmmision_fuel_typeMockMvc;

    private Emmision_fuel_type emmision_fuel_type;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Emmision_fuel_typeResource emmision_fuel_typeResource = new Emmision_fuel_typeResource(emmision_fuel_typeService);
        this.restEmmision_fuel_typeMockMvc = MockMvcBuilders.standaloneSetup(emmision_fuel_typeResource)
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
    public static Emmision_fuel_type createEntity() {
        Emmision_fuel_type emmision_fuel_type = new Emmision_fuel_type()
                .typeoffuel(DEFAULT_TYPEOFFUEL);
        return emmision_fuel_type;
    }

    @Before
    public void initTest() {
        emmision_fuel_typeRepository.deleteAll();
        emmision_fuel_type = createEntity();
    }

    @Test
    public void createEmmision_fuel_type() throws Exception {
        int databaseSizeBeforeCreate = emmision_fuel_typeRepository.findAll().size();

        // Create the Emmision_fuel_type
        Emmision_fuel_typeDTO emmision_fuel_typeDTO = emmision_fuel_typeMapper.emmision_fuel_typeToEmmision_fuel_typeDTO(emmision_fuel_type);

        restEmmision_fuel_typeMockMvc.perform(post("/api/emmision-fuel-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emmision_fuel_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Emmision_fuel_type in the database
        List<Emmision_fuel_type> emmision_fuel_typeList = emmision_fuel_typeRepository.findAll();
        assertThat(emmision_fuel_typeList).hasSize(databaseSizeBeforeCreate + 1);
        Emmision_fuel_type testEmmision_fuel_type = emmision_fuel_typeList.get(emmision_fuel_typeList.size() - 1);
        assertThat(testEmmision_fuel_type.getTypeoffuel()).isEqualTo(DEFAULT_TYPEOFFUEL);
    }

    @Test
    public void createEmmision_fuel_typeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emmision_fuel_typeRepository.findAll().size();

        // Create the Emmision_fuel_type with an existing ID
        Emmision_fuel_type existingEmmision_fuel_type = new Emmision_fuel_type();
        existingEmmision_fuel_type.setId(UUID.randomUUID());
        Emmision_fuel_typeDTO existingEmmision_fuel_typeDTO = emmision_fuel_typeMapper.emmision_fuel_typeToEmmision_fuel_typeDTO(existingEmmision_fuel_type);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmmision_fuel_typeMockMvc.perform(post("/api/emmision-fuel-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEmmision_fuel_typeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Emmision_fuel_type> emmision_fuel_typeList = emmision_fuel_typeRepository.findAll();
        assertThat(emmision_fuel_typeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEmmision_fuel_types() throws Exception {
        // Initialize the database
        emmision_fuel_typeRepository.save(emmision_fuel_type);

        // Get all the emmision_fuel_typeList
        restEmmision_fuel_typeMockMvc.perform(get("/api/emmision-fuel-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emmision_fuel_type.getId().toString())))
            .andExpect(jsonPath("$.[*].typeoffuel").value(hasItem(DEFAULT_TYPEOFFUEL.toString())));
    }

    @Test
    public void getEmmision_fuel_type() throws Exception {
        // Initialize the database
        emmision_fuel_typeRepository.save(emmision_fuel_type);

        // Get the emmision_fuel_type
        restEmmision_fuel_typeMockMvc.perform(get("/api/emmision-fuel-types/{id}", emmision_fuel_type.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emmision_fuel_type.getId().toString()))
            .andExpect(jsonPath("$.typeoffuel").value(DEFAULT_TYPEOFFUEL.toString()));
    }

    @Test
    public void getNonExistingEmmision_fuel_type() throws Exception {
        // Get the emmision_fuel_type
        restEmmision_fuel_typeMockMvc.perform(get("/api/emmision-fuel-types/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmmision_fuel_type() throws Exception {
        // Initialize the database
        emmision_fuel_typeRepository.save(emmision_fuel_type);
        int databaseSizeBeforeUpdate = emmision_fuel_typeRepository.findAll().size();

        // Update the emmision_fuel_type
        Emmision_fuel_type updatedEmmision_fuel_type = emmision_fuel_typeRepository.findOne(emmision_fuel_type.getId());
        updatedEmmision_fuel_type
                .typeoffuel(UPDATED_TYPEOFFUEL);
        Emmision_fuel_typeDTO emmision_fuel_typeDTO = emmision_fuel_typeMapper.emmision_fuel_typeToEmmision_fuel_typeDTO(updatedEmmision_fuel_type);

        restEmmision_fuel_typeMockMvc.perform(put("/api/emmision-fuel-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emmision_fuel_typeDTO)))
            .andExpect(status().isOk());

        // Validate the Emmision_fuel_type in the database
        List<Emmision_fuel_type> emmision_fuel_typeList = emmision_fuel_typeRepository.findAll();
        assertThat(emmision_fuel_typeList).hasSize(databaseSizeBeforeUpdate);
        Emmision_fuel_type testEmmision_fuel_type = emmision_fuel_typeList.get(emmision_fuel_typeList.size() - 1);
        assertThat(testEmmision_fuel_type.getTypeoffuel()).isEqualTo(UPDATED_TYPEOFFUEL);
    }

    @Test
    public void updateNonExistingEmmision_fuel_type() throws Exception {
        int databaseSizeBeforeUpdate = emmision_fuel_typeRepository.findAll().size();

        // Create the Emmision_fuel_type
        Emmision_fuel_typeDTO emmision_fuel_typeDTO = emmision_fuel_typeMapper.emmision_fuel_typeToEmmision_fuel_typeDTO(emmision_fuel_type);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmmision_fuel_typeMockMvc.perform(put("/api/emmision-fuel-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emmision_fuel_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Emmision_fuel_type in the database
        List<Emmision_fuel_type> emmision_fuel_typeList = emmision_fuel_typeRepository.findAll();
        assertThat(emmision_fuel_typeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEmmision_fuel_type() throws Exception {
        // Initialize the database
        emmision_fuel_typeRepository.save(emmision_fuel_type);
        int databaseSizeBeforeDelete = emmision_fuel_typeRepository.findAll().size();

        // Get the emmision_fuel_type
        restEmmision_fuel_typeMockMvc.perform(delete("/api/emmision-fuel-types/{id}", emmision_fuel_type.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Emmision_fuel_type> emmision_fuel_typeList = emmision_fuel_typeRepository.findAll();
        assertThat(emmision_fuel_typeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emmision_fuel_type.class);
    }
}
