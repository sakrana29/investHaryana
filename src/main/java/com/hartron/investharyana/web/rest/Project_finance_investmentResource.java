package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Project_finance_investmentService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Project_finance_investmentDTO;
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
 * REST controller for managing Project_finance_investment.
 */
@RestController
@RequestMapping("/api")
public class Project_finance_investmentResource {

    private final Logger log = LoggerFactory.getLogger(Project_finance_investmentResource.class);

    private static final String ENTITY_NAME = "project_finance_investment";
        
    private final Project_finance_investmentService project_finance_investmentService;

    public Project_finance_investmentResource(Project_finance_investmentService project_finance_investmentService) {
        this.project_finance_investmentService = project_finance_investmentService;
    }

    /**
     * POST  /project-finance-investments : Create a new project_finance_investment.
     *
     * @param project_finance_investmentDTO the project_finance_investmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new project_finance_investmentDTO, or with status 400 (Bad Request) if the project_finance_investment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-finance-investments")
    @Timed
    public ResponseEntity<Project_finance_investmentDTO> createProject_finance_investment(@RequestBody Project_finance_investmentDTO project_finance_investmentDTO) throws URISyntaxException {
        log.debug("REST request to save Project_finance_investment : {}", project_finance_investmentDTO);
        if (project_finance_investmentDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new project_finance_investment cannot already have an ID")).body(null);
        }
        Project_finance_investmentDTO result = project_finance_investmentService.save(project_finance_investmentDTO);
        return ResponseEntity.created(new URI("/api/project-finance-investments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-finance-investments : Updates an existing project_finance_investment.
     *
     * @param project_finance_investmentDTO the project_finance_investmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated project_finance_investmentDTO,
     * or with status 400 (Bad Request) if the project_finance_investmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the project_finance_investmentDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-finance-investments")
    @Timed
    public ResponseEntity<Project_finance_investmentDTO> updateProject_finance_investment(@RequestBody Project_finance_investmentDTO project_finance_investmentDTO) throws URISyntaxException {
        log.debug("REST request to update Project_finance_investment : {}", project_finance_investmentDTO);
        if (project_finance_investmentDTO.getId() == null) {
            return createProject_finance_investment(project_finance_investmentDTO);
        }
        Project_finance_investmentDTO result = project_finance_investmentService.save(project_finance_investmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, project_finance_investmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-finance-investments : get all the project_finance_investments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of project_finance_investments in body
     */
    @GetMapping("/project-finance-investments")
    @Timed
    public List<Project_finance_investmentDTO> getAllProject_finance_investments() {
        log.debug("REST request to get all Project_finance_investments");
        return project_finance_investmentService.findAll();
    }

    /**
     * GET  /project-finance-investments/:id : get the "id" project_finance_investment.
     *
     * @param id the id of the project_finance_investmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the project_finance_investmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-finance-investments/{id}")
    @Timed
    public ResponseEntity<Project_finance_investmentDTO> getProject_finance_investment(@PathVariable String id) {
        log.debug("REST request to get Project_finance_investment : {}", id);
        Project_finance_investmentDTO project_finance_investmentDTO = project_finance_investmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(project_finance_investmentDTO));
    }

    /**
     * DELETE  /project-finance-investments/:id : delete the "id" project_finance_investment.
     *
     * @param id the id of the project_finance_investmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-finance-investments/{id}")
    @Timed
    public ResponseEntity<Void> deleteProject_finance_investment(@PathVariable String id) {
        log.debug("REST request to delete Project_finance_investment : {}", id);
        project_finance_investmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
