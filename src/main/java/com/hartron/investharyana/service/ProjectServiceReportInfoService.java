package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectServiceReportInfoDTO;
import java.util.List;

/**
 * Service Interface for managing ProjectServiceReportInfo.
 */
public interface ProjectServiceReportInfoService {

    /**
     * Save a projectServiceReportInfo.
     *
     * @param projectServiceReportInfoDTO the entity to save
     * @return the persisted entity
     */
    ProjectServiceReportInfoDTO save(ProjectServiceReportInfoDTO projectServiceReportInfoDTO);

    /**
     *  Get all the projectServiceReportInfos.
     *
     *  @return the list of entities
     */
    List<ProjectServiceReportInfoDTO> findAll();

    /**
     *  Get the "id" projectServiceReportInfo.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectServiceReportInfoDTO findOne(String id);

    /**
     *  Delete the "id" projectServiceReportInfo.
     *
     *  @param id the id of the entity
     */
    void delete(String id);

    List<ProjectServiceReportInfoDTO> findAllByDepartment(String departmentname);

    ProjectServiceReportInfoDTO findByProjectDepartmentService(String projectid, String departmentname,String servicename);
}
