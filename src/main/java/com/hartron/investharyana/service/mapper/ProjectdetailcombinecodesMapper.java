package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectdetailcombinecodesDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectdetailcombinecodes and its DTO ProjectdetailcombinecodesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectdetailcombinecodesMapper {

    ProjectdetailcombinecodesDTO projectdetailcombinecodesToProjectdetailcombinecodesDTO(Projectdetailcombinecodes projectdetailcombinecodes);

    List<ProjectdetailcombinecodesDTO> projectdetailcombinecodesToProjectdetailcombinecodesDTOs(List<Projectdetailcombinecodes> projectdetailcombinecodes);

    Projectdetailcombinecodes projectdetailcombinecodesDTOToProjectdetailcombinecodes(ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO);

    List<Projectdetailcombinecodes> projectdetailcombinecodesDTOsToProjectdetailcombinecodes(List<ProjectdetailcombinecodesDTO> projectdetailcombinecodesDTOs);
}
