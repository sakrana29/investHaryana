package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Filetesting;
import com.hartron.investharyana.repository.FiletestingRepository;
import com.hartron.investharyana.service.FiletestingService;
import com.hartron.investharyana.service.dto.FiletestingDTO;
import com.hartron.investharyana.service.mapper.FiletestingMapper;
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
 * Test class for the FiletestingResource REST controller.
 *
 * @see FiletestingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class FiletestingResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_FILENAME = "AAAAAAAAAA";
    private static final String UPDATED_FILENAME = "BBBBBBBBBB";

    @Autowired
    private FiletestingRepository filetestingRepository;

    @Autowired
    private FiletestingMapper filetestingMapper;

    @Autowired
    private FiletestingService filetestingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restFiletestingMockMvc;

    private Filetesting filetesting;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FiletestingResource filetestingResource = new FiletestingResource(filetestingService);
        this.restFiletestingMockMvc = MockMvcBuilders.standaloneSetup(filetestingResource)
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
    public static Filetesting createEntity() {
        Filetesting filetesting = new Filetesting()
                .filename(DEFAULT_FILENAME);
        return filetesting;
    }

    @Before
    public void initTest() {
        filetestingRepository.deleteAll();
        filetesting = createEntity();
    }

    @Test
    public void createFiletesting() throws Exception {
        int databaseSizeBeforeCreate = filetestingRepository.findAll().size();

        // Create the Filetesting
        FiletestingDTO filetestingDTO = filetestingMapper.filetestingToFiletestingDTO(filetesting);

        restFiletestingMockMvc.perform(post("/api/filetestings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filetestingDTO)))
            .andExpect(status().isCreated());

        // Validate the Filetesting in the database
        List<Filetesting> filetestingList = filetestingRepository.findAll();
        assertThat(filetestingList).hasSize(databaseSizeBeforeCreate + 1);
        Filetesting testFiletesting = filetestingList.get(filetestingList.size() - 1);
        assertThat(testFiletesting.getFilename()).isEqualTo(DEFAULT_FILENAME);
    }

    @Test
    public void createFiletestingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = filetestingRepository.findAll().size();

        // Create the Filetesting with an existing ID
        Filetesting existingFiletesting = new Filetesting();
        existingFiletesting.setId(UUID.randomUUID());
        FiletestingDTO existingFiletestingDTO = filetestingMapper.filetestingToFiletestingDTO(existingFiletesting);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFiletestingMockMvc.perform(post("/api/filetestings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingFiletestingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Filetesting> filetestingList = filetestingRepository.findAll();
        assertThat(filetestingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllFiletestings() throws Exception {
        // Initialize the database
        filetestingRepository.save(filetesting);

        // Get all the filetestingList
        restFiletestingMockMvc.perform(get("/api/filetestings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(filetesting.getId().toString())))
            .andExpect(jsonPath("$.[*].filename").value(hasItem(DEFAULT_FILENAME.toString())));
    }

    @Test
    public void getFiletesting() throws Exception {
        // Initialize the database
        filetestingRepository.save(filetesting);

        // Get the filetesting
        restFiletestingMockMvc.perform(get("/api/filetestings/{id}", filetesting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(filetesting.getId().toString()))
            .andExpect(jsonPath("$.filename").value(DEFAULT_FILENAME.toString()));
    }

    @Test
    public void getNonExistingFiletesting() throws Exception {
        // Get the filetesting
        restFiletestingMockMvc.perform(get("/api/filetestings/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFiletesting() throws Exception {
        // Initialize the database
        filetestingRepository.save(filetesting);
        int databaseSizeBeforeUpdate = filetestingRepository.findAll().size();

        // Update the filetesting
        Filetesting updatedFiletesting = filetestingRepository.findOne(filetesting.getId());
        updatedFiletesting
                .filename(UPDATED_FILENAME);
        FiletestingDTO filetestingDTO = filetestingMapper.filetestingToFiletestingDTO(updatedFiletesting);

        restFiletestingMockMvc.perform(put("/api/filetestings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filetestingDTO)))
            .andExpect(status().isOk());

        // Validate the Filetesting in the database
        List<Filetesting> filetestingList = filetestingRepository.findAll();
        assertThat(filetestingList).hasSize(databaseSizeBeforeUpdate);
        Filetesting testFiletesting = filetestingList.get(filetestingList.size() - 1);
        assertThat(testFiletesting.getFilename()).isEqualTo(UPDATED_FILENAME);
    }

    @Test
    public void updateNonExistingFiletesting() throws Exception {
        int databaseSizeBeforeUpdate = filetestingRepository.findAll().size();

        // Create the Filetesting
        FiletestingDTO filetestingDTO = filetestingMapper.filetestingToFiletestingDTO(filetesting);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFiletestingMockMvc.perform(put("/api/filetestings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filetestingDTO)))
            .andExpect(status().isCreated());

        // Validate the Filetesting in the database
        List<Filetesting> filetestingList = filetestingRepository.findAll();
        assertThat(filetestingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteFiletesting() throws Exception {
        // Initialize the database
        filetestingRepository.save(filetesting);
        int databaseSizeBeforeDelete = filetestingRepository.findAll().size();

        // Get the filetesting
        restFiletestingMockMvc.perform(delete("/api/filetestings/{id}", filetesting.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Filetesting> filetestingList = filetestingRepository.findAll();
        assertThat(filetestingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Filetesting.class);
    }
}
