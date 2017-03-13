package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ManufacturingdetailDTO;
import java.util.List;

/**
 * Service Interface for managing Manufacturingdetail.
 */
public interface ManufacturingdetailService {

    /**
     * Save a manufacturingdetail.
     *
     * @param manufacturingdetailDTO the entity to save
     * @return the persisted entity
     */
    ManufacturingdetailDTO save(ManufacturingdetailDTO manufacturingdetailDTO);

    /**
     *  Get all the manufacturingdetails.
     *  
     *  @return the list of entities
     */
    List<ManufacturingdetailDTO> findAll();

    /**
     *  Get the "id" manufacturingdetail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ManufacturingdetailDTO findOne(String id);

    /**
     *  Delete the "id" manufacturingdetail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
