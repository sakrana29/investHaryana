package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectAttachemntDTO;
import java.util.List;

/**
 * Service Interface for managing ProjectAttachemnt.
 */
public interface ProjectAttachemntService {

    /**
     * Save a projectAttachemnt.
     *
     * @param projectAttachemntDTO the entity to save
     * @return the persisted entity
     */
    ProjectAttachemntDTO save(ProjectAttachemntDTO projectAttachemntDTO);

    /**
     *  Get all the projectAttachemnts.
     *  
     *  @return the list of entities
     */
    List<ProjectAttachemntDTO> findAll();

    /**
     *  Get the "id" projectAttachemnt.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectAttachemntDTO findOne(String id);

    /**
     *  Delete the "id" projectAttachemnt.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
