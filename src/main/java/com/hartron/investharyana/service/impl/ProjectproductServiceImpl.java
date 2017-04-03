package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectproductService;
import com.hartron.investharyana.domain.Projectproduct;
import com.hartron.investharyana.repository.ProjectproductRepository;
import com.hartron.investharyana.service.dto.ProjectproductDTO;
import com.hartron.investharyana.service.mapper.ProjectproductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectproduct.
 */
@Service
public class ProjectproductServiceImpl implements ProjectproductService{

    private final Logger log = LoggerFactory.getLogger(ProjectproductServiceImpl.class);

    private final ProjectproductRepository projectproductRepository;

    private final ProjectproductMapper projectproductMapper;

    public ProjectproductServiceImpl(ProjectproductRepository projectproductRepository, ProjectproductMapper projectproductMapper) {
        this.projectproductRepository = projectproductRepository;
        this.projectproductMapper = projectproductMapper;
    }

    /**
     * Save a projectproduct.
     *
     * @param projectproductDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectproductDTO save(ProjectproductDTO projectproductDTO) {
        log.debug("Request to save Projectproduct : {}", projectproductDTO);
        Projectproduct projectproduct = projectproductMapper.projectproductDTOToProjectproduct(projectproductDTO);
        projectproduct = projectproductRepository.save(projectproduct);
        ProjectproductDTO result = projectproductMapper.projectproductToProjectproductDTO(projectproduct);
        return result;
    }

    /**
     *  Get all the projectproducts.
     *
     *  @return the list of entities
     */
    @Override
    public List<ProjectproductDTO> findAll() {
        log.debug("Request to get all Projectproducts");
        List<ProjectproductDTO> result = projectproductRepository.findAll().stream()
            .map(projectproductMapper::projectproductToProjectproductDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectproduct by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectproductDTO findOne(String id) {
        log.debug("Request to get Projectproduct : {}", id);
        Projectproduct projectproduct = projectproductRepository.findOne(UUID.fromString(id));
        ProjectproductDTO projectproductDTO = projectproductMapper.projectproductToProjectproductDTO(projectproduct);
        return projectproductDTO;
    }

    /**
     *  Delete the  projectproduct by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectproduct : {}", id);
        projectproductRepository.delete(UUID.fromString(id));
    }

    @Override
    public List<ProjectproductDTO> findAllByProjectid(String projectid) {
        log.debug("Request to get all projectproduct by projectid");
        List<ProjectproductDTO> result = projectproductRepository.findAllByProjectid(UUID.fromString(projectid)).stream()
            .map(projectproductMapper::projectproductToProjectproductDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    @Override
    public void deleteByProject(String projectid) {
        log.debug("Request to delete entry from projectproduct by projectid : {}", projectid);
        projectproductRepository.deleteByProject(UUID.fromString(projectid));
    }
}
