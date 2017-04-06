package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.DepartmentService;
import com.hartron.investharyana.service.ProjectServiceReportInfoService;
import com.hartron.investharyana.service.dto.DepartmentDTO;
import com.hartron.investharyana.service.dto.DepartmentStatsDTO;
import com.hartron.investharyana.service.dto.ProjectServiceReportInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing Dashboard Department CAF.
 */
@RestController
@RequestMapping("/api")
public class DepartmentStatsResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentStatsResource.class);


    private final ProjectServiceReportInfoService projectServiceReportInfoService;

    private final DepartmentService departmentService;

    public DepartmentStatsResource(ProjectServiceReportInfoService projectServiceReportInfoService, DepartmentService departmentService) {
        this.projectServiceReportInfoService = projectServiceReportInfoService;
        this.departmentService = departmentService;
    }

    @GetMapping("/departmentstatscollection")
    @Timed
    public List<DepartmentStatsDTO> getAllDepartmentStats() {
        log.debug("REST request to get all DepartmentStats");



        List<DepartmentDTO> departmentDTOS= new ArrayList<>();
        departmentDTOS = departmentService.findAll();



        List<DepartmentStatsDTO> departmentStatsDTOList=new ArrayList<>();
        List<ProjectServiceReportInfoDTO> projectServiceReportInfoDTOList = new ArrayList<>();
        ProjectServiceReportInfoDTO projectServiceReportInfoDTO = new ProjectServiceReportInfoDTO();
        projectServiceReportInfoDTOList   = projectServiceReportInfoService.findAll();

            List<String> departNameList=new ArrayList<>();
            List<UUID> projectidList= new ArrayList<>();

            for(int i=0;i < projectServiceReportInfoDTOList.size();i++)
            {
                String departNameLocal;
                UUID projectidLocal;
                ProjectServiceReportInfoDTO projectServiceReportInfoDTO1= projectServiceReportInfoDTOList.get(i);
                departNameLocal= projectServiceReportInfoDTO1.getDepartmentname();
                projectidLocal          = projectServiceReportInfoDTO1.getProjectid();

               if(!(departNameList.contains(departNameLocal)))
                {
                    departNameList.add(departNameLocal);
                }

                if(!(projectidList.contains(projectidLocal)))
                {
                    projectidList.add(projectidLocal);

                }

            }

            System.out.println(departNameList);
            System.out.println(projectidList);


        Integer countElcc=0;
        Integer countDlcc=0;

        for(int deptNameL=0;deptNameL < departNameList.size();deptNameL++)
            {
                Integer totalcaf=0;
                Integer stageone=0;
                Integer stagetwo=0;
                Integer stagethree=0;
                Integer pendings=0;
                Double totalInvest=0.0;

                countElcc=0;
                countDlcc=0;

                DepartmentStatsDTO departmentStatsDTO=new DepartmentStatsDTO();

                String departNameLocal = departNameList.get(deptNameL);
                for(int projidL=0;projidL < projectidList.size();projidL++)
                {
                    String projectid = projectidList.get(projidL).toString();
                    totalInvest=0.0;

                    List<ProjectServiceReportInfoDTO> filteredArticleList= projectServiceReportInfoDTOList.stream().filter(article -> article.getDepartmentname().contains(departNameLocal)).filter(article -> article.getProjectid().toString().contains(projectid)) .collect(Collectors.toList());

                    if(filteredArticleList.size()>0)
                    {
                        totalcaf++;
                    }

                    if(filteredArticleList.size()>0) {
                        ProjectServiceReportInfoDTO projectServiceReportInfoDTO1 = filteredArticleList.get(0);
                        totalInvest = totalInvest + projectServiceReportInfoDTO1.getProjectInvestment();

                        if (totalInvest >= 10000000.0) {
                            countElcc++;
                        }
                        if (totalInvest < 10000000.0) {
                            countDlcc++;
                        }
                    }


                    List<ProjectServiceReportInfoDTO> filteredArticleListStageOne= projectServiceReportInfoDTOList.stream().filter(article -> article.getDepartmentname().contains(departNameLocal)).filter(article -> article.getProjectid().toString().contains(projectid)).filter(article -> article.getStage().contains("StageI")) .collect(Collectors.toList());

                    if(filteredArticleListStageOne.size()>0)
                    {
                        stageone++;
                    }

                    List<ProjectServiceReportInfoDTO> filteredArticleListStageTwo= projectServiceReportInfoDTOList.stream().filter(article -> article.getDepartmentname().contains(departNameLocal)).filter(article -> article.getProjectid().toString().contains(projectid)).filter(article -> article.getStage().contains("Stage-II")) .collect(Collectors.toList());

                    if(filteredArticleListStageTwo.size()>0)
                    {
                        stagetwo++;
                    }


                    List<ProjectServiceReportInfoDTO> filteredArticleListStageThree= projectServiceReportInfoDTOList.stream().filter(article -> article.getDepartmentname().contains(departNameLocal)).filter(article -> article.getProjectid().toString().contains(projectid)).filter(article -> article.getStage().contains("Stage-III")) .collect(Collectors.toList());

                    if(filteredArticleListStageThree.size()>0)
                    {
                        stagethree++;
                    }


                    List<ProjectServiceReportInfoDTO> filteredArticleListStatus= projectServiceReportInfoDTOList.stream().filter(article -> article.getDepartmentname().contains(departNameLocal)).filter(article -> article.getProjectid().toString().contains(projectid)).filter(article -> article.getStatus().contains("Pending")) .collect(Collectors.toList());

                    if(filteredArticleListStatus.size()>0)
                    {
                        pendings++;
                    }



                }
                System.out.println(totalcaf);

                departmentStatsDTO.setDepartmentName(departNameList.get(deptNameL));
                departmentStatsDTO.setTotalCaf(totalcaf.toString());
                departmentStatsDTO.setTotalPendingCaf(pendings.toString());
                departmentStatsDTO.setTotalStageOneCaf(stageone.toString());
                departmentStatsDTO.setTotalStageTwoCaf(stagetwo.toString());
                departmentStatsDTO.setTotalStageThreeCaf(stagethree.toString());
                departmentStatsDTO.setElcc(countElcc.toString());
                departmentStatsDTO.setDlcc(countDlcc.toString());

                departmentStatsDTOList.add(departmentStatsDTO);

            }







        return departmentStatsDTOList;
    }


}
