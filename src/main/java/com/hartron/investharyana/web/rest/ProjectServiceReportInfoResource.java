package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectServiceReportInfoService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectServiceReportInfoDTO;
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
 * REST controller for managing ProjectServiceReportInfo.
 */
@RestController
@RequestMapping("/api")
public class ProjectServiceReportInfoResource {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceReportInfoResource.class);

    private static final String ENTITY_NAME = "projectServiceReportInfo";

    private final ProjectServiceReportInfoService projectServiceReportInfoService;

    public ProjectServiceReportInfoResource(ProjectServiceReportInfoService projectServiceReportInfoService) {
        this.projectServiceReportInfoService = projectServiceReportInfoService;
    }

    /**
     * POST  /project-service-report-infos : Create a new projectServiceReportInfo.
     *
     * @param projectServiceReportInfoDTO the projectServiceReportInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectServiceReportInfoDTO, or with status 400 (Bad Request) if the projectServiceReportInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-service-report-infos")
    @Timed
    public ResponseEntity<ProjectServiceReportInfoDTO> createProjectServiceReportInfo(@RequestBody ProjectServiceReportInfoDTO projectServiceReportInfoDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectServiceReportInfo : {}", projectServiceReportInfoDTO);
        if (projectServiceReportInfoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectServiceReportInfo cannot already have an ID")).body(null);
        }
        ProjectServiceReportInfoDTO result = projectServiceReportInfoService.save(projectServiceReportInfoDTO);
        return ResponseEntity.created(new URI("/api/project-service-report-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-service-report-infos : Updates an existing projectServiceReportInfo.
     *
     * @param projectServiceReportInfoDTO the projectServiceReportInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectServiceReportInfoDTO,
     * or with status 400 (Bad Request) if the projectServiceReportInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectServiceReportInfoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-service-report-infos")
    @Timed
    public ResponseEntity<ProjectServiceReportInfoDTO> updateProjectServiceReportInfo(@RequestBody ProjectServiceReportInfoDTO projectServiceReportInfoDTO) throws URISyntaxException {
        log.debug("REST request to update ProjectServiceReportInfo : {}", projectServiceReportInfoDTO);
        if (projectServiceReportInfoDTO.getId() == null) {
            return createProjectServiceReportInfo(projectServiceReportInfoDTO);
        }
        ProjectServiceReportInfoDTO result = projectServiceReportInfoService.save(projectServiceReportInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectServiceReportInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-service-report-infos : get all the projectServiceReportInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectServiceReportInfos in body
     */
    @GetMapping("/project-service-report-infos")
    @Timed
    public List<ProjectServiceReportInfoDTO> getAllProjectServiceReportInfos() {
        log.debug("REST request to get all ProjectServiceReportInfos");
        return projectServiceReportInfoService.findAll();
    }

    @GetMapping("/project-service-report-infos/department/{departmentname}")
    @Timed
    public List<ProjectServiceReportInfoDTO> getAllProjectServiceReportInfosByDepartment(@PathVariable String departmentname) {
        log.debug("REST request to get all ProjectServiceReportInfos by department");
        return projectServiceReportInfoService.findAllByDepartment(departmentname);
    }

    /**
     * GET  /project-service-report-infos/:id : get the "id" projectServiceReportInfo.
     *
     * @param id the id of the projectServiceReportInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectServiceReportInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-service-report-infos/{id}")
    @Timed
    public ResponseEntity<ProjectServiceReportInfoDTO> getProjectServiceReportInfo(@PathVariable String id) {
        log.debug("REST request to get ProjectServiceReportInfo : {}", id);
        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = projectServiceReportInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectServiceReportInfoDTO));
    }

    /**
     * DELETE  /project-service-report-infos/:id : delete the "id" projectServiceReportInfo.
     *
     * @param id the id of the projectServiceReportInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-service-report-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectServiceReportInfo(@PathVariable String id) {
        log.debug("REST request to delete ProjectServiceReportInfo : {}", id);
        projectServiceReportInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
