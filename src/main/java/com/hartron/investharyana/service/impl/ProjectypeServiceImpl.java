package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectypeService;
import com.hartron.investharyana.domain.Projectype;
import com.hartron.investharyana.repository.ProjectypeRepository;
import com.hartron.investharyana.service.dto.ProjectypeDTO;
import com.hartron.investharyana.service.mapper.ProjectypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectype.
 */
@Service
public class ProjectypeServiceImpl implements ProjectypeService{

    private final Logger log = LoggerFactory.getLogger(ProjectypeServiceImpl.class);
    
    private final ProjectypeRepository projectypeRepository;

    private final ProjectypeMapper projectypeMapper;

    public ProjectypeServiceImpl(ProjectypeRepository projectypeRepository, ProjectypeMapper projectypeMapper) {
        this.projectypeRepository = projectypeRepository;
        this.projectypeMapper = projectypeMapper;
    }

    /**
     * Save a projectype.
     *
     * @param projectypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectypeDTO save(ProjectypeDTO projectypeDTO) {
        log.debug("Request to save Projectype : {}", projectypeDTO);
        Projectype projectype = projectypeMapper.projectypeDTOToProjectype(projectypeDTO);
        projectype = projectypeRepository.save(projectype);
        ProjectypeDTO result = projectypeMapper.projectypeToProjectypeDTO(projectype);
        return result;
    }

    /**
     *  Get all the projectypes.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectypeDTO> findAll() {
        log.debug("Request to get all Projectypes");
        List<ProjectypeDTO> result = projectypeRepository.findAll().stream()
            .map(projectypeMapper::projectypeToProjectypeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectype by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectypeDTO findOne(String id) {
        log.debug("Request to get Projectype : {}", id);
        Projectype projectype = projectypeRepository.findOne(UUID.fromString(id));
        ProjectypeDTO projectypeDTO = projectypeMapper.projectypeToProjectypeDTO(projectype);
        return projectypeDTO;
    }

    /**
     *  Delete the  projectype by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectype : {}", id);
        projectypeRepository.delete(UUID.fromString(id));
    }
}
