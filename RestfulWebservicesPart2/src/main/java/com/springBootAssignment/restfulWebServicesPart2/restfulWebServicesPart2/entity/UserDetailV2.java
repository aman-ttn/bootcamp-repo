package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This class is used in versioning.This consist of method which returns the object of UserDeatils.")
public class UserDetailV2 {
    private UserDeatils userDeatils;

    public UserDetailV2() {
    }

    public UserDeatils getUserDeatils() {
        return userDeatils;
    }

    public void setUserDeatils(UserDeatils userDeatils) {
        this.userDeatils = userDeatils;
    }

    public UserDetailV2(UserDeatils userDeatils) {
        this.userDeatils = userDeatils;
    }
}
