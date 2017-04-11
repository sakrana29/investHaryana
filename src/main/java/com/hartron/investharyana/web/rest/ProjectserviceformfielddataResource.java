package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectserviceformfielddataService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectserviceformfielddataDTO;
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
 * REST controller for managing Projectserviceformfielddata.
 */
@RestController
@RequestMapping("/api")
public class ProjectserviceformfielddataResource {

    private final Logger log = LoggerFactory.getLogger(ProjectserviceformfielddataResource.class);

    private static final String ENTITY_NAME = "projectserviceformfielddata";

    private final ProjectserviceformfielddataService projectserviceformfielddataService;

    public ProjectserviceformfielddataResource(ProjectserviceformfielddataService projectserviceformfielddataService) {
        this.projectserviceformfielddataService = projectserviceformfielddataService;
    }

    /**
     * POST  /projectserviceformfielddata : Create a new projectserviceformfielddata.
     *
     * @param projectserviceformfielddataDTO the projectserviceformfielddataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectserviceformfielddataDTO, or with status 400 (Bad Request) if the projectserviceformfielddata has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectserviceformfielddata")
    @Timed
    public ResponseEntity<ProjectserviceformfielddataDTO> createProjectserviceformfielddata(@Valid @RequestBody ProjectserviceformfielddataDTO projectserviceformfielddataDTO) throws URISyntaxException {
        log.debug("REST request to save Projectserviceformfielddata : {}", projectserviceformfielddataDTO);
        if (projectserviceformfielddataDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectserviceformfielddata cannot already have an ID")).body(null);
        }
        ProjectserviceformfielddataDTO result = projectserviceformfielddataService.save(projectserviceformfielddataDTO);
        return ResponseEntity.created(new URI("/api/projectserviceformfielddata/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectserviceformfielddata : Updates an existing projectserviceformfielddata.
     *
     * @param projectserviceformfielddataDTO the projectserviceformfielddataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectserviceformfielddataDTO,
     * or with status 400 (Bad Request) if the projectserviceformfielddataDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectserviceformfielddataDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectserviceformfielddata")
    @Timed
    public ResponseEntity<ProjectserviceformfielddataDTO> updateProjectserviceformfielddata(@Valid @RequestBody ProjectserviceformfielddataDTO projectserviceformfielddataDTO) throws URISyntaxException {
        log.debug("REST request to update Projectserviceformfielddata : {}", projectserviceformfielddataDTO);
        if (projectserviceformfielddataDTO.getId() == null) {
            return createProjectserviceformfielddata(projectserviceformfielddataDTO);
        }
        ProjectserviceformfielddataDTO result = projectserviceformfielddataService.save(projectserviceformfielddataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectserviceformfielddataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectserviceformfielddata : get all the projectserviceformfielddata.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectserviceformfielddata in body
     */
    @GetMapping("/projectserviceformfielddata")
    @Timed
    public List<ProjectserviceformfielddataDTO> getAllProjectserviceformfielddata() {
        log.debug("REST request to get all Projectserviceformfielddata");
        return projectserviceformfielddataService.findAll();
    }

    /**
     * GET  /projectserviceformfielddata/:id : get the "id" projectserviceformfielddata.
     *
     * @param id the id of the projectserviceformfielddataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectserviceformfielddataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectserviceformfielddata/{id}")
    @Timed
    public ResponseEntity<ProjectserviceformfielddataDTO> getProjectserviceformfielddata(@PathVariable String id) {
        log.debug("REST request to get Projectserviceformfielddata : {}", id);
        ProjectserviceformfielddataDTO projectserviceformfielddataDTO = projectserviceformfielddataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectserviceformfielddataDTO));
    }

    /**
     * DELETE  /projectserviceformfielddata/:id : delete the "id" projectserviceformfielddata.
     *
     * @param id the id of the projectserviceformfielddataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectserviceformfielddata/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectserviceformfielddata(@PathVariable String id) {
        log.debug("REST request to delete Projectserviceformfielddata : {}", id);
        projectserviceformfielddataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    @GetMapping("/projectserviceformfielddata/project/{projectid}")
    @Timed
    public List<ProjectserviceformfielddataDTO> getAllserviceFormFieldByProjectid(@PathVariable String projectid) {
        log.debug("REST request to get all Projectservicedetails");
        return projectserviceformfielddataService.findAllByProjectid(projectid);
    }

}
