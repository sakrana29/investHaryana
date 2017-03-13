package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ParticularDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Particular and its DTO ParticularDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ParticularMapper {

    ParticularDTO particularToParticularDTO(Particular particular);

    List<ParticularDTO> particularsToParticularDTOs(List<Particular> particulars);

    Particular particularDTOToParticular(ParticularDTO particularDTO);

    List<Particular> particularDTOsToParticulars(List<ParticularDTO> particularDTOs);
}
