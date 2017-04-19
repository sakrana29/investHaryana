package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.domain.Term_declaration_accept;
import com.hartron.investharyana.service.*;
import com.hartron.investharyana.service.dto.*;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Block.
 */
@RestController
@RequestMapping("/api")
public class ProjectCompleteDetailResource {

    private final Logger log = LoggerFactory.getLogger(ProjectCompleteDetailResource.class);

    private final InvestorService investorService;
    private final CompanydetailService companydetailService;
    private final ProjectdetailService projectdetailService;
    private final ProjectsitedetailService projectsitedetailService;
    private final Project_finance_investmentService project_finance_investmentService;
    private final ManufacturingdetailService manufacturingdetailService;
    private final Environment_impactdetailService environment_impactdetailService;
    private final ElectricrequirementService electricrequirementService;
    private final ProjectdetailcombinecodesService projectdetailcombinecodesService;
    private final Project_phaseService project_phaseService;
    private final ProjectrawmaterialService projectrawmaterialService;
    private final ProjectproductService projectproductService;
    private final ProjectprocessflowstepsService projectprocessflowstepsService;
    private final EmissiondetailService emissiondetailService;
    private final WastewaterdetailService wastewaterdetailService;
    private final Term_declaration_acceptService term_declaration_acceptService;

    public ProjectCompleteDetailResource(InvestorService investorService, CompanydetailService companydetailService, ProjectdetailService projectdetailService, ProjectsitedetailService projectsitedetailService, Project_finance_investmentService project_finance_investmentService, ManufacturingdetailService manufacturingdetailService, Environment_impactdetailService environment_impactdetailService, ElectricrequirementService electricrequirementService, ProjectdetailcombinecodesService projectdetailcombinecodesService, Project_phaseService project_phaseService, ProjectrawmaterialService projectrawmaterialService, ProjectproductService projectproductService, ProjectprocessflowstepsService projectprocessflowstepsService, EmissiondetailService emissiondetailService, WastewaterdetailService wastewaterdetailService, Term_declaration_acceptService term_declaration_acceptService) {
        this.investorService = investorService;
        this.companydetailService = companydetailService;
        this.projectdetailService = projectdetailService;
        this.projectsitedetailService = projectsitedetailService;
        this.project_finance_investmentService = project_finance_investmentService;
        this.manufacturingdetailService = manufacturingdetailService;
        this.environment_impactdetailService = environment_impactdetailService;
        this.electricrequirementService = electricrequirementService;
        this.projectdetailcombinecodesService = projectdetailcombinecodesService;
        this.project_phaseService = project_phaseService;
        this.projectrawmaterialService = projectrawmaterialService;
        this.projectproductService = projectproductService;
        this.projectprocessflowstepsService = projectprocessflowstepsService;
        this.emissiondetailService = emissiondetailService;
        this.wastewaterdetailService = wastewaterdetailService;
        this.term_declaration_acceptService = term_declaration_acceptService;
    }

