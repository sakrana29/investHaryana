package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Term_declaration_acceptDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Term_declaration_accept and its DTO Term_declaration_acceptDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Term_declaration_acceptMapper {

    Term_declaration_acceptDTO term_declaration_acceptToTerm_declaration_acceptDTO(Term_declaration_accept term_declaration_accept);

    List<Term_declaration_acceptDTO> term_declaration_acceptsToTerm_declaration_acceptDTOs(List<Term_declaration_accept> term_declaration_accepts);

    Term_declaration_accept term_declaration_acceptDTOToTerm_declaration_accept(Term_declaration_acceptDTO term_declaration_acceptDTO);

    List<Term_declaration_accept> term_declaration_acceptDTOsToTerm_declaration_accepts(List<Term_declaration_acceptDTO> term_declaration_acceptDTOs);
}
