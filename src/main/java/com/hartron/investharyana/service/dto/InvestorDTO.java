package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Investor entity.
 */
public class InvestorDTO implements Serializable {

    private UUID id;

    private Boolean mouapplicable;

    private Integer mousignyear;

    private ByteBuffer moudocument;
    private String moudocumentContentType;

    private String mouidnumber;

    private ByteBuffer photo;
    private String photoContentType;

    private String firstname;

    private String middlename;

    private String lastname;

    private UUID countryid;

    private UUID stateid;

    private UUID cityid;

    private String address1;

    private String address2;

    private String address3;

    private String emailprimary;

    private String emailsecondary;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public Boolean getMouapplicable() {
        return mouapplicable;
    }

    public void setMouapplicable(Boolean mouapplicable) {
        this.mouapplicable = mouapplicable;
    }
    public Integer getMousignyear() {
        return mousignyear;
    }

    public void setMousignyear(Integer mousignyear) {
        this.mousignyear = mousignyear;
    }
    public ByteBuffer getMoudocument() {
        return moudocument;
    }

    public void setMoudocument(ByteBuffer moudocument) {
        this.moudocument = moudocument;
    }

    public String getMoudocumentContentType() {
        return moudocumentContentType;
    }

    public void setMoudocumentContentType(String moudocumentContentType) {
        this.moudocumentContentType = moudocumentContentType;
    }
    public String getMouidnumber() {
        return mouidnumber;
    }

    public void setMouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
    }
    public ByteBuffer getPhoto() {
        return photo;
    }

    public void setPhoto(ByteBuffer photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public UUID getCountryid() {
        return countryid;
    }

    public void setCountryid(UUID countryid) {
        this.countryid = countryid;
    }
    public UUID getStateid() {
        return stateid;
    }

    public void setStateid(UUID stateid) {
        this.stateid = stateid;
    }
    public UUID getCityid() {
        return cityid;
    }

    public void setCityid(UUID cityid) {
        this.cityid = cityid;
    }
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }
    public String getEmailprimary() {
        return emailprimary;
    }

    public void setEmailprimary(String emailprimary) {
        this.emailprimary = emailprimary;
    }
    public String getEmailsecondary() {
        return emailsecondary;
    }

    public void setEmailsecondary(String emailsecondary) {
        this.emailsecondary = emailsecondary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestorDTO investorDTO = (InvestorDTO) o;

        if ( ! Objects.equals(id, investorDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InvestorDTO{" +
            "id=" + id +
            ", mouapplicable='" + mouapplicable + "'" +
            ", mousignyear='" + mousignyear + "'" +
            ", moudocument='" + moudocument + "'" +
            ", mouidnumber='" + mouidnumber + "'" +
            ", photo='" + photo + "'" +
            ", firstname='" + firstname + "'" +
            ", middlename='" + middlename + "'" +
            ", lastname='" + lastname + "'" +
            ", countryid='" + countryid + "'" +
            ", stateid='" + stateid + "'" +
            ", cityid='" + cityid + "'" +
            ", address1='" + address1 + "'" +
            ", address2='" + address2 + "'" +
            ", address3='" + address3 + "'" +
            ", emailprimary='" + emailprimary + "'" +
            ", emailsecondary='" + emailsecondary + "'" +
            '}';
    }
}
