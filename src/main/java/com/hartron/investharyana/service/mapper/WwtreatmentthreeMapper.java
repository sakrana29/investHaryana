package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.WwtreatmentthreeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Wwtreatmentthree and its DTO WwtreatmentthreeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WwtreatmentthreeMapper {

    WwtreatmentthreeDTO wwtreatmentthreeToWwtreatmentthreeDTO(Wwtreatmentthree wwtreatmentthree);

    List<WwtreatmentthreeDTO> wwtreatmentthreesToWwtreatmentthreeDTOs(List<Wwtreatmentthree> wwtreatmentthrees);

    Wwtreatmentthree wwtreatmentthreeDTOToWwtreatmentthree(WwtreatmentthreeDTO wwtreatmentthreeDTO);

    List<Wwtreatmentthree> wwtreatmentthreeDTOsToWwtreatmentthrees(List<WwtreatmentthreeDTO> wwtreatmentthreeDTOs);
}
