package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This class is used in versioning.It take name and email in its constructor.")
public class    UserDetailV1 {

    String name;
    String email;
    public UserDetailV1(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserDetailV1() {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
