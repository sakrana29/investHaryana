package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Waste_water_naturetypeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Waste_water_naturetype and its DTO Waste_water_naturetypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Waste_water_naturetypeMapper {

    Waste_water_naturetypeDTO waste_water_naturetypeToWaste_water_naturetypeDTO(Waste_water_naturetype waste_water_naturetype);

    List<Waste_water_naturetypeDTO> waste_water_naturetypesToWaste_water_naturetypeDTOs(List<Waste_water_naturetype> waste_water_naturetypes);

    Waste_water_naturetype waste_water_naturetypeDTOToWaste_water_naturetype(Waste_water_naturetypeDTO waste_water_naturetypeDTO);

    List<Waste_water_naturetype> waste_water_naturetypeDTOsToWaste_water_naturetypes(List<Waste_water_naturetypeDTO> waste_water_naturetypeDTOs);
}
