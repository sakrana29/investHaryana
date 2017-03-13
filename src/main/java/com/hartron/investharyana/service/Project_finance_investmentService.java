package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Project_finance_investmentDTO;
import java.util.List;

/**
 * Service Interface for managing Project_finance_investment.
 */
public interface Project_finance_investmentService {

    /**
     * Save a project_finance_investment.
     *
     * @param project_finance_investmentDTO the entity to save
     * @return the persisted entity
     */
    Project_finance_investmentDTO save(Project_finance_investmentDTO project_finance_investmentDTO);

    /**
     *  Get all the project_finance_investments.
     *  
     *  @return the list of entities
     */
    List<Project_finance_investmentDTO> findAll();

    /**
     *  Get the "id" project_finance_investment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Project_finance_investmentDTO findOne(String id);

    /**
     *  Delete the "id" project_finance_investment.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
