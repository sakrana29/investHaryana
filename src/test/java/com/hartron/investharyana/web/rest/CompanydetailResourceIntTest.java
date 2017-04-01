package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Companydetail;
import com.hartron.investharyana.repository.CompanydetailRepository;
import com.hartron.investharyana.service.CompanydetailService;
import com.hartron.investharyana.service.dto.CompanydetailDTO;
import com.hartron.investharyana.service.mapper.CompanydetailMapper;
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
 * Test class for the CompanydetailResource REST controller.
 *
 * @see CompanydetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class CompanydetailResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_PROMOTER_MD_DIRECTOR = "AAAAAAAAAA";
    private static final String UPDATED_PROMOTER_MD_DIRECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSENTITY = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSENTITY = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER = 1;
    private static final Integer UPDATED_DIRECTOR_PROMOTER_MD_CEO_NUMBER = 2;

    private static final String DEFAULT_PAN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAN_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_AADHAR_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NRI = false;
    private static final Boolean UPDATED_NRI = true;

    private static final String DEFAULT_TIN_VAT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TIN_VAT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CST_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CST_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSENTITYTYPE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSENTITYTYPE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private CompanydetailRepository companydetailRepository;

    @Autowired
    private CompanydetailMapper companydetailMapper;

    @Autowired
    private CompanydetailService companydetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCompanydetailMockMvc;

    private Companydetail companydetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CompanydetailResource companydetailResource = new CompanydetailResource(companydetailService);
        this.restCompanydetailMockMvc = MockMvcBuilders.standaloneSetup(companydetailResource)
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
    public static Companydetail createEntity() {
        Companydetail companydetail = new Companydetail()
                .promoter_md_director(DEFAULT_PROMOTER_MD_DIRECTOR)
                .designation(DEFAULT_DESIGNATION)
                .businessentity(DEFAULT_BUSINESSENTITY)
                .director_promoter_md_ceo_number(DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER)
                .pan_number(DEFAULT_PAN_NUMBER)
                .aadhar_number(DEFAULT_AADHAR_NUMBER)
                .nri(DEFAULT_NRI)
                .tin_vat_number(DEFAULT_TIN_VAT_NUMBER)
                .cst_number(DEFAULT_CST_NUMBER)
                .businessentitytype(DEFAULT_BUSINESSENTITYTYPE)
                .createdate(DEFAULT_CREATEDATE)
                .updatedate(DEFAULT_UPDATEDATE);
        return companydetail;
    }

    @Before
    public void initTest() {
        companydetailRepository.deleteAll();
        companydetail = createEntity();
    }

    @Test
    public void createCompanydetail() throws Exception {
        int databaseSizeBeforeCreate = companydetailRepository.findAll().size();

        // Create the Companydetail
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(companydetail);

        restCompanydetailMockMvc.perform(post("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeCreate + 1);
        Companydetail testCompanydetail = companydetailList.get(companydetailList.size() - 1);
        assertThat(testCompanydetail.getPromoter_md_director()).isEqualTo(DEFAULT_PROMOTER_MD_DIRECTOR);
        assertThat(testCompanydetail.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testCompanydetail.getBusinessentity()).isEqualTo(DEFAULT_BUSINESSENTITY);
        assertThat(testCompanydetail.getDirector_promoter_md_ceo_number()).isEqualTo(DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER);
        assertThat(testCompanydetail.getPan_number()).isEqualTo(DEFAULT_PAN_NUMBER);
        assertThat(testCompanydetail.getAadhar_number()).isEqualTo(DEFAULT_AADHAR_NUMBER);
        assertThat(testCompanydetail.isNri()).isEqualTo(DEFAULT_NRI);
        assertThat(testCompanydetail.getTin_vat_number()).isEqualTo(DEFAULT_TIN_VAT_NUMBER);
        assertThat(testCompanydetail.getCst_number()).isEqualTo(DEFAULT_CST_NUMBER);
        assertThat(testCompanydetail.getBusinessentitytype()).isEqualTo(DEFAULT_BUSINESSENTITYTYPE);
        assertThat(testCompanydetail.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testCompanydetail.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
    }

    @Test
    public void createCompanydetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companydetailRepository.findAll().size();

        // Create the Companydetail with an existing ID
        Companydetail existingCompanydetail = new Companydetail();
        existingCompanydetail.setId(UUID.randomUUID());
        CompanydetailDTO existingCompanydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(existingCompanydetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanydetailMockMvc.perform(post("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCompanydetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCompanydetails() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);

        // Get all the companydetailList
        restCompanydetailMockMvc.perform(get("/api/companydetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companydetail.getId().toString())))
            .andExpect(jsonPath("$.[*].promoter_md_director").value(hasItem(DEFAULT_PROMOTER_MD_DIRECTOR.toString())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
            .andExpect(jsonPath("$.[*].businessentity").value(hasItem(DEFAULT_BUSINESSENTITY.toString())))
            .andExpect(jsonPath("$.[*].director_promoter_md_ceo_number").value(hasItem(DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER)))
            .andExpect(jsonPath("$.[*].pan_number").value(hasItem(DEFAULT_PAN_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].aadhar_number").value(hasItem(DEFAULT_AADHAR_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].nri").value(hasItem(DEFAULT_NRI.booleanValue())))
            .andExpect(jsonPath("$.[*].tin_vat_number").value(hasItem(DEFAULT_TIN_VAT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].cst_number").value(hasItem(DEFAULT_CST_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].businessentitytype").value(hasItem(DEFAULT_BUSINESSENTITYTYPE.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(sameInstant(DEFAULT_UPDATEDATE))));
    }

    @Test
    public void getCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);

        // Get the companydetail
        restCompanydetailMockMvc.perform(get("/api/companydetails/{id}", companydetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(companydetail.getId().toString()))
            .andExpect(jsonPath("$.promoter_md_director").value(DEFAULT_PROMOTER_MD_DIRECTOR.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.businessentity").value(DEFAULT_BUSINESSENTITY.toString()))
            .andExpect(jsonPath("$.director_promoter_md_ceo_number").value(DEFAULT_DIRECTOR_PROMOTER_MD_CEO_NUMBER))
            .andExpect(jsonPath("$.pan_number").value(DEFAULT_PAN_NUMBER.toString()))
            .andExpect(jsonPath("$.aadhar_number").value(DEFAULT_AADHAR_NUMBER.toString()))
            .andExpect(jsonPath("$.nri").value(DEFAULT_NRI.booleanValue()))
            .andExpect(jsonPath("$.tin_vat_number").value(DEFAULT_TIN_VAT_NUMBER.toString()))
            .andExpect(jsonPath("$.cst_number").value(DEFAULT_CST_NUMBER.toString()))
            .andExpect(jsonPath("$.businessentitytype").value(DEFAULT_BUSINESSENTITYTYPE.toString()))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.updatedate").value(sameInstant(DEFAULT_UPDATEDATE)));
    }

    @Test
    public void getNonExistingCompanydetail() throws Exception {
        // Get the companydetail
        restCompanydetailMockMvc.perform(get("/api/companydetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);
        int databaseSizeBeforeUpdate = companydetailRepository.findAll().size();

        // Update the companydetail
        Companydetail updatedCompanydetail = companydetailRepository.findOne(companydetail.getId());
        updatedCompanydetail
                .promoter_md_director(UPDATED_PROMOTER_MD_DIRECTOR)
                .designation(UPDATED_DESIGNATION)
                .businessentity(UPDATED_BUSINESSENTITY)
                .director_promoter_md_ceo_number(UPDATED_DIRECTOR_PROMOTER_MD_CEO_NUMBER)
                .pan_number(UPDATED_PAN_NUMBER)
                .aadhar_number(UPDATED_AADHAR_NUMBER)
                .nri(UPDATED_NRI)
                .tin_vat_number(UPDATED_TIN_VAT_NUMBER)
                .cst_number(UPDATED_CST_NUMBER)
                .businessentitytype(UPDATED_BUSINESSENTITYTYPE)
                .createdate(UPDATED_CREATEDATE)
                .updatedate(UPDATED_UPDATEDATE);
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(updatedCompanydetail);

        restCompanydetailMockMvc.perform(put("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isOk());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeUpdate);
        Companydetail testCompanydetail = companydetailList.get(companydetailList.size() - 1);
        assertThat(testCompanydetail.getPromoter_md_director()).isEqualTo(UPDATED_PROMOTER_MD_DIRECTOR);
        assertThat(testCompanydetail.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testCompanydetail.getBusinessentity()).isEqualTo(UPDATED_BUSINESSENTITY);
        assertThat(testCompanydetail.getDirector_promoter_md_ceo_number()).isEqualTo(UPDATED_DIRECTOR_PROMOTER_MD_CEO_NUMBER);
        assertThat(testCompanydetail.getPan_number()).isEqualTo(UPDATED_PAN_NUMBER);
        assertThat(testCompanydetail.getAadhar_number()).isEqualTo(UPDATED_AADHAR_NUMBER);
        assertThat(testCompanydetail.isNri()).isEqualTo(UPDATED_NRI);
        assertThat(testCompanydetail.getTin_vat_number()).isEqualTo(UPDATED_TIN_VAT_NUMBER);
        assertThat(testCompanydetail.getCst_number()).isEqualTo(UPDATED_CST_NUMBER);
        assertThat(testCompanydetail.getBusinessentitytype()).isEqualTo(UPDATED_BUSINESSENTITYTYPE);
        assertThat(testCompanydetail.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testCompanydetail.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
    }

    @Test
    public void updateNonExistingCompanydetail() throws Exception {
        int databaseSizeBeforeUpdate = companydetailRepository.findAll().size();

        // Create the Companydetail
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(companydetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompanydetailMockMvc.perform(put("/api/companydetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companydetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Companydetail in the database
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCompanydetail() throws Exception {
        // Initialize the database
        companydetailRepository.save(companydetail);
        int databaseSizeBeforeDelete = companydetailRepository.findAll().size();

        // Get the companydetail
        restCompanydetailMockMvc.perform(delete("/api/companydetails/{id}", companydetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Companydetail> companydetailList = companydetailRepository.findAll();
        assertThat(companydetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Companydetail.class);
    }
}
