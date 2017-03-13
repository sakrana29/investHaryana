package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Environment_impactdetailService;
import com.hartron.investharyana.domain.Environment_impactdetail;
import com.hartron.investharyana.repository.Environment_impactdetailRepository;
import com.hartron.investharyana.service.dto.Environment_impactdetailDTO;
import com.hartron.investharyana.service.mapper.Environment_impactdetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Environment_impactdetail.
 */
@Service
public class Environment_impactdetailServiceImpl implements Environment_impactdetailService{

    private final Logger log = LoggerFactory.getLogger(Environment_impactdetailServiceImpl.class);
    
    private final Environment_impactdetailRepository environment_impactdetailRepository;

    private final Environment_impactdetailMapper environment_impactdetailMapper;

    public Environment_impactdetailServiceImpl(Environment_impactdetailRepository environment_impactdetailRepository, Environment_impactdetailMapper environment_impactdetailMapper) {
        this.environment_impactdetailRepository = environment_impactdetailRepository;
        this.environment_impactdetailMapper = environment_impactdetailMapper;
    }

    /**
     * Save a environment_impactdetail.
     *
     * @param environment_impactdetailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Environment_impactdetailDTO save(Environment_impactdetailDTO environment_impactdetailDTO) {
        log.debug("Request to save Environment_impactdetail : {}", environment_impactdetailDTO);
        Environment_impactdetail environment_impactdetail = environment_impactdetailMapper.environment_impactdetailDTOToEnvironment_impactdetail(environment_impactdetailDTO);
        environment_impactdetail = environment_impactdetailRepository.save(environment_impactdetail);
        Environment_impactdetailDTO result = environment_impactdetailMapper.environment_impactdetailToEnvironment_impactdetailDTO(environment_impactdetail);
        return result;
    }

    /**
     *  Get all the environment_impactdetails.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Environment_impactdetailDTO> findAll() {
        log.debug("Request to get all Environment_impactdetails");
        List<Environment_impactdetailDTO> result = environment_impactdetailRepository.findAll().stream()
            .map(environment_impactdetailMapper::environment_impactdetailToEnvironment_impactdetailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one environment_impactdetail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Environment_impactdetailDTO findOne(String id) {
        log.debug("Request to get Environment_impactdetail : {}", id);
        Environment_impactdetail environment_impactdetail = environment_impactdetailRepository.findOne(UUID.fromString(id));
        Environment_impactdetailDTO environment_impactdetailDTO = environment_impactdetailMapper.environment_impactdetailToEnvironment_impactdetailDTO(environment_impactdetail);
        return environment_impactdetailDTO;
    }

    /**
     *  Delete the  environment_impactdetail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Environment_impactdetail : {}", id);
        environment_impactdetailRepository.delete(UUID.fromString(id));
    }
}
