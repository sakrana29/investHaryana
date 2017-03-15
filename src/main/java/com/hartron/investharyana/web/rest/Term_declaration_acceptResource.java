package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Term_declaration_acceptService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Term_declaration_acceptDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Term_declaration_accept.
 */
@RestController
@RequestMapping("/api")
public class Term_declaration_acceptResource {

    private final Logger log = LoggerFactory.getLogger(Term_declaration_acceptResource.class);

    private static final String ENTITY_NAME = "term_declaration_accept";
        
    private final Term_declaration_acceptService term_declaration_acceptService;

    public Term_declaration_acceptResource(Term_declaration_acceptService term_declaration_acceptService) {
        this.term_declaration_acceptService = term_declaration_acceptService;
    }

    /**
     * POST  /term-declaration-accepts : Create a new term_declaration_accept.
     *
     * @param term_declaration_acceptDTO the term_declaration_acceptDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new term_declaration_acceptDTO, or with status 400 (Bad Request) if the term_declaration_accept has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/term-declaration-accepts")
    @Timed
    public ResponseEntity<Term_declaration_acceptDTO> createTerm_declaration_accept(@RequestBody Term_declaration_acceptDTO term_declaration_acceptDTO) throws URISyntaxException {
        log.debug("REST request to save Term_declaration_accept : {}", term_declaration_acceptDTO);
        if (term_declaration_acceptDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new term_declaration_accept cannot already have an ID")).body(null);
        }
        Term_declaration_acceptDTO result = term_declaration_acceptService.save(term_declaration_acceptDTO);
        return ResponseEntity.created(new URI("/api/term-declaration-accepts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /term-declaration-accepts : Updates an existing term_declaration_accept.
     *
     * @param term_declaration_acceptDTO the term_declaration_acceptDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated term_declaration_acceptDTO,
     * or with status 400 (Bad Request) if the term_declaration_acceptDTO is not valid,
     * or with status 500 (Internal Server Error) if the term_declaration_acceptDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/term-declaration-accepts")
    @Timed
    public ResponseEntity<Term_declaration_acceptDTO> updateTerm_declaration_accept(@RequestBody Term_declaration_acceptDTO term_declaration_acceptDTO) throws URISyntaxException {
        log.debug("REST request to update Term_declaration_accept : {}", term_declaration_acceptDTO);
        if (term_declaration_acceptDTO.getId() == null) {
            return createTerm_declaration_accept(term_declaration_acceptDTO);
        }
        Term_declaration_acceptDTO result = term_declaration_acceptService.save(term_declaration_acceptDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, term_declaration_acceptDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /term-declaration-accepts : get all the term_declaration_accepts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of term_declaration_accepts in body
     */
    @GetMapping("/term-declaration-accepts")
    @Timed
    public List<Term_declaration_acceptDTO> getAllTerm_declaration_accepts() {
        log.debug("REST request to get all Term_declaration_accepts");
        return term_declaration_acceptService.findAll();
    }

    /**
     * GET  /term-declaration-accepts/:id : get the "id" term_declaration_accept.
     *
     * @param id the id of the term_declaration_acceptDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the term_declaration_acceptDTO, or with status 404 (Not Found)
     */
    @GetMapping("/term-declaration-accepts/{id}")
    @Timed
    public ResponseEntity<Term_declaration_acceptDTO> getTerm_declaration_accept(@PathVariable String id) {
        log.debug("REST request to get Term_declaration_accept : {}", id);
        Term_declaration_acceptDTO term_declaration_acceptDTO = term_declaration_acceptService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(term_declaration_acceptDTO));
    }

    /**
     * DELETE  /term-declaration-accepts/:id : delete the "id" term_declaration_accept.
     *
     * @param id the id of the term_declaration_acceptDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/term-declaration-accepts/{id}")
    @Timed
    public ResponseEntity<Void> deleteTerm_declaration_accept(@PathVariable String id) {
        log.debug("REST request to delete Term_declaration_accept : {}", id);
        term_declaration_acceptService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
