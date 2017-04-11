package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ServiceFormFieldDTO;
import java.util.List;

/**
 * Service Interface for managing ServiceFormField.
 */
public interface ServiceFormFieldService {

    /**
     * Save a serviceFormField.
     *
     * @param serviceFormFieldDTO the entity to save
     * @return the persisted entity
     */
    ServiceFormFieldDTO save(ServiceFormFieldDTO serviceFormFieldDTO);

    /**
     *  Get all the serviceFormFields.
     *
     *  @return the list of entities
     */
    List<ServiceFormFieldDTO> findAll();

    /**
     *  Get the "id" serviceFormField.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ServiceFormFieldDTO findOne(String id);

    /**
     *  Delete the "id" serviceFormField.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
    List<ServiceFormFieldDTO> findAllByServiceid(String serviceid);

}
