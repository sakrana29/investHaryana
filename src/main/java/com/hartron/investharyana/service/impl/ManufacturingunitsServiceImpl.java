package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ManufacturingunitsService;
import com.hartron.investharyana.domain.Manufacturingunits;
import com.hartron.investharyana.repository.ManufacturingunitsRepository;
import com.hartron.investharyana.service.dto.ManufacturingunitsDTO;
import com.hartron.investharyana.service.mapper.ManufacturingunitsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Manufacturingunits.
 */
@Service
public class ManufacturingunitsServiceImpl implements ManufacturingunitsService{

    private final Logger log = LoggerFactory.getLogger(ManufacturingunitsServiceImpl.class);
    
    private final ManufacturingunitsRepository manufacturingunitsRepository;

    private final ManufacturingunitsMapper manufacturingunitsMapper;

    public ManufacturingunitsServiceImpl(ManufacturingunitsRepository manufacturingunitsRepository, ManufacturingunitsMapper manufacturingunitsMapper) {
        this.manufacturingunitsRepository = manufacturingunitsRepository;
        this.manufacturingunitsMapper = manufacturingunitsMapper;
    }

    /**
     * Save a manufacturingunits.
     *
     * @param manufacturingunitsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ManufacturingunitsDTO save(ManufacturingunitsDTO manufacturingunitsDTO) {
        log.debug("Request to save Manufacturingunits : {}", manufacturingunitsDTO);
        Manufacturingunits manufacturingunits = manufacturingunitsMapper.manufacturingunitsDTOToManufacturingunits(manufacturingunitsDTO);
        manufacturingunits = manufacturingunitsRepository.save(manufacturingunits);
        ManufacturingunitsDTO result = manufacturingunitsMapper.manufacturingunitsToManufacturingunitsDTO(manufacturingunits);
        return result;
    }

    /**
     *  Get all the manufacturingunits.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ManufacturingunitsDTO> findAll() {
        log.debug("Request to get all Manufacturingunits");
        List<ManufacturingunitsDTO> result = manufacturingunitsRepository.findAll().stream()
            .map(manufacturingunitsMapper::manufacturingunitsToManufacturingunitsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one manufacturingunits by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ManufacturingunitsDTO findOne(String id) {
        log.debug("Request to get Manufacturingunits : {}", id);
        Manufacturingunits manufacturingunits = manufacturingunitsRepository.findOne(UUID.fromString(id));
        ManufacturingunitsDTO manufacturingunitsDTO = manufacturingunitsMapper.manufacturingunitsToManufacturingunitsDTO(manufacturingunits);
        return manufacturingunitsDTO;
    }

    /**
     *  Delete the  manufacturingunits by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Manufacturingunits : {}", id);
        manufacturingunitsRepository.delete(UUID.fromString(id));
    }
}
