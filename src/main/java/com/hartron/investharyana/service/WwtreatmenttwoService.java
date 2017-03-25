package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.WwtreatmenttwoDTO;
import java.util.List;

/**
 * Service Interface for managing Wwtreatmenttwo.
 */
public interface WwtreatmenttwoService {

    /**
     * Save a wwtreatmenttwo.
     *
     * @param wwtreatmenttwoDTO the entity to save
     * @return the persisted entity
     */
    WwtreatmenttwoDTO save(WwtreatmenttwoDTO wwtreatmenttwoDTO);

    /**
     *  Get all the wwtreatmenttwos.
     *  
     *  @return the list of entities
     */
    List<WwtreatmenttwoDTO> findAll();

    /**
     *  Get the "id" wwtreatmenttwo.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WwtreatmenttwoDTO findOne(String id);

    /**
     *  Delete the "id" wwtreatmenttwo.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
