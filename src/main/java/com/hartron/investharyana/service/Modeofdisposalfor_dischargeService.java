package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Modeofdisposalfor_dischargeDTO;
import java.util.List;

/**
 * Service Interface for managing Modeofdisposalfor_discharge.
 */
public interface Modeofdisposalfor_dischargeService {

    /**
     * Save a modeofdisposalfor_discharge.
     *
     * @param modeofdisposalfor_dischargeDTO the entity to save
     * @return the persisted entity
     */
    Modeofdisposalfor_dischargeDTO save(Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO);

    /**
     *  Get all the modeofdisposalfor_discharges.
     *  
     *  @return the list of entities
     */
    List<Modeofdisposalfor_dischargeDTO> findAll();

    /**
     *  Get the "id" modeofdisposalfor_discharge.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Modeofdisposalfor_dischargeDTO findOne(String id);

    /**
     *  Delete the "id" modeofdisposalfor_discharge.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
