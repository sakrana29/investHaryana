package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.UserroleService;
import com.hartron.investharyana.domain.Userrole;
import com.hartron.investharyana.repository.UserroleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service Implementation for managing Userrole.
 */
@Service
public class UserroleServiceImpl implements UserroleService{

    private final Logger log = LoggerFactory.getLogger(UserroleServiceImpl.class);
    
    private final UserroleRepository userroleRepository;

    public UserroleServiceImpl(UserroleRepository userroleRepository) {
        this.userroleRepository = userroleRepository;
    }

    /**
     * Save a userrole.
     *
     * @param userrole the entity to save
     * @return the persisted entity
     */
    @Override
    public Userrole save(Userrole userrole) {
        log.debug("Request to save Userrole : {}", userrole);
        Userrole result = userroleRepository.save(userrole);
        return result;
    }

    /**
     *  Get all the userroles.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Userrole> findAll() {
        log.debug("Request to get all Userroles");
        List<Userrole> result = userroleRepository.findAll();

        return result;
    }

    /**
     *  Get one userrole by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Userrole findOne(String id) {
        log.debug("Request to get Userrole : {}", id);
        Userrole userrole = userroleRepository.findOne(UUID.fromString(id));
        return userrole;
    }

    /**
     *  Delete the  userrole by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Userrole : {}", id);
        userroleRepository.delete(UUID.fromString(id));
    }
}
