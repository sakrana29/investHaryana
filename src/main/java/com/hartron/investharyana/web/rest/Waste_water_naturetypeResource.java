package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Waste_water_naturetypeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Waste_water_naturetypeDTO;
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
 * REST controller for managing Waste_water_naturetype.
 */
@RestController
@RequestMapping("/api")
public class Waste_water_naturetypeResource {

    private final Logger log = LoggerFactory.getLogger(Waste_water_naturetypeResource.class);

    private static final String ENTITY_NAME = "waste_water_naturetype";
        
    private final Waste_water_naturetypeService waste_water_naturetypeService;

    public Waste_water_naturetypeResource(Waste_water_naturetypeService waste_water_naturetypeService) {
        this.waste_water_naturetypeService = waste_water_naturetypeService;
    }

    /**
     * POST  /waste-water-naturetypes : Create a new waste_water_naturetype.
     *
     * @param waste_water_naturetypeDTO the waste_water_naturetypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new waste_water_naturetypeDTO, or with status 400 (Bad Request) if the waste_water_naturetype has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/waste-water-naturetypes")
    @Timed
    public ResponseEntity<Waste_water_naturetypeDTO> createWaste_water_naturetype(@Valid @RequestBody Waste_water_naturetypeDTO waste_water_naturetypeDTO) throws URISyntaxException {
        log.debug("REST request to save Waste_water_naturetype : {}", waste_water_naturetypeDTO);
        if (waste_water_naturetypeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new waste_water_naturetype cannot already have an ID")).body(null);
        }
        Waste_water_naturetypeDTO result = waste_water_naturetypeService.save(waste_water_naturetypeDTO);
        return ResponseEntity.created(new URI("/api/waste-water-naturetypes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /waste-water-naturetypes : Updates an existing waste_water_naturetype.
     *
     * @param waste_water_naturetypeDTO the waste_water_naturetypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated waste_water_naturetypeDTO,
     * or with status 400 (Bad Request) if the waste_water_naturetypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the waste_water_naturetypeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/waste-water-naturetypes")
    @Timed
    public ResponseEntity<Waste_water_naturetypeDTO> updateWaste_water_naturetype(@Valid @RequestBody Waste_water_naturetypeDTO waste_water_naturetypeDTO) throws URISyntaxException {
        log.debug("REST request to update Waste_water_naturetype : {}", waste_water_naturetypeDTO);
        if (waste_water_naturetypeDTO.getId() == null) {
            return createWaste_water_naturetype(waste_water_naturetypeDTO);
        }
        Waste_water_naturetypeDTO result = waste_water_naturetypeService.save(waste_water_naturetypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, waste_water_naturetypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /waste-water-naturetypes : get all the waste_water_naturetypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of waste_water_naturetypes in body
     */
    @GetMapping("/waste-water-naturetypes")
    @Timed
    public List<Waste_water_naturetypeDTO> getAllWaste_water_naturetypes() {
        log.debug("REST request to get all Waste_water_naturetypes");
        return waste_water_naturetypeService.findAll();
    }

    /**
     * GET  /waste-water-naturetypes/:id : get the "id" waste_water_naturetype.
     *
     * @param id the id of the waste_water_naturetypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the waste_water_naturetypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/waste-water-naturetypes/{id}")
    @Timed
    public ResponseEntity<Waste_water_naturetypeDTO> getWaste_water_naturetype(@PathVariable String id) {
        log.debug("REST request to get Waste_water_naturetype : {}", id);
        Waste_water_naturetypeDTO waste_water_naturetypeDTO = waste_water_naturetypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(waste_water_naturetypeDTO));
    }

    /**
     * DELETE  /waste-water-naturetypes/:id : delete the "id" waste_water_naturetype.
     *
     * @param id the id of the waste_water_naturetypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/waste-water-naturetypes/{id}")
    @Timed
    public ResponseEntity<Void> deleteWaste_water_naturetype(@PathVariable String id) {
        log.debug("REST request to delete Waste_water_naturetype : {}", id);
        waste_water_naturetypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
