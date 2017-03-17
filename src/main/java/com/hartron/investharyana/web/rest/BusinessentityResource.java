package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.domain.Businessentity;
import com.hartron.investharyana.service.BusinessentityService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing Businessentity.
 */
@RestController
@RequestMapping("/api")
public class BusinessentityResource {

    private final Logger log = LoggerFactory.getLogger(BusinessentityResource.class);

    private static final String ENTITY_NAME = "businessentity";
        
    private final BusinessentityService businessentityService;

    public BusinessentityResource(BusinessentityService businessentityService) {
        this.businessentityService = businessentityService;
    }

    /**
     * POST  /businessentities : Create a new businessentity.
     *
     * @param businessentity the businessentity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessentity, or with status 400 (Bad Request) if the businessentity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/businessentities")
    @Timed
    public ResponseEntity<Businessentity> createBusinessentity(@Valid @RequestBody Businessentity businessentity) throws URISyntaxException {
        log.debug("REST request to save Businessentity : {}", businessentity);
        if (businessentity.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new businessentity cannot already have an ID")).body(null);
        }
        Businessentity result = businessentityService.save(businessentity);
        return ResponseEntity.created(new URI("/api/businessentities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /businessentities : Updates an existing businessentity.
     *
     * @param businessentity the businessentity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessentity,
     * or with status 400 (Bad Request) if the businessentity is not valid,
     * or with status 500 (Internal Server Error) if the businessentity couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/businessentities")
    @Timed
    public ResponseEntity<Businessentity> updateBusinessentity(@Valid @RequestBody Businessentity businessentity) throws URISyntaxException {
        log.debug("REST request to update Businessentity : {}", businessentity);
        if (businessentity.getId() == null) {
            return createBusinessentity(businessentity);
        }
        Businessentity result = businessentityService.save(businessentity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessentity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /businessentities : get all the businessentities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of businessentities in body
     */
    @GetMapping("/businessentities")
    @Timed
    public List<Businessentity> getAllBusinessentities() {
        log.debug("REST request to get all Businessentities");
        return businessentityService.findAll();
    }

    /**
     * GET  /businessentities/:id : get the "id" businessentity.
     *
     * @param id the id of the businessentity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessentity, or with status 404 (Not Found)
     */
    @GetMapping("/businessentities/{id}")
    @Timed
    public ResponseEntity<Businessentity> getBusinessentity(@PathVariable String id) {
        log.debug("REST request to get Businessentity : {}", id);
        Businessentity businessentity = businessentityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(businessentity));
    }

    /**
     * DELETE  /businessentities/:id : delete the "id" businessentity.
     *
     * @param id the id of the businessentity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/businessentities/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessentity(@PathVariable String id) {
        log.debug("REST request to delete Businessentity : {}", id);
        businessentityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
