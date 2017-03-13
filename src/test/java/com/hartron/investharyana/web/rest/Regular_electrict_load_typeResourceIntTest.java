package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Regular_electrict_load_type;
import com.hartron.investharyana.repository.Regular_electrict_load_typeRepository;
import com.hartron.investharyana.service.Regular_electrict_load_typeService;
import com.hartron.investharyana.service.dto.Regular_electrict_load_typeDTO;
import com.hartron.investharyana.service.mapper.Regular_electrict_load_typeMapper;
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
 * Test class for the Regular_electrict_load_typeResource REST controller.
 *
 * @see Regular_electrict_load_typeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Regular_electrict_load_typeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_TYPEOFLOAD = "AAAAAAAAAA";
    private static final String UPDATED_TYPEOFLOAD = "BBBBBBBBBB";

    @Autowired
    private Regular_electrict_load_typeRepository regular_electrict_load_typeRepository;

    @Autowired
    private Regular_electrict_load_typeMapper regular_electrict_load_typeMapper;

    @Autowired
    private Regular_electrict_load_typeService regular_electrict_load_typeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restRegular_electrict_load_typeMockMvc;

    private Regular_electrict_load_type regular_electrict_load_type;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Regular_electrict_load_typeResource regular_electrict_load_typeResource = new Regular_electrict_load_typeResource(regular_electrict_load_typeService);
        this.restRegular_electrict_load_typeMockMvc = MockMvcBuilders.standaloneSetup(regular_electrict_load_typeResource)
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
    public static Regular_electrict_load_type createEntity() {
        Regular_electrict_load_type regular_electrict_load_type = new Regular_electrict_load_type()
                .typeofload(DEFAULT_TYPEOFLOAD);
        return regular_electrict_load_type;
    }

    @Before
    public void initTest() {
        regular_electrict_load_typeRepository.deleteAll();
        regular_electrict_load_type = createEntity();
    }

    @Test
    public void createRegular_electrict_load_type() throws Exception {
        int databaseSizeBeforeCreate = regular_electrict_load_typeRepository.findAll().size();

        // Create the Regular_electrict_load_type
        Regular_electrict_load_typeDTO regular_electrict_load_typeDTO = regular_electrict_load_typeMapper.regular_electrict_load_typeToRegular_electrict_load_typeDTO(regular_electrict_load_type);

        restRegular_electrict_load_typeMockMvc.perform(post("/api/regular-electrict-load-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regular_electrict_load_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Regular_electrict_load_type in the database
        List<Regular_electrict_load_type> regular_electrict_load_typeList = regular_electrict_load_typeRepository.findAll();
        assertThat(regular_electrict_load_typeList).hasSize(databaseSizeBeforeCreate + 1);
        Regular_electrict_load_type testRegular_electrict_load_type = regular_electrict_load_typeList.get(regular_electrict_load_typeList.size() - 1);
        assertThat(testRegular_electrict_load_type.getTypeofload()).isEqualTo(DEFAULT_TYPEOFLOAD);
    }

    @Test
    public void createRegular_electrict_load_typeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = regular_electrict_load_typeRepository.findAll().size();

        // Create the Regular_electrict_load_type with an existing ID
        Regular_electrict_load_type existingRegular_electrict_load_type = new Regular_electrict_load_type();
        existingRegular_electrict_load_type.setId(UUID.randomUUID());
        Regular_electrict_load_typeDTO existingRegular_electrict_load_typeDTO = regular_electrict_load_typeMapper.regular_electrict_load_typeToRegular_electrict_load_typeDTO(existingRegular_electrict_load_type);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegular_electrict_load_typeMockMvc.perform(post("/api/regular-electrict-load-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRegular_electrict_load_typeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Regular_electrict_load_type> regular_electrict_load_typeList = regular_electrict_load_typeRepository.findAll();
        assertThat(regular_electrict_load_typeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllRegular_electrict_load_types() throws Exception {
        // Initialize the database
        regular_electrict_load_typeRepository.save(regular_electrict_load_type);

        // Get all the regular_electrict_load_typeList
        restRegular_electrict_load_typeMockMvc.perform(get("/api/regular-electrict-load-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(regular_electrict_load_type.getId().toString())))
            .andExpect(jsonPath("$.[*].typeofload").value(hasItem(DEFAULT_TYPEOFLOAD.toString())));
    }

    @Test
    public void getRegular_electrict_load_type() throws Exception {
        // Initialize the database
        regular_electrict_load_typeRepository.save(regular_electrict_load_type);

        // Get the regular_electrict_load_type
        restRegular_electrict_load_typeMockMvc.perform(get("/api/regular-electrict-load-types/{id}", regular_electrict_load_type.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(regular_electrict_load_type.getId().toString()))
            .andExpect(jsonPath("$.typeofload").value(DEFAULT_TYPEOFLOAD.toString()));
    }

    @Test
    public void getNonExistingRegular_electrict_load_type() throws Exception {
        // Get the regular_electrict_load_type
        restRegular_electrict_load_typeMockMvc.perform(get("/api/regular-electrict-load-types/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRegular_electrict_load_type() throws Exception {
        // Initialize the database
        regular_electrict_load_typeRepository.save(regular_electrict_load_type);
        int databaseSizeBeforeUpdate = regular_electrict_load_typeRepository.findAll().size();

        // Update the regular_electrict_load_type
        Regular_electrict_load_type updatedRegular_electrict_load_type = regular_electrict_load_typeRepository.findOne(regular_electrict_load_type.getId());
        updatedRegular_electrict_load_type
                .typeofload(UPDATED_TYPEOFLOAD);
        Regular_electrict_load_typeDTO regular_electrict_load_typeDTO = regular_electrict_load_typeMapper.regular_electrict_load_typeToRegular_electrict_load_typeDTO(updatedRegular_electrict_load_type);

        restRegular_electrict_load_typeMockMvc.perform(put("/api/regular-electrict-load-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regular_electrict_load_typeDTO)))
            .andExpect(status().isOk());

        // Validate the Regular_electrict_load_type in the database
        List<Regular_electrict_load_type> regular_electrict_load_typeList = regular_electrict_load_typeRepository.findAll();
        assertThat(regular_electrict_load_typeList).hasSize(databaseSizeBeforeUpdate);
        Regular_electrict_load_type testRegular_electrict_load_type = regular_electrict_load_typeList.get(regular_electrict_load_typeList.size() - 1);
        assertThat(testRegular_electrict_load_type.getTypeofload()).isEqualTo(UPDATED_TYPEOFLOAD);
    }

    @Test
    public void updateNonExistingRegular_electrict_load_type() throws Exception {
        int databaseSizeBeforeUpdate = regular_electrict_load_typeRepository.findAll().size();

        // Create the Regular_electrict_load_type
        Regular_electrict_load_typeDTO regular_electrict_load_typeDTO = regular_electrict_load_typeMapper.regular_electrict_load_typeToRegular_electrict_load_typeDTO(regular_electrict_load_type);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRegular_electrict_load_typeMockMvc.perform(put("/api/regular-electrict-load-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(regular_electrict_load_typeDTO)))
            .andExpect(status().isCreated());

        // Validate the Regular_electrict_load_type in the database
        List<Regular_electrict_load_type> regular_electrict_load_typeList = regular_electrict_load_typeRepository.findAll();
        assertThat(regular_electrict_load_typeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteRegular_electrict_load_type() throws Exception {
        // Initialize the database
        regular_electrict_load_typeRepository.save(regular_electrict_load_type);
        int databaseSizeBeforeDelete = regular_electrict_load_typeRepository.findAll().size();

        // Get the regular_electrict_load_type
        restRegular_electrict_load_typeMockMvc.perform(delete("/api/regular-electrict-load-types/{id}", regular_electrict_load_type.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Regular_electrict_load_type> regular_electrict_load_typeList = regular_electrict_load_typeRepository.findAll();
        assertThat(regular_electrict_load_typeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Regular_electrict_load_type.class);
    }
}
