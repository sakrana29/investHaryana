package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Term_declaration_acceptService;
import com.hartron.investharyana.domain.Term_declaration_accept;
import com.hartron.investharyana.repository.Term_declaration_acceptRepository;
import com.hartron.investharyana.service.dto.Term_declaration_acceptDTO;
import com.hartron.investharyana.service.mapper.Term_declaration_acceptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Term_declaration_accept.
 */
@Service
public class Term_declaration_acceptServiceImpl implements Term_declaration_acceptService{

    private final Logger log = LoggerFactory.getLogger(Term_declaration_acceptServiceImpl.class);
    
    private final Term_declaration_acceptRepository term_declaration_acceptRepository;

    private final Term_declaration_acceptMapper term_declaration_acceptMapper;

    public Term_declaration_acceptServiceImpl(Term_declaration_acceptRepository term_declaration_acceptRepository, Term_declaration_acceptMapper term_declaration_acceptMapper) {
        this.term_declaration_acceptRepository = term_declaration_acceptRepository;
        this.term_declaration_acceptMapper = term_declaration_acceptMapper;
    }

    /**
     * Save a term_declaration_accept.
     *
     * @param term_declaration_acceptDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Term_declaration_acceptDTO save(Term_declaration_acceptDTO term_declaration_acceptDTO) {
        log.debug("Request to save Term_declaration_accept : {}", term_declaration_acceptDTO);
        Term_declaration_accept term_declaration_accept = term_declaration_acceptMapper.term_declaration_acceptDTOToTerm_declaration_accept(term_declaration_acceptDTO);
        term_declaration_accept = term_declaration_acceptRepository.save(term_declaration_accept);
        Term_declaration_acceptDTO result = term_declaration_acceptMapper.term_declaration_acceptToTerm_declaration_acceptDTO(term_declaration_accept);
        return result;
    }

    /**
     *  Get all the term_declaration_accepts.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Term_declaration_acceptDTO> findAll() {
        log.debug("Request to get all Term_declaration_accepts");
        List<Term_declaration_acceptDTO> result = term_declaration_acceptRepository.findAll().stream()
            .map(term_declaration_acceptMapper::term_declaration_acceptToTerm_declaration_acceptDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one term_declaration_accept by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Term_declaration_acceptDTO findOne(String id) {
        log.debug("Request to get Term_declaration_accept : {}", id);
        Term_declaration_accept term_declaration_accept = term_declaration_acceptRepository.findOne(UUID.fromString(id));
        Term_declaration_acceptDTO term_declaration_acceptDTO = term_declaration_acceptMapper.term_declaration_acceptToTerm_declaration_acceptDTO(term_declaration_accept);
        return term_declaration_acceptDTO;
    }

    /**
     *  Delete the  term_declaration_accept by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Term_declaration_accept : {}", id);
        term_declaration_acceptRepository.delete(UUID.fromString(id));
    }
}
