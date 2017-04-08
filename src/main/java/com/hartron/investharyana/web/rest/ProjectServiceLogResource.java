package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.security.SecurityUtils;
import com.hartron.investharyana.service.ProjectServiceLogService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectServiceLogDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing ProjectServiceLog.
 */
@RestController
@RequestMapping("/api")
public class ProjectServiceLogResource {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceLogResource.class);

    private static final String ENTITY_NAME = "projectServiceLog";

    private final ProjectServiceLogService projectServiceLogService;

    public ProjectServiceLogResource(ProjectServiceLogService projectServiceLogService) {
        this.projectServiceLogService = projectServiceLogService;
    }

    /**
     * POST  /project-service-logs : Create a new projectServiceLog.
     *
     * @param projectServiceLogDTO the projectServiceLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectServiceLogDTO, or with status 400 (Bad Request) if the projectServiceLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-service-logs")
    @Timed
    public ResponseEntity<ProjectServiceLogDTO> createProjectServiceLog(@RequestBody ProjectServiceLogDTO projectServiceLogDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectServiceLog : {}", projectServiceLogDTO);
        if (projectServiceLogDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectServiceLog cannot already have an ID")).body(null);
        }
        projectServiceLogDTO.setCommentByUserLogin(SecurityUtils.getCurrentUserLogin());
        projectServiceLogDTO.setCommentDate(ZonedDateTime.now());
        ProjectServiceLogDTO result = projectServiceLogService.save(projectServiceLogDTO);
        return ResponseEntity.created(new URI("/api/project-service-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-service-logs : Updates an existing projectServiceLog.
     *
     * @param projectServiceLogDTO the projectServiceLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectServiceLogDTO,
     * or with status 400 (Bad Request) if the projectServiceLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectServiceLogDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-service-logs")
    @Timed
    public ResponseEntity<ProjectServiceLogDTO> updateProjectServiceLog(@RequestBody ProjectServiceLogDTO projectServiceLogDTO) throws URISyntaxException {
        log.debug("REST request to update ProjectServiceLog : {}", projectServiceLogDTO);
        if (projectServiceLogDTO.getId() == null) {
            return createProjectServiceLog(projectServiceLogDTO);
        }
        ProjectServiceLogDTO result = projectServiceLogService.save(projectServiceLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectServiceLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-service-logs : get all the projectServiceLogs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectServiceLogs in body
     */
    @GetMapping("/project-service-logs")
    @Timed
    public List<ProjectServiceLogDTO> getAllProjectServiceLogs() {
        log.debug("REST request to get all ProjectServiceLogs");
        return projectServiceLogService.findAll();
    }

    /**
     * GET  /project-service-logs/:id : get the "id" projectServiceLog.
     *
     * @param id the id of the projectServiceLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectServiceLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-service-logs/{id}")
    @Timed
    public ResponseEntity<ProjectServiceLogDTO> getProjectServiceLog(@PathVariable String id) {
        log.debug("REST request to get ProjectServiceLog : {}", id);
        ProjectServiceLogDTO projectServiceLogDTO = projectServiceLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectServiceLogDTO));
    }

    /**
     * DELETE  /project-service-logs/:id : delete the "id" projectServiceLog.
     *
     * @param id the id of the projectServiceLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-service-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectServiceLog(@PathVariable String id) {
        log.debug("REST request to delete ProjectServiceLog : {}", id);
        projectServiceLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
