package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Project_phaseService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Project_phaseDTO;
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
 * REST controller for managing Project_phase.
 */
@RestController
@RequestMapping("/api")
public class Project_phaseResource {

    private final Logger log = LoggerFactory.getLogger(Project_phaseResource.class);

    private static final String ENTITY_NAME = "project_phase";

    private final Project_phaseService project_phaseService;

    public Project_phaseResource(Project_phaseService project_phaseService) {
        this.project_phaseService = project_phaseService;
    }

    /**
     * POST  /project-phases : Create a new project_phase.
     *
     * @param project_phaseDTO the project_phaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new project_phaseDTO, or with status 400 (Bad Request) if the project_phase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-phases")
    @Timed
    public ResponseEntity<Project_phaseDTO> createProject_phase(@RequestBody Project_phaseDTO project_phaseDTO) throws URISyntaxException {
        log.debug("REST request to save Project_phase : {}", project_phaseDTO);
        if (project_phaseDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new project_phase cannot already have an ID")).body(null);
        }
        Project_phaseDTO result = project_phaseService.save(project_phaseDTO);
        return ResponseEntity.created(new URI("/api/project-phases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-phases : Updates an existing project_phase.
     *
     * @param project_phaseDTO the project_phaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated project_phaseDTO,
     * or with status 400 (Bad Request) if the project_phaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the project_phaseDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-phases")
    @Timed
    public ResponseEntity<Project_phaseDTO> updateProject_phase(@RequestBody Project_phaseDTO project_phaseDTO) throws URISyntaxException {
        log.debug("REST request to update Project_phase : {}", project_phaseDTO);
        if (project_phaseDTO.getId() == null) {
            return createProject_phase(project_phaseDTO);
        }
        Project_phaseDTO result = project_phaseService.save(project_phaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, project_phaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-phases : get all the project_phases.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of project_phases in body
     */
    @GetMapping("/project-phases")
    @Timed
    public List<Project_phaseDTO> getAllProject_phases() {
        log.debug("REST request to get all Project_phases");
        return project_phaseService.findAll();
    }

    /**
     * GET  /project-phases/:id : get the "id" project_phase.
     *
     * @param id the id of the project_phaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the project_phaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-phases/{id}")
    @Timed
    public ResponseEntity<Project_phaseDTO> getProject_phase(@PathVariable String id) {
        log.debug("REST request to get Project_phase : {}", id);
        Project_phaseDTO project_phaseDTO = project_phaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(project_phaseDTO));
    }

    @GetMapping("/project-phases/project/{projectid}")
    @Timed
    public List<Project_phaseDTO> getProject_phaseByProjectid(@PathVariable String projectid) {
        log.debug("REST request to get Project_phase by projectid : {}", projectid);
        List<Project_phaseDTO> project_phaseDTOList = project_phaseService.findAllByProjectid(projectid);
        return project_phaseDTOList;
    }

    /**
     * DELETE  /project-phases/:id : delete the "id" project_phase.
     *
     * @param id the id of the project_phaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-phases/{id}")
    @Timed
    public ResponseEntity<Void> deleteProject_phase(@PathVariable String id) {
        log.debug("REST request to delete Project_phase : {}", id);
        project_phaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
