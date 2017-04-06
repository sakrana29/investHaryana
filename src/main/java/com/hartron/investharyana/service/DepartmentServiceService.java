package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.DepartmentServiceDTO;
import java.util.List;

/**
 * Service Interface for managing DepartmentService.
 */
public interface DepartmentServiceService {

    /**
     * Save a departmentService.
     *
     * @param departmentServiceDTO the entity to save
     * @return the persisted entity
     */
    DepartmentServiceDTO save(DepartmentServiceDTO departmentServiceDTO);

    /**
     *  Get all the departmentServices.
     *
     *  @return the list of entities
     */
    List<DepartmentServiceDTO> findAll();

    /**
     *  Get the "id" departmentService.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DepartmentServiceDTO findOne(String id);

    List<DepartmentServiceDTO> findServiceByDepartment(String departmentid);

    /**
     *  Delete the "id" departmentService.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
