package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.ProjectServicePaymentDetails;
import com.hartron.investharyana.repository.ProjectServicePaymentDetailsRepository;
import com.hartron.investharyana.service.ProjectServicePaymentDetailsService;
import com.hartron.investharyana.service.dto.ProjectServicePaymentDetailsDTO;
import com.hartron.investharyana.service.mapper.ProjectServicePaymentDetailsMapper;
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
 * Test class for the ProjectServicePaymentDetailsResource REST controller.
 *
 * @see ProjectServicePaymentDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectServicePaymentDetailsResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final UUID DEFAULT_SERVICEID = UUID.randomUUID();
    private static final UUID UPDATED_SERVICEID = UUID.randomUUID();

    private static final BigDecimal DEFAULT_PAYMENT_MADE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PAYMENT_MADE = new BigDecimal(2);

    private static final String DEFAULT_PAYMENT_MADE_BY = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_MADE_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_PAYMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PAYMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_RESPONSE = "BBBBBBBBBB";

    @Autowired
    private ProjectServicePaymentDetailsRepository projectServicePaymentDetailsRepository;

    @Autowired
    private ProjectServicePaymentDetailsMapper projectServicePaymentDetailsMapper;

    @Autowired
    private ProjectServicePaymentDetailsService projectServicePaymentDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectServicePaymentDetailsMockMvc;

    private ProjectServicePaymentDetails projectServicePaymentDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectServicePaymentDetailsResource projectServicePaymentDetailsResource = new ProjectServicePaymentDetailsResource(projectServicePaymentDetailsService);
        this.restProjectServicePaymentDetailsMockMvc = MockMvcBuilders.standaloneSetup(projectServicePaymentDetailsResource)
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
    public static ProjectServicePaymentDetails createEntity() {
        ProjectServicePaymentDetails projectServicePaymentDetails = new ProjectServicePaymentDetails()
                .projectid(DEFAULT_PROJECTID)
                .serviceid(DEFAULT_SERVICEID)
                .paymentMade(DEFAULT_PAYMENT_MADE)
                .paymentMadeBy(DEFAULT_PAYMENT_MADE_BY)
                .paymentDate(DEFAULT_PAYMENT_DATE)
                .transactionId(DEFAULT_TRANSACTION_ID)
                .paymentResponse(DEFAULT_PAYMENT_RESPONSE);
        return projectServicePaymentDetails;
    }

    @Before
    public void initTest() {
        projectServicePaymentDetailsRepository.deleteAll();
        projectServicePaymentDetails = createEntity();
    }

    @Test
    public void createProjectServicePaymentDetails() throws Exception {
        int databaseSizeBeforeCreate = projectServicePaymentDetailsRepository.findAll().size();

        // Create the ProjectServicePaymentDetails
        ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO = projectServicePaymentDetailsMapper.projectServicePaymentDetailsToProjectServicePaymentDetailsDTO(projectServicePaymentDetails);

        restProjectServicePaymentDetailsMockMvc.perform(post("/api/project-service-payment-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServicePaymentDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectServicePaymentDetails in the database
        List<ProjectServicePaymentDetails> projectServicePaymentDetailsList = projectServicePaymentDetailsRepository.findAll();
        assertThat(projectServicePaymentDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectServicePaymentDetails testProjectServicePaymentDetails = projectServicePaymentDetailsList.get(projectServicePaymentDetailsList.size() - 1);
        assertThat(testProjectServicePaymentDetails.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectServicePaymentDetails.getServiceid()).isEqualTo(DEFAULT_SERVICEID);
        assertThat(testProjectServicePaymentDetails.getPaymentMade()).isEqualTo(DEFAULT_PAYMENT_MADE);
        assertThat(testProjectServicePaymentDetails.getPaymentMadeBy()).isEqualTo(DEFAULT_PAYMENT_MADE_BY);
        assertThat(testProjectServicePaymentDetails.getPaymentDate()).isEqualTo(DEFAULT_PAYMENT_DATE);
        assertThat(testProjectServicePaymentDetails.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testProjectServicePaymentDetails.getPaymentResponse()).isEqualTo(DEFAULT_PAYMENT_RESPONSE);
    }

    @Test
    public void createProjectServicePaymentDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectServicePaymentDetailsRepository.findAll().size();

        // Create the ProjectServicePaymentDetails with an existing ID
        ProjectServicePaymentDetails existingProjectServicePaymentDetails = new ProjectServicePaymentDetails();
        existingProjectServicePaymentDetails.setId(UUID.randomUUID());
        ProjectServicePaymentDetailsDTO existingProjectServicePaymentDetailsDTO = projectServicePaymentDetailsMapper.projectServicePaymentDetailsToProjectServicePaymentDetailsDTO(existingProjectServicePaymentDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectServicePaymentDetailsMockMvc.perform(post("/api/project-service-payment-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectServicePaymentDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ProjectServicePaymentDetails> projectServicePaymentDetailsList = projectServicePaymentDetailsRepository.findAll();
        assertThat(projectServicePaymentDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectServicePaymentDetails() throws Exception {
        // Initialize the database
        projectServicePaymentDetailsRepository.save(projectServicePaymentDetails);

        // Get all the projectServicePaymentDetailsList
        restProjectServicePaymentDetailsMockMvc.perform(get("/api/project-service-payment-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectServicePaymentDetails.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].serviceid").value(hasItem(DEFAULT_SERVICEID.toString())))
            .andExpect(jsonPath("$.[*].paymentMade").value(hasItem(DEFAULT_PAYMENT_MADE.intValue())))
            .andExpect(jsonPath("$.[*].paymentMadeBy").value(hasItem(DEFAULT_PAYMENT_MADE_BY.toString())))
            .andExpect(jsonPath("$.[*].paymentDate").value(hasItem(sameInstant(DEFAULT_PAYMENT_DATE))))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID.toString())))
            .andExpect(jsonPath("$.[*].paymentResponse").value(hasItem(DEFAULT_PAYMENT_RESPONSE.toString())));
    }

    @Test
    public void getProjectServicePaymentDetails() throws Exception {
        // Initialize the database
        projectServicePaymentDetailsRepository.save(projectServicePaymentDetails);

        // Get the projectServicePaymentDetails
        restProjectServicePaymentDetailsMockMvc.perform(get("/api/project-service-payment-details/{id}", projectServicePaymentDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectServicePaymentDetails.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.serviceid").value(DEFAULT_SERVICEID.toString()))
            .andExpect(jsonPath("$.paymentMade").value(DEFAULT_PAYMENT_MADE.intValue()))
            .andExpect(jsonPath("$.paymentMadeBy").value(DEFAULT_PAYMENT_MADE_BY.toString()))
            .andExpect(jsonPath("$.paymentDate").value(sameInstant(DEFAULT_PAYMENT_DATE)))
            .andExpect(jsonPath("$.transactionId").value(DEFAULT_TRANSACTION_ID.toString()))
            .andExpect(jsonPath("$.paymentResponse").value(DEFAULT_PAYMENT_RESPONSE.toString()));
    }

    @Test
    public void getNonExistingProjectServicePaymentDetails() throws Exception {
        // Get the projectServicePaymentDetails
        restProjectServicePaymentDetailsMockMvc.perform(get("/api/project-service-payment-details/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectServicePaymentDetails() throws Exception {
        // Initialize the database
        projectServicePaymentDetailsRepository.save(projectServicePaymentDetails);
        int databaseSizeBeforeUpdate = projectServicePaymentDetailsRepository.findAll().size();

        // Update the projectServicePaymentDetails
        ProjectServicePaymentDetails updatedProjectServicePaymentDetails = projectServicePaymentDetailsRepository.findOne(projectServicePaymentDetails.getId());
        updatedProjectServicePaymentDetails
                .projectid(UPDATED_PROJECTID)
                .serviceid(UPDATED_SERVICEID)
                .paymentMade(UPDATED_PAYMENT_MADE)
                .paymentMadeBy(UPDATED_PAYMENT_MADE_BY)
                .paymentDate(UPDATED_PAYMENT_DATE)
                .transactionId(UPDATED_TRANSACTION_ID)
                .paymentResponse(UPDATED_PAYMENT_RESPONSE);
        ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO = projectServicePaymentDetailsMapper.projectServicePaymentDetailsToProjectServicePaymentDetailsDTO(updatedProjectServicePaymentDetails);

        restProjectServicePaymentDetailsMockMvc.perform(put("/api/project-service-payment-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServicePaymentDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectServicePaymentDetails in the database
        List<ProjectServicePaymentDetails> projectServicePaymentDetailsList = projectServicePaymentDetailsRepository.findAll();
        assertThat(projectServicePaymentDetailsList).hasSize(databaseSizeBeforeUpdate);
        ProjectServicePaymentDetails testProjectServicePaymentDetails = projectServicePaymentDetailsList.get(projectServicePaymentDetailsList.size() - 1);
        assertThat(testProjectServicePaymentDetails.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectServicePaymentDetails.getServiceid()).isEqualTo(UPDATED_SERVICEID);
        assertThat(testProjectServicePaymentDetails.getPaymentMade()).isEqualTo(UPDATED_PAYMENT_MADE);
        assertThat(testProjectServicePaymentDetails.getPaymentMadeBy()).isEqualTo(UPDATED_PAYMENT_MADE_BY);
        assertThat(testProjectServicePaymentDetails.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testProjectServicePaymentDetails.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testProjectServicePaymentDetails.getPaymentResponse()).isEqualTo(UPDATED_PAYMENT_RESPONSE);
    }

    @Test
    public void updateNonExistingProjectServicePaymentDetails() throws Exception {
        int databaseSizeBeforeUpdate = projectServicePaymentDetailsRepository.findAll().size();

        // Create the ProjectServicePaymentDetails
        ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO = projectServicePaymentDetailsMapper.projectServicePaymentDetailsToProjectServicePaymentDetailsDTO(projectServicePaymentDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectServicePaymentDetailsMockMvc.perform(put("/api/project-service-payment-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServicePaymentDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectServicePaymentDetails in the database
        List<ProjectServicePaymentDetails> projectServicePaymentDetailsList = projectServicePaymentDetailsRepository.findAll();
        assertThat(projectServicePaymentDetailsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectServicePaymentDetails() throws Exception {
        // Initialize the database
        projectServicePaymentDetailsRepository.save(projectServicePaymentDetails);
        int databaseSizeBeforeDelete = projectServicePaymentDetailsRepository.findAll().size();

        // Get the projectServicePaymentDetails
        restProjectServicePaymentDetailsMockMvc.perform(delete("/api/project-service-payment-details/{id}", projectServicePaymentDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProjectServicePaymentDetails> projectServicePaymentDetailsList = projectServicePaymentDetailsRepository.findAll();
        assertThat(projectServicePaymentDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectServicePaymentDetails.class);
    }
}
