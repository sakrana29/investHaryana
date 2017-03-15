package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ParticularService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ParticularDTO;
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
 * REST controller for managing Particular.
 */
@RestController
@RequestMapping("/api")
public class ParticularResource {

    private final Logger log = LoggerFactory.getLogger(ParticularResource.class);

    private static final String ENTITY_NAME = "particular";
        
    private final ParticularService particularService;

    public ParticularResource(ParticularService particularService) {
        this.particularService = particularService;
    }

    /**
     * POST  /particulars : Create a new particular.
     *
     * @param particularDTO the particularDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new particularDTO, or with status 400 (Bad Request) if the particular has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/particulars")
    @Timed
    public ResponseEntity<ParticularDTO> createParticular(@RequestBody ParticularDTO particularDTO) throws URISyntaxException {
        log.debug("REST request to save Particular : {}", particularDTO);
        if (particularDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new particular cannot already have an ID")).body(null);
        }
        ParticularDTO result = particularService.save(particularDTO);
        return ResponseEntity.created(new URI("/api/particulars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /particulars : Updates an existing particular.
     *
     * @param particularDTO the particularDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated particularDTO,
     * or with status 400 (Bad Request) if the particularDTO is not valid,
     * or with status 500 (Internal Server Error) if the particularDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/particulars")
    @Timed
    public ResponseEntity<ParticularDTO> updateParticular(@RequestBody ParticularDTO particularDTO) throws URISyntaxException {
        log.debug("REST request to update Particular : {}", particularDTO);
        if (particularDTO.getId() == null) {
            return createParticular(particularDTO);
        }
        ParticularDTO result = particularService.save(particularDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, particularDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /particulars : get all the particulars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of particulars in body
     */
    @GetMapping("/particulars")
    @Timed
    public List<ParticularDTO> getAllParticulars() {
        log.debug("REST request to get all Particulars");
        return particularService.findAll();
    }

    /**
     * GET  /particulars/:id : get the "id" particular.
     *
     * @param id the id of the particularDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the particularDTO, or with status 404 (Not Found)
     */
    @GetMapping("/particulars/{id}")
    @Timed
    public ResponseEntity<ParticularDTO> getParticular(@PathVariable String id) {
        log.debug("REST request to get Particular : {}", id);
        ParticularDTO particularDTO = particularService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(particularDTO));
    }

    /**
     * DELETE  /particulars/:id : delete the "id" particular.
     *
     * @param id the id of the particularDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/particulars/{id}")
    @Timed
    public ResponseEntity<Void> deleteParticular(@PathVariable String id) {
        log.debug("REST request to delete Particular : {}", id);
        particularService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
