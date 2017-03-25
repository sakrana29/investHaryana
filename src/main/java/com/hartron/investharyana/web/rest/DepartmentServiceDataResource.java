package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.DepartmentService;
import com.hartron.investharyana.service.DepartmentServiceService;
import com.hartron.investharyana.service.dto.DepartmentDTO;
import com.hartron.investharyana.service.dto.DepartmentServiceDTO;
import com.hartron.investharyana.service.dto.DepartmentServiceDataDTO;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing DepartmentService.
 */
@RestController
@RequestMapping("/api")
public class DepartmentServiceDataResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceDataResource.class);

//    private static final String ENTITY_NAME = "departmentService";

    private final DepartmentServiceService departmentServiceService;
    private final DepartmentService departmentService;

    public DepartmentServiceDataResource(DepartmentServiceService departmentServiceService, DepartmentService departmentService) {
        this.departmentServiceService = departmentServiceService;
        this.departmentService = departmentService;
    }
//
//    /**
//     * POST  /department-services : Create a new departmentService.
//     *
//     * @param departmentServiceDTO the departmentServiceDTO to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new departmentServiceDTO, or with status 400 (Bad Request) if the departmentService has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/department-services")
//    @Timed
//    public ResponseEntity<DepartmentServiceDTO> createDepartmentService(@Valid @RequestBody DepartmentServiceDTO departmentServiceDTO) throws URISyntaxException {
//        log.debug("REST request to save DepartmentService : {}", departmentServiceDTO);
//        if (departmentServiceDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new departmentService cannot already have an ID")).body(null);
//        }
//        DepartmentServiceDTO result = departmentServiceService.save(departmentServiceDTO);
//        return ResponseEntity.created(new URI("/api/department-services/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * PUT  /department-services : Updates an existing departmentService.
//     *
//     * @param departmentServiceDTO the departmentServiceDTO to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated departmentServiceDTO,
//     * or with status 400 (Bad Request) if the departmentServiceDTO is not valid,
//     * or with status 500 (Internal Server Error) if the departmentServiceDTO couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/department-services")
//    @Timed
//    public ResponseEntity<DepartmentServiceDTO> updateDepartmentService(@Valid @RequestBody DepartmentServiceDTO departmentServiceDTO) throws URISyntaxException {
//        log.debug("REST request to update DepartmentService : {}", departmentServiceDTO);
//        if (departmentServiceDTO.getId() == null) {
//            return createDepartmentService(departmentServiceDTO);
//        }
//        DepartmentServiceDTO result = departmentServiceService.save(departmentServiceDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, departmentServiceDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /department-services : get all the departmentServices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of departmentServices in body
     */
    @GetMapping("/department-servicesdata")
    @Timed
    public List<DepartmentServiceDataDTO> getAllDepartmentServicesData() {
        log.debug("REST request to get all DepartmentServicesData");

        List<DepartmentServiceDataDTO> departmentServiceDataDTOList=new ArrayList<>();

        List<DepartmentServiceDTO> departmentServiceDTOList=departmentServiceService.findAll();

        for(int i=0;i<departmentServiceDTOList.size();i++)
        {
            DepartmentServiceDataDTO departmentServiceDataDTO=new DepartmentServiceDataDTO();
            DepartmentServiceDTO departmentServiceDTO = departmentServiceDTOList.get(i);
            DepartmentDTO dept = departmentService.findOne(departmentServiceDTO.getDepartmentID().toString());

            departmentServiceDataDTO.setDepartmentname(dept.getDepartmentname());
            departmentServiceDataDTO.setDepartmentID(dept.getId());
            departmentServiceDataDTO.setId(departmentServiceDTO.getId());
            departmentServiceDataDTO.setServiceName(departmentServiceDTO.getServiceName());
            departmentServiceDataDTO.setStage(departmentServiceDTO.getStage());
//            departmentServiceDataDTO.setDepartmentname(dept.getDepartmentname());
            departmentServiceDataDTOList.add(departmentServiceDataDTO);
        }
        return departmentServiceDataDTOList;

    }

//    /**
//     * GET  /department-services/:id : get the "id" departmentService.
//     *
//     * @param id the id of the departmentServiceDTO to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the departmentServiceDTO, or with status 404 (Not Found)
//     */
//    @GetMapping("/department-services/{id}")
//    @Timed
//    public ResponseEntity<DepartmentServiceDTO> getDepartmentService(@PathVariable String id) {
//        log.debug("REST request to get DepartmentService : {}", id);
//        DepartmentServiceDTO departmentServiceDTO = departmentServiceService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(departmentServiceDTO));
//    }
//
//    /**
//     * DELETE  /department-services/:id : delete the "id" departmentService.
//     *
//     * @param id the id of the departmentServiceDTO to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/department-services/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteDepartmentService(@PathVariable String id) {
//        log.debug("REST request to delete DepartmentService : {}", id);
//        departmentServiceService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }

}
