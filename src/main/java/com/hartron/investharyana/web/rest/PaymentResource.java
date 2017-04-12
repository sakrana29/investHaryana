package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.BlockService;
import com.hartron.investharyana.service.dto.BlockDTO;
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

/**
 * REST controller for managing Block.
 */
@RestController
@RequestMapping("/api")
public class PaymentResource {

    private final Logger log = LoggerFactory.getLogger(PaymentResource.class);



}
