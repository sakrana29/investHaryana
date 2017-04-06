package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.FiletestingService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.FiletestingDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Filetesting.
 */
@RestController
@RequestMapping("/api")
public class FiletestingResource {

    private final Logger log = LoggerFactory.getLogger(FiletestingResource.class);

    private static final String ENTITY_NAME = "filetesting";
        
    private final FiletestingService filetestingService;

    public FiletestingResource(FiletestingService filetestingService) {
        this.filetestingService = filetestingService;
    }

    /**
     * POST  /filetestings : Create a new filetesting.
     *
     * @param filetestingDTO the filetestingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new filetestingDTO, or with status 400 (Bad Request) if the filetesting has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/filetestings")
    @Timed
    public ResponseEntity<FiletestingDTO> createFiletesting(@RequestBody FiletestingDTO filetestingDTO) throws URISyntaxException {
        log.debug("REST request to save Filetesting : {}", filetestingDTO);
        if (filetestingDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new filetesting cannot already have an ID")).body(null);
        }
        FiletestingDTO result = filetestingService.save(filetestingDTO);
        return ResponseEntity.created(new URI("/api/filetestings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /filetestings : Updates an existing filetesting.
     *
     * @param filetestingDTO the filetestingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated filetestingDTO,
     * or with status 400 (Bad Request) if the filetestingDTO is not valid,
     * or with status 500 (Internal Server Error) if the filetestingDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/filetestings")
    @Timed
    public ResponseEntity<FiletestingDTO> updateFiletesting(@RequestBody FiletestingDTO filetestingDTO) throws URISyntaxException {
        log.debug("REST request to update Filetesting : {}", filetestingDTO);
        if (filetestingDTO.getId() == null) {
            return createFiletesting(filetestingDTO);
        }
        FiletestingDTO result = filetestingService.save(filetestingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, filetestingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /filetestings : get all the filetestings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of filetestings in body
     */
    @GetMapping("/filetestings")
    @Timed
    public List<FiletestingDTO> getAllFiletestings() {
        log.debug("REST request to get all Filetestings");
        return filetestingService.findAll();
    }

    /**
     * GET  /filetestings/:id : get the "id" filetesting.
     *
     * @param id the id of the filetestingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the filetestingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/filetestings/{id}")
    @Timed
    public ResponseEntity<FiletestingDTO> getFiletesting(@PathVariable String id) {
        log.debug("REST request to get Filetesting : {}", id);
        FiletestingDTO filetestingDTO = filetestingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(filetestingDTO));
    }

    /**
     * DELETE  /filetestings/:id : delete the "id" filetesting.
     *
     * @param id the id of the filetestingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/filetestings/{id}")
    @Timed
    public ResponseEntity<Void> deleteFiletesting(@PathVariable String id) {
        log.debug("REST request to delete Filetesting : {}", id);
        filetestingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
