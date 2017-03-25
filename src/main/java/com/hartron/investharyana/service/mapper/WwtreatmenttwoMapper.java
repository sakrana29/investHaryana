package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.WwtreatmenttwoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Wwtreatmenttwo and its DTO WwtreatmenttwoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WwtreatmenttwoMapper {

    WwtreatmenttwoDTO wwtreatmenttwoToWwtreatmenttwoDTO(Wwtreatmenttwo wwtreatmenttwo);

    List<WwtreatmenttwoDTO> wwtreatmenttwosToWwtreatmenttwoDTOs(List<Wwtreatmenttwo> wwtreatmenttwos);

    Wwtreatmenttwo wwtreatmenttwoDTOToWwtreatmenttwo(WwtreatmenttwoDTO wwtreatmenttwoDTO);

    List<Wwtreatmenttwo> wwtreatmenttwoDTOsToWwtreatmenttwos(List<WwtreatmenttwoDTO> wwtreatmenttwoDTOs);
}
