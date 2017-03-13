package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
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

    private ByteBuffer director_md_ceo_list;

    @Column(name = "director_md_ceo_list_content_type")
    private String director_md_ceo_listContentType;

    private String pan_number;

    private ByteBuffer pancard;

    @Column(name = "pancard_content_type")
    private String pancardContentType;

    private String aadhar_number;

    private ByteBuffer aadharcard;

    @Column(name = "aadharcard_content_type")
    private String aadharcardContentType;

    private Boolean nri;

    private String tin_vat_number;

    private ByteBuffer tin_vat_document;

    @Column(name = "tin_vat_document_content_type")
    private String tin_vat_documentContentType;

    private String cst_number;

    private ByteBuffer cst_document;

    @Column(name = "cst_document_content_type")
    private String cst_documentContentType;

    private ByteBuffer moa_partnershipdeed;

    @Column(name = "moa_partnershipdeed_content_type")
    private String moa_partnershipdeedContentType;

    private ByteBuffer registration_document;

    @Column(name = "registration_document_content_type")
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

    public ByteBuffer getDirector_md_ceo_list() {
        return director_md_ceo_list;
    }

    public Companydetail director_md_ceo_list(ByteBuffer director_md_ceo_list) {
        this.director_md_ceo_list = director_md_ceo_list;
        return this;
    }

    public void setDirector_md_ceo_list(ByteBuffer director_md_ceo_list) {
        this.director_md_ceo_list = director_md_ceo_list;
    }

    public String getDirector_md_ceo_listContentType() {
        return director_md_ceo_listContentType;
    }

    public Companydetail director_md_ceo_listContentType(String director_md_ceo_listContentType) {
        this.director_md_ceo_listContentType = director_md_ceo_listContentType;
        return this;
    }

    public void setDirector_md_ceo_listContentType(String director_md_ceo_listContentType) {
        this.director_md_ceo_listContentType = director_md_ceo_listContentType;
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

    public ByteBuffer getPancard() {
        return pancard;
    }

    public Companydetail pancard(ByteBuffer pancard) {
        this.pancard = pancard;
        return this;
    }

    public void setPancard(ByteBuffer pancard) {
        this.pancard = pancard;
    }

    public String getPancardContentType() {
        return pancardContentType;
    }

    public Companydetail pancardContentType(String pancardContentType) {
        this.pancardContentType = pancardContentType;
        return this;
    }

    public void setPancardContentType(String pancardContentType) {
        this.pancardContentType = pancardContentType;
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

    public ByteBuffer getAadharcard() {
        return aadharcard;
    }

    public Companydetail aadharcard(ByteBuffer aadharcard) {
        this.aadharcard = aadharcard;
        return this;
    }

    public void setAadharcard(ByteBuffer aadharcard) {
        this.aadharcard = aadharcard;
    }

    public String getAadharcardContentType() {
        return aadharcardContentType;
    }

    public Companydetail aadharcardContentType(String aadharcardContentType) {
        this.aadharcardContentType = aadharcardContentType;
        return this;
    }

    public void setAadharcardContentType(String aadharcardContentType) {
        this.aadharcardContentType = aadharcardContentType;
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

    public ByteBuffer getTin_vat_document() {
        return tin_vat_document;
    }

    public Companydetail tin_vat_document(ByteBuffer tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
        return this;
    }

    public void setTin_vat_document(ByteBuffer tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
    }

    public String getTin_vat_documentContentType() {
        return tin_vat_documentContentType;
    }

    public Companydetail tin_vat_documentContentType(String tin_vat_documentContentType) {
        this.tin_vat_documentContentType = tin_vat_documentContentType;
        return this;
    }

    public void setTin_vat_documentContentType(String tin_vat_documentContentType) {
        this.tin_vat_documentContentType = tin_vat_documentContentType;
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

    public ByteBuffer getCst_document() {
        return cst_document;
    }

    public Companydetail cst_document(ByteBuffer cst_document) {
        this.cst_document = cst_document;
        return this;
    }

    public void setCst_document(ByteBuffer cst_document) {
        this.cst_document = cst_document;
    }

    public String getCst_documentContentType() {
        return cst_documentContentType;
    }

    public Companydetail cst_documentContentType(String cst_documentContentType) {
        this.cst_documentContentType = cst_documentContentType;
        return this;
    }

    public void setCst_documentContentType(String cst_documentContentType) {
        this.cst_documentContentType = cst_documentContentType;
    }

    public ByteBuffer getMoa_partnershipdeed() {
        return moa_partnershipdeed;
    }

    public Companydetail moa_partnershipdeed(ByteBuffer moa_partnershipdeed) {
        this.moa_partnershipdeed = moa_partnershipdeed;
        return this;
    }

    public void setMoa_partnershipdeed(ByteBuffer moa_partnershipdeed) {
        this.moa_partnershipdeed = moa_partnershipdeed;
    }

    public String getMoa_partnershipdeedContentType() {
        return moa_partnershipdeedContentType;
    }

    public Companydetail moa_partnershipdeedContentType(String moa_partnershipdeedContentType) {
        this.moa_partnershipdeedContentType = moa_partnershipdeedContentType;
        return this;
    }

    public void setMoa_partnershipdeedContentType(String moa_partnershipdeedContentType) {
        this.moa_partnershipdeedContentType = moa_partnershipdeedContentType;
    }

    public ByteBuffer getRegistration_document() {
        return registration_document;
    }

    public Companydetail registration_document(ByteBuffer registration_document) {
        this.registration_document = registration_document;
        return this;
    }

    public void setRegistration_document(ByteBuffer registration_document) {
        this.registration_document = registration_document;
    }

    public String getRegistration_documentContentType() {
        return registration_documentContentType;
    }

    public Companydetail registration_documentContentType(String registration_documentContentType) {
        this.registration_documentContentType = registration_documentContentType;
        return this;
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
            ", director_md_ceo_list='" + director_md_ceo_list + "'" +
            ", director_md_ceo_listContentType='" + director_md_ceo_listContentType + "'" +
            ", pan_number='" + pan_number + "'" +
            ", pancard='" + pancard + "'" +
            ", pancardContentType='" + pancardContentType + "'" +
            ", aadhar_number='" + aadhar_number + "'" +
            ", aadharcard='" + aadharcard + "'" +
            ", aadharcardContentType='" + aadharcardContentType + "'" +
            ", nri='" + nri + "'" +
            ", tin_vat_number='" + tin_vat_number + "'" +
            ", tin_vat_document='" + tin_vat_document + "'" +
            ", tin_vat_documentContentType='" + tin_vat_documentContentType + "'" +
            ", cst_number='" + cst_number + "'" +
            ", cst_document='" + cst_document + "'" +
            ", cst_documentContentType='" + cst_documentContentType + "'" +
            ", moa_partnershipdeed='" + moa_partnershipdeed + "'" +
            ", moa_partnershipdeedContentType='" + moa_partnershipdeedContentType + "'" +
            ", registration_document='" + registration_document + "'" +
            ", registration_documentContentType='" + registration_documentContentType + "'" +
            '}';
    }
}
