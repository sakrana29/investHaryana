package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Project_phaseService;
import com.hartron.investharyana.domain.Project_phase;
import com.hartron.investharyana.repository.Project_phaseRepository;
import com.hartron.investharyana.service.dto.Project_phaseDTO;
import com.hartron.investharyana.service.mapper.Project_phaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Project_phase.
 */
@Service
public class Project_phaseServiceImpl implements Project_phaseService{

    private final Logger log = LoggerFactory.getLogger(Project_phaseServiceImpl.class);

    private final Project_phaseRepository project_phaseRepository;

    private final Project_phaseMapper project_phaseMapper;

    public Project_phaseServiceImpl(Project_phaseRepository project_phaseRepository, Project_phaseMapper project_phaseMapper) {
        this.project_phaseRepository = project_phaseRepository;
        this.project_phaseMapper = project_phaseMapper;
    }

    /**
     * Save a project_phase.
     *
     * @param project_phaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Project_phaseDTO save(Project_phaseDTO project_phaseDTO) {
        log.debug("Request to save Project_phase : {}", project_phaseDTO);
        Project_phase project_phase = project_phaseMapper.project_phaseDTOToProject_phase(project_phaseDTO);
        project_phase = project_phaseRepository.save(project_phase);
        Project_phaseDTO result = project_phaseMapper.project_phaseToProject_phaseDTO(project_phase);
        return result;
    }

    /**
     *  Get all the project_phases.
     *
     *  @return the list of entities
     */
    @Override
    public List<Project_phaseDTO> findAll() {
        log.debug("Request to get all Project_phases");
        List<Project_phaseDTO> result = project_phaseRepository.findAll().stream()
            .map(project_phaseMapper::project_phaseToProject_phaseDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one project_phase by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Project_phaseDTO findOne(String id) {
        log.debug("Request to get Project_phase : {}", id);
        Project_phase project_phase = project_phaseRepository.findOne(UUID.fromString(id));
        Project_phaseDTO project_phaseDTO = project_phaseMapper.project_phaseToProject_phaseDTO(project_phase);
        return project_phaseDTO;
    }

    /**
     *  Delete the  project_phase by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Project_phase : {}", id);
        project_phaseRepository.delete(UUID.fromString(id));
    }

    @Override
    public List<Project_phaseDTO> findAllByProjectid(String projectid) {
        log.debug("Request to get all Project_phases by projectid");
        List<Project_phaseDTO> result = project_phaseRepository.findAllByProjectid(UUID.fromString(projectid)).stream()
            .map(project_phaseMapper::project_phaseToProject_phaseDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    @Override
    public void deleteByProject(String projectid) {
        log.debug("Request to delete entry from Project_phaseByProject by projectid : {}", projectid);
        project_phaseRepository.deleteByProject(UUID.fromString(projectid));
    }
}
