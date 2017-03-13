package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Emmision_pollution_controllService;
import com.hartron.investharyana.domain.Emmision_pollution_controll;
import com.hartron.investharyana.repository.Emmision_pollution_controllRepository;
import com.hartron.investharyana.service.dto.Emmision_pollution_controllDTO;
import com.hartron.investharyana.service.mapper.Emmision_pollution_controllMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Emmision_pollution_controll.
 */
@Service
public class Emmision_pollution_controllServiceImpl implements Emmision_pollution_controllService{

    private final Logger log = LoggerFactory.getLogger(Emmision_pollution_controllServiceImpl.class);
    
    private final Emmision_pollution_controllRepository emmision_pollution_controllRepository;

    private final Emmision_pollution_controllMapper emmision_pollution_controllMapper;

    public Emmision_pollution_controllServiceImpl(Emmision_pollution_controllRepository emmision_pollution_controllRepository, Emmision_pollution_controllMapper emmision_pollution_controllMapper) {
        this.emmision_pollution_controllRepository = emmision_pollution_controllRepository;
        this.emmision_pollution_controllMapper = emmision_pollution_controllMapper;
    }

    /**
     * Save a emmision_pollution_controll.
     *
     * @param emmision_pollution_controllDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Emmision_pollution_controllDTO save(Emmision_pollution_controllDTO emmision_pollution_controllDTO) {
        log.debug("Request to save Emmision_pollution_controll : {}", emmision_pollution_controllDTO);
        Emmision_pollution_controll emmision_pollution_controll = emmision_pollution_controllMapper.emmision_pollution_controllDTOToEmmision_pollution_controll(emmision_pollution_controllDTO);
        emmision_pollution_controll = emmision_pollution_controllRepository.save(emmision_pollution_controll);
        Emmision_pollution_controllDTO result = emmision_pollution_controllMapper.emmision_pollution_controllToEmmision_pollution_controllDTO(emmision_pollution_controll);
        return result;
    }

    /**
     *  Get all the emmision_pollution_controlls.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Emmision_pollution_controllDTO> findAll() {
        log.debug("Request to get all Emmision_pollution_controlls");
        List<Emmision_pollution_controllDTO> result = emmision_pollution_controllRepository.findAll().stream()
            .map(emmision_pollution_controllMapper::emmision_pollution_controllToEmmision_pollution_controllDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one emmision_pollution_controll by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Emmision_pollution_controllDTO findOne(String id) {
        log.debug("Request to get Emmision_pollution_controll : {}", id);
        Emmision_pollution_controll emmision_pollution_controll = emmision_pollution_controllRepository.findOne(UUID.fromString(id));
        Emmision_pollution_controllDTO emmision_pollution_controllDTO = emmision_pollution_controllMapper.emmision_pollution_controllToEmmision_pollution_controllDTO(emmision_pollution_controll);
        return emmision_pollution_controllDTO;
    }

    /**
     *  Delete the  emmision_pollution_controll by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Emmision_pollution_controll : {}", id);
        emmision_pollution_controllRepository.delete(UUID.fromString(id));
    }
}
