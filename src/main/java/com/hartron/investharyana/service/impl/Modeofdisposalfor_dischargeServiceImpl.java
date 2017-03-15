package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Modeofdisposalfor_dischargeService;
import com.hartron.investharyana.domain.Modeofdisposalfor_discharge;
import com.hartron.investharyana.repository.Modeofdisposalfor_dischargeRepository;
import com.hartron.investharyana.service.dto.Modeofdisposalfor_dischargeDTO;
import com.hartron.investharyana.service.mapper.Modeofdisposalfor_dischargeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Modeofdisposalfor_discharge.
 */
@Service
public class Modeofdisposalfor_dischargeServiceImpl implements Modeofdisposalfor_dischargeService{

    private final Logger log = LoggerFactory.getLogger(Modeofdisposalfor_dischargeServiceImpl.class);
    
    private final Modeofdisposalfor_dischargeRepository modeofdisposalfor_dischargeRepository;

    private final Modeofdisposalfor_dischargeMapper modeofdisposalfor_dischargeMapper;

    public Modeofdisposalfor_dischargeServiceImpl(Modeofdisposalfor_dischargeRepository modeofdisposalfor_dischargeRepository, Modeofdisposalfor_dischargeMapper modeofdisposalfor_dischargeMapper) {
        this.modeofdisposalfor_dischargeRepository = modeofdisposalfor_dischargeRepository;
        this.modeofdisposalfor_dischargeMapper = modeofdisposalfor_dischargeMapper;
    }

    /**
     * Save a modeofdisposalfor_discharge.
     *
     * @param modeofdisposalfor_dischargeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Modeofdisposalfor_dischargeDTO save(Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO) {
        log.debug("Request to save Modeofdisposalfor_discharge : {}", modeofdisposalfor_dischargeDTO);
        Modeofdisposalfor_discharge modeofdisposalfor_discharge = modeofdisposalfor_dischargeMapper.modeofdisposalfor_dischargeDTOToModeofdisposalfor_discharge(modeofdisposalfor_dischargeDTO);
        modeofdisposalfor_discharge = modeofdisposalfor_dischargeRepository.save(modeofdisposalfor_discharge);
        Modeofdisposalfor_dischargeDTO result = modeofdisposalfor_dischargeMapper.modeofdisposalfor_dischargeToModeofdisposalfor_dischargeDTO(modeofdisposalfor_discharge);
        return result;
    }

    /**
     *  Get all the modeofdisposalfor_discharges.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Modeofdisposalfor_dischargeDTO> findAll() {
        log.debug("Request to get all Modeofdisposalfor_discharges");
        List<Modeofdisposalfor_dischargeDTO> result = modeofdisposalfor_dischargeRepository.findAll().stream()
            .map(modeofdisposalfor_dischargeMapper::modeofdisposalfor_dischargeToModeofdisposalfor_dischargeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one modeofdisposalfor_discharge by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Modeofdisposalfor_dischargeDTO findOne(String id) {
        log.debug("Request to get Modeofdisposalfor_discharge : {}", id);
        Modeofdisposalfor_discharge modeofdisposalfor_discharge = modeofdisposalfor_dischargeRepository.findOne(UUID.fromString(id));
        Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO = modeofdisposalfor_dischargeMapper.modeofdisposalfor_dischargeToModeofdisposalfor_dischargeDTO(modeofdisposalfor_discharge);
        return modeofdisposalfor_dischargeDTO;
    }

    /**
     *  Delete the  modeofdisposalfor_discharge by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Modeofdisposalfor_discharge : {}", id);
        modeofdisposalfor_dischargeRepository.delete(UUID.fromString(id));
    }
}
