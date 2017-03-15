package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Watersupplysource;
import com.hartron.investharyana.repository.WatersupplysourceRepository;
import com.hartron.investharyana.service.WatersupplysourceService;
import com.hartron.investharyana.service.dto.WatersupplysourceDTO;
import com.hartron.investharyana.service.mapper.WatersupplysourceMapper;
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
 * Test class for the WatersupplysourceResource REST controller.
 *
 * @see WatersupplysourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class WatersupplysourceResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_WATERSUPPLYSOURCETYPE = "AAAAAAAAAA";
    private static final String UPDATED_WATERSUPPLYSOURCETYPE = "BBBBBBBBBB";

    @Autowired
    private WatersupplysourceRepository watersupplysourceRepository;

    @Autowired
    private WatersupplysourceMapper watersupplysourceMapper;

    @Autowired
    private WatersupplysourceService watersupplysourceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWatersupplysourceMockMvc;

    private Watersupplysource watersupplysource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WatersupplysourceResource watersupplysourceResource = new WatersupplysourceResource(watersupplysourceService);
        this.restWatersupplysourceMockMvc = MockMvcBuilders.standaloneSetup(watersupplysourceResource)
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
    public static Watersupplysource createEntity() {
        Watersupplysource watersupplysource = new Watersupplysource()
                .watersupplysourcetype(DEFAULT_WATERSUPPLYSOURCETYPE);
        return watersupplysource;
    }

    @Before
    public void initTest() {
        watersupplysourceRepository.deleteAll();
        watersupplysource = createEntity();
    }

    @Test
    public void createWatersupplysource() throws Exception {
        int databaseSizeBeforeCreate = watersupplysourceRepository.findAll().size();

        // Create the Watersupplysource
        WatersupplysourceDTO watersupplysourceDTO = watersupplysourceMapper.watersupplysourceToWatersupplysourceDTO(watersupplysource);

        restWatersupplysourceMockMvc.perform(post("/api/watersupplysources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(watersupplysourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Watersupplysource in the database
        List<Watersupplysource> watersupplysourceList = watersupplysourceRepository.findAll();
        assertThat(watersupplysourceList).hasSize(databaseSizeBeforeCreate + 1);
        Watersupplysource testWatersupplysource = watersupplysourceList.get(watersupplysourceList.size() - 1);
        assertThat(testWatersupplysource.getWatersupplysourcetype()).isEqualTo(DEFAULT_WATERSUPPLYSOURCETYPE);
    }

    @Test
    public void createWatersupplysourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = watersupplysourceRepository.findAll().size();

        // Create the Watersupplysource with an existing ID
        Watersupplysource existingWatersupplysource = new Watersupplysource();
        existingWatersupplysource.setId(UUID.randomUUID());
        WatersupplysourceDTO existingWatersupplysourceDTO = watersupplysourceMapper.watersupplysourceToWatersupplysourceDTO(existingWatersupplysource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWatersupplysourceMockMvc.perform(post("/api/watersupplysources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWatersupplysourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Watersupplysource> watersupplysourceList = watersupplysourceRepository.findAll();
        assertThat(watersupplysourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWatersupplysources() throws Exception {
        // Initialize the database
        watersupplysourceRepository.save(watersupplysource);

        // Get all the watersupplysourceList
        restWatersupplysourceMockMvc.perform(get("/api/watersupplysources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(watersupplysource.getId().toString())))
            .andExpect(jsonPath("$.[*].watersupplysourcetype").value(hasItem(DEFAULT_WATERSUPPLYSOURCETYPE.toString())));
    }

    @Test
    public void getWatersupplysource() throws Exception {
        // Initialize the database
        watersupplysourceRepository.save(watersupplysource);

        // Get the watersupplysource
        restWatersupplysourceMockMvc.perform(get("/api/watersupplysources/{id}", watersupplysource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(watersupplysource.getId().toString()))
            .andExpect(jsonPath("$.watersupplysourcetype").value(DEFAULT_WATERSUPPLYSOURCETYPE.toString()));
    }

    @Test
    public void getNonExistingWatersupplysource() throws Exception {
        // Get the watersupplysource
        restWatersupplysourceMockMvc.perform(get("/api/watersupplysources/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWatersupplysource() throws Exception {
        // Initialize the database
        watersupplysourceRepository.save(watersupplysource);
        int databaseSizeBeforeUpdate = watersupplysourceRepository.findAll().size();

        // Update the watersupplysource
        Watersupplysource updatedWatersupplysource = watersupplysourceRepository.findOne(watersupplysource.getId());
        updatedWatersupplysource
                .watersupplysourcetype(UPDATED_WATERSUPPLYSOURCETYPE);
        WatersupplysourceDTO watersupplysourceDTO = watersupplysourceMapper.watersupplysourceToWatersupplysourceDTO(updatedWatersupplysource);

        restWatersupplysourceMockMvc.perform(put("/api/watersupplysources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(watersupplysourceDTO)))
            .andExpect(status().isOk());

        // Validate the Watersupplysource in the database
        List<Watersupplysource> watersupplysourceList = watersupplysourceRepository.findAll();
        assertThat(watersupplysourceList).hasSize(databaseSizeBeforeUpdate);
        Watersupplysource testWatersupplysource = watersupplysourceList.get(watersupplysourceList.size() - 1);
        assertThat(testWatersupplysource.getWatersupplysourcetype()).isEqualTo(UPDATED_WATERSUPPLYSOURCETYPE);
    }

    @Test
    public void updateNonExistingWatersupplysource() throws Exception {
        int databaseSizeBeforeUpdate = watersupplysourceRepository.findAll().size();

        // Create the Watersupplysource
        WatersupplysourceDTO watersupplysourceDTO = watersupplysourceMapper.watersupplysourceToWatersupplysourceDTO(watersupplysource);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWatersupplysourceMockMvc.perform(put("/api/watersupplysources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(watersupplysourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Watersupplysource in the database
        List<Watersupplysource> watersupplysourceList = watersupplysourceRepository.findAll();
        assertThat(watersupplysourceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWatersupplysource() throws Exception {
        // Initialize the database
        watersupplysourceRepository.save(watersupplysource);
        int databaseSizeBeforeDelete = watersupplysourceRepository.findAll().size();

        // Get the watersupplysource
        restWatersupplysourceMockMvc.perform(delete("/api/watersupplysources/{id}", watersupplysource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Watersupplysource> watersupplysourceList = watersupplysourceRepository.findAll();
        assertThat(watersupplysourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Watersupplysource.class);
    }
}
