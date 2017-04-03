package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectproductDTO;
import java.util.List;

/**
 * Service Interface for managing Projectproduct.
 */
public interface ProjectproductService {

    /**
     * Save a projectproduct.
     *
     * @param projectproductDTO the entity to save
     * @return the persisted entity
     */
    ProjectproductDTO save(ProjectproductDTO projectproductDTO);

    /**
     *  Get all the projectproducts.
     *
     *  @return the list of entities
     */
    List<ProjectproductDTO> findAll();

    /**
     *  Get the "id" projectproduct.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectproductDTO findOne(String id);

    /**
     *  Delete the "id" projectproduct.
     *
     *  @param id the id of the entity
     */
    void delete(String id);

    List<ProjectproductDTO> findAllByProjectid(String projectid);
    void deleteByProject(String projectid);
}
