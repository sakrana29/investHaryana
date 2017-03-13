package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ServiceFormFieldDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ServiceFormField and its DTO ServiceFormFieldDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServiceFormFieldMapper {

    ServiceFormFieldDTO serviceFormFieldToServiceFormFieldDTO(ServiceFormField serviceFormField);

    List<ServiceFormFieldDTO> serviceFormFieldsToServiceFormFieldDTOs(List<ServiceFormField> serviceFormFields);

    ServiceFormField serviceFormFieldDTOToServiceFormField(ServiceFormFieldDTO serviceFormFieldDTO);

    List<ServiceFormField> serviceFormFieldDTOsToServiceFormFields(List<ServiceFormFieldDTO> serviceFormFieldDTOs);
}
