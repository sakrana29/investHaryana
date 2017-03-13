package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Emmision_fuel_typeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Emmision_fuel_type and its DTO Emmision_fuel_typeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Emmision_fuel_typeMapper {

    Emmision_fuel_typeDTO emmision_fuel_typeToEmmision_fuel_typeDTO(Emmision_fuel_type emmision_fuel_type);

    List<Emmision_fuel_typeDTO> emmision_fuel_typesToEmmision_fuel_typeDTOs(List<Emmision_fuel_type> emmision_fuel_types);

    Emmision_fuel_type emmision_fuel_typeDTOToEmmision_fuel_type(Emmision_fuel_typeDTO emmision_fuel_typeDTO);

    List<Emmision_fuel_type> emmision_fuel_typeDTOsToEmmision_fuel_types(List<Emmision_fuel_typeDTO> emmision_fuel_typeDTOs);
}
