package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Term_declaration_acceptDTO;
import java.util.List;

/**
 * Service Interface for managing Term_declaration_accept.
 */
public interface Term_declaration_acceptService {

    /**
     * Save a term_declaration_accept.
     *
     * @param term_declaration_acceptDTO the entity to save
     * @return the persisted entity
     */
    Term_declaration_acceptDTO save(Term_declaration_acceptDTO term_declaration_acceptDTO);

    /**
     *  Get all the term_declaration_accepts.
     *  
     *  @return the list of entities
     */
    List<Term_declaration_acceptDTO> findAll();

    /**
     *  Get the "id" term_declaration_accept.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Term_declaration_acceptDTO findOne(String id);

    /**
     *  Delete the "id" term_declaration_accept.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
