package com.hartron.investharyana.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
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
    private Environment_impactdetailDTO environment_impactdetailDTO;
    private ProjectdetailcombinecodesDTO projectdetailcombinecodesDTO;

    private List<Project_phaseDTO> project_phaseDTOList;
    private List<ProjectrawmaterialDTO> projectrawmaterialDTOList;
    private List<ProjectproductDTO> projectproductDTOList;
    private List<ProjectprocessflowstepsDTO> projectprocessflowstepsDTOList;
    private List<EmissiondetailDTO> emissiondetailDTOList;
    private List<WastewaterdetailDTO> wastewaterdetailDTOList;

    public List<Project_phaseDTO> getProject_phaseDTOList() {
        return project_phaseDTOList;
    }
    public void setProject_phaseDTOList(List<Project_phaseDTO> project_phaseDTOList) {
        this.project_phaseDTOList= project_phaseDTOList;
    }

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


    public List<ProjectrawmaterialDTO> getProjectrawmaterialDTOList() {
        return projectrawmaterialDTOList;
    }

    public void setProjectrawmaterialDTOList(List<ProjectrawmaterialDTO> projectrawmaterialDTOList) {
        this.projectrawmaterialDTOList = projectrawmaterialDTOList;
    }

    public List<ProjectproductDTO> getProjectproductDTOList() {
        return projectproductDTOList;
    }

    public void setProjectproductDTOList(List<ProjectproductDTO> projectproductDTOList) {
        this.projectproductDTOList = projectproductDTOList;
    }

    public List<ProjectprocessflowstepsDTO> getProjectprocessflowstepsDTOList() {
        return projectprocessflowstepsDTOList;
    }

    public void setProjectprocessflowstepsDTOList(List<ProjectprocessflowstepsDTO> projectprocessflowstepsDTOList) {
        this.projectprocessflowstepsDTOList = projectprocessflowstepsDTOList;
    }

    public List<EmissiondetailDTO> getEmissiondetailDTOList() {
        return emissiondetailDTOList;
    }

    public void setEmissiondetailDTOList(List<EmissiondetailDTO> emissiondetailDTOList) {
        this.emissiondetailDTOList = emissiondetailDTOList;
    }

    public List<WastewaterdetailDTO> getWastewaterdetailDTOList() {
        return wastewaterdetailDTOList;
    }

    public void setWastewaterdetailDTOList(List<WastewaterdetailDTO> wastewaterdetailDTOList) {
        this.wastewaterdetailDTOList = wastewaterdetailDTOList;
    }

    public Environment_impactdetailDTO getEnvironment_impactdetailDTO() {
        return environment_impactdetailDTO;
    }

    public void setEnvironment_impactdetailDTO(Environment_impactdetailDTO environment_impactdetailDTO) {
        this.environment_impactdetailDTO = environment_impactdetailDTO;
    }
}
