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

    private static final String DEFAULT_DEPARTMENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICENAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICENAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISREQUIRED = false;
    private static final Boolean UPDATED_ISREQUIRED = true;

    private static final ZonedDateTime DEFAULT_MARKREQUIREDONDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MARKREQUIREDONDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MARKREQUIREDBY = "AAAAAAAAAA";
    private static final String UPDATED_MARKREQUIREDBY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISASSIGNED = false;
    private static final Boolean UPDATED_ISASSIGNED = true;

    private static final String DEFAULT_MARKASSIGNEDBY = "AAAAAAAAAA";
    private static final String UPDATED_MARKASSIGNEDBY = "BBBBBBBBBB";

    private static final Integer DEFAULT_FEEREQUIRED = 1;
    private static final Integer UPDATED_FEEREQUIRED = 2;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

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
                .departmentname(DEFAULT_DEPARTMENTNAME)
                .servicename(DEFAULT_SERVICENAME)
                .isrequired(DEFAULT_ISREQUIRED)
                .markrequiredondate(DEFAULT_MARKREQUIREDONDATE)
                .markrequiredby(DEFAULT_MARKREQUIREDBY)
                .isassigned(DEFAULT_ISASSIGNED)
                .markassignedby(DEFAULT_MARKASSIGNEDBY)
                .feerequired(DEFAULT_FEEREQUIRED)
                .status(DEFAULT_STATUS)
                .comment(DEFAULT_COMMENT);
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
        assertThat(testProjectservicedetail.getDepartmentname()).isEqualTo(DEFAULT_DEPARTMENTNAME);
        assertThat(testProjectservicedetail.getServicename()).isEqualTo(DEFAULT_SERVICENAME);
        assertThat(testProjectservicedetail.isIsrequired()).isEqualTo(DEFAULT_ISREQUIRED);
        assertThat(testProjectservicedetail.getMarkrequiredondate()).isEqualTo(DEFAULT_MARKREQUIREDONDATE);
        assertThat(testProjectservicedetail.getMarkrequiredby()).isEqualTo(DEFAULT_MARKREQUIREDBY);
        assertThat(testProjectservicedetail.isIsassigned()).isEqualTo(DEFAULT_ISASSIGNED);
        assertThat(testProjectservicedetail.getMarkassignedby()).isEqualTo(DEFAULT_MARKASSIGNEDBY);
        assertThat(testProjectservicedetail.getFeerequired()).isEqualTo(DEFAULT_FEEREQUIRED);
        assertThat(testProjectservicedetail.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProjectservicedetail.getComment()).isEqualTo(DEFAULT_COMMENT);
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
            .andExpect(jsonPath("$.[*].departmentname").value(hasItem(DEFAULT_DEPARTMENTNAME.toString())))
            .andExpect(jsonPath("$.[*].servicename").value(hasItem(DEFAULT_SERVICENAME.toString())))
            .andExpect(jsonPath("$.[*].isrequired").value(hasItem(DEFAULT_ISREQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].markrequiredondate").value(hasItem(sameInstant(DEFAULT_MARKREQUIREDONDATE))))
            .andExpect(jsonPath("$.[*].markrequiredby").value(hasItem(DEFAULT_MARKREQUIREDBY.toString())))
            .andExpect(jsonPath("$.[*].isassigned").value(hasItem(DEFAULT_ISASSIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].markassignedby").value(hasItem(DEFAULT_MARKASSIGNEDBY.toString())))
            .andExpect(jsonPath("$.[*].feerequired").value(hasItem(DEFAULT_FEEREQUIRED)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())));
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
            .andExpect(jsonPath("$.departmentname").value(DEFAULT_DEPARTMENTNAME.toString()))
            .andExpect(jsonPath("$.servicename").value(DEFAULT_SERVICENAME.toString()))
            .andExpect(jsonPath("$.isrequired").value(DEFAULT_ISREQUIRED.booleanValue()))
            .andExpect(jsonPath("$.markrequiredondate").value(sameInstant(DEFAULT_MARKREQUIREDONDATE)))
            .andExpect(jsonPath("$.markrequiredby").value(DEFAULT_MARKREQUIREDBY.toString()))
            .andExpect(jsonPath("$.isassigned").value(DEFAULT_ISASSIGNED.booleanValue()))
            .andExpect(jsonPath("$.markassignedby").value(DEFAULT_MARKASSIGNEDBY.toString()))
            .andExpect(jsonPath("$.feerequired").value(DEFAULT_FEEREQUIRED))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()));
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
                .departmentname(UPDATED_DEPARTMENTNAME)
                .servicename(UPDATED_SERVICENAME)
                .isrequired(UPDATED_ISREQUIRED)
                .markrequiredondate(UPDATED_MARKREQUIREDONDATE)
                .markrequiredby(UPDATED_MARKREQUIREDBY)
                .isassigned(UPDATED_ISASSIGNED)
                .markassignedby(UPDATED_MARKASSIGNEDBY)
                .feerequired(UPDATED_FEEREQUIRED)
                .status(UPDATED_STATUS)
                .comment(UPDATED_COMMENT);
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
        assertThat(testProjectservicedetail.getDepartmentname()).isEqualTo(UPDATED_DEPARTMENTNAME);
        assertThat(testProjectservicedetail.getServicename()).isEqualTo(UPDATED_SERVICENAME);
        assertThat(testProjectservicedetail.isIsrequired()).isEqualTo(UPDATED_ISREQUIRED);
        assertThat(testProjectservicedetail.getMarkrequiredondate()).isEqualTo(UPDATED_MARKREQUIREDONDATE);
        assertThat(testProjectservicedetail.getMarkrequiredby()).isEqualTo(UPDATED_MARKREQUIREDBY);
        assertThat(testProjectservicedetail.isIsassigned()).isEqualTo(UPDATED_ISASSIGNED);
        assertThat(testProjectservicedetail.getMarkassignedby()).isEqualTo(UPDATED_MARKASSIGNEDBY);
        assertThat(testProjectservicedetail.getFeerequired()).isEqualTo(UPDATED_FEEREQUIRED);
        assertThat(testProjectservicedetail.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProjectservicedetail.getComment()).isEqualTo(UPDATED_COMMENT);
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
