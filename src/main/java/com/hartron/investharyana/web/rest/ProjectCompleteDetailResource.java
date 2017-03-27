package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.*;
import com.hartron.investharyana.service.dto.*;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final CountryService countryService;
    private final StateService stateService;
    private final City_town_villageService city_town_villageService;
    private final BusinessentitysService businessentitysService;
    private final SectorService sectorService;
    private final IndustrysizeService industrysizeService;
    private final ProjectypeService projectypeService;
    private final ProjectcategoryService projectcategoryService;
    private final ApprovalformsService approvalformsService;

    public ProjectCompleteDetailResource(InvestorService investorService, CompanydetailService companydetailService, ProjectdetailService projectdetailService, ProjectsitedetailService projectsitedetailService, Project_finance_investmentService project_finance_investmentService, ManufacturingdetailService manufacturingdetailService, ElectricrequirementService electricrequirementService, ProjectdetailcombinecodesService projectdetailcombinecodesService, CountryService countryService, StateService stateService, City_town_villageService city_town_villageService, BusinessentitysService businessentitysService, SectorService sectorService, IndustrysizeService industrysizeService, ProjectypeService projectypeService, ProjectcategoryService projectcategoryService, ApprovalformsService approvalformsService) {
        this.investorService = investorService;
        this.companydetailService = companydetailService;
        this.projectdetailService = projectdetailService;
        this.projectsitedetailService = projectsitedetailService;
        this.project_finance_investmentService = project_finance_investmentService;
        this.manufacturingdetailService = manufacturingdetailService;
        this.electricrequirementService = electricrequirementService;
        this.projectdetailcombinecodesService = projectdetailcombinecodesService;
        this.countryService = countryService;
        this.stateService = stateService;
        this.city_town_villageService = city_town_villageService;
        this.businessentitysService = businessentitysService;
        this.sectorService = sectorService;
        this.industrysizeService = industrysizeService;
        this.projectypeService = projectypeService;
        this.projectcategoryService = projectcategoryService;
        this.approvalformsService = approvalformsService;
    }

    @GetMapping("/CompleteProjectDetail")
    public List<ProjectCompleteDetailDTO> getAllProjectCompleteDetail() {
        log.debug("REST request to get all project complete detail");
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

    @GetMapping("/CompleteProjectDetail/{id}")
    @Timed
    public ResponseEntity<ProjectCompleteDetailDTO> getOneProjectCompleteDetail(@PathVariable String id) {
        log.debug("REST request to get one project complete detail");
        //ProjectCompleteDetailDTO completeprojectdto=new ProjectCompleteDetailDTO();

        ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO=projectdetailcombinecodesService.findOne(id);
        ProjectCompleteDetailDTO completeprojectdto=new ProjectCompleteDetailDTO();
        completeprojectdto.setProjectdetailDTO(projectdetailService.findOne(projectdetailcombinecodesDTO.getId().toString()));
        completeprojectdto.setInvestorDTO(investorService.findOne(projectdetailcombinecodesDTO.getInvestorid().toString()));
        completeprojectdto.setCompanydetailDTO(companydetailService.findOne(projectdetailcombinecodesDTO.getCompanydetailid().toString()));
        completeprojectdto.setProjectsitedetailDTO(projectsitedetailService.findOne(projectdetailcombinecodesDTO.getProjectsitedetailid().toString()));
        completeprojectdto.setProject_finance_investmentDTO(project_finance_investmentService.findOne(projectdetailcombinecodesDTO.getProjectfinanceid().toString()));
        completeprojectdto.setManufacturingdetailDTO(manufacturingdetailService.findOne(projectdetailcombinecodesDTO.getManufacturingid().toString()));
        completeprojectdto.setElectricrequirementDTO(electricrequirementService.findOne(projectdetailcombinecodesDTO.getElectricityrequirementid().toString()));

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(completeprojectdto));
        //return projectdetailcombinecodesDTO;
    }

