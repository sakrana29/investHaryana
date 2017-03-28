package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.City_town_village;
import com.hartron.investharyana.repository.City_town_villageRepository;
import com.hartron.investharyana.service.City_town_villageService;
import com.hartron.investharyana.service.dto.City_town_villageDTO;
import com.hartron.investharyana.service.mapper.City_town_villageMapper;
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
 * Test class for the City_town_villageResource REST controller.
 *
 * @see City_town_villageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class City_town_villageResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_CITY_TOWN_VILLAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CITY_TOWN_VILLAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCKNAME = "AAAAAAAAAA";
    private static final String UPDATED_BLOCKNAME = "BBBBBBBBBB";

    @Autowired
    private City_town_villageRepository city_town_villageRepository;

    @Autowired
    private City_town_villageMapper city_town_villageMapper;

    @Autowired
    private City_town_villageService city_town_villageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCity_town_villageMockMvc;

    private City_town_village city_town_village;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        City_town_villageResource city_town_villageResource = new City_town_villageResource(city_town_villageService);
        this.restCity_town_villageMockMvc = MockMvcBuilders.standaloneSetup(city_town_villageResource)
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
    public static City_town_village createEntity() {
        City_town_village city_town_village = new City_town_village()
                .city_town_village_name(DEFAULT_CITY_TOWN_VILLAGE_NAME)
                .blockname(DEFAULT_BLOCKNAME);
        return city_town_village;
    }

    @Before
    public void initTest() {
        city_town_villageRepository.deleteAll();
        city_town_village = createEntity();
    }

    @Test
    public void createCity_town_village() throws Exception {
        int databaseSizeBeforeCreate = city_town_villageRepository.findAll().size();

        // Create the City_town_village
        City_town_villageDTO city_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(city_town_village);

        restCity_town_villageMockMvc.perform(post("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city_town_villageDTO)))
            .andExpect(status().isCreated());

        // Validate the City_town_village in the database
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeCreate + 1);
        City_town_village testCity_town_village = city_town_villageList.get(city_town_villageList.size() - 1);
        assertThat(testCity_town_village.getCity_town_village_name()).isEqualTo(DEFAULT_CITY_TOWN_VILLAGE_NAME);
        assertThat(testCity_town_village.getBlockname()).isEqualTo(DEFAULT_BLOCKNAME);
    }

    @Test
    public void createCity_town_villageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = city_town_villageRepository.findAll().size();

        // Create the City_town_village with an existing ID
        City_town_village existingCity_town_village = new City_town_village();
        existingCity_town_village.setId(UUID.randomUUID());
        City_town_villageDTO existingCity_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(existingCity_town_village);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCity_town_villageMockMvc.perform(post("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCity_town_villageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkCity_town_village_nameIsRequired() throws Exception {
        int databaseSizeBeforeTest = city_town_villageRepository.findAll().size();
        // set the field null
        city_town_village.setCity_town_village_name(null);

        // Create the City_town_village, which fails.
        City_town_villageDTO city_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(city_town_village);

        restCity_town_villageMockMvc.perform(post("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city_town_villageDTO)))
            .andExpect(status().isBadRequest());

        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllCity_town_villages() throws Exception {
        // Initialize the database
        city_town_villageRepository.save(city_town_village);

        // Get all the city_town_villageList
        restCity_town_villageMockMvc.perform(get("/api/city-town-villages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(city_town_village.getId().toString())))
            .andExpect(jsonPath("$.[*].city_town_village_name").value(hasItem(DEFAULT_CITY_TOWN_VILLAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].blockname").value(hasItem(DEFAULT_BLOCKNAME.toString())));
    }

    @Test
    public void getCity_town_village() throws Exception {
        // Initialize the database
        city_town_villageRepository.save(city_town_village);

        // Get the city_town_village
        restCity_town_villageMockMvc.perform(get("/api/city-town-villages/{id}", city_town_village.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(city_town_village.getId().toString()))
            .andExpect(jsonPath("$.city_town_village_name").value(DEFAULT_CITY_TOWN_VILLAGE_NAME.toString()))
            .andExpect(jsonPath("$.blockname").value(DEFAULT_BLOCKNAME.toString()));
    }

    @Test
    public void getNonExistingCity_town_village() throws Exception {
        // Get the city_town_village
        restCity_town_villageMockMvc.perform(get("/api/city-town-villages/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCity_town_village() throws Exception {
        // Initialize the database
        city_town_villageRepository.save(city_town_village);
        int databaseSizeBeforeUpdate = city_town_villageRepository.findAll().size();

        // Update the city_town_village
        City_town_village updatedCity_town_village = city_town_villageRepository.findOne(city_town_village.getId());
        updatedCity_town_village
                .city_town_village_name(UPDATED_CITY_TOWN_VILLAGE_NAME)
                .blockname(UPDATED_BLOCKNAME);
        City_town_villageDTO city_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(updatedCity_town_village);

        restCity_town_villageMockMvc.perform(put("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city_town_villageDTO)))
            .andExpect(status().isOk());

        // Validate the City_town_village in the database
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeUpdate);
        City_town_village testCity_town_village = city_town_villageList.get(city_town_villageList.size() - 1);
        assertThat(testCity_town_village.getCity_town_village_name()).isEqualTo(UPDATED_CITY_TOWN_VILLAGE_NAME);
        assertThat(testCity_town_village.getBlockname()).isEqualTo(UPDATED_BLOCKNAME);
    }

    @Test
    public void updateNonExistingCity_town_village() throws Exception {
        int databaseSizeBeforeUpdate = city_town_villageRepository.findAll().size();

        // Create the City_town_village
        City_town_villageDTO city_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(city_town_village);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCity_town_villageMockMvc.perform(put("/api/city-town-villages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(city_town_villageDTO)))
            .andExpect(status().isCreated());

        // Validate the City_town_village in the database
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCity_town_village() throws Exception {
        // Initialize the database
        city_town_villageRepository.save(city_town_village);
        int databaseSizeBeforeDelete = city_town_villageRepository.findAll().size();

        // Get the city_town_village
        restCity_town_villageMockMvc.perform(delete("/api/city-town-villages/{id}", city_town_village.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<City_town_village> city_town_villageList = city_town_villageRepository.findAll();
        assertThat(city_town_villageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(City_town_village.class);
    }
}
