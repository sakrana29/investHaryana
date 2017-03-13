package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.DepartmentDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Department and its DTO DepartmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DepartmentMapper {

    DepartmentDTO departmentToDepartmentDTO(Department department);

    List<DepartmentDTO> departmentsToDepartmentDTOs(List<Department> departments);

    Department departmentDTOToDepartment(DepartmentDTO departmentDTO);

    List<Department> departmentDTOsToDepartments(List<DepartmentDTO> departmentDTOs);
}
