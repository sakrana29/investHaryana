package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectproductService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectproductDTO;
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
 * REST controller for managing Projectproduct.
 */
@RestController
@RequestMapping("/api")
public class ProjectproductResource {

    private final Logger log = LoggerFactory.getLogger(ProjectproductResource.class);

    private static final String ENTITY_NAME = "projectproduct";
        
    private final ProjectproductService projectproductService;

    public ProjectproductResource(ProjectproductService projectproductService) {
        this.projectproductService = projectproductService;
    }

    /**
     * POST  /projectproducts : Create a new projectproduct.
     *
     * @param projectproductDTO the projectproductDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectproductDTO, or with status 400 (Bad Request) if the projectproduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectproducts")
    @Timed
    public ResponseEntity<ProjectproductDTO> createProjectproduct(@RequestBody ProjectproductDTO projectproductDTO) throws URISyntaxException {
        log.debug("REST request to save Projectproduct : {}", projectproductDTO);
        if (projectproductDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectproduct cannot already have an ID")).body(null);
        }
        ProjectproductDTO result = projectproductService.save(projectproductDTO);
        return ResponseEntity.created(new URI("/api/projectproducts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectproducts : Updates an existing projectproduct.
     *
     * @param projectproductDTO the projectproductDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectproductDTO,
     * or with status 400 (Bad Request) if the projectproductDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectproductDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectproducts")
    @Timed
    public ResponseEntity<ProjectproductDTO> updateProjectproduct(@RequestBody ProjectproductDTO projectproductDTO) throws URISyntaxException {
        log.debug("REST request to update Projectproduct : {}", projectproductDTO);
        if (projectproductDTO.getId() == null) {
            return createProjectproduct(projectproductDTO);
        }
        ProjectproductDTO result = projectproductService.save(projectproductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectproductDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectproducts : get all the projectproducts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectproducts in body
     */
    @GetMapping("/projectproducts")
    @Timed
    public List<ProjectproductDTO> getAllProjectproducts() {
        log.debug("REST request to get all Projectproducts");
        return projectproductService.findAll();
    }

    /**
     * GET  /projectproducts/:id : get the "id" projectproduct.
     *
     * @param id the id of the projectproductDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectproductDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectproducts/{id}")
    @Timed
    public ResponseEntity<ProjectproductDTO> getProjectproduct(@PathVariable String id) {
        log.debug("REST request to get Projectproduct : {}", id);
        ProjectproductDTO projectproductDTO = projectproductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectproductDTO));
    }

    /**
     * DELETE  /projectproducts/:id : delete the "id" projectproduct.
     *
     * @param id the id of the projectproductDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectproducts/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectproduct(@PathVariable String id) {
        log.debug("REST request to delete Projectproduct : {}", id);
        projectproductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
