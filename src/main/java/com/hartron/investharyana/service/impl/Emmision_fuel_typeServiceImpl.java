package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Emmision_fuel_typeService;
import com.hartron.investharyana.domain.Emmision_fuel_type;
import com.hartron.investharyana.repository.Emmision_fuel_typeRepository;
import com.hartron.investharyana.service.dto.Emmision_fuel_typeDTO;
import com.hartron.investharyana.service.mapper.Emmision_fuel_typeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Emmision_fuel_type.
 */
@Service
public class Emmision_fuel_typeServiceImpl implements Emmision_fuel_typeService{

    private final Logger log = LoggerFactory.getLogger(Emmision_fuel_typeServiceImpl.class);
    
    private final Emmision_fuel_typeRepository emmision_fuel_typeRepository;

    private final Emmision_fuel_typeMapper emmision_fuel_typeMapper;

    public Emmision_fuel_typeServiceImpl(Emmision_fuel_typeRepository emmision_fuel_typeRepository, Emmision_fuel_typeMapper emmision_fuel_typeMapper) {
        this.emmision_fuel_typeRepository = emmision_fuel_typeRepository;
        this.emmision_fuel_typeMapper = emmision_fuel_typeMapper;
    }

    /**
     * Save a emmision_fuel_type.
     *
     * @param emmision_fuel_typeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Emmision_fuel_typeDTO save(Emmision_fuel_typeDTO emmision_fuel_typeDTO) {
        log.debug("Request to save Emmision_fuel_type : {}", emmision_fuel_typeDTO);
        Emmision_fuel_type emmision_fuel_type = emmision_fuel_typeMapper.emmision_fuel_typeDTOToEmmision_fuel_type(emmision_fuel_typeDTO);
        emmision_fuel_type = emmision_fuel_typeRepository.save(emmision_fuel_type);
        Emmision_fuel_typeDTO result = emmision_fuel_typeMapper.emmision_fuel_typeToEmmision_fuel_typeDTO(emmision_fuel_type);
        return result;
    }

    /**
     *  Get all the emmision_fuel_types.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Emmision_fuel_typeDTO> findAll() {
        log.debug("Request to get all Emmision_fuel_types");
        List<Emmision_fuel_typeDTO> result = emmision_fuel_typeRepository.findAll().stream()
            .map(emmision_fuel_typeMapper::emmision_fuel_typeToEmmision_fuel_typeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one emmision_fuel_type by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Emmision_fuel_typeDTO findOne(String id) {
        log.debug("Request to get Emmision_fuel_type : {}", id);
        Emmision_fuel_type emmision_fuel_type = emmision_fuel_typeRepository.findOne(UUID.fromString(id));
        Emmision_fuel_typeDTO emmision_fuel_typeDTO = emmision_fuel_typeMapper.emmision_fuel_typeToEmmision_fuel_typeDTO(emmision_fuel_type);
        return emmision_fuel_typeDTO;
    }

    /**
     *  Delete the  emmision_fuel_type by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Emmision_fuel_type : {}", id);
        emmision_fuel_typeRepository.delete(UUID.fromString(id));
    }
}
