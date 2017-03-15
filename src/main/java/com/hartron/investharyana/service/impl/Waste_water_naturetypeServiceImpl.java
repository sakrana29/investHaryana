package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Waste_water_naturetypeService;
import com.hartron.investharyana.domain.Waste_water_naturetype;
import com.hartron.investharyana.repository.Waste_water_naturetypeRepository;
import com.hartron.investharyana.service.dto.Waste_water_naturetypeDTO;
import com.hartron.investharyana.service.mapper.Waste_water_naturetypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Waste_water_naturetype.
 */
@Service
public class Waste_water_naturetypeServiceImpl implements Waste_water_naturetypeService{

    private final Logger log = LoggerFactory.getLogger(Waste_water_naturetypeServiceImpl.class);
    
    private final Waste_water_naturetypeRepository waste_water_naturetypeRepository;

    private final Waste_water_naturetypeMapper waste_water_naturetypeMapper;

    public Waste_water_naturetypeServiceImpl(Waste_water_naturetypeRepository waste_water_naturetypeRepository, Waste_water_naturetypeMapper waste_water_naturetypeMapper) {
        this.waste_water_naturetypeRepository = waste_water_naturetypeRepository;
        this.waste_water_naturetypeMapper = waste_water_naturetypeMapper;
    }

    /**
     * Save a waste_water_naturetype.
     *
     * @param waste_water_naturetypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Waste_water_naturetypeDTO save(Waste_water_naturetypeDTO waste_water_naturetypeDTO) {
        log.debug("Request to save Waste_water_naturetype : {}", waste_water_naturetypeDTO);
        Waste_water_naturetype waste_water_naturetype = waste_water_naturetypeMapper.waste_water_naturetypeDTOToWaste_water_naturetype(waste_water_naturetypeDTO);
        waste_water_naturetype = waste_water_naturetypeRepository.save(waste_water_naturetype);
        Waste_water_naturetypeDTO result = waste_water_naturetypeMapper.waste_water_naturetypeToWaste_water_naturetypeDTO(waste_water_naturetype);
        return result;
    }

    /**
     *  Get all the waste_water_naturetypes.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Waste_water_naturetypeDTO> findAll() {
        log.debug("Request to get all Waste_water_naturetypes");
        List<Waste_water_naturetypeDTO> result = waste_water_naturetypeRepository.findAll().stream()
            .map(waste_water_naturetypeMapper::waste_water_naturetypeToWaste_water_naturetypeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one waste_water_naturetype by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Waste_water_naturetypeDTO findOne(String id) {
        log.debug("Request to get Waste_water_naturetype : {}", id);
        Waste_water_naturetype waste_water_naturetype = waste_water_naturetypeRepository.findOne(UUID.fromString(id));
        Waste_water_naturetypeDTO waste_water_naturetypeDTO = waste_water_naturetypeMapper.waste_water_naturetypeToWaste_water_naturetypeDTO(waste_water_naturetype);
        return waste_water_naturetypeDTO;
    }

    /**
     *  Delete the  waste_water_naturetype by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Waste_water_naturetype : {}", id);
        waste_water_naturetypeRepository.delete(UUID.fromString(id));
    }
}
