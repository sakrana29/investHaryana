package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Emmision_pollution_controllDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Emmision_pollution_controll and its DTO Emmision_pollution_controllDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Emmision_pollution_controllMapper {

    Emmision_pollution_controllDTO emmision_pollution_controllToEmmision_pollution_controllDTO(Emmision_pollution_controll emmision_pollution_controll);

    List<Emmision_pollution_controllDTO> emmision_pollution_controllsToEmmision_pollution_controllDTOs(List<Emmision_pollution_controll> emmision_pollution_controlls);

    Emmision_pollution_controll emmision_pollution_controllDTOToEmmision_pollution_controll(Emmision_pollution_controllDTO emmision_pollution_controllDTO);

    List<Emmision_pollution_controll> emmision_pollution_controllDTOsToEmmision_pollution_controlls(List<Emmision_pollution_controllDTO> emmision_pollution_controllDTOs);
}
