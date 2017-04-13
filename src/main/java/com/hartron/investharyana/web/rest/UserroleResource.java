package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.domain.Userrole;
import com.hartron.investharyana.service.UserroleService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing Userrole.
 */
@RestController
@RequestMapping("/api")
public class UserroleResource {

    private final Logger log = LoggerFactory.getLogger(UserroleResource.class);

    private static final String ENTITY_NAME = "userrole";
        
    private final UserroleService userroleService;

    public UserroleResource(UserroleService userroleService) {
        this.userroleService = userroleService;
    }

    /**
     * POST  /userroles : Create a new userrole.
     *
     * @param userrole the userrole to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userrole, or with status 400 (Bad Request) if the userrole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/userroles")
    @Timed
    public ResponseEntity<Userrole> createUserrole(@Valid @RequestBody Userrole userrole) throws URISyntaxException {
        log.debug("REST request to save Userrole : {}", userrole);
        if (userrole.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userrole cannot already have an ID")).body(null);
        }
        Userrole result = userroleService.save(userrole);
        return ResponseEntity.created(new URI("/api/userroles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /userroles : Updates an existing userrole.
     *
     * @param userrole the userrole to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userrole,
     * or with status 400 (Bad Request) if the userrole is not valid,
     * or with status 500 (Internal Server Error) if the userrole couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/userroles")
    @Timed
    public ResponseEntity<Userrole> updateUserrole(@Valid @RequestBody Userrole userrole) throws URISyntaxException {
        log.debug("REST request to update Userrole : {}", userrole);
        if (userrole.getId() == null) {
            return createUserrole(userrole);
        }
        Userrole result = userroleService.save(userrole);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userrole.getId().toString()))
            .body(result);
    }

    /**
     * GET  /userroles : get all the userroles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userroles in body
     */
    @GetMapping("/userroles")
    @Timed
    public List<Userrole> getAllUserroles() {
        log.debug("REST request to get all Userroles");
        return userroleService.findAll();
    }

    /**
     * GET  /userroles/:id : get the "id" userrole.
     *
     * @param id the id of the userrole to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userrole, or with status 404 (Not Found)
     */
    @GetMapping("/userroles/{id}")
    @Timed
    public ResponseEntity<Userrole> getUserrole(@PathVariable String id) {
        log.debug("REST request to get Userrole : {}", id);
        Userrole userrole = userroleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userrole));
    }

    /**
     * DELETE  /userroles/:id : delete the "id" userrole.
     *
     * @param id the id of the userrole to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/userroles/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserrole(@PathVariable String id) {
        log.debug("REST request to delete Userrole : {}", id);
        userroleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
