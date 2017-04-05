package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ApprovalformsService;
import com.hartron.investharyana.service.ProjectServiceReportInfoService;
import com.hartron.investharyana.service.dto.ApprovalformsDTO;
import com.hartron.investharyana.service.dto.DepartmentStatsDTO;
import com.hartron.investharyana.service.dto.ProjectServiceReportInfoDTO;
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
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Dashboard Department CAF.
 */
@RestController
@RequestMapping("/api")
public class DepartmentStatsResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentStatsResource.class);


    private final ProjectServiceReportInfoService projectServiceReportInfoService;

    public DepartmentStatsResource(ProjectServiceReportInfoService projectServiceReportInfoService) {
        this.projectServiceReportInfoService = projectServiceReportInfoService;
    }

    @GetMapping("/departmentstatscollection")
    @Timed
    public List<DepartmentStatsDTO> getAllDepartmentStats() {
        log.debug("REST request to get all DepartmentStats");



        List<DepartmentStatsDTO> departmentStatsDTOList=new ArrayList<>();

        List<ProjectServiceReportInfoDTO> projectServiceReportInfoDTOList = new ArrayList<>();

        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = new ProjectServiceReportInfoDTO();

        projectServiceReportInfoDTOList   = projectServiceReportInfoService.findAll();
        List<UUID> projectidlist;
        List<ProjectServiceReportInfoDTO> filteredArticleList= projectServiceReportInfoDTOList.stream().filter(article -> article.getStatus().contains("Pending")).collect(Collectors.toList());

//        List<ProjectServiceReportInfoDTO> fil= projectServiceReportInfoDTOList.stream().filter(article -> article.getStatus().contains("Pending")).collect(Collectors.toList());
//        for(int reportinfordto=0;i<projectServiceReportInfoDTOList.size(); reportinfordto++) {
//            projectServiceReportInfoDTO = projectServiceReportInfoDTOList.get(reportinfordto);
//
//            if("Hafed" == projectServiceReportInfoDTO.getDepartmentname())
//            {
//                projectidlistprojectServiceReportInfoDTO.getProjectid();
//            }
//        }

        DepartmentStatsDTO departmentStatsDTO=new DepartmentStatsDTO();
        departmentStatsDTO.setDepartmentName("HAFED");
        departmentStatsDTO.setTotalCaf("89");
        departmentStatsDTO.setTotalPendingCaf("65");
        departmentStatsDTO.setTotalStageOneCaf("28");
        departmentStatsDTO.setTotalStageTwoCaf("12");
        departmentStatsDTO.setTotalStageThreeCaf("17");
        departmentStatsDTOList.add(departmentStatsDTO);

        DepartmentStatsDTO departmentStatsDTO1=new DepartmentStatsDTO();
        departmentStatsDTO1.setDepartmentName("Higher Education");
        departmentStatsDTO1.setTotalCaf("40");
        departmentStatsDTO1.setTotalPendingCaf("16");
        departmentStatsDTO1.setTotalStageOneCaf("34");
        departmentStatsDTO1.setTotalStageTwoCaf("17");
        departmentStatsDTO1.setTotalStageThreeCaf("60");
        departmentStatsDTOList.add(departmentStatsDTO1);

        DepartmentStatsDTO departmentStatsDTO2=new DepartmentStatsDTO();
        departmentStatsDTO2.setDepartmentName("HSAMV");
        departmentStatsDTO2.setTotalCaf("12");
        departmentStatsDTO2.setTotalPendingCaf("7");
        departmentStatsDTO2.setTotalStageOneCaf("3");
        departmentStatsDTO2.setTotalStageTwoCaf("1");
        departmentStatsDTO2.setTotalStageThreeCaf("3");
        departmentStatsDTOList.add(departmentStatsDTO2);

        DepartmentStatsDTO departmentStatsDTO3=new DepartmentStatsDTO();
        departmentStatsDTO3.setDepartmentName("Food & Supplies");
        departmentStatsDTO3.setTotalCaf("76");
        departmentStatsDTO3.setTotalPendingCaf("65");
        departmentStatsDTO3.setTotalStageOneCaf("28");
        departmentStatsDTO3.setTotalStageTwoCaf("11");
        departmentStatsDTO3.setTotalStageThreeCaf("19");
        departmentStatsDTOList.add(departmentStatsDTO3);

        DepartmentStatsDTO departmentStatsDTO4=new DepartmentStatsDTO();
        departmentStatsDTO4.setDepartmentName("Food & Supplies");
        departmentStatsDTO4.setTotalCaf("76");
        departmentStatsDTO4.setTotalPendingCaf("65");
        departmentStatsDTO4.setTotalStageOneCaf("28");
        departmentStatsDTO4.setTotalStageTwoCaf("11");
        departmentStatsDTO4.setTotalStageThreeCaf("19");
        departmentStatsDTOList.add(departmentStatsDTO4);

        return departmentStatsDTOList;
    }


}
