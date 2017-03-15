package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectprocessflowstepsService;
import com.hartron.investharyana.domain.Projectprocessflowsteps;
import com.hartron.investharyana.repository.ProjectprocessflowstepsRepository;
import com.hartron.investharyana.service.dto.ProjectprocessflowstepsDTO;
import com.hartron.investharyana.service.mapper.ProjectprocessflowstepsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectprocessflowsteps.
 */
@Service
public class ProjectprocessflowstepsServiceImpl implements ProjectprocessflowstepsService{

    private final Logger log = LoggerFactory.getLogger(ProjectprocessflowstepsServiceImpl.class);
    
    private final ProjectprocessflowstepsRepository projectprocessflowstepsRepository;

    private final ProjectprocessflowstepsMapper projectprocessflowstepsMapper;

    public ProjectprocessflowstepsServiceImpl(ProjectprocessflowstepsRepository projectprocessflowstepsRepository, ProjectprocessflowstepsMapper projectprocessflowstepsMapper) {
        this.projectprocessflowstepsRepository = projectprocessflowstepsRepository;
        this.projectprocessflowstepsMapper = projectprocessflowstepsMapper;
    }

    /**
     * Save a projectprocessflowsteps.
     *
     * @param projectprocessflowstepsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectprocessflowstepsDTO save(ProjectprocessflowstepsDTO projectprocessflowstepsDTO) {
        log.debug("Request to save Projectprocessflowsteps : {}", projectprocessflowstepsDTO);
        Projectprocessflowsteps projectprocessflowsteps = projectprocessflowstepsMapper.projectprocessflowstepsDTOToProjectprocessflowsteps(projectprocessflowstepsDTO);
        projectprocessflowsteps = projectprocessflowstepsRepository.save(projectprocessflowsteps);
        ProjectprocessflowstepsDTO result = projectprocessflowstepsMapper.projectprocessflowstepsToProjectprocessflowstepsDTO(projectprocessflowsteps);
        return result;
    }

    /**
     *  Get all the projectprocessflowsteps.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectprocessflowstepsDTO> findAll() {
        log.debug("Request to get all Projectprocessflowsteps");
        List<ProjectprocessflowstepsDTO> result = projectprocessflowstepsRepository.findAll().stream()
            .map(projectprocessflowstepsMapper::projectprocessflowstepsToProjectprocessflowstepsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectprocessflowsteps by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectprocessflowstepsDTO findOne(String id) {
        log.debug("Request to get Projectprocessflowsteps : {}", id);
        Projectprocessflowsteps projectprocessflowsteps = projectprocessflowstepsRepository.findOne(UUID.fromString(id));
        ProjectprocessflowstepsDTO projectprocessflowstepsDTO = projectprocessflowstepsMapper.projectprocessflowstepsToProjectprocessflowstepsDTO(projectprocessflowsteps);
        return projectprocessflowstepsDTO;
    }

    /**
     *  Delete the  projectprocessflowsteps by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectprocessflowsteps : {}", id);
        projectprocessflowstepsRepository.delete(UUID.fromString(id));
    }
}
