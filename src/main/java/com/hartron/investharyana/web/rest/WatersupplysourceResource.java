package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.WatersupplysourceService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.WatersupplysourceDTO;
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
 * REST controller for managing Watersupplysource.
 */
@RestController
@RequestMapping("/api")
public class WatersupplysourceResource {

    private final Logger log = LoggerFactory.getLogger(WatersupplysourceResource.class);

    private static final String ENTITY_NAME = "watersupplysource";
        
    private final WatersupplysourceService watersupplysourceService;

    public WatersupplysourceResource(WatersupplysourceService watersupplysourceService) {
        this.watersupplysourceService = watersupplysourceService;
    }

    /**
     * POST  /watersupplysources : Create a new watersupplysource.
     *
     * @param watersupplysourceDTO the watersupplysourceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new watersupplysourceDTO, or with status 400 (Bad Request) if the watersupplysource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/watersupplysources")
    @Timed
    public ResponseEntity<WatersupplysourceDTO> createWatersupplysource(@RequestBody WatersupplysourceDTO watersupplysourceDTO) throws URISyntaxException {
        log.debug("REST request to save Watersupplysource : {}", watersupplysourceDTO);
        if (watersupplysourceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new watersupplysource cannot already have an ID")).body(null);
        }
        WatersupplysourceDTO result = watersupplysourceService.save(watersupplysourceDTO);
        return ResponseEntity.created(new URI("/api/watersupplysources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /watersupplysources : Updates an existing watersupplysource.
     *
     * @param watersupplysourceDTO the watersupplysourceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated watersupplysourceDTO,
     * or with status 400 (Bad Request) if the watersupplysourceDTO is not valid,
     * or with status 500 (Internal Server Error) if the watersupplysourceDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/watersupplysources")
    @Timed
    public ResponseEntity<WatersupplysourceDTO> updateWatersupplysource(@RequestBody WatersupplysourceDTO watersupplysourceDTO) throws URISyntaxException {
        log.debug("REST request to update Watersupplysource : {}", watersupplysourceDTO);
        if (watersupplysourceDTO.getId() == null) {
            return createWatersupplysource(watersupplysourceDTO);
        }
        WatersupplysourceDTO result = watersupplysourceService.save(watersupplysourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, watersupplysourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /watersupplysources : get all the watersupplysources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of watersupplysources in body
     */
    @GetMapping("/watersupplysources")
    @Timed
    public List<WatersupplysourceDTO> getAllWatersupplysources() {
        log.debug("REST request to get all Watersupplysources");
        return watersupplysourceService.findAll();
    }

    /**
     * GET  /watersupplysources/:id : get the "id" watersupplysource.
     *
     * @param id the id of the watersupplysourceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the watersupplysourceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/watersupplysources/{id}")
    @Timed
    public ResponseEntity<WatersupplysourceDTO> getWatersupplysource(@PathVariable String id) {
        log.debug("REST request to get Watersupplysource : {}", id);
        WatersupplysourceDTO watersupplysourceDTO = watersupplysourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(watersupplysourceDTO));
    }

    /**
     * DELETE  /watersupplysources/:id : delete the "id" watersupplysource.
     *
     * @param id the id of the watersupplysourceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/watersupplysources/{id}")
    @Timed
    public ResponseEntity<Void> deleteWatersupplysource(@PathVariable String id) {
        log.debug("REST request to delete Watersupplysource : {}", id);
        watersupplysourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
