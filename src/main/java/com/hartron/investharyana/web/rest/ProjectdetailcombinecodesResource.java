package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectdetailcombinecodesService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectdetailcombinecodesDTO;
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
 * REST controller for managing Projectdetailcombinecodes.
 */
@RestController
@RequestMapping("/api")
public class ProjectdetailcombinecodesResource {

    private final Logger log = LoggerFactory.getLogger(ProjectdetailcombinecodesResource.class);

    private static final String ENTITY_NAME = "projectdetailcombinecodes";
        
    private final ProjectdetailcombinecodesService projectdetailcombinecodesService;

    public ProjectdetailcombinecodesResource(ProjectdetailcombinecodesService projectdetailcombinecodesService) {
        this.projectdetailcombinecodesService = projectdetailcombinecodesService;
    }

    /**
     * POST  /projectdetailcombinecodes : Create a new projectdetailcombinecodes.
     *
     * @param projectdetailcombinecodesDTO the projectdetailcombinecodesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectdetailcombinecodesDTO, or with status 400 (Bad Request) if the projectdetailcombinecodes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectdetailcombinecodes")
    @Timed
    public ResponseEntity<ProjectdetailcombinecodesDTO> createProjectdetailcombinecodes(@RequestBody ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO) throws URISyntaxException {
        log.debug("REST request to save Projectdetailcombinecodes : {}", projectdetailcombinecodesDTO);
        if (projectdetailcombinecodesDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectdetailcombinecodes cannot already have an ID")).body(null);
        }
        ProjectdetailcombinecodesDTO result = projectdetailcombinecodesService.save(projectdetailcombinecodesDTO);
        return ResponseEntity.created(new URI("/api/projectdetailcombinecodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectdetailcombinecodes : Updates an existing projectdetailcombinecodes.
     *
     * @param projectdetailcombinecodesDTO the projectdetailcombinecodesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectdetailcombinecodesDTO,
     * or with status 400 (Bad Request) if the projectdetailcombinecodesDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectdetailcombinecodesDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectdetailcombinecodes")
    @Timed
    public ResponseEntity<ProjectdetailcombinecodesDTO> updateProjectdetailcombinecodes(@RequestBody ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO) throws URISyntaxException {
        log.debug("REST request to update Projectdetailcombinecodes : {}", projectdetailcombinecodesDTO);
        if (projectdetailcombinecodesDTO.getId() == null) {
            return createProjectdetailcombinecodes(projectdetailcombinecodesDTO);
        }
        ProjectdetailcombinecodesDTO result = projectdetailcombinecodesService.save(projectdetailcombinecodesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectdetailcombinecodesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectdetailcombinecodes : get all the projectdetailcombinecodes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectdetailcombinecodes in body
     */
    @GetMapping("/projectdetailcombinecodes")
    @Timed
    public List<ProjectdetailcombinecodesDTO> getAllProjectdetailcombinecodes() {
        log.debug("REST request to get all Projectdetailcombinecodes");
        return projectdetailcombinecodesService.findAll();
    }

    /**
     * GET  /projectdetailcombinecodes/:id : get the "id" projectdetailcombinecodes.
     *
     * @param id the id of the projectdetailcombinecodesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectdetailcombinecodesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectdetailcombinecodes/{id}")
    @Timed
    public ResponseEntity<ProjectdetailcombinecodesDTO> getProjectdetailcombinecodes(@PathVariable String id) {
        log.debug("REST request to get Projectdetailcombinecodes : {}", id);
        ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO = projectdetailcombinecodesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectdetailcombinecodesDTO));
    }

    /**
     * DELETE  /projectdetailcombinecodes/:id : delete the "id" projectdetailcombinecodes.
     *
     * @param id the id of the projectdetailcombinecodesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectdetailcombinecodes/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectdetailcombinecodes(@PathVariable String id) {
        log.debug("REST request to delete Projectdetailcombinecodes : {}", id);
        projectdetailcombinecodesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
