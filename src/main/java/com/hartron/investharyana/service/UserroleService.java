package com.hartron.investharyana.service;

import com.hartron.investharyana.domain.Userrole;
import java.util.List;

/**
 * Service Interface for managing Userrole.
 */
public interface UserroleService {

    /**
     * Save a userrole.
     *
     * @param userrole the entity to save
     * @return the persisted entity
     */
    Userrole save(Userrole userrole);

    /**
     *  Get all the userroles.
     *  
     *  @return the list of entities
     */
    List<Userrole> findAll();

    /**
     *  Get the "id" userrole.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Userrole findOne(String id);

    /**
     *  Delete the "id" userrole.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
