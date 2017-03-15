package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectprocessflowstepsService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectprocessflowstepsDTO;
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
 * REST controller for managing Projectprocessflowsteps.
 */
@RestController
@RequestMapping("/api")
public class ProjectprocessflowstepsResource {

    private final Logger log = LoggerFactory.getLogger(ProjectprocessflowstepsResource.class);

    private static final String ENTITY_NAME = "projectprocessflowsteps";
        
    private final ProjectprocessflowstepsService projectprocessflowstepsService;

    public ProjectprocessflowstepsResource(ProjectprocessflowstepsService projectprocessflowstepsService) {
        this.projectprocessflowstepsService = projectprocessflowstepsService;
    }

    /**
     * POST  /projectprocessflowsteps : Create a new projectprocessflowsteps.
     *
     * @param projectprocessflowstepsDTO the projectprocessflowstepsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectprocessflowstepsDTO, or with status 400 (Bad Request) if the projectprocessflowsteps has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectprocessflowsteps")
    @Timed
    public ResponseEntity<ProjectprocessflowstepsDTO> createProjectprocessflowsteps(@RequestBody ProjectprocessflowstepsDTO projectprocessflowstepsDTO) throws URISyntaxException {
        log.debug("REST request to save Projectprocessflowsteps : {}", projectprocessflowstepsDTO);
        if (projectprocessflowstepsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectprocessflowsteps cannot already have an ID")).body(null);
        }
        ProjectprocessflowstepsDTO result = projectprocessflowstepsService.save(projectprocessflowstepsDTO);
        return ResponseEntity.created(new URI("/api/projectprocessflowsteps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectprocessflowsteps : Updates an existing projectprocessflowsteps.
     *
     * @param projectprocessflowstepsDTO the projectprocessflowstepsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectprocessflowstepsDTO,
     * or with status 400 (Bad Request) if the projectprocessflowstepsDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectprocessflowstepsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectprocessflowsteps")
    @Timed
    public ResponseEntity<ProjectprocessflowstepsDTO> updateProjectprocessflowsteps(@RequestBody ProjectprocessflowstepsDTO projectprocessflowstepsDTO) throws URISyntaxException {
        log.debug("REST request to update Projectprocessflowsteps : {}", projectprocessflowstepsDTO);
        if (projectprocessflowstepsDTO.getId() == null) {
            return createProjectprocessflowsteps(projectprocessflowstepsDTO);
        }
        ProjectprocessflowstepsDTO result = projectprocessflowstepsService.save(projectprocessflowstepsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectprocessflowstepsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectprocessflowsteps : get all the projectprocessflowsteps.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectprocessflowsteps in body
     */
    @GetMapping("/projectprocessflowsteps")
    @Timed
    public List<ProjectprocessflowstepsDTO> getAllProjectprocessflowsteps() {
        log.debug("REST request to get all Projectprocessflowsteps");
        return projectprocessflowstepsService.findAll();
    }

    /**
     * GET  /projectprocessflowsteps/:id : get the "id" projectprocessflowsteps.
     *
     * @param id the id of the projectprocessflowstepsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectprocessflowstepsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectprocessflowsteps/{id}")
    @Timed
    public ResponseEntity<ProjectprocessflowstepsDTO> getProjectprocessflowsteps(@PathVariable String id) {
        log.debug("REST request to get Projectprocessflowsteps : {}", id);
        ProjectprocessflowstepsDTO projectprocessflowstepsDTO = projectprocessflowstepsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectprocessflowstepsDTO));
    }

    /**
     * DELETE  /projectprocessflowsteps/:id : delete the "id" projectprocessflowsteps.
     *
     * @param id the id of the projectprocessflowstepsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectprocessflowsteps/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectprocessflowsteps(@PathVariable String id) {
        log.debug("REST request to delete Projectprocessflowsteps : {}", id);
        projectprocessflowstepsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
