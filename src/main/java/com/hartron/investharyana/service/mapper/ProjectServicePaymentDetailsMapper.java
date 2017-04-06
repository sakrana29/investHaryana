package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectServicePaymentDetailsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProjectServicePaymentDetails and its DTO ProjectServicePaymentDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectServicePaymentDetailsMapper {

    ProjectServicePaymentDetailsDTO projectServicePaymentDetailsToProjectServicePaymentDetailsDTO(ProjectServicePaymentDetails projectServicePaymentDetails);

    List<ProjectServicePaymentDetailsDTO> projectServicePaymentDetailsToProjectServicePaymentDetailsDTOs(List<ProjectServicePaymentDetails> projectServicePaymentDetails);

    ProjectServicePaymentDetails projectServicePaymentDetailsDTOToProjectServicePaymentDetails(ProjectServicePaymentDetailsDTO projectServicePaymentDetailsDTO);

    List<ProjectServicePaymentDetails> projectServicePaymentDetailsDTOsToProjectServicePaymentDetails(List<ProjectServicePaymentDetailsDTO> projectServicePaymentDetailsDTOs);
}
