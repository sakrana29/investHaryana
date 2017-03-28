package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Electricrequirement;
import com.hartron.investharyana.repository.ElectricrequirementRepository;
import com.hartron.investharyana.service.ElectricrequirementService;
import com.hartron.investharyana.service.dto.ElectricrequirementDTO;
import com.hartron.investharyana.service.mapper.ElectricrequirementMapper;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ElectricrequirementResource REST controller.
 *
 * @see ElectricrequirementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ElectricrequirementResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final Boolean DEFAULT_TEMPORARYREQUIRED = false;
    private static final Boolean UPDATED_TEMPORARYREQUIRED = true;

    private static final Boolean DEFAULT_TEM_LOAD_EXISTING = false;
    private static final Boolean UPDATED_TEM_LOAD_EXISTING = true;

    private static final String DEFAULT_TEM_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TEM_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KW = new BigDecimal(1);
    private static final BigDecimal UPDATED_TEMP_EXISTING_LOAD_DEMAND_KW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_TEMP_EXISTING_LOAD_DEMAND_KVA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TEMP_NEW_LOAD_DEMAND_KW = new BigDecimal(1);
    private static final BigDecimal UPDATED_TEMP_NEW_LOAD_DEMAND_KW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TEMP_NEW_LOAD_DEMAND_KVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_TEMP_NEW_LOAD_DEMAND_KVA = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_TEMP_LOAD_DEMAND_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TEMP_LOAD_DEMAND_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_REGULAR_LOAD_REQUIRED = false;
    private static final Boolean UPDATED_REGULAR_LOAD_REQUIRED = true;

    private static final Boolean DEFAULT_REGULAR_EXISTING_CONNECTION = false;
    private static final Boolean UPDATED_REGULAR_EXISTING_CONNECTION = true;

    private static final UUID DEFAULT_CUSTOMERTYPE = UUID.randomUUID();
    private static final UUID UPDATED_CUSTOMERTYPE = UUID.randomUUID();

    private static final String DEFAULT_REGULAR_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGULAR_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KW = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGULAR_EXISTING_LOAD_IFANY_KW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGULAR_EXISTING_LOAD_IFANY_KVA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGULAR_NEW_LOAD_DEMAND_KW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA = new BigDecimal(1);
    private static final BigDecimal UPDATED_REGULAR_NEW_LOAD_DEMAND_KVA = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_REGULAR_LOAD_DEMAND_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGULAR_LOAD_DEMAND_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TEMPORARYCONNECTION = "AAAAAAAAAA";
    private static final String UPDATED_TEMPORARYCONNECTION = "BBBBBBBBBB";

    private static final String DEFAULT_REGULAR_CONNECTION_DOC = "AAAAAAAAAA";
    private static final String UPDATED_REGULAR_CONNECTION_DOC = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERTYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERTYPENAME = "BBBBBBBBBB";

    @Autowired
    private ElectricrequirementRepository electricrequirementRepository;

    @Autowired
    private ElectricrequirementMapper electricrequirementMapper;

    @Autowired
    private ElectricrequirementService electricrequirementService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restElectricrequirementMockMvc;

    private Electricrequirement electricrequirement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ElectricrequirementResource electricrequirementResource = new ElectricrequirementResource(electricrequirementService);
        this.restElectricrequirementMockMvc = MockMvcBuilders.standaloneSetup(electricrequirementResource)
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
    public static Electricrequirement createEntity() {
        Electricrequirement electricrequirement = new Electricrequirement()
                .projectid(DEFAULT_PROJECTID)
                .temporaryrequired(DEFAULT_TEMPORARYREQUIRED)
                .tem_load_existing(DEFAULT_TEM_LOAD_EXISTING)
                .tem_account_number(DEFAULT_TEM_ACCOUNT_NUMBER)
                .temp_existing_load_demand_kw(DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KW)
                .temp_existing_load_demand_kva(DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KVA)
                .temp_new_load_demand_kw(DEFAULT_TEMP_NEW_LOAD_DEMAND_KW)
                .temp_new_load_demand_kva(DEFAULT_TEMP_NEW_LOAD_DEMAND_KVA)
                .temp_load_demand_date(DEFAULT_TEMP_LOAD_DEMAND_DATE)
                .regular_load_required(DEFAULT_REGULAR_LOAD_REQUIRED)
                .regular_existing_connection(DEFAULT_REGULAR_EXISTING_CONNECTION)
                .customertype(DEFAULT_CUSTOMERTYPE)
                .regular_account_number(DEFAULT_REGULAR_ACCOUNT_NUMBER)
                .regular_existing_load_ifany_kw(DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KW)
                .regular_existing_load_ifany_kva(DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KVA)
                .regular_new_load_demand_kw(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW)
                .regular_new_load_demand_kva(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA)
                .regular_load_demand_date(DEFAULT_REGULAR_LOAD_DEMAND_DATE)
                .temporaryconnection(DEFAULT_TEMPORARYCONNECTION)
                .regular_connection_doc(DEFAULT_REGULAR_CONNECTION_DOC)
                .projectname(DEFAULT_PROJECTNAME)
                .customertypename(DEFAULT_CUSTOMERTYPENAME);
        return electricrequirement;
    }

    @Before
    public void initTest() {
        electricrequirementRepository.deleteAll();
        electricrequirement = createEntity();
    }

    @Test
    public void createElectricrequirement() throws Exception {
        int databaseSizeBeforeCreate = electricrequirementRepository.findAll().size();

        // Create the Electricrequirement
        ElectricrequirementDTO electricrequirementDTO = electricrequirementMapper.electricrequirementToElectricrequirementDTO(electricrequirement);

        restElectricrequirementMockMvc.perform(post("/api/electricrequirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electricrequirementDTO)))
            .andExpect(status().isCreated());

        // Validate the Electricrequirement in the database
        List<Electricrequirement> electricrequirementList = electricrequirementRepository.findAll();
        assertThat(electricrequirementList).hasSize(databaseSizeBeforeCreate + 1);
        Electricrequirement testElectricrequirement = electricrequirementList.get(electricrequirementList.size() - 1);
        assertThat(testElectricrequirement.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testElectricrequirement.isTemporaryrequired()).isEqualTo(DEFAULT_TEMPORARYREQUIRED);
        assertThat(testElectricrequirement.isTem_load_existing()).isEqualTo(DEFAULT_TEM_LOAD_EXISTING);
        assertThat(testElectricrequirement.getTem_account_number()).isEqualTo(DEFAULT_TEM_ACCOUNT_NUMBER);
        assertThat(testElectricrequirement.getTemp_existing_load_demand_kw()).isEqualTo(DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KW);
        assertThat(testElectricrequirement.getTemp_existing_load_demand_kva()).isEqualTo(DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KVA);
        assertThat(testElectricrequirement.getTemp_new_load_demand_kw()).isEqualTo(DEFAULT_TEMP_NEW_LOAD_DEMAND_KW);
        assertThat(testElectricrequirement.getTemp_new_load_demand_kva()).isEqualTo(DEFAULT_TEMP_NEW_LOAD_DEMAND_KVA);
        assertThat(testElectricrequirement.getTemp_load_demand_date()).isEqualTo(DEFAULT_TEMP_LOAD_DEMAND_DATE);
        assertThat(testElectricrequirement.isRegular_load_required()).isEqualTo(DEFAULT_REGULAR_LOAD_REQUIRED);
        assertThat(testElectricrequirement.isRegular_existing_connection()).isEqualTo(DEFAULT_REGULAR_EXISTING_CONNECTION);
        assertThat(testElectricrequirement.getCustomertype()).isEqualTo(DEFAULT_CUSTOMERTYPE);
        assertThat(testElectricrequirement.getRegular_account_number()).isEqualTo(DEFAULT_REGULAR_ACCOUNT_NUMBER);
        assertThat(testElectricrequirement.getRegular_existing_load_ifany_kw()).isEqualTo(DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KW);
        assertThat(testElectricrequirement.getRegular_existing_load_ifany_kva()).isEqualTo(DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KVA);
        assertThat(testElectricrequirement.getRegular_new_load_demand_kw()).isEqualTo(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW);
        assertThat(testElectricrequirement.getRegular_new_load_demand_kva()).isEqualTo(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA);
        assertThat(testElectricrequirement.getRegular_load_demand_date()).isEqualTo(DEFAULT_REGULAR_LOAD_DEMAND_DATE);
        assertThat(testElectricrequirement.getTemporaryconnection()).isEqualTo(DEFAULT_TEMPORARYCONNECTION);
        assertThat(testElectricrequirement.getRegular_connection_doc()).isEqualTo(DEFAULT_REGULAR_CONNECTION_DOC);
        assertThat(testElectricrequirement.getProjectname()).isEqualTo(DEFAULT_PROJECTNAME);
        assertThat(testElectricrequirement.getCustomertypename()).isEqualTo(DEFAULT_CUSTOMERTYPENAME);
    }

    @Test
    public void createElectricrequirementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = electricrequirementRepository.findAll().size();

        // Create the Electricrequirement with an existing ID
        Electricrequirement existingElectricrequirement = new Electricrequirement();
        existingElectricrequirement.setId(UUID.randomUUID());
        ElectricrequirementDTO existingElectricrequirementDTO = electricrequirementMapper.electricrequirementToElectricrequirementDTO(existingElectricrequirement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElectricrequirementMockMvc.perform(post("/api/electricrequirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingElectricrequirementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Electricrequirement> electricrequirementList = electricrequirementRepository.findAll();
        assertThat(electricrequirementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllElectricrequirements() throws Exception {
        // Initialize the database
        electricrequirementRepository.save(electricrequirement);

        // Get all the electricrequirementList
        restElectricrequirementMockMvc.perform(get("/api/electricrequirements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(electricrequirement.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].temporaryrequired").value(hasItem(DEFAULT_TEMPORARYREQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].tem_load_existing").value(hasItem(DEFAULT_TEM_LOAD_EXISTING.booleanValue())))
            .andExpect(jsonPath("$.[*].tem_account_number").value(hasItem(DEFAULT_TEM_ACCOUNT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].temp_existing_load_demand_kw").value(hasItem(DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KW.intValue())))
            .andExpect(jsonPath("$.[*].temp_existing_load_demand_kva").value(hasItem(DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KVA.intValue())))
            .andExpect(jsonPath("$.[*].temp_new_load_demand_kw").value(hasItem(DEFAULT_TEMP_NEW_LOAD_DEMAND_KW.intValue())))
            .andExpect(jsonPath("$.[*].temp_new_load_demand_kva").value(hasItem(DEFAULT_TEMP_NEW_LOAD_DEMAND_KVA.intValue())))
            .andExpect(jsonPath("$.[*].temp_load_demand_date").value(hasItem(sameInstant(DEFAULT_TEMP_LOAD_DEMAND_DATE))))
            .andExpect(jsonPath("$.[*].regular_load_required").value(hasItem(DEFAULT_REGULAR_LOAD_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].regular_existing_connection").value(hasItem(DEFAULT_REGULAR_EXISTING_CONNECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].customertype").value(hasItem(DEFAULT_CUSTOMERTYPE.toString())))
            .andExpect(jsonPath("$.[*].regular_account_number").value(hasItem(DEFAULT_REGULAR_ACCOUNT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].regular_existing_load_ifany_kw").value(hasItem(DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KW.intValue())))
            .andExpect(jsonPath("$.[*].regular_existing_load_ifany_kva").value(hasItem(DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KVA.intValue())))
            .andExpect(jsonPath("$.[*].regular_new_load_demand_kw").value(hasItem(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW.intValue())))
            .andExpect(jsonPath("$.[*].regular_new_load_demand_kva").value(hasItem(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA.intValue())))
            .andExpect(jsonPath("$.[*].regular_load_demand_date").value(hasItem(sameInstant(DEFAULT_REGULAR_LOAD_DEMAND_DATE))))
            .andExpect(jsonPath("$.[*].temporaryconnection").value(hasItem(DEFAULT_TEMPORARYCONNECTION.toString())))
            .andExpect(jsonPath("$.[*].regular_connection_doc").value(hasItem(DEFAULT_REGULAR_CONNECTION_DOC.toString())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME.toString())))
            .andExpect(jsonPath("$.[*].customertypename").value(hasItem(DEFAULT_CUSTOMERTYPENAME.toString())));
    }

    @Test
    public void getElectricrequirement() throws Exception {
        // Initialize the database
        electricrequirementRepository.save(electricrequirement);

        // Get the electricrequirement
        restElectricrequirementMockMvc.perform(get("/api/electricrequirements/{id}", electricrequirement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(electricrequirement.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.temporaryrequired").value(DEFAULT_TEMPORARYREQUIRED.booleanValue()))
            .andExpect(jsonPath("$.tem_load_existing").value(DEFAULT_TEM_LOAD_EXISTING.booleanValue()))
            .andExpect(jsonPath("$.tem_account_number").value(DEFAULT_TEM_ACCOUNT_NUMBER.toString()))
            .andExpect(jsonPath("$.temp_existing_load_demand_kw").value(DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KW.intValue()))
            .andExpect(jsonPath("$.temp_existing_load_demand_kva").value(DEFAULT_TEMP_EXISTING_LOAD_DEMAND_KVA.intValue()))
            .andExpect(jsonPath("$.temp_new_load_demand_kw").value(DEFAULT_TEMP_NEW_LOAD_DEMAND_KW.intValue()))
            .andExpect(jsonPath("$.temp_new_load_demand_kva").value(DEFAULT_TEMP_NEW_LOAD_DEMAND_KVA.intValue()))
            .andExpect(jsonPath("$.temp_load_demand_date").value(sameInstant(DEFAULT_TEMP_LOAD_DEMAND_DATE)))
            .andExpect(jsonPath("$.regular_load_required").value(DEFAULT_REGULAR_LOAD_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.regular_existing_connection").value(DEFAULT_REGULAR_EXISTING_CONNECTION.booleanValue()))
            .andExpect(jsonPath("$.customertype").value(DEFAULT_CUSTOMERTYPE.toString()))
            .andExpect(jsonPath("$.regular_account_number").value(DEFAULT_REGULAR_ACCOUNT_NUMBER.toString()))
            .andExpect(jsonPath("$.regular_existing_load_ifany_kw").value(DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KW.intValue()))
            .andExpect(jsonPath("$.regular_existing_load_ifany_kva").value(DEFAULT_REGULAR_EXISTING_LOAD_IFANY_KVA.intValue()))
            .andExpect(jsonPath("$.regular_new_load_demand_kw").value(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KW.intValue()))
            .andExpect(jsonPath("$.regular_new_load_demand_kva").value(DEFAULT_REGULAR_NEW_LOAD_DEMAND_KVA.intValue()))
            .andExpect(jsonPath("$.regular_load_demand_date").value(sameInstant(DEFAULT_REGULAR_LOAD_DEMAND_DATE)))
            .andExpect(jsonPath("$.temporaryconnection").value(DEFAULT_TEMPORARYCONNECTION.toString()))
            .andExpect(jsonPath("$.regular_connection_doc").value(DEFAULT_REGULAR_CONNECTION_DOC.toString()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME.toString()))
            .andExpect(jsonPath("$.customertypename").value(DEFAULT_CUSTOMERTYPENAME.toString()));
    }

    @Test
    public void getNonExistingElectricrequirement() throws Exception {
        // Get the electricrequirement
        restElectricrequirementMockMvc.perform(get("/api/electricrequirements/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateElectricrequirement() throws Exception {
        // Initialize the database
        electricrequirementRepository.save(electricrequirement);
        int databaseSizeBeforeUpdate = electricrequirementRepository.findAll().size();

        // Update the electricrequirement
        Electricrequirement updatedElectricrequirement = electricrequirementRepository.findOne(electricrequirement.getId());
        updatedElectricrequirement
                .projectid(UPDATED_PROJECTID)
                .temporaryrequired(UPDATED_TEMPORARYREQUIRED)
                .tem_load_existing(UPDATED_TEM_LOAD_EXISTING)
                .tem_account_number(UPDATED_TEM_ACCOUNT_NUMBER)
                .temp_existing_load_demand_kw(UPDATED_TEMP_EXISTING_LOAD_DEMAND_KW)
                .temp_existing_load_demand_kva(UPDATED_TEMP_EXISTING_LOAD_DEMAND_KVA)
                .temp_new_load_demand_kw(UPDATED_TEMP_NEW_LOAD_DEMAND_KW)
                .temp_new_load_demand_kva(UPDATED_TEMP_NEW_LOAD_DEMAND_KVA)
                .temp_load_demand_date(UPDATED_TEMP_LOAD_DEMAND_DATE)
                .regular_load_required(UPDATED_REGULAR_LOAD_REQUIRED)
                .regular_existing_connection(UPDATED_REGULAR_EXISTING_CONNECTION)
                .customertype(UPDATED_CUSTOMERTYPE)
                .regular_account_number(UPDATED_REGULAR_ACCOUNT_NUMBER)
                .regular_existing_load_ifany_kw(UPDATED_REGULAR_EXISTING_LOAD_IFANY_KW)
                .regular_existing_load_ifany_kva(UPDATED_REGULAR_EXISTING_LOAD_IFANY_KVA)
                .regular_new_load_demand_kw(UPDATED_REGULAR_NEW_LOAD_DEMAND_KW)
                .regular_new_load_demand_kva(UPDATED_REGULAR_NEW_LOAD_DEMAND_KVA)
                .regular_load_demand_date(UPDATED_REGULAR_LOAD_DEMAND_DATE)
                .temporaryconnection(UPDATED_TEMPORARYCONNECTION)
                .regular_connection_doc(UPDATED_REGULAR_CONNECTION_DOC)
                .projectname(UPDATED_PROJECTNAME)
                .customertypename(UPDATED_CUSTOMERTYPENAME);
        ElectricrequirementDTO electricrequirementDTO = electricrequirementMapper.electricrequirementToElectricrequirementDTO(updatedElectricrequirement);

        restElectricrequirementMockMvc.perform(put("/api/electricrequirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electricrequirementDTO)))
            .andExpect(status().isOk());

        // Validate the Electricrequirement in the database
        List<Electricrequirement> electricrequirementList = electricrequirementRepository.findAll();
        assertThat(electricrequirementList).hasSize(databaseSizeBeforeUpdate);
        Electricrequirement testElectricrequirement = electricrequirementList.get(electricrequirementList.size() - 1);
        assertThat(testElectricrequirement.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testElectricrequirement.isTemporaryrequired()).isEqualTo(UPDATED_TEMPORARYREQUIRED);
        assertThat(testElectricrequirement.isTem_load_existing()).isEqualTo(UPDATED_TEM_LOAD_EXISTING);
        assertThat(testElectricrequirement.getTem_account_number()).isEqualTo(UPDATED_TEM_ACCOUNT_NUMBER);
        assertThat(testElectricrequirement.getTemp_existing_load_demand_kw()).isEqualTo(UPDATED_TEMP_EXISTING_LOAD_DEMAND_KW);
        assertThat(testElectricrequirement.getTemp_existing_load_demand_kva()).isEqualTo(UPDATED_TEMP_EXISTING_LOAD_DEMAND_KVA);
        assertThat(testElectricrequirement.getTemp_new_load_demand_kw()).isEqualTo(UPDATED_TEMP_NEW_LOAD_DEMAND_KW);
        assertThat(testElectricrequirement.getTemp_new_load_demand_kva()).isEqualTo(UPDATED_TEMP_NEW_LOAD_DEMAND_KVA);
        assertThat(testElectricrequirement.getTemp_load_demand_date()).isEqualTo(UPDATED_TEMP_LOAD_DEMAND_DATE);
        assertThat(testElectricrequirement.isRegular_load_required()).isEqualTo(UPDATED_REGULAR_LOAD_REQUIRED);
        assertThat(testElectricrequirement.isRegular_existing_connection()).isEqualTo(UPDATED_REGULAR_EXISTING_CONNECTION);
        assertThat(testElectricrequirement.getCustomertype()).isEqualTo(UPDATED_CUSTOMERTYPE);
        assertThat(testElectricrequirement.getRegular_account_number()).isEqualTo(UPDATED_REGULAR_ACCOUNT_NUMBER);
        assertThat(testElectricrequirement.getRegular_existing_load_ifany_kw()).isEqualTo(UPDATED_REGULAR_EXISTING_LOAD_IFANY_KW);
        assertThat(testElectricrequirement.getRegular_existing_load_ifany_kva()).isEqualTo(UPDATED_REGULAR_EXISTING_LOAD_IFANY_KVA);
        assertThat(testElectricrequirement.getRegular_new_load_demand_kw()).isEqualTo(UPDATED_REGULAR_NEW_LOAD_DEMAND_KW);
        assertThat(testElectricrequirement.getRegular_new_load_demand_kva()).isEqualTo(UPDATED_REGULAR_NEW_LOAD_DEMAND_KVA);
        assertThat(testElectricrequirement.getRegular_load_demand_date()).isEqualTo(UPDATED_REGULAR_LOAD_DEMAND_DATE);
        assertThat(testElectricrequirement.getTemporaryconnection()).isEqualTo(UPDATED_TEMPORARYCONNECTION);
        assertThat(testElectricrequirement.getRegular_connection_doc()).isEqualTo(UPDATED_REGULAR_CONNECTION_DOC);
        assertThat(testElectricrequirement.getProjectname()).isEqualTo(UPDATED_PROJECTNAME);
        assertThat(testElectricrequirement.getCustomertypename()).isEqualTo(UPDATED_CUSTOMERTYPENAME);
    }

    @Test
    public void updateNonExistingElectricrequirement() throws Exception {
        int databaseSizeBeforeUpdate = electricrequirementRepository.findAll().size();

        // Create the Electricrequirement
        ElectricrequirementDTO electricrequirementDTO = electricrequirementMapper.electricrequirementToElectricrequirementDTO(electricrequirement);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restElectricrequirementMockMvc.perform(put("/api/electricrequirements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(electricrequirementDTO)))
            .andExpect(status().isCreated());

        // Validate the Electricrequirement in the database
        List<Electricrequirement> electricrequirementList = electricrequirementRepository.findAll();
        assertThat(electricrequirementList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteElectricrequirement() throws Exception {
        // Initialize the database
        electricrequirementRepository.save(electricrequirement);
        int databaseSizeBeforeDelete = electricrequirementRepository.findAll().size();

        // Get the electricrequirement
        restElectricrequirementMockMvc.perform(delete("/api/electricrequirements/{id}", electricrequirement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Electricrequirement> electricrequirementList = electricrequirementRepository.findAll();
        assertThat(electricrequirementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Electricrequirement.class);
    }
}
