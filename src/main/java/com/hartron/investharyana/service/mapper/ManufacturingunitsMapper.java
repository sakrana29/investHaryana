package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ManufacturingunitsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Manufacturingunits and its DTO ManufacturingunitsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ManufacturingunitsMapper {

    ManufacturingunitsDTO manufacturingunitsToManufacturingunitsDTO(Manufacturingunits manufacturingunits);

    List<ManufacturingunitsDTO> manufacturingunitsToManufacturingunitsDTOs(List<Manufacturingunits> manufacturingunits);

    Manufacturingunits manufacturingunitsDTOToManufacturingunits(ManufacturingunitsDTO manufacturingunitsDTO);

    List<Manufacturingunits> manufacturingunitsDTOsToManufacturingunits(List<ManufacturingunitsDTO> manufacturingunitsDTOs);
}
