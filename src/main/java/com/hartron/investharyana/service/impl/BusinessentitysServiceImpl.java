package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.BusinessentitysService;
import com.hartron.investharyana.domain.Businessentitys;
import com.hartron.investharyana.repository.BusinessentitysRepository;
import com.hartron.investharyana.service.dto.BusinessentitysDTO;
import com.hartron.investharyana.service.mapper.BusinessentitysMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Businessentitys.
 */
@Service
public class BusinessentitysServiceImpl implements BusinessentitysService{

    private final Logger log = LoggerFactory.getLogger(BusinessentitysServiceImpl.class);
    
    private final BusinessentitysRepository businessentitysRepository;

    private final BusinessentitysMapper businessentitysMapper;

    public BusinessentitysServiceImpl(BusinessentitysRepository businessentitysRepository, BusinessentitysMapper businessentitysMapper) {
        this.businessentitysRepository = businessentitysRepository;
        this.businessentitysMapper = businessentitysMapper;
    }

    /**
     * Save a businessentitys.
     *
     * @param businessentitysDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BusinessentitysDTO save(BusinessentitysDTO businessentitysDTO) {
        log.debug("Request to save Businessentitys : {}", businessentitysDTO);
        Businessentitys businessentitys = businessentitysMapper.businessentitysDTOToBusinessentitys(businessentitysDTO);
        businessentitys = businessentitysRepository.save(businessentitys);
        BusinessentitysDTO result = businessentitysMapper.businessentitysToBusinessentitysDTO(businessentitys);
        return result;
    }

    /**
     *  Get all the businessentitys.
     *  
     *  @return the list of entities
     */
    @Override
    public List<BusinessentitysDTO> findAll() {
        log.debug("Request to get all Businessentitys");
        List<BusinessentitysDTO> result = businessentitysRepository.findAll().stream()
            .map(businessentitysMapper::businessentitysToBusinessentitysDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one businessentitys by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public BusinessentitysDTO findOne(String id) {
        log.debug("Request to get Businessentitys : {}", id);
        Businessentitys businessentitys = businessentitysRepository.findOne(UUID.fromString(id));
        BusinessentitysDTO businessentitysDTO = businessentitysMapper.businessentitysToBusinessentitysDTO(businessentitys);
        return businessentitysDTO;
    }

    /**
     *  Delete the  businessentitys by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Businessentitys : {}", id);
        businessentitysRepository.delete(UUID.fromString(id));
    }
}
