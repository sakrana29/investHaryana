package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.WwtreatmentthreeDTO;
import java.util.List;

/**
 * Service Interface for managing Wwtreatmentthree.
 */
public interface WwtreatmentthreeService {

    /**
     * Save a wwtreatmentthree.
     *
     * @param wwtreatmentthreeDTO the entity to save
     * @return the persisted entity
     */
    WwtreatmentthreeDTO save(WwtreatmentthreeDTO wwtreatmentthreeDTO);

    /**
     *  Get all the wwtreatmentthrees.
     *  
     *  @return the list of entities
     */
    List<WwtreatmentthreeDTO> findAll();

    /**
     *  Get the "id" wwtreatmentthree.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WwtreatmentthreeDTO findOne(String id);

    /**
     *  Delete the "id" wwtreatmentthree.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
