package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.WastewaterdetailDTO;
import java.util.List;

/**
 * Service Interface for managing Wastewaterdetail.
 */
public interface WastewaterdetailService {

    /**
     * Save a wastewaterdetail.
     *
     * @param wastewaterdetailDTO the entity to save
     * @return the persisted entity
     */
    WastewaterdetailDTO save(WastewaterdetailDTO wastewaterdetailDTO);

    /**
     *  Get all the wastewaterdetails.
     *
     *  @return the list of entities
     */
    List<WastewaterdetailDTO> findAll();

    /**
     *  Get the "id" wastewaterdetail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WastewaterdetailDTO findOne(String id);

    /**
     *  Delete the "id" wastewaterdetail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);

    List<WastewaterdetailDTO> findAllByProjectid(String projectid);
    void deleteByProject(String projectid);
}
