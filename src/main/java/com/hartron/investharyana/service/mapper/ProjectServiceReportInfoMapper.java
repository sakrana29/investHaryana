package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectServiceReportInfoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProjectServiceReportInfo and its DTO ProjectServiceReportInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectServiceReportInfoMapper {

    ProjectServiceReportInfoDTO projectServiceReportInfoToProjectServiceReportInfoDTO(ProjectServiceReportInfo projectServiceReportInfo);

    List<ProjectServiceReportInfoDTO> projectServiceReportInfosToProjectServiceReportInfoDTOs(List<ProjectServiceReportInfo> projectServiceReportInfos);

    ProjectServiceReportInfo projectServiceReportInfoDTOToProjectServiceReportInfo(ProjectServiceReportInfoDTO projectServiceReportInfoDTO);

    List<ProjectServiceReportInfo> projectServiceReportInfoDTOsToProjectServiceReportInfos(List<ProjectServiceReportInfoDTO> projectServiceReportInfoDTOs);
}
