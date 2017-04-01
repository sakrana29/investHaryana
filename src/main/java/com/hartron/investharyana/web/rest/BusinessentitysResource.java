package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.BusinessentitysService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.BusinessentitysDTO;
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
 * REST controller for managing Businessentitys.
 */
@RestController
@RequestMapping("/api")
public class BusinessentitysResource {

    private final Logger log = LoggerFactory.getLogger(BusinessentitysResource.class);

    private static final String ENTITY_NAME = "businessentitys";
        
    private final BusinessentitysService businessentitysService;

    public BusinessentitysResource(BusinessentitysService businessentitysService) {
        this.businessentitysService = businessentitysService;
    }

    /**
     * POST  /businessentitys : Create a new businessentitys.
     *
     * @param businessentitysDTO the businessentitysDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessentitysDTO, or with status 400 (Bad Request) if the businessentitys has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/businessentitys")
    @Timed
    public ResponseEntity<BusinessentitysDTO> createBusinessentitys(@RequestBody BusinessentitysDTO businessentitysDTO) throws URISyntaxException {
        log.debug("REST request to save Businessentitys : {}", businessentitysDTO);
        if (businessentitysDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new businessentitys cannot already have an ID")).body(null);
        }
        BusinessentitysDTO result = businessentitysService.save(businessentitysDTO);
        return ResponseEntity.created(new URI("/api/businessentitys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /businessentitys : Updates an existing businessentitys.
     *
     * @param businessentitysDTO the businessentitysDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessentitysDTO,
     * or with status 400 (Bad Request) if the businessentitysDTO is not valid,
     * or with status 500 (Internal Server Error) if the businessentitysDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/businessentitys")
    @Timed
    public ResponseEntity<BusinessentitysDTO> updateBusinessentitys(@RequestBody BusinessentitysDTO businessentitysDTO) throws URISyntaxException {
        log.debug("REST request to update Businessentitys : {}", businessentitysDTO);
        if (businessentitysDTO.getId() == null) {
            return createBusinessentitys(businessentitysDTO);
        }
        BusinessentitysDTO result = businessentitysService.save(businessentitysDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessentitysDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /businessentitys : get all the businessentitys.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of businessentitys in body
     */
    @GetMapping("/businessentitys")
    @Timed
    public List<BusinessentitysDTO> getAllBusinessentitys() {
        log.debug("REST request to get all Businessentitys");
        return businessentitysService.findAll();
    }

    /**
     * GET  /businessentitys/:id : get the "id" businessentitys.
     *
     * @param id the id of the businessentitysDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessentitysDTO, or with status 404 (Not Found)
     */
    @GetMapping("/businessentitys/{id}")
    @Timed
    public ResponseEntity<BusinessentitysDTO> getBusinessentitys(@PathVariable String id) {
        log.debug("REST request to get Businessentitys : {}", id);
        BusinessentitysDTO businessentitysDTO = businessentitysService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(businessentitysDTO));
    }

    /**
     * DELETE  /businessentitys/:id : delete the "id" businessentitys.
     *
     * @param id the id of the businessentitysDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/businessentitys/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessentitys(@PathVariable String id) {
        log.debug("REST request to delete Businessentitys : {}", id);
        businessentitysService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
