package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ApprovalformsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Approvalforms and its DTO ApprovalformsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApprovalformsMapper {

    ApprovalformsDTO approvalformsToApprovalformsDTO(Approvalforms approvalforms);

    List<ApprovalformsDTO> approvalformsToApprovalformsDTOs(List<Approvalforms> approvalforms);

    Approvalforms approvalformsDTOToApprovalforms(ApprovalformsDTO approvalformsDTO);

    List<Approvalforms> approvalformsDTOsToApprovalforms(List<ApprovalformsDTO> approvalformsDTOs);
}
