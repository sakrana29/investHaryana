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

    private static final String DEFAULT_USERLOGIN = "AAAAAAAAAA";
    private static final String UPDATED_USERLOGIN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SERVICEREQUIRED = false;
    private static final Boolean UPDATED_SERVICEREQUIRED = true;

    private static final String DEFAULT_SERVICESTATUS = "AAAAAAAAAA";
    private static final String UPDATED_SERVICESTATUS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_ASSIGNDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ASSIGNDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_SERVICEFEE = 1D;
    private static final Double UPDATED_SERVICEFEE = 2D;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICENAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICENAME = "BBBBBBBBBB";

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
                .userlogin(DEFAULT_USERLOGIN)
                .servicerequired(DEFAULT_SERVICEREQUIRED)
                .servicestatus(DEFAULT_SERVICESTATUS)
                .assigndate(DEFAULT_ASSIGNDATE)
                .servicefee(DEFAULT_SERVICEFEE)
                .remarks(DEFAULT_REMARKS)
                .projectname(DEFAULT_PROJECTNAME)
                .servicename(DEFAULT_SERVICENAME);
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
        assertThat(testProjectservicedetail.getUserlogin()).isEqualTo(DEFAULT_USERLOGIN);
        assertThat(testProjectservicedetail.isServicerequired()).isEqualTo(DEFAULT_SERVICEREQUIRED);
        assertThat(testProjectservicedetail.getServicestatus()).isEqualTo(DEFAULT_SERVICESTATUS);
        assertThat(testProjectservicedetail.getAssigndate()).isEqualTo(DEFAULT_ASSIGNDATE);
        assertThat(testProjectservicedetail.getServicefee()).isEqualTo(DEFAULT_SERVICEFEE);
        assertThat(testProjectservicedetail.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testProjectservicedetail.getProjectname()).isEqualTo(DEFAULT_PROJECTNAME);
        assertThat(testProjectservicedetail.getServicename()).isEqualTo(DEFAULT_SERVICENAME);
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
            .andExpect(jsonPath("$.[*].userlogin").value(hasItem(DEFAULT_USERLOGIN.toString())))
            .andExpect(jsonPath("$.[*].servicerequired").value(hasItem(DEFAULT_SERVICEREQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].servicestatus").value(hasItem(DEFAULT_SERVICESTATUS.toString())))
            .andExpect(jsonPath("$.[*].assigndate").value(hasItem(sameInstant(DEFAULT_ASSIGNDATE))))
            .andExpect(jsonPath("$.[*].servicefee").value(hasItem(DEFAULT_SERVICEFEE.doubleValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME.toString())))
            .andExpect(jsonPath("$.[*].servicename").value(hasItem(DEFAULT_SERVICENAME.toString())));
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
            .andExpect(jsonPath("$.userlogin").value(DEFAULT_USERLOGIN.toString()))
            .andExpect(jsonPath("$.servicerequired").value(DEFAULT_SERVICEREQUIRED.booleanValue()))
            .andExpect(jsonPath("$.servicestatus").value(DEFAULT_SERVICESTATUS.toString()))
            .andExpect(jsonPath("$.assigndate").value(sameInstant(DEFAULT_ASSIGNDATE)))
            .andExpect(jsonPath("$.servicefee").value(DEFAULT_SERVICEFEE.doubleValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME.toString()))
            .andExpect(jsonPath("$.servicename").value(DEFAULT_SERVICENAME.toString()));
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
                .userlogin(UPDATED_USERLOGIN)
                .servicerequired(UPDATED_SERVICEREQUIRED)
                .servicestatus(UPDATED_SERVICESTATUS)
                .assigndate(UPDATED_ASSIGNDATE)
                .servicefee(UPDATED_SERVICEFEE)
                .remarks(UPDATED_REMARKS)
                .projectname(UPDATED_PROJECTNAME)
                .servicename(UPDATED_SERVICENAME);
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
        assertThat(testProjectservicedetail.getUserlogin()).isEqualTo(UPDATED_USERLOGIN);
        assertThat(testProjectservicedetail.isServicerequired()).isEqualTo(UPDATED_SERVICEREQUIRED);
        assertThat(testProjectservicedetail.getServicestatus()).isEqualTo(UPDATED_SERVICESTATUS);
        assertThat(testProjectservicedetail.getAssigndate()).isEqualTo(UPDATED_ASSIGNDATE);
        assertThat(testProjectservicedetail.getServicefee()).isEqualTo(UPDATED_SERVICEFEE);
        assertThat(testProjectservicedetail.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testProjectservicedetail.getProjectname()).isEqualTo(UPDATED_PROJECTNAME);
        assertThat(testProjectservicedetail.getServicename()).isEqualTo(UPDATED_SERVICENAME);
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
