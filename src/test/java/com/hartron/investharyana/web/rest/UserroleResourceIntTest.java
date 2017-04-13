package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Userrole;
import com.hartron.investharyana.repository.UserroleRepository;
import com.hartron.investharyana.service.UserroleService;
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
 * Test class for the UserroleResource REST controller.
 *
 * @see UserroleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class UserroleResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_USERROLE = "AAAAAAAAAA";
    private static final String UPDATED_USERROLE = "BBBBBBBBBB";

    @Autowired
    private UserroleRepository userroleRepository;

    @Autowired
    private UserroleService userroleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restUserroleMockMvc;

    private Userrole userrole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserroleResource userroleResource = new UserroleResource(userroleService);
        this.restUserroleMockMvc = MockMvcBuilders.standaloneSetup(userroleResource)
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
    public static Userrole createEntity() {
        Userrole userrole = new Userrole()
                .userrole(DEFAULT_USERROLE);
        return userrole;
    }

    @Before
    public void initTest() {
        userroleRepository.deleteAll();
        userrole = createEntity();
    }

    @Test
    public void createUserrole() throws Exception {
        int databaseSizeBeforeCreate = userroleRepository.findAll().size();

        // Create the Userrole

        restUserroleMockMvc.perform(post("/api/userroles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userrole)))
            .andExpect(status().isCreated());

        // Validate the Userrole in the database
        List<Userrole> userroleList = userroleRepository.findAll();
        assertThat(userroleList).hasSize(databaseSizeBeforeCreate + 1);
        Userrole testUserrole = userroleList.get(userroleList.size() - 1);
        assertThat(testUserrole.getUserrole()).isEqualTo(DEFAULT_USERROLE);
    }

    @Test
    public void createUserroleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userroleRepository.findAll().size();

        // Create the Userrole with an existing ID
        Userrole existingUserrole = new Userrole();
        existingUserrole.setId(UUID.randomUUID());

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserroleMockMvc.perform(post("/api/userroles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserrole)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Userrole> userroleList = userroleRepository.findAll();
        assertThat(userroleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkUserroleIsRequired() throws Exception {
        int databaseSizeBeforeTest = userroleRepository.findAll().size();
        // set the field null
        userrole.setUserrole(null);

        // Create the Userrole, which fails.

        restUserroleMockMvc.perform(post("/api/userroles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userrole)))
            .andExpect(status().isBadRequest());

        List<Userrole> userroleList = userroleRepository.findAll();
        assertThat(userroleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllUserroles() throws Exception {
        // Initialize the database
        userroleRepository.save(userrole);

        // Get all the userroleList
        restUserroleMockMvc.perform(get("/api/userroles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userrole.getId().toString())))
            .andExpect(jsonPath("$.[*].userrole").value(hasItem(DEFAULT_USERROLE.toString())));
    }

    @Test
    public void getUserrole() throws Exception {
        // Initialize the database
        userroleRepository.save(userrole);

        // Get the userrole
        restUserroleMockMvc.perform(get("/api/userroles/{id}", userrole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userrole.getId().toString()))
            .andExpect(jsonPath("$.userrole").value(DEFAULT_USERROLE.toString()));
    }

    @Test
    public void getNonExistingUserrole() throws Exception {
        // Get the userrole
        restUserroleMockMvc.perform(get("/api/userroles/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserrole() throws Exception {
        // Initialize the database
        userroleService.save(userrole);

        int databaseSizeBeforeUpdate = userroleRepository.findAll().size();

        // Update the userrole
        Userrole updatedUserrole = userroleRepository.findOne(userrole.getId());
        updatedUserrole
                .userrole(UPDATED_USERROLE);

        restUserroleMockMvc.perform(put("/api/userroles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserrole)))
            .andExpect(status().isOk());

        // Validate the Userrole in the database
        List<Userrole> userroleList = userroleRepository.findAll();
        assertThat(userroleList).hasSize(databaseSizeBeforeUpdate);
        Userrole testUserrole = userroleList.get(userroleList.size() - 1);
        assertThat(testUserrole.getUserrole()).isEqualTo(UPDATED_USERROLE);
    }

    @Test
    public void updateNonExistingUserrole() throws Exception {
        int databaseSizeBeforeUpdate = userroleRepository.findAll().size();

        // Create the Userrole

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserroleMockMvc.perform(put("/api/userroles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userrole)))
            .andExpect(status().isCreated());

        // Validate the Userrole in the database
        List<Userrole> userroleList = userroleRepository.findAll();
        assertThat(userroleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteUserrole() throws Exception {
        // Initialize the database
        userroleService.save(userrole);

        int databaseSizeBeforeDelete = userroleRepository.findAll().size();

        // Get the userrole
        restUserroleMockMvc.perform(delete("/api/userroles/{id}", userrole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Userrole> userroleList = userroleRepository.findAll();
        assertThat(userroleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Userrole.class);
    }
}
