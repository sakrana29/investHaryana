package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Department;
import com.hartron.investharyana.repository.DepartmentRepository;
import com.hartron.investharyana.service.DepartmentService;
import com.hartron.investharyana.service.dto.DepartmentDTO;
import com.hartron.investharyana.service.mapper.DepartmentMapper;
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
 * Test class for the DepartmentResource REST controller.
 *
 * @see DepartmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class DepartmentResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_DEPARTMENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_HOD = "AAAAAAAAAA";
    private static final String UPDATED_HOD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_HODMOBILE = "AAAAAAAAAA";
    private static final String UPDATED_HODMOBILE = "BBBBBBBBBB";

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDepartmentMockMvc;

    private Department department;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepartmentResource departmentResource = new DepartmentResource(departmentService);
        this.restDepartmentMockMvc = MockMvcBuilders.standaloneSetup(departmentResource)
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
    public static Department createEntity() {
        Department department = new Department()
                .departmentname(DEFAULT_DEPARTMENTNAME)
                .description(DEFAULT_DESCRIPTION)
                .hod(DEFAULT_HOD)
                .email(DEFAULT_EMAIL)
                .hodmobile(DEFAULT_HODMOBILE);
        return department;
    }

    @Before
    public void initTest() {
        departmentRepository.deleteAll();
        department = createEntity();
    }

    @Test
    public void createDepartment() throws Exception {
        int databaseSizeBeforeCreate = departmentRepository.findAll().size();

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.departmentToDepartmentDTO(department);

        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeCreate + 1);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getDepartmentname()).isEqualTo(DEFAULT_DEPARTMENTNAME);
        assertThat(testDepartment.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDepartment.getHod()).isEqualTo(DEFAULT_HOD);
        assertThat(testDepartment.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDepartment.getHodmobile()).isEqualTo(DEFAULT_HODMOBILE);
    }

    @Test
    public void createDepartmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departmentRepository.findAll().size();

        // Create the Department with an existing ID
        Department existingDepartment = new Department();
        existingDepartment.setId(UUID.randomUUID());
        DepartmentDTO existingDepartmentDTO = departmentMapper.departmentToDepartmentDTO(existingDepartment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDepartmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkDepartmentnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = departmentRepository.findAll().size();
        // set the field null
        department.setDepartmentname(null);

        // Create the Department, which fails.
        DepartmentDTO departmentDTO = departmentMapper.departmentToDepartmentDTO(department);

        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isBadRequest());

        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = departmentRepository.findAll().size();
        // set the field null
        department.setDescription(null);

        // Create the Department, which fails.
        DepartmentDTO departmentDTO = departmentMapper.departmentToDepartmentDTO(department);

        restDepartmentMockMvc.perform(post("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isBadRequest());

        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDepartments() throws Exception {
        // Initialize the database
        departmentRepository.save(department);

        // Get all the departmentList
        restDepartmentMockMvc.perform(get("/api/departments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(department.getId().toString())))
            .andExpect(jsonPath("$.[*].departmentname").value(hasItem(DEFAULT_DEPARTMENTNAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].hod").value(hasItem(DEFAULT_HOD.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].hodmobile").value(hasItem(DEFAULT_HODMOBILE.toString())));
    }

    @Test
    public void getDepartment() throws Exception {
        // Initialize the database
        departmentRepository.save(department);

        // Get the department
        restDepartmentMockMvc.perform(get("/api/departments/{id}", department.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(department.getId().toString()))
            .andExpect(jsonPath("$.departmentname").value(DEFAULT_DEPARTMENTNAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.hod").value(DEFAULT_HOD.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.hodmobile").value(DEFAULT_HODMOBILE.toString()));
    }

    @Test
    public void getNonExistingDepartment() throws Exception {
        // Get the department
        restDepartmentMockMvc.perform(get("/api/departments/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDepartment() throws Exception {
        // Initialize the database
        departmentRepository.save(department);
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Update the department
        Department updatedDepartment = departmentRepository.findOne(department.getId());
        updatedDepartment
                .departmentname(UPDATED_DEPARTMENTNAME)
                .description(UPDATED_DESCRIPTION)
                .hod(UPDATED_HOD)
                .email(UPDATED_EMAIL)
                .hodmobile(UPDATED_HODMOBILE);
        DepartmentDTO departmentDTO = departmentMapper.departmentToDepartmentDTO(updatedDepartment);

        restDepartmentMockMvc.perform(put("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isOk());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getDepartmentname()).isEqualTo(UPDATED_DEPARTMENTNAME);
        assertThat(testDepartment.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDepartment.getHod()).isEqualTo(UPDATED_HOD);
        assertThat(testDepartment.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDepartment.getHodmobile()).isEqualTo(UPDATED_HODMOBILE);
    }

    @Test
    public void updateNonExistingDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.departmentToDepartmentDTO(department);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDepartmentMockMvc.perform(put("/api/departments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDepartment() throws Exception {
        // Initialize the database
        departmentRepository.save(department);
        int databaseSizeBeforeDelete = departmentRepository.findAll().size();

        // Get the department
        restDepartmentMockMvc.perform(delete("/api/departments/{id}", department.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Department.class);
    }
}
