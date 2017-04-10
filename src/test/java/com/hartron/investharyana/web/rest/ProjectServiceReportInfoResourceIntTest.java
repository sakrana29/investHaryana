package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.ProjectServiceReportInfo;
import com.hartron.investharyana.repository.ProjectServiceReportInfoRepository;
import com.hartron.investharyana.service.ProjectServiceReportInfoService;
import com.hartron.investharyana.service.dto.ProjectServiceReportInfoDTO;
import com.hartron.investharyana.service.mapper.ProjectServiceReportInfoMapper;
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
 * Test class for the ProjectServiceReportInfoResource REST controller.
 *
 * @see ProjectServiceReportInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProjectServiceReportInfoResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_PROJECTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICENAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICENAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_ASSIGN_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ASSIGN_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_REQUIRE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REQUIRE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STAGE = "AAAAAAAAAA";
    private static final String UPDATED_STAGE = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAF_PIN = "AAAAAAAAAA";
    private static final String UPDATED_CAF_PIN = "BBBBBBBBBB";

    private static final String DEFAULT_FINAL_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_FINAL_ACTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FINAL_ACTION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FINAL_ACTION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_PROJECT_INVESTMENT = 1D;
    private static final Double UPDATED_PROJECT_INVESTMENT = 2D;

    private static final String DEFAULT_PROJECT_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_EMPLOYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PROPOSEDPROJECTAREA = "AAAAAAAAAA";
    private static final String UPDATED_PROPOSEDPROJECTAREA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONFIRMITYLANDUSE = false;
    private static final Boolean UPDATED_CONFIRMITYLANDUSE = true;

    private static final String DEFAULT_LANDZONEUSETYPE = "AAAAAAAAAA";
    private static final String UPDATED_LANDZONEUSETYPE = "BBBBBBBBBB";

    @Autowired
    private ProjectServiceReportInfoRepository projectServiceReportInfoRepository;

    @Autowired
    private ProjectServiceReportInfoMapper projectServiceReportInfoMapper;

    @Autowired
    private ProjectServiceReportInfoService projectServiceReportInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProjectServiceReportInfoMockMvc;

    private ProjectServiceReportInfo projectServiceReportInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectServiceReportInfoResource projectServiceReportInfoResource = new ProjectServiceReportInfoResource(projectServiceReportInfoService);
        this.restProjectServiceReportInfoMockMvc = MockMvcBuilders.standaloneSetup(projectServiceReportInfoResource)
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
    public static ProjectServiceReportInfo createEntity() {
        ProjectServiceReportInfo projectServiceReportInfo = new ProjectServiceReportInfo()
                .projectid(DEFAULT_PROJECTID)
                .projecttype(DEFAULT_PROJECTTYPE)
                .departmentname(DEFAULT_DEPARTMENTNAME)
                .servicename(DEFAULT_SERVICENAME)
                .assignDate(DEFAULT_ASSIGN_DATE)
                .requireDate(DEFAULT_REQUIRE_DATE)
                .status(DEFAULT_STATUS)
                .stage(DEFAULT_STAGE)
                .investorName(DEFAULT_INVESTOR_NAME)
                .cafPin(DEFAULT_CAF_PIN)
                .finalAction(DEFAULT_FINAL_ACTION)
                .finalActionDate(DEFAULT_FINAL_ACTION_DATE)
                .projectInvestment(DEFAULT_PROJECT_INVESTMENT)
                .projectEmployment(DEFAULT_PROJECT_EMPLOYMENT)
                .proposedprojectarea(DEFAULT_PROPOSEDPROJECTAREA)
                .confirmitylanduse(DEFAULT_CONFIRMITYLANDUSE)
                .landzoneusetype(DEFAULT_LANDZONEUSETYPE);
        return projectServiceReportInfo;
    }

    @Before
    public void initTest() {
        projectServiceReportInfoRepository.deleteAll();
        projectServiceReportInfo = createEntity();
    }

    @Test
    public void createProjectServiceReportInfo() throws Exception {
        int databaseSizeBeforeCreate = projectServiceReportInfoRepository.findAll().size();

        // Create the ProjectServiceReportInfo
        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = projectServiceReportInfoMapper.projectServiceReportInfoToProjectServiceReportInfoDTO(projectServiceReportInfo);

        restProjectServiceReportInfoMockMvc.perform(post("/api/project-service-report-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServiceReportInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectServiceReportInfo in the database
        List<ProjectServiceReportInfo> projectServiceReportInfoList = projectServiceReportInfoRepository.findAll();
        assertThat(projectServiceReportInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectServiceReportInfo testProjectServiceReportInfo = projectServiceReportInfoList.get(projectServiceReportInfoList.size() - 1);
        assertThat(testProjectServiceReportInfo.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProjectServiceReportInfo.getProjecttype()).isEqualTo(DEFAULT_PROJECTTYPE);
        assertThat(testProjectServiceReportInfo.getDepartmentname()).isEqualTo(DEFAULT_DEPARTMENTNAME);
        assertThat(testProjectServiceReportInfo.getServicename()).isEqualTo(DEFAULT_SERVICENAME);
        assertThat(testProjectServiceReportInfo.getAssignDate()).isEqualTo(DEFAULT_ASSIGN_DATE);
        assertThat(testProjectServiceReportInfo.getRequireDate()).isEqualTo(DEFAULT_REQUIRE_DATE);
        assertThat(testProjectServiceReportInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProjectServiceReportInfo.getStage()).isEqualTo(DEFAULT_STAGE);
        assertThat(testProjectServiceReportInfo.getInvestorName()).isEqualTo(DEFAULT_INVESTOR_NAME);
        assertThat(testProjectServiceReportInfo.getCafPin()).isEqualTo(DEFAULT_CAF_PIN);
        assertThat(testProjectServiceReportInfo.getFinalAction()).isEqualTo(DEFAULT_FINAL_ACTION);
        assertThat(testProjectServiceReportInfo.getFinalActionDate()).isEqualTo(DEFAULT_FINAL_ACTION_DATE);
        assertThat(testProjectServiceReportInfo.getProjectInvestment()).isEqualTo(DEFAULT_PROJECT_INVESTMENT);
        assertThat(testProjectServiceReportInfo.getProjectEmployment()).isEqualTo(DEFAULT_PROJECT_EMPLOYMENT);
        assertThat(testProjectServiceReportInfo.getProposedprojectarea()).isEqualTo(DEFAULT_PROPOSEDPROJECTAREA);
        assertThat(testProjectServiceReportInfo.isConfirmitylanduse()).isEqualTo(DEFAULT_CONFIRMITYLANDUSE);
        assertThat(testProjectServiceReportInfo.getLandzoneusetype()).isEqualTo(DEFAULT_LANDZONEUSETYPE);
    }

    @Test
    public void createProjectServiceReportInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectServiceReportInfoRepository.findAll().size();

        // Create the ProjectServiceReportInfo with an existing ID
        ProjectServiceReportInfo existingProjectServiceReportInfo = new ProjectServiceReportInfo();
        existingProjectServiceReportInfo.setId(UUID.randomUUID());
        ProjectServiceReportInfoDTO existingProjectServiceReportInfoDTO = projectServiceReportInfoMapper.projectServiceReportInfoToProjectServiceReportInfoDTO(existingProjectServiceReportInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectServiceReportInfoMockMvc.perform(post("/api/project-service-report-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProjectServiceReportInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ProjectServiceReportInfo> projectServiceReportInfoList = projectServiceReportInfoRepository.findAll();
        assertThat(projectServiceReportInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProjectServiceReportInfos() throws Exception {
        // Initialize the database
        projectServiceReportInfoRepository.save(projectServiceReportInfo);

        // Get all the projectServiceReportInfoList
        restProjectServiceReportInfoMockMvc.perform(get("/api/project-service-report-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectServiceReportInfo.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].projecttype").value(hasItem(DEFAULT_PROJECTTYPE.toString())))
            .andExpect(jsonPath("$.[*].departmentname").value(hasItem(DEFAULT_DEPARTMENTNAME.toString())))
            .andExpect(jsonPath("$.[*].servicename").value(hasItem(DEFAULT_SERVICENAME.toString())))
            .andExpect(jsonPath("$.[*].assignDate").value(hasItem(sameInstant(DEFAULT_ASSIGN_DATE))))
            .andExpect(jsonPath("$.[*].requireDate").value(hasItem(sameInstant(DEFAULT_REQUIRE_DATE))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE.toString())))
            .andExpect(jsonPath("$.[*].investorName").value(hasItem(DEFAULT_INVESTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].cafPin").value(hasItem(DEFAULT_CAF_PIN.toString())))
            .andExpect(jsonPath("$.[*].finalAction").value(hasItem(DEFAULT_FINAL_ACTION.toString())))
            .andExpect(jsonPath("$.[*].finalActionDate").value(hasItem(sameInstant(DEFAULT_FINAL_ACTION_DATE))))
            .andExpect(jsonPath("$.[*].projectInvestment").value(hasItem(DEFAULT_PROJECT_INVESTMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].projectEmployment").value(hasItem(DEFAULT_PROJECT_EMPLOYMENT.toString())))
            .andExpect(jsonPath("$.[*].proposedprojectarea").value(hasItem(DEFAULT_PROPOSEDPROJECTAREA.toString())))
            .andExpect(jsonPath("$.[*].confirmitylanduse").value(hasItem(DEFAULT_CONFIRMITYLANDUSE.booleanValue())))
            .andExpect(jsonPath("$.[*].landzoneusetype").value(hasItem(DEFAULT_LANDZONEUSETYPE.toString())));
    }

    @Test
    public void getProjectServiceReportInfo() throws Exception {
        // Initialize the database
        projectServiceReportInfoRepository.save(projectServiceReportInfo);

        // Get the projectServiceReportInfo
        restProjectServiceReportInfoMockMvc.perform(get("/api/project-service-report-infos/{id}", projectServiceReportInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectServiceReportInfo.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.projecttype").value(DEFAULT_PROJECTTYPE.toString()))
            .andExpect(jsonPath("$.departmentname").value(DEFAULT_DEPARTMENTNAME.toString()))
            .andExpect(jsonPath("$.servicename").value(DEFAULT_SERVICENAME.toString()))
            .andExpect(jsonPath("$.assignDate").value(sameInstant(DEFAULT_ASSIGN_DATE)))
            .andExpect(jsonPath("$.requireDate").value(sameInstant(DEFAULT_REQUIRE_DATE)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE.toString()))
            .andExpect(jsonPath("$.investorName").value(DEFAULT_INVESTOR_NAME.toString()))
            .andExpect(jsonPath("$.cafPin").value(DEFAULT_CAF_PIN.toString()))
            .andExpect(jsonPath("$.finalAction").value(DEFAULT_FINAL_ACTION.toString()))
            .andExpect(jsonPath("$.finalActionDate").value(sameInstant(DEFAULT_FINAL_ACTION_DATE)))
            .andExpect(jsonPath("$.projectInvestment").value(DEFAULT_PROJECT_INVESTMENT.doubleValue()))
            .andExpect(jsonPath("$.projectEmployment").value(DEFAULT_PROJECT_EMPLOYMENT.toString()))
            .andExpect(jsonPath("$.proposedprojectarea").value(DEFAULT_PROPOSEDPROJECTAREA.toString()))
            .andExpect(jsonPath("$.confirmitylanduse").value(DEFAULT_CONFIRMITYLANDUSE.booleanValue()))
            .andExpect(jsonPath("$.landzoneusetype").value(DEFAULT_LANDZONEUSETYPE.toString()));
    }

    @Test
    public void getNonExistingProjectServiceReportInfo() throws Exception {
        // Get the projectServiceReportInfo
        restProjectServiceReportInfoMockMvc.perform(get("/api/project-service-report-infos/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProjectServiceReportInfo() throws Exception {
        // Initialize the database
        projectServiceReportInfoRepository.save(projectServiceReportInfo);
        int databaseSizeBeforeUpdate = projectServiceReportInfoRepository.findAll().size();

        // Update the projectServiceReportInfo
        ProjectServiceReportInfo updatedProjectServiceReportInfo = projectServiceReportInfoRepository.findOne(projectServiceReportInfo.getId());
        updatedProjectServiceReportInfo
                .projectid(UPDATED_PROJECTID)
                .projecttype(UPDATED_PROJECTTYPE)
                .departmentname(UPDATED_DEPARTMENTNAME)
                .servicename(UPDATED_SERVICENAME)
                .assignDate(UPDATED_ASSIGN_DATE)
                .requireDate(UPDATED_REQUIRE_DATE)
                .status(UPDATED_STATUS)
                .stage(UPDATED_STAGE)
                .investorName(UPDATED_INVESTOR_NAME)
                .cafPin(UPDATED_CAF_PIN)
                .finalAction(UPDATED_FINAL_ACTION)
                .finalActionDate(UPDATED_FINAL_ACTION_DATE)
                .projectInvestment(UPDATED_PROJECT_INVESTMENT)
                .projectEmployment(UPDATED_PROJECT_EMPLOYMENT)
                .proposedprojectarea(UPDATED_PROPOSEDPROJECTAREA)
                .confirmitylanduse(UPDATED_CONFIRMITYLANDUSE)
                .landzoneusetype(UPDATED_LANDZONEUSETYPE);
        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = projectServiceReportInfoMapper.projectServiceReportInfoToProjectServiceReportInfoDTO(updatedProjectServiceReportInfo);

        restProjectServiceReportInfoMockMvc.perform(put("/api/project-service-report-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServiceReportInfoDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectServiceReportInfo in the database
        List<ProjectServiceReportInfo> projectServiceReportInfoList = projectServiceReportInfoRepository.findAll();
        assertThat(projectServiceReportInfoList).hasSize(databaseSizeBeforeUpdate);
        ProjectServiceReportInfo testProjectServiceReportInfo = projectServiceReportInfoList.get(projectServiceReportInfoList.size() - 1);
        assertThat(testProjectServiceReportInfo.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProjectServiceReportInfo.getProjecttype()).isEqualTo(UPDATED_PROJECTTYPE);
        assertThat(testProjectServiceReportInfo.getDepartmentname()).isEqualTo(UPDATED_DEPARTMENTNAME);
        assertThat(testProjectServiceReportInfo.getServicename()).isEqualTo(UPDATED_SERVICENAME);
        assertThat(testProjectServiceReportInfo.getAssignDate()).isEqualTo(UPDATED_ASSIGN_DATE);
        assertThat(testProjectServiceReportInfo.getRequireDate()).isEqualTo(UPDATED_REQUIRE_DATE);
        assertThat(testProjectServiceReportInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProjectServiceReportInfo.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testProjectServiceReportInfo.getInvestorName()).isEqualTo(UPDATED_INVESTOR_NAME);
        assertThat(testProjectServiceReportInfo.getCafPin()).isEqualTo(UPDATED_CAF_PIN);
        assertThat(testProjectServiceReportInfo.getFinalAction()).isEqualTo(UPDATED_FINAL_ACTION);
        assertThat(testProjectServiceReportInfo.getFinalActionDate()).isEqualTo(UPDATED_FINAL_ACTION_DATE);
        assertThat(testProjectServiceReportInfo.getProjectInvestment()).isEqualTo(UPDATED_PROJECT_INVESTMENT);
        assertThat(testProjectServiceReportInfo.getProjectEmployment()).isEqualTo(UPDATED_PROJECT_EMPLOYMENT);
        assertThat(testProjectServiceReportInfo.getProposedprojectarea()).isEqualTo(UPDATED_PROPOSEDPROJECTAREA);
        assertThat(testProjectServiceReportInfo.isConfirmitylanduse()).isEqualTo(UPDATED_CONFIRMITYLANDUSE);
        assertThat(testProjectServiceReportInfo.getLandzoneusetype()).isEqualTo(UPDATED_LANDZONEUSETYPE);
    }

    @Test
    public void updateNonExistingProjectServiceReportInfo() throws Exception {
        int databaseSizeBeforeUpdate = projectServiceReportInfoRepository.findAll().size();

        // Create the ProjectServiceReportInfo
        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = projectServiceReportInfoMapper.projectServiceReportInfoToProjectServiceReportInfoDTO(projectServiceReportInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectServiceReportInfoMockMvc.perform(put("/api/project-service-report-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectServiceReportInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectServiceReportInfo in the database
        List<ProjectServiceReportInfo> projectServiceReportInfoList = projectServiceReportInfoRepository.findAll();
        assertThat(projectServiceReportInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProjectServiceReportInfo() throws Exception {
        // Initialize the database
        projectServiceReportInfoRepository.save(projectServiceReportInfo);
        int databaseSizeBeforeDelete = projectServiceReportInfoRepository.findAll().size();

        // Get the projectServiceReportInfo
        restProjectServiceReportInfoMockMvc.perform(delete("/api/project-service-report-infos/{id}", projectServiceReportInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProjectServiceReportInfo> projectServiceReportInfoList = projectServiceReportInfoRepository.findAll();
        assertThat(projectServiceReportInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectServiceReportInfo.class);
    }
}
