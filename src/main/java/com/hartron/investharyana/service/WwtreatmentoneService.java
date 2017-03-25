package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.WwtreatmentoneDTO;
import java.util.List;

/**
 * Service Interface for managing Wwtreatmentone.
 */
public interface WwtreatmentoneService {

    /**
     * Save a wwtreatmentone.
     *
     * @param wwtreatmentoneDTO the entity to save
     * @return the persisted entity
     */
    WwtreatmentoneDTO save(WwtreatmentoneDTO wwtreatmentoneDTO);

    /**
     *  Get all the wwtreatmentones.
     *  
     *  @return the list of entities
     */
    List<WwtreatmentoneDTO> findAll();

    /**
     *  Get the "id" wwtreatmentone.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WwtreatmentoneDTO findOne(String id);

    /**
     *  Delete the "id" wwtreatmentone.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
