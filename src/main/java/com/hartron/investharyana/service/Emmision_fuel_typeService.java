package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Emmision_fuel_typeDTO;
import java.util.List;

/**
 * Service Interface for managing Emmision_fuel_type.
 */
public interface Emmision_fuel_typeService {

    /**
     * Save a emmision_fuel_type.
     *
     * @param emmision_fuel_typeDTO the entity to save
     * @return the persisted entity
     */
    Emmision_fuel_typeDTO save(Emmision_fuel_typeDTO emmision_fuel_typeDTO);

    /**
     *  Get all the emmision_fuel_types.
     *  
     *  @return the list of entities
     */
    List<Emmision_fuel_typeDTO> findAll();

    /**
     *  Get the "id" emmision_fuel_type.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Emmision_fuel_typeDTO findOne(String id);

    /**
     *  Delete the "id" emmision_fuel_type.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
