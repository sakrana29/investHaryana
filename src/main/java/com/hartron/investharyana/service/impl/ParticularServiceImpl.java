package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ParticularService;
import com.hartron.investharyana.domain.Particular;
import com.hartron.investharyana.repository.ParticularRepository;
import com.hartron.investharyana.service.dto.ParticularDTO;
import com.hartron.investharyana.service.mapper.ParticularMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Particular.
 */
@Service
public class ParticularServiceImpl implements ParticularService{

    private final Logger log = LoggerFactory.getLogger(ParticularServiceImpl.class);
    
    private final ParticularRepository particularRepository;

    private final ParticularMapper particularMapper;

    public ParticularServiceImpl(ParticularRepository particularRepository, ParticularMapper particularMapper) {
        this.particularRepository = particularRepository;
        this.particularMapper = particularMapper;
    }

    /**
     * Save a particular.
     *
     * @param particularDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ParticularDTO save(ParticularDTO particularDTO) {
        log.debug("Request to save Particular : {}", particularDTO);
        Particular particular = particularMapper.particularDTOToParticular(particularDTO);
        particular = particularRepository.save(particular);
        ParticularDTO result = particularMapper.particularToParticularDTO(particular);
        return result;
    }

    /**
     *  Get all the particulars.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ParticularDTO> findAll() {
        log.debug("Request to get all Particulars");
        List<ParticularDTO> result = particularRepository.findAll().stream()
            .map(particularMapper::particularToParticularDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one particular by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ParticularDTO findOne(String id) {
        log.debug("Request to get Particular : {}", id);
        Particular particular = particularRepository.findOne(UUID.fromString(id));
        ParticularDTO particularDTO = particularMapper.particularToParticularDTO(particular);
        return particularDTO;
    }

    /**
     *  Delete the  particular by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Particular : {}", id);
        particularRepository.delete(UUID.fromString(id));
    }
}
