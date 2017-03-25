package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.DepartmentserviceService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.DepartmentserviceDTO;
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
 * REST controller for managing Departmentservice.
 */
@RestController
@RequestMapping("/api")
public class DepartmentserviceResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentserviceResource.class);

    private static final String ENTITY_NAME = "departmentservice";
        
    private final DepartmentserviceService departmentserviceService;

    public DepartmentserviceResource(DepartmentserviceService departmentserviceService) {
        this.departmentserviceService = departmentserviceService;
    }

    /**
     * POST  /departmentservices : Create a new departmentservice.
     *
     * @param departmentserviceDTO the departmentserviceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new departmentserviceDTO, or with status 400 (Bad Request) if the departmentservice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/departmentservices")
    @Timed
    public ResponseEntity<DepartmentserviceDTO> createDepartmentservice(@RequestBody DepartmentserviceDTO departmentserviceDTO) throws URISyntaxException {
        log.debug("REST request to save Departmentservice : {}", departmentserviceDTO);
        if (departmentserviceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new departmentservice cannot already have an ID")).body(null);
        }
        DepartmentserviceDTO result = departmentserviceService.save(departmentserviceDTO);
        return ResponseEntity.created(new URI("/api/departmentservices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /departmentservices : Updates an existing departmentservice.
     *
     * @param departmentserviceDTO the departmentserviceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated departmentserviceDTO,
     * or with status 400 (Bad Request) if the departmentserviceDTO is not valid,
     * or with status 500 (Internal Server Error) if the departmentserviceDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/departmentservices")
    @Timed
    public ResponseEntity<DepartmentserviceDTO> updateDepartmentservice(@RequestBody DepartmentserviceDTO departmentserviceDTO) throws URISyntaxException {
        log.debug("REST request to update Departmentservice : {}", departmentserviceDTO);
        if (departmentserviceDTO.getId() == null) {
            return createDepartmentservice(departmentserviceDTO);
        }
        DepartmentserviceDTO result = departmentserviceService.save(departmentserviceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, departmentserviceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /departmentservices : get all the departmentservices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of departmentservices in body
     */
    @GetMapping("/departmentservices")
    @Timed
    public List<DepartmentserviceDTO> getAllDepartmentservices() {
        log.debug("REST request to get all Departmentservices");
        return departmentserviceService.findAll();
    }

    /**
     * GET  /departmentservices/:id : get the "id" departmentservice.
     *
     * @param id the id of the departmentserviceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the departmentserviceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/departmentservices/{id}")
    @Timed
    public ResponseEntity<DepartmentserviceDTO> getDepartmentservice(@PathVariable String id) {
        log.debug("REST request to get Departmentservice : {}", id);
        DepartmentserviceDTO departmentserviceDTO = departmentserviceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(departmentserviceDTO));
    }

    /**
     * DELETE  /departmentservices/:id : delete the "id" departmentservice.
     *
     * @param id the id of the departmentserviceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/departmentservices/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepartmentservice(@PathVariable String id) {
        log.debug("REST request to delete Departmentservice : {}", id);
        departmentserviceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
