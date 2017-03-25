package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectservicedetailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectservicedetail and its DTO ProjectservicedetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectservicedetailMapper {

    ProjectservicedetailDTO projectservicedetailToProjectservicedetailDTO(Projectservicedetail projectservicedetail);

    List<ProjectservicedetailDTO> projectservicedetailsToProjectservicedetailDTOs(List<Projectservicedetail> projectservicedetails);

    Projectservicedetail projectservicedetailDTOToProjectservicedetail(ProjectservicedetailDTO projectservicedetailDTO);

    List<Projectservicedetail> projectservicedetailDTOsToProjectservicedetails(List<ProjectservicedetailDTO> projectservicedetailDTOs);
}
