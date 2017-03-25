package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.WwtreatmentoneDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Wwtreatmentone and its DTO WwtreatmentoneDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WwtreatmentoneMapper {

    WwtreatmentoneDTO wwtreatmentoneToWwtreatmentoneDTO(Wwtreatmentone wwtreatmentone);

    List<WwtreatmentoneDTO> wwtreatmentonesToWwtreatmentoneDTOs(List<Wwtreatmentone> wwtreatmentones);

    Wwtreatmentone wwtreatmentoneDTOToWwtreatmentone(WwtreatmentoneDTO wwtreatmentoneDTO);

    List<Wwtreatmentone> wwtreatmentoneDTOsToWwtreatmentones(List<WwtreatmentoneDTO> wwtreatmentoneDTOs);
}
