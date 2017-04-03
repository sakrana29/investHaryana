package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Wastewaterdetail;
import com.hartron.investharyana.repository.WastewaterdetailRepository;
import com.hartron.investharyana.service.WastewaterdetailService;
import com.hartron.investharyana.service.dto.WastewaterdetailDTO;
import com.hartron.investharyana.service.mapper.WastewaterdetailMapper;
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
 * Test class for the WastewaterdetailResource REST controller.
 *
 * @see WastewaterdetailResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class WastewaterdetailResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_SOURCE_OF_GENERATION = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_OF_GENERATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_NATURETYPE = "AAAAAAAAAA";
    private static final String UPDATED_NATURETYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MODE_OF_DISPOSAL = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_DISPOSAL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    @Autowired
    private WastewaterdetailRepository wastewaterdetailRepository;

    @Autowired
    private WastewaterdetailMapper wastewaterdetailMapper;

    @Autowired
    private WastewaterdetailService wastewaterdetailService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restWastewaterdetailMockMvc;

    private Wastewaterdetail wastewaterdetail;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WastewaterdetailResource wastewaterdetailResource = new WastewaterdetailResource(wastewaterdetailService);
        this.restWastewaterdetailMockMvc = MockMvcBuilders.standaloneSetup(wastewaterdetailResource)
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
    public static Wastewaterdetail createEntity() {
        Wastewaterdetail wastewaterdetail = new Wastewaterdetail()
                .source_of_generation(DEFAULT_SOURCE_OF_GENERATION)
                .quantity(DEFAULT_QUANTITY)
                .naturetype(DEFAULT_NATURETYPE)
                .mode_of_disposal(DEFAULT_MODE_OF_DISPOSAL)
                .createdate(DEFAULT_CREATEDATE)
                .updatedate(DEFAULT_UPDATEDATE)
                .projectid(DEFAULT_PROJECTID);
        return wastewaterdetail;
    }

    @Before
    public void initTest() {
        wastewaterdetailRepository.deleteAll();
        wastewaterdetail = createEntity();
    }

    @Test
    public void createWastewaterdetail() throws Exception {
        int databaseSizeBeforeCreate = wastewaterdetailRepository.findAll().size();

        // Create the Wastewaterdetail
        WastewaterdetailDTO wastewaterdetailDTO = wastewaterdetailMapper.wastewaterdetailToWastewaterdetailDTO(wastewaterdetail);

        restWastewaterdetailMockMvc.perform(post("/api/wastewaterdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wastewaterdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Wastewaterdetail in the database
        List<Wastewaterdetail> wastewaterdetailList = wastewaterdetailRepository.findAll();
        assertThat(wastewaterdetailList).hasSize(databaseSizeBeforeCreate + 1);
        Wastewaterdetail testWastewaterdetail = wastewaterdetailList.get(wastewaterdetailList.size() - 1);
        assertThat(testWastewaterdetail.getSource_of_generation()).isEqualTo(DEFAULT_SOURCE_OF_GENERATION);
        assertThat(testWastewaterdetail.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testWastewaterdetail.getNaturetype()).isEqualTo(DEFAULT_NATURETYPE);
        assertThat(testWastewaterdetail.getMode_of_disposal()).isEqualTo(DEFAULT_MODE_OF_DISPOSAL);
        assertThat(testWastewaterdetail.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testWastewaterdetail.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
        assertThat(testWastewaterdetail.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
    }

    @Test
    public void createWastewaterdetailWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wastewaterdetailRepository.findAll().size();

        // Create the Wastewaterdetail with an existing ID
        Wastewaterdetail existingWastewaterdetail = new Wastewaterdetail();
        existingWastewaterdetail.setId(UUID.randomUUID());
        WastewaterdetailDTO existingWastewaterdetailDTO = wastewaterdetailMapper.wastewaterdetailToWastewaterdetailDTO(existingWastewaterdetail);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWastewaterdetailMockMvc.perform(post("/api/wastewaterdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingWastewaterdetailDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Wastewaterdetail> wastewaterdetailList = wastewaterdetailRepository.findAll();
        assertThat(wastewaterdetailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllWastewaterdetails() throws Exception {
        // Initialize the database
        wastewaterdetailRepository.save(wastewaterdetail);

        // Get all the wastewaterdetailList
        restWastewaterdetailMockMvc.perform(get("/api/wastewaterdetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wastewaterdetail.getId().toString())))
            .andExpect(jsonPath("$.[*].source_of_generation").value(hasItem(DEFAULT_SOURCE_OF_GENERATION.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].naturetype").value(hasItem(DEFAULT_NATURETYPE.toString())))
            .andExpect(jsonPath("$.[*].mode_of_disposal").value(hasItem(DEFAULT_MODE_OF_DISPOSAL.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(sameInstant(DEFAULT_UPDATEDATE))))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())));
    }

    @Test
    public void getWastewaterdetail() throws Exception {
        // Initialize the database
        wastewaterdetailRepository.save(wastewaterdetail);

        // Get the wastewaterdetail
        restWastewaterdetailMockMvc.perform(get("/api/wastewaterdetails/{id}", wastewaterdetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wastewaterdetail.getId().toString()))
            .andExpect(jsonPath("$.source_of_generation").value(DEFAULT_SOURCE_OF_GENERATION.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.naturetype").value(DEFAULT_NATURETYPE.toString()))
            .andExpect(jsonPath("$.mode_of_disposal").value(DEFAULT_MODE_OF_DISPOSAL.toString()))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.updatedate").value(sameInstant(DEFAULT_UPDATEDATE)))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()));
    }

    @Test
    public void getNonExistingWastewaterdetail() throws Exception {
        // Get the wastewaterdetail
        restWastewaterdetailMockMvc.perform(get("/api/wastewaterdetails/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWastewaterdetail() throws Exception {
        // Initialize the database
        wastewaterdetailRepository.save(wastewaterdetail);
        int databaseSizeBeforeUpdate = wastewaterdetailRepository.findAll().size();

        // Update the wastewaterdetail
        Wastewaterdetail updatedWastewaterdetail = wastewaterdetailRepository.findOne(wastewaterdetail.getId());
        updatedWastewaterdetail
                .source_of_generation(UPDATED_SOURCE_OF_GENERATION)
                .quantity(UPDATED_QUANTITY)
                .naturetype(UPDATED_NATURETYPE)
                .mode_of_disposal(UPDATED_MODE_OF_DISPOSAL)
                .createdate(UPDATED_CREATEDATE)
                .updatedate(UPDATED_UPDATEDATE)
                .projectid(UPDATED_PROJECTID);
        WastewaterdetailDTO wastewaterdetailDTO = wastewaterdetailMapper.wastewaterdetailToWastewaterdetailDTO(updatedWastewaterdetail);

        restWastewaterdetailMockMvc.perform(put("/api/wastewaterdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wastewaterdetailDTO)))
            .andExpect(status().isOk());

        // Validate the Wastewaterdetail in the database
        List<Wastewaterdetail> wastewaterdetailList = wastewaterdetailRepository.findAll();
        assertThat(wastewaterdetailList).hasSize(databaseSizeBeforeUpdate);
        Wastewaterdetail testWastewaterdetail = wastewaterdetailList.get(wastewaterdetailList.size() - 1);
        assertThat(testWastewaterdetail.getSource_of_generation()).isEqualTo(UPDATED_SOURCE_OF_GENERATION);
        assertThat(testWastewaterdetail.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testWastewaterdetail.getNaturetype()).isEqualTo(UPDATED_NATURETYPE);
        assertThat(testWastewaterdetail.getMode_of_disposal()).isEqualTo(UPDATED_MODE_OF_DISPOSAL);
        assertThat(testWastewaterdetail.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testWastewaterdetail.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
        assertThat(testWastewaterdetail.getProjectid()).isEqualTo(UPDATED_PROJECTID);
    }

    @Test
    public void updateNonExistingWastewaterdetail() throws Exception {
        int databaseSizeBeforeUpdate = wastewaterdetailRepository.findAll().size();

        // Create the Wastewaterdetail
        WastewaterdetailDTO wastewaterdetailDTO = wastewaterdetailMapper.wastewaterdetailToWastewaterdetailDTO(wastewaterdetail);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWastewaterdetailMockMvc.perform(put("/api/wastewaterdetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wastewaterdetailDTO)))
            .andExpect(status().isCreated());

        // Validate the Wastewaterdetail in the database
        List<Wastewaterdetail> wastewaterdetailList = wastewaterdetailRepository.findAll();
        assertThat(wastewaterdetailList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteWastewaterdetail() throws Exception {
        // Initialize the database
        wastewaterdetailRepository.save(wastewaterdetail);
        int databaseSizeBeforeDelete = wastewaterdetailRepository.findAll().size();

        // Get the wastewaterdetail
        restWastewaterdetailMockMvc.perform(delete("/api/wastewaterdetails/{id}", wastewaterdetail.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Wastewaterdetail> wastewaterdetailList = wastewaterdetailRepository.findAll();
        assertThat(wastewaterdetailList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wastewaterdetail.class);
    }
}
