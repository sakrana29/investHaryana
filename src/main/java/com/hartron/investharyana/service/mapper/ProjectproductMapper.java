package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectproductDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectproduct and its DTO ProjectproductDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectproductMapper {

    ProjectproductDTO projectproductToProjectproductDTO(Projectproduct projectproduct);

    List<ProjectproductDTO> projectproductsToProjectproductDTOs(List<Projectproduct> projectproducts);

    Projectproduct projectproductDTOToProjectproduct(ProjectproductDTO projectproductDTO);

    List<Projectproduct> projectproductDTOsToProjectproducts(List<ProjectproductDTO> projectproductDTOs);
}
