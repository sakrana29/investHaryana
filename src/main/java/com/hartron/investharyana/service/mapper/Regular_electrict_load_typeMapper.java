package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Regular_electrict_load_typeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Regular_electrict_load_type and its DTO Regular_electrict_load_typeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Regular_electrict_load_typeMapper {

    Regular_electrict_load_typeDTO regular_electrict_load_typeToRegular_electrict_load_typeDTO(Regular_electrict_load_type regular_electrict_load_type);

    List<Regular_electrict_load_typeDTO> regular_electrict_load_typesToRegular_electrict_load_typeDTOs(List<Regular_electrict_load_type> regular_electrict_load_types);

    Regular_electrict_load_type regular_electrict_load_typeDTOToRegular_electrict_load_type(Regular_electrict_load_typeDTO regular_electrict_load_typeDTO);

    List<Regular_electrict_load_type> regular_electrict_load_typeDTOsToRegular_electrict_load_types(List<Regular_electrict_load_typeDTO> regular_electrict_load_typeDTOs);
}
