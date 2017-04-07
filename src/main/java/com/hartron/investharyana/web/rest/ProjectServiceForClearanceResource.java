package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.DepartmentServiceService;
import com.hartron.investharyana.service.ProjectservicedetailService;
import com.hartron.investharyana.service.dto.DepartmentServiceDTO;
import com.hartron.investharyana.service.dto.ProjectservicedetailDTO;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.concurrent.TimeUnit.DAYS;

/**
 * REST controller for managing Projectservicedetail.
 */
@RestController
@RequestMapping("/api")
public class ProjectServiceForClearanceResource {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceForClearanceResource.class);

    private static final String ENTITY_NAME = "projectservicedetail";

    private final ProjectservicedetailService projectservicedetailService;
    private final DepartmentServiceService departmentServiceService;

    public ProjectServiceForClearanceResource(ProjectservicedetailService projectservicedetailService, DepartmentServiceService departmentServiceService) {
        this.projectservicedetailService = projectservicedetailService;
        this.departmentServiceService = departmentServiceService;
    }

    /**
     * GET  /projectservicedetails/:id : get the "id" projectservicedetail.
     *
     * @param id the id of the projectservicedetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectservicedetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectservicelistforclearance/project/{id}")
    @Timed
    public List<ProjectservicedetailDTO> getProjectServiceForClearance(@PathVariable String id) {
        log.debug("REST request to get Projectservicedetail : {}", id);
        List<DepartmentServiceDTO> departmentServiceDTOList=departmentServiceService.findAll();
        List<ProjectservicedetailDTO> projectservicedetailDTOList= projectservicedetailService.findAllByProjectid(id);
        List<ProjectservicedetailDTO> projectServiceClearanceList= new ArrayList<>();

            for(int counterService=0;counterService<departmentServiceDTOList.size();counterService++)
            {
                boolean flag=false;
                for(int projectcounter=0;projectcounter<projectservicedetailDTOList.size();projectcounter++)
                {
                    if(projectservicedetailDTOList.get(projectcounter).getServiceid() == departmentServiceDTOList.get(projectcounter).getId())
                    {
//                        boolean isdimmed= projectservicedetailDTOList.get(projectcounter).getIsDimmed();
                        if(projectservicedetailDTOList.get(projectcounter).getAssigOnDate() != null)
                        {
                            ZonedDateTime assignDate = projectservicedetailDTOList.get(projectcounter).getAssigOnDate();
                            LocalDate assignedOn=assignDate.toLocalDate();
                            LocalDate today=LocalDate.now();
                            int days= ((int) assignedOn.until(today, ChronoUnit.DAYS));
                            System.out.print(days);
                            if(days>projectservicedetailDTOList.get(projectcounter).getServiceDuration())
                            projectservicedetailDTOList.get(projectcounter).setIsDimmed(true);
                        }
                        else
                            projectservicedetailDTOList.get(projectcounter).setIsDimmed(false);

                        projectServiceClearanceList.add(projectservicedetailDTOList.get(projectcounter));
                        flag=true;
                        break;
                    }
                }
                if(flag==false)
                {
                    ProjectservicedetailDTO ProjectservicedetailDTO=new ProjectservicedetailDTO();
                    ProjectservicedetailDTO.setProjectid(UUID.fromString(id));
                    ProjectservicedetailDTO.setServiceid(departmentServiceDTOList.get(counterService).getId());
                    ProjectservicedetailDTO.setServiceName(departmentServiceDTOList.get(counterService).getServiceName());
                    ProjectservicedetailDTO.setDepartmentName(departmentServiceDTOList.get(counterService).getDepartmentname());
                    ProjectservicedetailDTO.setServiceStage(departmentServiceDTOList.get(counterService).getStage());
                    ProjectservicedetailDTO.setServiceDuration(departmentServiceDTOList.get(counterService).getDuration());
                    ProjectservicedetailDTO.setStatus("Pending");
                    ProjectservicedetailDTO.setIsRequired(false);
                    ProjectservicedetailDTO.setIsAssigned(false);
                    ProjectservicedetailDTO.setFormFilledStatus(false);
                    ProjectservicedetailDTO.setIsPaymentMade(false);
                    ProjectservicedetailDTO.setIsPaymentVerified(false);
                    ProjectservicedetailDTO.setIsDimmed(false);
                    projectServiceClearanceList.add(ProjectservicedetailDTO);
                }
            }
        return projectServiceClearanceList;
    }
}
