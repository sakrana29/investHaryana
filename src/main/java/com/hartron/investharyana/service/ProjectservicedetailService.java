package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectservicedetailDTO;
import java.util.List;

/**
 * Service Interface for managing Projectservicedetail.
 */
public interface ProjectservicedetailService {

    /**
     * Save a projectservicedetail.
     *
     * @param projectservicedetailDTO the entity to save
     * @return the persisted entity
     */
    ProjectservicedetailDTO save(ProjectservicedetailDTO projectservicedetailDTO);

    /**
     *  Get all the projectservicedetails.
     *
     *  @return the list of entities
     */
    List<ProjectservicedetailDTO> findAll();

    /**
     *  Get the "id" projectservicedetail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectservicedetailDTO findOne(String id);

    List<ProjectservicedetailDTO> findByProject(String projectid);

    /**
     *  Delete the "id" projectservicedetail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
