package com.bootcamp.bootcampecomproject.dtos;

import com.bootcamp.bootcampecomproject.entities.Address;
import com.bootcamp.bootcampecomproject.entities.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SellerRegisterDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    @ValidPassword
    private String password;
    @NotNull
    @Pattern(regexp="(\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1})")
    private String gst;
    @NotNull
    private String companyName;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String contactNumber;


    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGst() {
        return gst;
    }

    public SellerRegisterDto(@Email String email, String password, String gst, String companyName, @Pattern(regexp = "(^$|[0-9]{10})") String contactNumber) {
        this.email = email;
        this.password = password;
        this.gst = gst;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
/* 1)-First 2 digits of the GST Number will represent State Code as per the Census (2011).
 2)-Next 10 digits will be same as in the PAN number of the taxpayer.
        *-First five will be alphabets
        *-Next four will be numbers
        *-Last will be check code
 3)-The 13th digit will be the number of registration you take within a state i.e. after 9, A to Z is considered as 10 to 35 .
 4)-14th digit will be Z by default.
 5)-Last would be the check code.
 27AAPFU0939F1ZV -Smaple data
*/
