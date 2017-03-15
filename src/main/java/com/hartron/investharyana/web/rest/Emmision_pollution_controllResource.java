package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Emmision_pollution_controllService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Emmision_pollution_controllDTO;
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
 * REST controller for managing Emmision_pollution_controll.
 */
@RestController
@RequestMapping("/api")
public class Emmision_pollution_controllResource {

    private final Logger log = LoggerFactory.getLogger(Emmision_pollution_controllResource.class);

    private static final String ENTITY_NAME = "emmision_pollution_controll";
        
    private final Emmision_pollution_controllService emmision_pollution_controllService;

    public Emmision_pollution_controllResource(Emmision_pollution_controllService emmision_pollution_controllService) {
        this.emmision_pollution_controllService = emmision_pollution_controllService;
    }

    /**
     * POST  /emmision-pollution-controlls : Create a new emmision_pollution_controll.
     *
     * @param emmision_pollution_controllDTO the emmision_pollution_controllDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emmision_pollution_controllDTO, or with status 400 (Bad Request) if the emmision_pollution_controll has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emmision-pollution-controlls")
    @Timed
    public ResponseEntity<Emmision_pollution_controllDTO> createEmmision_pollution_controll(@RequestBody Emmision_pollution_controllDTO emmision_pollution_controllDTO) throws URISyntaxException {
        log.debug("REST request to save Emmision_pollution_controll : {}", emmision_pollution_controllDTO);
        if (emmision_pollution_controllDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new emmision_pollution_controll cannot already have an ID")).body(null);
        }
        Emmision_pollution_controllDTO result = emmision_pollution_controllService.save(emmision_pollution_controllDTO);
        return ResponseEntity.created(new URI("/api/emmision-pollution-controlls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emmision-pollution-controlls : Updates an existing emmision_pollution_controll.
     *
     * @param emmision_pollution_controllDTO the emmision_pollution_controllDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emmision_pollution_controllDTO,
     * or with status 400 (Bad Request) if the emmision_pollution_controllDTO is not valid,
     * or with status 500 (Internal Server Error) if the emmision_pollution_controllDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emmision-pollution-controlls")
    @Timed
    public ResponseEntity<Emmision_pollution_controllDTO> updateEmmision_pollution_controll(@RequestBody Emmision_pollution_controllDTO emmision_pollution_controllDTO) throws URISyntaxException {
        log.debug("REST request to update Emmision_pollution_controll : {}", emmision_pollution_controllDTO);
        if (emmision_pollution_controllDTO.getId() == null) {
            return createEmmision_pollution_controll(emmision_pollution_controllDTO);
        }
        Emmision_pollution_controllDTO result = emmision_pollution_controllService.save(emmision_pollution_controllDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emmision_pollution_controllDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /emmision-pollution-controlls : get all the emmision_pollution_controlls.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emmision_pollution_controlls in body
     */
    @GetMapping("/emmision-pollution-controlls")
    @Timed
    public List<Emmision_pollution_controllDTO> getAllEmmision_pollution_controlls() {
        log.debug("REST request to get all Emmision_pollution_controlls");
        return emmision_pollution_controllService.findAll();
    }

    /**
     * GET  /emmision-pollution-controlls/:id : get the "id" emmision_pollution_controll.
     *
     * @param id the id of the emmision_pollution_controllDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emmision_pollution_controllDTO, or with status 404 (Not Found)
     */
    @GetMapping("/emmision-pollution-controlls/{id}")
    @Timed
    public ResponseEntity<Emmision_pollution_controllDTO> getEmmision_pollution_controll(@PathVariable String id) {
        log.debug("REST request to get Emmision_pollution_controll : {}", id);
        Emmision_pollution_controllDTO emmision_pollution_controllDTO = emmision_pollution_controllService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emmision_pollution_controllDTO));
    }

    /**
     * DELETE  /emmision-pollution-controlls/:id : delete the "id" emmision_pollution_controll.
     *
     * @param id the id of the emmision_pollution_controllDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emmision-pollution-controlls/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmmision_pollution_controll(@PathVariable String id) {
        log.debug("REST request to delete Emmision_pollution_controll : {}", id);
        emmision_pollution_controllService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
