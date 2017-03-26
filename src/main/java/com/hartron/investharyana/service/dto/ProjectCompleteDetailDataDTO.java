package com.hartron.investharyana.service.dto;


import java.io.Serializable;

/**
 * A DTO for the ProjectCompleteDetail data entity.
 */
public class ProjectCompleteDetailDataDTO implements Serializable {

    private InvestorDataDTO investorDTO;
    private CompanydetailDataDTO companydetailDTO;
    private ProjectdetailDataDTO projectdetailDTO;
    private ProjectsitedetailDTO projectsitedetailDTO;
    private Project_finance_investmentDTO project_finance_investmentDTO;
    private ManufacturingdetailDTO manufacturingdetailDTO;
    private ElectricrequirementDTO electricrequirementDTO;
    private ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO;

    public InvestorDataDTO getInvestorDTO() {
        return investorDTO;
    }
    public void setInvestorDTO(InvestorDataDTO investorDTO) {
        this.investorDTO = investorDTO;
    }

    public CompanydetailDataDTO getCompanydetailDTO() {
        return companydetailDTO;
    }
    public void setCompanydetailDTO(CompanydetailDataDTO companydetailDTO) {
        this.companydetailDTO = companydetailDTO;
    }

    public ProjectdetailDataDTO getProjectdetailDTO() {
        return projectdetailDTO;
    }
    public void setProjectdetailDTO(ProjectdetailDataDTO projectdetailDTO) {
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