package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectdetailcombinecodes.
 */

@Table(name = "projectdetailcombinecodes")
public class Projectdetailcombinecodes implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID investorid;

    private UUID companydetailid;

    private UUID projectsitedetailid;

    private UUID projectfinanceid;

    private UUID manufacturingid;

    private UUID electricityrequirementid;

    private UUID environmentimpactdetailid;

    private UUID termdeclarationacceptid;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInvestorid() {
        return investorid;
    }

    public Projectdetailcombinecodes investorid(UUID investorid) {
        this.investorid = investorid;
        return this;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }

    public UUID getCompanydetailid() {
        return companydetailid;
    }

    public Projectdetailcombinecodes companydetailid(UUID companydetailid) {
        this.companydetailid = companydetailid;
        return this;
    }

    public void setCompanydetailid(UUID companydetailid) {
        this.companydetailid = companydetailid;
    }

    public UUID getProjectsitedetailid() {
        return projectsitedetailid;
    }

    public Projectdetailcombinecodes projectsitedetailid(UUID projectsitedetailid) {
        this.projectsitedetailid = projectsitedetailid;
        return this;
    }

    public void setProjectsitedetailid(UUID projectsitedetailid) {
        this.projectsitedetailid = projectsitedetailid;
    }

    public UUID getProjectfinanceid() {
        return projectfinanceid;
    }

    public Projectdetailcombinecodes projectfinanceid(UUID projectfinanceid) {
        this.projectfinanceid = projectfinanceid;
        return this;
    }

    public void setProjectfinanceid(UUID projectfinanceid) {
        this.projectfinanceid = projectfinanceid;
    }

    public UUID getManufacturingid() {
        return manufacturingid;
    }

    public Projectdetailcombinecodes manufacturingid(UUID manufacturingid) {
        this.manufacturingid = manufacturingid;
        return this;
    }

    public void setManufacturingid(UUID manufacturingid) {
        this.manufacturingid = manufacturingid;
    }

    public UUID getElectricityrequirementid() {
        return electricityrequirementid;
    }

    public Projectdetailcombinecodes electricityrequirementid(UUID electricityrequirementid) {
        this.electricityrequirementid = electricityrequirementid;
        return this;
    }

    public void setElectricityrequirementid(UUID electricityrequirementid) {
        this.electricityrequirementid = electricityrequirementid;
    }

    public UUID getEnvironmentimpactdetailid() {
        return environmentimpactdetailid;
    }

    public Projectdetailcombinecodes environmentimpactdetailid(UUID environmentimpactdetailid) {
        this.environmentimpactdetailid = environmentimpactdetailid;
        return this;
    }

    public void setEnvironmentimpactdetailid(UUID environmentimpactdetailid) {
        this.environmentimpactdetailid = environmentimpactdetailid;
    }

    public UUID getTermdeclarationacceptid() {
        return termdeclarationacceptid;
    }

    public Projectdetailcombinecodes termdeclarationacceptid(UUID termdeclarationacceptid) {
        this.termdeclarationacceptid = termdeclarationacceptid;
        return this;
    }

    public void setTermdeclarationacceptid(UUID termdeclarationacceptid) {
        this.termdeclarationacceptid = termdeclarationacceptid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectdetailcombinecodes projectdetailcombinecodes = (Projectdetailcombinecodes) o;
        if (projectdetailcombinecodes.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectdetailcombinecodes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectdetailcombinecodes{" +
            "id=" + id +
            ", investorid='" + investorid + "'" +
            ", companydetailid='" + companydetailid + "'" +
            ", projectsitedetailid='" + projectsitedetailid + "'" +
            ", projectfinanceid='" + projectfinanceid + "'" +
            ", manufacturingid='" + manufacturingid + "'" +
            ", electricityrequirementid='" + electricityrequirementid + "'" +
            ", environmentimpactdetailid='" + environmentimpactdetailid + "'" +
            ", termdeclarationacceptid='" + termdeclarationacceptid + "'" +
            '}';
    }
}
