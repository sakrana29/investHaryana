package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectServiceLogService;
import com.hartron.investharyana.domain.ProjectServiceLog;
import com.hartron.investharyana.repository.ProjectServiceLogRepository;
import com.hartron.investharyana.service.dto.ProjectServiceLogDTO;
import com.hartron.investharyana.service.mapper.ProjectServiceLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ProjectServiceLog.
 */
@Service
public class ProjectServiceLogServiceImpl implements ProjectServiceLogService{

    private final Logger log = LoggerFactory.getLogger(ProjectServiceLogServiceImpl.class);
    
    private final ProjectServiceLogRepository projectServiceLogRepository;

    private final ProjectServiceLogMapper projectServiceLogMapper;

    public ProjectServiceLogServiceImpl(ProjectServiceLogRepository projectServiceLogRepository, ProjectServiceLogMapper projectServiceLogMapper) {
        this.projectServiceLogRepository = projectServiceLogRepository;
        this.projectServiceLogMapper = projectServiceLogMapper;
    }

    /**
     * Save a projectServiceLog.
     *
     * @param projectServiceLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectServiceLogDTO save(ProjectServiceLogDTO projectServiceLogDTO) {
        log.debug("Request to save ProjectServiceLog : {}", projectServiceLogDTO);
        ProjectServiceLog projectServiceLog = projectServiceLogMapper.projectServiceLogDTOToProjectServiceLog(projectServiceLogDTO);
        projectServiceLog = projectServiceLogRepository.save(projectServiceLog);
        ProjectServiceLogDTO result = projectServiceLogMapper.projectServiceLogToProjectServiceLogDTO(projectServiceLog);
        return result;
    }

    /**
     *  Get all the projectServiceLogs.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectServiceLogDTO> findAll() {
        log.debug("Request to get all ProjectServiceLogs");
        List<ProjectServiceLogDTO> result = projectServiceLogRepository.findAll().stream()
            .map(projectServiceLogMapper::projectServiceLogToProjectServiceLogDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectServiceLog by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectServiceLogDTO findOne(String id) {
        log.debug("Request to get ProjectServiceLog : {}", id);
        ProjectServiceLog projectServiceLog = projectServiceLogRepository.findOne(UUID.fromString(id));
        ProjectServiceLogDTO projectServiceLogDTO = projectServiceLogMapper.projectServiceLogToProjectServiceLogDTO(projectServiceLog);
        return projectServiceLogDTO;
    }

    /**
     *  Delete the  projectServiceLog by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ProjectServiceLog : {}", id);
        projectServiceLogRepository.delete(UUID.fromString(id));
    }
}
