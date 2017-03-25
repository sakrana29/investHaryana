package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.DepartmentserviceDTO;
import java.util.List;

/**
 * Service Interface for managing Departmentservice.
 */
public interface DepartmentserviceService {

    /**
     * Save a departmentservice.
     *
     * @param departmentserviceDTO the entity to save
     * @return the persisted entity
     */
    DepartmentserviceDTO save(DepartmentserviceDTO departmentserviceDTO);

    /**
     *  Get all the departmentservices.
     *  
     *  @return the list of entities
     */
    List<DepartmentserviceDTO> findAll();

    /**
     *  Get the "id" departmentservice.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DepartmentserviceDTO findOne(String id);

    /**
     *  Delete the "id" departmentservice.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
