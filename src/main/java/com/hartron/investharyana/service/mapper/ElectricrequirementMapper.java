package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ElectricrequirementDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Electricrequirement and its DTO ElectricrequirementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ElectricrequirementMapper {

    ElectricrequirementDTO electricrequirementToElectricrequirementDTO(Electricrequirement electricrequirement);

    List<ElectricrequirementDTO> electricrequirementsToElectricrequirementDTOs(List<Electricrequirement> electricrequirements);

    Electricrequirement electricrequirementDTOToElectricrequirement(ElectricrequirementDTO electricrequirementDTO);

    List<Electricrequirement> electricrequirementDTOsToElectricrequirements(List<ElectricrequirementDTO> electricrequirementDTOs);
}
