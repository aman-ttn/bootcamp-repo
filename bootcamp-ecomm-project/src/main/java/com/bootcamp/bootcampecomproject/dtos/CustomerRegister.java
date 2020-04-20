package com.bootcamp.bootcampecomproject.dtos;


import com.bootcamp.bootcampecomproject.entities.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerRegister {

    @Email
    private String email;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    @Pattern(regexp="(^$|[0-9]{10})",message = "Only 10 digit Contact Number is allowed")
    private String contactNumber;
    @NotNull
    @ValidPassword
    private String password;
    @NotNull
    @ValidPassword
    private String confirmPassword;

    public CustomerRegister(@Email String email, String firstName, String middleName, String lastName, @Pattern(regexp = "(^$|[0-9]{10})", message = "Only 10 digit Contact Number is allowed") String contactNumber, String password, String confirmPassword) {
        this.email = email;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
