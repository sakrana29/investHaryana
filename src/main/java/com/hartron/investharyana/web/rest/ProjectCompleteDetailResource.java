package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.service.*;
import com.hartron.investharyana.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Block.
 */
@RestController
@RequestMapping("/api")
public class ProjectCompleteDetailResource {

    private final Logger log = LoggerFactory.getLogger(ProjectCompleteDetailResource.class);

//    private static final String ENTITY_NAME = "block";


    private final InvestorService investorService;
    private final CompanydetailService companydetailService;
    private final ProjectdetailService projectdetailService;
    private final ProjectsitedetailService projectsitedetailService;
    private final Project_finance_investmentService project_finance_investmentService;
    private final ManufacturingdetailService manufacturingdetailService;
    private final ElectricrequirementService electricrequirementService;
    private final ProjectdetailcombinecodesService projectdetailcombinecodesService;

    public ProjectCompleteDetailResource(InvestorService investorService, CompanydetailService companydetailService, ProjectdetailService projectdetailService, ProjectsitedetailService projectsitedetailService, Project_finance_investmentService project_finance_investmentService, ManufacturingdetailService manufacturingdetailService, ElectricrequirementService electricrequirementService, ProjectdetailcombinecodesService projectdetailcombinecodesService) {
        this.investorService = investorService;
        this.companydetailService = companydetailService;
        this.projectdetailService = projectdetailService;
        this.projectsitedetailService = projectsitedetailService;
        this.project_finance_investmentService = project_finance_investmentService;
        this.manufacturingdetailService = manufacturingdetailService;
        this.electricrequirementService = electricrequirementService;
        this.projectdetailcombinecodesService = projectdetailcombinecodesService;
    }

    @GetMapping("/CompleteProjectDetail")
    public List<ProjectCompleteDetailDTO> getOneProjectCompleteDetail() {
        log.debug("REST request to get one project complete detail");
//        String projectid= "d383d243-da16-4558-9e7b-7217c1259ea3";

        List<ProjectCompleteDetailDTO> completeprojectdtolist=new ArrayList<>();

//        ProjectCompleteDetailDTO completeprojectdto=new ProjectCompleteDetailDTO();

        List<ProjectdetailcombinecodesDTO> projectdetailcombinecodesDTOlist=projectdetailcombinecodesService.findAll();

        for(int i=0; i < projectdetailcombinecodesDTOlist.size(); i++) {

            ProjectCompleteDetailDTO completeprojectdto=new ProjectCompleteDetailDTO();
            completeprojectdto.setProjectdetailDTO(projectdetailService.findOne(projectdetailcombinecodesDTOlist.get(i).getId().toString()));
            completeprojectdto.setInvestorDTO(investorService.findOne(projectdetailcombinecodesDTOlist.get(i).getInvestorid().toString()));
            completeprojectdto.setCompanydetailDTO(companydetailService.findOne(projectdetailcombinecodesDTOlist.get(i).getCompanydetailid().toString()));
            completeprojectdto.setProjectsitedetailDTO(projectsitedetailService.findOne(projectdetailcombinecodesDTOlist.get(i).getProjectsitedetailid().toString()));
            completeprojectdto.setProject_finance_investmentDTO(project_finance_investmentService.findOne(projectdetailcombinecodesDTOlist.get(i).getProjectfinanceid().toString()));
            completeprojectdto.setManufacturingdetailDTO(manufacturingdetailService.findOne(projectdetailcombinecodesDTOlist.get(i).getManufacturingid().toString()));
            completeprojectdto.setElectricrequirementDTO(electricrequirementService.findOne(projectdetailcombinecodesDTOlist.get(i).getElectricityrequirementid().toString()));

            completeprojectdtolist.add(completeprojectdto);
        }

        return completeprojectdtolist;
    }





    /**
     * POST  /blocks : Create a new block.
     *
     * @param blockDTO the blockDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new blockDTO, or with status 400 (Bad Request) if the block has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/blocks")
//    @Timed
//    public ResponseEntity<BlockDTO> createBlock(@Valid @RequestBody BlockDTO blockDTO) throws URISyntaxException {
//        log.debug("REST request to save Block : {}", blockDTO);
//        if (blockDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new block cannot already have an ID")).body(null);
//        }
//        BlockDTO result = blockService.save(blockDTO);
//        return ResponseEntity.created(new URI("/api/blocks/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * PUT  /blocks : Updates an existing block.
//     *
//     * @param blockDTO the blockDTO to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated blockDTO,
//     * or with status 400 (Bad Request) if the blockDTO is not valid,
//     * or with status 500 (Internal Server Error) if the blockDTO couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/blocks")
//    @Timed
//    public ResponseEntity<BlockDTO> updateBlock(@Valid @RequestBody BlockDTO blockDTO) throws URISyntaxException {
//        log.debug("REST request to update Block : {}", blockDTO);
//        if (blockDTO.getId() == null) {
//            return createBlock(blockDTO);
//        }
//        BlockDTO result = blockService.save(blockDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, blockDTO.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * GET  /blocks : get all the blocks.
//     *
//     * @return the ResponseEntity with status 200 (OK) and the list of blocks in body
//     */
//    @GetMapping("/blocks")
//    @Timed
//    public List<BlockDTO> getAllBlocks() {
//        log.debug("REST request to get all Blocks");
//        return blockService.findAll();
//    }
//
//    @GetMapping("/blocks/district/{districtid}")
//    @Timed
//    public List<BlockDTO> getAllBlocksByDistrict(@PathVariable String districtid) {
//        log.debug("REST request to get all Blocks by districtid");
//        return blockService.findBlockByDistrict(districtid);
//    }
//
//    /**
//     * GET  /blocks/:id : get the "id" block.
//     *
//     * @param id the id of the blockDTO to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the blockDTO, or with status 404 (Not Found)
//     */
//    @GetMapping("/blocks/{id}")
//    @Timed
//    public ResponseEntity<BlockDTO> getBlock(@PathVariable String id) {
//        log.debug("REST request to get Block : {}", id);
//        BlockDTO blockDTO = blockService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(blockDTO));
//    }
//
//    /**
//     * DELETE  /blocks/:id : delete the "id" block.
//     *
//     * @param id the id of the blockDTO to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/blocks/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteBlock(@PathVariable String id) {
//        log.debug("REST request to delete Block : {}", id);
//        blockService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }

}
