package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ManufacturingunitsDTO;
import java.util.List;

/**
 * Service Interface for managing Manufacturingunits.
 */
public interface ManufacturingunitsService {

    /**
     * Save a manufacturingunits.
     *
     * @param manufacturingunitsDTO the entity to save
     * @return the persisted entity
     */
    ManufacturingunitsDTO save(ManufacturingunitsDTO manufacturingunitsDTO);

    /**
     *  Get all the manufacturingunits.
     *  
     *  @return the list of entities
     */
    List<ManufacturingunitsDTO> findAll();

    /**
     *  Get the "id" manufacturingunits.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ManufacturingunitsDTO findOne(String id);

    /**
     *  Delete the "id" manufacturingunits.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
