package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.City_town_villageService;
import com.hartron.investharyana.service.CountryService;
import com.hartron.investharyana.service.InvestorService;
import com.hartron.investharyana.service.StateService;
import com.hartron.investharyana.service.dto.*;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Investor.
 */
@RestController
@RequestMapping("/api")
public class InvestorDataResource {

    private final Logger log = LoggerFactory.getLogger(InvestorDataResource.class);

//    private static final String ENTITY_NAME = "investor";

    private final InvestorService investorService;
    private final CountryService countryService;
    private final StateService stateService;
    private final City_town_villageService city_town_villageService;



    public InvestorDataResource(InvestorService investorService, CountryService countryService, StateService stateService, City_town_villageService city_town_villageService) {
        this.investorService = investorService;
        this.countryService = countryService;
        this.stateService = stateService;
        this.city_town_villageService = city_town_villageService;
    }

//    /**
//     * POST  /investors : Create a new investor.
//     *
//     * @param investorDTO the investorDTO to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new investorDTO, or with status 400 (Bad Request) if the investor has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/investors")
//    @Timed
//    public ResponseEntity<InvestorDTO> createInvestor(@RequestBody InvestorDTO investorDTO) throws URISyntaxException {
//        log.debug("REST request to save Investor : {}", investorDTO);
//        if (investorDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new investor cannot already have an ID")).body(null);
//        }
//        InvestorDTO result = investorService.save(investorDTO);
//        return ResponseEntity.created(new URI("/api/investors/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

//    /**
//     * PUT  /investors : Updates an existing investor.
//     *
//     * @param investorDTO the investorDTO to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated investorDTO,
//     * or with status 400 (Bad Request) if the investorDTO is not valid,
//     * or with status 500 (Internal Server Error) if the investorDTO couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/investors")
//    @Timed
//    public ResponseEntity<InvestorDTO> updateInvestor(@RequestBody InvestorDTO investorDTO) throws URISyntaxException {
//        log.debug("REST request to update Investor : {}", investorDTO);
//        if (investorDTO.getId() == null) {
//            return createInvestor(investorDTO);
//        }
//        InvestorDTO result = investorService.save(investorDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, investorDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /investors : get all the investors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of investors in body
     */
    @GetMapping("/investorsdata")
    @Timed
    public List<InvestorDataDTO> getAllInvestorsData() {
        log.debug("REST request to get all Investors");
        List<InvestorDTO> investorDTOList= investorService.findAll();
        List<InvestorDataDTO> investorDataDTOList= new ArrayList<>();

        for(int i=0;i<investorDTOList.size();i++)
        {
            InvestorDataDTO investorDataDTO= new InvestorDataDTO();
            InvestorDTO investorDTO = investorDTOList.get(i);

            CountryDTO countryDTO= countryService.findOne(investorDTO.getCountryid().toString());
            StateDTO stateDTO = stateService.findOne(investorDTO.getStateid().toString());
            City_town_villageDTO city_town_villageDTO = city_town_villageService.findOne(investorDTO.getCityid().toString());

            generateInvstorDataDTO(investorDTO, investorDataDTO, countryDTO, stateDTO, city_town_villageDTO);

            investorDataDTOList.add(investorDataDTO);
        }
        return investorDataDTOList;
    }

    @GetMapping("/investorsdata/{id}")
    @Timed
    public ResponseEntity<InvestorDataDTO> getOneInvestorsData(@PathVariable String id) {
        log.debug("REST request to get all Investors");
        InvestorDTO investorDTO= investorService.findOne(id);
        InvestorDataDTO investorDataDTO= new InvestorDataDTO();

        CountryDTO countryDTO= countryService.findOne(investorDTO.getCountryid().toString());
        StateDTO stateDTO = stateService.findOne(investorDTO.getStateid().toString());
        City_town_villageDTO city_town_villageDTO = city_town_villageService.findOne(investorDTO.getCityid().toString());

        generateInvstorDataDTO(investorDTO, investorDataDTO, countryDTO, stateDTO, city_town_villageDTO);

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(investorDataDTO));
    }

    private void generateInvstorDataDTO(InvestorDTO investorDTO, InvestorDataDTO investorDataDTO, CountryDTO countryDTO, StateDTO stateDTO, City_town_villageDTO city_town_villageDTO) {
        investorDataDTO.setId(investorDTO.getId());
        investorDataDTO.setAddress1(investorDTO.getAddress1());
        investorDataDTO.setAddress2(investorDTO.getAddress2());
        investorDataDTO.setAddress3(investorDTO.getAddress3());
        investorDataDTO.setCityid(investorDTO.getCityid());
        investorDataDTO.setCityname(city_town_villageDTO.getCity_town_village_name());
        investorDataDTO.setCountryid(investorDTO.getCountryid());
        investorDataDTO.setCountryname(countryDTO.getCountryname());
        investorDataDTO.setEmailprimary(investorDTO.getEmailprimary());
        investorDataDTO.setEmailsecondary(investorDTO.getEmailsecondary());
        investorDataDTO.setFirstname(investorDTO.getFirstname());
        investorDataDTO.setInvestorpicpath(investorDTO.getInvestorpicpath());
        investorDataDTO.setLastname(investorDTO.getLastname());
        investorDataDTO.setMiddlename(investorDTO.getMiddlename());
        investorDataDTO.setMouapplicable(investorDTO.getMouapplicable());
        investorDataDTO.setMoudocument(investorDTO.getMoudocument());
        investorDataDTO.setMouidnumber(investorDTO.getMouidnumber());
        investorDataDTO.setMousignyear(investorDTO.getMousignyear());
        investorDataDTO.setStateid(investorDTO.getStateid());
        investorDataDTO.setStatename(stateDTO.getStatename());
        investorDataDTO.setUserlogin(investorDTO.getUserlogin());
    }

//    @GetMapping("/investors/ByUser")
//    @Timed
//    public List<InvestorDTO> getAllInvestorsByUser() {
//        log.debug("REST request to get all Investors By User")
//        return investorService.findAllInvestorByUserLogin();
//    }

//    /**
//     * GET  /investors/:id : get the "id" investor.
//     *
//     * @param id the id of the investorDTO to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the investorDTO, or with status 404 (Not Found)
//     */
//    @GetMapping("/investors/{id}")
//    @Timed
//    public ResponseEntity<InvestorDTO> getInvestor(@PathVariable String id) {
//        log.debug("REST request to get Investor : {}", id);
//        InvestorDTO investorDTO = investorService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(investorDTO));
//    }
//
//    /**
//     * DELETE  /investors/:id : delete the "id" investor.
//     *
//     * @param id the id of the investorDTO to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/investors/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteInvestor(@PathVariable String id) {
//        log.debug("REST request to delete Investor : {}", id);
//        investorService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
}
