package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.ProjectServiceLog;
import com.hartron.investharyana.repository.ProjectServiceLogRepository;
import com.hartron.investharyana.service.ProjectServiceLogService;
import com.hartron.investharyana.service.dto.ProjectServiceLogDTO;
import com.hartron.investharyana.service.mapper.ProjectServiceLogMapper;
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
 * Test class for the ProjectServiceLogResource REST controller.
 *
 * @see ProjectServiceLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectServiceLogResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final UUID DEFAULT_SERVICEID = UUID.randomUUID();
    private static final UUID UPDATED_SERVICEID = UUID.randomUUID();

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_COMMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_COMMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_COMMENT_BY_USER_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT_BY_USER_LOGIN = "BBBBBBBBBB";

    @Autowired
    private ProjectServiceLogRepository projectServiceLogRepository;

    @Autowired
    private ProjectServiceLogMapper projectServiceLogMapper;

    @Autowired
    private ProjectServiceLogService projectServiceLogService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectServiceLogMockMvc;

    private ProjectServiceLog projectServiceLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectServiceLogResource projectServiceLogResource = new ProjectServiceLogResource(projectServiceLogService);
        this.restProjectServiceLogMockMvc = MockMvcBuilders.standaloneSetup(projectServiceLogResource)
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
    public static ProjectServiceLog createEntity() {
        ProjectServiceLog projectServiceLog = new ProjectServiceLog()
                .projectid(DEFAULT_PROJECTID)
                .serviceid(DEFAULT_SERVICEID)
                .comments(DEFAULT_COMMENTS)
                .commentDate(DEFAULT_COMMENT_DATE)
                .commentByUserLogin(DEFAULT_COMMENT_BY_USER_LOGIN);
        return projectServiceLog;
    }

    @Before
    public void initTest() {
        projectServiceLogRepository.deleteAll();
        projectServiceLog = createEntity();
    }

    @Test
    public void createProjectServiceLog() throws Exception {
        int databaseSizeBeforeCreate = projectServiceLogRepository.findAll().size();

        // Create the ProjectServiceLog
        ProjectServiceLogDTO projectServiceLogDTO = projectServiceLogMapper.projectServiceLogToProjectServiceLogDTO(projectServiceLog);

        restProjectServiceLogMockMvc.perform(post("/api/project-service-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServiceLogDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectServiceLog in the database
        List<ProjectServiceLog> projectServiceLogList = projectServiceLogRepository.findAll();
        assertThat(projectServiceLogList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectServiceLog testProjectServiceLog = projectServiceLogList.get(projectServiceLogList.size() - 1);
        assertThat(testProjectServiceLog.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectServiceLog.getServiceid()).isEqualTo(DEFAULT_SERVICEID);
        assertThat(testProjectServiceLog.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testProjectServiceLog.getCommentDate()).isEqualTo(DEFAULT_COMMENT_DATE);
        assertThat(testProjectServiceLog.getCommentByUserLogin()).isEqualTo(DEFAULT_COMMENT_BY_USER_LOGIN);
    }

    @Test
    public void createProjectServiceLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectServiceLogRepository.findAll().size();

        // Create the ProjectServiceLog with an existing ID
        ProjectServiceLog existingProjectServiceLog = new ProjectServiceLog();
        existingProjectServiceLog.setId(UUID.randomUUID());
        ProjectServiceLogDTO existingProjectServiceLogDTO = projectServiceLogMapper.projectServiceLogToProjectServiceLogDTO(existingProjectServiceLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectServiceLogMockMvc.perform(post("/api/project-service-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectServiceLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ProjectServiceLog> projectServiceLogList = projectServiceLogRepository.findAll();
        assertThat(projectServiceLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectServiceLogs() throws Exception {
        // Initialize the database
        projectServiceLogRepository.save(projectServiceLog);

        // Get all the projectServiceLogList
        restProjectServiceLogMockMvc.perform(get("/api/project-service-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectServiceLog.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].serviceid").value(hasItem(DEFAULT_SERVICEID.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].commentDate").value(hasItem(sameInstant(DEFAULT_COMMENT_DATE))))
            .andExpect(jsonPath("$.[*].commentByUserLogin").value(hasItem(DEFAULT_COMMENT_BY_USER_LOGIN.toString())));
    }

    @Test
    public void getProjectServiceLog() throws Exception {
        // Initialize the database
        projectServiceLogRepository.save(projectServiceLog);

        // Get the projectServiceLog
        restProjectServiceLogMockMvc.perform(get("/api/project-service-logs/{id}", projectServiceLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectServiceLog.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.serviceid").value(DEFAULT_SERVICEID.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.commentDate").value(sameInstant(DEFAULT_COMMENT_DATE)))
            .andExpect(jsonPath("$.commentByUserLogin").value(DEFAULT_COMMENT_BY_USER_LOGIN.toString()));
    }

    @Test
    public void getNonExistingProjectServiceLog() throws Exception {
        // Get the projectServiceLog
        restProjectServiceLogMockMvc.perform(get("/api/project-service-logs/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectServiceLog() throws Exception {
        // Initialize the database
        projectServiceLogRepository.save(projectServiceLog);
        int databaseSizeBeforeUpdate = projectServiceLogRepository.findAll().size();

        // Update the projectServiceLog
        ProjectServiceLog updatedProjectServiceLog = projectServiceLogRepository.findOne(projectServiceLog.getId());
        updatedProjectServiceLog
                .projectid(UPDATED_PROJECTID)
                .serviceid(UPDATED_SERVICEID)
                .comments(UPDATED_COMMENTS)
                .commentDate(UPDATED_COMMENT_DATE)
                .commentByUserLogin(UPDATED_COMMENT_BY_USER_LOGIN);
        ProjectServiceLogDTO projectServiceLogDTO = projectServiceLogMapper.projectServiceLogToProjectServiceLogDTO(updatedProjectServiceLog);

        restProjectServiceLogMockMvc.perform(put("/api/project-service-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServiceLogDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectServiceLog in the database
        List<ProjectServiceLog> projectServiceLogList = projectServiceLogRepository.findAll();
        assertThat(projectServiceLogList).hasSize(databaseSizeBeforeUpdate);
        ProjectServiceLog testProjectServiceLog = projectServiceLogList.get(projectServiceLogList.size() - 1);
        assertThat(testProjectServiceLog.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectServiceLog.getServiceid()).isEqualTo(UPDATED_SERVICEID);
        assertThat(testProjectServiceLog.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testProjectServiceLog.getCommentDate()).isEqualTo(UPDATED_COMMENT_DATE);
        assertThat(testProjectServiceLog.getCommentByUserLogin()).isEqualTo(UPDATED_COMMENT_BY_USER_LOGIN);
    }

    @Test
    public void updateNonExistingProjectServiceLog() throws Exception {
        int databaseSizeBeforeUpdate = projectServiceLogRepository.findAll().size();

        // Create the ProjectServiceLog
        ProjectServiceLogDTO projectServiceLogDTO = projectServiceLogMapper.projectServiceLogToProjectServiceLogDTO(projectServiceLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectServiceLogMockMvc.perform(put("/api/project-service-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServiceLogDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectServiceLog in the database
        List<ProjectServiceLog> projectServiceLogList = projectServiceLogRepository.findAll();
        assertThat(projectServiceLogList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectServiceLog() throws Exception {
        // Initialize the database
        projectServiceLogRepository.save(projectServiceLog);
        int databaseSizeBeforeDelete = projectServiceLogRepository.findAll().size();

        // Get the projectServiceLog
        restProjectServiceLogMockMvc.perform(delete("/api/project-service-logs/{id}", projectServiceLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProjectServiceLog> projectServiceLogList = projectServiceLogRepository.findAll();
        assertThat(projectServiceLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectServiceLog.class);
    }
}
