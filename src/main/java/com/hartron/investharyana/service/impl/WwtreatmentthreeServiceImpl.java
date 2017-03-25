package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.WwtreatmentthreeService;
import com.hartron.investharyana.domain.Wwtreatmentthree;
import com.hartron.investharyana.repository.WwtreatmentthreeRepository;
import com.hartron.investharyana.service.dto.WwtreatmentthreeDTO;
import com.hartron.investharyana.service.mapper.WwtreatmentthreeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Wwtreatmentthree.
 */
@Service
public class WwtreatmentthreeServiceImpl implements WwtreatmentthreeService{

    private final Logger log = LoggerFactory.getLogger(WwtreatmentthreeServiceImpl.class);
    
    private final WwtreatmentthreeRepository wwtreatmentthreeRepository;

    private final WwtreatmentthreeMapper wwtreatmentthreeMapper;

    public WwtreatmentthreeServiceImpl(WwtreatmentthreeRepository wwtreatmentthreeRepository, WwtreatmentthreeMapper wwtreatmentthreeMapper) {
        this.wwtreatmentthreeRepository = wwtreatmentthreeRepository;
        this.wwtreatmentthreeMapper = wwtreatmentthreeMapper;
    }

    /**
     * Save a wwtreatmentthree.
     *
     * @param wwtreatmentthreeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WwtreatmentthreeDTO save(WwtreatmentthreeDTO wwtreatmentthreeDTO) {
        log.debug("Request to save Wwtreatmentthree : {}", wwtreatmentthreeDTO);
        Wwtreatmentthree wwtreatmentthree = wwtreatmentthreeMapper.wwtreatmentthreeDTOToWwtreatmentthree(wwtreatmentthreeDTO);
        wwtreatmentthree = wwtreatmentthreeRepository.save(wwtreatmentthree);
        WwtreatmentthreeDTO result = wwtreatmentthreeMapper.wwtreatmentthreeToWwtreatmentthreeDTO(wwtreatmentthree);
        return result;
    }

    /**
     *  Get all the wwtreatmentthrees.
     *  
     *  @return the list of entities
     */
    @Override
    public List<WwtreatmentthreeDTO> findAll() {
        log.debug("Request to get all Wwtreatmentthrees");
        List<WwtreatmentthreeDTO> result = wwtreatmentthreeRepository.findAll().stream()
            .map(wwtreatmentthreeMapper::wwtreatmentthreeToWwtreatmentthreeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one wwtreatmentthree by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public WwtreatmentthreeDTO findOne(String id) {
        log.debug("Request to get Wwtreatmentthree : {}", id);
        Wwtreatmentthree wwtreatmentthree = wwtreatmentthreeRepository.findOne(UUID.fromString(id));
        WwtreatmentthreeDTO wwtreatmentthreeDTO = wwtreatmentthreeMapper.wwtreatmentthreeToWwtreatmentthreeDTO(wwtreatmentthree);
        return wwtreatmentthreeDTO;
    }

    /**
     *  Delete the  wwtreatmentthree by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Wwtreatmentthree : {}", id);
        wwtreatmentthreeRepository.delete(UUID.fromString(id));
    }
}
