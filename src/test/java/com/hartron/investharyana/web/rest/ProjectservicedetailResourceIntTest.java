package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Projectservicedetail;
import com.hartron.investharyana.repository.ProjectservicedetailRepository;
import com.hartron.investharyana.service.ProjectservicedetailService;
import com.hartron.investharyana.service.dto.ProjectservicedetailDTO;
import com.hartron.investharyana.service.mapper.ProjectservicedetailMapper;
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
 * Test class for the ProjectservicedetailResource REST controller.
 *
 * @see ProjectservicedetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectservicedetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final UUID DEFAULT_SERVICEID = UUID.randomUUID();
    private static final UUID UPDATED_SERVICEID = UUID.randomUUID();

    private static final Boolean DEFAULT_IS_REQUIRED = false;
    private static final Boolean UPDATED_IS_REQUIRED = true;

    private static final ZonedDateTime DEFAULT_REQUIRE_MARKED_ON_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REQUIRE_MARKED_ON_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_REQUIRE_MARKED_BY = "AAAAAAAAAA";
    private static final String UPDATED_REQUIRE_MARKED_BY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ASSIGNED = false;
    private static final Boolean UPDATED_IS_ASSIGNED = true;

    private static final ZonedDateTime DEFAULT_ASSIG_ON_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ASSIG_ON_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_ASSIGN_BY = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGN_BY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FORM_FILLED_STATUS = false;
    private static final Boolean UPDATED_FORM_FILLED_STATUS = true;

    private static final Boolean DEFAULT_IS_PAYMENT_MADE = false;
    private static final Boolean UPDATED_IS_PAYMENT_MADE = true;

    private static final Boolean DEFAULT_IS_PAYMENT_VERIFIED = false;
    private static final Boolean UPDATED_IS_PAYMENT_VERIFIED = true;

    private static final ZonedDateTime DEFAULT_FORM_FILLED_ON_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FORM_FILLED_ON_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_FORM_FILLED_BY = "AAAAAAAAAA";
    private static final String UPDATED_FORM_FILLED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_PAYMENT_MADE_ON_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PAYMENT_MADE_ON_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LATEST_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_LATEST_COMMENTS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SERVICE_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SERVICE_FEE = new BigDecimal(2);

    @Autowired
    private ProjectservicedetailRepository projectservicedetailRepository;

    @Autowired
    private ProjectservicedetailMapper projectservicedetailMapper;

    @Autowired
    private ProjectservicedetailService projectservicedetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectservicedetailMockMvc;

    private Projectservicedetail projectservicedetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectservicedetailResource projectservicedetailResource = new ProjectservicedetailResource(projectservicedetailService);
        this.restProjectservicedetailMockMvc = MockMvcBuilders.standaloneSetup(projectservicedetailResource)
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
    public static Projectservicedetail createEntity() {
        Projectservicedetail projectservicedetail = new Projectservicedetail()
                .projectid(DEFAULT_PROJECTID)
                .serviceid(DEFAULT_SERVICEID)
                .isRequired(DEFAULT_IS_REQUIRED)
                .requireMarkedOnDate(DEFAULT_REQUIRE_MARKED_ON_DATE)
                .requireMarkedBy(DEFAULT_REQUIRE_MARKED_BY)
                .isAssigned(DEFAULT_IS_ASSIGNED)
                .assigOnDate(DEFAULT_ASSIG_ON_DATE)
                .assignBy(DEFAULT_ASSIGN_BY)
                .formFilledStatus(DEFAULT_FORM_FILLED_STATUS)
                .isPaymentMade(DEFAULT_IS_PAYMENT_MADE)
                .isPaymentVerified(DEFAULT_IS_PAYMENT_VERIFIED)
                .formFilledOnDate(DEFAULT_FORM_FILLED_ON_DATE)
                .formFilledBy(DEFAULT_FORM_FILLED_BY)
                .paymentMadeOnDate(DEFAULT_PAYMENT_MADE_ON_DATE)
                .status(DEFAULT_STATUS)
                .latestComments(DEFAULT_LATEST_COMMENTS)
                .serviceFee(DEFAULT_SERVICE_FEE);
        return projectservicedetail;
    }

    @Before
    public void initTest() {
        projectservicedetailRepository.deleteAll();
        projectservicedetail = createEntity();
    }

    @Test
    public void createProjectservicedetail() throws Exception {
        int databaseSizeBeforeCreate = projectservicedetailRepository.findAll().size();

        // Create the Projectservicedetail
        ProjectservicedetailDTO projectservicedetailDTO = projectservicedetailMapper.projectservicedetailToProjectservicedetailDTO(projectservicedetail);

        restProjectservicedetailMockMvc.perform(post("/api/projectservicedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectservicedetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectservicedetail in the database
        List<Projectservicedetail> projectservicedetailList = projectservicedetailRepository.findAll();
        assertThat(projectservicedetailList).hasSize(databaseSizeBeforeCreate + 1);
        Projectservicedetail testProjectservicedetail = projectservicedetailList.get(projectservicedetailList.size() - 1);
        assertThat(testProjectservicedetail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectservicedetail.getServiceid()).isEqualTo(DEFAULT_SERVICEID);
        assertThat(testProjectservicedetail.isIsRequired()).isEqualTo(DEFAULT_IS_REQUIRED);
        assertThat(testProjectservicedetail.getRequireMarkedOnDate()).isEqualTo(DEFAULT_REQUIRE_MARKED_ON_DATE);
        assertThat(testProjectservicedetail.getRequireMarkedBy()).isEqualTo(DEFAULT_REQUIRE_MARKED_BY);
        assertThat(testProjectservicedetail.isIsAssigned()).isEqualTo(DEFAULT_IS_ASSIGNED);
        assertThat(testProjectservicedetail.getAssigOnDate()).isEqualTo(DEFAULT_ASSIG_ON_DATE);
        assertThat(testProjectservicedetail.getAssignBy()).isEqualTo(DEFAULT_ASSIGN_BY);
        assertThat(testProjectservicedetail.isFormFilledStatus()).isEqualTo(DEFAULT_FORM_FILLED_STATUS);
        assertThat(testProjectservicedetail.isIsPaymentMade()).isEqualTo(DEFAULT_IS_PAYMENT_MADE);
        assertThat(testProjectservicedetail.isIsPaymentVerified()).isEqualTo(DEFAULT_IS_PAYMENT_VERIFIED);
        assertThat(testProjectservicedetail.getFormFilledOnDate()).isEqualTo(DEFAULT_FORM_FILLED_ON_DATE);
        assertThat(testProjectservicedetail.getFormFilledBy()).isEqualTo(DEFAULT_FORM_FILLED_BY);
        assertThat(testProjectservicedetail.getPaymentMadeOnDate()).isEqualTo(DEFAULT_PAYMENT_MADE_ON_DATE);
        assertThat(testProjectservicedetail.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProjectservicedetail.getLatestComments()).isEqualTo(DEFAULT_LATEST_COMMENTS);
        assertThat(testProjectservicedetail.getServiceFee()).isEqualTo(DEFAULT_SERVICE_FEE);
    }

    @Test
    public void createProjectservicedetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectservicedetailRepository.findAll().size();

        // Create the Projectservicedetail with an existing ID
        Projectservicedetail existingProjectservicedetail = new Projectservicedetail();
        existingProjectservicedetail.setId(UUID.randomUUID());
        ProjectservicedetailDTO existingProjectservicedetailDTO = projectservicedetailMapper.projectservicedetailToProjectservicedetailDTO(existingProjectservicedetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectservicedetailMockMvc.perform(post("/api/projectservicedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectservicedetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Projectservicedetail> projectservicedetailList = projectservicedetailRepository.findAll();
        assertThat(projectservicedetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectservicedetails() throws Exception {
        // Initialize the database
        projectservicedetailRepository.save(projectservicedetail);

        // Get all the projectservicedetailList
        restProjectservicedetailMockMvc.perform(get("/api/projectservicedetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectservicedetail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].serviceid").value(hasItem(DEFAULT_SERVICEID.toString())))
            .andExpect(jsonPath("$.[*].isRequired").value(hasItem(DEFAULT_IS_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].requireMarkedOnDate").value(hasItem(sameInstant(DEFAULT_REQUIRE_MARKED_ON_DATE))))
            .andExpect(jsonPath("$.[*].requireMarkedBy").value(hasItem(DEFAULT_REQUIRE_MARKED_BY.toString())))
            .andExpect(jsonPath("$.[*].isAssigned").value(hasItem(DEFAULT_IS_ASSIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].assigOnDate").value(hasItem(sameInstant(DEFAULT_ASSIG_ON_DATE))))
            .andExpect(jsonPath("$.[*].assignBy").value(hasItem(DEFAULT_ASSIGN_BY.toString())))
            .andExpect(jsonPath("$.[*].formFilledStatus").value(hasItem(DEFAULT_FORM_FILLED_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].isPaymentMade").value(hasItem(DEFAULT_IS_PAYMENT_MADE.booleanValue())))
            .andExpect(jsonPath("$.[*].isPaymentVerified").value(hasItem(DEFAULT_IS_PAYMENT_VERIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].formFilledOnDate").value(hasItem(sameInstant(DEFAULT_FORM_FILLED_ON_DATE))))
            .andExpect(jsonPath("$.[*].formFilledBy").value(hasItem(DEFAULT_FORM_FILLED_BY.toString())))
            .andExpect(jsonPath("$.[*].paymentMadeOnDate").value(hasItem(sameInstant(DEFAULT_PAYMENT_MADE_ON_DATE))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].latestComments").value(hasItem(DEFAULT_LATEST_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].serviceFee").value(hasItem(DEFAULT_SERVICE_FEE.intValue())));
    }

    @Test
    public void getProjectservicedetail() throws Exception {
        // Initialize the database
        projectservicedetailRepository.save(projectservicedetail);

        // Get the projectservicedetail
        restProjectservicedetailMockMvc.perform(get("/api/projectservicedetails/{id}", projectservicedetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectservicedetail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.serviceid").value(DEFAULT_SERVICEID.toString()))
            .andExpect(jsonPath("$.isRequired").value(DEFAULT_IS_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.requireMarkedOnDate").value(sameInstant(DEFAULT_REQUIRE_MARKED_ON_DATE)))
            .andExpect(jsonPath("$.requireMarkedBy").value(DEFAULT_REQUIRE_MARKED_BY.toString()))
            .andExpect(jsonPath("$.isAssigned").value(DEFAULT_IS_ASSIGNED.booleanValue()))
            .andExpect(jsonPath("$.assigOnDate").value(sameInstant(DEFAULT_ASSIG_ON_DATE)))
            .andExpect(jsonPath("$.assignBy").value(DEFAULT_ASSIGN_BY.toString()))
            .andExpect(jsonPath("$.formFilledStatus").value(DEFAULT_FORM_FILLED_STATUS.booleanValue()))
            .andExpect(jsonPath("$.isPaymentMade").value(DEFAULT_IS_PAYMENT_MADE.booleanValue()))
            .andExpect(jsonPath("$.isPaymentVerified").value(DEFAULT_IS_PAYMENT_VERIFIED.booleanValue()))
            .andExpect(jsonPath("$.formFilledOnDate").value(sameInstant(DEFAULT_FORM_FILLED_ON_DATE)))
            .andExpect(jsonPath("$.formFilledBy").value(DEFAULT_FORM_FILLED_BY.toString()))
            .andExpect(jsonPath("$.paymentMadeOnDate").value(sameInstant(DEFAULT_PAYMENT_MADE_ON_DATE)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.latestComments").value(DEFAULT_LATEST_COMMENTS.toString()))
            .andExpect(jsonPath("$.serviceFee").value(DEFAULT_SERVICE_FEE.intValue()));
    }

    @Test
    public void getNonExistingProjectservicedetail() throws Exception {
        // Get the projectservicedetail
        restProjectservicedetailMockMvc.perform(get("/api/projectservicedetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectservicedetail() throws Exception {
        // Initialize the database
        projectservicedetailRepository.save(projectservicedetail);
        int databaseSizeBeforeUpdate = projectservicedetailRepository.findAll().size();

        // Update the projectservicedetail
        Projectservicedetail updatedProjectservicedetail = projectservicedetailRepository.findOne(projectservicedetail.getId());
        updatedProjectservicedetail
                .projectid(UPDATED_PROJECTID)
                .serviceid(UPDATED_SERVICEID)
                .isRequired(UPDATED_IS_REQUIRED)
                .requireMarkedOnDate(UPDATED_REQUIRE_MARKED_ON_DATE)
                .requireMarkedBy(UPDATED_REQUIRE_MARKED_BY)
                .isAssigned(UPDATED_IS_ASSIGNED)
                .assigOnDate(UPDATED_ASSIG_ON_DATE)
                .assignBy(UPDATED_ASSIGN_BY)
                .formFilledStatus(UPDATED_FORM_FILLED_STATUS)
                .isPaymentMade(UPDATED_IS_PAYMENT_MADE)
                .isPaymentVerified(UPDATED_IS_PAYMENT_VERIFIED)
                .formFilledOnDate(UPDATED_FORM_FILLED_ON_DATE)
                .formFilledBy(UPDATED_FORM_FILLED_BY)
                .paymentMadeOnDate(UPDATED_PAYMENT_MADE_ON_DATE)
                .status(UPDATED_STATUS)
                .latestComments(UPDATED_LATEST_COMMENTS)
                .serviceFee(UPDATED_SERVICE_FEE);
        ProjectservicedetailDTO projectservicedetailDTO = projectservicedetailMapper.projectservicedetailToProjectservicedetailDTO(updatedProjectservicedetail);

        restProjectservicedetailMockMvc.perform(put("/api/projectservicedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectservicedetailDTO)))
            .andExpect(status().isOk());

        // Validate the Projectservicedetail in the database
        List<Projectservicedetail> projectservicedetailList = projectservicedetailRepository.findAll();
        assertThat(projectservicedetailList).hasSize(databaseSizeBeforeUpdate);
        Projectservicedetail testProjectservicedetail = projectservicedetailList.get(projectservicedetailList.size() - 1);
        assertThat(testProjectservicedetail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectservicedetail.getServiceid()).isEqualTo(UPDATED_SERVICEID);
        assertThat(testProjectservicedetail.isIsRequired()).isEqualTo(UPDATED_IS_REQUIRED);
        assertThat(testProjectservicedetail.getRequireMarkedOnDate()).isEqualTo(UPDATED_REQUIRE_MARKED_ON_DATE);
        assertThat(testProjectservicedetail.getRequireMarkedBy()).isEqualTo(UPDATED_REQUIRE_MARKED_BY);
        assertThat(testProjectservicedetail.isIsAssigned()).isEqualTo(UPDATED_IS_ASSIGNED);
        assertThat(testProjectservicedetail.getAssigOnDate()).isEqualTo(UPDATED_ASSIG_ON_DATE);
        assertThat(testProjectservicedetail.getAssignBy()).isEqualTo(UPDATED_ASSIGN_BY);
        assertThat(testProjectservicedetail.isFormFilledStatus()).isEqualTo(UPDATED_FORM_FILLED_STATUS);
        assertThat(testProjectservicedetail.isIsPaymentMade()).isEqualTo(UPDATED_IS_PAYMENT_MADE);
        assertThat(testProjectservicedetail.isIsPaymentVerified()).isEqualTo(UPDATED_IS_PAYMENT_VERIFIED);
        assertThat(testProjectservicedetail.getFormFilledOnDate()).isEqualTo(UPDATED_FORM_FILLED_ON_DATE);
        assertThat(testProjectservicedetail.getFormFilledBy()).isEqualTo(UPDATED_FORM_FILLED_BY);
        assertThat(testProjectservicedetail.getPaymentMadeOnDate()).isEqualTo(UPDATED_PAYMENT_MADE_ON_DATE);
        assertThat(testProjectservicedetail.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProjectservicedetail.getLatestComments()).isEqualTo(UPDATED_LATEST_COMMENTS);
        assertThat(testProjectservicedetail.getServiceFee()).isEqualTo(UPDATED_SERVICE_FEE);
    }

    @Test
    public void updateNonExistingProjectservicedetail() throws Exception {
        int databaseSizeBeforeUpdate = projectservicedetailRepository.findAll().size();

        // Create the Projectservicedetail
        ProjectservicedetailDTO projectservicedetailDTO = projectservicedetailMapper.projectservicedetailToProjectservicedetailDTO(projectservicedetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectservicedetailMockMvc.perform(put("/api/projectservicedetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectservicedetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Projectservicedetail in the database
        List<Projectservicedetail> projectservicedetailList = projectservicedetailRepository.findAll();
        assertThat(projectservicedetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectservicedetail() throws Exception {
        // Initialize the database
        projectservicedetailRepository.save(projectservicedetail);
        int databaseSizeBeforeDelete = projectservicedetailRepository.findAll().size();

        // Get the projectservicedetail
        restProjectservicedetailMockMvc.perform(delete("/api/projectservicedetails/{id}", projectservicedetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Projectservicedetail> projectservicedetailList = projectservicedetailRepository.findAll();
        assertThat(projectservicedetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectservicedetail.class);
    }
}
