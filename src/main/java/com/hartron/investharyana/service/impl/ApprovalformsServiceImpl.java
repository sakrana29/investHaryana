package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ApprovalformsService;
import com.hartron.investharyana.domain.Approvalforms;
import com.hartron.investharyana.repository.ApprovalformsRepository;
import com.hartron.investharyana.service.dto.ApprovalformsDTO;
import com.hartron.investharyana.service.mapper.ApprovalformsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Approvalforms.
 */
@Service
public class ApprovalformsServiceImpl implements ApprovalformsService{

    private final Logger log = LoggerFactory.getLogger(ApprovalformsServiceImpl.class);
    
    private final ApprovalformsRepository approvalformsRepository;

    private final ApprovalformsMapper approvalformsMapper;

    public ApprovalformsServiceImpl(ApprovalformsRepository approvalformsRepository, ApprovalformsMapper approvalformsMapper) {
        this.approvalformsRepository = approvalformsRepository;
        this.approvalformsMapper = approvalformsMapper;
    }

    /**
     * Save a approvalforms.
     *
     * @param approvalformsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ApprovalformsDTO save(ApprovalformsDTO approvalformsDTO) {
        log.debug("Request to save Approvalforms : {}", approvalformsDTO);
        Approvalforms approvalforms = approvalformsMapper.approvalformsDTOToApprovalforms(approvalformsDTO);
        approvalforms = approvalformsRepository.save(approvalforms);
        ApprovalformsDTO result = approvalformsMapper.approvalformsToApprovalformsDTO(approvalforms);
        return result;
    }

    /**
     *  Get all the approvalforms.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ApprovalformsDTO> findAll() {
        log.debug("Request to get all Approvalforms");
        List<ApprovalformsDTO> result = approvalformsRepository.findAll().stream()
            .map(approvalformsMapper::approvalformsToApprovalformsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one approvalforms by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ApprovalformsDTO findOne(String id) {
        log.debug("Request to get Approvalforms : {}", id);
        Approvalforms approvalforms = approvalformsRepository.findOne(UUID.fromString(id));
        ApprovalformsDTO approvalformsDTO = approvalformsMapper.approvalformsToApprovalformsDTO(approvalforms);
        return approvalformsDTO;
    }

    /**
     *  Delete the  approvalforms by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Approvalforms : {}", id);
        approvalformsRepository.delete(UUID.fromString(id));
    }
}