//    @GetMapping("/CompleteProjectDetailData")
//    @Timed
//    public List<ProjectCompleteDetailDataDTO> getAllProjectCompleteDetailData() {
//        log.debug("REST request to get all project complete detail data");
//        //ProjectCompleteDetailDTO completeprojectdto=new ProjectCompleteDetailDTO();
//
//        List<ProjectCompleteDetailDataDTO> projectCompleteDetailDataDTOList=new ArrayList<>();
//        List<ProjectdetailcombinecodesDTO> projectdetailcombinecodesDTOList = projectdetailcombinecodesService.findAll();
//
//        for(int i=0;i<projectdetailcombinecodesDTOList.size();i++) {
//            ProjectCompleteDetailDataDTO completeprojectdatadto = new ProjectCompleteDetailDataDTO();
//            ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO= projectdetailcombinecodesDTOList.get(i);
//
//            InvestorDTO investorDTO = investorService.findOne(projectdetailcombinecodesDTO.getInvestorid().toString());
//            InvestorDataDTO investorDataDTO = new InvestorDataDTO();
//            CountryDTO countryDTO = new CountryDTO();
//            if (investorDTO.getCountryid() != null)
//                countryDTO = countryService.findOne(investorDTO.getCountryid().toString());
//
//            StateDTO stateDTO = new StateDTO();
//            if (investorDTO.getStateid() != null)
//                stateDTO = stateService.findOne(investorDTO.getStateid().toString());
//
//            City_town_villageDTO city_town_villageDTO = new City_town_villageDTO();
//            if (investorDTO.getCityid() != null)
//                city_town_villageDTO = city_town_villageService.findOne(investorDTO.getCityid().toString());
//            generateInvstorDataDTO(investorDTO, investorDataDTO, countryDTO, stateDTO, city_town_villageDTO);
//
//            CompanydetailDTO companydetailDTO1 = companydetailService.findOne(projectdetailcombinecodesDTO.getCompanydetailid().toString());
//            BusinessentitysDTO businessentitysDTO = new BusinessentitysDTO();
//            if (companydetailDTO1.getBusinessentitytype() != null)
//                businessentitysDTO = businessentitysService.findOne(companydetailDTO1.getBusinessentitytype().toString());
//            CompanydetailDataDTO companydetailDTO = new CompanydetailDataDTO();
//            generateCompanyDataDTO(companydetailDTO1, businessentitysDTO, companydetailDTO);
//
//            ProjectdetailDTO projectdetailDTO1 = projectdetailService.findOne(projectdetailcombinecodesDTO.getId().toString());
//            SectorDTO sectorDTO = new SectorDTO();
//            if (projectdetailDTO1.getSectorid() != null)
//                sectorDTO = sectorService.findOne(projectdetailDTO1.getSectorid().toString());
//
//            IndustrysizeDTO industrysizeDTO = new IndustrysizeDTO();
//            if (projectdetailDTO1.getSize_of_industry() != null)
//                industrysizeDTO = industrysizeService.findOne(projectdetailDTO1.getSize_of_industry().toString());
//
//            ProjectypeDTO projectypeDTO = new ProjectypeDTO();
//            if (projectdetailDTO1.getProjectype() != null)
//                projectypeDTO = projectypeService.findOne(projectdetailDTO1.getProjectype().toString());
//
//            ProjectcategoryDTO projectcategoryDTO = new ProjectcategoryDTO();
//            if (projectdetailDTO1.getCategory_of_project() != null)
//                projectcategoryDTO = projectcategoryService.findOne(projectdetailDTO1.getCategory_of_project().toString());
//
//            CountryDTO countryDTO1 = new CountryDTO();
//            if (projectdetailDTO1.getCollaboration_with_foreign_country() != null)
//                countryDTO1 = countryService.findOne(projectdetailDTO1.getCollaboration_with_foreign_country().toString());
//
//            ApprovalformsDTO approvalformsDTO = new ApprovalformsDTO();
//            if (projectdetailDTO1.getApproval_application_form() != null)
//                approvalformsDTO = approvalformsService.findOne(projectdetailDTO1.getApproval_application_form().toString());
//            ProjectdetailDataDTO projectdetailDTO = generateProjectdetailDataDTO(projectdetailDTO1, sectorDTO, industrysizeDTO, projectypeDTO, projectcategoryDTO, countryDTO1, approvalformsDTO);
//
//            completeprojectdatadto.setInvestorDTO(investorDataDTO);
//            completeprojectdatadto.setCompanydetailDTO(companydetailDTO);
//            completeprojectdatadto.setProjectdetailDTO(projectdetailDTO);
//            completeprojectdatadto.setProjectsitedetailDTO(projectsitedetailService.findOne(projectdetailcombinecodesDTO.getProjectsitedetailid().toString()));
//            completeprojectdatadto.setProject_finance_investmentDTO(project_finance_investmentService.findOne(projectdetailcombinecodesDTO.getProjectfinanceid().toString()));
//            completeprojectdatadto.setManufacturingdetailDTO(manufacturingdetailService.findOne(projectdetailcombinecodesDTO.getManufacturingid().toString()));
//            completeprojectdatadto.setElectricrequirementDTO(electricrequirementService.findOne(projectdetailcombinecodesDTO.getElectricityrequirementid().toString()));
//
//            projectCompleteDetailDataDTOList.add(completeprojectdatadto);
//        }
////        System.out.print(completeprojectdatadto);
//        return projectCompleteDetailDataDTOList;
//        //return projectdetailcombinecodesDTO;
//    }
//    @GetMapping("/CompleteProjectDetailData/{id}")
//    @Timed
//    public ResponseEntity<ProjectCompleteDetailDataDTO> getOneProjectCompleteDetailData(@PathVariable String id){
//            log.debug("REST request to get one project complete detail data");
//            //ProjectCompleteDetailDTO completeprojectdto=new ProjectCompleteDetailDTO();
//
//            ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO = projectdetailcombinecodesService.findOne(id);
//            ProjectCompleteDetailDataDTO completeprojectdatadto = new ProjectCompleteDetailDataDTO();
//
//            InvestorDTO investorDTO = investorService.findOne(projectdetailcombinecodesDTO.getInvestorid().toString());
//            InvestorDataDTO investorDataDTO = new InvestorDataDTO();
//            CountryDTO countryDTO = new CountryDTO();
//            if (investorDTO.getCountryid() != null)
//                countryDTO = countryService.findOne(investorDTO.getCountryid().toString());
//
//            StateDTO stateDTO = new StateDTO();
//            if (investorDTO.getStateid() != null)
//                stateDTO = stateService.findOne(investorDTO.getStateid().toString());
//
//            City_town_villageDTO city_town_villageDTO = new City_town_villageDTO();
//            if (investorDTO.getCityid() != null)
//                city_town_villageDTO = city_town_villageService.findOne(investorDTO.getCityid().toString());
//            generateInvstorDataDTO(investorDTO, investorDataDTO, countryDTO, stateDTO, city_town_villageDTO);
//
//            CompanydetailDTO companydetailDTO1 = companydetailService.findOne(projectdetailcombinecodesDTO.getCompanydetailid().toString());
//            BusinessentitysDTO businessentitysDTO = new BusinessentitysDTO();
//            if (companydetailDTO1.getBusinessentitytype() != null)
//                businessentitysDTO = businessentitysService.findOne(companydetailDTO1.getBusinessentitytype().toString());
//            CompanydetailDataDTO companydetailDTO = new CompanydetailDataDTO();
//            generateCompanyDataDTO(companydetailDTO1, businessentitysDTO, companydetailDTO);
//
//            ProjectdetailDTO projectdetailDTO1 = projectdetailService.findOne(projectdetailcombinecodesDTO.getId().toString());
//            SectorDTO sectorDTO = new SectorDTO();
//            if (projectdetailDTO1.getSectorid() != null)
//                sectorDTO = sectorService.findOne(projectdetailDTO1.getSectorid().toString());
//
//            IndustrysizeDTO industrysizeDTO = new IndustrysizeDTO();
//            if (projectdetailDTO1.getSize_of_industry() != null)
//                industrysizeDTO = industrysizeService.findOne(projectdetailDTO1.getSize_of_industry().toString());
//
//            ProjectypeDTO projectypeDTO = new ProjectypeDTO();
//            if (projectdetailDTO1.getProjectype() != null)
//                projectypeDTO = projectypeService.findOne(projectdetailDTO1.getProjectype().toString());
//
//            ProjectcategoryDTO projectcategoryDTO = new ProjectcategoryDTO();
//            if (projectdetailDTO1.getCategory_of_project() != null)
//                projectcategoryDTO = projectcategoryService.findOne(projectdetailDTO1.getCategory_of_project().toString());
//
//            CountryDTO countryDTO1 = new CountryDTO();
//            if (projectdetailDTO1.getCollaboration_with_foreign_country() != null)
//                countryDTO1 = countryService.findOne(projectdetailDTO1.getCollaboration_with_foreign_country().toString());
//
//            ApprovalformsDTO approvalformsDTO = new ApprovalformsDTO();
//            if (projectdetailDTO1.getApproval_application_form() != null)
//                approvalformsDTO = approvalformsService.findOne(projectdetailDTO1.getApproval_application_form().toString());
//            ProjectdetailDataDTO projectdetailDTO = generateProjectdetailDataDTO(projectdetailDTO1, sectorDTO, industrysizeDTO, projectypeDTO, projectcategoryDTO, countryDTO1, approvalformsDTO);
//
//            completeprojectdatadto.setInvestorDTO(investorDataDTO);
//            completeprojectdatadto.setCompanydetailDTO(companydetailDTO);
//            completeprojectdatadto.setProjectdetailDTO(projectdetailDTO);
//            completeprojectdatadto.setProjectsitedetailDTO(projectsitedetailService.findOne(projectdetailcombinecodesDTO.getProjectsitedetailid().toString()));
//            completeprojectdatadto.setProject_finance_investmentDTO(project_finance_investmentService.findOne(projectdetailcombinecodesDTO.getProjectfinanceid().toString()));
//            completeprojectdatadto.setManufacturingdetailDTO(manufacturingdetailService.findOne(projectdetailcombinecodesDTO.getManufacturingid().toString()));
//            completeprojectdatadto.setElectricrequirementDTO(electricrequirementService.findOne(projectdetailcombinecodesDTO.getElectricityrequirementid().toString()));
//
////        System.out.print(completeprojectdatadto);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(completeprojectdatadto));
//        //return projectdetailcombinecodesDTO;
//    }
//
//    private ProjectdetailDataDTO generateProjectdetailDataDTO(ProjectdetailDTO projectdetailDTO1, SectorDTO sectorDTO, IndustrysizeDTO industrysizeDTO, ProjectypeDTO projectypeDTO, ProjectcategoryDTO projectcategoryDTO, CountryDTO countryDTO1, ApprovalformsDTO approvalformsDTO) {
//        ProjectdetailDataDTO projectdetailDTO=new ProjectdetailDataDTO();
//        projectdetailDTO.setApproval_application_form(projectdetailDTO1.getApproval_application_form());
//        projectdetailDTO.setApproval_application_formname(approvalformsDTO.getExistingapprovalforms());
//        projectdetailDTO.setApproval_document(projectdetailDTO1.getApproval_document());
//        projectdetailDTO.setCategory_of_project(projectdetailDTO1.getCategory_of_project());
//        projectdetailDTO.setCategory_of_projectname(projectcategoryDTO.getCategorytype());
//        projectdetailDTO.setCollaboration_with_foreign_country(projectdetailDTO1.getCollaboration_with_foreign_country());
//        projectdetailDTO.setCollaboration_with_foreign_countryname(countryDTO1.getCountryname());
//        projectdetailDTO.setDetail_project_report(projectdetailDTO1.getDetail_project_report());
//        projectdetailDTO.setEdc_sif_clu_fee_paid_applicable(projectdetailDTO1.getEdc_sif_clu_fee_paid_applicable());
//        projectdetailDTO.setEdc_sif_clu_fee_paid_document(projectdetailDTO1.getEdc_sif_clu_fee_paid_document());
//        projectdetailDTO.setExisting_regulatory_approval(projectdetailDTO1.getExisting_regulatory_approval());
//        projectdetailDTO.setId(projectdetailDTO1.getId());
//        projectdetailDTO.setInvestorid(projectdetailDTO1.getInvestorid());
//        projectdetailDTO.setNiccode(projectdetailDTO1.getNiccode());
//        projectdetailDTO.setProjectpurpose(projectdetailDTO1.getProjectpurpose());
//        projectdetailDTO.setProjectype(projectdetailDTO1.getProjectype());
//        projectdetailDTO.setProjectypename(projectypeDTO.getProjectypes());
//        projectdetailDTO.setSectorid(projectdetailDTO1.getSectorid());
//        projectdetailDTO.setSectorname(sectorDTO.getSectortype());
//        projectdetailDTO.setSize_of_industry(projectdetailDTO1.getSize_of_industry());
//        projectdetailDTO.setSize_of_industryname(industrysizeDTO.getSizeofindustry());
//        return projectdetailDTO;
//    }
//
//    private void generateCompanyDataDTO(CompanydetailDTO companydetailDTO1, BusinessentitysDTO businessentitysDTO, CompanydetailDataDTO companydetailDTO) {
//        companydetailDTO.setAadhar_number(companydetailDTO1.getAadhar_number());
//        companydetailDTO.setAadharcard(companydetailDTO1.getAadharcard());
//        companydetailDTO.setBusinessentity(companydetailDTO1.getBusinessentity());
//        companydetailDTO.setBusinessentitytype(companydetailDTO1.getBusinessentitytype());
//        companydetailDTO.setBusinessentitytypename(businessentitysDTO.getBusinessentitytype());
//        companydetailDTO.setCst_document(companydetailDTO1.getCst_document());
//        companydetailDTO.setCst_number(companydetailDTO1.getCst_number());
//        companydetailDTO.setDesignation(companydetailDTO1.getDesignation());
//        companydetailDTO.setDirector_md_ceo_list(companydetailDTO1.getDirector_md_ceo_list());
//        companydetailDTO.setDirector_promoter_md_ceo_number(companydetailDTO1.getDirector_promoter_md_ceo_number());
//        companydetailDTO.setId(companydetailDTO1.getId());
//        companydetailDTO.setInvestorid(companydetailDTO1.getInvestorid());
//        companydetailDTO.setMoa_partnershipdeed(companydetailDTO1.getMoa_partnershipdeed());
//        companydetailDTO.setNri(companydetailDTO1.getNri());
//        companydetailDTO.setPan_number(companydetailDTO1.getPan_number());
//        companydetailDTO.setPancard(companydetailDTO1.getPancard());
//        companydetailDTO.setPromoter_md_director(companydetailDTO1.getPromoter_md_director());
//        companydetailDTO.setRegistration_document(companydetailDTO1.getRegistration_document());
//        companydetailDTO.setTin_vat_document(companydetailDTO1.getTin_vat_document());
//        companydetailDTO.setTin_vat_number(companydetailDTO1.getTin_vat_number());
//    }
//
//    private void generateInvstorDataDTO(InvestorDTO investorDTO, InvestorDataDTO investorDataDTO, CountryDTO countryDTO, StateDTO stateDTO, City_town_villageDTO city_town_villageDTO) {
//        investorDataDTO.setId(investorDTO.getId());
//        investorDataDTO.setAddress1(investorDTO.getAddress1());
//        investorDataDTO.setAddress2(investorDTO.getAddress2());
//        investorDataDTO.setAddress3(investorDTO.getAddress3());
//        investorDataDTO.setCityid(investorDTO.getCityid());
//        investorDataDTO.setCityname(city_town_villageDTO.getCity_town_village_name());
//        investorDataDTO.setCountryid(investorDTO.getCountryid());
//        investorDataDTO.setCountryname(countryDTO.getCountryname());
//        investorDataDTO.setEmailprimary(investorDTO.getEmailprimary());
//        investorDataDTO.setEmailsecondary(investorDTO.getEmailsecondary());
//        investorDataDTO.setFirstname(investorDTO.getFirstname());
//        investorDataDTO.setInvestorpicpath(investorDTO.getInvestorpicpath());
//        investorDataDTO.setLastname(investorDTO.getLastname());
//        investorDataDTO.setMiddlename(investorDTO.getMiddlename());
//        investorDataDTO.setMouapplicable(investorDTO.getMouapplicable());
//        investorDataDTO.setMoudocument(investorDTO.getMoudocument());
//        investorDataDTO.setMouidnumber(investorDTO.getMouidnumber());
//        investorDataDTO.setMousignyear(investorDTO.getMousignyear());
//        investorDataDTO.setStateid(investorDTO.getStateid());
//        investorDataDTO.setStatename(stateDTO.getStatename());
//        investorDataDTO.setUserlogin(investorDTO.getUserlogin());
//    }

    @PostMapping("/CompleteProjectDetail")
    @Timed
    public ResponseEntity<ProjectCompleteDetailDTO> createProjectCompleteDetail(@Valid @RequestBody ProjectCompleteDetailDTO projectCompleteDetailDTO) throws URISyntaxException {
        log.debug("REST request to save data in all project entities : {}", projectCompleteDetailDTO);
        if (projectCompleteDetailDTO.getProjectdetailDTO().getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Complete Project Detail", "idexists", "A new Project cannot already have an ID")).body(null);
        }
        InvestorDTO resultInvestor = investorService.save(projectCompleteDetailDTO.getInvestorDTO());

        projectCompleteDetailDTO.getCompanydetailDTO().setInvestorid(resultInvestor.getId());
        CompanydetailDTO resultCompany = companydetailService.save(projectCompleteDetailDTO.getCompanydetailDTO());

        projectCompleteDetailDTO.getProjectdetailDTO().setInvestorid(resultInvestor.getId());
        ProjectdetailDTO resultProjectdetail = projectdetailService.save(projectCompleteDetailDTO.getProjectdetailDTO());

        projectCompleteDetailDTO.getProjectsitedetailDTO().setProjectid(resultProjectdetail.getId());
        ProjectsitedetailDTO resultSiteDetail = projectsitedetailService.save(projectCompleteDetailDTO.getProjectsitedetailDTO());

        projectCompleteDetailDTO.getProject_finance_investmentDTO().setProjectid(resultProjectdetail.getId());
        Project_finance_investmentDTO resultFinance = project_finance_investmentService.save(projectCompleteDetailDTO.getProject_finance_investmentDTO());

        projectCompleteDetailDTO.getManufacturingdetailDTO().setProjectid(resultProjectdetail.getId());
        ManufacturingdetailDTO resultManufacturing = manufacturingdetailService.save(projectCompleteDetailDTO.getManufacturingdetailDTO());

        projectCompleteDetailDTO.getElectricrequirementDTO().setProjectid(resultProjectdetail.getId());
        ElectricrequirementDTO resultElectric = electricrequirementService.save(projectCompleteDetailDTO.getElectricrequirementDTO());

        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setInvestorid(resultInvestor.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setId(resultProjectdetail.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setCompanydetailid(resultCompany.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setProjectsitedetailid(resultSiteDetail.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setElectricityrequirementid(resultElectric.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setProjectfinanceid(resultFinance.getId());
        projectCompleteDetailDTO.getProjectdetailcombinecodesDTO().setManufacturingid(resultManufacturing.getId());
        ProjectdetailcombinecodesDTO resultCombineCodes = projectdetailcombinecodesService.save(projectCompleteDetailDTO.getProjectdetailcombinecodesDTO());

        return ResponseEntity.created(new URI("/api/CompleteProjectDetail/" + resultProjectdetail.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("CompleteProjectDetail", resultProjectdetail.getId().toString()))
            .body(projectCompleteDetailDTO);
    }

    @PutMapping("/CompleteProjectDetail")
    @Timed
    public ResponseEntity<ProjectCompleteDetailDTO> updateProjectdetail(@RequestBody ProjectCompleteDetailDTO projectCompleteDetailDTO) throws URISyntaxException {
        log.debug("REST request to update Complete Project Detail: {}", projectCompleteDetailDTO);
        if (projectCompleteDetailDTO.getProjectdetailDTO().getId() == null) {
            return createProjectCompleteDetail(projectCompleteDetailDTO);
        }
//        ProjectdetailDTO result = projectdetailService.save(projectCompleteDetailDTO);
        InvestorDTO resultInvestor = investorService.save(projectCompleteDetailDTO.getInvestorDTO());

//        projectCompleteDetailDTO.getCompanydetailDTO().setInvestorid(resultInvestor.getId());
        CompanydetailDTO resultCompany = companydetailService.save(projectCompleteDetailDTO.getCompanydetailDTO());

//        projectCompleteDetailDTO.getProjectdetailDTO().setInvestorid(resultInvestor.getId());
        ProjectdetailDTO resultProjectdetail = projectdetailService.save(projectCompleteDetailDTO.getProjectdetailDTO());

//        projectCompleteDetailDTO.getProjectsitedetailDTO().setProjectid(resultProjectdetail.getId());
        ProjectsitedetailDTO resultSiteDetail = projectsitedetailService.save(projectCompleteDetailDTO.getProjectsitedetailDTO());

//        projectCompleteDetailDTO.getProject_finance_investmentDTO().setProjectid(resultProjectdetail.getId());
        Project_finance_investmentDTO resultFinance = project_finance_investmentService.save(projectCompleteDetailDTO.getProject_finance_investmentDTO());

//        projectCompleteDetailDTO.getManufacturingdetailDTO().setProjectid(resultProjectdetail.getId());
        ManufacturingdetailDTO resultManufacturing = manufacturingdetailService.save(projectCompleteDetailDTO.getManufacturingdetailDTO());

//        projectCompleteDetailDTO.getElectricrequirementDTO().setProjectid(resultProjectdetail.getId());
        ElectricrequirementDTO resultElectric = electricrequirementService.save(projectCompleteDetailDTO.getElectricrequirementDTO());



        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Complete Project Detail", projectCompleteDetailDTO.getProjectdetailDTO().getId().toString()))
            .body(projectCompleteDetailDTO);
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
