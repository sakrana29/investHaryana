package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectservicedetailService;
import com.hartron.investharyana.domain.Projectservicedetail;
import com.hartron.investharyana.repository.ProjectservicedetailRepository;
import com.hartron.investharyana.service.dto.ProjectservicedetailDTO;
import com.hartron.investharyana.service.mapper.ProjectservicedetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectservicedetail.
 */
@Service
public class ProjectservicedetailServiceImpl implements ProjectservicedetailService{

    private final Logger log = LoggerFactory.getLogger(ProjectservicedetailServiceImpl.class);

    private final ProjectservicedetailRepository projectservicedetailRepository;

    private final ProjectservicedetailMapper projectservicedetailMapper;

    public ProjectservicedetailServiceImpl(ProjectservicedetailRepository projectservicedetailRepository, ProjectservicedetailMapper projectservicedetailMapper) {
        this.projectservicedetailRepository = projectservicedetailRepository;
        this.projectservicedetailMapper = projectservicedetailMapper;
    }

    /**
     * Save a projectservicedetail.
     *
     * @param projectservicedetailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectservicedetailDTO save(ProjectservicedetailDTO projectservicedetailDTO) {
        log.debug("Request to save Projectservicedetail : {}", projectservicedetailDTO);
        Projectservicedetail projectservicedetail = projectservicedetailMapper.projectservicedetailDTOToProjectservicedetail(projectservicedetailDTO);
        projectservicedetail = projectservicedetailRepository.save(projectservicedetail);
        ProjectservicedetailDTO result = projectservicedetailMapper.projectservicedetailToProjectservicedetailDTO(projectservicedetail);
        return result;
    }

    /**
     *  Get all the projectservicedetails.
     *
     *  @return the list of entities
     */
    @Override
    public List<ProjectservicedetailDTO> findAll() {
        log.debug("Request to get all Projectservicedetails");
        List<ProjectservicedetailDTO> result = projectservicedetailRepository.findAll().stream()
            .map(projectservicedetailMapper::projectservicedetailToProjectservicedetailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectservicedetail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectservicedetailDTO findOne(String id) {
        log.debug("Request to get Projectservicedetail : {}", id);
        Projectservicedetail projectservicedetail = projectservicedetailRepository.findOne(UUID.fromString(id));
        ProjectservicedetailDTO projectservicedetailDTO = projectservicedetailMapper.projectservicedetailToProjectservicedetailDTO(projectservicedetail);
        return projectservicedetailDTO;
    }

    /**
     *  Delete the  projectservicedetail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectservicedetail : {}", id);
        projectservicedetailRepository.delete(UUID.fromString(id));
    }

    @Override
    public List<ProjectservicedetailDTO> findAllByProjectid(String projectid) {
    log.debug("Request to get all projectprocess by projectid");
    List<ProjectservicedetailDTO> result = projectservicedetailRepository.findAllByProjectid(UUID.fromString(projectid)).stream()
        .map(projectservicedetailMapper::projectservicedetailToProjectservicedetailDTO)
        .collect(Collectors.toCollection(LinkedList::new));
    return result;
    }

    @Override
    public void deleteByProject(String projectid) {
        log.debug("Request to delete entry from projectprocess by projectid : {}", projectid);
        projectservicedetailRepository.deleteByProject(UUID.fromString(projectid));
    }
}
