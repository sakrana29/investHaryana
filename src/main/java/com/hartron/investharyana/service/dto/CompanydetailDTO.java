package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Companydetail entity.
 */
public class CompanydetailDTO implements Serializable {

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

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }
    public String getPromoter_md_director() {
        return promoter_md_director;
    }

    public void setPromoter_md_director(String promoter_md_director) {
        this.promoter_md_director = promoter_md_director;
    }
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getBusinessentity() {
        return businessentity;
    }

    public void setBusinessentity(String businessentity) {
        this.businessentity = businessentity;
    }
    public UUID getBusinessentitytype() {
        return businessentitytype;
    }

    public void setBusinessentitytype(UUID businessentitytype) {
        this.businessentitytype = businessentitytype;
    }
    public Integer getDirector_promoter_md_ceo_number() {
        return director_promoter_md_ceo_number;
    }

    public void setDirector_promoter_md_ceo_number(Integer director_promoter_md_ceo_number) {
        this.director_promoter_md_ceo_number = director_promoter_md_ceo_number;
    }
    public String getPan_number() {
        return pan_number;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }
    public String getAadhar_number() {
        return aadhar_number;
    }

    public void setAadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
    }
    public Boolean getNri() {
        return nri;
    }

    public void setNri(Boolean nri) {
        this.nri = nri;
    }
    public String getTin_vat_number() {
        return tin_vat_number;
    }

    public void setTin_vat_number(String tin_vat_number) {
        this.tin_vat_number = tin_vat_number;
    }
    public String getCst_number() {
        return cst_number;
    }

    public void setCst_number(String cst_number) {
        this.cst_number = cst_number;
    }
    public String getDirector_md_ceo_list() {
        return director_md_ceo_list;
    }

    public void setDirector_md_ceo_list(String director_md_ceo_list) {
        this.director_md_ceo_list = director_md_ceo_list;
    }
    public String getPancard() {
        return pancard;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }
    public String getAadharcard() {
        return aadharcard;
    }

    public void setAadharcard(String aadharcard) {
        this.aadharcard = aadharcard;
    }
    public String getTin_vat_document() {
        return tin_vat_document;
    }

    public void setTin_vat_document(String tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
    }
    public String getCst_document() {
        return cst_document;
    }

    public void setCst_document(String cst_document) {
        this.cst_document = cst_document;
    }
    public String getMoa_partnershipdeed() {
        return moa_partnershipdeed;
    }

    public void setMoa_partnershipdeed(String moa_partnershipdeed) {
        this.moa_partnershipdeed = moa_partnershipdeed;
    }
    public String getRegistration_document() {
        return registration_document;
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

        CompanydetailDTO companydetailDTO = (CompanydetailDTO) o;

        if ( ! Objects.equals(id, companydetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CompanydetailDTO{" +
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
