package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectAttachemntService;
import com.hartron.investharyana.domain.ProjectAttachemnt;
import com.hartron.investharyana.repository.ProjectAttachemntRepository;
import com.hartron.investharyana.service.dto.ProjectAttachemntDTO;
import com.hartron.investharyana.service.mapper.ProjectAttachemntMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ProjectAttachemnt.
 */
@Service
public class ProjectAttachemntServiceImpl implements ProjectAttachemntService{

    private final Logger log = LoggerFactory.getLogger(ProjectAttachemntServiceImpl.class);
    
    private final ProjectAttachemntRepository projectAttachemntRepository;

    private final ProjectAttachemntMapper projectAttachemntMapper;

    public ProjectAttachemntServiceImpl(ProjectAttachemntRepository projectAttachemntRepository, ProjectAttachemntMapper projectAttachemntMapper) {
        this.projectAttachemntRepository = projectAttachemntRepository;
        this.projectAttachemntMapper = projectAttachemntMapper;
    }

    /**
     * Save a projectAttachemnt.
     *
     * @param projectAttachemntDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectAttachemntDTO save(ProjectAttachemntDTO projectAttachemntDTO) {
        log.debug("Request to save ProjectAttachemnt : {}", projectAttachemntDTO);
        ProjectAttachemnt projectAttachemnt = projectAttachemntMapper.projectAttachemntDTOToProjectAttachemnt(projectAttachemntDTO);
        projectAttachemnt = projectAttachemntRepository.save(projectAttachemnt);
        ProjectAttachemntDTO result = projectAttachemntMapper.projectAttachemntToProjectAttachemntDTO(projectAttachemnt);
        return result;
    }

    /**
     *  Get all the projectAttachemnts.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectAttachemntDTO> findAll() {
        log.debug("Request to get all ProjectAttachemnts");
        List<ProjectAttachemntDTO> result = projectAttachemntRepository.findAll().stream()
            .map(projectAttachemntMapper::projectAttachemntToProjectAttachemntDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectAttachemnt by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectAttachemntDTO findOne(String id) {
        log.debug("Request to get ProjectAttachemnt : {}", id);
        ProjectAttachemnt projectAttachemnt = projectAttachemntRepository.findOne(UUID.fromString(id));
        ProjectAttachemntDTO projectAttachemntDTO = projectAttachemntMapper.projectAttachemntToProjectAttachemntDTO(projectAttachemnt);
        return projectAttachemntDTO;
    }

    /**
     *  Delete the  projectAttachemnt by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ProjectAttachemnt : {}", id);
        projectAttachemntRepository.delete(UUID.fromString(id));
    }
}
