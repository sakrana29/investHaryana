package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ManufacturingdetailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Manufacturingdetail and its DTO ManufacturingdetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ManufacturingdetailMapper {

    ManufacturingdetailDTO manufacturingdetailToManufacturingdetailDTO(Manufacturingdetail manufacturingdetail);

    List<ManufacturingdetailDTO> manufacturingdetailsToManufacturingdetailDTOs(List<Manufacturingdetail> manufacturingdetails);

    Manufacturingdetail manufacturingdetailDTOToManufacturingdetail(ManufacturingdetailDTO manufacturingdetailDTO);

    List<Manufacturingdetail> manufacturingdetailDTOsToManufacturingdetails(List<ManufacturingdetailDTO> manufacturingdetailDTOs);
}
