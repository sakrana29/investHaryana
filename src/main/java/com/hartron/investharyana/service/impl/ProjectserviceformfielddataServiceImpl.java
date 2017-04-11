package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectserviceformfielddataService;
import com.hartron.investharyana.domain.Projectserviceformfielddata;
import com.hartron.investharyana.repository.ProjectserviceformfielddataRepository;
import com.hartron.investharyana.service.dto.ProjectserviceformfielddataDTO;
import com.hartron.investharyana.service.mapper.ProjectserviceformfielddataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectserviceformfielddata.
 */
@Service
public class ProjectserviceformfielddataServiceImpl implements ProjectserviceformfielddataService{

    private final Logger log = LoggerFactory.getLogger(ProjectserviceformfielddataServiceImpl.class);

    private final ProjectserviceformfielddataRepository projectserviceformfielddataRepository;

    private final ProjectserviceformfielddataMapper projectserviceformfielddataMapper;

    public ProjectserviceformfielddataServiceImpl(ProjectserviceformfielddataRepository projectserviceformfielddataRepository, ProjectserviceformfielddataMapper projectserviceformfielddataMapper) {
        this.projectserviceformfielddataRepository = projectserviceformfielddataRepository;
        this.projectserviceformfielddataMapper = projectserviceformfielddataMapper;
    }

    /**
     * Save a projectserviceformfielddata.
     *
     * @param projectserviceformfielddataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectserviceformfielddataDTO save(ProjectserviceformfielddataDTO projectserviceformfielddataDTO) {
        log.debug("Request to save Projectserviceformfielddata : {}", projectserviceformfielddataDTO);
        Projectserviceformfielddata projectserviceformfielddata = projectserviceformfielddataMapper.projectserviceformfielddataDTOToProjectserviceformfielddata(projectserviceformfielddataDTO);
        projectserviceformfielddata = projectserviceformfielddataRepository.save(projectserviceformfielddata);
        ProjectserviceformfielddataDTO result = projectserviceformfielddataMapper.projectserviceformfielddataToProjectserviceformfielddataDTO(projectserviceformfielddata);
        return result;
    }

    /**
     *  Get all the projectserviceformfielddata.
     *
     *  @return the list of entities
     */
    @Override
    public List<ProjectserviceformfielddataDTO> findAll() {
        log.debug("Request to get all Projectserviceformfielddata");
        List<ProjectserviceformfielddataDTO> result = projectserviceformfielddataRepository.findAll().stream()
            .map(projectserviceformfielddataMapper::projectserviceformfielddataToProjectserviceformfielddataDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectserviceformfielddata by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectserviceformfielddataDTO findOne(String id) {
        log.debug("Request to get Projectserviceformfielddata : {}", id);
        Projectserviceformfielddata projectserviceformfielddata = projectserviceformfielddataRepository.findOne(UUID.fromString(id));
        ProjectserviceformfielddataDTO projectserviceformfielddataDTO = projectserviceformfielddataMapper.projectserviceformfielddataToProjectserviceformfielddataDTO(projectserviceformfielddata);
        return projectserviceformfielddataDTO;
    }

    /**
     *  Delete the  projectserviceformfielddata by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectserviceformfielddata : {}", id);
        projectserviceformfielddataRepository.delete(UUID.fromString(id));
    }
    @Override
    public List<ProjectserviceformfielddataDTO> findAllByProjectid(String projectid) {
        log.debug("Request to get all serviceformfield by serviceid : {}", projectid);
        List<ProjectserviceformfielddataDTO> result = projectserviceformfielddataRepository.findAllByProjectid(UUID.fromString(projectid)).stream()
            .map(projectserviceformfielddataMapper::projectserviceformfielddataToProjectserviceformfielddataDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }
}
