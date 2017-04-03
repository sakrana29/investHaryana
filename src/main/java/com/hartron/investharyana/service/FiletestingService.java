package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.FiletestingDTO;
import java.util.List;

/**
 * Service Interface for managing Filetesting.
 */
public interface FiletestingService {

    /**
     * Save a filetesting.
     *
     * @param filetestingDTO the entity to save
     * @return the persisted entity
     */
    FiletestingDTO save(FiletestingDTO filetestingDTO);

    /**
     *  Get all the filetestings.
     *  
     *  @return the list of entities
     */
    List<FiletestingDTO> findAll();

    /**
     *  Get the "id" filetesting.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FiletestingDTO findOne(String id);

    /**
     *  Delete the "id" filetesting.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
