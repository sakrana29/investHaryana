package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Emmision_pollution_controll;
import com.hartron.investharyana.repository.Emmision_pollution_controllRepository;
import com.hartron.investharyana.service.Emmision_pollution_controllService;
import com.hartron.investharyana.service.dto.Emmision_pollution_controllDTO;
import com.hartron.investharyana.service.mapper.Emmision_pollution_controllMapper;
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
 * Test class for the Emmision_pollution_controllResource REST controller.
 *
 * @see Emmision_pollution_controllResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Emmision_pollution_controllResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_AIRPOLLUTIONCONTROLDEVICE = "AAAAAAAAAA";
    private static final String UPDATED_AIRPOLLUTIONCONTROLDEVICE = "BBBBBBBBBB";

    @Autowired
    private Emmision_pollution_controllRepository emmision_pollution_controllRepository;

    @Autowired
    private Emmision_pollution_controllMapper emmision_pollution_controllMapper;

    @Autowired
    private Emmision_pollution_controllService emmision_pollution_controllService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEmmision_pollution_controllMockMvc;

    private Emmision_pollution_controll emmision_pollution_controll;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Emmision_pollution_controllResource emmision_pollution_controllResource = new Emmision_pollution_controllResource(emmision_pollution_controllService);
        this.restEmmision_pollution_controllMockMvc = MockMvcBuilders.standaloneSetup(emmision_pollution_controllResource)
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
    public static Emmision_pollution_controll createEntity() {
        Emmision_pollution_controll emmision_pollution_controll = new Emmision_pollution_controll()
                .airpollutioncontroldevice(DEFAULT_AIRPOLLUTIONCONTROLDEVICE);
        return emmision_pollution_controll;
    }

    @Before
    public void initTest() {
        emmision_pollution_controllRepository.deleteAll();
        emmision_pollution_controll = createEntity();
    }

    @Test
    public void createEmmision_pollution_controll() throws Exception {
        int databaseSizeBeforeCreate = emmision_pollution_controllRepository.findAll().size();

        // Create the Emmision_pollution_controll
        Emmision_pollution_controllDTO emmision_pollution_controllDTO = emmision_pollution_controllMapper.emmision_pollution_controllToEmmision_pollution_controllDTO(emmision_pollution_controll);

        restEmmision_pollution_controllMockMvc.perform(post("/api/emmision-pollution-controlls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emmision_pollution_controllDTO)))
            .andExpect(status().isCreated());

        // Validate the Emmision_pollution_controll in the database
        List<Emmision_pollution_controll> emmision_pollution_controllList = emmision_pollution_controllRepository.findAll();
        assertThat(emmision_pollution_controllList).hasSize(databaseSizeBeforeCreate + 1);
        Emmision_pollution_controll testEmmision_pollution_controll = emmision_pollution_controllList.get(emmision_pollution_controllList.size() - 1);
        assertThat(testEmmision_pollution_controll.getAirpollutioncontroldevice()).isEqualTo(DEFAULT_AIRPOLLUTIONCONTROLDEVICE);
    }

    @Test
    public void createEmmision_pollution_controllWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emmision_pollution_controllRepository.findAll().size();

        // Create the Emmision_pollution_controll with an existing ID
        Emmision_pollution_controll existingEmmision_pollution_controll = new Emmision_pollution_controll();
        existingEmmision_pollution_controll.setId(UUID.randomUUID());
        Emmision_pollution_controllDTO existingEmmision_pollution_controllDTO = emmision_pollution_controllMapper.emmision_pollution_controllToEmmision_pollution_controllDTO(existingEmmision_pollution_controll);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmmision_pollution_controllMockMvc.perform(post("/api/emmision-pollution-controlls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEmmision_pollution_controllDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Emmision_pollution_controll> emmision_pollution_controllList = emmision_pollution_controllRepository.findAll();
        assertThat(emmision_pollution_controllList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEmmision_pollution_controlls() throws Exception {
        // Initialize the database
        emmision_pollution_controllRepository.save(emmision_pollution_controll);

        // Get all the emmision_pollution_controllList
        restEmmision_pollution_controllMockMvc.perform(get("/api/emmision-pollution-controlls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emmision_pollution_controll.getId().toString())))
            .andExpect(jsonPath("$.[*].airpollutioncontroldevice").value(hasItem(DEFAULT_AIRPOLLUTIONCONTROLDEVICE.toString())));
    }

    @Test
    public void getEmmision_pollution_controll() throws Exception {
        // Initialize the database
        emmision_pollution_controllRepository.save(emmision_pollution_controll);

        // Get the emmision_pollution_controll
        restEmmision_pollution_controllMockMvc.perform(get("/api/emmision-pollution-controlls/{id}", emmision_pollution_controll.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(emmision_pollution_controll.getId().toString()))
            .andExpect(jsonPath("$.airpollutioncontroldevice").value(DEFAULT_AIRPOLLUTIONCONTROLDEVICE.toString()));
    }

    @Test
    public void getNonExistingEmmision_pollution_controll() throws Exception {
        // Get the emmision_pollution_controll
        restEmmision_pollution_controllMockMvc.perform(get("/api/emmision-pollution-controlls/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmmision_pollution_controll() throws Exception {
        // Initialize the database
        emmision_pollution_controllRepository.save(emmision_pollution_controll);
        int databaseSizeBeforeUpdate = emmision_pollution_controllRepository.findAll().size();

        // Update the emmision_pollution_controll
        Emmision_pollution_controll updatedEmmision_pollution_controll = emmision_pollution_controllRepository.findOne(emmision_pollution_controll.getId());
        updatedEmmision_pollution_controll
                .airpollutioncontroldevice(UPDATED_AIRPOLLUTIONCONTROLDEVICE);
        Emmision_pollution_controllDTO emmision_pollution_controllDTO = emmision_pollution_controllMapper.emmision_pollution_controllToEmmision_pollution_controllDTO(updatedEmmision_pollution_controll);

        restEmmision_pollution_controllMockMvc.perform(put("/api/emmision-pollution-controlls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emmision_pollution_controllDTO)))
            .andExpect(status().isOk());

        // Validate the Emmision_pollution_controll in the database
        List<Emmision_pollution_controll> emmision_pollution_controllList = emmision_pollution_controllRepository.findAll();
        assertThat(emmision_pollution_controllList).hasSize(databaseSizeBeforeUpdate);
        Emmision_pollution_controll testEmmision_pollution_controll = emmision_pollution_controllList.get(emmision_pollution_controllList.size() - 1);
        assertThat(testEmmision_pollution_controll.getAirpollutioncontroldevice()).isEqualTo(UPDATED_AIRPOLLUTIONCONTROLDEVICE);
    }

    @Test
    public void updateNonExistingEmmision_pollution_controll() throws Exception {
        int databaseSizeBeforeUpdate = emmision_pollution_controllRepository.findAll().size();

        // Create the Emmision_pollution_controll
        Emmision_pollution_controllDTO emmision_pollution_controllDTO = emmision_pollution_controllMapper.emmision_pollution_controllToEmmision_pollution_controllDTO(emmision_pollution_controll);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmmision_pollution_controllMockMvc.perform(put("/api/emmision-pollution-controlls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(emmision_pollution_controllDTO)))
            .andExpect(status().isCreated());

        // Validate the Emmision_pollution_controll in the database
        List<Emmision_pollution_controll> emmision_pollution_controllList = emmision_pollution_controllRepository.findAll();
        assertThat(emmision_pollution_controllList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEmmision_pollution_controll() throws Exception {
        // Initialize the database
        emmision_pollution_controllRepository.save(emmision_pollution_controll);
        int databaseSizeBeforeDelete = emmision_pollution_controllRepository.findAll().size();

        // Get the emmision_pollution_controll
        restEmmision_pollution_controllMockMvc.perform(delete("/api/emmision-pollution-controlls/{id}", emmision_pollution_controll.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Emmision_pollution_controll> emmision_pollution_controllList = emmision_pollution_controllRepository.findAll();
        assertThat(emmision_pollution_controllList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emmision_pollution_controll.class);
    }
}
