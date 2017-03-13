package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.WatersupplysourceDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Watersupplysource and its DTO WatersupplysourceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WatersupplysourceMapper {

    WatersupplysourceDTO watersupplysourceToWatersupplysourceDTO(Watersupplysource watersupplysource);

    List<WatersupplysourceDTO> watersupplysourcesToWatersupplysourceDTOs(List<Watersupplysource> watersupplysources);

    Watersupplysource watersupplysourceDTOToWatersupplysource(WatersupplysourceDTO watersupplysourceDTO);

    List<Watersupplysource> watersupplysourceDTOsToWatersupplysources(List<WatersupplysourceDTO> watersupplysourceDTOs);
}
