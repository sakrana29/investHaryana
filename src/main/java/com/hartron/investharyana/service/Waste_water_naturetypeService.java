package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Waste_water_naturetypeDTO;
import java.util.List;

/**
 * Service Interface for managing Waste_water_naturetype.
 */
public interface Waste_water_naturetypeService {

    /**
     * Save a waste_water_naturetype.
     *
     * @param waste_water_naturetypeDTO the entity to save
     * @return the persisted entity
     */
    Waste_water_naturetypeDTO save(Waste_water_naturetypeDTO waste_water_naturetypeDTO);

    /**
     *  Get all the waste_water_naturetypes.
     *  
     *  @return the list of entities
     */
    List<Waste_water_naturetypeDTO> findAll();

    /**
     *  Get the "id" waste_water_naturetype.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Waste_water_naturetypeDTO findOne(String id);

    /**
     *  Delete the "id" waste_water_naturetype.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
