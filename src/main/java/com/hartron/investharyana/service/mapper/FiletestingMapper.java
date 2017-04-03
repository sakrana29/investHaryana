package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.FiletestingDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Filetesting and its DTO FiletestingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FiletestingMapper {

    FiletestingDTO filetestingToFiletestingDTO(Filetesting filetesting);

    List<FiletestingDTO> filetestingsToFiletestingDTOs(List<Filetesting> filetestings);

    Filetesting filetestingDTOToFiletesting(FiletestingDTO filetestingDTO);

    List<Filetesting> filetestingDTOsToFiletestings(List<FiletestingDTO> filetestingDTOs);
}
