package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.DepartmentServiceService;
import com.hartron.investharyana.service.ProjectservicedetailService;
import com.hartron.investharyana.service.dto.DepartmentServiceDTO;
import com.hartron.investharyana.service.dto.ProjectservicedetailDTO;

import com.hartron.investharyana.service.dto.DepartmentServiceDetailsProjectWiseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing DepartmentService.
 */
@RestController
@RequestMapping("/api")
public class DepartmentServiceDetailsProjectWiseResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceDetailsProjectWiseResource.class);

//    private static final String ENTITY_NAME = "departmentService";

    private final DepartmentServiceService departmentServiceService;
    private final ProjectservicedetailService projectservicedetailService;


    public DepartmentServiceDetailsProjectWiseResource(DepartmentServiceService departmentServiceService, ProjectservicedetailService projectservicedetailService) {
        this.departmentServiceService = departmentServiceService;
        this.projectservicedetailService = projectservicedetailService;
    }

    /**
     * POST  /department-services : Create a new departmentService.
     *
     * @param departmentServiceDTO the departmentServiceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new departmentServiceDTO, or with status 400 (Bad Request) if the departmentService has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
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
//
//    /**
//     * GET  /department-services : get all the departmentServices.
//     *
//     * @return the ResponseEntity with status 200 (OK) and the list of departmentServices in body
//     */
//    @GetMapping("/department-services")
//    @Timed
//    public List<DepartmentServiceDTO> getAllDepartmentServices() {
//        log.debug("REST request to get all DepartmentServices");
//        return
// departmentServiceService.findAll();
//    }
//
    @GetMapping("/ProjectWiseServiceDetails/{projectid}")
    @Timed
    public List<DepartmentServiceDetailsProjectWiseDTO> getProjectWiseServiceStatus(@PathVariable String projectid) {
        log.debug("REST request to get DepartmentServices : {}", projectid);

        List<DepartmentServiceDetailsProjectWiseDTO> departmentServiceDetailsProjectWiseDTOList = new ArrayList<>();
//        DepartmentServiceDTO departmentService = new ArrayList<>();
        List<DepartmentServiceDTO> departmentServiceDTO = new ArrayList<>();
        departmentServiceDTO = departmentServiceService.findAll();
        List<ProjectservicedetailDTO> projectservicedetailDTOSList = new ArrayList<>();
//        projectservicedetailDTOSList =  projectservicedetailService.findByProject(projectid);
        projectservicedetailDTOSList =  projectservicedetailService.findAll();
        for(int i = 0; i<departmentServiceDTO.size(); i++)
        {
            DepartmentServiceDetailsProjectWiseDTO departmentServiceDetailsProjectWiseDTO = new DepartmentServiceDetailsProjectWiseDTO();
            boolean flagIsRequired = false;
            boolean flagIsAssigned = false;
            UUID id =null;
            for(int j=0; j<projectservicedetailDTOSList.size();j++){
                String sid= departmentServiceDTO.get(i).getId().toString();
                String ssid= projectservicedetailDTOSList.get(j).getServiceid().toString();
                if(sid.equals(ssid)) {
                    flagIsRequired = (Boolean) projectservicedetailDTOSList.get(j).getIsrequired();
                    flagIsAssigned = (Boolean) projectservicedetailDTOSList.get(j).getIsassigned();
                    id = (UUID) projectservicedetailDTOSList.get(j).getId();
//                if(departmentServiceDTO.get(i).getId() == projectservicedetailDTOSList.get(j).getServiceid())
                }
                else
                {

                }

            }
            departmentServiceDetailsProjectWiseDTO.setId(id);
            departmentServiceDetailsProjectWiseDTO.setIsrequired(flagIsRequired);
            departmentServiceDetailsProjectWiseDTO.setIsassigned(flagIsAssigned);
            departmentServiceDetailsProjectWiseDTO.setDepartmentid(departmentServiceDTO.get(i).getDepartmentid());
            departmentServiceDetailsProjectWiseDTO.setDepartmentname(departmentServiceDTO.get(i).getDepartmentname());
            departmentServiceDetailsProjectWiseDTO.setServiceid(departmentServiceDTO.get(i).getId());
            departmentServiceDetailsProjectWiseDTO.setServiceName(departmentServiceDTO.get(i).getServiceName());
            departmentServiceDetailsProjectWiseDTO.setDuration(departmentServiceDTO.get(i).getDuration());
            departmentServiceDetailsProjectWiseDTO.setStage(departmentServiceDTO.get(i).getStage());
            departmentServiceDetailsProjectWiseDTO.setServiceDescription(departmentServiceDTO.get(i).getServiceDescription());

            departmentServiceDetailsProjectWiseDTOList.add(departmentServiceDetailsProjectWiseDTO);
//         if(departmentServiceDTO.get(i).getId() == projectservicedetailDTOSList.get(i).getServiceid())

        }

        return departmentServiceDetailsProjectWiseDTOList;
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
