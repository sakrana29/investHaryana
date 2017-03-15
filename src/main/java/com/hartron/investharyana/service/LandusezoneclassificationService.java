package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.LandusezoneclassificationDTO;
import java.util.List;

/**
 * Service Interface for managing Landusezoneclassification.
 */
public interface LandusezoneclassificationService {

    /**
     * Save a landusezoneclassification.
     *
     * @param landusezoneclassificationDTO the entity to save
     * @return the persisted entity
     */
    LandusezoneclassificationDTO save(LandusezoneclassificationDTO landusezoneclassificationDTO);

    /**
     *  Get all the landusezoneclassifications.
     *  
     *  @return the list of entities
     */
    List<LandusezoneclassificationDTO> findAll();

    /**
     *  Get the "id" landusezoneclassification.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    LandusezoneclassificationDTO findOne(String id);

    /**
     *  Delete the "id" landusezoneclassification.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
