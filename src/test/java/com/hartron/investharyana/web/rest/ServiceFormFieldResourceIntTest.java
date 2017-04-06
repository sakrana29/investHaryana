package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.ServiceFormField;
import com.hartron.investharyana.repository.ServiceFormFieldRepository;
import com.hartron.investharyana.service.ServiceFormFieldService;
import com.hartron.investharyana.service.dto.ServiceFormFieldDTO;
import com.hartron.investharyana.service.mapper.ServiceFormFieldMapper;
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
 * Test class for the ServiceFormFieldResource REST controller.
 *
 * @see ServiceFormFieldResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ServiceFormFieldResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_FIELD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIELD_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_TYPE = "BBBBBBBBBB";

    private static final UUID DEFAULT_SERVICE_ID = UUID.randomUUID();
    private static final UUID UPDATED_SERVICE_ID = UUID.randomUUID();

    private static final String DEFAULT_FIELD_TYPE_OPTION = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_TYPE_OPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_FIELD_RENDERING_ORDER = 1;
    private static final Integer UPDATED_FIELD_RENDERING_ORDER = 2;

    @Autowired
    private ServiceFormFieldRepository serviceFormFieldRepository;

    @Autowired
    private ServiceFormFieldMapper serviceFormFieldMapper;

    @Autowired
    private ServiceFormFieldService serviceFormFieldService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restServiceFormFieldMockMvc;

    private ServiceFormField serviceFormField;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ServiceFormFieldResource serviceFormFieldResource = new ServiceFormFieldResource(serviceFormFieldService);
        this.restServiceFormFieldMockMvc = MockMvcBuilders.standaloneSetup(serviceFormFieldResource)
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
    public static ServiceFormField createEntity() {
        ServiceFormField serviceFormField = new ServiceFormField()
                .fieldName(DEFAULT_FIELD_NAME)
                .fieldType(DEFAULT_FIELD_TYPE)
                .serviceID(DEFAULT_SERVICE_ID)
                .fieldTypeOption(DEFAULT_FIELD_TYPE_OPTION)
                .fieldRenderingOrder(DEFAULT_FIELD_RENDERING_ORDER);
        return serviceFormField;
    }

    @Before
    public void initTest() {
        serviceFormFieldRepository.deleteAll();
        serviceFormField = createEntity();
    }

    @Test
    public void createServiceFormField() throws Exception {
        int databaseSizeBeforeCreate = serviceFormFieldRepository.findAll().size();

        // Create the ServiceFormField
        ServiceFormFieldDTO serviceFormFieldDTO = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(serviceFormField);

        restServiceFormFieldMockMvc.perform(post("/api/service-form-fields")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceFormFieldDTO)))
            .andExpect(status().isCreated());

        // Validate the ServiceFormField in the database
        List<ServiceFormField> serviceFormFieldList = serviceFormFieldRepository.findAll();
        assertThat(serviceFormFieldList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceFormField testServiceFormField = serviceFormFieldList.get(serviceFormFieldList.size() - 1);
        assertThat(testServiceFormField.getFieldName()).isEqualTo(DEFAULT_FIELD_NAME);
        assertThat(testServiceFormField.getFieldType()).isEqualTo(DEFAULT_FIELD_TYPE);
        assertThat(testServiceFormField.getServiceID()).isEqualTo(DEFAULT_SERVICE_ID);
        assertThat(testServiceFormField.getFieldTypeOption()).isEqualTo(DEFAULT_FIELD_TYPE_OPTION);
        assertThat(testServiceFormField.getFieldRenderingOrder()).isEqualTo(DEFAULT_FIELD_RENDERING_ORDER);
    }

    @Test
    public void createServiceFormFieldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceFormFieldRepository.findAll().size();

        // Create the ServiceFormField with an existing ID
        ServiceFormField existingServiceFormField = new ServiceFormField();
        existingServiceFormField.setId(UUID.randomUUID());
        ServiceFormFieldDTO existingServiceFormFieldDTO = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(existingServiceFormField);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceFormFieldMockMvc.perform(post("/api/service-form-fields")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingServiceFormFieldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ServiceFormField> serviceFormFieldList = serviceFormFieldRepository.findAll();
        assertThat(serviceFormFieldList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkFieldNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceFormFieldRepository.findAll().size();
        // set the field null
        serviceFormField.setFieldName(null);

        // Create the ServiceFormField, which fails.
        ServiceFormFieldDTO serviceFormFieldDTO = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(serviceFormField);

        restServiceFormFieldMockMvc.perform(post("/api/service-form-fields")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceFormFieldDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceFormField> serviceFormFieldList = serviceFormFieldRepository.findAll();
        assertThat(serviceFormFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkFieldTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceFormFieldRepository.findAll().size();
        // set the field null
        serviceFormField.setFieldType(null);

        // Create the ServiceFormField, which fails.
        ServiceFormFieldDTO serviceFormFieldDTO = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(serviceFormField);

        restServiceFormFieldMockMvc.perform(post("/api/service-form-fields")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceFormFieldDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceFormField> serviceFormFieldList = serviceFormFieldRepository.findAll();
        assertThat(serviceFormFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkServiceIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceFormFieldRepository.findAll().size();
        // set the field null
        serviceFormField.setServiceID(null);

        // Create the ServiceFormField, which fails.
        ServiceFormFieldDTO serviceFormFieldDTO = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(serviceFormField);

        restServiceFormFieldMockMvc.perform(post("/api/service-form-fields")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceFormFieldDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceFormField> serviceFormFieldList = serviceFormFieldRepository.findAll();
        assertThat(serviceFormFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllServiceFormFields() throws Exception {
        // Initialize the database
        serviceFormFieldRepository.save(serviceFormField);

        // Get all the serviceFormFieldList
        restServiceFormFieldMockMvc.perform(get("/api/service-form-fields?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceFormField.getId().toString())))
            .andExpect(jsonPath("$.[*].fieldName").value(hasItem(DEFAULT_FIELD_NAME.toString())))
            .andExpect(jsonPath("$.[*].fieldType").value(hasItem(DEFAULT_FIELD_TYPE.toString())))
            .andExpect(jsonPath("$.[*].serviceID").value(hasItem(DEFAULT_SERVICE_ID.toString())))
            .andExpect(jsonPath("$.[*].fieldTypeOption").value(hasItem(DEFAULT_FIELD_TYPE_OPTION.toString())))
            .andExpect(jsonPath("$.[*].fieldRenderingOrder").value(hasItem(DEFAULT_FIELD_RENDERING_ORDER)));
    }

    @Test
    public void getServiceFormField() throws Exception {
        // Initialize the database
        serviceFormFieldRepository.save(serviceFormField);

        // Get the serviceFormField
        restServiceFormFieldMockMvc.perform(get("/api/service-form-fields/{id}", serviceFormField.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(serviceFormField.getId().toString()))
            .andExpect(jsonPath("$.fieldName").value(DEFAULT_FIELD_NAME.toString()))
            .andExpect(jsonPath("$.fieldType").value(DEFAULT_FIELD_TYPE.toString()))
            .andExpect(jsonPath("$.serviceID").value(DEFAULT_SERVICE_ID.toString()))
            .andExpect(jsonPath("$.fieldTypeOption").value(DEFAULT_FIELD_TYPE_OPTION.toString()))
            .andExpect(jsonPath("$.fieldRenderingOrder").value(DEFAULT_FIELD_RENDERING_ORDER));
    }

    @Test
    public void getNonExistingServiceFormField() throws Exception {
        // Get the serviceFormField
        restServiceFormFieldMockMvc.perform(get("/api/service-form-fields/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateServiceFormField() throws Exception {
        // Initialize the database
        serviceFormFieldRepository.save(serviceFormField);
        int databaseSizeBeforeUpdate = serviceFormFieldRepository.findAll().size();

        // Update the serviceFormField
        ServiceFormField updatedServiceFormField = serviceFormFieldRepository.findOne(serviceFormField.getId());
        updatedServiceFormField
                .fieldName(UPDATED_FIELD_NAME)
                .fieldType(UPDATED_FIELD_TYPE)
                .serviceID(UPDATED_SERVICE_ID)
                .fieldTypeOption(UPDATED_FIELD_TYPE_OPTION)
                .fieldRenderingOrder(UPDATED_FIELD_RENDERING_ORDER);
        ServiceFormFieldDTO serviceFormFieldDTO = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(updatedServiceFormField);

        restServiceFormFieldMockMvc.perform(put("/api/service-form-fields")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceFormFieldDTO)))
            .andExpect(status().isOk());

        // Validate the ServiceFormField in the database
        List<ServiceFormField> serviceFormFieldList = serviceFormFieldRepository.findAll();
        assertThat(serviceFormFieldList).hasSize(databaseSizeBeforeUpdate);
        ServiceFormField testServiceFormField = serviceFormFieldList.get(serviceFormFieldList.size() - 1);
        assertThat(testServiceFormField.getFieldName()).isEqualTo(UPDATED_FIELD_NAME);
        assertThat(testServiceFormField.getFieldType()).isEqualTo(UPDATED_FIELD_TYPE);
        assertThat(testServiceFormField.getServiceID()).isEqualTo(UPDATED_SERVICE_ID);
        assertThat(testServiceFormField.getFieldTypeOption()).isEqualTo(UPDATED_FIELD_TYPE_OPTION);
        assertThat(testServiceFormField.getFieldRenderingOrder()).isEqualTo(UPDATED_FIELD_RENDERING_ORDER);
    }

    @Test
    public void updateNonExistingServiceFormField() throws Exception {
        int databaseSizeBeforeUpdate = serviceFormFieldRepository.findAll().size();

        // Create the ServiceFormField
        ServiceFormFieldDTO serviceFormFieldDTO = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(serviceFormField);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restServiceFormFieldMockMvc.perform(put("/api/service-form-fields")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceFormFieldDTO)))
            .andExpect(status().isCreated());

        // Validate the ServiceFormField in the database
        List<ServiceFormField> serviceFormFieldList = serviceFormFieldRepository.findAll();
        assertThat(serviceFormFieldList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteServiceFormField() throws Exception {
        // Initialize the database
        serviceFormFieldRepository.save(serviceFormField);
        int databaseSizeBeforeDelete = serviceFormFieldRepository.findAll().size();

        // Get the serviceFormField
        restServiceFormFieldMockMvc.perform(delete("/api/service-form-fields/{id}", serviceFormField.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ServiceFormField> serviceFormFieldList = serviceFormFieldRepository.findAll();
        assertThat(serviceFormFieldList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceFormField.class);
    }
}
