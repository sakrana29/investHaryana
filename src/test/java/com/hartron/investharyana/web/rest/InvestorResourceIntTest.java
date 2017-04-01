package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Investor;
import com.hartron.investharyana.repository.InvestorRepository;
import com.hartron.investharyana.service.InvestorService;
import com.hartron.investharyana.service.dto.InvestorDTO;
import com.hartron.investharyana.service.mapper.InvestorMapper;
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
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InvestorResource REST controller.
 *
 * @see InvestorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class InvestorResourceIntTest extends AbstractCassandraTest {

    private static final Boolean DEFAULT_MOUAPPLICABLE = false;
    private static final Boolean UPDATED_MOUAPPLICABLE = true;

    private static final Integer DEFAULT_MOUSIGNYEAR = 1;
    private static final Integer UPDATED_MOUSIGNYEAR = 2;

    private static final String DEFAULT_MOUIDNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOUIDNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLENAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLENAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_EMAILPRIMARY = "AAAAAAAAAA";
    private static final String UPDATED_EMAILPRIMARY = "BBBBBBBBBB";

    private static final String DEFAULT_EMAILSECONDARY = "AAAAAAAAAA";
    private static final String UPDATED_EMAILSECONDARY = "BBBBBBBBBB";

    private static final String DEFAULT_USERLOGIN = "AAAAAAAAAA";
    private static final String UPDATED_USERLOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_CITYNAME = "AAAAAAAAAA";
    private static final String UPDATED_CITYNAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRYNAME = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRYNAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATENAME = "AAAAAAAAAA";
    private static final String UPDATED_STATENAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PINCODE = 1;
    private static final Integer UPDATED_PINCODE = 2;

    private static final Double DEFAULT_PHONENUMBER = 1D;
    private static final Double UPDATED_PHONENUMBER = 2D;

    private static final Double DEFAULT_MOBILENUMBER = 1D;
    private static final Double UPDATED_MOBILENUMBER = 2D;

    private static final Double DEFAULT_CAFPIN = 1D;
    private static final Double UPDATED_CAFPIN = 2D;

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private InvestorMapper investorMapper;

    @Autowired
    private InvestorService investorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restInvestorMockMvc;

    private Investor investor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InvestorResource investorResource = new InvestorResource(investorService);
        this.restInvestorMockMvc = MockMvcBuilders.standaloneSetup(investorResource)
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
    public static Investor createEntity() {
        Investor investor = new Investor()
                .mouapplicable(DEFAULT_MOUAPPLICABLE)
                .mousignyear(DEFAULT_MOUSIGNYEAR)
                .mouidnumber(DEFAULT_MOUIDNUMBER)
                .firstname(DEFAULT_FIRSTNAME)
                .middlename(DEFAULT_MIDDLENAME)
                .lastname(DEFAULT_LASTNAME)
                .address1(DEFAULT_ADDRESS_1)
                .address2(DEFAULT_ADDRESS_2)
                .address3(DEFAULT_ADDRESS_3)
                .emailprimary(DEFAULT_EMAILPRIMARY)
                .emailsecondary(DEFAULT_EMAILSECONDARY)
                .userlogin(DEFAULT_USERLOGIN)
                .cityname(DEFAULT_CITYNAME)
                .countryname(DEFAULT_COUNTRYNAME)
                .statename(DEFAULT_STATENAME)
                .pincode(DEFAULT_PINCODE)
                .phonenumber(DEFAULT_PHONENUMBER)
                .mobilenumber(DEFAULT_MOBILENUMBER)
                .cafpin(DEFAULT_CAFPIN)
                .createdate(DEFAULT_CREATEDATE)
                .updatedate(DEFAULT_UPDATEDATE);
        return investor;
    }

    @Before
    public void initTest() {
        investorRepository.deleteAll();
        investor = createEntity();
    }

    @Test
    public void createInvestor() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor
        InvestorDTO investorDTO = investorMapper.investorToInvestorDTO(investor);

        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isCreated());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate + 1);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.isMouapplicable()).isEqualTo(DEFAULT_MOUAPPLICABLE);
        assertThat(testInvestor.getMousignyear()).isEqualTo(DEFAULT_MOUSIGNYEAR);
        assertThat(testInvestor.getMouidnumber()).isEqualTo(DEFAULT_MOUIDNUMBER);
        assertThat(testInvestor.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testInvestor.getMiddlename()).isEqualTo(DEFAULT_MIDDLENAME);
        assertThat(testInvestor.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testInvestor.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testInvestor.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testInvestor.getAddress3()).isEqualTo(DEFAULT_ADDRESS_3);
        assertThat(testInvestor.getEmailprimary()).isEqualTo(DEFAULT_EMAILPRIMARY);
        assertThat(testInvestor.getEmailsecondary()).isEqualTo(DEFAULT_EMAILSECONDARY);
        assertThat(testInvestor.getUserlogin()).isEqualTo(DEFAULT_USERLOGIN);
        assertThat(testInvestor.getCityname()).isEqualTo(DEFAULT_CITYNAME);
        assertThat(testInvestor.getCountryname()).isEqualTo(DEFAULT_COUNTRYNAME);
        assertThat(testInvestor.getStatename()).isEqualTo(DEFAULT_STATENAME);
        assertThat(testInvestor.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testInvestor.getPhonenumber()).isEqualTo(DEFAULT_PHONENUMBER);
        assertThat(testInvestor.getMobilenumber()).isEqualTo(DEFAULT_MOBILENUMBER);
        assertThat(testInvestor.getCafpin()).isEqualTo(DEFAULT_CAFPIN);
        assertThat(testInvestor.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testInvestor.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
    }

    @Test
    public void createInvestorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor with an existing ID
        Investor existingInvestor = new Investor();
        existingInvestor.setId(UUID.randomUUID());
        InvestorDTO existingInvestorDTO = investorMapper.investorToInvestorDTO(existingInvestor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingInvestorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllInvestors() throws Exception {
        // Initialize the database
        investorRepository.save(investor);

        // Get all the investorList
        restInvestorMockMvc.perform(get("/api/investors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investor.getId().toString())))
            .andExpect(jsonPath("$.[*].mouapplicable").value(hasItem(DEFAULT_MOUAPPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].mousignyear").value(hasItem(DEFAULT_MOUSIGNYEAR)))
            .andExpect(jsonPath("$.[*].mouidnumber").value(hasItem(DEFAULT_MOUIDNUMBER.toString())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].middlename").value(hasItem(DEFAULT_MIDDLENAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1.toString())))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2.toString())))
            .andExpect(jsonPath("$.[*].address3").value(hasItem(DEFAULT_ADDRESS_3.toString())))
            .andExpect(jsonPath("$.[*].emailprimary").value(hasItem(DEFAULT_EMAILPRIMARY.toString())))
            .andExpect(jsonPath("$.[*].emailsecondary").value(hasItem(DEFAULT_EMAILSECONDARY.toString())))
            .andExpect(jsonPath("$.[*].userlogin").value(hasItem(DEFAULT_USERLOGIN.toString())))
            .andExpect(jsonPath("$.[*].cityname").value(hasItem(DEFAULT_CITYNAME.toString())))
            .andExpect(jsonPath("$.[*].countryname").value(hasItem(DEFAULT_COUNTRYNAME.toString())))
            .andExpect(jsonPath("$.[*].statename").value(hasItem(DEFAULT_STATENAME.toString())))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER.doubleValue())))
            .andExpect(jsonPath("$.[*].mobilenumber").value(hasItem(DEFAULT_MOBILENUMBER.doubleValue())))
            .andExpect(jsonPath("$.[*].cafpin").value(hasItem(DEFAULT_CAFPIN.doubleValue())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(sameInstant(DEFAULT_UPDATEDATE))));
    }

    @Test
    public void getInvestor() throws Exception {
        // Initialize the database
        investorRepository.save(investor);

        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", investor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investor.getId().toString()))
            .andExpect(jsonPath("$.mouapplicable").value(DEFAULT_MOUAPPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.mousignyear").value(DEFAULT_MOUSIGNYEAR))
            .andExpect(jsonPath("$.mouidnumber").value(DEFAULT_MOUIDNUMBER.toString()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.middlename").value(DEFAULT_MIDDLENAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1.toString()))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2.toString()))
            .andExpect(jsonPath("$.address3").value(DEFAULT_ADDRESS_3.toString()))
            .andExpect(jsonPath("$.emailprimary").value(DEFAULT_EMAILPRIMARY.toString()))
            .andExpect(jsonPath("$.emailsecondary").value(DEFAULT_EMAILSECONDARY.toString()))
            .andExpect(jsonPath("$.userlogin").value(DEFAULT_USERLOGIN.toString()))
            .andExpect(jsonPath("$.cityname").value(DEFAULT_CITYNAME.toString()))
            .andExpect(jsonPath("$.countryname").value(DEFAULT_COUNTRYNAME.toString()))
            .andExpect(jsonPath("$.statename").value(DEFAULT_STATENAME.toString()))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER.doubleValue()))
            .andExpect(jsonPath("$.mobilenumber").value(DEFAULT_MOBILENUMBER.doubleValue()))
            .andExpect(jsonPath("$.cafpin").value(DEFAULT_CAFPIN.doubleValue()))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.updatedate").value(sameInstant(DEFAULT_UPDATEDATE)));
    }

    @Test
    public void getNonExistingInvestor() throws Exception {
        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInvestor() throws Exception {
        // Initialize the database
        investorRepository.save(investor);
        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Update the investor
        Investor updatedInvestor = investorRepository.findOne(investor.getId());
        updatedInvestor
                .mouapplicable(UPDATED_MOUAPPLICABLE)
                .mousignyear(UPDATED_MOUSIGNYEAR)
                .mouidnumber(UPDATED_MOUIDNUMBER)
                .firstname(UPDATED_FIRSTNAME)
                .middlename(UPDATED_MIDDLENAME)
                .lastname(UPDATED_LASTNAME)
                .address1(UPDATED_ADDRESS_1)
                .address2(UPDATED_ADDRESS_2)
                .address3(UPDATED_ADDRESS_3)
                .emailprimary(UPDATED_EMAILPRIMARY)
                .emailsecondary(UPDATED_EMAILSECONDARY)
                .userlogin(UPDATED_USERLOGIN)
                .cityname(UPDATED_CITYNAME)
                .countryname(UPDATED_COUNTRYNAME)
                .statename(UPDATED_STATENAME)
                .pincode(UPDATED_PINCODE)
                .phonenumber(UPDATED_PHONENUMBER)
                .mobilenumber(UPDATED_MOBILENUMBER)
                .cafpin(UPDATED_CAFPIN)
                .createdate(UPDATED_CREATEDATE)
                .updatedate(UPDATED_UPDATEDATE);
        InvestorDTO investorDTO = investorMapper.investorToInvestorDTO(updatedInvestor);

        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isOk());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.isMouapplicable()).isEqualTo(UPDATED_MOUAPPLICABLE);
        assertThat(testInvestor.getMousignyear()).isEqualTo(UPDATED_MOUSIGNYEAR);
        assertThat(testInvestor.getMouidnumber()).isEqualTo(UPDATED_MOUIDNUMBER);
        assertThat(testInvestor.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testInvestor.getMiddlename()).isEqualTo(UPDATED_MIDDLENAME);
        assertThat(testInvestor.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testInvestor.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testInvestor.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testInvestor.getAddress3()).isEqualTo(UPDATED_ADDRESS_3);
        assertThat(testInvestor.getEmailprimary()).isEqualTo(UPDATED_EMAILPRIMARY);
        assertThat(testInvestor.getEmailsecondary()).isEqualTo(UPDATED_EMAILSECONDARY);
        assertThat(testInvestor.getUserlogin()).isEqualTo(UPDATED_USERLOGIN);
        assertThat(testInvestor.getCityname()).isEqualTo(UPDATED_CITYNAME);
        assertThat(testInvestor.getCountryname()).isEqualTo(UPDATED_COUNTRYNAME);
        assertThat(testInvestor.getStatename()).isEqualTo(UPDATED_STATENAME);
        assertThat(testInvestor.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testInvestor.getPhonenumber()).isEqualTo(UPDATED_PHONENUMBER);
        assertThat(testInvestor.getMobilenumber()).isEqualTo(UPDATED_MOBILENUMBER);
        assertThat(testInvestor.getCafpin()).isEqualTo(UPDATED_CAFPIN);
        assertThat(testInvestor.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testInvestor.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
    }

    @Test
    public void updateNonExistingInvestor() throws Exception {
        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Create the Investor
        InvestorDTO investorDTO = investorMapper.investorToInvestorDTO(investor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isCreated());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteInvestor() throws Exception {
        // Initialize the database
        investorRepository.save(investor);
        int databaseSizeBeforeDelete = investorRepository.findAll().size();

        // Get the investor
        restInvestorMockMvc.perform(delete("/api/investors/{id}", investor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Investor.class);
    }
}
