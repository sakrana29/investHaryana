package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Project_finance_investmentService;
import com.hartron.investharyana.domain.Project_finance_investment;
import com.hartron.investharyana.repository.Project_finance_investmentRepository;
import com.hartron.investharyana.service.dto.Project_finance_investmentDTO;
import com.hartron.investharyana.service.mapper.Project_finance_investmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Project_finance_investment.
 */
@Service
public class Project_finance_investmentServiceImpl implements Project_finance_investmentService{

    private final Logger log = LoggerFactory.getLogger(Project_finance_investmentServiceImpl.class);
    
    private final Project_finance_investmentRepository project_finance_investmentRepository;

    private final Project_finance_investmentMapper project_finance_investmentMapper;

    public Project_finance_investmentServiceImpl(Project_finance_investmentRepository project_finance_investmentRepository, Project_finance_investmentMapper project_finance_investmentMapper) {
        this.project_finance_investmentRepository = project_finance_investmentRepository;
        this.project_finance_investmentMapper = project_finance_investmentMapper;
    }

    /**
     * Save a project_finance_investment.
     *
     * @param project_finance_investmentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Project_finance_investmentDTO save(Project_finance_investmentDTO project_finance_investmentDTO) {
        log.debug("Request to save Project_finance_investment : {}", project_finance_investmentDTO);
        Project_finance_investment project_finance_investment = project_finance_investmentMapper.project_finance_investmentDTOToProject_finance_investment(project_finance_investmentDTO);
        project_finance_investment = project_finance_investmentRepository.save(project_finance_investment);
        Project_finance_investmentDTO result = project_finance_investmentMapper.project_finance_investmentToProject_finance_investmentDTO(project_finance_investment);
        return result;
    }

    /**
     *  Get all the project_finance_investments.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Project_finance_investmentDTO> findAll() {
        log.debug("Request to get all Project_finance_investments");
        List<Project_finance_investmentDTO> result = project_finance_investmentRepository.findAll().stream()
            .map(project_finance_investmentMapper::project_finance_investmentToProject_finance_investmentDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one project_finance_investment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Project_finance_investmentDTO findOne(String id) {
        log.debug("Request to get Project_finance_investment : {}", id);
        Project_finance_investment project_finance_investment = project_finance_investmentRepository.findOne(UUID.fromString(id));
        Project_finance_investmentDTO project_finance_investmentDTO = project_finance_investmentMapper.project_finance_investmentToProject_finance_investmentDTO(project_finance_investment);
        return project_finance_investmentDTO;
    }

    /**
     *  Delete the  project_finance_investment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Project_finance_investment : {}", id);
        project_finance_investmentRepository.delete(UUID.fromString(id));
    }
}
