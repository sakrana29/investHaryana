package com.hartron.investharyana.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the DepartmentService entity.
 */
public class DepartmentServiceDetailsProjectWiseDTO implements Serializable {

    private UUID serviceid;

    @NotNull
    private String serviceName;

    @NotNull
    private String serviceDescription;

    private Integer duration;

    private String stage;

    private String departmentname;

    private UUID departmentid;

    private Boolean isrequired;

    private Boolean isassigned;

    private UUID id;

    public DepartmentServiceDetailsProjectWiseDTO() {
    }

    public UUID getServiceid() {
        return serviceid;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
    public UUID getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(UUID departmentid) {
        this.departmentid = departmentid;
    }

    public Boolean getIsassigned() {
        return isassigned;
    }

    public void setIsassigned(Boolean isassigned) {
        this.isassigned = isassigned;
    }


    public Boolean getIsrequired() {
        return isrequired;
    }
    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

}
