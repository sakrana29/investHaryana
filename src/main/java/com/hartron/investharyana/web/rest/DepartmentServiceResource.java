package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.DepartmentServiceService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.DepartmentServiceDTO;
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
 * REST controller for managing DepartmentService.
 */
@RestController
@RequestMapping("/api")
public class DepartmentServiceResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceResource.class);

    private static final String ENTITY_NAME = "departmentService";

    private final DepartmentServiceService departmentServiceService;

    public DepartmentServiceResource(DepartmentServiceService departmentServiceService) {
        this.departmentServiceService = departmentServiceService;
    }

    /**
     * POST  /department-services : Create a new departmentService.
     *
     * @param departmentServiceDTO the departmentServiceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new departmentServiceDTO, or with status 400 (Bad Request) if the departmentService has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/department-services")
    @Timed
    public ResponseEntity<DepartmentServiceDTO> createDepartmentService(@Valid @RequestBody DepartmentServiceDTO departmentServiceDTO) throws URISyntaxException {
        log.debug("REST request to save DepartmentService : {}", departmentServiceDTO);
        if (departmentServiceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new departmentService cannot already have an ID")).body(null);
        }
        DepartmentServiceDTO result = departmentServiceService.save(departmentServiceDTO);
        return ResponseEntity.created(new URI("/api/department-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /department-services : Updates an existing departmentService.
     *
     * @param departmentServiceDTO the departmentServiceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated departmentServiceDTO,
     * or with status 400 (Bad Request) if the departmentServiceDTO is not valid,
     * or with status 500 (Internal Server Error) if the departmentServiceDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/department-services")
    @Timed
    public ResponseEntity<DepartmentServiceDTO> updateDepartmentService(@Valid @RequestBody DepartmentServiceDTO departmentServiceDTO) throws URISyntaxException {
        log.debug("REST request to update DepartmentService : {}", departmentServiceDTO);
        if (departmentServiceDTO.getId() == null) {
            return createDepartmentService(departmentServiceDTO);
        }
        DepartmentServiceDTO result = departmentServiceService.save(departmentServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, departmentServiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /department-services : get all the departmentServices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of departmentServices in body
     */
    @GetMapping("/department-services")
    @Timed
    public List<DepartmentServiceDTO> getAllDepartmentServices() {
        log.debug("REST request to get all DepartmentServices");
        return departmentServiceService.findAll();
    }

    /**
     * GET  /department-services/:id : get the "id" departmentService.
     *
     * @param id the id of the departmentServiceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the departmentServiceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/department-services/{id}")
    @Timed
    public ResponseEntity<DepartmentServiceDTO> getDepartmentService(@PathVariable String id) {
        log.debug("REST request to get DepartmentService : {}", id);
        DepartmentServiceDTO departmentServiceDTO = departmentServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(departmentServiceDTO));
    }

    @GetMapping("/department-services/department/{deptid}")
    @Timed
    public List<DepartmentServiceDTO> getServicesByDepartmentid(@PathVariable String deptid) {
        log.debug("REST request to get DepartmentService by Departmentid : {}", deptid);
        return departmentServiceService.findServiceByDepartmentId(deptid);
    }

    /**
     * DELETE  /department-services/:id : delete the "id" departmentService.
     *
     * @param id the id of the departmentServiceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/department-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepartmentService(@PathVariable String id) {
        log.debug("REST request to delete DepartmentService : {}", id);
        departmentServiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
