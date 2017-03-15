package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Regular_electrict_load_typeDTO;
import java.util.List;

/**
 * Service Interface for managing Regular_electrict_load_type.
 */
public interface Regular_electrict_load_typeService {

    /**
     * Save a regular_electrict_load_type.
     *
     * @param regular_electrict_load_typeDTO the entity to save
     * @return the persisted entity
     */
    Regular_electrict_load_typeDTO save(Regular_electrict_load_typeDTO regular_electrict_load_typeDTO);

    /**
     *  Get all the regular_electrict_load_types.
     *  
     *  @return the list of entities
     */
    List<Regular_electrict_load_typeDTO> findAll();

    /**
     *  Get the "id" regular_electrict_load_type.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Regular_electrict_load_typeDTO findOne(String id);

    /**
     *  Delete the "id" regular_electrict_load_type.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
