package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.security.SecurityUtils;
import com.hartron.investharyana.service.ProjectServicePaymentDetailsService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectServicePaymentDetailsDTO;
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
 * REST controller for managing ProjectServicePaymentDetails.
 */
@RestController
@RequestMapping("/api")
public class ProjectServicePaymentDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ProjectServicePaymentDetailsResource.class);

    private static final String ENTITY_NAME = "projectServicePaymentDetails";

    private final ProjectServicePaymentDetailsService projectServicePaymentDetailsService;

    public ProjectServicePaymentDetailsResource(ProjectServicePaymentDetailsService projectServicePaymentDetailsService) {
        this.projectServicePaymentDetailsService = projectServicePaymentDetailsService;
    }

    /**
     * POST  /project-service-payment-details : Create a new projectServicePaymentDetails.
     *
     * @param projectServicePaymentDetailsDTO the projectServicePaymentDetailsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectServicePaymentDetailsDTO, or with status 400 (Bad Request) if the projectServicePaymentDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-service-payment-details")
    @Timed
    public ResponseEntity<ProjectServicePaymentDetailsDTO> createProjectServicePaymentDetails(@RequestBody ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectServicePaymentDetails : {}", projectServicePaymentDetailsDTO);
        if (projectServicePaymentDetailsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectServicePaymentDetails cannot already have an ID")).body(null);
        }
        projectServicePaymentDetailsDTO.setPaymentDate(ZonedDateTime.now());
        projectServicePaymentDetailsDTO.setPaymentMadeBy(SecurityUtils.getCurrentUserLogin());
        ProjectServicePaymentDetailsDTO result = projectServicePaymentDetailsService.save(projectServicePaymentDetailsDTO);
        return ResponseEntity.created(new URI("/api/project-service-payment-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-service-payment-details : Updates an existing projectServicePaymentDetails.
     *
     * @param projectServicePaymentDetailsDTO the projectServicePaymentDetailsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectServicePaymentDetailsDTO,
     * or with status 400 (Bad Request) if the projectServicePaymentDetailsDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectServicePaymentDetailsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-service-payment-details")
    @Timed
    public ResponseEntity<ProjectServicePaymentDetailsDTO> updateProjectServicePaymentDetails(@RequestBody ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update ProjectServicePaymentDetails : {}", projectServicePaymentDetailsDTO);
        if (projectServicePaymentDetailsDTO.getId() == null) {
            return createProjectServicePaymentDetails(projectServicePaymentDetailsDTO);
        }
        ProjectServicePaymentDetailsDTO result = projectServicePaymentDetailsService.save(projectServicePaymentDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectServicePaymentDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-service-payment-details : get all the projectServicePaymentDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectServicePaymentDetails in body
     */
    @GetMapping("/project-service-payment-details")
    @Timed
    public List<ProjectServicePaymentDetailsDTO> getAllProjectServicePaymentDetails() {
        log.debug("REST request to get all ProjectServicePaymentDetails");
        return projectServicePaymentDetailsService.findAll();
    }

    /**
     * GET  /project-service-payment-details/:id : get the "id" projectServicePaymentDetails.
     *
     * @param id the id of the projectServicePaymentDetailsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectServicePaymentDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-service-payment-details/{id}")
    @Timed
    public ResponseEntity<ProjectServicePaymentDetailsDTO> getProjectServicePaymentDetails(@PathVariable String id) {
        log.debug("REST request to get ProjectServicePaymentDetails : {}", id);
        ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO = projectServicePaymentDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectServicePaymentDetailsDTO));
    }

    /**
     * DELETE  /project-service-payment-details/:id : delete the "id" projectServicePaymentDetails.
     *
     * @param id the id of the projectServicePaymentDetailsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-service-payment-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectServicePaymentDetails(@PathVariable String id) {
        log.debug("REST request to delete ProjectServicePaymentDetails : {}", id);
        projectServicePaymentDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/project-service-payment/projectandservice/{projectid}/{serviceid}")
    @Timed
    public List<ProjectServicePaymentDetailsDTO> getAllProjectServicePaymentDetailsByProjectAndServiceid(@PathVariable String projectid,@PathVariable String serviceid) {
        log.debug("REST request to get all ProjectServicePaymentDetails");
        return projectServicePaymentDetailsService.findAllByProjectAndServiceid(projectid,serviceid);
    }

}
