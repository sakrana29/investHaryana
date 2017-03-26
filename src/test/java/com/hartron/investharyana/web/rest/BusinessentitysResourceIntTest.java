package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Businessentitys;
import com.hartron.investharyana.repository.BusinessentitysRepository;
import com.hartron.investharyana.service.BusinessentitysService;
import com.hartron.investharyana.service.dto.BusinessentitysDTO;
import com.hartron.investharyana.service.mapper.BusinessentitysMapper;
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
 * Test class for the BusinessentitysResource REST controller.
 *
 * @see BusinessentitysResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class BusinessentitysResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_BUSINESSENTITYTYPE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSENTITYTYPE = "BBBBBBBBBB";

    @Autowired
    private BusinessentitysRepository businessentitysRepository;

    @Autowired
    private BusinessentitysMapper businessentitysMapper;

    @Autowired
    private BusinessentitysService businessentitysService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restBusinessentitysMockMvc;

    private Businessentitys businessentitys;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BusinessentitysResource businessentitysResource = new BusinessentitysResource(businessentitysService);
        this.restBusinessentitysMockMvc = MockMvcBuilders.standaloneSetup(businessentitysResource)
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
    public static Businessentitys createEntity() {
        Businessentitys businessentitys = new Businessentitys()
                .businessentitytype(DEFAULT_BUSINESSENTITYTYPE);
        return businessentitys;
    }

    @Before
    public void initTest() {
        businessentitysRepository.deleteAll();
        businessentitys = createEntity();
    }

    @Test
    public void createBusinessentitys() throws Exception {
        int databaseSizeBeforeCreate = businessentitysRepository.findAll().size();

        // Create the Businessentitys
        BusinessentitysDTO businessentitysDTO = businessentitysMapper.businessentitysToBusinessentitysDTO(businessentitys);

        restBusinessentitysMockMvc.perform(post("/api/businessentitys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessentitysDTO)))
            .andExpect(status().isCreated());

        // Validate the Businessentitys in the database
        List<Businessentitys> businessentitysList = businessentitysRepository.findAll();
        assertThat(businessentitysList).hasSize(databaseSizeBeforeCreate + 1);
        Businessentitys testBusinessentitys = businessentitysList.get(businessentitysList.size() - 1);
        assertThat(testBusinessentitys.getBusinessentitytype()).isEqualTo(DEFAULT_BUSINESSENTITYTYPE);
    }

    @Test
    public void createBusinessentitysWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessentitysRepository.findAll().size();

        // Create the Businessentitys with an existing ID
        Businessentitys existingBusinessentitys = new Businessentitys();
        existingBusinessentitys.setId(UUID.randomUUID());
        BusinessentitysDTO existingBusinessentitysDTO = businessentitysMapper.businessentitysToBusinessentitysDTO(existingBusinessentitys);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessentitysMockMvc.perform(post("/api/businessentitys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingBusinessentitysDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Businessentitys> businessentitysList = businessentitysRepository.findAll();
        assertThat(businessentitysList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllBusinessentitys() throws Exception {
        // Initialize the database
        businessentitysRepository.save(businessentitys);

        // Get all the businessentitysList
        restBusinessentitysMockMvc.perform(get("/api/businessentitys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessentitys.getId().toString())))
            .andExpect(jsonPath("$.[*].businessentitytype").value(hasItem(DEFAULT_BUSINESSENTITYTYPE.toString())));
    }

    @Test
    public void getBusinessentitys() throws Exception {
        // Initialize the database
        businessentitysRepository.save(businessentitys);

        // Get the businessentitys
        restBusinessentitysMockMvc.perform(get("/api/businessentitys/{id}", businessentitys.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(businessentitys.getId().toString()))
            .andExpect(jsonPath("$.businessentitytype").value(DEFAULT_BUSINESSENTITYTYPE.toString()));
    }

    @Test
    public void getNonExistingBusinessentitys() throws Exception {
        // Get the businessentitys
        restBusinessentitysMockMvc.perform(get("/api/businessentitys/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBusinessentitys() throws Exception {
        // Initialize the database
        businessentitysRepository.save(businessentitys);
        int databaseSizeBeforeUpdate = businessentitysRepository.findAll().size();

        // Update the businessentitys
        Businessentitys updatedBusinessentitys = businessentitysRepository.findOne(businessentitys.getId());
        updatedBusinessentitys
                .businessentitytype(UPDATED_BUSINESSENTITYTYPE);
        BusinessentitysDTO businessentitysDTO = businessentitysMapper.businessentitysToBusinessentitysDTO(updatedBusinessentitys);

        restBusinessentitysMockMvc.perform(put("/api/businessentitys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessentitysDTO)))
            .andExpect(status().isOk());

        // Validate the Businessentitys in the database
        List<Businessentitys> businessentitysList = businessentitysRepository.findAll();
        assertThat(businessentitysList).hasSize(databaseSizeBeforeUpdate);
        Businessentitys testBusinessentitys = businessentitysList.get(businessentitysList.size() - 1);
        assertThat(testBusinessentitys.getBusinessentitytype()).isEqualTo(UPDATED_BUSINESSENTITYTYPE);
    }

    @Test
    public void updateNonExistingBusinessentitys() throws Exception {
        int databaseSizeBeforeUpdate = businessentitysRepository.findAll().size();

        // Create the Businessentitys
        BusinessentitysDTO businessentitysDTO = businessentitysMapper.businessentitysToBusinessentitysDTO(businessentitys);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBusinessentitysMockMvc.perform(put("/api/businessentitys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessentitysDTO)))
            .andExpect(status().isCreated());

        // Validate the Businessentitys in the database
        List<Businessentitys> businessentitysList = businessentitysRepository.findAll();
        assertThat(businessentitysList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteBusinessentitys() throws Exception {
        // Initialize the database
        businessentitysRepository.save(businessentitys);
        int databaseSizeBeforeDelete = businessentitysRepository.findAll().size();

        // Get the businessentitys
        restBusinessentitysMockMvc.perform(delete("/api/businessentitys/{id}", businessentitys.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Businessentitys> businessentitysList = businessentitysRepository.findAll();
        assertThat(businessentitysList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Businessentitys.class);
    }
}
