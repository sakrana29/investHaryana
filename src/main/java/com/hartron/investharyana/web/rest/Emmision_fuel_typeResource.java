package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Emmision_fuel_typeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Emmision_fuel_typeDTO;
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
 * REST controller for managing Emmision_fuel_type.
 */
@RestController
@RequestMapping("/api")
public class Emmision_fuel_typeResource {

    private final Logger log = LoggerFactory.getLogger(Emmision_fuel_typeResource.class);

    private static final String ENTITY_NAME = "emmision_fuel_type";
        
    private final Emmision_fuel_typeService emmision_fuel_typeService;

    public Emmision_fuel_typeResource(Emmision_fuel_typeService emmision_fuel_typeService) {
        this.emmision_fuel_typeService = emmision_fuel_typeService;
    }

    /**
     * POST  /emmision-fuel-types : Create a new emmision_fuel_type.
     *
     * @param emmision_fuel_typeDTO the emmision_fuel_typeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emmision_fuel_typeDTO, or with status 400 (Bad Request) if the emmision_fuel_type has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emmision-fuel-types")
    @Timed
    public ResponseEntity<Emmision_fuel_typeDTO> createEmmision_fuel_type(@RequestBody Emmision_fuel_typeDTO emmision_fuel_typeDTO) throws URISyntaxException {
        log.debug("REST request to save Emmision_fuel_type : {}", emmision_fuel_typeDTO);
        if (emmision_fuel_typeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new emmision_fuel_type cannot already have an ID")).body(null);
        }
        Emmision_fuel_typeDTO result = emmision_fuel_typeService.save(emmision_fuel_typeDTO);
        return ResponseEntity.created(new URI("/api/emmision-fuel-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emmision-fuel-types : Updates an existing emmision_fuel_type.
     *
     * @param emmision_fuel_typeDTO the emmision_fuel_typeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emmision_fuel_typeDTO,
     * or with status 400 (Bad Request) if the emmision_fuel_typeDTO is not valid,
     * or with status 500 (Internal Server Error) if the emmision_fuel_typeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emmision-fuel-types")
    @Timed
    public ResponseEntity<Emmision_fuel_typeDTO> updateEmmision_fuel_type(@RequestBody Emmision_fuel_typeDTO emmision_fuel_typeDTO) throws URISyntaxException {
        log.debug("REST request to update Emmision_fuel_type : {}", emmision_fuel_typeDTO);
        if (emmision_fuel_typeDTO.getId() == null) {
            return createEmmision_fuel_type(emmision_fuel_typeDTO);
        }
        Emmision_fuel_typeDTO result = emmision_fuel_typeService.save(emmision_fuel_typeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emmision_fuel_typeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /emmision-fuel-types : get all the emmision_fuel_types.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emmision_fuel_types in body
     */
    @GetMapping("/emmision-fuel-types")
    @Timed
    public List<Emmision_fuel_typeDTO> getAllEmmision_fuel_types() {
        log.debug("REST request to get all Emmision_fuel_types");
        return emmision_fuel_typeService.findAll();
    }

    /**
     * GET  /emmision-fuel-types/:id : get the "id" emmision_fuel_type.
     *
     * @param id the id of the emmision_fuel_typeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emmision_fuel_typeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/emmision-fuel-types/{id}")
    @Timed
    public ResponseEntity<Emmision_fuel_typeDTO> getEmmision_fuel_type(@PathVariable String id) {
        log.debug("REST request to get Emmision_fuel_type : {}", id);
        Emmision_fuel_typeDTO emmision_fuel_typeDTO = emmision_fuel_typeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emmision_fuel_typeDTO));
    }

    /**
     * DELETE  /emmision-fuel-types/:id : delete the "id" emmision_fuel_type.
     *
     * @param id the id of the emmision_fuel_typeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emmision-fuel-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmmision_fuel_type(@PathVariable String id) {
        log.debug("REST request to delete Emmision_fuel_type : {}", id);
        emmision_fuel_typeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
