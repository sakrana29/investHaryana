package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectypeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectypeDTO;
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
 * REST controller for managing Projectype.
 */
@RestController
@RequestMapping("/api")
public class ProjectypeResource {

    private final Logger log = LoggerFactory.getLogger(ProjectypeResource.class);

    private static final String ENTITY_NAME = "projectype";
        
    private final ProjectypeService projectypeService;

    public ProjectypeResource(ProjectypeService projectypeService) {
        this.projectypeService = projectypeService;
    }

    /**
     * POST  /projectypes : Create a new projectype.
     *
     * @param projectypeDTO the projectypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectypeDTO, or with status 400 (Bad Request) if the projectype has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectypes")
    @Timed
    public ResponseEntity<ProjectypeDTO> createProjectype(@Valid @RequestBody ProjectypeDTO projectypeDTO) throws URISyntaxException {
        log.debug("REST request to save Projectype : {}", projectypeDTO);
        if (projectypeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectype cannot already have an ID")).body(null);
        }
        ProjectypeDTO result = projectypeService.save(projectypeDTO);
        return ResponseEntity.created(new URI("/api/projectypes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectypes : Updates an existing projectype.
     *
     * @param projectypeDTO the projectypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectypeDTO,
     * or with status 400 (Bad Request) if the projectypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectypeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectypes")
    @Timed
    public ResponseEntity<ProjectypeDTO> updateProjectype(@Valid @RequestBody ProjectypeDTO projectypeDTO) throws URISyntaxException {
        log.debug("REST request to update Projectype : {}", projectypeDTO);
        if (projectypeDTO.getId() == null) {
            return createProjectype(projectypeDTO);
        }
        ProjectypeDTO result = projectypeService.save(projectypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectypes : get all the projectypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectypes in body
     */
    @GetMapping("/projectypes")
    @Timed
    public List<ProjectypeDTO> getAllProjectypes() {
        log.debug("REST request to get all Projectypes");
        return projectypeService.findAll();
    }

    /**
     * GET  /projectypes/:id : get the "id" projectype.
     *
     * @param id the id of the projectypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectypes/{id}")
    @Timed
    public ResponseEntity<ProjectypeDTO> getProjectype(@PathVariable String id) {
        log.debug("REST request to get Projectype : {}", id);
        ProjectypeDTO projectypeDTO = projectypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectypeDTO));
    }

    /**
     * DELETE  /projectypes/:id : delete the "id" projectype.
     *
     * @param id the id of the projectypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectypes/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectype(@PathVariable String id) {
        log.debug("REST request to delete Projectype : {}", id);
        projectypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