    @GetMapping("/CompleteProjectDetail")
    public List<ProjectCompleteDetailDTO> getAllProjectCompleteDetail() {
        log.debug("REST request to get all project complete detail");
        List<ProjectCompleteDetailDTO> completeprojectdtolist=new ArrayList<>();

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
            completeprojectdto.setProject_phaseDTOList(project_phaseService.findAllByProjectid(projectdetailcombinecodesDTOlist.get(i).getId().toString()));
            completeprojectdto.setProjectrawmaterialDTOList(projectrawmaterialService.findAllByProjectid(projectdetailcombinecodesDTOlist.get(i).getId().toString()));
            completeprojectdto.setProjectproductDTOList(projectproductService.findAllByProjectid(projectdetailcombinecodesDTOlist.get(i).getId().toString()));
            completeprojectdto.setProjectprocessflowstepsDTOList(projectprocessflowstepsService.findAllByProjectid(projectdetailcombinecodesDTOlist.get(i).getId().toString()));
            completeprojectdto.setEmissiondetailDTOList(emissiondetailService.findAllByProjectid(projectdetailcombinecodesDTOlist.get(i).getId().toString()));
            completeprojectdto.setWastewaterdetailDTOList(wastewaterdetailService.findAllByProjectid(projectdetailcombinecodesDTOlist.get(i).getId().toString()));
            completeprojectdtolist.add(completeprojectdto);
        }
        return completeprojectdtolist;
    }

    @GetMapping("/CompleteProjectDetail/{id}")
    @Timed
    public ResponseEntity<ProjectCompleteDetailDTO> getOneProjectCompleteDetail(@PathVariable String id) {
        log.debug("REST request to get one project complete detail");

        ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO=projectdetailcombinecodesService.findOne(id);
        System.out.println(projectdetailcombinecodesDTO);
        ProjectCompleteDetailDTO completeprojectdto=new ProjectCompleteDetailDTO();

        completeprojectdto.setProjectdetailDTO(projectdetailService.findOne(projectdetailcombinecodesDTO.getId().toString()));
        completeprojectdto.setInvestorDTO(investorService.findOne(projectdetailcombinecodesDTO.getInvestorid().toString()));
        completeprojectdto.setCompanydetailDTO(companydetailService.findOne(projectdetailcombinecodesDTO.getCompanydetailid().toString()));
        completeprojectdto.setProjectsitedetailDTO(projectsitedetailService.findOne(projectdetailcombinecodesDTO.getProjectsitedetailid().toString()));
        completeprojectdto.setProject_finance_investmentDTO(project_finance_investmentService.findOne(projectdetailcombinecodesDTO.getProjectfinanceid().toString()));
        completeprojectdto.setManufacturingdetailDTO(manufacturingdetailService.findOne(projectdetailcombinecodesDTO.getManufacturingid().toString()));
        completeprojectdto.setEnvironment_impactdetailDTO(environment_impactdetailService.findOne(projectdetailcombinecodesDTO.getEnvironmentimpactdetailid().toString()));
        completeprojectdto.setElectricrequirementDTO(electricrequirementService.findOne(projectdetailcombinecodesDTO.getElectricityrequirementid().toString()));
        completeprojectdto.setTerm_declaration_acceptDTO(term_declaration_acceptService.findOne(projectdetailcombinecodesDTO.getTermdeclarationacceptid().toString()));

        completeprojectdto.setProject_phaseDTOList(project_phaseService.findAllByProjectid(id));
        completeprojectdto.setProjectrawmaterialDTOList(projectrawmaterialService.findAllByProjectid(id));
        completeprojectdto.setProjectproductDTOList(projectproductService.findAllByProjectid(id));
        completeprojectdto.setProjectprocessflowstepsDTOList(projectprocessflowstepsService.findAllByProjectid(id));
        completeprojectdto.setEmissiondetailDTOList(emissiondetailService.findAllByProjectid(id));
        completeprojectdto.setWastewaterdetailDTOList(wastewaterdetailService.findAllByProjectid(id));

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(completeprojectdto));
    }

    @PostMapping("/CompleteProjectDetail")
    @Timed
    public ResponseEntity<ProjectCompleteDetailDTO> createProjectCompleteDetail(@Valid @RequestBody ProjectCompleteDetailDTO projectCompleteDetailDTO) throws URISyntaxException {
        log.debug("REST request to save data in all project entities : {}", projectCompleteDetailDTO);
        if (projectCompleteDetailDTO.getProjectdetailDTO().getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Complete Project Detail", "idexists", "A new Project cannot already have an ID")).body(null);
        }

        if(projectCompleteDetailDTO.getInvestorDTO().getCafpin()!=null)
        {
            if(projectCompleteDetailDTO.getInvestorDTO().getCafpin().equals("1"))
            {
                boolean flag=false;
                String finalCAFPin="";
                while(flag==false)
                {
                    String tempcafpin= RandomStringUtils.randomNumeric(10);
                    List<InvestorDTO> investorDTOList= investorService.findAll();
                    List<InvestorDTO> filteredArticleList= investorDTOList.stream().filter(article -> article.getCafpin().equals(tempcafpin)).collect(Collectors.toList());

                    if(filteredArticleList.size()==0)
                    {
                        flag=true;
                        finalCAFPin=tempcafpin;
                    }
                }
                projectCompleteDetailDTO.getInvestorDTO().setCafpin(finalCAFPin);
            }
        }
        else
            projectCompleteDetailDTO.getInvestorDTO().setCafpin("NA");

        InvestorDTO resultInvestor = investorService.save(projectCompleteDetailDTO.getInvestorDTO());
        CompanydetailDTO resultCompany = companydetailService.save(projectCompleteDetailDTO.getCompanydetailDTO());
        ProjectdetailDTO resultProjectdetail = projectdetailService.save(projectCompleteDetailDTO.getProjectdetailDTO());
        ProjectsitedetailDTO resultSiteDetail = projectsitedetailService.save(projectCompleteDetailDTO.getProjectsitedetailDTO());
        Project_finance_investmentDTO resultFinance = project_finance_investmentService.save(projectCompleteDetailDTO.getProject_finance_investmentDTO());

        projectCompleteDetailDTO.getManufacturingdetailDTO().setProjectid(resultProjectdetail.getId());
        ManufacturingdetailDTO resultManufacturing = manufacturingdetailService.save(projectCompleteDetailDTO.getManufacturingdetailDTO());

        Environment_impactdetailDTO resultEnvironmentImpact=environment_impactdetailService.save(projectCompleteDetailDTO.getEnvironment_impactdetailDTO());

        ElectricrequirementDTO resultElectric = electricrequirementService.save(projectCompleteDetailDTO.getElectricrequirementDTO());

        projectCompleteDetailDTO.getTerm_declaration_acceptDTO().setApplicationdate(ZonedDateTime.now());
        projectCompleteDetailDTO.getTerm_declaration_acceptDTO().setCreatedate(ZonedDateTime.now());
        projectCompleteDetailDTO.getTerm_declaration_acceptDTO().setUpdatedate(ZonedDateTime.now());
        Term_declaration_acceptDTO resultTermDeclaration= term_declaration_acceptService.save(projectCompleteDetailDTO.getTerm_declaration_acceptDTO());

        saveInnerEntities(projectCompleteDetailDTO, resultProjectdetail);

        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setInvestorid(resultInvestor.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setId(resultProjectdetail.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setCompanydetailid(resultCompany.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setProjectsitedetailid(resultSiteDetail.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setElectricityrequirementid(resultElectric.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setProjectfinanceid(resultFinance.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setManufacturingid(resultManufacturing.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setEnvironmentimpactdetailid(resultEnvironmentImpact.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setTermdeclarationacceptid(resultTermDeclaration.getId());

        ProjectdetailcombinecodesDTO resultCombineCodes = projectdetailcombinecodesService.save(projectCompleteDetailDTO.getProjectdetailcombinecodesDTO());

        return ResponseEntity.created(new URI("/api/CompleteProjectDetail/" + resultProjectdetail.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("CompleteProjectDetail", resultProjectdetail.getId().toString()))
            .body(projectCompleteDetailDTO);
    }

    private void saveInnerEntities(@Valid @RequestBody ProjectCompleteDetailDTO projectCompleteDetailDTO, ProjectdetailDTO resultProjectdetail) {

        List<Project_phaseDTO> project_phaseDTOList=projectCompleteDetailDTO.getProject_phaseDTOList();
        if(project_phaseDTOList.size()!=0)
        {
            for(int i=0;i<project_phaseDTOList.size();i++)
            {
                project_phaseDTOList.get(i).setProjectid(resultProjectdetail.getId());
                Project_phaseDTO project_phaseDTO=project_phaseDTOList.get(i);
                project_phaseService.save(project_phaseDTO);
            }
        }

        List<ProjectrawmaterialDTO> projectrawmaterialDTOList=projectCompleteDetailDTO.getProjectrawmaterialDTOList();
        if(projectrawmaterialDTOList.size()!=0) {
            for (int i = 0; i < projectrawmaterialDTOList.size(); i++) {
                projectrawmaterialDTOList.get(i).setProjectid(resultProjectdetail.getId());
                ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialDTOList.get(i);
                projectrawmaterialService.save(projectrawmaterialDTO);
            }
        }

        List<ProjectproductDTO> projectproductDTOList=projectCompleteDetailDTO.getProjectproductDTOList();
        if(projectproductDTOList.size()!=0) {
            for (int i = 0; i < projectproductDTOList.size(); i++) {
                projectproductDTOList.get(i).setProjectid(resultProjectdetail.getId());
                ProjectproductDTO projectproductDTO = projectproductDTOList.get(i);
                projectproductService.save(projectproductDTO);
            }
        }

        List<ProjectprocessflowstepsDTO> projectprocessflowstepsDTOList=projectCompleteDetailDTO.getProjectprocessflowstepsDTOList();
        if(projectprocessflowstepsDTOList.size()!=0) {
            for (int i = 0; i < projectprocessflowstepsDTOList.size(); i++) {
                projectprocessflowstepsDTOList.get(i).setProjectid(resultProjectdetail.getId());
                ProjectprocessflowstepsDTO projectprocessflowstepsDTO = projectprocessflowstepsDTOList.get(i);
                projectprocessflowstepsService.save(projectprocessflowstepsDTO);
            }
        }

        List<EmissiondetailDTO> emissiondetailDTOList = projectCompleteDetailDTO.getEmissiondetailDTOList();
        if(emissiondetailDTOList.size()!=0) {
            for (int i = 0; i < emissiondetailDTOList.size(); i++) {
                emissiondetailDTOList.get(i).setProjectid(resultProjectdetail.getId());
                EmissiondetailDTO emissiondetailDTO = emissiondetailDTOList.get(i);
                emissiondetailService.save(emissiondetailDTO);
            }
        }

        List<WastewaterdetailDTO> wastewaterdetailDTOList=projectCompleteDetailDTO.getWastewaterdetailDTOList();
        if(wastewaterdetailDTOList.size()!=0) {
            for (int i = 0; i < wastewaterdetailDTOList.size(); i++) {
                wastewaterdetailDTOList.get(i).setProjectid(resultProjectdetail.getId());
                WastewaterdetailDTO wastewaterdetailDTO = wastewaterdetailDTOList.get(i);
                wastewaterdetailService.save(wastewaterdetailDTO);
            }
        }
    }

    @PutMapping("/CompleteProjectDetail")
    @Timed
    public ResponseEntity<ProjectCompleteDetailDTO> updateProjectdetail(@RequestBody ProjectCompleteDetailDTO projectCompleteDetailDTO) throws URISyntaxException {
        log.debug("REST request to update Complete Project Detail: {}", projectCompleteDetailDTO);
        if (projectCompleteDetailDTO.getProjectdetailDTO().getId() == null) {
            return createProjectCompleteDetail(projectCompleteDetailDTO);
        }

        if(projectCompleteDetailDTO.getInvestorDTO().getCafpin()!="NA")
        {
            if(projectCompleteDetailDTO.getInvestorDTO().getCafpin().equals("1"))
            {
                boolean flag=false;
                String finalCAFPin="";
                while(flag==false)
                {
                    String tempcafpin= RandomStringUtils.randomNumeric(10);
                    List<InvestorDTO> investorDTOList= investorService.findAll();
                    List<InvestorDTO> filteredArticleList= investorDTOList.stream().filter(article -> article.getCafpin().equals(tempcafpin)).collect(Collectors.toList());

                    if(filteredArticleList.size()==0)
                    {
                        flag=true;
                        finalCAFPin=tempcafpin;
                    }
                }
                projectCompleteDetailDTO.getInvestorDTO().setCafpin(finalCAFPin);
            }
        }

        InvestorDTO resultInvestor = investorService.save(projectCompleteDetailDTO.getInvestorDTO());
        CompanydetailDTO resultCompany = companydetailService.save(projectCompleteDetailDTO.getCompanydetailDTO());
        ProjectdetailDTO resultProjectdetail = projectdetailService.save(projectCompleteDetailDTO.getProjectdetailDTO());
        ProjectsitedetailDTO resultSiteDetail = projectsitedetailService.save(projectCompleteDetailDTO.getProjectsitedetailDTO());
        Project_finance_investmentDTO resultFinance = project_finance_investmentService.save(projectCompleteDetailDTO.getProject_finance_investmentDTO());
        ManufacturingdetailDTO resultManufacturing = manufacturingdetailService.save(projectCompleteDetailDTO.getManufacturingdetailDTO());
        Environment_impactdetailDTO resultEnvironmentImpact=environment_impactdetailService.save(projectCompleteDetailDTO.getEnvironment_impactdetailDTO());
        ElectricrequirementDTO resultElectric = electricrequirementService.save(projectCompleteDetailDTO.getElectricrequirementDTO());

        projectCompleteDetailDTO.getTerm_declaration_acceptDTO().setUpdatedate(ZonedDateTime.now());
        Term_declaration_acceptDTO resultTermDeclaration= term_declaration_acceptService.save(projectCompleteDetailDTO.getTerm_declaration_acceptDTO());

        updateInnerEntities(projectCompleteDetailDTO, resultProjectdetail);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Complete Project Detail", projectCompleteDetailDTO.getProjectdetailDTO().getId().toString()))
            .body(projectCompleteDetailDTO);
    }

    private void updateInnerEntities(@RequestBody ProjectCompleteDetailDTO projectCompleteDetailDTO, ProjectdetailDTO resultProjectdetail) {
        List<Project_phaseDTO> project_phaseDTOList=project_phaseService.findAllByProjectid(projectCompleteDetailDTO.getProjectdetailDTO().getId().toString());
        for(int i=0;i<project_phaseDTOList.size();i++)
        {
            project_phaseService.delete(project_phaseDTOList.get(i).getId().toString());
            project_phaseService.deleteByProject(project_phaseDTOList.get(i).getProjectid().toString());
        }
        project_phaseDTOList=projectCompleteDetailDTO.getProject_phaseDTOList();
        for(int i=0;i<project_phaseDTOList.size();i++)
        {
            project_phaseDTOList.get(i).setProjectid(resultProjectdetail.getId());
            project_phaseService.save(project_phaseDTOList.get(i));
        }

        List<ProjectrawmaterialDTO> projectrawmaterialDTOList=projectrawmaterialService.findAllByProjectid(projectCompleteDetailDTO.getProjectdetailDTO().getId().toString());
        for(int i=0;i<projectrawmaterialDTOList.size();i++)
        {
            projectrawmaterialService.delete(projectrawmaterialDTOList.get(i).getId().toString());
            projectrawmaterialService.deleteByProject(projectrawmaterialDTOList.get(i).getProjectid().toString());
        }
        projectrawmaterialDTOList=projectCompleteDetailDTO.getProjectrawmaterialDTOList();
        System.out.println(projectCompleteDetailDTO.getProjectrawmaterialDTOList());
        for(int i=0;i<projectrawmaterialDTOList.size();i++)
        {
            projectrawmaterialDTOList.get(i).setProjectid(resultProjectdetail.getId());
            projectrawmaterialService.save(projectrawmaterialDTOList.get(i));
        }

        List<ProjectproductDTO> projectproductDTOList=projectproductService.findAllByProjectid(projectCompleteDetailDTO.getProjectdetailDTO().getId().toString());
        for(int i=0;i<projectproductDTOList.size();i++)
        {
            projectproductService.delete(projectproductDTOList.get(i).getId().toString());
            projectproductService.deleteByProject(projectproductDTOList.get(i).getProjectid().toString());
        }
        projectproductDTOList=projectCompleteDetailDTO.getProjectproductDTOList();
        for(int i=0;i<projectproductDTOList.size();i++)
        {
            projectproductDTOList.get(i).setProjectid(resultProjectdetail.getId());
            projectproductService.save(projectproductDTOList.get(i));
        }

        List<ProjectprocessflowstepsDTO> projectprocessflowstepsDTOList=projectprocessflowstepsService.findAllByProjectid(projectCompleteDetailDTO.getProjectdetailDTO().getId().toString());
        for(int i=0;i<projectprocessflowstepsDTOList.size();i++)
        {
            projectprocessflowstepsService.delete(projectprocessflowstepsDTOList.get(i).getId().toString());
            projectprocessflowstepsService.deleteByProject(projectprocessflowstepsDTOList.get(i).getProjectid().toString());
        }
        projectprocessflowstepsDTOList=projectCompleteDetailDTO.getProjectprocessflowstepsDTOList();
        for(int i=0;i<projectprocessflowstepsDTOList.size();i++)
        {
            projectprocessflowstepsDTOList.get(i).setProjectid(resultProjectdetail.getId());
            projectprocessflowstepsService.save(projectprocessflowstepsDTOList.get(i));
        }

        List<EmissiondetailDTO> emissiondetailDTOList=emissiondetailService.findAllByProjectid(projectCompleteDetailDTO.getProjectdetailDTO().getId().toString());
        for(int i=0;i<emissiondetailDTOList.size();i++)
        {
            emissiondetailService.delete(emissiondetailDTOList.get(i).getId().toString());
            emissiondetailService.deleteByProject(emissiondetailDTOList.get(i).getProjectid().toString());
        }
        emissiondetailDTOList=projectCompleteDetailDTO.getEmissiondetailDTOList();
        for(int i=0;i<emissiondetailDTOList.size();i++)
        {
            emissiondetailDTOList.get(i).setProjectid(resultProjectdetail.getId());
            emissiondetailService.save(emissiondetailDTOList.get(i));
        }

        List<WastewaterdetailDTO> wastewaterdetailDTOList=wastewaterdetailService.findAllByProjectid(projectCompleteDetailDTO.getProjectdetailDTO().getId().toString());
        for(int i=0;i<wastewaterdetailDTOList.size();i++)
        {
            wastewaterdetailService.delete(wastewaterdetailDTOList.get(i).getId().toString());
            wastewaterdetailService.deleteByProject(wastewaterdetailDTOList.get(i).getProjectid().toString());
        }
        wastewaterdetailDTOList=projectCompleteDetailDTO.getWastewaterdetailDTOList();
        for(int i=0;i<wastewaterdetailDTOList.size();i++)
        {
            wastewaterdetailDTOList.get(i).setProjectid(resultProjectdetail.getId());
            wastewaterdetailService.save(wastewaterdetailDTOList.get(i));
        }
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
