package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Landusezoneclassification;
import com.hartron.investharyana.repository.LandusezoneclassificationRepository;
import com.hartron.investharyana.service.LandusezoneclassificationService;
import com.hartron.investharyana.service.dto.LandusezoneclassificationDTO;
import com.hartron.investharyana.service.mapper.LandusezoneclassificationMapper;
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
 * Test class for the LandusezoneclassificationResource REST controller.
 *
 * @see LandusezoneclassificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class LandusezoneclassificationResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_LANDZONECLASSIFICATIONTYPE = "AAAAAAAAAA";
    private static final String UPDATED_LANDZONECLASSIFICATIONTYPE = "BBBBBBBBBB";

    @Autowired
    private LandusezoneclassificationRepository landusezoneclassificationRepository;

    @Autowired
    private LandusezoneclassificationMapper landusezoneclassificationMapper;

    @Autowired
    private LandusezoneclassificationService landusezoneclassificationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restLandusezoneclassificationMockMvc;

    private Landusezoneclassification landusezoneclassification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LandusezoneclassificationResource landusezoneclassificationResource = new LandusezoneclassificationResource(landusezoneclassificationService);
        this.restLandusezoneclassificationMockMvc = MockMvcBuilders.standaloneSetup(landusezoneclassificationResource)
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
    public static Landusezoneclassification createEntity() {
        Landusezoneclassification landusezoneclassification = new Landusezoneclassification()
                .landzoneclassificationtype(DEFAULT_LANDZONECLASSIFICATIONTYPE);
        return landusezoneclassification;
    }

    @Before
    public void initTest() {
        landusezoneclassificationRepository.deleteAll();
        landusezoneclassification = createEntity();
    }

    @Test
    public void createLandusezoneclassification() throws Exception {
        int databaseSizeBeforeCreate = landusezoneclassificationRepository.findAll().size();

        // Create the Landusezoneclassification
        LandusezoneclassificationDTO landusezoneclassificationDTO = landusezoneclassificationMapper.landusezoneclassificationToLandusezoneclassificationDTO(landusezoneclassification);

        restLandusezoneclassificationMockMvc.perform(post("/api/landusezoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landusezoneclassificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Landusezoneclassification in the database
        List<Landusezoneclassification> landusezoneclassificationList = landusezoneclassificationRepository.findAll();
        assertThat(landusezoneclassificationList).hasSize(databaseSizeBeforeCreate + 1);
        Landusezoneclassification testLandusezoneclassification = landusezoneclassificationList.get(landusezoneclassificationList.size() - 1);
        assertThat(testLandusezoneclassification.getLandzoneclassificationtype()).isEqualTo(DEFAULT_LANDZONECLASSIFICATIONTYPE);
    }

    @Test
    public void createLandusezoneclassificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = landusezoneclassificationRepository.findAll().size();

        // Create the Landusezoneclassification with an existing ID
        Landusezoneclassification existingLandusezoneclassification = new Landusezoneclassification();
        existingLandusezoneclassification.setId(UUID.randomUUID());
        LandusezoneclassificationDTO existingLandusezoneclassificationDTO = landusezoneclassificationMapper.landusezoneclassificationToLandusezoneclassificationDTO(existingLandusezoneclassification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLandusezoneclassificationMockMvc.perform(post("/api/landusezoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingLandusezoneclassificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Landusezoneclassification> landusezoneclassificationList = landusezoneclassificationRepository.findAll();
        assertThat(landusezoneclassificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkLandzoneclassificationtypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = landusezoneclassificationRepository.findAll().size();
        // set the field null
        landusezoneclassification.setLandzoneclassificationtype(null);

        // Create the Landusezoneclassification, which fails.
        LandusezoneclassificationDTO landusezoneclassificationDTO = landusezoneclassificationMapper.landusezoneclassificationToLandusezoneclassificationDTO(landusezoneclassification);

        restLandusezoneclassificationMockMvc.perform(post("/api/landusezoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landusezoneclassificationDTO)))
            .andExpect(status().isBadRequest());

        List<Landusezoneclassification> landusezoneclassificationList = landusezoneclassificationRepository.findAll();
        assertThat(landusezoneclassificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllLandusezoneclassifications() throws Exception {
        // Initialize the database
        landusezoneclassificationRepository.save(landusezoneclassification);

        // Get all the landusezoneclassificationList
        restLandusezoneclassificationMockMvc.perform(get("/api/landusezoneclassifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(landusezoneclassification.getId().toString())))
            .andExpect(jsonPath("$.[*].landzoneclassificationtype").value(hasItem(DEFAULT_LANDZONECLASSIFICATIONTYPE.toString())));
    }

    @Test
    public void getLandusezoneclassification() throws Exception {
        // Initialize the database
        landusezoneclassificationRepository.save(landusezoneclassification);

        // Get the landusezoneclassification
        restLandusezoneclassificationMockMvc.perform(get("/api/landusezoneclassifications/{id}", landusezoneclassification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(landusezoneclassification.getId().toString()))
            .andExpect(jsonPath("$.landzoneclassificationtype").value(DEFAULT_LANDZONECLASSIFICATIONTYPE.toString()));
    }

    @Test
    public void getNonExistingLandusezoneclassification() throws Exception {
        // Get the landusezoneclassification
        restLandusezoneclassificationMockMvc.perform(get("/api/landusezoneclassifications/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLandusezoneclassification() throws Exception {
        // Initialize the database
        landusezoneclassificationRepository.save(landusezoneclassification);
        int databaseSizeBeforeUpdate = landusezoneclassificationRepository.findAll().size();

        // Update the landusezoneclassification
        Landusezoneclassification updatedLandusezoneclassification = landusezoneclassificationRepository.findOne(landusezoneclassification.getId());
        updatedLandusezoneclassification
                .landzoneclassificationtype(UPDATED_LANDZONECLASSIFICATIONTYPE);
        LandusezoneclassificationDTO landusezoneclassificationDTO = landusezoneclassificationMapper.landusezoneclassificationToLandusezoneclassificationDTO(updatedLandusezoneclassification);

        restLandusezoneclassificationMockMvc.perform(put("/api/landusezoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landusezoneclassificationDTO)))
            .andExpect(status().isOk());

        // Validate the Landusezoneclassification in the database
        List<Landusezoneclassification> landusezoneclassificationList = landusezoneclassificationRepository.findAll();
        assertThat(landusezoneclassificationList).hasSize(databaseSizeBeforeUpdate);
        Landusezoneclassification testLandusezoneclassification = landusezoneclassificationList.get(landusezoneclassificationList.size() - 1);
        assertThat(testLandusezoneclassification.getLandzoneclassificationtype()).isEqualTo(UPDATED_LANDZONECLASSIFICATIONTYPE);
    }

    @Test
    public void updateNonExistingLandusezoneclassification() throws Exception {
        int databaseSizeBeforeUpdate = landusezoneclassificationRepository.findAll().size();

        // Create the Landusezoneclassification
        LandusezoneclassificationDTO landusezoneclassificationDTO = landusezoneclassificationMapper.landusezoneclassificationToLandusezoneclassificationDTO(landusezoneclassification);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLandusezoneclassificationMockMvc.perform(put("/api/landusezoneclassifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(landusezoneclassificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Landusezoneclassification in the database
        List<Landusezoneclassification> landusezoneclassificationList = landusezoneclassificationRepository.findAll();
        assertThat(landusezoneclassificationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteLandusezoneclassification() throws Exception {
        // Initialize the database
        landusezoneclassificationRepository.save(landusezoneclassification);
        int databaseSizeBeforeDelete = landusezoneclassificationRepository.findAll().size();

        // Get the landusezoneclassification
        restLandusezoneclassificationMockMvc.perform(delete("/api/landusezoneclassifications/{id}", landusezoneclassification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Landusezoneclassification> landusezoneclassificationList = landusezoneclassificationRepository.findAll();
        assertThat(landusezoneclassificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Landusezoneclassification.class);
    }
}
