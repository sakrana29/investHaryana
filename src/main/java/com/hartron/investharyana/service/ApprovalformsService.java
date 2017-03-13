package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ApprovalformsDTO;
import java.util.List;

/**
 * Service Interface for managing Approvalforms.
 */
public interface ApprovalformsService {

    /**
     * Save a approvalforms.
     *
     * @param approvalformsDTO the entity to save
     * @return the persisted entity
     */
    ApprovalformsDTO save(ApprovalformsDTO approvalformsDTO);

    /**
     *  Get all the approvalforms.
     *  
     *  @return the list of entities
     */
    List<ApprovalformsDTO> findAll();

    /**
     *  Get the "id" approvalforms.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ApprovalformsDTO findOne(String id);

    /**
     *  Delete the "id" approvalforms.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
