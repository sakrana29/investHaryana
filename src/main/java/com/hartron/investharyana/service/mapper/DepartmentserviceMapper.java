package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.DepartmentserviceDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Departmentservice and its DTO DepartmentserviceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DepartmentserviceMapper {

    DepartmentserviceDTO departmentserviceToDepartmentserviceDTO(Departmentservice departmentservice);

    List<DepartmentserviceDTO> departmentservicesToDepartmentserviceDTOs(List<Departmentservice> departmentservices);

    Departmentservice departmentserviceDTOToDepartmentservice(DepartmentserviceDTO departmentserviceDTO);

    List<Departmentservice> departmentserviceDTOsToDepartmentservices(List<DepartmentserviceDTO> departmentserviceDTOs);
}
