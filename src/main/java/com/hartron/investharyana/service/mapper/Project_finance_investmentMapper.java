package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Project_finance_investmentDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Project_finance_investment and its DTO Project_finance_investmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Project_finance_investmentMapper {

    Project_finance_investmentDTO project_finance_investmentToProject_finance_investmentDTO(Project_finance_investment project_finance_investment);

    List<Project_finance_investmentDTO> project_finance_investmentsToProject_finance_investmentDTOs(List<Project_finance_investment> project_finance_investments);

    Project_finance_investment project_finance_investmentDTOToProject_finance_investment(Project_finance_investmentDTO project_finance_investmentDTO);

    List<Project_finance_investment> project_finance_investmentDTOsToProject_finance_investments(List<Project_finance_investmentDTO> project_finance_investmentDTOs);
}
