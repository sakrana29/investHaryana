package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectserviceformfielddataDTO;
import java.util.List;

/**
 * Service Interface for managing Projectserviceformfielddata.
 */
public interface ProjectserviceformfielddataService {

    /**
     * Save a projectserviceformfielddata.
     *
     * @param projectserviceformfielddataDTO the entity to save
     * @return the persisted entity
     */
    ProjectserviceformfielddataDTO save(ProjectserviceformfielddataDTO projectserviceformfielddataDTO);

    /**
     *  Get all the projectserviceformfielddata.
     *
     *  @return the list of entities
     */
    List<ProjectserviceformfielddataDTO> findAll();

    /**
     *  Get the "id" projectserviceformfielddata.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectserviceformfielddataDTO findOne(String id);

    /**
     *  Delete the "id" projectserviceformfielddata.
     *
     *  @param id the id of the entity
     */
    void delete(String id);

    List<ProjectserviceformfielddataDTO> findAllByProjectid(String projectid);
}
