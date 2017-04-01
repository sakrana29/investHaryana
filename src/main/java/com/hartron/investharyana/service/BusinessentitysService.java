package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.BusinessentitysDTO;
import java.util.List;

/**
 * Service Interface for managing Businessentitys.
 */
public interface BusinessentitysService {

    /**
     * Save a businessentitys.
     *
     * @param businessentitysDTO the entity to save
     * @return the persisted entity
     */
    BusinessentitysDTO save(BusinessentitysDTO businessentitysDTO);

    /**
     *  Get all the businessentitys.
     *  
     *  @return the list of entities
     */
    List<BusinessentitysDTO> findAll();

    /**
     *  Get the "id" businessentitys.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BusinessentitysDTO findOne(String id);

    /**
     *  Delete the "id" businessentitys.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
