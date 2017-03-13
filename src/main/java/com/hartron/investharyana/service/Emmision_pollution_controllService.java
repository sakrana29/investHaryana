package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Emmision_pollution_controllDTO;
import java.util.List;

/**
 * Service Interface for managing Emmision_pollution_controll.
 */
public interface Emmision_pollution_controllService {

    /**
     * Save a emmision_pollution_controll.
     *
     * @param emmision_pollution_controllDTO the entity to save
     * @return the persisted entity
     */
    Emmision_pollution_controllDTO save(Emmision_pollution_controllDTO emmision_pollution_controllDTO);

    /**
     *  Get all the emmision_pollution_controlls.
     *  
     *  @return the list of entities
     */
    List<Emmision_pollution_controllDTO> findAll();

    /**
     *  Get the "id" emmision_pollution_controll.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Emmision_pollution_controllDTO findOne(String id);

    /**
     *  Delete the "id" emmision_pollution_controll.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
