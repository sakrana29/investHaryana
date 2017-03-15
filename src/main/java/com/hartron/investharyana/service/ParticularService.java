package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ParticularDTO;
import java.util.List;

/**
 * Service Interface for managing Particular.
 */
public interface ParticularService {

    /**
     * Save a particular.
     *
     * @param particularDTO the entity to save
     * @return the persisted entity
     */
    ParticularDTO save(ParticularDTO particularDTO);

    /**
     *  Get all the particulars.
     *  
     *  @return the list of entities
     */
    List<ParticularDTO> findAll();

    /**
     *  Get the "id" particular.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ParticularDTO findOne(String id);

    /**
     *  Delete the "id" particular.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
