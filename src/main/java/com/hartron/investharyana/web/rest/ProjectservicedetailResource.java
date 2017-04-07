package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectservicedetailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectservicedetailDTO;
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
 * REST controller for managing Projectservicedetail.
 */
@RestController
@RequestMapping("/api")
public class ProjectservicedetailResource {

    private final Logger log = LoggerFactory.getLogger(ProjectservicedetailResource.class);

    private static final String ENTITY_NAME = "projectservicedetail";

    private final ProjectservicedetailService projectservicedetailService;

    public ProjectservicedetailResource(ProjectservicedetailService projectservicedetailService) {
        this.projectservicedetailService = projectservicedetailService;
    }

    /**
     * POST  /projectservicedetails : Create a new projectservicedetail.
     *
     * @param projectservicedetailDTO the projectservicedetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectservicedetailDTO, or with status 400 (Bad Request) if the projectservicedetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectservicedetails")
    @Timed
    public ResponseEntity<ProjectservicedetailDTO> createProjectservicedetail(@RequestBody ProjectservicedetailDTO projectservicedetailDTO) throws URISyntaxException {
        log.debug("REST request to save Projectservicedetail : {}", projectservicedetailDTO);
        if (projectservicedetailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectservicedetail cannot already have an ID")).body(null);
        }
        ProjectservicedetailDTO result = projectservicedetailService.save(projectservicedetailDTO);
        return ResponseEntity.created(new URI("/api/projectservicedetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectservicedetails : Updates an existing projectservicedetail.
     *
     * @param projectservicedetailDTO the projectservicedetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectservicedetailDTO,
     * or with status 400 (Bad Request) if the projectservicedetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectservicedetailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectservicedetails")
    @Timed
    public ResponseEntity<ProjectservicedetailDTO> updateProjectservicedetail(@RequestBody ProjectservicedetailDTO projectservicedetailDTO) throws URISyntaxException {
        log.debug("REST request to update Projectservicedetail : {}", projectservicedetailDTO);
        if (projectservicedetailDTO.getId() == null) {
            return createProjectservicedetail(projectservicedetailDTO);
        }
        ProjectservicedetailDTO result = projectservicedetailService.save(projectservicedetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectservicedetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectservicedetails : get all the projectservicedetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectservicedetails in body
     */
    @GetMapping("/projectservicedetails")
    @Timed
    public List<ProjectservicedetailDTO> getAllProjectservicedetails() {
        log.debug("REST request to get all Projectservicedetails");
        return projectservicedetailService.findAll();
    }

    /**
     * GET  /projectservicedetails/:id : get the "id" projectservicedetail.
     *
     * @param id the id of the projectservicedetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectservicedetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectservicedetails/{id}")
    @Timed
    public ResponseEntity<ProjectservicedetailDTO> getProjectservicedetail(@PathVariable String id) {
        log.debug("REST request to get Projectservicedetail : {}", id);
        ProjectservicedetailDTO projectservicedetailDTO = projectservicedetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectservicedetailDTO));
    }

    /**
     * DELETE  /projectservicedetails/:id : delete the "id" projectservicedetail.
     *
     * @param id the id of the projectservicedetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectservicedetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectservicedetail(@PathVariable String id) {
        log.debug("REST request to delete Projectservicedetail : {}", id);
        projectservicedetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/projectservicedetails/project/{projectid}")
    @Timed
    public List<ProjectservicedetailDTO> getAllProjectservicedetailsByProjectid(@PathVariable String projectid) {
        log.debug("REST request to get all Projectservicedetails");
        return projectservicedetailService.findAllByProjectid(projectid);
    }
}
