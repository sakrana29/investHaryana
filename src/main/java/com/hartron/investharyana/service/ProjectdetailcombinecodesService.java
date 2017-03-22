package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectdetailcombinecodesDTO;
import java.util.List;

/**
 * Service Interface for managing Projectdetailcombinecodes.
 */
public interface ProjectdetailcombinecodesService {

    /**
     * Save a projectdetailcombinecodes.
     *
     * @param projectdetailcombinecodesDTO the entity to save
     * @return the persisted entity
     */
    ProjectdetailcombinecodesDTO save(ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO);

    /**
     *  Get all the projectdetailcombinecodes.
     *  
     *  @return the list of entities
     */
    List<ProjectdetailcombinecodesDTO> findAll();

    /**
     *  Get the "id" projectdetailcombinecodes.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectdetailcombinecodesDTO findOne(String id);

    /**
     *  Delete the "id" projectdetailcombinecodes.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
