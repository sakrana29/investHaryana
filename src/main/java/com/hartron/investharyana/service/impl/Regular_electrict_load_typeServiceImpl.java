package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Regular_electrict_load_typeService;
import com.hartron.investharyana.domain.Regular_electrict_load_type;
import com.hartron.investharyana.repository.Regular_electrict_load_typeRepository;
import com.hartron.investharyana.service.dto.Regular_electrict_load_typeDTO;
import com.hartron.investharyana.service.mapper.Regular_electrict_load_typeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Regular_electrict_load_type.
 */
@Service
public class Regular_electrict_load_typeServiceImpl implements Regular_electrict_load_typeService{

    private final Logger log = LoggerFactory.getLogger(Regular_electrict_load_typeServiceImpl.class);
    
    private final Regular_electrict_load_typeRepository regular_electrict_load_typeRepository;

    private final Regular_electrict_load_typeMapper regular_electrict_load_typeMapper;

    public Regular_electrict_load_typeServiceImpl(Regular_electrict_load_typeRepository regular_electrict_load_typeRepository, Regular_electrict_load_typeMapper regular_electrict_load_typeMapper) {
        this.regular_electrict_load_typeRepository = regular_electrict_load_typeRepository;
        this.regular_electrict_load_typeMapper = regular_electrict_load_typeMapper;
    }

    /**
     * Save a regular_electrict_load_type.
     *
     * @param regular_electrict_load_typeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Regular_electrict_load_typeDTO save(Regular_electrict_load_typeDTO regular_electrict_load_typeDTO) {
        log.debug("Request to save Regular_electrict_load_type : {}", regular_electrict_load_typeDTO);
        Regular_electrict_load_type regular_electrict_load_type = regular_electrict_load_typeMapper.regular_electrict_load_typeDTOToRegular_electrict_load_type(regular_electrict_load_typeDTO);
        regular_electrict_load_type = regular_electrict_load_typeRepository.save(regular_electrict_load_type);
        Regular_electrict_load_typeDTO result = regular_electrict_load_typeMapper.regular_electrict_load_typeToRegular_electrict_load_typeDTO(regular_electrict_load_type);
        return result;
    }

    /**
     *  Get all the regular_electrict_load_types.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Regular_electrict_load_typeDTO> findAll() {
        log.debug("Request to get all Regular_electrict_load_types");
        List<Regular_electrict_load_typeDTO> result = regular_electrict_load_typeRepository.findAll().stream()
            .map(regular_electrict_load_typeMapper::regular_electrict_load_typeToRegular_electrict_load_typeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one regular_electrict_load_type by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Regular_electrict_load_typeDTO findOne(String id) {
        log.debug("Request to get Regular_electrict_load_type : {}", id);
        Regular_electrict_load_type regular_electrict_load_type = regular_electrict_load_typeRepository.findOne(UUID.fromString(id));
        Regular_electrict_load_typeDTO regular_electrict_load_typeDTO = regular_electrict_load_typeMapper.regular_electrict_load_typeToRegular_electrict_load_typeDTO(regular_electrict_load_type);
        return regular_electrict_load_typeDTO;
    }

    /**
     *  Delete the  regular_electrict_load_type by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Regular_electrict_load_type : {}", id);
        regular_electrict_load_typeRepository.delete(UUID.fromString(id));
    }
}
