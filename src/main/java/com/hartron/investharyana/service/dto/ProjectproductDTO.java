package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectproduct entity.
 */
public class ProjectproductDTO implements Serializable {

    private UUID id;

    private String mainproduct;

    private Integer quantity;

    private String units;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    private UUID projectid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getMainproduct() {
        return mainproduct;
    }

    public void setMainproduct(String mainproduct) {
        this.mainproduct = mainproduct;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }
    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
    }
    public UUID getProjectid() {
        return projectid;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectproductDTO projectproductDTO = (ProjectproductDTO) o;

        if ( ! Objects.equals(id, projectproductDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectproductDTO{" +
            "id=" + id +
            ", mainproduct='" + mainproduct + "'" +
            ", quantity='" + quantity + "'" +
            ", units='" + units + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            ", projectid='" + projectid + "'" +
            '}';
    }
}
