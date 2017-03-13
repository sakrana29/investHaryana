package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Environment_impactdetailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Environment_impactdetailDTO;
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
 * REST controller for managing Environment_impactdetail.
 */
@RestController
@RequestMapping("/api")
public class Environment_impactdetailResource {

    private final Logger log = LoggerFactory.getLogger(Environment_impactdetailResource.class);

    private static final String ENTITY_NAME = "environment_impactdetail";
        
    private final Environment_impactdetailService environment_impactdetailService;

    public Environment_impactdetailResource(Environment_impactdetailService environment_impactdetailService) {
        this.environment_impactdetailService = environment_impactdetailService;
    }

    /**
     * POST  /environment-impactdetails : Create a new environment_impactdetail.
     *
     * @param environment_impactdetailDTO the environment_impactdetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new environment_impactdetailDTO, or with status 400 (Bad Request) if the environment_impactdetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/environment-impactdetails")
    @Timed
    public ResponseEntity<Environment_impactdetailDTO> createEnvironment_impactdetail(@RequestBody Environment_impactdetailDTO environment_impactdetailDTO) throws URISyntaxException {
        log.debug("REST request to save Environment_impactdetail : {}", environment_impactdetailDTO);
        if (environment_impactdetailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new environment_impactdetail cannot already have an ID")).body(null);
        }
        Environment_impactdetailDTO result = environment_impactdetailService.save(environment_impactdetailDTO);
        return ResponseEntity.created(new URI("/api/environment-impactdetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /environment-impactdetails : Updates an existing environment_impactdetail.
     *
     * @param environment_impactdetailDTO the environment_impactdetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated environment_impactdetailDTO,
     * or with status 400 (Bad Request) if the environment_impactdetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the environment_impactdetailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/environment-impactdetails")
    @Timed
    public ResponseEntity<Environment_impactdetailDTO> updateEnvironment_impactdetail(@RequestBody Environment_impactdetailDTO environment_impactdetailDTO) throws URISyntaxException {
        log.debug("REST request to update Environment_impactdetail : {}", environment_impactdetailDTO);
        if (environment_impactdetailDTO.getId() == null) {
            return createEnvironment_impactdetail(environment_impactdetailDTO);
        }
        Environment_impactdetailDTO result = environment_impactdetailService.save(environment_impactdetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, environment_impactdetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /environment-impactdetails : get all the environment_impactdetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of environment_impactdetails in body
     */
    @GetMapping("/environment-impactdetails")
    @Timed
    public List<Environment_impactdetailDTO> getAllEnvironment_impactdetails() {
        log.debug("REST request to get all Environment_impactdetails");
        return environment_impactdetailService.findAll();
    }

    /**
     * GET  /environment-impactdetails/:id : get the "id" environment_impactdetail.
     *
     * @param id the id of the environment_impactdetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the environment_impactdetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/environment-impactdetails/{id}")
    @Timed
    public ResponseEntity<Environment_impactdetailDTO> getEnvironment_impactdetail(@PathVariable String id) {
        log.debug("REST request to get Environment_impactdetail : {}", id);
        Environment_impactdetailDTO environment_impactdetailDTO = environment_impactdetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(environment_impactdetailDTO));
    }

    /**
     * DELETE  /environment-impactdetails/:id : delete the "id" environment_impactdetail.
     *
     * @param id the id of the environment_impactdetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/environment-impactdetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnvironment_impactdetail(@PathVariable String id) {
        log.debug("REST request to delete Environment_impactdetail : {}", id);
        environment_impactdetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
