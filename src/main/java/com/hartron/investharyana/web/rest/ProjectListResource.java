package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.*;
import com.hartron.investharyana.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Project List.
 */
@RestController
@RequestMapping("/api")
public class ProjectListResource {

    private final Logger log = LoggerFactory.getLogger(ProjectListResource.class);

//    private static final String ENTITY_NAME = "block";

    private final InvestorService investorService;
    private final ProjectdetailService projectdetailService;
    private final Project_finance_investmentService project_finance_investmentService;
    private final ProjectdetailcombinecodesService projectdetailcombinecodesService;
    private final ProjectsitedetailService projectsitedetailService;
    private final CompanydetailService companydetailService;

    public ProjectListResource(InvestorService investorService, ProjectdetailService projectdetailService, Project_finance_investmentService project_finance_investmentService, ProjectdetailcombinecodesService projectdetailcombinecodesService, ProjectsitedetailService projectsitedetailService, CompanydetailService companydetailService) {
        this.investorService = investorService;
        this.projectdetailService = projectdetailService;
        this.project_finance_investmentService = project_finance_investmentService;
        this.projectdetailcombinecodesService = projectdetailcombinecodesService;
        this.projectsitedetailService = projectsitedetailService;
        this.companydetailService = companydetailService;
    }

    @GetMapping("/ProjectList")
    @Timed
    public List<ListofProjectsDTO> getProjectList() {
        log.debug("REST request to get complete Project List");
        List<ListofProjectsDTO> listofProjectsDTOList=new ArrayList<>();
        List<ProjectdetailcombinecodesDTO> projectdetailcombinecodesDTOList= projectdetailcombinecodesService.findAll();

        for(int projectlistcount =0; projectlistcount<projectdetailcombinecodesDTOList.size();projectlistcount++)
        {
            InvestorDTO investorDTO= investorService.findOne(projectdetailcombinecodesDTOList.get(projectlistcount).getInvestorid().toString());
            ProjectdetailDTO projectdetailDTO= projectdetailService.findOne(projectdetailcombinecodesDTOList.get(projectlistcount).getId().toString());
            Project_finance_investmentDTO project_finance_investmentDTO= project_finance_investmentService.findOne(projectdetailcombinecodesDTOList.get(projectlistcount).getProjectfinanceid().toString());
            CompanydetailDTO companydetailDTO = companydetailService.findOne(projectdetailcombinecodesDTOList.get(projectlistcount).getCompanydetailid().toString());
            ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailService.findOne(projectdetailcombinecodesDTOList.get(projectlistcount).getProjectsitedetailid().toString());

            ListofProjectsDTO listofProjectsDTO=new ListofProjectsDTO();
            listofProjectsDTO.setProjectid(projectdetailDTO.getId().toString());
            listofProjectsDTO.setCAFPin(investorDTO.getCafpin());
            listofProjectsDTO.setInvestorName(investorDTO.getFirstname());
            listofProjectsDTO.setProjectTotalCost(project_finance_investmentDTO.getTotal_project_cost());
            listofProjectsDTO.setProjectType(projectdetailDTO.getProjectype());
            listofProjectsDTO.setTotalEmployement(project_finance_investmentDTO.getTotalpurposedemployment());

            listofProjectsDTO.setMouYear(investorDTO.getMousignyear());
            listofProjectsDTO.setApplicationDate(investorDTO.getCreatedate());
            listofProjectsDTO.setSectorName(projectdetailDTO.getSectorname());

            listofProjectsDTO.setSectorName(projectdetailDTO.getSectorname());

            listofProjectsDTO.setSiteaddress(projectsitedetailDTO.getSiteaddress());
            listofProjectsDTO.setBlock(projectsitedetailDTO.getBlock());
            listofProjectsDTO.setDistrict(projectsitedetailDTO.getDistrict());
            listofProjectsDTO.setBuisnessEntity(companydetailDTO.getBusinessentity());

            listofProjectsDTOList.add(listofProjectsDTO);
        }
        return listofProjectsDTOList;
    }
}
