package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.nio.ByteBuffer;
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

    private ByteBuffer director_md_ceo_list;
    private String director_md_ceo_listContentType;

    private String pan_number;

    private ByteBuffer pancard;
    private String pancardContentType;

    private String aadhar_number;

    private ByteBuffer aadharcard;
    private String aadharcardContentType;

    private Boolean nri;

    private String tin_vat_number;

    private ByteBuffer tin_vat_document;
    private String tin_vat_documentContentType;

    private String cst_number;

    private ByteBuffer cst_document;
    private String cst_documentContentType;

    private ByteBuffer moa_partnershipdeed;
    private String moa_partnershipdeedContentType;

    private ByteBuffer registration_document;
    private String registration_documentContentType;

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
    public ByteBuffer getDirector_md_ceo_list() {
        return director_md_ceo_list;
    }

    public void setDirector_md_ceo_list(ByteBuffer director_md_ceo_list) {
        this.director_md_ceo_list = director_md_ceo_list;
    }

    public String getDirector_md_ceo_listContentType() {
        return director_md_ceo_listContentType;
    }

    public void setDirector_md_ceo_listContentType(String director_md_ceo_listContentType) {
        this.director_md_ceo_listContentType = director_md_ceo_listContentType;
    }
    public String getPan_number() {
        return pan_number;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }
    public ByteBuffer getPancard() {
        return pancard;
    }

    public void setPancard(ByteBuffer pancard) {
        this.pancard = pancard;
    }

    public String getPancardContentType() {
        return pancardContentType;
    }

    public void setPancardContentType(String pancardContentType) {
        this.pancardContentType = pancardContentType;
    }
    public String getAadhar_number() {
        return aadhar_number;
    }

    public void setAadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
    }
    public ByteBuffer getAadharcard() {
        return aadharcard;
    }

    public void setAadharcard(ByteBuffer aadharcard) {
        this.aadharcard = aadharcard;
    }

    public String getAadharcardContentType() {
        return aadharcardContentType;
    }

    public void setAadharcardContentType(String aadharcardContentType) {
        this.aadharcardContentType = aadharcardContentType;
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
    public ByteBuffer getTin_vat_document() {
        return tin_vat_document;
    }

    public void setTin_vat_document(ByteBuffer tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
    }

    public String getTin_vat_documentContentType() {
        return tin_vat_documentContentType;
    }

    public void setTin_vat_documentContentType(String tin_vat_documentContentType) {
        this.tin_vat_documentContentType = tin_vat_documentContentType;
    }
    public String getCst_number() {
        return cst_number;
    }

    public void setCst_number(String cst_number) {
        this.cst_number = cst_number;
    }
    public ByteBuffer getCst_document() {
        return cst_document;
    }

    public void setCst_document(ByteBuffer cst_document) {
        this.cst_document = cst_document;
    }

    public String getCst_documentContentType() {
        return cst_documentContentType;
    }

    public void setCst_documentContentType(String cst_documentContentType) {
        this.cst_documentContentType = cst_documentContentType;
    }
    public ByteBuffer getMoa_partnershipdeed() {
        return moa_partnershipdeed;
    }

    public void setMoa_partnershipdeed(ByteBuffer moa_partnershipdeed) {
        this.moa_partnershipdeed = moa_partnershipdeed;
    }

    public String getMoa_partnershipdeedContentType() {
        return moa_partnershipdeedContentType;
    }

    public void setMoa_partnershipdeedContentType(String moa_partnershipdeedContentType) {
        this.moa_partnershipdeedContentType = moa_partnershipdeedContentType;
    }
    public ByteBuffer getRegistration_document() {
        return registration_document;
    }

    public void setRegistration_document(ByteBuffer registration_document) {
        this.registration_document = registration_document;
    }

    public String getRegistration_documentContentType() {
        return registration_documentContentType;
    }

    public void setRegistration_documentContentType(String registration_documentContentType) {
        this.registration_documentContentType = registration_documentContentType;
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
            ", director_md_ceo_list='" + director_md_ceo_list + "'" +
            ", pan_number='" + pan_number + "'" +
            ", pancard='" + pancard + "'" +
            ", aadhar_number='" + aadhar_number + "'" +
            ", aadharcard='" + aadharcard + "'" +
            ", nri='" + nri + "'" +
            ", tin_vat_number='" + tin_vat_number + "'" +
            ", tin_vat_document='" + tin_vat_document + "'" +
            ", cst_number='" + cst_number + "'" +
            ", cst_document='" + cst_document + "'" +
            ", moa_partnershipdeed='" + moa_partnershipdeed + "'" +
            ", registration_document='" + registration_document + "'" +
            '}';
    }
}
