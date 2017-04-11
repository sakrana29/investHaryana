package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.*;
import com.hartron.investharyana.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final ProjectServiceReportInfoService projectServiceReportInfoService;


    public ProjectListResource(InvestorService investorService, ProjectdetailService projectdetailService, Project_finance_investmentService project_finance_investmentService, ProjectdetailcombinecodesService projectdetailcombinecodesService, ProjectServiceReportInfoService projectServiceReportInfoService) {
        this.investorService = investorService;
        this.projectdetailService = projectdetailService;
        this.project_finance_investmentService = project_finance_investmentService;
        this.projectdetailcombinecodesService = projectdetailcombinecodesService;
        this.projectServiceReportInfoService = projectServiceReportInfoService;
    }


    @GetMapping("/ProjectList")
    @Timed
    public List<ListofProjectsDTO> getProjectList() {
        log.debug("REST request to get complete Project List");
        List<ListofProjectsDTO> listofProjectsDTOList = new ArrayList<>();
        List<ProjectdetailcombinecodesDTO> projectdetailcombinecodesDTOList = projectdetailcombinecodesService.findAll();

        for (int projectlistcount = 0; projectlistcount < projectdetailcombinecodesDTOList.size(); projectlistcount++) {
            InvestorDTO investorDTO = investorService.findOne(projectdetailcombinecodesDTOList.get(projectlistcount).getInvestorid().toString());
            ProjectdetailDTO projectdetailDTO = projectdetailService.findOne(projectdetailcombinecodesDTOList.get(projectlistcount).getId().toString());
            Project_finance_investmentDTO project_finance_investmentDTO = project_finance_investmentService.findOne(projectdetailcombinecodesDTOList.get(projectlistcount).getProjectfinanceid().toString());

            ListofProjectsDTO listofProjectsDTO = new ListofProjectsDTO();
            listofProjectsDTO.setProjectid(projectdetailDTO.getId().toString());
            listofProjectsDTO.setCAFPin(investorDTO.getCafpin());
            listofProjectsDTO.setInvestorName(investorDTO.getFirstname());
            listofProjectsDTO.setProjectTotalCost(project_finance_investmentDTO.getTotal_project_cost());
            listofProjectsDTO.setProjectType(projectdetailDTO.getProjectype());
            listofProjectsDTO.setTotalEmployement(project_finance_investmentDTO.getTotalpurposedemployment());

            listofProjectsDTOList.add(listofProjectsDTO);
        }
        return listofProjectsDTOList;
    }


}
