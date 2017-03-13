package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectprocessflowstepsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectprocessflowsteps and its DTO ProjectprocessflowstepsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectprocessflowstepsMapper {

    ProjectprocessflowstepsDTO projectprocessflowstepsToProjectprocessflowstepsDTO(Projectprocessflowsteps projectprocessflowsteps);

    List<ProjectprocessflowstepsDTO> projectprocessflowstepsToProjectprocessflowstepsDTOs(List<Projectprocessflowsteps> projectprocessflowsteps);

    Projectprocessflowsteps projectprocessflowstepsDTOToProjectprocessflowsteps(ProjectprocessflowstepsDTO projectprocessflowstepsDTO);

    List<Projectprocessflowsteps> projectprocessflowstepsDTOsToProjectprocessflowsteps(List<ProjectprocessflowstepsDTO> projectprocessflowstepsDTOs);
}
