package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectserviceformfielddataDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectserviceformfielddata and its DTO ProjectserviceformfielddataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectserviceformfielddataMapper {

    ProjectserviceformfielddataDTO projectserviceformfielddataToProjectserviceformfielddataDTO(Projectserviceformfielddata projectserviceformfielddata);

    List<ProjectserviceformfielddataDTO> projectserviceformfielddataToProjectserviceformfielddataDTOs(List<Projectserviceformfielddata> projectserviceformfielddata);

    Projectserviceformfielddata projectserviceformfielddataDTOToProjectserviceformfielddata(ProjectserviceformfielddataDTO projectserviceformfielddataDTO);

    List<Projectserviceformfielddata> projectserviceformfielddataDTOsToProjectserviceformfielddata(List<ProjectserviceformfielddataDTO> projectserviceformfielddataDTOs);
}
