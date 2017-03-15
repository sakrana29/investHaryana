package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Modeofdisposalfor_dischargeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Modeofdisposalfor_discharge and its DTO Modeofdisposalfor_dischargeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Modeofdisposalfor_dischargeMapper {

    Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeToModeofdisposalfor_dischargeDTO(Modeofdisposalfor_discharge modeofdisposalfor_discharge);

    List<Modeofdisposalfor_dischargeDTO> modeofdisposalfor_dischargesToModeofdisposalfor_dischargeDTOs(List<Modeofdisposalfor_discharge> modeofdisposalfor_discharges);

    Modeofdisposalfor_discharge modeofdisposalfor_dischargeDTOToModeofdisposalfor_discharge(Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO);

    List<Modeofdisposalfor_discharge> modeofdisposalfor_dischargeDTOsToModeofdisposalfor_discharges(List<Modeofdisposalfor_dischargeDTO> modeofdisposalfor_dischargeDTOs);
}
