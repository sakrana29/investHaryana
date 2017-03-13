package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ServiceFormFieldService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ServiceFormFieldDTO;
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
 * REST controller for managing ServiceFormField.
 */
@RestController
@RequestMapping("/api")
public class ServiceFormFieldResource {

    private final Logger log = LoggerFactory.getLogger(ServiceFormFieldResource.class);

    private static final String ENTITY_NAME = "serviceFormField";

    private final ServiceFormFieldService serviceFormFieldService;

    public ServiceFormFieldResource(ServiceFormFieldService serviceFormFieldService) {
        this.serviceFormFieldService = serviceFormFieldService;
    }

    /**
     * POST  /service-form-fields : Create a new serviceFormField.
     *
     * @param serviceFormFieldDTO the serviceFormFieldDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serviceFormFieldDTO, or with status 400 (Bad Request) if the serviceFormField has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/service-form-fields")
    @Timed
    public ResponseEntity<ServiceFormFieldDTO> createServiceFormField(@Valid @RequestBody ServiceFormFieldDTO serviceFormFieldDTO) throws URISyntaxException {
        log.debug("REST request to save ServiceFormField : {}", serviceFormFieldDTO);
        if (serviceFormFieldDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new serviceFormField cannot already have an ID")).body(null);
        }
        ServiceFormFieldDTO result = serviceFormFieldService.save(serviceFormFieldDTO);
        return ResponseEntity.created(new URI("/api/service-form-fields/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /service-form-fields : Updates an existing serviceFormField.
     *
     * @param serviceFormFieldDTO the serviceFormFieldDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serviceFormFieldDTO,
     * or with status 400 (Bad Request) if the serviceFormFieldDTO is not valid,
     * or with status 500 (Internal Server Error) if the serviceFormFieldDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/service-form-fields")
    @Timed
    public ResponseEntity<ServiceFormFieldDTO> updateServiceFormField(@Valid @RequestBody ServiceFormFieldDTO serviceFormFieldDTO) throws URISyntaxException {
        log.debug("REST request to update ServiceFormField : {}", serviceFormFieldDTO);
        if (serviceFormFieldDTO.getId() == null) {
            return createServiceFormField(serviceFormFieldDTO);
        }
        ServiceFormFieldDTO result = serviceFormFieldService.save(serviceFormFieldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serviceFormFieldDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /service-form-fields : get all the serviceFormFields.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of serviceFormFields in body
     */
    @GetMapping("/service-form-fields")
    @Timed
    public List<ServiceFormFieldDTO> getAllServiceFormFields() {
        log.debug("REST request to get all ServiceFormFields");
        return serviceFormFieldService.findAll();
    }

    /**
     * GET  /service-form-fields : get all the serviceFormFields by Deptid.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of serviceFormFields in body
     */
    @GetMapping("/service-form-fields/Service/{serviceid}")
    @Timed
    public List<ServiceFormFieldDTO> getAllServiceFormFieldsByServiceId(String serviceid) {
        log.debug("REST request to get all ServiceFormFields by Serviceid");
        return serviceFormFieldService.findServiceFormFieldsByServiceId(serviceid);
    }

    /**
     * GET  /service-form-fields/:id : get the "id" serviceFormField.
     *
     * @param id the id of the serviceFormFieldDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serviceFormFieldDTO, or with status 404 (Not Found)
     */
    @GetMapping("/service-form-fields/{id}")
    @Timed
    public ResponseEntity<ServiceFormFieldDTO> getServiceFormField(@PathVariable String id) {
        log.debug("REST request to get ServiceFormField : {}", id);
        ServiceFormFieldDTO serviceFormFieldDTO = serviceFormFieldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(serviceFormFieldDTO));
    }

    /**
     * DELETE  /service-form-fields/:id : delete the "id" serviceFormField.
     *
     * @param id the id of the serviceFormFieldDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/service-form-fields/{id}")
    @Timed
    public ResponseEntity<Void> deleteServiceFormField(@PathVariable String id) {
        log.debug("REST request to delete ServiceFormField : {}", id);
        serviceFormFieldService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
