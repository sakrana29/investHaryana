package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Manufacturingdetail;
import com.hartron.investharyana.repository.ManufacturingdetailRepository;
import com.hartron.investharyana.service.ManufacturingdetailService;
import com.hartron.investharyana.service.dto.ManufacturingdetailDTO;
import com.hartron.investharyana.service.mapper.ManufacturingdetailMapper;
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
 * Test class for the ManufacturingdetailResource REST controller.
 *
 * @see ManufacturingdetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ManufacturingdetailResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final UUID DEFAULT_PROJECTRAWMATERIALID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTRAWMATERIALID = UUID.randomUUID();

    private static final UUID DEFAULT_PRODUCTID = UUID.randomUUID();
    private static final UUID UPDATED_PRODUCTID = UUID.randomUUID();

    private static final UUID DEFAULT_PROCESSID = UUID.randomUUID();
    private static final UUID UPDATED_PROCESSID = UUID.randomUUID();

    private static final String DEFAULT_MANUFACTURING_FLOW_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_MANUFACTURING_FLOW_DOCUMENT = "BBBBBBBBBB";

    @Autowired
    private ManufacturingdetailRepository manufacturingdetailRepository;

    @Autowired
    private ManufacturingdetailMapper manufacturingdetailMapper;

    @Autowired
    private ManufacturingdetailService manufacturingdetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restManufacturingdetailMockMvc;

    private Manufacturingdetail manufacturingdetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ManufacturingdetailResource manufacturingdetailResource = new ManufacturingdetailResource(manufacturingdetailService);
        this.restManufacturingdetailMockMvc = MockMvcBuilders.standaloneSetup(manufacturingdetailResource)
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
    public static Manufacturingdetail createEntity() {
        Manufacturingdetail manufacturingdetail = new Manufacturingdetail()
                .projectid(DEFAULT_PROJECTID)
                .projectrawmaterialid(DEFAULT_PROJECTRAWMATERIALID)
                .productid(DEFAULT_PRODUCTID)
                .processid(DEFAULT_PROCESSID)
                .manufacturing_flow_document(DEFAULT_MANUFACTURING_FLOW_DOCUMENT);
        return manufacturingdetail;
    }

    @Before
    public void initTest() {
        manufacturingdetailRepository.deleteAll();
        manufacturingdetail = createEntity();
    }

    @Test
    public void createManufacturingdetail() throws Exception {
        int databaseSizeBeforeCreate = manufacturingdetailRepository.findAll().size();

        // Create the Manufacturingdetail
        ManufacturingdetailDTO manufacturingdetailDTO = manufacturingdetailMapper.manufacturingdetailToManufacturingdetailDTO(manufacturingdetail);

        restManufacturingdetailMockMvc.perform(post("/api/manufacturingdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturingdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Manufacturingdetail in the database
        List<Manufacturingdetail> manufacturingdetailList = manufacturingdetailRepository.findAll();
        assertThat(manufacturingdetailList).hasSize(databaseSizeBeforeCreate + 1);
        Manufacturingdetail testManufacturingdetail = manufacturingdetailList.get(manufacturingdetailList.size() - 1);
        assertThat(testManufacturingdetail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testManufacturingdetail.getProjectrawmaterialid()).isEqualTo(DEFAULT_PROJECTRAWMATERIALID);
        assertThat(testManufacturingdetail.getProductid()).isEqualTo(DEFAULT_PRODUCTID);
        assertThat(testManufacturingdetail.getProcessid()).isEqualTo(DEFAULT_PROCESSID);
        assertThat(testManufacturingdetail.getManufacturing_flow_document()).isEqualTo(DEFAULT_MANUFACTURING_FLOW_DOCUMENT);
    }

    @Test
    public void createManufacturingdetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manufacturingdetailRepository.findAll().size();

        // Create the Manufacturingdetail with an existing ID
        Manufacturingdetail existingManufacturingdetail = new Manufacturingdetail();
        existingManufacturingdetail.setId(UUID.randomUUID());
        ManufacturingdetailDTO existingManufacturingdetailDTO = manufacturingdetailMapper.manufacturingdetailToManufacturingdetailDTO(existingManufacturingdetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufacturingdetailMockMvc.perform(post("/api/manufacturingdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingManufacturingdetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Manufacturingdetail> manufacturingdetailList = manufacturingdetailRepository.findAll();
        assertThat(manufacturingdetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllManufacturingdetails() throws Exception {
        // Initialize the database
        manufacturingdetailRepository.save(manufacturingdetail);

        // Get all the manufacturingdetailList
        restManufacturingdetailMockMvc.perform(get("/api/manufacturingdetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufacturingdetail.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].projectrawmaterialid").value(hasItem(DEFAULT_PROJECTRAWMATERIALID.toString())))
            .andExpect(jsonPath("$.[*].productid").value(hasItem(DEFAULT_PRODUCTID.toString())))
            .andExpect(jsonPath("$.[*].processid").value(hasItem(DEFAULT_PROCESSID.toString())))
            .andExpect(jsonPath("$.[*].manufacturing_flow_document").value(hasItem(DEFAULT_MANUFACTURING_FLOW_DOCUMENT.toString())));
    }

    @Test
    public void getManufacturingdetail() throws Exception {
        // Initialize the database
        manufacturingdetailRepository.save(manufacturingdetail);

        // Get the manufacturingdetail
        restManufacturingdetailMockMvc.perform(get("/api/manufacturingdetails/{id}", manufacturingdetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(manufacturingdetail.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.projectrawmaterialid").value(DEFAULT_PROJECTRAWMATERIALID.toString()))
            .andExpect(jsonPath("$.productid").value(DEFAULT_PRODUCTID.toString()))
            .andExpect(jsonPath("$.processid").value(DEFAULT_PROCESSID.toString()))
            .andExpect(jsonPath("$.manufacturing_flow_document").value(DEFAULT_MANUFACTURING_FLOW_DOCUMENT.toString()));
    }

    @Test
    public void getNonExistingManufacturingdetail() throws Exception {
        // Get the manufacturingdetail
        restManufacturingdetailMockMvc.perform(get("/api/manufacturingdetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateManufacturingdetail() throws Exception {
        // Initialize the database
        manufacturingdetailRepository.save(manufacturingdetail);
        int databaseSizeBeforeUpdate = manufacturingdetailRepository.findAll().size();

        // Update the manufacturingdetail
        Manufacturingdetail updatedManufacturingdetail = manufacturingdetailRepository.findOne(manufacturingdetail.getId());
        updatedManufacturingdetail
                .projectid(UPDATED_PROJECTID)
                .projectrawmaterialid(UPDATED_PROJECTRAWMATERIALID)
                .productid(UPDATED_PRODUCTID)
                .processid(UPDATED_PROCESSID)
                .manufacturing_flow_document(UPDATED_MANUFACTURING_FLOW_DOCUMENT);
        ManufacturingdetailDTO manufacturingdetailDTO = manufacturingdetailMapper.manufacturingdetailToManufacturingdetailDTO(updatedManufacturingdetail);

        restManufacturingdetailMockMvc.perform(put("/api/manufacturingdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturingdetailDTO)))
            .andExpect(status().isOk());

        // Validate the Manufacturingdetail in the database
        List<Manufacturingdetail> manufacturingdetailList = manufacturingdetailRepository.findAll();
        assertThat(manufacturingdetailList).hasSize(databaseSizeBeforeUpdate);
        Manufacturingdetail testManufacturingdetail = manufacturingdetailList.get(manufacturingdetailList.size() - 1);
        assertThat(testManufacturingdetail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testManufacturingdetail.getProjectrawmaterialid()).isEqualTo(UPDATED_PROJECTRAWMATERIALID);
        assertThat(testManufacturingdetail.getProductid()).isEqualTo(UPDATED_PRODUCTID);
        assertThat(testManufacturingdetail.getProcessid()).isEqualTo(UPDATED_PROCESSID);
        assertThat(testManufacturingdetail.getManufacturing_flow_document()).isEqualTo(UPDATED_MANUFACTURING_FLOW_DOCUMENT);
    }

    @Test
    public void updateNonExistingManufacturingdetail() throws Exception {
        int databaseSizeBeforeUpdate = manufacturingdetailRepository.findAll().size();

        // Create the Manufacturingdetail
        ManufacturingdetailDTO manufacturingdetailDTO = manufacturingdetailMapper.manufacturingdetailToManufacturingdetailDTO(manufacturingdetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restManufacturingdetailMockMvc.perform(put("/api/manufacturingdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(manufacturingdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Manufacturingdetail in the database
        List<Manufacturingdetail> manufacturingdetailList = manufacturingdetailRepository.findAll();
        assertThat(manufacturingdetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteManufacturingdetail() throws Exception {
        // Initialize the database
        manufacturingdetailRepository.save(manufacturingdetail);
        int databaseSizeBeforeDelete = manufacturingdetailRepository.findAll().size();

        // Get the manufacturingdetail
        restManufacturingdetailMockMvc.perform(delete("/api/manufacturingdetails/{id}", manufacturingdetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Manufacturingdetail> manufacturingdetailList = manufacturingdetailRepository.findAll();
        assertThat(manufacturingdetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manufacturingdetail.class);
    }
}
