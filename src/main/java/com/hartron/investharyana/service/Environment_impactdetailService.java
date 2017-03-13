package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Environment_impactdetailDTO;
import java.util.List;

/**
 * Service Interface for managing Environment_impactdetail.
 */
public interface Environment_impactdetailService {

    /**
     * Save a environment_impactdetail.
     *
     * @param environment_impactdetailDTO the entity to save
     * @return the persisted entity
     */
    Environment_impactdetailDTO save(Environment_impactdetailDTO environment_impactdetailDTO);

    /**
     *  Get all the environment_impactdetails.
     *  
     *  @return the list of entities
     */
    List<Environment_impactdetailDTO> findAll();

    /**
     *  Get the "id" environment_impactdetail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Environment_impactdetailDTO findOne(String id);

    /**
     *  Delete the "id" environment_impactdetail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
