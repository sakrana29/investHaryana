package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectServiceReportInfoService;
import com.hartron.investharyana.domain.ProjectServiceReportInfo;
import com.hartron.investharyana.repository.ProjectServiceReportInfoRepository;
import com.hartron.investharyana.service.dto.ProjectServiceReportInfoDTO;
import com.hartron.investharyana.service.mapper.ProjectServiceReportInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ProjectServiceReportInfo.
 */
@Service
public class ProjectServiceReportInfoServiceImpl implements ProjectServiceReportInfoService{

    private final Logger log = LoggerFactory.getLogger(ProjectServiceReportInfoServiceImpl.class);

    private final ProjectServiceReportInfoRepository projectServiceReportInfoRepository;

    private final ProjectServiceReportInfoMapper projectServiceReportInfoMapper;

    public ProjectServiceReportInfoServiceImpl(ProjectServiceReportInfoRepository projectServiceReportInfoRepository, ProjectServiceReportInfoMapper projectServiceReportInfoMapper) {
        this.projectServiceReportInfoRepository = projectServiceReportInfoRepository;
        this.projectServiceReportInfoMapper = projectServiceReportInfoMapper;
    }

    /**
     * Save a projectServiceReportInfo.
     *
     * @param projectServiceReportInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectServiceReportInfoDTO save(ProjectServiceReportInfoDTO projectServiceReportInfoDTO) {
        log.debug("Request to save ProjectServiceReportInfo : {}", projectServiceReportInfoDTO);
        ProjectServiceReportInfo projectServiceReportInfo = projectServiceReportInfoMapper.projectServiceReportInfoDTOToProjectServiceReportInfo(projectServiceReportInfoDTO);
        projectServiceReportInfo = projectServiceReportInfoRepository.save(projectServiceReportInfo);
        ProjectServiceReportInfoDTO result = projectServiceReportInfoMapper.projectServiceReportInfoToProjectServiceReportInfoDTO(projectServiceReportInfo);
        return result;
    }

    /**
     *  Get all the projectServiceReportInfos.
     *
     *  @return the list of entities
     */
    @Override
    public List<ProjectServiceReportInfoDTO> findAll() {
        log.debug("Request to get all ProjectServiceReportInfos");
        List<ProjectServiceReportInfoDTO> result = projectServiceReportInfoRepository.findAll().stream()
            .map(projectServiceReportInfoMapper::projectServiceReportInfoToProjectServiceReportInfoDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectServiceReportInfo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectServiceReportInfoDTO findOne(String id) {
        log.debug("Request to get ProjectServiceReportInfo : {}", id);
        ProjectServiceReportInfo projectServiceReportInfo = projectServiceReportInfoRepository.findOne(UUID.fromString(id));
        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = projectServiceReportInfoMapper.projectServiceReportInfoToProjectServiceReportInfoDTO(projectServiceReportInfo);
        return projectServiceReportInfoDTO;
    }

    /**
     *  Delete the  projectServiceReportInfo by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ProjectServiceReportInfo : {}", id);
        ProjectServiceReportInfo projectServiceReportInfo =  projectServiceReportInfoRepository.findOne(UUID.fromString(id));
        if(projectServiceReportInfo!=null)
            projectServiceReportInfoRepository.delete(projectServiceReportInfo);
    }

    @Override
    public List<ProjectServiceReportInfoDTO> findAllByDepartment(String departmentname) {
        log.debug("Request to get all ProjectServiceReportInfos by department");
        List<ProjectServiceReportInfoDTO> result = projectServiceReportInfoRepository.findAllByDept(departmentname).stream()
            .map(projectServiceReportInfoMapper::projectServiceReportInfoToProjectServiceReportInfoDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    @Override
    public ProjectServiceReportInfoDTO findByProjectDepartmentService(String projectid, String departmentname, String servicename) {
        log.debug("Request to get ProjectServiceReportInfo by ProjectDepartmentService : {}", projectid,departmentname,servicename);
        ProjectServiceReportInfo projectServiceReportInfo = projectServiceReportInfoRepository.findOneByProjectDepartmentService(UUID.fromString(projectid),departmentname,servicename);
        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = projectServiceReportInfoMapper.projectServiceReportInfoToProjectServiceReportInfoDTO(projectServiceReportInfo);
        return projectServiceReportInfoDTO;
    }
}
