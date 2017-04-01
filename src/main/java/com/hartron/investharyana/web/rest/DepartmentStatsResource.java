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

        DepartmentStatsDTO departmentStatsDTO=new DepartmentStatsDTO();

        List<DepartmentStatsDTO> departmentStatsDTOList=new ArrayList<>();

        List<ProjectServiceReportInfoDTO> projectServiceReportInfoDTOList = new ArrayList<>();

        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = new ProjectServiceReportInfoDTO();

        projectServiceReportInfoDTOList   = projectServiceReportInfoService.findAll();

        List<UUID> projectidlist;

//        for(int reportinfordto=0;i<projectServiceReportInfoDTOList.size(); reportinfordto++) {
//            projectServiceReportInfoDTO = projectServiceReportInfoDTOList.get(reportinfordto);
//
//            if("Hafed" == projectServiceReportInfoDTO.getDepartmentname())
//            {
//                projectidlistprojectServiceReportInfoDTO.getProjectid();
//            }

//        }




        departmentStatsDTO.setDepartmentName("HAFED");
        departmentStatsDTO.setTotalCaf("89");
        departmentStatsDTO.setTotalPendingCaf("65");
        departmentStatsDTO.setTotalStageOneCaf("28");
        departmentStatsDTO.setTotalStageTwoCaf("12");
        departmentStatsDTO.setTotalStageThreeCaf("17");

        departmentStatsDTOList.add(departmentStatsDTO);



        return departmentStatsDTOList;
    }


}
