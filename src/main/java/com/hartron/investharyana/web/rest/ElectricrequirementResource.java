package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ElectricrequirementService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ElectricrequirementDTO;
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
 * REST controller for managing Electricrequirement.
 */
@RestController
@RequestMapping("/api")
public class ElectricrequirementResource {

    private final Logger log = LoggerFactory.getLogger(ElectricrequirementResource.class);

    private static final String ENTITY_NAME = "electricrequirement";
        
    private final ElectricrequirementService electricrequirementService;

    public ElectricrequirementResource(ElectricrequirementService electricrequirementService) {
        this.electricrequirementService = electricrequirementService;
    }

    /**
     * POST  /electricrequirements : Create a new electricrequirement.
     *
     * @param electricrequirementDTO the electricrequirementDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new electricrequirementDTO, or with status 400 (Bad Request) if the electricrequirement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/electricrequirements")
    @Timed
    public ResponseEntity<ElectricrequirementDTO> createElectricrequirement(@RequestBody ElectricrequirementDTO electricrequirementDTO) throws URISyntaxException {
        log.debug("REST request to save Electricrequirement : {}", electricrequirementDTO);
        if (electricrequirementDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new electricrequirement cannot already have an ID")).body(null);
        }
        ElectricrequirementDTO result = electricrequirementService.save(electricrequirementDTO);
        return ResponseEntity.created(new URI("/api/electricrequirements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /electricrequirements : Updates an existing electricrequirement.
     *
     * @param electricrequirementDTO the electricrequirementDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated electricrequirementDTO,
     * or with status 400 (Bad Request) if the electricrequirementDTO is not valid,
     * or with status 500 (Internal Server Error) if the electricrequirementDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/electricrequirements")
    @Timed
    public ResponseEntity<ElectricrequirementDTO> updateElectricrequirement(@RequestBody ElectricrequirementDTO electricrequirementDTO) throws URISyntaxException {
        log.debug("REST request to update Electricrequirement : {}", electricrequirementDTO);
        if (electricrequirementDTO.getId() == null) {
            return createElectricrequirement(electricrequirementDTO);
        }
        ElectricrequirementDTO result = electricrequirementService.save(electricrequirementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, electricrequirementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /electricrequirements : get all the electricrequirements.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of electricrequirements in body
     */
    @GetMapping("/electricrequirements")
    @Timed
    public List<ElectricrequirementDTO> getAllElectricrequirements() {
        log.debug("REST request to get all Electricrequirements");
        return electricrequirementService.findAll();
    }

    /**
     * GET  /electricrequirements/:id : get the "id" electricrequirement.
     *
     * @param id the id of the electricrequirementDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the electricrequirementDTO, or with status 404 (Not Found)
     */
    @GetMapping("/electricrequirements/{id}")
    @Timed
    public ResponseEntity<ElectricrequirementDTO> getElectricrequirement(@PathVariable String id) {
        log.debug("REST request to get Electricrequirement : {}", id);
        ElectricrequirementDTO electricrequirementDTO = electricrequirementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(electricrequirementDTO));
    }

    /**
     * DELETE  /electricrequirements/:id : delete the "id" electricrequirement.
     *
     * @param id the id of the electricrequirementDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/electricrequirements/{id}")
    @Timed
    public ResponseEntity<Void> deleteElectricrequirement(@PathVariable String id) {
        log.debug("REST request to delete Electricrequirement : {}", id);
        electricrequirementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
