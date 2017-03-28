package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Project_phaseDTO;
import java.util.List;

/**
 * Service Interface for managing Project_phase.
 */
public interface Project_phaseService {

    /**
     * Save a project_phase.
     *
     * @param project_phaseDTO the entity to save
     * @return the persisted entity
     */
    Project_phaseDTO save(Project_phaseDTO project_phaseDTO);

    /**
     *  Get all the project_phases.
     *
     *  @return the list of entities
     */
    List<Project_phaseDTO> findAll();
    List<Project_phaseDTO> findAllByProjectid(String projectid);

    /**
     *  Get the "id" project_phase.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Project_phaseDTO findOne(String id);

    /**
     *  Delete the "id" project_phase.
     *
     *  @param id the id of the entity
     */
    void delete(String id);

    void deleteByProject(String projectid);

}
