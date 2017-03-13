package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ManufacturingdetailService;
import com.hartron.investharyana.domain.Manufacturingdetail;
import com.hartron.investharyana.repository.ManufacturingdetailRepository;
import com.hartron.investharyana.service.dto.ManufacturingdetailDTO;
import com.hartron.investharyana.service.mapper.ManufacturingdetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Manufacturingdetail.
 */
@Service
public class ManufacturingdetailServiceImpl implements ManufacturingdetailService{

    private final Logger log = LoggerFactory.getLogger(ManufacturingdetailServiceImpl.class);
    
    private final ManufacturingdetailRepository manufacturingdetailRepository;

    private final ManufacturingdetailMapper manufacturingdetailMapper;

    public ManufacturingdetailServiceImpl(ManufacturingdetailRepository manufacturingdetailRepository, ManufacturingdetailMapper manufacturingdetailMapper) {
        this.manufacturingdetailRepository = manufacturingdetailRepository;
        this.manufacturingdetailMapper = manufacturingdetailMapper;
    }

    /**
     * Save a manufacturingdetail.
     *
     * @param manufacturingdetailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ManufacturingdetailDTO save(ManufacturingdetailDTO manufacturingdetailDTO) {
        log.debug("Request to save Manufacturingdetail : {}", manufacturingdetailDTO);
        Manufacturingdetail manufacturingdetail = manufacturingdetailMapper.manufacturingdetailDTOToManufacturingdetail(manufacturingdetailDTO);
        manufacturingdetail = manufacturingdetailRepository.save(manufacturingdetail);
        ManufacturingdetailDTO result = manufacturingdetailMapper.manufacturingdetailToManufacturingdetailDTO(manufacturingdetail);
        return result;
    }

    /**
     *  Get all the manufacturingdetails.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ManufacturingdetailDTO> findAll() {
        log.debug("Request to get all Manufacturingdetails");
        List<ManufacturingdetailDTO> result = manufacturingdetailRepository.findAll().stream()
            .map(manufacturingdetailMapper::manufacturingdetailToManufacturingdetailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one manufacturingdetail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ManufacturingdetailDTO findOne(String id) {
        log.debug("Request to get Manufacturingdetail : {}", id);
        Manufacturingdetail manufacturingdetail = manufacturingdetailRepository.findOne(UUID.fromString(id));
        ManufacturingdetailDTO manufacturingdetailDTO = manufacturingdetailMapper.manufacturingdetailToManufacturingdetailDTO(manufacturingdetail);
        return manufacturingdetailDTO;
    }

    /**
     *  Delete the  manufacturingdetail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Manufacturingdetail : {}", id);
        manufacturingdetailRepository.delete(UUID.fromString(id));
    }
}
