package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Regular_electrict_load_typeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Regular_electrict_load_typeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Regular_electrict_load_type.
 */
@RestController
@RequestMapping("/api")
public class Regular_electrict_load_typeResource {

    private final Logger log = LoggerFactory.getLogger(Regular_electrict_load_typeResource.class);

    private static final String ENTITY_NAME = "regular_electrict_load_type";
        
    private final Regular_electrict_load_typeService regular_electrict_load_typeService;

    public Regular_electrict_load_typeResource(Regular_electrict_load_typeService regular_electrict_load_typeService) {
        this.regular_electrict_load_typeService = regular_electrict_load_typeService;
    }

    /**
     * POST  /regular-electrict-load-types : Create a new regular_electrict_load_type.
     *
     * @param regular_electrict_load_typeDTO the regular_electrict_load_typeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new regular_electrict_load_typeDTO, or with status 400 (Bad Request) if the regular_electrict_load_type has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/regular-electrict-load-types")
    @Timed
    public ResponseEntity<Regular_electrict_load_typeDTO> createRegular_electrict_load_type(@Valid @RequestBody Regular_electrict_load_typeDTO regular_electrict_load_typeDTO) throws URISyntaxException {
        log.debug("REST request to save Regular_electrict_load_type : {}", regular_electrict_load_typeDTO);
        if (regular_electrict_load_typeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new regular_electrict_load_type cannot already have an ID")).body(null);
        }
        Regular_electrict_load_typeDTO result = regular_electrict_load_typeService.save(regular_electrict_load_typeDTO);
        return ResponseEntity.created(new URI("/api/regular-electrict-load-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /regular-electrict-load-types : Updates an existing regular_electrict_load_type.
     *
     * @param regular_electrict_load_typeDTO the regular_electrict_load_typeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated regular_electrict_load_typeDTO,
     * or with status 400 (Bad Request) if the regular_electrict_load_typeDTO is not valid,
     * or with status 500 (Internal Server Error) if the regular_electrict_load_typeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/regular-electrict-load-types")
    @Timed
    public ResponseEntity<Regular_electrict_load_typeDTO> updateRegular_electrict_load_type(@Valid @RequestBody Regular_electrict_load_typeDTO regular_electrict_load_typeDTO) throws URISyntaxException {
        log.debug("REST request to update Regular_electrict_load_type : {}", regular_electrict_load_typeDTO);
        if (regular_electrict_load_typeDTO.getId() == null) {
            return createRegular_electrict_load_type(regular_electrict_load_typeDTO);
        }
        Regular_electrict_load_typeDTO result = regular_electrict_load_typeService.save(regular_electrict_load_typeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, regular_electrict_load_typeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /regular-electrict-load-types : get all the regular_electrict_load_types.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of regular_electrict_load_types in body
     */
    @GetMapping("/regular-electrict-load-types")
    @Timed
    public List<Regular_electrict_load_typeDTO> getAllRegular_electrict_load_types() {
        log.debug("REST request to get all Regular_electrict_load_types");
        return regular_electrict_load_typeService.findAll();
    }

    /**
     * GET  /regular-electrict-load-types/:id : get the "id" regular_electrict_load_type.
     *
     * @param id the id of the regular_electrict_load_typeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the regular_electrict_load_typeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/regular-electrict-load-types/{id}")
    @Timed
    public ResponseEntity<Regular_electrict_load_typeDTO> getRegular_electrict_load_type(@PathVariable String id) {
        log.debug("REST request to get Regular_electrict_load_type : {}", id);
        Regular_electrict_load_typeDTO regular_electrict_load_typeDTO = regular_electrict_load_typeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(regular_electrict_load_typeDTO));
    }

    /**
     * DELETE  /regular-electrict-load-types/:id : delete the "id" regular_electrict_load_type.
     *
     * @param id the id of the regular_electrict_load_typeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/regular-electrict-load-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteRegular_electrict_load_type(@PathVariable String id) {
        log.debug("REST request to delete Regular_electrict_load_type : {}", id);
        regular_electrict_load_typeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
