package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.DepartmentService;
import com.hartron.investharyana.repository.DepartmentServiceRepository;
import com.hartron.investharyana.service.DepartmentServiceService;
import com.hartron.investharyana.service.dto.DepartmentServiceDTO;
import com.hartron.investharyana.service.mapper.DepartmentServiceMapper;
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
 * Test class for the DepartmentServiceResource REST controller.
 *
 * @see DepartmentServiceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class DepartmentServiceResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final String DEFAULT_STAGE = "AAAAAAAAAA";
    private static final String UPDATED_STAGE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENTNAME = "BBBBBBBBBB";

    @Autowired
    private DepartmentServiceRepository departmentServiceRepository;

    @Autowired
    private DepartmentServiceMapper departmentServiceMapper;

    @Autowired
    private DepartmentServiceService departmentServiceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDepartmentServiceMockMvc;

    private DepartmentService departmentService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepartmentServiceResource departmentServiceResource = new DepartmentServiceResource(departmentServiceService);
        this.restDepartmentServiceMockMvc = MockMvcBuilders.standaloneSetup(departmentServiceResource)
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
    public static DepartmentService createEntity() {
        DepartmentService departmentService = new DepartmentService()
                .serviceName(DEFAULT_SERVICE_NAME)
                .serviceDescription(DEFAULT_SERVICE_DESCRIPTION)
                .duration(DEFAULT_DURATION)
                .stage(DEFAULT_STAGE)
                .departmentname(DEFAULT_DEPARTMENTNAME);
        return departmentService;
    }

    @Before
    public void initTest() {
        departmentServiceRepository.deleteAll();
        departmentService = createEntity();
    }

    @Test
    public void createDepartmentService() throws Exception {
        int databaseSizeBeforeCreate = departmentServiceRepository.findAll().size();

        // Create the DepartmentService
        DepartmentServiceDTO departmentServiceDTO = departmentServiceMapper.departmentServiceToDepartmentServiceDTO(departmentService);

        restDepartmentServiceMockMvc.perform(post("/api/department-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the DepartmentService in the database
        List<DepartmentService> departmentServiceList = departmentServiceRepository.findAll();
        assertThat(departmentServiceList).hasSize(databaseSizeBeforeCreate + 1);
        DepartmentService testDepartmentService = departmentServiceList.get(departmentServiceList.size() - 1);
        assertThat(testDepartmentService.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
        assertThat(testDepartmentService.getServiceDescription()).isEqualTo(DEFAULT_SERVICE_DESCRIPTION);
        assertThat(testDepartmentService.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testDepartmentService.getStage()).isEqualTo(DEFAULT_STAGE);
        assertThat(testDepartmentService.getDepartmentname()).isEqualTo(DEFAULT_DEPARTMENTNAME);
    }

    @Test
    public void createDepartmentServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departmentServiceRepository.findAll().size();

        // Create the DepartmentService with an existing ID
        DepartmentService existingDepartmentService = new DepartmentService();
        existingDepartmentService.setId(UUID.randomUUID());
        DepartmentServiceDTO existingDepartmentServiceDTO = departmentServiceMapper.departmentServiceToDepartmentServiceDTO(existingDepartmentService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartmentServiceMockMvc.perform(post("/api/department-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDepartmentServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DepartmentService> departmentServiceList = departmentServiceRepository.findAll();
        assertThat(departmentServiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkServiceNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = departmentServiceRepository.findAll().size();
        // set the field null
        departmentService.setServiceName(null);

        // Create the DepartmentService, which fails.
        DepartmentServiceDTO departmentServiceDTO = departmentServiceMapper.departmentServiceToDepartmentServiceDTO(departmentService);

        restDepartmentServiceMockMvc.perform(post("/api/department-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentServiceDTO)))
            .andExpect(status().isBadRequest());

        List<DepartmentService> departmentServiceList = departmentServiceRepository.findAll();
        assertThat(departmentServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = departmentServiceRepository.findAll().size();
        // set the field null
        departmentService.setServiceDescription(null);

        // Create the DepartmentService, which fails.
        DepartmentServiceDTO departmentServiceDTO = departmentServiceMapper.departmentServiceToDepartmentServiceDTO(departmentService);

        restDepartmentServiceMockMvc.perform(post("/api/department-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentServiceDTO)))
            .andExpect(status().isBadRequest());

        List<DepartmentService> departmentServiceList = departmentServiceRepository.findAll();
        assertThat(departmentServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDepartmentServices() throws Exception {
        // Initialize the database
        departmentServiceRepository.save(departmentService);

        // Get all the departmentServiceList
        restDepartmentServiceMockMvc.perform(get("/api/department-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departmentService.getId().toString())))
            .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME.toString())))
            .andExpect(jsonPath("$.[*].serviceDescription").value(hasItem(DEFAULT_SERVICE_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE.toString())))
            .andExpect(jsonPath("$.[*].departmentname").value(hasItem(DEFAULT_DEPARTMENTNAME.toString())));
    }

    @Test
    public void getDepartmentService() throws Exception {
        // Initialize the database
        departmentServiceRepository.save(departmentService);

        // Get the departmentService
        restDepartmentServiceMockMvc.perform(get("/api/department-services/{id}", departmentService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(departmentService.getId().toString()))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME.toString()))
            .andExpect(jsonPath("$.serviceDescription").value(DEFAULT_SERVICE_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE.toString()))
            .andExpect(jsonPath("$.departmentname").value(DEFAULT_DEPARTMENTNAME.toString()));
    }

    @Test
    public void getNonExistingDepartmentService() throws Exception {
        // Get the departmentService
        restDepartmentServiceMockMvc.perform(get("/api/department-services/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDepartmentService() throws Exception {
        // Initialize the database
        departmentServiceRepository.save(departmentService);
        int databaseSizeBeforeUpdate = departmentServiceRepository.findAll().size();

        // Update the departmentService
        DepartmentService updatedDepartmentService = departmentServiceRepository.findOne(departmentService.getId());
        updatedDepartmentService
                .serviceName(UPDATED_SERVICE_NAME)
                .serviceDescription(UPDATED_SERVICE_DESCRIPTION)
                .duration(UPDATED_DURATION)
                .stage(UPDATED_STAGE)
                .departmentname(UPDATED_DEPARTMENTNAME);
        DepartmentServiceDTO departmentServiceDTO = departmentServiceMapper.departmentServiceToDepartmentServiceDTO(updatedDepartmentService);

        restDepartmentServiceMockMvc.perform(put("/api/department-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentServiceDTO)))
            .andExpect(status().isOk());

        // Validate the DepartmentService in the database
        List<DepartmentService> departmentServiceList = departmentServiceRepository.findAll();
        assertThat(departmentServiceList).hasSize(databaseSizeBeforeUpdate);
        DepartmentService testDepartmentService = departmentServiceList.get(departmentServiceList.size() - 1);
        assertThat(testDepartmentService.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
        assertThat(testDepartmentService.getServiceDescription()).isEqualTo(UPDATED_SERVICE_DESCRIPTION);
        assertThat(testDepartmentService.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testDepartmentService.getStage()).isEqualTo(UPDATED_STAGE);
        assertThat(testDepartmentService.getDepartmentname()).isEqualTo(UPDATED_DEPARTMENTNAME);
    }

    @Test
    public void updateNonExistingDepartmentService() throws Exception {
        int databaseSizeBeforeUpdate = departmentServiceRepository.findAll().size();

        // Create the DepartmentService
        DepartmentServiceDTO departmentServiceDTO = departmentServiceMapper.departmentServiceToDepartmentServiceDTO(departmentService);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDepartmentServiceMockMvc.perform(put("/api/department-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the DepartmentService in the database
        List<DepartmentService> departmentServiceList = departmentServiceRepository.findAll();
        assertThat(departmentServiceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDepartmentService() throws Exception {
        // Initialize the database
        departmentServiceRepository.save(departmentService);
        int databaseSizeBeforeDelete = departmentServiceRepository.findAll().size();

        // Get the departmentService
        restDepartmentServiceMockMvc.perform(delete("/api/department-services/{id}", departmentService.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DepartmentService> departmentServiceList = departmentServiceRepository.findAll();
        assertThat(departmentServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartmentService.class);
    }
}
