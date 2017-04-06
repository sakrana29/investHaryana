package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectServicePaymentDetailsDTO;
import java.util.List;

/**
 * Service Interface for managing ProjectServicePaymentDetails.
 */
public interface ProjectServicePaymentDetailsService {

    /**
     * Save a projectServicePaymentDetails.
     *
     * @param projectServicePaymentDetailsDTO the entity to save
     * @return the persisted entity
     */
    ProjectServicePaymentDetailsDTO save(ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO);

    /**
     *  Get all the projectServicePaymentDetails.
     *  
     *  @return the list of entities
     */
    List<ProjectServicePaymentDetailsDTO> findAll();

    /**
     *  Get the "id" projectServicePaymentDetails.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectServicePaymentDetailsDTO findOne(String id);

    /**
     *  Delete the "id" projectServicePaymentDetails.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
