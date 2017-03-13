package com.hartron.investharyana.service;

import com.hartron.investharyana.domain.Businessentity;
import java.util.List;

/**
 * Service Interface for managing Businessentity.
 */
public interface BusinessentityService {

    /**
     * Save a businessentity.
     *
     * @param businessentity the entity to save
     * @return the persisted entity
     */
    Businessentity save(Businessentity businessentity);

    /**
     *  Get all the businessentities.
     *  
     *  @return the list of entities
     */
    List<Businessentity> findAll();

    /**
     *  Get the "id" businessentity.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Businessentity findOne(String id);

    /**
     *  Delete the "id" businessentity.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
