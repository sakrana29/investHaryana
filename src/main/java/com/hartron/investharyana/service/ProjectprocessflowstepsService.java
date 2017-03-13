package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectprocessflowstepsDTO;
import java.util.List;

/**
 * Service Interface for managing Projectprocessflowsteps.
 */
public interface ProjectprocessflowstepsService {

    /**
     * Save a projectprocessflowsteps.
     *
     * @param projectprocessflowstepsDTO the entity to save
     * @return the persisted entity
     */
    ProjectprocessflowstepsDTO save(ProjectprocessflowstepsDTO projectprocessflowstepsDTO);

    /**
     *  Get all the projectprocessflowsteps.
     *  
     *  @return the list of entities
     */
    List<ProjectprocessflowstepsDTO> findAll();

    /**
     *  Get the "id" projectprocessflowsteps.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectprocessflowstepsDTO findOne(String id);

    /**
     *  Delete the "id" projectprocessflowsteps.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
