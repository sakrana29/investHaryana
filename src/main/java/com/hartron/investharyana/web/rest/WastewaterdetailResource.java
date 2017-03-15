package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.WastewaterdetailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.WastewaterdetailDTO;
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
 * REST controller for managing Wastewaterdetail.
 */
@RestController
@RequestMapping("/api")
public class WastewaterdetailResource {

    private final Logger log = LoggerFactory.getLogger(WastewaterdetailResource.class);

    private static final String ENTITY_NAME = "wastewaterdetail";
        
    private final WastewaterdetailService wastewaterdetailService;

    public WastewaterdetailResource(WastewaterdetailService wastewaterdetailService) {
        this.wastewaterdetailService = wastewaterdetailService;
    }

    /**
     * POST  /wastewaterdetails : Create a new wastewaterdetail.
     *
     * @param wastewaterdetailDTO the wastewaterdetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wastewaterdetailDTO, or with status 400 (Bad Request) if the wastewaterdetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wastewaterdetails")
    @Timed
    public ResponseEntity<WastewaterdetailDTO> createWastewaterdetail(@RequestBody WastewaterdetailDTO wastewaterdetailDTO) throws URISyntaxException {
        log.debug("REST request to save Wastewaterdetail : {}", wastewaterdetailDTO);
        if (wastewaterdetailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new wastewaterdetail cannot already have an ID")).body(null);
        }
        WastewaterdetailDTO result = wastewaterdetailService.save(wastewaterdetailDTO);
        return ResponseEntity.created(new URI("/api/wastewaterdetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wastewaterdetails : Updates an existing wastewaterdetail.
     *
     * @param wastewaterdetailDTO the wastewaterdetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wastewaterdetailDTO,
     * or with status 400 (Bad Request) if the wastewaterdetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the wastewaterdetailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wastewaterdetails")
    @Timed
    public ResponseEntity<WastewaterdetailDTO> updateWastewaterdetail(@RequestBody WastewaterdetailDTO wastewaterdetailDTO) throws URISyntaxException {
        log.debug("REST request to update Wastewaterdetail : {}", wastewaterdetailDTO);
        if (wastewaterdetailDTO.getId() == null) {
            return createWastewaterdetail(wastewaterdetailDTO);
        }
        WastewaterdetailDTO result = wastewaterdetailService.save(wastewaterdetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wastewaterdetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wastewaterdetails : get all the wastewaterdetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wastewaterdetails in body
     */
    @GetMapping("/wastewaterdetails")
    @Timed
    public List<WastewaterdetailDTO> getAllWastewaterdetails() {
        log.debug("REST request to get all Wastewaterdetails");
        return wastewaterdetailService.findAll();
    }

    /**
     * GET  /wastewaterdetails/:id : get the "id" wastewaterdetail.
     *
     * @param id the id of the wastewaterdetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wastewaterdetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wastewaterdetails/{id}")
    @Timed
    public ResponseEntity<WastewaterdetailDTO> getWastewaterdetail(@PathVariable String id) {
        log.debug("REST request to get Wastewaterdetail : {}", id);
        WastewaterdetailDTO wastewaterdetailDTO = wastewaterdetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wastewaterdetailDTO));
    }

    /**
     * DELETE  /wastewaterdetails/:id : delete the "id" wastewaterdetail.
     *
     * @param id the id of the wastewaterdetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wastewaterdetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteWastewaterdetail(@PathVariable String id) {
        log.debug("REST request to delete Wastewaterdetail : {}", id);
        wastewaterdetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
