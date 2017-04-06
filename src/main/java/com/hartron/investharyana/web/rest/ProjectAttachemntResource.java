package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectAttachemntService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectAttachemntDTO;
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
 * REST controller for managing ProjectAttachemnt.
 */
@RestController
@RequestMapping("/api")
public class ProjectAttachemntResource {

    private final Logger log = LoggerFactory.getLogger(ProjectAttachemntResource.class);

    private static final String ENTITY_NAME = "projectAttachemnt";
        
    private final ProjectAttachemntService projectAttachemntService;

    public ProjectAttachemntResource(ProjectAttachemntService projectAttachemntService) {
        this.projectAttachemntService = projectAttachemntService;
    }

    /**
     * POST  /project-attachemnts : Create a new projectAttachemnt.
     *
     * @param projectAttachemntDTO the projectAttachemntDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectAttachemntDTO, or with status 400 (Bad Request) if the projectAttachemnt has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-attachemnts")
    @Timed
    public ResponseEntity<ProjectAttachemntDTO> createProjectAttachemnt(@RequestBody ProjectAttachemntDTO projectAttachemntDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectAttachemnt : {}", projectAttachemntDTO);
        if (projectAttachemntDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectAttachemnt cannot already have an ID")).body(null);
        }
        ProjectAttachemntDTO result = projectAttachemntService.save(projectAttachemntDTO);
        return ResponseEntity.created(new URI("/api/project-attachemnts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-attachemnts : Updates an existing projectAttachemnt.
     *
     * @param projectAttachemntDTO the projectAttachemntDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectAttachemntDTO,
     * or with status 400 (Bad Request) if the projectAttachemntDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectAttachemntDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-attachemnts")
    @Timed
    public ResponseEntity<ProjectAttachemntDTO> updateProjectAttachemnt(@RequestBody ProjectAttachemntDTO projectAttachemntDTO) throws URISyntaxException {
        log.debug("REST request to update ProjectAttachemnt : {}", projectAttachemntDTO);
        if (projectAttachemntDTO.getId() == null) {
            return createProjectAttachemnt(projectAttachemntDTO);
        }
        ProjectAttachemntDTO result = projectAttachemntService.save(projectAttachemntDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectAttachemntDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-attachemnts : get all the projectAttachemnts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectAttachemnts in body
     */
    @GetMapping("/project-attachemnts")
    @Timed
    public List<ProjectAttachemntDTO> getAllProjectAttachemnts() {
        log.debug("REST request to get all ProjectAttachemnts");
        return projectAttachemntService.findAll();
    }

    /**
     * GET  /project-attachemnts/:id : get the "id" projectAttachemnt.
     *
     * @param id the id of the projectAttachemntDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectAttachemntDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-attachemnts/{id}")
    @Timed
    public ResponseEntity<ProjectAttachemntDTO> getProjectAttachemnt(@PathVariable String id) {
        log.debug("REST request to get ProjectAttachemnt : {}", id);
        ProjectAttachemntDTO projectAttachemntDTO = projectAttachemntService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectAttachemntDTO));
    }

    /**
     * DELETE  /project-attachemnts/:id : delete the "id" projectAttachemnt.
     *
     * @param id the id of the projectAttachemntDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-attachemnts/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectAttachemnt(@PathVariable String id) {
        log.debug("REST request to delete ProjectAttachemnt : {}", id);
        projectAttachemntService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
