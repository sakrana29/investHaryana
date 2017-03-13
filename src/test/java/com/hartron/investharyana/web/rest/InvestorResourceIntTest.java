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
import org.springframework.util.Base64Utils;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

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

    private static final ByteBuffer DEFAULT_MOUDOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_MOUDOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_MOUDOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MOUDOCUMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_MOUIDNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOUIDNUMBER = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_PHOTO = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_PHOTO = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLENAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLENAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_COUNTRYID = UUID.randomUUID();
    private static final UUID UPDATED_COUNTRYID = UUID.randomUUID();

    private static final UUID DEFAULT_STATEID = UUID.randomUUID();
    private static final UUID UPDATED_STATEID = UUID.randomUUID();

    private static final UUID DEFAULT_CITYID = UUID.randomUUID();
    private static final UUID UPDATED_CITYID = UUID.randomUUID();

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
                .moudocument(DEFAULT_MOUDOCUMENT)
                .moudocumentContentType(DEFAULT_MOUDOCUMENT_CONTENT_TYPE)
                .mouidnumber(DEFAULT_MOUIDNUMBER)
                .photo(DEFAULT_PHOTO)
                .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
                .firstname(DEFAULT_FIRSTNAME)
                .middlename(DEFAULT_MIDDLENAME)
                .lastname(DEFAULT_LASTNAME)
                .countryid(DEFAULT_COUNTRYID)
                .stateid(DEFAULT_STATEID)
                .cityid(DEFAULT_CITYID)
                .address1(DEFAULT_ADDRESS_1)
                .address2(DEFAULT_ADDRESS_2)
                .address3(DEFAULT_ADDRESS_3)
                .emailprimary(DEFAULT_EMAILPRIMARY)
                .emailsecondary(DEFAULT_EMAILSECONDARY);
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
        assertThat(testInvestor.getMoudocument()).isEqualTo(DEFAULT_MOUDOCUMENT);
        assertThat(testInvestor.getMoudocumentContentType()).isEqualTo(DEFAULT_MOUDOCUMENT_CONTENT_TYPE);
        assertThat(testInvestor.getMouidnumber()).isEqualTo(DEFAULT_MOUIDNUMBER);
        assertThat(testInvestor.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testInvestor.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testInvestor.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testInvestor.getMiddlename()).isEqualTo(DEFAULT_MIDDLENAME);
        assertThat(testInvestor.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testInvestor.getCountryid()).isEqualTo(DEFAULT_COUNTRYID);
        assertThat(testInvestor.getStateid()).isEqualTo(DEFAULT_STATEID);
        assertThat(testInvestor.getCityid()).isEqualTo(DEFAULT_CITYID);
        assertThat(testInvestor.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testInvestor.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testInvestor.getAddress3()).isEqualTo(DEFAULT_ADDRESS_3);
        assertThat(testInvestor.getEmailprimary()).isEqualTo(DEFAULT_EMAILPRIMARY);
        assertThat(testInvestor.getEmailsecondary()).isEqualTo(DEFAULT_EMAILSECONDARY);
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
            .andExpect(jsonPath("$.[*].moudocumentContentType").value(hasItem(DEFAULT_MOUDOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].moudocument").value(hasItem(Base64Utils.encodeToString(DEFAULT_MOUDOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].mouidnumber").value(hasItem(DEFAULT_MOUIDNUMBER.toString())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO.array()))))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].middlename").value(hasItem(DEFAULT_MIDDLENAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].countryid").value(hasItem(DEFAULT_COUNTRYID.toString())))
            .andExpect(jsonPath("$.[*].stateid").value(hasItem(DEFAULT_STATEID.toString())))
            .andExpect(jsonPath("$.[*].cityid").value(hasItem(DEFAULT_CITYID.toString())))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1.toString())))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2.toString())))
            .andExpect(jsonPath("$.[*].address3").value(hasItem(DEFAULT_ADDRESS_3.toString())))
            .andExpect(jsonPath("$.[*].emailprimary").value(hasItem(DEFAULT_EMAILPRIMARY.toString())))
            .andExpect(jsonPath("$.[*].emailsecondary").value(hasItem(DEFAULT_EMAILSECONDARY.toString())));
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
            .andExpect(jsonPath("$.moudocumentContentType").value(DEFAULT_MOUDOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.moudocument").value(Base64Utils.encodeToString(DEFAULT_MOUDOCUMENT.array())))
            .andExpect(jsonPath("$.mouidnumber").value(DEFAULT_MOUIDNUMBER.toString()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO.array())))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.middlename").value(DEFAULT_MIDDLENAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.countryid").value(DEFAULT_COUNTRYID.toString()))
            .andExpect(jsonPath("$.stateid").value(DEFAULT_STATEID.toString()))
            .andExpect(jsonPath("$.cityid").value(DEFAULT_CITYID.toString()))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1.toString()))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2.toString()))
            .andExpect(jsonPath("$.address3").value(DEFAULT_ADDRESS_3.toString()))
            .andExpect(jsonPath("$.emailprimary").value(DEFAULT_EMAILPRIMARY.toString()))
            .andExpect(jsonPath("$.emailsecondary").value(DEFAULT_EMAILSECONDARY.toString()));
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
                .moudocument(UPDATED_MOUDOCUMENT)
                .moudocumentContentType(UPDATED_MOUDOCUMENT_CONTENT_TYPE)
                .mouidnumber(UPDATED_MOUIDNUMBER)
                .photo(UPDATED_PHOTO)
                .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
                .firstname(UPDATED_FIRSTNAME)
                .middlename(UPDATED_MIDDLENAME)
                .lastname(UPDATED_LASTNAME)
                .countryid(UPDATED_COUNTRYID)
                .stateid(UPDATED_STATEID)
                .cityid(UPDATED_CITYID)
                .address1(UPDATED_ADDRESS_1)
                .address2(UPDATED_ADDRESS_2)
                .address3(UPDATED_ADDRESS_3)
                .emailprimary(UPDATED_EMAILPRIMARY)
                .emailsecondary(UPDATED_EMAILSECONDARY);
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
        assertThat(testInvestor.getMoudocument()).isEqualTo(UPDATED_MOUDOCUMENT);
        assertThat(testInvestor.getMoudocumentContentType()).isEqualTo(UPDATED_MOUDOCUMENT_CONTENT_TYPE);
        assertThat(testInvestor.getMouidnumber()).isEqualTo(UPDATED_MOUIDNUMBER);
        assertThat(testInvestor.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testInvestor.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testInvestor.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testInvestor.getMiddlename()).isEqualTo(UPDATED_MIDDLENAME);
        assertThat(testInvestor.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testInvestor.getCountryid()).isEqualTo(UPDATED_COUNTRYID);
        assertThat(testInvestor.getStateid()).isEqualTo(UPDATED_STATEID);
        assertThat(testInvestor.getCityid()).isEqualTo(UPDATED_CITYID);
        assertThat(testInvestor.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testInvestor.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testInvestor.getAddress3()).isEqualTo(UPDATED_ADDRESS_3);
        assertThat(testInvestor.getEmailprimary()).isEqualTo(UPDATED_EMAILPRIMARY);
        assertThat(testInvestor.getEmailsecondary()).isEqualTo(UPDATED_EMAILSECONDARY);
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
