package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Environment_impactdetailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Environment_impactdetail and its DTO Environment_impactdetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Environment_impactdetailMapper {

    Environment_impactdetailDTO environment_impactdetailToEnvironment_impactdetailDTO(Environment_impactdetail environment_impactdetail);

    List<Environment_impactdetailDTO> environment_impactdetailsToEnvironment_impactdetailDTOs(List<Environment_impactdetail> environment_impactdetails);

    Environment_impactdetail environment_impactdetailDTOToEnvironment_impactdetail(Environment_impactdetailDTO environment_impactdetailDTO);

    List<Environment_impactdetail> environment_impactdetailDTOsToEnvironment_impactdetails(List<Environment_impactdetailDTO> environment_impactdetailDTOs);
}
