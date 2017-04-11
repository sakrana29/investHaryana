package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ServiceFormFieldService;
import com.hartron.investharyana.service.ProjectserviceformfielddataService;
import com.hartron.investharyana.service.dto.ProjectServiceReportInfoDTO;
import com.hartron.investharyana.service.dto.ProjectserviceformfielddataDTO;
import com.hartron.investharyana.service.dto.ServiceFormFieldDTO;
import com.hartron.investharyana.service.dto.ProjectserviceformfielddataCollectionDTO;
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
import java.util.stream.Collectors;

/**
 * REST controller for managing ServiceFormField.
 */
@RestController
@RequestMapping("/api")
public class ServiceFormFieldDataCollectionResource {

    private final Logger log = LoggerFactory.getLogger(ServiceFormFieldDataCollectionResource.class);

    private static final String ENTITY_NAME = "serviceFormField";

    private final ServiceFormFieldService serviceFormFieldService;
    private final ProjectserviceformfielddataService projectserviceformfielddataService;

    public ServiceFormFieldDataCollectionResource(ServiceFormFieldService serviceFormFieldService, ProjectserviceformfielddataService projectserviceformfielddataService) {
        this.serviceFormFieldService = serviceFormFieldService;
        this.projectserviceformfielddataService = projectserviceformfielddataService;
    }

    /**
     * POST  /service-form-fields : Create a new serviceFormField.
     *
     * @param serviceFormFieldDTO the serviceFormFieldDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serviceFormFieldDTO, or with status 400 (Bad Request) if the serviceFormField has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/service-form-fields")
//    @Timed
//    public ResponseEntity<ServiceFormFieldDTO> createServiceFormField(@Valid @RequestBody ServiceFormFieldDTO serviceFormFieldDTO) throws URISyntaxException {
//        log.debug("REST request to save ServiceFormField : {}", serviceFormFieldDTO);
//        if (serviceFormFieldDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new serviceFormField cannot already have an ID")).body(null);
//        }
//        ServiceFormFieldDTO result = serviceFormFieldService.save(serviceFormFieldDTO);
//        return ResponseEntity.created(new URI("/api/service-form-fields/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * PUT  /service-form-fields : Updates an existing serviceFormField.
//     *
//     * @param serviceFormFieldDTO the serviceFormFieldDTO to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated serviceFormFieldDTO,
//     * or with status 400 (Bad Request) if the serviceFormFieldDTO is not valid,
//     * or with status 500 (Internal Server Error) if the serviceFormFieldDTO couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/service-form-fields")
//    @Timed
//    public ResponseEntity<ServiceFormFieldDTO> updateServiceFormField(@Valid @RequestBody ServiceFormFieldDTO serviceFormFieldDTO) throws URISyntaxException {
//        log.debug("REST request to update ServiceFormField : {}", serviceFormFieldDTO);
//        if (serviceFormFieldDTO.getId() == null) {
//            return createServiceFormField(serviceFormFieldDTO);
//        }
//        ServiceFormFieldDTO result = serviceFormFieldService.save(serviceFormFieldDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serviceFormFieldDTO.getId().toString()))
//            .body(result);
//    }

    @GetMapping("/serviceFormFieldDtaCollection/service/{serviceid}")
    @Timed
    public List<ProjectserviceformfielddataCollectionDTO> getAllserviceFormFieldDataCollectionByServiceid(@PathVariable String serviceid, String projectid) {
        log.debug("REST request to get all Projectservicedetails");
        List<ProjectserviceformfielddataDTO> projectserviceformfielddataDTOList = projectserviceformfielddataService.findAllByProjectid(projectid);
        List<ProjectserviceformfielddataCollectionDTO> listDataCollection =new ArrayList<>();
        List<ProjectserviceformfielddataDTO> fieldDataByService = projectserviceformfielddataDTOList.stream()
            .filter(p -> p.getServiceid().toString().equals(serviceid)).collect(Collectors.toList());
        if(fieldDataByService.size()>0)
        {
           for (int i = 0; i < projectserviceformfielddataDTOList.size(); i++) {
            ProjectserviceformfielddataCollectionDTO projectserviceformfielddataCollectionDTO = new ProjectserviceformfielddataCollectionDTO();
            ProjectserviceformfielddataDTO projectserviceformfielddataDTO = fieldDataByService.get(i);
            projectserviceformfielddataCollectionDTO.setId(projectserviceformfielddataDTO.getId());
            projectserviceformfielddataCollectionDTO.setFormfieldName(projectserviceformfielddataDTO.getFormfieldName());
            projectserviceformfielddataCollectionDTO.setProjectid(projectserviceformfielddataDTO.getProjectid());
            projectserviceformfielddataCollectionDTO.setServiceid(projectserviceformfielddataDTO.getServiceid());
            projectserviceformfielddataCollectionDTO.setServiceformfieldid(projectserviceformfielddataDTO.getServiceformfieldid());
            projectserviceformfielddataCollectionDTO.setFormfieldvalue(projectserviceformfielddataDTO.getFormfieldvalue());
            projectserviceformfielddataCollectionDTO.setFormfieldOrder(projectserviceformfielddataDTO.getFormfieldOrder());
            projectserviceformfielddataCollectionDTO.setFormfieldtype(projectserviceformfielddataDTO.getFormfieldtype());
            projectserviceformfielddataCollectionDTO.setFormtypeOption(projectserviceformfielddataDTO.getFormtypeOption());
            listDataCollection.add(projectserviceformfielddataCollectionDTO);

        }

        }
        else {
//
            List<ServiceFormFieldDTO> fieldData = serviceFormFieldService.findAllByServiceid(serviceid);
            for (int i = 0; i < fieldData.size(); i++) {
                ProjectserviceformfielddataCollectionDTO projectserviceformfielddataCollectionDTO = new ProjectserviceformfielddataCollectionDTO();
                ServiceFormFieldDTO serviceFormFieldDTO = fieldData.get(i);
                projectserviceformfielddataCollectionDTO.setId(null);
                projectserviceformfielddataCollectionDTO.setFormfieldName(serviceFormFieldDTO.getFieldName());
                projectserviceformfielddataCollectionDTO.setProjectid(null);
                projectserviceformfielddataCollectionDTO.setServiceid(serviceFormFieldDTO.getServiceID());
                projectserviceformfielddataCollectionDTO.setServiceformfieldid(serviceFormFieldDTO.getId());
                projectserviceformfielddataCollectionDTO.setFormfieldvalue(null);
                projectserviceformfielddataCollectionDTO.setFormfieldOrder(serviceFormFieldDTO.getFieldRenderingOrder());
                projectserviceformfielddataCollectionDTO.setFormfieldtype(serviceFormFieldDTO.getFieldType());
                projectserviceformfielddataCollectionDTO.setFormtypeOption(serviceFormFieldDTO.getFieldTypeOption());
                listDataCollection.add(projectserviceformfielddataCollectionDTO);
            }
        }
        return listDataCollection;

    }
}
