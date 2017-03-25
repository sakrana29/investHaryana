package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.WwtreatmentthreeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.WwtreatmentthreeDTO;
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
 * REST controller for managing Wwtreatmentthree.
 */
@RestController
@RequestMapping("/api")
public class WwtreatmentthreeResource {

    private final Logger log = LoggerFactory.getLogger(WwtreatmentthreeResource.class);

    private static final String ENTITY_NAME = "wwtreatmentthree";
        
    private final WwtreatmentthreeService wwtreatmentthreeService;

    public WwtreatmentthreeResource(WwtreatmentthreeService wwtreatmentthreeService) {
        this.wwtreatmentthreeService = wwtreatmentthreeService;
    }

    /**
     * POST  /wwtreatmentthrees : Create a new wwtreatmentthree.
     *
     * @param wwtreatmentthreeDTO the wwtreatmentthreeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wwtreatmentthreeDTO, or with status 400 (Bad Request) if the wwtreatmentthree has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wwtreatmentthrees")
    @Timed
    public ResponseEntity<WwtreatmentthreeDTO> createWwtreatmentthree(@RequestBody WwtreatmentthreeDTO wwtreatmentthreeDTO) throws URISyntaxException {
        log.debug("REST request to save Wwtreatmentthree : {}", wwtreatmentthreeDTO);
        if (wwtreatmentthreeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new wwtreatmentthree cannot already have an ID")).body(null);
        }
        WwtreatmentthreeDTO result = wwtreatmentthreeService.save(wwtreatmentthreeDTO);
        return ResponseEntity.created(new URI("/api/wwtreatmentthrees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wwtreatmentthrees : Updates an existing wwtreatmentthree.
     *
     * @param wwtreatmentthreeDTO the wwtreatmentthreeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wwtreatmentthreeDTO,
     * or with status 400 (Bad Request) if the wwtreatmentthreeDTO is not valid,
     * or with status 500 (Internal Server Error) if the wwtreatmentthreeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wwtreatmentthrees")
    @Timed
    public ResponseEntity<WwtreatmentthreeDTO> updateWwtreatmentthree(@RequestBody WwtreatmentthreeDTO wwtreatmentthreeDTO) throws URISyntaxException {
        log.debug("REST request to update Wwtreatmentthree : {}", wwtreatmentthreeDTO);
        if (wwtreatmentthreeDTO.getId() == null) {
            return createWwtreatmentthree(wwtreatmentthreeDTO);
        }
        WwtreatmentthreeDTO result = wwtreatmentthreeService.save(wwtreatmentthreeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wwtreatmentthreeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wwtreatmentthrees : get all the wwtreatmentthrees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wwtreatmentthrees in body
     */
    @GetMapping("/wwtreatmentthrees")
    @Timed
    public List<WwtreatmentthreeDTO> getAllWwtreatmentthrees() {
        log.debug("REST request to get all Wwtreatmentthrees");
        return wwtreatmentthreeService.findAll();
    }

    /**
     * GET  /wwtreatmentthrees/:id : get the "id" wwtreatmentthree.
     *
     * @param id the id of the wwtreatmentthreeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wwtreatmentthreeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wwtreatmentthrees/{id}")
    @Timed
    public ResponseEntity<WwtreatmentthreeDTO> getWwtreatmentthree(@PathVariable String id) {
        log.debug("REST request to get Wwtreatmentthree : {}", id);
        WwtreatmentthreeDTO wwtreatmentthreeDTO = wwtreatmentthreeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wwtreatmentthreeDTO));
    }

    /**
     * DELETE  /wwtreatmentthrees/:id : delete the "id" wwtreatmentthree.
     *
     * @param id the id of the wwtreatmentthreeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wwtreatmentthrees/{id}")
    @Timed
    public ResponseEntity<Void> deleteWwtreatmentthree(@PathVariable String id) {
        log.debug("REST request to delete Wwtreatmentthree : {}", id);
        wwtreatmentthreeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
