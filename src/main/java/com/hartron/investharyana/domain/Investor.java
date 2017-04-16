package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Investor.
 */

@Table(name = "investor")
public class Investor implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    private Integer pincode;

    private Double phonenumber;

    private Double mobilenumber;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    private String cafpin;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean isMouapplicable() {
        return mouapplicable;
    }

    public Investor mouapplicable(Boolean mouapplicable) {
        this.mouapplicable = mouapplicable;
        return this;
    }

    public void setMouapplicable(Boolean mouapplicable) {
        this.mouapplicable = mouapplicable;
    }

    public Integer getMousignyear() {
        return mousignyear;
    }

    public Investor mousignyear(Integer mousignyear) {
        this.mousignyear = mousignyear;
        return this;
    }

    public void setMousignyear(Integer mousignyear) {
        this.mousignyear = mousignyear;
    }

    public String getMouidnumber() {
        return mouidnumber;
    }

    public Investor mouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
        return this;
    }

    public void setMouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public Investor firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public Investor middlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public Investor lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress1() {
        return address1;
    }

    public Investor address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public Investor address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public Investor address3(String address3) {
        this.address3 = address3;
        return this;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getEmailprimary() {
        return emailprimary;
    }

    public Investor emailprimary(String emailprimary) {
        this.emailprimary = emailprimary;
        return this;
    }

    public void setEmailprimary(String emailprimary) {
        this.emailprimary = emailprimary;
    }

    public String getEmailsecondary() {
        return emailsecondary;
    }

    public Investor emailsecondary(String emailsecondary) {
        this.emailsecondary = emailsecondary;
        return this;
    }

    public void setEmailsecondary(String emailsecondary) {
        this.emailsecondary = emailsecondary;
    }

    public String getUserlogin() {
        return userlogin;
    }

    public Investor userlogin(String userlogin) {
        this.userlogin = userlogin;
        return this;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    public String getCityname() {
        return cityname;
    }

    public Investor cityname(String cityname) {
        this.cityname = cityname;
        return this;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCountryname() {
        return countryname;
    }

    public Investor countryname(String countryname) {
        this.countryname = countryname;
        return this;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getStatename() {
        return statename;
    }

    public Investor statename(String statename) {
        this.statename = statename;
        return this;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public Integer getPincode() {
        return pincode;
    }

    public Investor pincode(Integer pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Double getPhonenumber() {
        return phonenumber;
    }

    public Investor phonenumber(Double phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public void setPhonenumber(Double phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Double getMobilenumber() {
        return mobilenumber;
    }

    public Investor mobilenumber(Double mobilenumber) {
        this.mobilenumber = mobilenumber;
        return this;
    }

    public void setMobilenumber(Double mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Investor createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Investor updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
    }

    public String getCafpin() {
        return cafpin;
    }

    public Investor cafpin(String cafpin) {
        this.cafpin = cafpin;
        return this;
    }

    public void setCafpin(String cafpin) {
        this.cafpin = cafpin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Investor investor = (Investor) o;
        if (investor.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, investor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Investor{" +
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
            ", pincode='" + pincode + "'" +
            ", phonenumber='" + phonenumber + "'" +
            ", mobilenumber='" + mobilenumber + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            ", cafpin='" + cafpin + "'" +
            '}';
    }
}
