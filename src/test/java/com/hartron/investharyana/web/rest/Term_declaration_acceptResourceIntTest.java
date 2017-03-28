package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Term_declaration_accept;
import com.hartron.investharyana.repository.Term_declaration_acceptRepository;
import com.hartron.investharyana.service.Term_declaration_acceptService;
import com.hartron.investharyana.service.dto.Term_declaration_acceptDTO;
import com.hartron.investharyana.service.mapper.Term_declaration_acceptMapper;
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
import org.springframework.util.Base64Utils;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import static com.hartron.investharyana.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Term_declaration_acceptResource REST controller.
 *
 * @see Term_declaration_acceptResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class Term_declaration_acceptResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final Boolean DEFAULT_ACCEPTANCE = false;
    private static final Boolean UPDATED_ACCEPTANCE = true;

    private static final ZonedDateTime DEFAULT_APPLICATIONDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_APPLICATIONDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_PLACE = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_SIGNATURE = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_SIGNATURE = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_SIGNATURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SIGNATURE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    @Autowired
    private Term_declaration_acceptRepository term_declaration_acceptRepository;

    @Autowired
    private Term_declaration_acceptMapper term_declaration_acceptMapper;

    @Autowired
    private Term_declaration_acceptService term_declaration_acceptService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restTerm_declaration_acceptMockMvc;

    private Term_declaration_accept term_declaration_accept;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Term_declaration_acceptResource term_declaration_acceptResource = new Term_declaration_acceptResource(term_declaration_acceptService);
        this.restTerm_declaration_acceptMockMvc = MockMvcBuilders.standaloneSetup(term_declaration_acceptResource)
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
    public static Term_declaration_accept createEntity() {
        Term_declaration_accept term_declaration_accept = new Term_declaration_accept()
                .projectid(DEFAULT_PROJECTID)
                .acceptance(DEFAULT_ACCEPTANCE)
                .applicationdate(DEFAULT_APPLICATIONDATE)
                .place(DEFAULT_PLACE)
                .signature(DEFAULT_SIGNATURE)
                .signatureContentType(DEFAULT_SIGNATURE_CONTENT_TYPE)
                .projectname(DEFAULT_PROJECTNAME);
        return term_declaration_accept;
    }

    @Before
    public void initTest() {
        term_declaration_acceptRepository.deleteAll();
        term_declaration_accept = createEntity();
    }

    @Test
    public void createTerm_declaration_accept() throws Exception {
        int databaseSizeBeforeCreate = term_declaration_acceptRepository.findAll().size();

        // Create the Term_declaration_accept
        Term_declaration_acceptDTO term_declaration_acceptDTO = term_declaration_acceptMapper.term_declaration_acceptToTerm_declaration_acceptDTO(term_declaration_accept);

        restTerm_declaration_acceptMockMvc.perform(post("/api/term-declaration-accepts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(term_declaration_acceptDTO)))
            .andExpect(status().isCreated());

        // Validate the Term_declaration_accept in the database
        List<Term_declaration_accept> term_declaration_acceptList = term_declaration_acceptRepository.findAll();
        assertThat(term_declaration_acceptList).hasSize(databaseSizeBeforeCreate + 1);
        Term_declaration_accept testTerm_declaration_accept = term_declaration_acceptList.get(term_declaration_acceptList.size() - 1);
        assertThat(testTerm_declaration_accept.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testTerm_declaration_accept.isAcceptance()).isEqualTo(DEFAULT_ACCEPTANCE);
        assertThat(testTerm_declaration_accept.getApplicationdate()).isEqualTo(DEFAULT_APPLICATIONDATE);
        assertThat(testTerm_declaration_accept.getPlace()).isEqualTo(DEFAULT_PLACE);
        assertThat(testTerm_declaration_accept.getSignature()).isEqualTo(DEFAULT_SIGNATURE);
        assertThat(testTerm_declaration_accept.getSignatureContentType()).isEqualTo(DEFAULT_SIGNATURE_CONTENT_TYPE);
        assertThat(testTerm_declaration_accept.getProjectname()).isEqualTo(DEFAULT_PROJECTNAME);
    }

    @Test
    public void createTerm_declaration_acceptWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = term_declaration_acceptRepository.findAll().size();

        // Create the Term_declaration_accept with an existing ID
        Term_declaration_accept existingTerm_declaration_accept = new Term_declaration_accept();
        existingTerm_declaration_accept.setId(UUID.randomUUID());
        Term_declaration_acceptDTO existingTerm_declaration_acceptDTO = term_declaration_acceptMapper.term_declaration_acceptToTerm_declaration_acceptDTO(existingTerm_declaration_accept);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTerm_declaration_acceptMockMvc.perform(post("/api/term-declaration-accepts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingTerm_declaration_acceptDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Term_declaration_accept> term_declaration_acceptList = term_declaration_acceptRepository.findAll();
        assertThat(term_declaration_acceptList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllTerm_declaration_accepts() throws Exception {
        // Initialize the database
        term_declaration_acceptRepository.save(term_declaration_accept);

        // Get all the term_declaration_acceptList
        restTerm_declaration_acceptMockMvc.perform(get("/api/term-declaration-accepts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(term_declaration_accept.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].acceptance").value(hasItem(DEFAULT_ACCEPTANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].applicationdate").value(hasItem(sameInstant(DEFAULT_APPLICATIONDATE))))
            .andExpect(jsonPath("$.[*].place").value(hasItem(DEFAULT_PLACE.toString())))
            .andExpect(jsonPath("$.[*].signatureContentType").value(hasItem(DEFAULT_SIGNATURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].signature").value(hasItem(Base64Utils.encodeToString(DEFAULT_SIGNATURE.array()))))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME.toString())));
    }

    @Test
    public void getTerm_declaration_accept() throws Exception {
        // Initialize the database
        term_declaration_acceptRepository.save(term_declaration_accept);

        // Get the term_declaration_accept
        restTerm_declaration_acceptMockMvc.perform(get("/api/term-declaration-accepts/{id}", term_declaration_accept.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(term_declaration_accept.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.acceptance").value(DEFAULT_ACCEPTANCE.booleanValue()))
            .andExpect(jsonPath("$.applicationdate").value(sameInstant(DEFAULT_APPLICATIONDATE)))
            .andExpect(jsonPath("$.place").value(DEFAULT_PLACE.toString()))
            .andExpect(jsonPath("$.signatureContentType").value(DEFAULT_SIGNATURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.signature").value(Base64Utils.encodeToString(DEFAULT_SIGNATURE.array())))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME.toString()));
    }

    @Test
    public void getNonExistingTerm_declaration_accept() throws Exception {
        // Get the term_declaration_accept
        restTerm_declaration_acceptMockMvc.perform(get("/api/term-declaration-accepts/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTerm_declaration_accept() throws Exception {
        // Initialize the database
        term_declaration_acceptRepository.save(term_declaration_accept);
        int databaseSizeBeforeUpdate = term_declaration_acceptRepository.findAll().size();

        // Update the term_declaration_accept
        Term_declaration_accept updatedTerm_declaration_accept = term_declaration_acceptRepository.findOne(term_declaration_accept.getId());
        updatedTerm_declaration_accept
                .projectid(UPDATED_PROJECTID)
                .acceptance(UPDATED_ACCEPTANCE)
                .applicationdate(UPDATED_APPLICATIONDATE)
                .place(UPDATED_PLACE)
                .signature(UPDATED_SIGNATURE)
                .signatureContentType(UPDATED_SIGNATURE_CONTENT_TYPE)
                .projectname(UPDATED_PROJECTNAME);
        Term_declaration_acceptDTO term_declaration_acceptDTO = term_declaration_acceptMapper.term_declaration_acceptToTerm_declaration_acceptDTO(updatedTerm_declaration_accept);

        restTerm_declaration_acceptMockMvc.perform(put("/api/term-declaration-accepts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(term_declaration_acceptDTO)))
            .andExpect(status().isOk());

        // Validate the Term_declaration_accept in the database
        List<Term_declaration_accept> term_declaration_acceptList = term_declaration_acceptRepository.findAll();
        assertThat(term_declaration_acceptList).hasSize(databaseSizeBeforeUpdate);
        Term_declaration_accept testTerm_declaration_accept = term_declaration_acceptList.get(term_declaration_acceptList.size() - 1);
        assertThat(testTerm_declaration_accept.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testTerm_declaration_accept.isAcceptance()).isEqualTo(UPDATED_ACCEPTANCE);
        assertThat(testTerm_declaration_accept.getApplicationdate()).isEqualTo(UPDATED_APPLICATIONDATE);
        assertThat(testTerm_declaration_accept.getPlace()).isEqualTo(UPDATED_PLACE);
        assertThat(testTerm_declaration_accept.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testTerm_declaration_accept.getSignatureContentType()).isEqualTo(UPDATED_SIGNATURE_CONTENT_TYPE);
        assertThat(testTerm_declaration_accept.getProjectname()).isEqualTo(UPDATED_PROJECTNAME);
    }

    @Test
    public void updateNonExistingTerm_declaration_accept() throws Exception {
        int databaseSizeBeforeUpdate = term_declaration_acceptRepository.findAll().size();

        // Create the Term_declaration_accept
        Term_declaration_acceptDTO term_declaration_acceptDTO = term_declaration_acceptMapper.term_declaration_acceptToTerm_declaration_acceptDTO(term_declaration_accept);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTerm_declaration_acceptMockMvc.perform(put("/api/term-declaration-accepts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(term_declaration_acceptDTO)))
            .andExpect(status().isCreated());

        // Validate the Term_declaration_accept in the database
        List<Term_declaration_accept> term_declaration_acceptList = term_declaration_acceptRepository.findAll();
        assertThat(term_declaration_acceptList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteTerm_declaration_accept() throws Exception {
        // Initialize the database
        term_declaration_acceptRepository.save(term_declaration_accept);
        int databaseSizeBeforeDelete = term_declaration_acceptRepository.findAll().size();

        // Get the term_declaration_accept
        restTerm_declaration_acceptMockMvc.perform(delete("/api/term-declaration-accepts/{id}", term_declaration_accept.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Term_declaration_accept> term_declaration_acceptList = term_declaration_acceptRepository.findAll();
        assertThat(term_declaration_acceptList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Term_declaration_accept.class);
    }
}
