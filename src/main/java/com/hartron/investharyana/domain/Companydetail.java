package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Companydetail.
 */

@Table(name = "companydetail")
public class Companydetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID investorid;

    private String promoter_md_director;

    private String designation;

    private String businessentity;

    private UUID businessentitytype;

    private Integer director_promoter_md_ceo_number;

    private String pan_number;

    private String aadhar_number;

    private Boolean nri;

    private String tin_vat_number;

    private String cst_number;

    private String director_md_ceo_list;

    private String pancard;

    private String aadharcard;

    private String tin_vat_document;

    private String cst_document;

    private String moa_partnershipdeed;

    private String registration_document;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInvestorid() {
        return investorid;
    }

    public Companydetail investorid(UUID investorid) {
        this.investorid = investorid;
        return this;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }

    public String getPromoter_md_director() {
        return promoter_md_director;
    }

    public Companydetail promoter_md_director(String promoter_md_director) {
        this.promoter_md_director = promoter_md_director;
        return this;
    }

    public void setPromoter_md_director(String promoter_md_director) {
        this.promoter_md_director = promoter_md_director;
    }

    public String getDesignation() {
        return designation;
    }

    public Companydetail designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBusinessentity() {
        return businessentity;
    }

    public Companydetail businessentity(String businessentity) {
        this.businessentity = businessentity;
        return this;
    }

    public void setBusinessentity(String businessentity) {
        this.businessentity = businessentity;
    }

    public UUID getBusinessentitytype() {
        return businessentitytype;
    }

    public Companydetail businessentitytype(UUID businessentitytype) {
        this.businessentitytype = businessentitytype;
        return this;
    }

    public void setBusinessentitytype(UUID businessentitytype) {
        this.businessentitytype = businessentitytype;
    }

    public Integer getDirector_promoter_md_ceo_number() {
        return director_promoter_md_ceo_number;
    }

    public Companydetail director_promoter_md_ceo_number(Integer director_promoter_md_ceo_number) {
        this.director_promoter_md_ceo_number = director_promoter_md_ceo_number;
        return this;
    }

    public void setDirector_promoter_md_ceo_number(Integer director_promoter_md_ceo_number) {
        this.director_promoter_md_ceo_number = director_promoter_md_ceo_number;
    }

    public String getPan_number() {
        return pan_number;
    }

    public Companydetail pan_number(String pan_number) {
        this.pan_number = pan_number;
        return this;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }

    public String getAadhar_number() {
        return aadhar_number;
    }

    public Companydetail aadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
        return this;
    }

    public void setAadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
    }

    public Boolean isNri() {
        return nri;
    }

    public Companydetail nri(Boolean nri) {
        this.nri = nri;
        return this;
    }

    public void setNri(Boolean nri) {
        this.nri = nri;
    }

    public String getTin_vat_number() {
        return tin_vat_number;
    }

    public Companydetail tin_vat_number(String tin_vat_number) {
        this.tin_vat_number = tin_vat_number;
        return this;
    }

    public void setTin_vat_number(String tin_vat_number) {
        this.tin_vat_number = tin_vat_number;
    }

    public String getCst_number() {
        return cst_number;
    }

    public Companydetail cst_number(String cst_number) {
        this.cst_number = cst_number;
        return this;
    }

    public void setCst_number(String cst_number) {
        this.cst_number = cst_number;
    }

    public String getDirector_md_ceo_list() {
        return director_md_ceo_list;
    }

    public Companydetail director_md_ceo_list(String director_md_ceo_list) {
        this.director_md_ceo_list = director_md_ceo_list;
        return this;
    }

    public void setDirector_md_ceo_list(String director_md_ceo_list) {
        this.director_md_ceo_list = director_md_ceo_list;
    }

    public String getPancard() {
        return pancard;
    }

    public Companydetail pancard(String pancard) {
        this.pancard = pancard;
        return this;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }

    public String getAadharcard() {
        return aadharcard;
    }

    public Companydetail aadharcard(String aadharcard) {
        this.aadharcard = aadharcard;
        return this;
    }

    public void setAadharcard(String aadharcard) {
        this.aadharcard = aadharcard;
    }

    public String getTin_vat_document() {
        return tin_vat_document;
    }

    public Companydetail tin_vat_document(String tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
        return this;
    }

    public void setTin_vat_document(String tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
    }

    public String getCst_document() {
        return cst_document;
    }

    public Companydetail cst_document(String cst_document) {
        this.cst_document = cst_document;
        return this;
    }

    public void setCst_document(String cst_document) {
        this.cst_document = cst_document;
    }

    public String getMoa_partnershipdeed() {
        return moa_partnershipdeed;
    }

    public Companydetail moa_partnershipdeed(String moa_partnershipdeed) {
        this.moa_partnershipdeed = moa_partnershipdeed;
        return this;
    }

    public void setMoa_partnershipdeed(String moa_partnershipdeed) {
        this.moa_partnershipdeed = moa_partnershipdeed;
    }

    public String getRegistration_document() {
        return registration_document;
    }

    public Companydetail registration_document(String registration_document) {
        this.registration_document = registration_document;
        return this;
    }

    public void setRegistration_document(String registration_document) {
        this.registration_document = registration_document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Companydetail companydetail = (Companydetail) o;
        if (companydetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, companydetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Companydetail{" +
            "id=" + id +
            ", investorid='" + investorid + "'" +
            ", promoter_md_director='" + promoter_md_director + "'" +
            ", designation='" + designation + "'" +
            ", businessentity='" + businessentity + "'" +
            ", businessentitytype='" + businessentitytype + "'" +
            ", director_promoter_md_ceo_number='" + director_promoter_md_ceo_number + "'" +
            ", pan_number='" + pan_number + "'" +
            ", aadhar_number='" + aadhar_number + "'" +
            ", nri='" + nri + "'" +
            ", tin_vat_number='" + tin_vat_number + "'" +
            ", cst_number='" + cst_number + "'" +
            ", director_md_ceo_list='" + director_md_ceo_list + "'" +
            ", pancard='" + pancard + "'" +
            ", aadharcard='" + aadharcard + "'" +
            ", tin_vat_document='" + tin_vat_document + "'" +
            ", cst_document='" + cst_document + "'" +
            ", moa_partnershipdeed='" + moa_partnershipdeed + "'" +
            ", registration_document='" + registration_document + "'" +
            '}';
    }
}
