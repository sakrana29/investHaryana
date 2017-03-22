package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectdetailcombinecodesService;
import com.hartron.investharyana.domain.Projectdetailcombinecodes;
import com.hartron.investharyana.repository.ProjectdetailcombinecodesRepository;
import com.hartron.investharyana.service.dto.ProjectdetailcombinecodesDTO;
import com.hartron.investharyana.service.mapper.ProjectdetailcombinecodesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectdetailcombinecodes.
 */
@Service
public class ProjectdetailcombinecodesServiceImpl implements ProjectdetailcombinecodesService{

    private final Logger log = LoggerFactory.getLogger(ProjectdetailcombinecodesServiceImpl.class);
    
    private final ProjectdetailcombinecodesRepository projectdetailcombinecodesRepository;

    private final ProjectdetailcombinecodesMapper projectdetailcombinecodesMapper;

    public ProjectdetailcombinecodesServiceImpl(ProjectdetailcombinecodesRepository projectdetailcombinecodesRepository, ProjectdetailcombinecodesMapper projectdetailcombinecodesMapper) {
        this.projectdetailcombinecodesRepository = projectdetailcombinecodesRepository;
        this.projectdetailcombinecodesMapper = projectdetailcombinecodesMapper;
    }

    /**
     * Save a projectdetailcombinecodes.
     *
     * @param projectdetailcombinecodesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectdetailcombinecodesDTO save(ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO) {
        log.debug("Request to save Projectdetailcombinecodes : {}", projectdetailcombinecodesDTO);
        Projectdetailcombinecodes projectdetailcombinecodes = projectdetailcombinecodesMapper.projectdetailcombinecodesDTOToProjectdetailcombinecodes(projectdetailcombinecodesDTO);
        projectdetailcombinecodes = projectdetailcombinecodesRepository.save(projectdetailcombinecodes);
        ProjectdetailcombinecodesDTO result = projectdetailcombinecodesMapper.projectdetailcombinecodesToProjectdetailcombinecodesDTO(projectdetailcombinecodes);
        return result;
    }

    /**
     *  Get all the projectdetailcombinecodes.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectdetailcombinecodesDTO> findAll() {
        log.debug("Request to get all Projectdetailcombinecodes");
        List<ProjectdetailcombinecodesDTO> result = projectdetailcombinecodesRepository.findAll().stream()
            .map(projectdetailcombinecodesMapper::projectdetailcombinecodesToProjectdetailcombinecodesDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectdetailcombinecodes by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectdetailcombinecodesDTO findOne(String id) {
        log.debug("Request to get Projectdetailcombinecodes : {}", id);
        Projectdetailcombinecodes projectdetailcombinecodes = projectdetailcombinecodesRepository.findOne(UUID.fromString(id));
        ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO = projectdetailcombinecodesMapper.projectdetailcombinecodesToProjectdetailcombinecodesDTO(projectdetailcombinecodes);
        return projectdetailcombinecodesDTO;
    }

    /**
     *  Delete the  projectdetailcombinecodes by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectdetailcombinecodes : {}", id);
        projectdetailcombinecodesRepository.delete(UUID.fromString(id));
    }
}
