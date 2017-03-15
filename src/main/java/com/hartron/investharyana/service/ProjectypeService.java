package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectypeDTO;
import java.util.List;

/**
 * Service Interface for managing Projectype.
 */
public interface ProjectypeService {

    /**
     * Save a projectype.
     *
     * @param projectypeDTO the entity to save
     * @return the persisted entity
     */
    ProjectypeDTO save(ProjectypeDTO projectypeDTO);

    /**
     *  Get all the projectypes.
     *  
     *  @return the list of entities
     */
    List<ProjectypeDTO> findAll();

    /**
     *  Get the "id" projectype.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectypeDTO findOne(String id);

    /**
     *  Delete the "id" projectype.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
