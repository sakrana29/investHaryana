package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Environment_impactdetail;
import com.hartron.investharyana.repository.Environment_impactdetailRepository;
import com.hartron.investharyana.service.Environment_impactdetailService;
import com.hartron.investharyana.service.dto.Environment_impactdetailDTO;
import com.hartron.investharyana.service.mapper.Environment_impactdetailMapper;
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
 * Test class for the Environment_impactdetailResource REST controller.
 *
 * @see Environment_impactdetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Environment_impactdetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final UUID DEFAULT_SOURCE_OF_WATER_SUPPLY = UUID.randomUUID();
    private static final UUID UPDATED_SOURCE_OF_WATER_SUPPLY = UUID.randomUUID();

    private static final Integer DEFAULT_WATER_PROCESS = 1;
    private static final Integer UPDATED_WATER_PROCESS = 2;

    private static final Integer DEFAULT_WATER_COOLING = 1;
    private static final Integer UPDATED_WATER_COOLING = 2;

    private static final Integer DEFAULT_WATER_DOMESTIC = 1;
    private static final Integer UPDATED_WATER_DOMESTIC = 2;

    private static final Integer DEFAULT_WATER_OTHER = 1;
    private static final Integer UPDATED_WATER_OTHER = 2;

    private static final Integer DEFAULT_WASTE_WATER_PROCESS = 1;
    private static final Integer UPDATED_WASTE_WATER_PROCESS = 2;

    private static final Integer DEFAULT_WASTE_WATER_COOLING = 1;
    private static final Integer UPDATED_WASTE_WATER_COOLING = 2;

    private static final Integer DEFAULT_WASTE_WATER_DOMESTING = 1;
    private static final Integer UPDATED_WASTE_WATER_DOMESTING = 2;

    private static final Integer DEFAULT_WASTE_WATER_OTHER = 1;
    private static final Integer UPDATED_WASTE_WATER_OTHER = 2;

    private static final String DEFAULT_WASTE_WATER_TREATMENT = "AAAAAAAAAA";
    private static final String UPDATED_WASTE_WATER_TREATMENT = "BBBBBBBBBB";

    private static final UUID DEFAULT_MODE_OF_DISPOSAL_FOR_DISCHARGE = UUID.randomUUID();
    private static final UUID UPDATED_MODE_OF_DISPOSAL_FOR_DISCHARGE = UUID.randomUUID();

    private static final UUID DEFAULT_EMISSIONID = UUID.randomUUID();
    private static final UUID UPDATED_EMISSIONID = UUID.randomUUID();

    private static final UUID DEFAULT_WASTEWATERDETAILID = UUID.randomUUID();
    private static final UUID UPDATED_WASTEWATERDETAILID = UUID.randomUUID();

    private static final String DEFAULT_DOCUMENT_ATTACHED = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_ATTACHED = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCEOFWATERSUPPLY = "AAAAAAAAAA";
    private static final String UPDATED_SOURCEOFWATERSUPPLY = "BBBBBBBBBB";

    private static final String DEFAULT_MODEOFDISPOSALFORDISCHARGE = "AAAAAAAAAA";
    private static final String UPDATED_MODEOFDISPOSALFORDISCHARGE = "BBBBBBBBBB";

    @Autowired
    private Environment_impactdetailRepository environment_impactdetailRepository;

    @Autowired
    private Environment_impactdetailMapper environment_impactdetailMapper;

    @Autowired
    private Environment_impactdetailService environment_impactdetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEnvironment_impactdetailMockMvc;

    private Environment_impactdetail environment_impactdetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Environment_impactdetailResource environment_impactdetailResource = new Environment_impactdetailResource(environment_impactdetailService);
        this.restEnvironment_impactdetailMockMvc = MockMvcBuilders.standaloneSetup(environment_impactdetailResource)
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
    public static Environment_impactdetail createEntity() {
        Environment_impactdetail environment_impactdetail = new Environment_impactdetail()
                .projectid(DEFAULT_PROJECTID)
                .source_of_water_supply(DEFAULT_SOURCE_OF_WATER_SUPPLY)
                .water_process(DEFAULT_WATER_PROCESS)
                .water_cooling(DEFAULT_WATER_COOLING)
                .water_domestic(DEFAULT_WATER_DOMESTIC)
                .water_other(DEFAULT_WATER_OTHER)
                .waste_water_process(DEFAULT_WASTE_WATER_PROCESS)
                .waste_water_cooling(DEFAULT_WASTE_WATER_COOLING)
                .waste_water_domesting(DEFAULT_WASTE_WATER_DOMESTING)
                .waste_water_other(DEFAULT_WASTE_WATER_OTHER)
                .waste_water_treatment(DEFAULT_WASTE_WATER_TREATMENT)
                .mode_of_disposal_for_discharge(DEFAULT_MODE_OF_DISPOSAL_FOR_DISCHARGE)
                .emissionid(DEFAULT_EMISSIONID)
                .wastewaterdetailid(DEFAULT_WASTEWATERDETAILID)
                .document_attached(DEFAULT_DOCUMENT_ATTACHED)
                .other(DEFAULT_OTHER)
                .projectname(DEFAULT_PROJECTNAME)
                .sourceofwatersupply(DEFAULT_SOURCEOFWATERSUPPLY)
                .modeofdisposalfordischarge(DEFAULT_MODEOFDISPOSALFORDISCHARGE);
        return environment_impactdetail;
    }

    @Before
    public void initTest() {
        environment_impactdetailRepository.deleteAll();
        environment_impactdetail = createEntity();
    }

    @Test
    public void createEnvironment_impactdetail() throws Exception {
        int databaseSizeBeforeCreate = environment_impactdetailRepository.findAll().size();

        // Create the Environment_impactdetail
        Environment_impactdetailDTO environment_impactdetailDTO = environment_impactdetailMapper.environment_impactdetailToEnvironment_impactdetailDTO(environment_impactdetail);

        restEnvironment_impactdetailMockMvc.perform(post("/api/environment-impactdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(environment_impactdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Environment_impactdetail in the database
        List<Environment_impactdetail> environment_impactdetailList = environment_impactdetailRepository.findAll();
        assertThat(environment_impactdetailList).hasSize(databaseSizeBeforeCreate + 1);
        Environment_impactdetail testEnvironment_impactdetail = environment_impactdetailList.get(environment_impactdetailList.size() - 1);
        assertThat(testEnvironment_impactdetail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testEnvironment_impactdetail.getSource_of_water_supply()).isEqualTo(DEFAULT_SOURCE_OF_WATER_SUPPLY);
        assertThat(testEnvironment_impactdetail.getWater_process()).isEqualTo(DEFAULT_WATER_PROCESS);
        assertThat(testEnvironment_impactdetail.getWater_cooling()).isEqualTo(DEFAULT_WATER_COOLING);
        assertThat(testEnvironment_impactdetail.getWater_domestic()).isEqualTo(DEFAULT_WATER_DOMESTIC);
        assertThat(testEnvironment_impactdetail.getWater_other()).isEqualTo(DEFAULT_WATER_OTHER);
        assertThat(testEnvironment_impactdetail.getWaste_water_process()).isEqualTo(DEFAULT_WASTE_WATER_PROCESS);
        assertThat(testEnvironment_impactdetail.getWaste_water_cooling()).isEqualTo(DEFAULT_WASTE_WATER_COOLING);
        assertThat(testEnvironment_impactdetail.getWaste_water_domesting()).isEqualTo(DEFAULT_WASTE_WATER_DOMESTING);
        assertThat(testEnvironment_impactdetail.getWaste_water_other()).isEqualTo(DEFAULT_WASTE_WATER_OTHER);
        assertThat(testEnvironment_impactdetail.getWaste_water_treatment()).isEqualTo(DEFAULT_WASTE_WATER_TREATMENT);
        assertThat(testEnvironment_impactdetail.getMode_of_disposal_for_discharge()).isEqualTo(DEFAULT_MODE_OF_DISPOSAL_FOR_DISCHARGE);
        assertThat(testEnvironment_impactdetail.getEmissionid()).isEqualTo(DEFAULT_EMISSIONID);
        assertThat(testEnvironment_impactdetail.getWastewaterdetailid()).isEqualTo(DEFAULT_WASTEWATERDETAILID);
        assertThat(testEnvironment_impactdetail.getDocument_attached()).isEqualTo(DEFAULT_DOCUMENT_ATTACHED);
        assertThat(testEnvironment_impactdetail.getOther()).isEqualTo(DEFAULT_OTHER);
        assertThat(testEnvironment_impactdetail.getProjectname()).isEqualTo(DEFAULT_PROJECTNAME);
        assertThat(testEnvironment_impactdetail.getSourceofwatersupply()).isEqualTo(DEFAULT_SOURCEOFWATERSUPPLY);
        assertThat(testEnvironment_impactdetail.getModeofdisposalfordischarge()).isEqualTo(DEFAULT_MODEOFDISPOSALFORDISCHARGE);
    }

    @Test
    public void createEnvironment_impactdetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = environment_impactdetailRepository.findAll().size();

        // Create the Environment_impactdetail with an existing ID
        Environment_impactdetail existingEnvironment_impactdetail = new Environment_impactdetail();
        existingEnvironment_impactdetail.setId(UUID.randomUUID());
        Environment_impactdetailDTO existingEnvironment_impactdetailDTO = environment_impactdetailMapper.environment_impactdetailToEnvironment_impactdetailDTO(existingEnvironment_impactdetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnvironment_impactdetailMockMvc.perform(post("/api/environment-impactdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEnvironment_impactdetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Environment_impactdetail> environment_impactdetailList = environment_impactdetailRepository.findAll();
        assertThat(environment_impactdetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEnvironment_impactdetails() throws Exception {
        // Initialize the database
        environment_impactdetailRepository.save(environment_impactdetail);

        // Get all the environment_impactdetailList
        restEnvironment_impactdetailMockMvc.perform(get("/api/environment-impactdetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(environment_impactdetail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].source_of_water_supply").value(hasItem(DEFAULT_SOURCE_OF_WATER_SUPPLY.toString())))
            .andExpect(jsonPath("$.[*].water_process").value(hasItem(DEFAULT_WATER_PROCESS)))
            .andExpect(jsonPath("$.[*].water_cooling").value(hasItem(DEFAULT_WATER_COOLING)))
            .andExpect(jsonPath("$.[*].water_domestic").value(hasItem(DEFAULT_WATER_DOMESTIC)))
            .andExpect(jsonPath("$.[*].water_other").value(hasItem(DEFAULT_WATER_OTHER)))
            .andExpect(jsonPath("$.[*].waste_water_process").value(hasItem(DEFAULT_WASTE_WATER_PROCESS)))
            .andExpect(jsonPath("$.[*].waste_water_cooling").value(hasItem(DEFAULT_WASTE_WATER_COOLING)))
            .andExpect(jsonPath("$.[*].waste_water_domesting").value(hasItem(DEFAULT_WASTE_WATER_DOMESTING)))
            .andExpect(jsonPath("$.[*].waste_water_other").value(hasItem(DEFAULT_WASTE_WATER_OTHER)))
            .andExpect(jsonPath("$.[*].waste_water_treatment").value(hasItem(DEFAULT_WASTE_WATER_TREATMENT.toString())))
            .andExpect(jsonPath("$.[*].mode_of_disposal_for_discharge").value(hasItem(DEFAULT_MODE_OF_DISPOSAL_FOR_DISCHARGE.toString())))
            .andExpect(jsonPath("$.[*].emissionid").value(hasItem(DEFAULT_EMISSIONID.toString())))
            .andExpect(jsonPath("$.[*].wastewaterdetailid").value(hasItem(DEFAULT_WASTEWATERDETAILID.toString())))
            .andExpect(jsonPath("$.[*].document_attached").value(hasItem(DEFAULT_DOCUMENT_ATTACHED.toString())))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME.toString())))
            .andExpect(jsonPath("$.[*].sourceofwatersupply").value(hasItem(DEFAULT_SOURCEOFWATERSUPPLY.toString())))
            .andExpect(jsonPath("$.[*].modeofdisposalfordischarge").value(hasItem(DEFAULT_MODEOFDISPOSALFORDISCHARGE.toString())));
    }

    @Test
    public void getEnvironment_impactdetail() throws Exception {
        // Initialize the database
        environment_impactdetailRepository.save(environment_impactdetail);

        // Get the environment_impactdetail
        restEnvironment_impactdetailMockMvc.perform(get("/api/environment-impactdetails/{id}", environment_impactdetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(environment_impactdetail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.source_of_water_supply").value(DEFAULT_SOURCE_OF_WATER_SUPPLY.toString()))
            .andExpect(jsonPath("$.water_process").value(DEFAULT_WATER_PROCESS))
            .andExpect(jsonPath("$.water_cooling").value(DEFAULT_WATER_COOLING))
            .andExpect(jsonPath("$.water_domestic").value(DEFAULT_WATER_DOMESTIC))
            .andExpect(jsonPath("$.water_other").value(DEFAULT_WATER_OTHER))
            .andExpect(jsonPath("$.waste_water_process").value(DEFAULT_WASTE_WATER_PROCESS))
            .andExpect(jsonPath("$.waste_water_cooling").value(DEFAULT_WASTE_WATER_COOLING))
            .andExpect(jsonPath("$.waste_water_domesting").value(DEFAULT_WASTE_WATER_DOMESTING))
            .andExpect(jsonPath("$.waste_water_other").value(DEFAULT_WASTE_WATER_OTHER))
            .andExpect(jsonPath("$.waste_water_treatment").value(DEFAULT_WASTE_WATER_TREATMENT.toString()))
            .andExpect(jsonPath("$.mode_of_disposal_for_discharge").value(DEFAULT_MODE_OF_DISPOSAL_FOR_DISCHARGE.toString()))
            .andExpect(jsonPath("$.emissionid").value(DEFAULT_EMISSIONID.toString()))
            .andExpect(jsonPath("$.wastewaterdetailid").value(DEFAULT_WASTEWATERDETAILID.toString()))
            .andExpect(jsonPath("$.document_attached").value(DEFAULT_DOCUMENT_ATTACHED.toString()))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME.toString()))
            .andExpect(jsonPath("$.sourceofwatersupply").value(DEFAULT_SOURCEOFWATERSUPPLY.toString()))
            .andExpect(jsonPath("$.modeofdisposalfordischarge").value(DEFAULT_MODEOFDISPOSALFORDISCHARGE.toString()));
    }

    @Test
    public void getNonExistingEnvironment_impactdetail() throws Exception {
        // Get the environment_impactdetail
        restEnvironment_impactdetailMockMvc.perform(get("/api/environment-impactdetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEnvironment_impactdetail() throws Exception {
        // Initialize the database
        environment_impactdetailRepository.save(environment_impactdetail);
        int databaseSizeBeforeUpdate = environment_impactdetailRepository.findAll().size();

        // Update the environment_impactdetail
        Environment_impactdetail updatedEnvironment_impactdetail = environment_impactdetailRepository.findOne(environment_impactdetail.getId());
        updatedEnvironment_impactdetail
                .projectid(UPDATED_PROJECTID)
                .source_of_water_supply(UPDATED_SOURCE_OF_WATER_SUPPLY)
                .water_process(UPDATED_WATER_PROCESS)
                .water_cooling(UPDATED_WATER_COOLING)
                .water_domestic(UPDATED_WATER_DOMESTIC)
                .water_other(UPDATED_WATER_OTHER)
                .waste_water_process(UPDATED_WASTE_WATER_PROCESS)
                .waste_water_cooling(UPDATED_WASTE_WATER_COOLING)
                .waste_water_domesting(UPDATED_WASTE_WATER_DOMESTING)
                .waste_water_other(UPDATED_WASTE_WATER_OTHER)
                .waste_water_treatment(UPDATED_WASTE_WATER_TREATMENT)
                .mode_of_disposal_for_discharge(UPDATED_MODE_OF_DISPOSAL_FOR_DISCHARGE)
                .emissionid(UPDATED_EMISSIONID)
                .wastewaterdetailid(UPDATED_WASTEWATERDETAILID)
                .document_attached(UPDATED_DOCUMENT_ATTACHED)
                .other(UPDATED_OTHER)
                .projectname(UPDATED_PROJECTNAME)
                .sourceofwatersupply(UPDATED_SOURCEOFWATERSUPPLY)
                .modeofdisposalfordischarge(UPDATED_MODEOFDISPOSALFORDISCHARGE);
        Environment_impactdetailDTO environment_impactdetailDTO = environment_impactdetailMapper.environment_impactdetailToEnvironment_impactdetailDTO(updatedEnvironment_impactdetail);

        restEnvironment_impactdetailMockMvc.perform(put("/api/environment-impactdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(environment_impactdetailDTO)))
            .andExpect(status().isOk());

        // Validate the Environment_impactdetail in the database
        List<Environment_impactdetail> environment_impactdetailList = environment_impactdetailRepository.findAll();
        assertThat(environment_impactdetailList).hasSize(databaseSizeBeforeUpdate);
        Environment_impactdetail testEnvironment_impactdetail = environment_impactdetailList.get(environment_impactdetailList.size() - 1);
        assertThat(testEnvironment_impactdetail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testEnvironment_impactdetail.getSource_of_water_supply()).isEqualTo(UPDATED_SOURCE_OF_WATER_SUPPLY);
        assertThat(testEnvironment_impactdetail.getWater_process()).isEqualTo(UPDATED_WATER_PROCESS);
        assertThat(testEnvironment_impactdetail.getWater_cooling()).isEqualTo(UPDATED_WATER_COOLING);
        assertThat(testEnvironment_impactdetail.getWater_domestic()).isEqualTo(UPDATED_WATER_DOMESTIC);
        assertThat(testEnvironment_impactdetail.getWater_other()).isEqualTo(UPDATED_WATER_OTHER);
        assertThat(testEnvironment_impactdetail.getWaste_water_process()).isEqualTo(UPDATED_WASTE_WATER_PROCESS);
        assertThat(testEnvironment_impactdetail.getWaste_water_cooling()).isEqualTo(UPDATED_WASTE_WATER_COOLING);
        assertThat(testEnvironment_impactdetail.getWaste_water_domesting()).isEqualTo(UPDATED_WASTE_WATER_DOMESTING);
        assertThat(testEnvironment_impactdetail.getWaste_water_other()).isEqualTo(UPDATED_WASTE_WATER_OTHER);
        assertThat(testEnvironment_impactdetail.getWaste_water_treatment()).isEqualTo(UPDATED_WASTE_WATER_TREATMENT);
        assertThat(testEnvironment_impactdetail.getMode_of_disposal_for_discharge()).isEqualTo(UPDATED_MODE_OF_DISPOSAL_FOR_DISCHARGE);
        assertThat(testEnvironment_impactdetail.getEmissionid()).isEqualTo(UPDATED_EMISSIONID);
        assertThat(testEnvironment_impactdetail.getWastewaterdetailid()).isEqualTo(UPDATED_WASTEWATERDETAILID);
        assertThat(testEnvironment_impactdetail.getDocument_attached()).isEqualTo(UPDATED_DOCUMENT_ATTACHED);
        assertThat(testEnvironment_impactdetail.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testEnvironment_impactdetail.getProjectname()).isEqualTo(UPDATED_PROJECTNAME);
        assertThat(testEnvironment_impactdetail.getSourceofwatersupply()).isEqualTo(UPDATED_SOURCEOFWATERSUPPLY);
        assertThat(testEnvironment_impactdetail.getModeofdisposalfordischarge()).isEqualTo(UPDATED_MODEOFDISPOSALFORDISCHARGE);
    }

    @Test
    public void updateNonExistingEnvironment_impactdetail() throws Exception {
        int databaseSizeBeforeUpdate = environment_impactdetailRepository.findAll().size();

        // Create the Environment_impactdetail
        Environment_impactdetailDTO environment_impactdetailDTO = environment_impactdetailMapper.environment_impactdetailToEnvironment_impactdetailDTO(environment_impactdetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEnvironment_impactdetailMockMvc.perform(put("/api/environment-impactdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(environment_impactdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Environment_impactdetail in the database
        List<Environment_impactdetail> environment_impactdetailList = environment_impactdetailRepository.findAll();
        assertThat(environment_impactdetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteEnvironment_impactdetail() throws Exception {
        // Initialize the database
        environment_impactdetailRepository.save(environment_impactdetail);
        int databaseSizeBeforeDelete = environment_impactdetailRepository.findAll().size();

        // Get the environment_impactdetail
        restEnvironment_impactdetailMockMvc.perform(delete("/api/environment-impactdetails/{id}", environment_impactdetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Environment_impactdetail> environment_impactdetailList = environment_impactdetailRepository.findAll();
        assertThat(environment_impactdetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Environment_impactdetail.class);
    }
}
