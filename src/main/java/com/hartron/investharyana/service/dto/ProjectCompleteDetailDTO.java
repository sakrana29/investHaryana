package com.hartron.investharyana.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the ProjectCompleteDetail entity.
 */
public class ProjectCompleteDetailDTO implements Serializable {

    private InvestorDTO investorDTO;
    private CompanydetailDTO companydetailDTO;
    private ProjectdetailDTO projectdetailDTO;
    private ProjectsitedetailDTO projectsitedetailDTO;
    private Project_finance_investmentDTO project_finance_investmentDTO;
    private ManufacturingdetailDTO manufacturingdetailDTO;
    private ElectricrequirementDTO electricrequirementDTO;
    private ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO;

    public InvestorDTO getInvestorDTO() {
        return investorDTO;
    }
    public void setInvestorDTO(InvestorDTO investorDTO) {
        this.investorDTO = investorDTO;
    }

    public CompanydetailDTO getCompanydetailDTO() {
        return companydetailDTO;
    }
    public void setCompanydetailDTO(CompanydetailDTO companydetailDTO) {
        this.companydetailDTO = companydetailDTO;
    }

    public ProjectdetailDTO getProjectdetailDTO() {
        return projectdetailDTO;
    }
    public void setProjectdetailDTO(ProjectdetailDTO projectdetailDTO) {
        this.projectdetailDTO = projectdetailDTO;
    }

    public ProjectsitedetailDTO getProjectsitedetailDTO() {
        return projectsitedetailDTO;
    }
    public void setProjectsitedetailDTO(ProjectsitedetailDTO projectsitedetailDTO) {
        this.projectsitedetailDTO = projectsitedetailDTO;
    }

    public Project_finance_investmentDTO getProject_finance_investmentDTO() {
        return project_finance_investmentDTO;
    }
    public void setProject_finance_investmentDTO(Project_finance_investmentDTO project_finance_investmentDTO) {
        this.project_finance_investmentDTO = project_finance_investmentDTO;
    }

    public ManufacturingdetailDTO getManufacturingdetailDTO() {
        return manufacturingdetailDTO;
    }
    public void setManufacturingdetailDTO(ManufacturingdetailDTO manufacturingdetailDTO) {
        this.manufacturingdetailDTO = manufacturingdetailDTO;
    }

    public ElectricrequirementDTO getElectricrequirementDTO() {
        return electricrequirementDTO;
    }
    public void setElectricrequirementDTO(ElectricrequirementDTO electricrequirementDTO) {
        this.electricrequirementDTO = electricrequirementDTO;
    }

    public ProjectdetailcombinecodesDTO getProjectdetailcombinecodesDTO() {
        return projectdetailcombinecodesDTO;
    }
    public void setProjectdetailcombinecodesDTO(ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO) {
        this.projectdetailcombinecodesDTO = projectdetailcombinecodesDTO;
    }


}
