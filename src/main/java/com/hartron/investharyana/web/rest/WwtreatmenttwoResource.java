package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.WwtreatmenttwoService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.WwtreatmenttwoDTO;
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
 * REST controller for managing Wwtreatmenttwo.
 */
@RestController
@RequestMapping("/api")
public class WwtreatmenttwoResource {

    private final Logger log = LoggerFactory.getLogger(WwtreatmenttwoResource.class);

    private static final String ENTITY_NAME = "wwtreatmenttwo";
        
    private final WwtreatmenttwoService wwtreatmenttwoService;

    public WwtreatmenttwoResource(WwtreatmenttwoService wwtreatmenttwoService) {
        this.wwtreatmenttwoService = wwtreatmenttwoService;
    }

    /**
     * POST  /wwtreatmenttwos : Create a new wwtreatmenttwo.
     *
     * @param wwtreatmenttwoDTO the wwtreatmenttwoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wwtreatmenttwoDTO, or with status 400 (Bad Request) if the wwtreatmenttwo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wwtreatmenttwos")
    @Timed
    public ResponseEntity<WwtreatmenttwoDTO> createWwtreatmenttwo(@RequestBody WwtreatmenttwoDTO wwtreatmenttwoDTO) throws URISyntaxException {
        log.debug("REST request to save Wwtreatmenttwo : {}", wwtreatmenttwoDTO);
        if (wwtreatmenttwoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new wwtreatmenttwo cannot already have an ID")).body(null);
        }
        WwtreatmenttwoDTO result = wwtreatmenttwoService.save(wwtreatmenttwoDTO);
        return ResponseEntity.created(new URI("/api/wwtreatmenttwos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wwtreatmenttwos : Updates an existing wwtreatmenttwo.
     *
     * @param wwtreatmenttwoDTO the wwtreatmenttwoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wwtreatmenttwoDTO,
     * or with status 400 (Bad Request) if the wwtreatmenttwoDTO is not valid,
     * or with status 500 (Internal Server Error) if the wwtreatmenttwoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wwtreatmenttwos")
    @Timed
    public ResponseEntity<WwtreatmenttwoDTO> updateWwtreatmenttwo(@RequestBody WwtreatmenttwoDTO wwtreatmenttwoDTO) throws URISyntaxException {
        log.debug("REST request to update Wwtreatmenttwo : {}", wwtreatmenttwoDTO);
        if (wwtreatmenttwoDTO.getId() == null) {
            return createWwtreatmenttwo(wwtreatmenttwoDTO);
        }
        WwtreatmenttwoDTO result = wwtreatmenttwoService.save(wwtreatmenttwoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wwtreatmenttwoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wwtreatmenttwos : get all the wwtreatmenttwos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wwtreatmenttwos in body
     */
    @GetMapping("/wwtreatmenttwos")
    @Timed
    public List<WwtreatmenttwoDTO> getAllWwtreatmenttwos() {
        log.debug("REST request to get all Wwtreatmenttwos");
        return wwtreatmenttwoService.findAll();
    }

    /**
     * GET  /wwtreatmenttwos/:id : get the "id" wwtreatmenttwo.
     *
     * @param id the id of the wwtreatmenttwoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wwtreatmenttwoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wwtreatmenttwos/{id}")
    @Timed
    public ResponseEntity<WwtreatmenttwoDTO> getWwtreatmenttwo(@PathVariable String id) {
        log.debug("REST request to get Wwtreatmenttwo : {}", id);
        WwtreatmenttwoDTO wwtreatmenttwoDTO = wwtreatmenttwoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wwtreatmenttwoDTO));
    }

    /**
     * DELETE  /wwtreatmenttwos/:id : delete the "id" wwtreatmenttwo.
     *
     * @param id the id of the wwtreatmenttwoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wwtreatmenttwos/{id}")
    @Timed
    public ResponseEntity<Void> deleteWwtreatmenttwo(@PathVariable String id) {
        log.debug("REST request to delete Wwtreatmenttwo : {}", id);
        wwtreatmenttwoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
