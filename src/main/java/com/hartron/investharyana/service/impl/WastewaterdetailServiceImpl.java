package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.WastewaterdetailService;
import com.hartron.investharyana.domain.Wastewaterdetail;
import com.hartron.investharyana.repository.WastewaterdetailRepository;
import com.hartron.investharyana.service.dto.WastewaterdetailDTO;
import com.hartron.investharyana.service.mapper.WastewaterdetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Wastewaterdetail.
 */
@Service
public class WastewaterdetailServiceImpl implements WastewaterdetailService{

    private final Logger log = LoggerFactory.getLogger(WastewaterdetailServiceImpl.class);
    
    private final WastewaterdetailRepository wastewaterdetailRepository;

    private final WastewaterdetailMapper wastewaterdetailMapper;

    public WastewaterdetailServiceImpl(WastewaterdetailRepository wastewaterdetailRepository, WastewaterdetailMapper wastewaterdetailMapper) {
        this.wastewaterdetailRepository = wastewaterdetailRepository;
        this.wastewaterdetailMapper = wastewaterdetailMapper;
    }

    /**
     * Save a wastewaterdetail.
     *
     * @param wastewaterdetailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WastewaterdetailDTO save(WastewaterdetailDTO wastewaterdetailDTO) {
        log.debug("Request to save Wastewaterdetail : {}", wastewaterdetailDTO);
        Wastewaterdetail wastewaterdetail = wastewaterdetailMapper.wastewaterdetailDTOToWastewaterdetail(wastewaterdetailDTO);
        wastewaterdetail = wastewaterdetailRepository.save(wastewaterdetail);
        WastewaterdetailDTO result = wastewaterdetailMapper.wastewaterdetailToWastewaterdetailDTO(wastewaterdetail);
        return result;
    }

    /**
     *  Get all the wastewaterdetails.
     *  
     *  @return the list of entities
     */
    @Override
    public List<WastewaterdetailDTO> findAll() {
        log.debug("Request to get all Wastewaterdetails");
        List<WastewaterdetailDTO> result = wastewaterdetailRepository.findAll().stream()
            .map(wastewaterdetailMapper::wastewaterdetailToWastewaterdetailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one wastewaterdetail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public WastewaterdetailDTO findOne(String id) {
        log.debug("Request to get Wastewaterdetail : {}", id);
        Wastewaterdetail wastewaterdetail = wastewaterdetailRepository.findOne(UUID.fromString(id));
        WastewaterdetailDTO wastewaterdetailDTO = wastewaterdetailMapper.wastewaterdetailToWastewaterdetailDTO(wastewaterdetail);
        return wastewaterdetailDTO;
    }

    /**
     *  Delete the  wastewaterdetail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Wastewaterdetail : {}", id);
        wastewaterdetailRepository.delete(UUID.fromString(id));
    }
}
