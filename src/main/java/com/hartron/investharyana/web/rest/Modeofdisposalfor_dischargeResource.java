package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Modeofdisposalfor_dischargeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Modeofdisposalfor_dischargeDTO;
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
 * REST controller for managing Modeofdisposalfor_discharge.
 */
@RestController
@RequestMapping("/api")
public class Modeofdisposalfor_dischargeResource {

    private final Logger log = LoggerFactory.getLogger(Modeofdisposalfor_dischargeResource.class);

    private static final String ENTITY_NAME = "modeofdisposalfor_discharge";
        
    private final Modeofdisposalfor_dischargeService modeofdisposalfor_dischargeService;

    public Modeofdisposalfor_dischargeResource(Modeofdisposalfor_dischargeService modeofdisposalfor_dischargeService) {
        this.modeofdisposalfor_dischargeService = modeofdisposalfor_dischargeService;
    }

    /**
     * POST  /modeofdisposalfor-discharges : Create a new modeofdisposalfor_discharge.
     *
     * @param modeofdisposalfor_dischargeDTO the modeofdisposalfor_dischargeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new modeofdisposalfor_dischargeDTO, or with status 400 (Bad Request) if the modeofdisposalfor_discharge has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/modeofdisposalfor-discharges")
    @Timed
    public ResponseEntity<Modeofdisposalfor_dischargeDTO> createModeofdisposalfor_discharge(@Valid @RequestBody Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO) throws URISyntaxException {
        log.debug("REST request to save Modeofdisposalfor_discharge : {}", modeofdisposalfor_dischargeDTO);
        if (modeofdisposalfor_dischargeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new modeofdisposalfor_discharge cannot already have an ID")).body(null);
        }
        Modeofdisposalfor_dischargeDTO result = modeofdisposalfor_dischargeService.save(modeofdisposalfor_dischargeDTO);
        return ResponseEntity.created(new URI("/api/modeofdisposalfor-discharges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /modeofdisposalfor-discharges : Updates an existing modeofdisposalfor_discharge.
     *
     * @param modeofdisposalfor_dischargeDTO the modeofdisposalfor_dischargeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated modeofdisposalfor_dischargeDTO,
     * or with status 400 (Bad Request) if the modeofdisposalfor_dischargeDTO is not valid,
     * or with status 500 (Internal Server Error) if the modeofdisposalfor_dischargeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/modeofdisposalfor-discharges")
    @Timed
    public ResponseEntity<Modeofdisposalfor_dischargeDTO> updateModeofdisposalfor_discharge(@Valid @RequestBody Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO) throws URISyntaxException {
        log.debug("REST request to update Modeofdisposalfor_discharge : {}", modeofdisposalfor_dischargeDTO);
        if (modeofdisposalfor_dischargeDTO.getId() == null) {
            return createModeofdisposalfor_discharge(modeofdisposalfor_dischargeDTO);
        }
        Modeofdisposalfor_dischargeDTO result = modeofdisposalfor_dischargeService.save(modeofdisposalfor_dischargeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, modeofdisposalfor_dischargeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /modeofdisposalfor-discharges : get all the modeofdisposalfor_discharges.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of modeofdisposalfor_discharges in body
     */
    @GetMapping("/modeofdisposalfor-discharges")
    @Timed
    public List<Modeofdisposalfor_dischargeDTO> getAllModeofdisposalfor_discharges() {
        log.debug("REST request to get all Modeofdisposalfor_discharges");
        return modeofdisposalfor_dischargeService.findAll();
    }

    /**
     * GET  /modeofdisposalfor-discharges/:id : get the "id" modeofdisposalfor_discharge.
     *
     * @param id the id of the modeofdisposalfor_dischargeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the modeofdisposalfor_dischargeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/modeofdisposalfor-discharges/{id}")
    @Timed
    public ResponseEntity<Modeofdisposalfor_dischargeDTO> getModeofdisposalfor_discharge(@PathVariable String id) {
        log.debug("REST request to get Modeofdisposalfor_discharge : {}", id);
        Modeofdisposalfor_dischargeDTO modeofdisposalfor_dischargeDTO = modeofdisposalfor_dischargeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(modeofdisposalfor_dischargeDTO));
    }

    /**
     * DELETE  /modeofdisposalfor-discharges/:id : delete the "id" modeofdisposalfor_discharge.
     *
     * @param id the id of the modeofdisposalfor_dischargeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/modeofdisposalfor-discharges/{id}")
    @Timed
    public ResponseEntity<Void> deleteModeofdisposalfor_discharge(@PathVariable String id) {
        log.debug("REST request to delete Modeofdisposalfor_discharge : {}", id);
        modeofdisposalfor_dischargeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
