package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.WatersupplysourceService;
import com.hartron.investharyana.domain.Watersupplysource;
import com.hartron.investharyana.repository.WatersupplysourceRepository;
import com.hartron.investharyana.service.dto.WatersupplysourceDTO;
import com.hartron.investharyana.service.mapper.WatersupplysourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Watersupplysource.
 */
@Service
public class WatersupplysourceServiceImpl implements WatersupplysourceService{

    private final Logger log = LoggerFactory.getLogger(WatersupplysourceServiceImpl.class);
    
    private final WatersupplysourceRepository watersupplysourceRepository;

    private final WatersupplysourceMapper watersupplysourceMapper;

    public WatersupplysourceServiceImpl(WatersupplysourceRepository watersupplysourceRepository, WatersupplysourceMapper watersupplysourceMapper) {
        this.watersupplysourceRepository = watersupplysourceRepository;
        this.watersupplysourceMapper = watersupplysourceMapper;
    }

    /**
     * Save a watersupplysource.
     *
     * @param watersupplysourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WatersupplysourceDTO save(WatersupplysourceDTO watersupplysourceDTO) {
        log.debug("Request to save Watersupplysource : {}", watersupplysourceDTO);
        Watersupplysource watersupplysource = watersupplysourceMapper.watersupplysourceDTOToWatersupplysource(watersupplysourceDTO);
        watersupplysource = watersupplysourceRepository.save(watersupplysource);
        WatersupplysourceDTO result = watersupplysourceMapper.watersupplysourceToWatersupplysourceDTO(watersupplysource);
        return result;
    }

    /**
     *  Get all the watersupplysources.
     *  
     *  @return the list of entities
     */
    @Override
    public List<WatersupplysourceDTO> findAll() {
        log.debug("Request to get all Watersupplysources");
        List<WatersupplysourceDTO> result = watersupplysourceRepository.findAll().stream()
            .map(watersupplysourceMapper::watersupplysourceToWatersupplysourceDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one watersupplysource by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public WatersupplysourceDTO findOne(String id) {
        log.debug("Request to get Watersupplysource : {}", id);
        Watersupplysource watersupplysource = watersupplysourceRepository.findOne(UUID.fromString(id));
        WatersupplysourceDTO watersupplysourceDTO = watersupplysourceMapper.watersupplysourceToWatersupplysourceDTO(watersupplysource);
        return watersupplysourceDTO;
    }

    /**
     *  Delete the  watersupplysource by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Watersupplysource : {}", id);
        watersupplysourceRepository.delete(UUID.fromString(id));
    }
}
