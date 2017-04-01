package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.BusinessentitysDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Businessentitys and its DTO BusinessentitysDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BusinessentitysMapper {

    BusinessentitysDTO businessentitysToBusinessentitysDTO(Businessentitys businessentitys);

    List<BusinessentitysDTO> businessentitysToBusinessentitysDTOs(List<Businessentitys> businessentitys);

    Businessentitys businessentitysDTOToBusinessentitys(BusinessentitysDTO businessentitysDTO);

    List<Businessentitys> businessentitysDTOsToBusinessentitys(List<BusinessentitysDTO> businessentitysDTOs);
}
