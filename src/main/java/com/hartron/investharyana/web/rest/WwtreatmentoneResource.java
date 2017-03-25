package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.WwtreatmentoneService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.WwtreatmentoneDTO;
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
 * REST controller for managing Wwtreatmentone.
 */
@RestController
@RequestMapping("/api")
public class WwtreatmentoneResource {

    private final Logger log = LoggerFactory.getLogger(WwtreatmentoneResource.class);

    private static final String ENTITY_NAME = "wwtreatmentone";
        
    private final WwtreatmentoneService wwtreatmentoneService;

    public WwtreatmentoneResource(WwtreatmentoneService wwtreatmentoneService) {
        this.wwtreatmentoneService = wwtreatmentoneService;
    }

    /**
     * POST  /wwtreatmentones : Create a new wwtreatmentone.
     *
     * @param wwtreatmentoneDTO the wwtreatmentoneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wwtreatmentoneDTO, or with status 400 (Bad Request) if the wwtreatmentone has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wwtreatmentones")
    @Timed
    public ResponseEntity<WwtreatmentoneDTO> createWwtreatmentone(@RequestBody WwtreatmentoneDTO wwtreatmentoneDTO) throws URISyntaxException {
        log.debug("REST request to save Wwtreatmentone : {}", wwtreatmentoneDTO);
        if (wwtreatmentoneDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new wwtreatmentone cannot already have an ID")).body(null);
        }
        WwtreatmentoneDTO result = wwtreatmentoneService.save(wwtreatmentoneDTO);
        return ResponseEntity.created(new URI("/api/wwtreatmentones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wwtreatmentones : Updates an existing wwtreatmentone.
     *
     * @param wwtreatmentoneDTO the wwtreatmentoneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wwtreatmentoneDTO,
     * or with status 400 (Bad Request) if the wwtreatmentoneDTO is not valid,
     * or with status 500 (Internal Server Error) if the wwtreatmentoneDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wwtreatmentones")
    @Timed
    public ResponseEntity<WwtreatmentoneDTO> updateWwtreatmentone(@RequestBody WwtreatmentoneDTO wwtreatmentoneDTO) throws URISyntaxException {
        log.debug("REST request to update Wwtreatmentone : {}", wwtreatmentoneDTO);
        if (wwtreatmentoneDTO.getId() == null) {
            return createWwtreatmentone(wwtreatmentoneDTO);
        }
        WwtreatmentoneDTO result = wwtreatmentoneService.save(wwtreatmentoneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wwtreatmentoneDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wwtreatmentones : get all the wwtreatmentones.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wwtreatmentones in body
     */
    @GetMapping("/wwtreatmentones")
    @Timed
    public List<WwtreatmentoneDTO> getAllWwtreatmentones() {
        log.debug("REST request to get all Wwtreatmentones");
        return wwtreatmentoneService.findAll();
    }

    /**
     * GET  /wwtreatmentones/:id : get the "id" wwtreatmentone.
     *
     * @param id the id of the wwtreatmentoneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wwtreatmentoneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wwtreatmentones/{id}")
    @Timed
    public ResponseEntity<WwtreatmentoneDTO> getWwtreatmentone(@PathVariable String id) {
        log.debug("REST request to get Wwtreatmentone : {}", id);
        WwtreatmentoneDTO wwtreatmentoneDTO = wwtreatmentoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wwtreatmentoneDTO));
    }

    /**
     * DELETE  /wwtreatmentones/:id : delete the "id" wwtreatmentone.
     *
     * @param id the id of the wwtreatmentoneDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wwtreatmentones/{id}")
    @Timed
    public ResponseEntity<Void> deleteWwtreatmentone(@PathVariable String id) {
        log.debug("REST request to delete Wwtreatmentone : {}", id);
        wwtreatmentoneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
