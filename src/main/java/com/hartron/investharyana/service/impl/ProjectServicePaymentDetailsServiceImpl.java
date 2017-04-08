package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectServicePaymentDetailsService;
import com.hartron.investharyana.domain.ProjectServicePaymentDetails;
import com.hartron.investharyana.repository.ProjectServicePaymentDetailsRepository;
import com.hartron.investharyana.service.dto.ProjectServicePaymentDetailsDTO;
import com.hartron.investharyana.service.mapper.ProjectServicePaymentDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ProjectServicePaymentDetails.
 */
@Service
public class ProjectServicePaymentDetailsServiceImpl implements ProjectServicePaymentDetailsService{

    private final Logger log = LoggerFactory.getLogger(ProjectServicePaymentDetailsServiceImpl.class);

    private final ProjectServicePaymentDetailsRepository projectServicePaymentDetailsRepository;

    private final ProjectServicePaymentDetailsMapper projectServicePaymentDetailsMapper;

    public ProjectServicePaymentDetailsServiceImpl(ProjectServicePaymentDetailsRepository projectServicePaymentDetailsRepository, ProjectServicePaymentDetailsMapper projectServicePaymentDetailsMapper) {
        this.projectServicePaymentDetailsRepository = projectServicePaymentDetailsRepository;
        this.projectServicePaymentDetailsMapper = projectServicePaymentDetailsMapper;
    }

    /**
     * Save a projectServicePaymentDetails.
     *
     * @param projectServicePaymentDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectServicePaymentDetailsDTO save(ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO) {
        log.debug("Request to save ProjectServicePaymentDetails : {}", projectServicePaymentDetailsDTO);
        ProjectServicePaymentDetails projectServicePaymentDetails = projectServicePaymentDetailsMapper.projectServicePaymentDetailsDTOToProjectServicePaymentDetails(projectServicePaymentDetailsDTO);
        projectServicePaymentDetails = projectServicePaymentDetailsRepository.save(projectServicePaymentDetails);
        ProjectServicePaymentDetailsDTO result = projectServicePaymentDetailsMapper.projectServicePaymentDetailsToProjectServicePaymentDetailsDTO(projectServicePaymentDetails);
        return result;
    }

    /**
     *  Get all the projectServicePaymentDetails.
     *
     *  @return the list of entities
     */
    @Override
    public List<ProjectServicePaymentDetailsDTO> findAll() {
        log.debug("Request to get all ProjectServicePaymentDetails");
        List<ProjectServicePaymentDetailsDTO> result = projectServicePaymentDetailsRepository.findAll().stream()
            .map(projectServicePaymentDetailsMapper::projectServicePaymentDetailsToProjectServicePaymentDetailsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectServicePaymentDetails by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectServicePaymentDetailsDTO findOne(String id) {
        log.debug("Request to get ProjectServicePaymentDetails : {}", id);
        ProjectServicePaymentDetails projectServicePaymentDetails = projectServicePaymentDetailsRepository.findOne(UUID.fromString(id));
        ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO = projectServicePaymentDetailsMapper.projectServicePaymentDetailsToProjectServicePaymentDetailsDTO(projectServicePaymentDetails);
        return projectServicePaymentDetailsDTO;
    }

    /**
     *  Delete the  projectServicePaymentDetails by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ProjectServicePaymentDetails : {}", id);
        projectServicePaymentDetailsRepository.delete(UUID.fromString(id));
    }

    @Override
    public List<ProjectServicePaymentDetailsDTO> findAllByProjectAndServiceid(String projectid,String serviceid) {
        log.debug("Request to get all ProjectServicePaymentDetails by projectid and serviceid");
        List<ProjectServicePaymentDetailsDTO> result = projectServicePaymentDetailsRepository.findAllByProjectAndServiceid(UUID.fromString(projectid),UUID.fromString(serviceid)).stream()
            .map(projectServicePaymentDetailsMapper::projectServicePaymentDetailsToProjectServicePaymentDetailsDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    @Override
    public void deleteByProject(String projectid,String serviceid) {
        log.debug("Request to delete entry from ProjectServicePaymentDetails by projectid and serviceid: {}", projectid,serviceid);
        projectServicePaymentDetailsRepository.deleteByProjectAndServiceid(UUID.fromString(projectid),UUID.fromString(serviceid));
    }
}
