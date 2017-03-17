package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ApprovalformsService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ApprovalformsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Approvalforms.
 */
@RestController
@RequestMapping("/api")
public class ApprovalformsResource {

    private final Logger log = LoggerFactory.getLogger(ApprovalformsResource.class);

    private static final String ENTITY_NAME = "approvalforms";
        
    private final ApprovalformsService approvalformsService;

    public ApprovalformsResource(ApprovalformsService approvalformsService) {
        this.approvalformsService = approvalformsService;
    }

    /**
     * POST  /approvalforms : Create a new approvalforms.
     *
     * @param approvalformsDTO the approvalformsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new approvalformsDTO, or with status 400 (Bad Request) if the approvalforms has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/approvalforms")
    @Timed
    public ResponseEntity<ApprovalformsDTO> createApprovalforms(@Valid @RequestBody ApprovalformsDTO approvalformsDTO) throws URISyntaxException {
        log.debug("REST request to save Approvalforms : {}", approvalformsDTO);
        if (approvalformsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new approvalforms cannot already have an ID")).body(null);
        }
        ApprovalformsDTO result = approvalformsService.save(approvalformsDTO);
        return ResponseEntity.created(new URI("/api/approvalforms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /approvalforms : Updates an existing approvalforms.
     *
     * @param approvalformsDTO the approvalformsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated approvalformsDTO,
     * or with status 400 (Bad Request) if the approvalformsDTO is not valid,
     * or with status 500 (Internal Server Error) if the approvalformsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/approvalforms")
    @Timed
    public ResponseEntity<ApprovalformsDTO> updateApprovalforms(@Valid @RequestBody ApprovalformsDTO approvalformsDTO) throws URISyntaxException {
        log.debug("REST request to update Approvalforms : {}", approvalformsDTO);
        if (approvalformsDTO.getId() == null) {
            return createApprovalforms(approvalformsDTO);
        }
        ApprovalformsDTO result = approvalformsService.save(approvalformsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, approvalformsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /approvalforms : get all the approvalforms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of approvalforms in body
     */
    @GetMapping("/approvalforms")
    @Timed
    public List<ApprovalformsDTO> getAllApprovalforms() {
        log.debug("REST request to get all Approvalforms");
        return approvalformsService.findAll();
    }

    /**
     * GET  /approvalforms/:id : get the "id" approvalforms.
     *
     * @param id the id of the approvalformsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the approvalformsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/approvalforms/{id}")
    @Timed
    public ResponseEntity<ApprovalformsDTO> getApprovalforms(@PathVariable String id) {
        log.debug("REST request to get Approvalforms : {}", id);
        ApprovalformsDTO approvalformsDTO = approvalformsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(approvalformsDTO));
    }

    /**
     * DELETE  /approvalforms/:id : delete the "id" approvalforms.
     *
     * @param id the id of the approvalformsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/approvalforms/{id}")
    @Timed
    public ResponseEntity<Void> deleteApprovalforms(@PathVariable String id) {
        log.debug("REST request to delete Approvalforms : {}", id);
        approvalformsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
