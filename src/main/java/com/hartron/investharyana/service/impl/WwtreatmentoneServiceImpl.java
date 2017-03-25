package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.WwtreatmentoneService;
import com.hartron.investharyana.domain.Wwtreatmentone;
import com.hartron.investharyana.repository.WwtreatmentoneRepository;
import com.hartron.investharyana.service.dto.WwtreatmentoneDTO;
import com.hartron.investharyana.service.mapper.WwtreatmentoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Wwtreatmentone.
 */
@Service
public class WwtreatmentoneServiceImpl implements WwtreatmentoneService{

    private final Logger log = LoggerFactory.getLogger(WwtreatmentoneServiceImpl.class);
    
    private final WwtreatmentoneRepository wwtreatmentoneRepository;

    private final WwtreatmentoneMapper wwtreatmentoneMapper;

    public WwtreatmentoneServiceImpl(WwtreatmentoneRepository wwtreatmentoneRepository, WwtreatmentoneMapper wwtreatmentoneMapper) {
        this.wwtreatmentoneRepository = wwtreatmentoneRepository;
        this.wwtreatmentoneMapper = wwtreatmentoneMapper;
    }

    /**
     * Save a wwtreatmentone.
     *
     * @param wwtreatmentoneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WwtreatmentoneDTO save(WwtreatmentoneDTO wwtreatmentoneDTO) {
        log.debug("Request to save Wwtreatmentone : {}", wwtreatmentoneDTO);
        Wwtreatmentone wwtreatmentone = wwtreatmentoneMapper.wwtreatmentoneDTOToWwtreatmentone(wwtreatmentoneDTO);
        wwtreatmentone = wwtreatmentoneRepository.save(wwtreatmentone);
        WwtreatmentoneDTO result = wwtreatmentoneMapper.wwtreatmentoneToWwtreatmentoneDTO(wwtreatmentone);
        return result;
    }

    /**
     *  Get all the wwtreatmentones.
     *  
     *  @return the list of entities
     */
    @Override
    public List<WwtreatmentoneDTO> findAll() {
        log.debug("Request to get all Wwtreatmentones");
        List<WwtreatmentoneDTO> result = wwtreatmentoneRepository.findAll().stream()
            .map(wwtreatmentoneMapper::wwtreatmentoneToWwtreatmentoneDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one wwtreatmentone by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public WwtreatmentoneDTO findOne(String id) {
        log.debug("Request to get Wwtreatmentone : {}", id);
        Wwtreatmentone wwtreatmentone = wwtreatmentoneRepository.findOne(UUID.fromString(id));
        WwtreatmentoneDTO wwtreatmentoneDTO = wwtreatmentoneMapper.wwtreatmentoneToWwtreatmentoneDTO(wwtreatmentone);
        return wwtreatmentoneDTO;
    }

    /**
     *  Delete the  wwtreatmentone by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Wwtreatmentone : {}", id);
        wwtreatmentoneRepository.delete(UUID.fromString(id));
    }
}
