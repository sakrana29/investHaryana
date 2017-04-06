package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectServiceLogDTO;
import java.util.List;

/**
 * Service Interface for managing ProjectServiceLog.
 */
public interface ProjectServiceLogService {

    /**
     * Save a projectServiceLog.
     *
     * @param projectServiceLogDTO the entity to save
     * @return the persisted entity
     */
    ProjectServiceLogDTO save(ProjectServiceLogDTO projectServiceLogDTO);

    /**
     *  Get all the projectServiceLogs.
     *  
     *  @return the list of entities
     */
    List<ProjectServiceLogDTO> findAll();

    /**
     *  Get the "id" projectServiceLog.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectServiceLogDTO findOne(String id);

    /**
     *  Delete the "id" projectServiceLog.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
