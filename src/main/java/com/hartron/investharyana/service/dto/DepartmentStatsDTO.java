package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DepartmentcafDTO for the Dashboard Department CAF
 */
public class DepartmentStatsDTO implements Serializable {
    private String departmentName;
    private String totalCaf;
    private String totalPendingCaf;
    private String totalStageOneCaf;
    private String totalStageTwoCaf;
    private String totalStageThreeCaf;

    private String elcc;
    private String dlcc;



    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTotalCaf() {
        return totalCaf;
    }

    public void setTotalCaf(String totalCaf) {
        this.totalCaf = totalCaf;
    }

    public String getTotalPendingCaf() {
        return totalPendingCaf;
    }

    public void setTotalPendingCaf(String totalPendingCaf) {
        this.totalPendingCaf = totalPendingCaf;
    }

    public String getTotalStageOneCaf() {
        return totalStageOneCaf;
    }

    public void setTotalStageOneCaf(String totalStageOneCaf) {
        this.totalStageOneCaf = totalStageOneCaf;
    }

    public String getTotalStageTwoCaf() {
        return totalStageTwoCaf;
    }

    public void setTotalStageTwoCaf(String totalStageTwoCaf) {
        this.totalStageTwoCaf = totalStageTwoCaf;
    }

    public String getTotalStageThreeCaf() {
        return totalStageThreeCaf;
    }

    public void setTotalStageThreeCaf(String totalStageThreeCaf) {
        this.totalStageThreeCaf = totalStageThreeCaf;
    }

    public String getElcc() {
        return elcc;
    }

    public void setElcc(String elcc) {
        this.elcc = elcc;
    }

    public String getDlcc() {
        return dlcc;
    }

    public void setDlcc(String dlcc) {
        this.dlcc = dlcc;
    }
}
