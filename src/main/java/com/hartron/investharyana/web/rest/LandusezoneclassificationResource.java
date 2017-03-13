package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.LandusezoneclassificationService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.LandusezoneclassificationDTO;
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
 * REST controller for managing Landusezoneclassification.
 */
@RestController
@RequestMapping("/api")
public class LandusezoneclassificationResource {

    private final Logger log = LoggerFactory.getLogger(LandusezoneclassificationResource.class);

    private static final String ENTITY_NAME = "landusezoneclassification";
        
    private final LandusezoneclassificationService landusezoneclassificationService;

    public LandusezoneclassificationResource(LandusezoneclassificationService landusezoneclassificationService) {
        this.landusezoneclassificationService = landusezoneclassificationService;
    }

    /**
     * POST  /landusezoneclassifications : Create a new landusezoneclassification.
     *
     * @param landusezoneclassificationDTO the landusezoneclassificationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new landusezoneclassificationDTO, or with status 400 (Bad Request) if the landusezoneclassification has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/landusezoneclassifications")
    @Timed
    public ResponseEntity<LandusezoneclassificationDTO> createLandusezoneclassification(@RequestBody LandusezoneclassificationDTO landusezoneclassificationDTO) throws URISyntaxException {
        log.debug("REST request to save Landusezoneclassification : {}", landusezoneclassificationDTO);
        if (landusezoneclassificationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new landusezoneclassification cannot already have an ID")).body(null);
        }
        LandusezoneclassificationDTO result = landusezoneclassificationService.save(landusezoneclassificationDTO);
        return ResponseEntity.created(new URI("/api/landusezoneclassifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /landusezoneclassifications : Updates an existing landusezoneclassification.
     *
     * @param landusezoneclassificationDTO the landusezoneclassificationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated landusezoneclassificationDTO,
     * or with status 400 (Bad Request) if the landusezoneclassificationDTO is not valid,
     * or with status 500 (Internal Server Error) if the landusezoneclassificationDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/landusezoneclassifications")
    @Timed
    public ResponseEntity<LandusezoneclassificationDTO> updateLandusezoneclassification(@RequestBody LandusezoneclassificationDTO landusezoneclassificationDTO) throws URISyntaxException {
        log.debug("REST request to update Landusezoneclassification : {}", landusezoneclassificationDTO);
        if (landusezoneclassificationDTO.getId() == null) {
            return createLandusezoneclassification(landusezoneclassificationDTO);
        }
        LandusezoneclassificationDTO result = landusezoneclassificationService.save(landusezoneclassificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, landusezoneclassificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /landusezoneclassifications : get all the landusezoneclassifications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of landusezoneclassifications in body
     */
    @GetMapping("/landusezoneclassifications")
    @Timed
    public List<LandusezoneclassificationDTO> getAllLandusezoneclassifications() {
        log.debug("REST request to get all Landusezoneclassifications");
        return landusezoneclassificationService.findAll();
    }

    /**
     * GET  /landusezoneclassifications/:id : get the "id" landusezoneclassification.
     *
     * @param id the id of the landusezoneclassificationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the landusezoneclassificationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/landusezoneclassifications/{id}")
    @Timed
    public ResponseEntity<LandusezoneclassificationDTO> getLandusezoneclassification(@PathVariable String id) {
        log.debug("REST request to get Landusezoneclassification : {}", id);
        LandusezoneclassificationDTO landusezoneclassificationDTO = landusezoneclassificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(landusezoneclassificationDTO));
    }

    /**
     * DELETE  /landusezoneclassifications/:id : delete the "id" landusezoneclassification.
     *
     * @param id the id of the landusezoneclassificationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/landusezoneclassifications/{id}")
    @Timed
    public ResponseEntity<Void> deleteLandusezoneclassification(@PathVariable String id) {
        log.debug("REST request to delete Landusezoneclassification : {}", id);
        landusezoneclassificationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
