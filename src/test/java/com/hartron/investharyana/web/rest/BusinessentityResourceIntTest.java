package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Businessentity;
import com.hartron.investharyana.repository.BusinessentityRepository;
import com.hartron.investharyana.service.BusinessentityService;
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
 * Test class for the BusinessentityResource REST controller.
 *
 * @see BusinessentityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class BusinessentityResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_BUSINESSENTITYTYPE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSENTITYTYPE = "BBBBBBBBBB";

    @Autowired
    private BusinessentityRepository businessentityRepository;

    @Autowired
    private BusinessentityService businessentityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restBusinessentityMockMvc;

    private Businessentity businessentity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BusinessentityResource businessentityResource = new BusinessentityResource(businessentityService);
        this.restBusinessentityMockMvc = MockMvcBuilders.standaloneSetup(businessentityResource)
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
    public static Businessentity createEntity() {
        Businessentity businessentity = new Businessentity()
                .businessentitytype(DEFAULT_BUSINESSENTITYTYPE);
        return businessentity;
    }

    @Before
    public void initTest() {
        businessentityRepository.deleteAll();
        businessentity = createEntity();
    }

    @Test
    public void createBusinessentity() throws Exception {
        int databaseSizeBeforeCreate = businessentityRepository.findAll().size();

        // Create the Businessentity

        restBusinessentityMockMvc.perform(post("/api/businessentities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessentity)))
            .andExpect(status().isCreated());

        // Validate the Businessentity in the database
        List<Businessentity> businessentityList = businessentityRepository.findAll();
        assertThat(businessentityList).hasSize(databaseSizeBeforeCreate + 1);
        Businessentity testBusinessentity = businessentityList.get(businessentityList.size() - 1);
        assertThat(testBusinessentity.getBusinessentitytype()).isEqualTo(DEFAULT_BUSINESSENTITYTYPE);
    }

    @Test
    public void createBusinessentityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessentityRepository.findAll().size();

        // Create the Businessentity with an existing ID
        Businessentity existingBusinessentity = new Businessentity();
        existingBusinessentity.setId(UUID.randomUUID());

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessentityMockMvc.perform(post("/api/businessentities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingBusinessentity)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Businessentity> businessentityList = businessentityRepository.findAll();
        assertThat(businessentityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllBusinessentities() throws Exception {
        // Initialize the database
        businessentityRepository.save(businessentity);

        // Get all the businessentityList
        restBusinessentityMockMvc.perform(get("/api/businessentities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessentity.getId().toString())))
            .andExpect(jsonPath("$.[*].businessentitytype").value(hasItem(DEFAULT_BUSINESSENTITYTYPE.toString())));
    }

    @Test
    public void getBusinessentity() throws Exception {
        // Initialize the database
        businessentityRepository.save(businessentity);

        // Get the businessentity
        restBusinessentityMockMvc.perform(get("/api/businessentities/{id}", businessentity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(businessentity.getId().toString()))
            .andExpect(jsonPath("$.businessentitytype").value(DEFAULT_BUSINESSENTITYTYPE.toString()));
    }

    @Test
    public void getNonExistingBusinessentity() throws Exception {
        // Get the businessentity
        restBusinessentityMockMvc.perform(get("/api/businessentities/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBusinessentity() throws Exception {
        // Initialize the database
        businessentityService.save(businessentity);

        int databaseSizeBeforeUpdate = businessentityRepository.findAll().size();

        // Update the businessentity
        Businessentity updatedBusinessentity = businessentityRepository.findOne(businessentity.getId());
        updatedBusinessentity
                .businessentitytype(UPDATED_BUSINESSENTITYTYPE);

        restBusinessentityMockMvc.perform(put("/api/businessentities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBusinessentity)))
            .andExpect(status().isOk());

        // Validate the Businessentity in the database
        List<Businessentity> businessentityList = businessentityRepository.findAll();
        assertThat(businessentityList).hasSize(databaseSizeBeforeUpdate);
        Businessentity testBusinessentity = businessentityList.get(businessentityList.size() - 1);
        assertThat(testBusinessentity.getBusinessentitytype()).isEqualTo(UPDATED_BUSINESSENTITYTYPE);
    }

    @Test
    public void updateNonExistingBusinessentity() throws Exception {
        int databaseSizeBeforeUpdate = businessentityRepository.findAll().size();

        // Create the Businessentity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBusinessentityMockMvc.perform(put("/api/businessentities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessentity)))
            .andExpect(status().isCreated());

        // Validate the Businessentity in the database
        List<Businessentity> businessentityList = businessentityRepository.findAll();
        assertThat(businessentityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteBusinessentity() throws Exception {
        // Initialize the database
        businessentityService.save(businessentity);

        int databaseSizeBeforeDelete = businessentityRepository.findAll().size();

        // Get the businessentity
        restBusinessentityMockMvc.perform(delete("/api/businessentities/{id}", businessentity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Businessentity> businessentityList = businessentityRepository.findAll();
        assertThat(businessentityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Businessentity.class);
    }
}
