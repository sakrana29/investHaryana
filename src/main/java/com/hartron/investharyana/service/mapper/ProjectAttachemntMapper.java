package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectAttachemntDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProjectAttachemnt and its DTO ProjectAttachemntDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectAttachemntMapper {

    ProjectAttachemntDTO projectAttachemntToProjectAttachemntDTO(ProjectAttachemnt projectAttachemnt);

    List<ProjectAttachemntDTO> projectAttachemntsToProjectAttachemntDTOs(List<ProjectAttachemnt> projectAttachemnts);

    ProjectAttachemnt projectAttachemntDTOToProjectAttachemnt(ProjectAttachemntDTO projectAttachemntDTO);

    List<ProjectAttachemnt> projectAttachemntDTOsToProjectAttachemnts(List<ProjectAttachemntDTO> projectAttachemntDTOs);
}
