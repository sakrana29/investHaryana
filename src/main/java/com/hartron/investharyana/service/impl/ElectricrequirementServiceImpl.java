package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ElectricrequirementService;
import com.hartron.investharyana.domain.Electricrequirement;
import com.hartron.investharyana.repository.ElectricrequirementRepository;
import com.hartron.investharyana.service.dto.ElectricrequirementDTO;
import com.hartron.investharyana.service.mapper.ElectricrequirementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Electricrequirement.
 */
@Service
public class ElectricrequirementServiceImpl implements ElectricrequirementService{

    private final Logger log = LoggerFactory.getLogger(ElectricrequirementServiceImpl.class);
    
    private final ElectricrequirementRepository electricrequirementRepository;

    private final ElectricrequirementMapper electricrequirementMapper;

    public ElectricrequirementServiceImpl(ElectricrequirementRepository electricrequirementRepository, ElectricrequirementMapper electricrequirementMapper) {
        this.electricrequirementRepository = electricrequirementRepository;
        this.electricrequirementMapper = electricrequirementMapper;
    }

    /**
     * Save a electricrequirement.
     *
     * @param electricrequirementDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ElectricrequirementDTO save(ElectricrequirementDTO electricrequirementDTO) {
        log.debug("Request to save Electricrequirement : {}", electricrequirementDTO);
        Electricrequirement electricrequirement = electricrequirementMapper.electricrequirementDTOToElectricrequirement(electricrequirementDTO);
        electricrequirement = electricrequirementRepository.save(electricrequirement);
        ElectricrequirementDTO result = electricrequirementMapper.electricrequirementToElectricrequirementDTO(electricrequirement);
        return result;
    }

    /**
     *  Get all the electricrequirements.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ElectricrequirementDTO> findAll() {
        log.debug("Request to get all Electricrequirements");
        List<ElectricrequirementDTO> result = electricrequirementRepository.findAll().stream()
            .map(electricrequirementMapper::electricrequirementToElectricrequirementDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one electricrequirement by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ElectricrequirementDTO findOne(String id) {
        log.debug("Request to get Electricrequirement : {}", id);
        Electricrequirement electricrequirement = electricrequirementRepository.findOne(UUID.fromString(id));
        ElectricrequirementDTO electricrequirementDTO = electricrequirementMapper.electricrequirementToElectricrequirementDTO(electricrequirement);
        return electricrequirementDTO;
    }

    /**
     *  Delete the  electricrequirement by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Electricrequirement : {}", id);
        electricrequirementRepository.delete(UUID.fromString(id));
    }
}
