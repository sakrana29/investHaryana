package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectServiceLogDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProjectServiceLog and its DTO ProjectServiceLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectServiceLogMapper {

    ProjectServiceLogDTO projectServiceLogToProjectServiceLogDTO(ProjectServiceLog projectServiceLog);

    List<ProjectServiceLogDTO> projectServiceLogsToProjectServiceLogDTOs(List<ProjectServiceLog> projectServiceLogs);

    ProjectServiceLog projectServiceLogDTOToProjectServiceLog(ProjectServiceLogDTO projectServiceLogDTO);

    List<ProjectServiceLog> projectServiceLogDTOsToProjectServiceLogs(List<ProjectServiceLogDTO> projectServiceLogDTOs);
}
