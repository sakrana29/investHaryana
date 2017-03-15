package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Approvalforms;
import com.hartron.investharyana.repository.ApprovalformsRepository;
import com.hartron.investharyana.service.ApprovalformsService;
import com.hartron.investharyana.service.dto.ApprovalformsDTO;
import com.hartron.investharyana.service.mapper.ApprovalformsMapper;
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
 * Test class for the ApprovalformsResource REST controller.
 *
 * @see ApprovalformsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ApprovalformsResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_EXISTINGAPPROVALFORMS = "AAAAAAAAAA";
    private static final String UPDATED_EXISTINGAPPROVALFORMS = "BBBBBBBBBB";

    @Autowired
    private ApprovalformsRepository approvalformsRepository;

    @Autowired
    private ApprovalformsMapper approvalformsMapper;

    @Autowired
    private ApprovalformsService approvalformsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restApprovalformsMockMvc;

    private Approvalforms approvalforms;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ApprovalformsResource approvalformsResource = new ApprovalformsResource(approvalformsService);
        this.restApprovalformsMockMvc = MockMvcBuilders.standaloneSetup(approvalformsResource)
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
    public static Approvalforms createEntity() {
        Approvalforms approvalforms = new Approvalforms()
                .existingapprovalforms(DEFAULT_EXISTINGAPPROVALFORMS);
        return approvalforms;
    }

    @Before
    public void initTest() {
        approvalformsRepository.deleteAll();
        approvalforms = createEntity();
    }

    @Test
    public void createApprovalforms() throws Exception {
        int databaseSizeBeforeCreate = approvalformsRepository.findAll().size();

        // Create the Approvalforms
        ApprovalformsDTO approvalformsDTO = approvalformsMapper.approvalformsToApprovalformsDTO(approvalforms);

        restApprovalformsMockMvc.perform(post("/api/approvalforms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalformsDTO)))
            .andExpect(status().isCreated());

        // Validate the Approvalforms in the database
        List<Approvalforms> approvalformsList = approvalformsRepository.findAll();
        assertThat(approvalformsList).hasSize(databaseSizeBeforeCreate + 1);
        Approvalforms testApprovalforms = approvalformsList.get(approvalformsList.size() - 1);
        assertThat(testApprovalforms.getExistingapprovalforms()).isEqualTo(DEFAULT_EXISTINGAPPROVALFORMS);
    }

    @Test
    public void createApprovalformsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = approvalformsRepository.findAll().size();

        // Create the Approvalforms with an existing ID
        Approvalforms existingApprovalforms = new Approvalforms();
        existingApprovalforms.setId(UUID.randomUUID());
        ApprovalformsDTO existingApprovalformsDTO = approvalformsMapper.approvalformsToApprovalformsDTO(existingApprovalforms);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApprovalformsMockMvc.perform(post("/api/approvalforms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingApprovalformsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Approvalforms> approvalformsList = approvalformsRepository.findAll();
        assertThat(approvalformsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllApprovalforms() throws Exception {
        // Initialize the database
        approvalformsRepository.save(approvalforms);

        // Get all the approvalformsList
        restApprovalformsMockMvc.perform(get("/api/approvalforms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(approvalforms.getId().toString())))
            .andExpect(jsonPath("$.[*].existingapprovalforms").value(hasItem(DEFAULT_EXISTINGAPPROVALFORMS.toString())));
    }

    @Test
    public void getApprovalforms() throws Exception {
        // Initialize the database
        approvalformsRepository.save(approvalforms);

        // Get the approvalforms
        restApprovalformsMockMvc.perform(get("/api/approvalforms/{id}", approvalforms.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(approvalforms.getId().toString()))
            .andExpect(jsonPath("$.existingapprovalforms").value(DEFAULT_EXISTINGAPPROVALFORMS.toString()));
    }

    @Test
    public void getNonExistingApprovalforms() throws Exception {
        // Get the approvalforms
        restApprovalformsMockMvc.perform(get("/api/approvalforms/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateApprovalforms() throws Exception {
        // Initialize the database
        approvalformsRepository.save(approvalforms);
        int databaseSizeBeforeUpdate = approvalformsRepository.findAll().size();

        // Update the approvalforms
        Approvalforms updatedApprovalforms = approvalformsRepository.findOne(approvalforms.getId());
        updatedApprovalforms
                .existingapprovalforms(UPDATED_EXISTINGAPPROVALFORMS);
        ApprovalformsDTO approvalformsDTO = approvalformsMapper.approvalformsToApprovalformsDTO(updatedApprovalforms);

        restApprovalformsMockMvc.perform(put("/api/approvalforms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalformsDTO)))
            .andExpect(status().isOk());

        // Validate the Approvalforms in the database
        List<Approvalforms> approvalformsList = approvalformsRepository.findAll();
        assertThat(approvalformsList).hasSize(databaseSizeBeforeUpdate);
        Approvalforms testApprovalforms = approvalformsList.get(approvalformsList.size() - 1);
        assertThat(testApprovalforms.getExistingapprovalforms()).isEqualTo(UPDATED_EXISTINGAPPROVALFORMS);
    }

    @Test
    public void updateNonExistingApprovalforms() throws Exception {
        int databaseSizeBeforeUpdate = approvalformsRepository.findAll().size();

        // Create the Approvalforms
        ApprovalformsDTO approvalformsDTO = approvalformsMapper.approvalformsToApprovalformsDTO(approvalforms);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restApprovalformsMockMvc.perform(put("/api/approvalforms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalformsDTO)))
            .andExpect(status().isCreated());

        // Validate the Approvalforms in the database
        List<Approvalforms> approvalformsList = approvalformsRepository.findAll();
        assertThat(approvalformsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteApprovalforms() throws Exception {
        // Initialize the database
        approvalformsRepository.save(approvalforms);
        int databaseSizeBeforeDelete = approvalformsRepository.findAll().size();

        // Get the approvalforms
        restApprovalformsMockMvc.perform(delete("/api/approvalforms/{id}", approvalforms.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Approvalforms> approvalformsList = approvalformsRepository.findAll();
        assertThat(approvalformsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Approvalforms.class);
    }
}
