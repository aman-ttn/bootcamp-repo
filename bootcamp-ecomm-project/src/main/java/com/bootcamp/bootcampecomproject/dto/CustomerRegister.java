package com.bootcamp.bootcampecomproject.dto;


import com.bootcamp.bootcampecomproject.entities.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class CustomerRegister {

    @Email
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    @Pattern(regexp="(^$|[0-9]{10})")
    private String contactNumber;
    @ValidPassword
    private String password;

    public CustomerRegister(@Email String email, String firstName, String middleName, String lastName, @Pattern(regexp = "(^$|[0-9]{10})") String contactNumber, String password) {
        this.email = email;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
