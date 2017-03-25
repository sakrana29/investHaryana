package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Departmentservice;
import com.hartron.investharyana.repository.DepartmentserviceRepository;
import com.hartron.investharyana.service.DepartmentserviceService;
import com.hartron.investharyana.service.dto.DepartmentserviceDTO;
import com.hartron.investharyana.service.mapper.DepartmentserviceMapper;
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
 * Test class for the DepartmentserviceResource REST controller.
 *
 * @see DepartmentserviceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class DepartmentserviceResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_DURATION = "AAAAAAAAAA";
    private static final String UPDATED_DURATION = "BBBBBBBBBB";

    private static final String DEFAULT_STAGE = "AAAAAAAAAA";
    private static final String UPDATED_STAGE = "BBBBBBBBBB";

    @Autowired
    private DepartmentserviceRepository departmentserviceRepository;

    @Autowired
    private DepartmentserviceMapper departmentserviceMapper;

    @Autowired
    private DepartmentserviceService departmentserviceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDepartmentserviceMockMvc;

    private Departmentservice departmentservice;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepartmentserviceResource departmentserviceResource = new DepartmentserviceResource(departmentserviceService);
        this.restDepartmentserviceMockMvc = MockMvcBuilders.standaloneSetup(departmentserviceResource)
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
    public static Departmentservice createEntity() {
        Departmentservice departmentservice = new Departmentservice()
                .duration(DEFAULT_DURATION)
                .stage(DEFAULT_STAGE);
        return departmentservice;
    }

    @Before
    public void initTest() {
        departmentserviceRepository.deleteAll();
        departmentservice = createEntity();
    }

    @Test
    public void createDepartmentservice() throws Exception {
        int databaseSizeBeforeCreate = departmentserviceRepository.findAll().size();

        // Create the Departmentservice
        DepartmentserviceDTO departmentserviceDTO = departmentserviceMapper.departmentserviceToDepartmentserviceDTO(departmentservice);

        restDepartmentserviceMockMvc.perform(post("/api/departmentservices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentserviceDTO)))
            .andExpect(status().isCreated());

        // Validate the Departmentservice in the database
        List<Departmentservice> departmentserviceList = departmentserviceRepository.findAll();
        assertThat(departmentserviceList).hasSize(databaseSizeBeforeCreate + 1);
        Departmentservice testDepartmentservice = departmentserviceList.get(departmentserviceList.size() - 1);
        assertThat(testDepartmentservice.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testDepartmentservice.getStage()).isEqualTo(DEFAULT_STAGE);
    }

    @Test
    public void createDepartmentserviceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departmentserviceRepository.findAll().size();

        // Create the Departmentservice with an existing ID
        Departmentservice existingDepartmentservice = new Departmentservice();
        existingDepartmentservice.setId(UUID.randomUUID());
        DepartmentserviceDTO existingDepartmentserviceDTO = departmentserviceMapper.departmentserviceToDepartmentserviceDTO(existingDepartmentservice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartmentserviceMockMvc.perform(post("/api/departmentservices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDepartmentserviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Departmentservice> departmentserviceList = departmentserviceRepository.findAll();
        assertThat(departmentserviceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDepartmentservices() throws Exception {
        // Initialize the database
        departmentserviceRepository.save(departmentservice);

        // Get all the departmentserviceList
        restDepartmentserviceMockMvc.perform(get("/api/departmentservices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departmentservice.getId().toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.toString())))
            .andExpect(jsonPath("$.[*].stage").value(hasItem(DEFAULT_STAGE.toString())));
    }

    @Test
    public void getDepartmentservice() throws Exception {
        // Initialize the database
        departmentserviceRepository.save(departmentservice);

        // Get the departmentservice
        restDepartmentserviceMockMvc.perform(get("/api/departmentservices/{id}", departmentservice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(departmentservice.getId().toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()))
            .andExpect(jsonPath("$.stage").value(DEFAULT_STAGE.toString()));
    }

    @Test
    public void getNonExistingDepartmentservice() throws Exception {
        // Get the departmentservice
        restDepartmentserviceMockMvc.perform(get("/api/departmentservices/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDepartmentservice() throws Exception {
        // Initialize the database
        departmentserviceRepository.save(departmentservice);
        int databaseSizeBeforeUpdate = departmentserviceRepository.findAll().size();

        // Update the departmentservice
        Departmentservice updatedDepartmentservice = departmentserviceRepository.findOne(departmentservice.getId());
        updatedDepartmentservice
                .duration(UPDATED_DURATION)
                .stage(UPDATED_STAGE);
        DepartmentserviceDTO departmentserviceDTO = departmentserviceMapper.departmentserviceToDepartmentserviceDTO(updatedDepartmentservice);

        restDepartmentserviceMockMvc.perform(put("/api/departmentservices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentserviceDTO)))
            .andExpect(status().isOk());

        // Validate the Departmentservice in the database
        List<Departmentservice> departmentserviceList = departmentserviceRepository.findAll();
        assertThat(departmentserviceList).hasSize(databaseSizeBeforeUpdate);
        Departmentservice testDepartmentservice = departmentserviceList.get(departmentserviceList.size() - 1);
        assertThat(testDepartmentservice.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testDepartmentservice.getStage()).isEqualTo(UPDATED_STAGE);
    }

    @Test
    public void updateNonExistingDepartmentservice() throws Exception {
        int databaseSizeBeforeUpdate = departmentserviceRepository.findAll().size();

        // Create the Departmentservice
        DepartmentserviceDTO departmentserviceDTO = departmentserviceMapper.departmentserviceToDepartmentserviceDTO(departmentservice);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDepartmentserviceMockMvc.perform(put("/api/departmentservices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departmentserviceDTO)))
            .andExpect(status().isCreated());

        // Validate the Departmentservice in the database
        List<Departmentservice> departmentserviceList = departmentserviceRepository.findAll();
        assertThat(departmentserviceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDepartmentservice() throws Exception {
        // Initialize the database
        departmentserviceRepository.save(departmentservice);
        int databaseSizeBeforeDelete = departmentserviceRepository.findAll().size();

        // Get the departmentservice
        restDepartmentserviceMockMvc.perform(delete("/api/departmentservices/{id}", departmentservice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Departmentservice> departmentserviceList = departmentserviceRepository.findAll();
        assertThat(departmentserviceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Departmentservice.class);
    }
}
