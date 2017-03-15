package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectypeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectype and its DTO ProjectypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectypeMapper {

    ProjectypeDTO projectypeToProjectypeDTO(Projectype projectype);

    List<ProjectypeDTO> projectypesToProjectypeDTOs(List<Projectype> projectypes);

    Projectype projectypeDTOToProjectype(ProjectypeDTO projectypeDTO);

    List<Projectype> projectypeDTOsToProjectypes(List<ProjectypeDTO> projectypeDTOs);
}
