package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Project_phaseDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Project_phase and its DTO Project_phaseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Project_phaseMapper {

    Project_phaseDTO project_phaseToProject_phaseDTO(Project_phase project_phase);

    List<Project_phaseDTO> project_phasesToProject_phaseDTOs(List<Project_phase> project_phases);

    Project_phase project_phaseDTOToProject_phase(Project_phaseDTO project_phaseDTO);

    List<Project_phase> project_phaseDTOsToProject_phases(List<Project_phaseDTO> project_phaseDTOs);
}
