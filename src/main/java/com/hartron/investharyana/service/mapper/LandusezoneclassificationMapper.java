package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.LandusezoneclassificationDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Landusezoneclassification and its DTO LandusezoneclassificationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LandusezoneclassificationMapper {

    LandusezoneclassificationDTO landusezoneclassificationToLandusezoneclassificationDTO(Landusezoneclassification landusezoneclassification);

    List<LandusezoneclassificationDTO> landusezoneclassificationsToLandusezoneclassificationDTOs(List<Landusezoneclassification> landusezoneclassifications);

    Landusezoneclassification landusezoneclassificationDTOToLandusezoneclassification(LandusezoneclassificationDTO landusezoneclassificationDTO);

    List<Landusezoneclassification> landusezoneclassificationDTOsToLandusezoneclassifications(List<LandusezoneclassificationDTO> landusezoneclassificationDTOs);
}
