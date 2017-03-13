package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.LandusezoneclassificationService;
import com.hartron.investharyana.domain.Landusezoneclassification;
import com.hartron.investharyana.repository.LandusezoneclassificationRepository;
import com.hartron.investharyana.service.dto.LandusezoneclassificationDTO;
import com.hartron.investharyana.service.mapper.LandusezoneclassificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Landusezoneclassification.
 */
@Service
public class LandusezoneclassificationServiceImpl implements LandusezoneclassificationService{

    private final Logger log = LoggerFactory.getLogger(LandusezoneclassificationServiceImpl.class);
    
    private final LandusezoneclassificationRepository landusezoneclassificationRepository;

    private final LandusezoneclassificationMapper landusezoneclassificationMapper;

    public LandusezoneclassificationServiceImpl(LandusezoneclassificationRepository landusezoneclassificationRepository, LandusezoneclassificationMapper landusezoneclassificationMapper) {
        this.landusezoneclassificationRepository = landusezoneclassificationRepository;
        this.landusezoneclassificationMapper = landusezoneclassificationMapper;
    }

    /**
     * Save a landusezoneclassification.
     *
     * @param landusezoneclassificationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LandusezoneclassificationDTO save(LandusezoneclassificationDTO landusezoneclassificationDTO) {
        log.debug("Request to save Landusezoneclassification : {}", landusezoneclassificationDTO);
        Landusezoneclassification landusezoneclassification = landusezoneclassificationMapper.landusezoneclassificationDTOToLandusezoneclassification(landusezoneclassificationDTO);
        landusezoneclassification = landusezoneclassificationRepository.save(landusezoneclassification);
        LandusezoneclassificationDTO result = landusezoneclassificationMapper.landusezoneclassificationToLandusezoneclassificationDTO(landusezoneclassification);
        return result;
    }

    /**
     *  Get all the landusezoneclassifications.
     *  
     *  @return the list of entities
     */
    @Override
    public List<LandusezoneclassificationDTO> findAll() {
        log.debug("Request to get all Landusezoneclassifications");
        List<LandusezoneclassificationDTO> result = landusezoneclassificationRepository.findAll().stream()
            .map(landusezoneclassificationMapper::landusezoneclassificationToLandusezoneclassificationDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one landusezoneclassification by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public LandusezoneclassificationDTO findOne(String id) {
        log.debug("Request to get Landusezoneclassification : {}", id);
        Landusezoneclassification landusezoneclassification = landusezoneclassificationRepository.findOne(UUID.fromString(id));
        LandusezoneclassificationDTO landusezoneclassificationDTO = landusezoneclassificationMapper.landusezoneclassificationToLandusezoneclassificationDTO(landusezoneclassification);
        return landusezoneclassificationDTO;
    }

    /**
     *  Delete the  landusezoneclassification by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Landusezoneclassification : {}", id);
        landusezoneclassificationRepository.delete(UUID.fromString(id));
    }
}
