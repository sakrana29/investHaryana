package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.WatersupplysourceDTO;
import java.util.List;

/**
 * Service Interface for managing Watersupplysource.
 */
public interface WatersupplysourceService {

    /**
     * Save a watersupplysource.
     *
     * @param watersupplysourceDTO the entity to save
     * @return the persisted entity
     */
    WatersupplysourceDTO save(WatersupplysourceDTO watersupplysourceDTO);

    /**
     *  Get all the watersupplysources.
     *  
     *  @return the list of entities
     */
    List<WatersupplysourceDTO> findAll();

    /**
     *  Get the "id" watersupplysource.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WatersupplysourceDTO findOne(String id);

    /**
     *  Delete the "id" watersupplysource.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
