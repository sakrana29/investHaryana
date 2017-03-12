package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.DepartmentServiceDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity DepartmentService and its DTO DepartmentServiceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DepartmentServiceMapper {

    DepartmentServiceDTO departmentServiceToDepartmentServiceDTO(DepartmentService departmentService);

    List<DepartmentServiceDTO> departmentServicesToDepartmentServiceDTOs(List<DepartmentService> departmentServices);

    DepartmentService departmentServiceDTOToDepartmentService(DepartmentServiceDTO departmentServiceDTO);

    List<DepartmentService> departmentServiceDTOsToDepartmentServices(List<DepartmentServiceDTO> departmentServiceDTOs);
}
