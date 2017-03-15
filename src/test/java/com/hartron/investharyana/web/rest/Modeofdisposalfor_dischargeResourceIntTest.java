package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Modeofdisposalfor_discharge;
import com.hartron.investharyana.repository.Modeofdisposalfor_dischargeRepository;
import com.hartron.investharyana.service.Modeofdisposalfor_dischargeService;
import com.hartron.investharyana.service.dto.Modeofdisposalfor_dischargeDTO;
import com.hartron.investharyana.service.mapper.Modeofdisposalfor_dischargeMapper;
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
 * Test class for the Modeofdisposalfor_dischargeResource REST controller.
 *
 * @see Modeofdisposalfor_dischargeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Modeofdisposalfor_dischargeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_DISPOSAL_FOR_DISCHARGE = "AAAAAAAAAA";
    private static final String UPDATED_DISPOSAL_FOR_DISCHARGE = "BBBBBBBBBB";

    @Autowired
    private Modeofdisposalfor_dischargeRepository modeofdisposalfor_dischargeRepository;

    @Autowired
    private Modeofdisposalfor_dischargeMapper modeofdisposalfor_dischargeMapper;

    @Autowired
    private Modeofdisposalfor_dischargeService modeofdisposalfor_dischargeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restModeofdisposalfor_dischargeMockMvc;

    private Modeofdisposalfor_discharge modeofdisposalfor_discharge;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Modeofdisposalfor_dischargeResource modeofdisposalfor_dischargeResource = new Modeofdisposalfor_dischargeResource(modeofdisposalfor_dischargeService);
        this.restModeofdisposalfor_dischargeMockMvc = MockMvcBuilders.standaloneSetup(modeofdisposalfor_dischargeResource)
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
    public static Modeofdisposalfor_discharge createEntity() {
        Modeofdisposalfor_discharge modeofdisposalfor_discharge = new Modeofdisposalfor_discharge()
                .disposal_for_discharge(DEFAULT_DISPOSAL_FOR_DISCHARGE);
        return modeofdisposalfor_discharge;
    }

    @Before
    public void initTest() {
        modeofdisposalfor_dischargeRepository.deleteAll();
        modeofdisposalfor_discharge = createEntity();
    }

    @Test
    public void createModeofdisposalfor_discharge() throws Exception {
        int databaseSizeBeforeCreate = modeofdisposalfor_dischargeRepository.findAll().size();

        // Create the Modeofdisposalfor_discharge
        Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO = modeofdisposalfor_dischargeMapper.modeofdisposalfor_dischargeToModeofdisposalfor_dischargeDTO(modeofdisposalfor_discharge);

        restModeofdisposalfor_dischargeMockMvc.perform(post("/api/modeofdisposalfor-discharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modeofdisposalfor_dischargeDTO)))
            .andExpect(status().isCreated());

        // Validate the Modeofdisposalfor_discharge in the database
        List<Modeofdisposalfor_discharge> modeofdisposalfor_dischargeList = modeofdisposalfor_dischargeRepository.findAll();
        assertThat(modeofdisposalfor_dischargeList).hasSize(databaseSizeBeforeCreate + 1);
        Modeofdisposalfor_discharge testModeofdisposalfor_discharge = modeofdisposalfor_dischargeList.get(modeofdisposalfor_dischargeList.size() - 1);
        assertThat(testModeofdisposalfor_discharge.getDisposal_for_discharge()).isEqualTo(DEFAULT_DISPOSAL_FOR_DISCHARGE);
    }

    @Test
    public void createModeofdisposalfor_dischargeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modeofdisposalfor_dischargeRepository.findAll().size();

        // Create the Modeofdisposalfor_discharge with an existing ID
        Modeofdisposalfor_discharge existingModeofdisposalfor_discharge = new Modeofdisposalfor_discharge();
        existingModeofdisposalfor_discharge.setId(UUID.randomUUID());
        Modeofdisposalfor_dischargeDTO existingModeofdisposalfor_dischargeDTO = modeofdisposalfor_dischargeMapper.modeofdisposalfor_dischargeToModeofdisposalfor_dischargeDTO(existingModeofdisposalfor_discharge);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModeofdisposalfor_dischargeMockMvc.perform(post("/api/modeofdisposalfor-discharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingModeofdisposalfor_dischargeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Modeofdisposalfor_discharge> modeofdisposalfor_dischargeList = modeofdisposalfor_dischargeRepository.findAll();
        assertThat(modeofdisposalfor_dischargeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllModeofdisposalfor_discharges() throws Exception {
        // Initialize the database
        modeofdisposalfor_dischargeRepository.save(modeofdisposalfor_discharge);

        // Get all the modeofdisposalfor_dischargeList
        restModeofdisposalfor_dischargeMockMvc.perform(get("/api/modeofdisposalfor-discharges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modeofdisposalfor_discharge.getId().toString())))
            .andExpect(jsonPath("$.[*].disposal_for_discharge").value(hasItem(DEFAULT_DISPOSAL_FOR_DISCHARGE.toString())));
    }

    @Test
    public void getModeofdisposalfor_discharge() throws Exception {
        // Initialize the database
        modeofdisposalfor_dischargeRepository.save(modeofdisposalfor_discharge);

        // Get the modeofdisposalfor_discharge
        restModeofdisposalfor_dischargeMockMvc.perform(get("/api/modeofdisposalfor-discharges/{id}", modeofdisposalfor_discharge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(modeofdisposalfor_discharge.getId().toString()))
            .andExpect(jsonPath("$.disposal_for_discharge").value(DEFAULT_DISPOSAL_FOR_DISCHARGE.toString()));
    }

    @Test
    public void getNonExistingModeofdisposalfor_discharge() throws Exception {
        // Get the modeofdisposalfor_discharge
        restModeofdisposalfor_dischargeMockMvc.perform(get("/api/modeofdisposalfor-discharges/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateModeofdisposalfor_discharge() throws Exception {
        // Initialize the database
        modeofdisposalfor_dischargeRepository.save(modeofdisposalfor_discharge);
        int databaseSizeBeforeUpdate = modeofdisposalfor_dischargeRepository.findAll().size();

        // Update the modeofdisposalfor_discharge
        Modeofdisposalfor_discharge updatedModeofdisposalfor_discharge = modeofdisposalfor_dischargeRepository.findOne(modeofdisposalfor_discharge.getId());
        updatedModeofdisposalfor_discharge
                .disposal_for_discharge(UPDATED_DISPOSAL_FOR_DISCHARGE);
        Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO = modeofdisposalfor_dischargeMapper.modeofdisposalfor_dischargeToModeofdisposalfor_dischargeDTO(updatedModeofdisposalfor_discharge);

        restModeofdisposalfor_dischargeMockMvc.perform(put("/api/modeofdisposalfor-discharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modeofdisposalfor_dischargeDTO)))
            .andExpect(status().isOk());

        // Validate the Modeofdisposalfor_discharge in the database
        List<Modeofdisposalfor_discharge> modeofdisposalfor_dischargeList = modeofdisposalfor_dischargeRepository.findAll();
        assertThat(modeofdisposalfor_dischargeList).hasSize(databaseSizeBeforeUpdate);
        Modeofdisposalfor_discharge testModeofdisposalfor_discharge = modeofdisposalfor_dischargeList.get(modeofdisposalfor_dischargeList.size() - 1);
        assertThat(testModeofdisposalfor_discharge.getDisposal_for_discharge()).isEqualTo(UPDATED_DISPOSAL_FOR_DISCHARGE);
    }

    @Test
    public void updateNonExistingModeofdisposalfor_discharge() throws Exception {
        int databaseSizeBeforeUpdate = modeofdisposalfor_dischargeRepository.findAll().size();

        // Create the Modeofdisposalfor_discharge
        Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO = modeofdisposalfor_dischargeMapper.modeofdisposalfor_dischargeToModeofdisposalfor_dischargeDTO(modeofdisposalfor_discharge);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restModeofdisposalfor_dischargeMockMvc.perform(put("/api/modeofdisposalfor-discharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modeofdisposalfor_dischargeDTO)))
            .andExpect(status().isCreated());

        // Validate the Modeofdisposalfor_discharge in the database
        List<Modeofdisposalfor_discharge> modeofdisposalfor_dischargeList = modeofdisposalfor_dischargeRepository.findAll();
        assertThat(modeofdisposalfor_dischargeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteModeofdisposalfor_discharge() throws Exception {
        // Initialize the database
        modeofdisposalfor_dischargeRepository.save(modeofdisposalfor_discharge);
        int databaseSizeBeforeDelete = modeofdisposalfor_dischargeRepository.findAll().size();

        // Get the modeofdisposalfor_discharge
        restModeofdisposalfor_dischargeMockMvc.perform(delete("/api/modeofdisposalfor-discharges/{id}", modeofdisposalfor_discharge.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Modeofdisposalfor_discharge> modeofdisposalfor_dischargeList = modeofdisposalfor_dischargeRepository.findAll();
        assertThat(modeofdisposalfor_dischargeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Modeofdisposalfor_discharge.class);
    }
}
