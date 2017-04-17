package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Investor entity.
 */
public class InvestorDTO implements Serializable {

    private UUID id;

    private Boolean mouapplicable;

    private Integer mousignyear;

    private String mouidnumber;

    private String firstname;

    private String middlename;

    private String lastname;

    private String address1;

    private String address2;

    private String address3;

    private String emailprimary;

    private String emailsecondary;

    private String userlogin;

    private String cityname;

    private String countryname;

    private String statename;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    private String cafpin;

    private String pincode;

    private String phonenumber;

    private String mobilenumber;

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
    public String getMouidnumber() {
        return mouidnumber;
    }

    public void setMouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
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
    public String getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }
    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }
    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
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
    public String getCafpin() {
        return cafpin;
    }

    public void setCafpin(String cafpin) {
        this.cafpin = cafpin;
    }
    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
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
            ", mouidnumber='" + mouidnumber + "'" +
            ", firstname='" + firstname + "'" +
            ", middlename='" + middlename + "'" +
            ", lastname='" + lastname + "'" +
            ", address1='" + address1 + "'" +
            ", address2='" + address2 + "'" +
            ", address3='" + address3 + "'" +
            ", emailprimary='" + emailprimary + "'" +
            ", emailsecondary='" + emailsecondary + "'" +
            ", userlogin='" + userlogin + "'" +
            ", cityname='" + cityname + "'" +
            ", countryname='" + countryname + "'" +
            ", statename='" + statename + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            ", cafpin='" + cafpin + "'" +
            ", pincode='" + pincode + "'" +
            ", phonenumber='" + phonenumber + "'" +
            ", mobilenumber='" + mobilenumber + "'" +
            '}';
    }
}
