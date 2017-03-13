package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.WastewaterdetailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Wastewaterdetail and its DTO WastewaterdetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WastewaterdetailMapper {

    WastewaterdetailDTO wastewaterdetailToWastewaterdetailDTO(Wastewaterdetail wastewaterdetail);

    List<WastewaterdetailDTO> wastewaterdetailsToWastewaterdetailDTOs(List<Wastewaterdetail> wastewaterdetails);

    Wastewaterdetail wastewaterdetailDTOToWastewaterdetail(WastewaterdetailDTO wastewaterdetailDTO);

    List<Wastewaterdetail> wastewaterdetailDTOsToWastewaterdetails(List<WastewaterdetailDTO> wastewaterdetailDTOs);
}
