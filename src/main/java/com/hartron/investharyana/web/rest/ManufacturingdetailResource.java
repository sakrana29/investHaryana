package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ManufacturingdetailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ManufacturingdetailDTO;
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
 * REST controller for managing Manufacturingdetail.
 */
@RestController
@RequestMapping("/api")
public class ManufacturingdetailResource {

    private final Logger log = LoggerFactory.getLogger(ManufacturingdetailResource.class);

    private static final String ENTITY_NAME = "manufacturingdetail";
        
    private final ManufacturingdetailService manufacturingdetailService;

    public ManufacturingdetailResource(ManufacturingdetailService manufacturingdetailService) {
        this.manufacturingdetailService = manufacturingdetailService;
    }

    /**
     * POST  /manufacturingdetails : Create a new manufacturingdetail.
     *
     * @param manufacturingdetailDTO the manufacturingdetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new manufacturingdetailDTO, or with status 400 (Bad Request) if the manufacturingdetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/manufacturingdetails")
    @Timed
    public ResponseEntity<ManufacturingdetailDTO> createManufacturingdetail(@RequestBody ManufacturingdetailDTO manufacturingdetailDTO) throws URISyntaxException {
        log.debug("REST request to save Manufacturingdetail : {}", manufacturingdetailDTO);
        if (manufacturingdetailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new manufacturingdetail cannot already have an ID")).body(null);
        }
        ManufacturingdetailDTO result = manufacturingdetailService.save(manufacturingdetailDTO);
        return ResponseEntity.created(new URI("/api/manufacturingdetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /manufacturingdetails : Updates an existing manufacturingdetail.
     *
     * @param manufacturingdetailDTO the manufacturingdetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated manufacturingdetailDTO,
     * or with status 400 (Bad Request) if the manufacturingdetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the manufacturingdetailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/manufacturingdetails")
    @Timed
    public ResponseEntity<ManufacturingdetailDTO> updateManufacturingdetail(@RequestBody ManufacturingdetailDTO manufacturingdetailDTO) throws URISyntaxException {
        log.debug("REST request to update Manufacturingdetail : {}", manufacturingdetailDTO);
        if (manufacturingdetailDTO.getId() == null) {
            return createManufacturingdetail(manufacturingdetailDTO);
        }
        ManufacturingdetailDTO result = manufacturingdetailService.save(manufacturingdetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, manufacturingdetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /manufacturingdetails : get all the manufacturingdetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of manufacturingdetails in body
     */
    @GetMapping("/manufacturingdetails")
    @Timed
    public List<ManufacturingdetailDTO> getAllManufacturingdetails() {
        log.debug("REST request to get all Manufacturingdetails");
        return manufacturingdetailService.findAll();
    }

    /**
     * GET  /manufacturingdetails/:id : get the "id" manufacturingdetail.
     *
     * @param id the id of the manufacturingdetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the manufacturingdetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/manufacturingdetails/{id}")
    @Timed
    public ResponseEntity<ManufacturingdetailDTO> getManufacturingdetail(@PathVariable String id) {
        log.debug("REST request to get Manufacturingdetail : {}", id);
        ManufacturingdetailDTO manufacturingdetailDTO = manufacturingdetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(manufacturingdetailDTO));
    }

    /**
     * DELETE  /manufacturingdetails/:id : delete the "id" manufacturingdetail.
     *
     * @param id the id of the manufacturingdetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/manufacturingdetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteManufacturingdetail(@PathVariable String id) {
        log.debug("REST request to delete Manufacturingdetail : {}", id);
        manufacturingdetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
