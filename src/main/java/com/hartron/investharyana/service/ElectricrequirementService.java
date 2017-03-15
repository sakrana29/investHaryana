package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ElectricrequirementDTO;
import java.util.List;

/**
 * Service Interface for managing Electricrequirement.
 */
public interface ElectricrequirementService {

    /**
     * Save a electricrequirement.
     *
     * @param electricrequirementDTO the entity to save
     * @return the persisted entity
     */
    ElectricrequirementDTO save(ElectricrequirementDTO electricrequirementDTO);

    /**
     *  Get all the electricrequirements.
     *  
     *  @return the list of entities
     */
    List<ElectricrequirementDTO> findAll();

    /**
     *  Get the "id" electricrequirement.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ElectricrequirementDTO findOne(String id);

    /**
     *  Delete the "id" electricrequirement.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
