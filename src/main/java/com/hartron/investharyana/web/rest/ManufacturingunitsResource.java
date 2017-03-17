package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ManufacturingunitsService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ManufacturingunitsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Manufacturingunits.
 */
@RestController
@RequestMapping("/api")
public class ManufacturingunitsResource {

    private final Logger log = LoggerFactory.getLogger(ManufacturingunitsResource.class);

    private static final String ENTITY_NAME = "manufacturingunits";
        
    private final ManufacturingunitsService manufacturingunitsService;

    public ManufacturingunitsResource(ManufacturingunitsService manufacturingunitsService) {
        this.manufacturingunitsService = manufacturingunitsService;
    }

    /**
     * POST  /manufacturingunits : Create a new manufacturingunits.
     *
     * @param manufacturingunitsDTO the manufacturingunitsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new manufacturingunitsDTO, or with status 400 (Bad Request) if the manufacturingunits has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/manufacturingunits")
    @Timed
    public ResponseEntity<ManufacturingunitsDTO> createManufacturingunits(@Valid @RequestBody ManufacturingunitsDTO manufacturingunitsDTO) throws URISyntaxException {
        log.debug("REST request to save Manufacturingunits : {}", manufacturingunitsDTO);
        if (manufacturingunitsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new manufacturingunits cannot already have an ID")).body(null);
        }
        ManufacturingunitsDTO result = manufacturingunitsService.save(manufacturingunitsDTO);
        return ResponseEntity.created(new URI("/api/manufacturingunits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /manufacturingunits : Updates an existing manufacturingunits.
     *
     * @param manufacturingunitsDTO the manufacturingunitsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated manufacturingunitsDTO,
     * or with status 400 (Bad Request) if the manufacturingunitsDTO is not valid,
     * or with status 500 (Internal Server Error) if the manufacturingunitsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/manufacturingunits")
    @Timed
    public ResponseEntity<ManufacturingunitsDTO> updateManufacturingunits(@Valid @RequestBody ManufacturingunitsDTO manufacturingunitsDTO) throws URISyntaxException {
        log.debug("REST request to update Manufacturingunits : {}", manufacturingunitsDTO);
        if (manufacturingunitsDTO.getId() == null) {
            return createManufacturingunits(manufacturingunitsDTO);
        }
        ManufacturingunitsDTO result = manufacturingunitsService.save(manufacturingunitsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, manufacturingunitsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /manufacturingunits : get all the manufacturingunits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of manufacturingunits in body
     */
    @GetMapping("/manufacturingunits")
    @Timed
    public List<ManufacturingunitsDTO> getAllManufacturingunits() {
        log.debug("REST request to get all Manufacturingunits");
        return manufacturingunitsService.findAll();
    }

    /**
     * GET  /manufacturingunits/:id : get the "id" manufacturingunits.
     *
     * @param id the id of the manufacturingunitsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the manufacturingunitsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/manufacturingunits/{id}")
    @Timed
    public ResponseEntity<ManufacturingunitsDTO> getManufacturingunits(@PathVariable String id) {
        log.debug("REST request to get Manufacturingunits : {}", id);
        ManufacturingunitsDTO manufacturingunitsDTO = manufacturingunitsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(manufacturingunitsDTO));
    }

    /**
     * DELETE  /manufacturingunits/:id : delete the "id" manufacturingunits.
     *
     * @param id the id of the manufacturingunitsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/manufacturingunits/{id}")
    @Timed
    public ResponseEntity<Void> deleteManufacturingunits(@PathVariable String id) {
        log.debug("REST request to delete Manufacturingunits : {}", id);
        manufacturingunitsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
