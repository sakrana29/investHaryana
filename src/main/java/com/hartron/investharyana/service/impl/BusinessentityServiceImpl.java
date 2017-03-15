package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.BusinessentityService;
import com.hartron.investharyana.domain.Businessentity;
import com.hartron.investharyana.repository.BusinessentityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service Implementation for managing Businessentity.
 */
@Service
public class BusinessentityServiceImpl implements BusinessentityService{

    private final Logger log = LoggerFactory.getLogger(BusinessentityServiceImpl.class);
    
    private final BusinessentityRepository businessentityRepository;

    public BusinessentityServiceImpl(BusinessentityRepository businessentityRepository) {
        this.businessentityRepository = businessentityRepository;
    }

    /**
     * Save a businessentity.
     *
     * @param businessentity the entity to save
     * @return the persisted entity
     */
    @Override
    public Businessentity save(Businessentity businessentity) {
        log.debug("Request to save Businessentity : {}", businessentity);
        Businessentity result = businessentityRepository.save(businessentity);
        return result;
    }

    /**
     *  Get all the businessentities.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Businessentity> findAll() {
        log.debug("Request to get all Businessentities");
        List<Businessentity> result = businessentityRepository.findAll();

        return result;
    }

    /**
     *  Get one businessentity by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Businessentity findOne(String id) {
        log.debug("Request to get Businessentity : {}", id);
        Businessentity businessentity = businessentityRepository.findOne(UUID.fromString(id));
        return businessentity;
    }

    /**
     *  Delete the  businessentity by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Businessentity : {}", id);
        businessentityRepository.delete(UUID.fromString(id));
    }
}
