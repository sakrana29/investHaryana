package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectdetail;
import com.hartron.investharyana.repository.ProjectdetailRepository;
import com.hartron.investharyana.service.ProjectdetailService;
import com.hartron.investharyana.service.dto.ProjectdetailDTO;
import com.hartron.investharyana.service.mapper.ProjectdetailMapper;
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
 * Test class for the ProjectdetailResource REST controller.
 *
 * @see ProjectdetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectdetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_INVESTORID = UUID.randomUUID();
    private static final UUID UPDATED_INVESTORID = UUID.randomUUID();

    private static final UUID DEFAULT_SECTOR = UUID.randomUUID();
    private static final UUID UPDATED_SECTOR = UUID.randomUUID();

    private static final String DEFAULT_PROJECTPURPOSE = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTPURPOSE = "BBBBBBBBBB";

    private static final UUID DEFAULT_SIZE_OF_INDUSTRY = UUID.randomUUID();
    private static final UUID UPDATED_SIZE_OF_INDUSTRY = UUID.randomUUID();

    private static final UUID DEFAULT_PROJECTYPE = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTYPE = UUID.randomUUID();

    private static final String DEFAULT_NICCODE = "AAAAAAAAAA";
    private static final String UPDATED_NICCODE = "BBBBBBBBBB";

    private static final UUID DEFAULT_CATEGORY_OF_PROJECT = UUID.randomUUID();
    private static final UUID UPDATED_CATEGORY_OF_PROJECT = UUID.randomUUID();

    private static final UUID DEFAULT_COLLABORATION_WITH_FOREIGN_COUNTRY = UUID.randomUUID();
    private static final UUID UPDATED_COLLABORATION_WITH_FOREIGN_COUNTRY = UUID.randomUUID();

    private static final Boolean DEFAULT_EXISTING_REGULATORY_APPROVAL = false;
    private static final Boolean UPDATED_EXISTING_REGULATORY_APPROVAL = true;

    private static final UUID DEFAULT_APPROVAL_APPLICATION_FORM = UUID.randomUUID();
    private static final UUID UPDATED_APPROVAL_APPLICATION_FORM = UUID.randomUUID();

    private static final Boolean DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE = false;
    private static final Boolean UPDATED_EDC_SIF_CLU_FEE_PAID_APPLICABLE = true;

    private static final String DEFAULT_DETAIL_PROJECT_REPORT = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL_PROJECT_REPORT = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVAL_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_APPROVAL_DOCUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_EDC_SIF_CLU_FEE_PAID_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_EDC_SIF_CLU_FEE_PAID_DOCUMENT = "BBBBBBBBBB";

    @Autowired
    private ProjectdetailRepository projectdetailRepository;

    @Autowired
    private ProjectdetailMapper projectdetailMapper;

    @Autowired
    private ProjectdetailService projectdetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectdetailMockMvc;

    private Projectdetail projectdetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectdetailResource projectdetailResource = new ProjectdetailResource(projectdetailService);
        this.restProjectdetailMockMvc = MockMvcBuilders.standaloneSetup(projectdetailResource)
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
    public static Projectdetail createEntity() {
        Projectdetail projectdetail = new Projectdetail()
                .investorid(DEFAULT_INVESTORID)
                .sector(DEFAULT_SECTOR)
                .projectpurpose(DEFAULT_PROJECTPURPOSE)
                .size_of_industry(DEFAULT_SIZE_OF_INDUSTRY)
                .projectype(DEFAULT_PROJECTYPE)
                .niccode(DEFAULT_NICCODE)
                .category_of_project(DEFAULT_CATEGORY_OF_PROJECT)
                .collaboration_with_foreign_country(DEFAULT_COLLABORATION_WITH_FOREIGN_COUNTRY)
                .existing_regulatory_approval(DEFAULT_EXISTING_REGULATORY_APPROVAL)
                .approval_application_form(DEFAULT_APPROVAL_APPLICATION_FORM)
                .edc_sif_clu_fee_paid_applicable(DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE)
                .detail_project_report(DEFAULT_DETAIL_PROJECT_REPORT)
                .approval_document(DEFAULT_APPROVAL_DOCUMENT)
                .edc_sif_clu_fee_paid_document(DEFAULT_EDC_SIF_CLU_FEE_PAID_DOCUMENT);
        return projectdetail;
    }

    @Before
    public void initTest() {
        projectdetailRepository.deleteAll();
        projectdetail = createEntity();
    }

    @Test
    public void createProjectdetail() throws Exception {
        int databaseSizeBeforeCreate = projectdetailRepository.findAll().size();

        // Create the Projectdetail
        ProjectdetailDTO projectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(projectdetail);

        restProjectdetailMockMvc.perform(post("/api/projectdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectdetail in the database
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeCreate + 1);
        Projectdetail testProjectdetail = projectdetailList.get(projectdetailList.size() - 1);
        assertThat(testProjectdetail.getInvestorid()).isEqualTo(DEFAULT_INVESTORID);
        assertThat(testProjectdetail.getSector()).isEqualTo(DEFAULT_SECTOR);
        assertThat(testProjectdetail.getProjectpurpose()).isEqualTo(DEFAULT_PROJECTPURPOSE);
        assertThat(testProjectdetail.getSize_of_industry()).isEqualTo(DEFAULT_SIZE_OF_INDUSTRY);
        assertThat(testProjectdetail.getProjectype()).isEqualTo(DEFAULT_PROJECTYPE);
        assertThat(testProjectdetail.getNiccode()).isEqualTo(DEFAULT_NICCODE);
        assertThat(testProjectdetail.getCategory_of_project()).isEqualTo(DEFAULT_CATEGORY_OF_PROJECT);
        assertThat(testProjectdetail.getCollaboration_with_foreign_country()).isEqualTo(DEFAULT_COLLABORATION_WITH_FOREIGN_COUNTRY);
        assertThat(testProjectdetail.isExisting_regulatory_approval()).isEqualTo(DEFAULT_EXISTING_REGULATORY_APPROVAL);
        assertThat(testProjectdetail.getApproval_application_form()).isEqualTo(DEFAULT_APPROVAL_APPLICATION_FORM);
        assertThat(testProjectdetail.isEdc_sif_clu_fee_paid_applicable()).isEqualTo(DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE);
        assertThat(testProjectdetail.getDetail_project_report()).isEqualTo(DEFAULT_DETAIL_PROJECT_REPORT);
        assertThat(testProjectdetail.getApproval_document()).isEqualTo(DEFAULT_APPROVAL_DOCUMENT);
        assertThat(testProjectdetail.getEdc_sif_clu_fee_paid_document()).isEqualTo(DEFAULT_EDC_SIF_CLU_FEE_PAID_DOCUMENT);
    }

    @Test
    public void createProjectdetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectdetailRepository.findAll().size();

        // Create the Projectdetail with an existing ID
        Projectdetail existingProjectdetail = new Projectdetail();
        existingProjectdetail.setId(UUID.randomUUID());
        ProjectdetailDTO existingProjectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(existingProjectdetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectdetailMockMvc.perform(post("/api/projectdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectdetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectdetails() throws Exception {
        // Initialize the database
        projectdetailRepository.save(projectdetail);

        // Get all the projectdetailList
        restProjectdetailMockMvc.perform(get("/api/projectdetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectdetail.getId().toString())))
            .andExpect(jsonPath("$.[*].investorid").value(hasItem(DEFAULT_INVESTORID.toString())))
            .andExpect(jsonPath("$.[*].sector").value(hasItem(DEFAULT_SECTOR.toString())))
            .andExpect(jsonPath("$.[*].projectpurpose").value(hasItem(DEFAULT_PROJECTPURPOSE.toString())))
            .andExpect(jsonPath("$.[*].size_of_industry").value(hasItem(DEFAULT_SIZE_OF_INDUSTRY.toString())))
            .andExpect(jsonPath("$.[*].projectype").value(hasItem(DEFAULT_PROJECTYPE.toString())))
            .andExpect(jsonPath("$.[*].niccode").value(hasItem(DEFAULT_NICCODE.toString())))
            .andExpect(jsonPath("$.[*].category_of_project").value(hasItem(DEFAULT_CATEGORY_OF_PROJECT.toString())))
            .andExpect(jsonPath("$.[*].collaboration_with_foreign_country").value(hasItem(DEFAULT_COLLABORATION_WITH_FOREIGN_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].existing_regulatory_approval").value(hasItem(DEFAULT_EXISTING_REGULATORY_APPROVAL.booleanValue())))
            .andExpect(jsonPath("$.[*].approval_application_form").value(hasItem(DEFAULT_APPROVAL_APPLICATION_FORM.toString())))
            .andExpect(jsonPath("$.[*].edc_sif_clu_fee_paid_applicable").value(hasItem(DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].detail_project_report").value(hasItem(DEFAULT_DETAIL_PROJECT_REPORT.toString())))
            .andExpect(jsonPath("$.[*].approval_document").value(hasItem(DEFAULT_APPROVAL_DOCUMENT.toString())))
            .andExpect(jsonPath("$.[*].edc_sif_clu_fee_paid_document").value(hasItem(DEFAULT_EDC_SIF_CLU_FEE_PAID_DOCUMENT.toString())));
    }

    @Test
    public void getProjectdetail() throws Exception {
        // Initialize the database
        projectdetailRepository.save(projectdetail);

        // Get the projectdetail
        restProjectdetailMockMvc.perform(get("/api/projectdetails/{id}", projectdetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectdetail.getId().toString()))
            .andExpect(jsonPath("$.investorid").value(DEFAULT_INVESTORID.toString()))
            .andExpect(jsonPath("$.sector").value(DEFAULT_SECTOR.toString()))
            .andExpect(jsonPath("$.projectpurpose").value(DEFAULT_PROJECTPURPOSE.toString()))
            .andExpect(jsonPath("$.size_of_industry").value(DEFAULT_SIZE_OF_INDUSTRY.toString()))
            .andExpect(jsonPath("$.projectype").value(DEFAULT_PROJECTYPE.toString()))
            .andExpect(jsonPath("$.niccode").value(DEFAULT_NICCODE.toString()))
            .andExpect(jsonPath("$.category_of_project").value(DEFAULT_CATEGORY_OF_PROJECT.toString()))
            .andExpect(jsonPath("$.collaboration_with_foreign_country").value(DEFAULT_COLLABORATION_WITH_FOREIGN_COUNTRY.toString()))
            .andExpect(jsonPath("$.existing_regulatory_approval").value(DEFAULT_EXISTING_REGULATORY_APPROVAL.booleanValue()))
            .andExpect(jsonPath("$.approval_application_form").value(DEFAULT_APPROVAL_APPLICATION_FORM.toString()))
            .andExpect(jsonPath("$.edc_sif_clu_fee_paid_applicable").value(DEFAULT_EDC_SIF_CLU_FEE_PAID_APPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.detail_project_report").value(DEFAULT_DETAIL_PROJECT_REPORT.toString()))
            .andExpect(jsonPath("$.approval_document").value(DEFAULT_APPROVAL_DOCUMENT.toString()))
            .andExpect(jsonPath("$.edc_sif_clu_fee_paid_document").value(DEFAULT_EDC_SIF_CLU_FEE_PAID_DOCUMENT.toString()));
    }

    @Test
    public void getNonExistingProjectdetail() throws Exception {
        // Get the projectdetail
        restProjectdetailMockMvc.perform(get("/api/projectdetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectdetail() throws Exception {
        // Initialize the database
        projectdetailRepository.save(projectdetail);
        int databaseSizeBeforeUpdate = projectdetailRepository.findAll().size();

        // Update the projectdetail
        Projectdetail updatedProjectdetail = projectdetailRepository.findOne(projectdetail.getId());
        updatedProjectdetail
                .investorid(UPDATED_INVESTORID)
                .sector(UPDATED_SECTOR)
                .projectpurpose(UPDATED_PROJECTPURPOSE)
                .size_of_industry(UPDATED_SIZE_OF_INDUSTRY)
                .projectype(UPDATED_PROJECTYPE)
                .niccode(UPDATED_NICCODE)
                .category_of_project(UPDATED_CATEGORY_OF_PROJECT)
                .collaboration_with_foreign_country(UPDATED_COLLABORATION_WITH_FOREIGN_COUNTRY)
                .existing_regulatory_approval(UPDATED_EXISTING_REGULATORY_APPROVAL)
                .approval_application_form(UPDATED_APPROVAL_APPLICATION_FORM)
                .edc_sif_clu_fee_paid_applicable(UPDATED_EDC_SIF_CLU_FEE_PAID_APPLICABLE)
                .detail_project_report(UPDATED_DETAIL_PROJECT_REPORT)
                .approval_document(UPDATED_APPROVAL_DOCUMENT)
                .edc_sif_clu_fee_paid_document(UPDATED_EDC_SIF_CLU_FEE_PAID_DOCUMENT);
        ProjectdetailDTO projectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(updatedProjectdetail);

        restProjectdetailMockMvc.perform(put("/api/projectdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailDTO)))
            .andExpect(status().isOk());

        // Validate the Projectdetail in the database
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeUpdate);
        Projectdetail testProjectdetail = projectdetailList.get(projectdetailList.size() - 1);
        assertThat(testProjectdetail.getInvestorid()).isEqualTo(UPDATED_INVESTORID);
        assertThat(testProjectdetail.getSector()).isEqualTo(UPDATED_SECTOR);
        assertThat(testProjectdetail.getProjectpurpose()).isEqualTo(UPDATED_PROJECTPURPOSE);
        assertThat(testProjectdetail.getSize_of_industry()).isEqualTo(UPDATED_SIZE_OF_INDUSTRY);
        assertThat(testProjectdetail.getProjectype()).isEqualTo(UPDATED_PROJECTYPE);
        assertThat(testProjectdetail.getNiccode()).isEqualTo(UPDATED_NICCODE);
        assertThat(testProjectdetail.getCategory_of_project()).isEqualTo(UPDATED_CATEGORY_OF_PROJECT);
        assertThat(testProjectdetail.getCollaboration_with_foreign_country()).isEqualTo(UPDATED_COLLABORATION_WITH_FOREIGN_COUNTRY);
        assertThat(testProjectdetail.isExisting_regulatory_approval()).isEqualTo(UPDATED_EXISTING_REGULATORY_APPROVAL);
        assertThat(testProjectdetail.getApproval_application_form()).isEqualTo(UPDATED_APPROVAL_APPLICATION_FORM);
        assertThat(testProjectdetail.isEdc_sif_clu_fee_paid_applicable()).isEqualTo(UPDATED_EDC_SIF_CLU_FEE_PAID_APPLICABLE);
        assertThat(testProjectdetail.getDetail_project_report()).isEqualTo(UPDATED_DETAIL_PROJECT_REPORT);
        assertThat(testProjectdetail.getApproval_document()).isEqualTo(UPDATED_APPROVAL_DOCUMENT);
        assertThat(testProjectdetail.getEdc_sif_clu_fee_paid_document()).isEqualTo(UPDATED_EDC_SIF_CLU_FEE_PAID_DOCUMENT);
    }

    @Test
    public void updateNonExistingProjectdetail() throws Exception {
        int databaseSizeBeforeUpdate = projectdetailRepository.findAll().size();

        // Create the Projectdetail
        ProjectdetailDTO projectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(projectdetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectdetailMockMvc.perform(put("/api/projectdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectdetail in the database
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectdetail() throws Exception {
        // Initialize the database
        projectdetailRepository.save(projectdetail);
        int databaseSizeBeforeDelete = projectdetailRepository.findAll().size();

        // Get the projectdetail
        restProjectdetailMockMvc.perform(delete("/api/projectdetails/{id}", projectdetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectdetail> projectdetailList = projectdetailRepository.findAll();
        assertThat(projectdetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectdetail.class);
    }
}
