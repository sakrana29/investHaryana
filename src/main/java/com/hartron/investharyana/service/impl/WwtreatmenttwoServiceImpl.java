package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.WwtreatmenttwoService;
import com.hartron.investharyana.domain.Wwtreatmenttwo;
import com.hartron.investharyana.repository.WwtreatmenttwoRepository;
import com.hartron.investharyana.service.dto.WwtreatmenttwoDTO;
import com.hartron.investharyana.service.mapper.WwtreatmenttwoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Wwtreatmenttwo.
 */
@Service
public class WwtreatmenttwoServiceImpl implements WwtreatmenttwoService{

    private final Logger log = LoggerFactory.getLogger(WwtreatmenttwoServiceImpl.class);
    
    private final WwtreatmenttwoRepository wwtreatmenttwoRepository;

    private final WwtreatmenttwoMapper wwtreatmenttwoMapper;

    public WwtreatmenttwoServiceImpl(WwtreatmenttwoRepository wwtreatmenttwoRepository, WwtreatmenttwoMapper wwtreatmenttwoMapper) {
        this.wwtreatmenttwoRepository = wwtreatmenttwoRepository;
        this.wwtreatmenttwoMapper = wwtreatmenttwoMapper;
    }

    /**
     * Save a wwtreatmenttwo.
     *
     * @param wwtreatmenttwoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WwtreatmenttwoDTO save(WwtreatmenttwoDTO wwtreatmenttwoDTO) {
        log.debug("Request to save Wwtreatmenttwo : {}", wwtreatmenttwoDTO);
        Wwtreatmenttwo wwtreatmenttwo = wwtreatmenttwoMapper.wwtreatmenttwoDTOToWwtreatmenttwo(wwtreatmenttwoDTO);
        wwtreatmenttwo = wwtreatmenttwoRepository.save(wwtreatmenttwo);
        WwtreatmenttwoDTO result = wwtreatmenttwoMapper.wwtreatmenttwoToWwtreatmenttwoDTO(wwtreatmenttwo);
        return result;
    }

    /**
     *  Get all the wwtreatmenttwos.
     *  
     *  @return the list of entities
     */
    @Override
    public List<WwtreatmenttwoDTO> findAll() {
        log.debug("Request to get all Wwtreatmenttwos");
        List<WwtreatmenttwoDTO> result = wwtreatmenttwoRepository.findAll().stream()
            .map(wwtreatmenttwoMapper::wwtreatmenttwoToWwtreatmenttwoDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one wwtreatmenttwo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public WwtreatmenttwoDTO findOne(String id) {
        log.debug("Request to get Wwtreatmenttwo : {}", id);
        Wwtreatmenttwo wwtreatmenttwo = wwtreatmenttwoRepository.findOne(UUID.fromString(id));
        WwtreatmenttwoDTO wwtreatmenttwoDTO = wwtreatmenttwoMapper.wwtreatmenttwoToWwtreatmenttwoDTO(wwtreatmenttwo);
        return wwtreatmenttwoDTO;
    }

    /**
     *  Delete the  wwtreatmenttwo by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Wwtreatmenttwo : {}", id);
        wwtreatmenttwoRepository.delete(UUID.fromString(id));
    }
}
