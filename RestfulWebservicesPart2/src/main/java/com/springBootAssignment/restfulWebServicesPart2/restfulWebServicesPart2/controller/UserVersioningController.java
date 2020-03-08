package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.controller;

import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity.UserDeatils;
import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity.UserDetailV1;
import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity.UserDetailV2;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.java2d.marlin.Version;

@RestController
@ApiModel(description = "This is controller used for versioning.This contains of uri,request parameter,custom header and mime type of versioning.")

public class UserVersioningController {


//    -----------------URI Versioning-----------------
    @GetMapping(path="/v1/user")
    public UserDetailV1 userDetailV1(){
        return new UserDetailV1("Aman","aman.saini@tothenew.com");
    }
    @GetMapping(path="/v2/user")
    public UserDetailV2 userDetailV2(){
        return new UserDetailV2(new UserDeatils("Aman","aman.saini@tothenew.com",1,29));
    }

//    -----------------Request Parameter versioning------------------
    @GetMapping(value = "/user/param",params = "version=1")
    public UserDetailV1 paramV1(){
        return new UserDetailV1("Aman","aman.saini@tothenew.com");
    }


    @GetMapping(value = "/user/param",params = "version=2")
    public UserDetailV2 paramV2(){
        return new UserDetailV2(new UserDeatils("Aman","aman.saini@tothenew.com",1,29));
    }


    //    -------------------Custom Header Versioning--------------------
    @GetMapping(value = "/user/header",headers = "API-VERSION=1")
    public UserDetailV1 headerV1(){
        return new UserDetailV1("Aman","aman.saini@tothenew.com");
    }


        @GetMapping(value = "/user/header",headers = "API-VERSION=2")
        public UserDetailV2 headerV2(){
            return new UserDetailV2(new UserDeatils("Aman","aman.saini@tothenew.com",1,29));
        }

    //        ----------------Mime type versioning-------------------------
    @GetMapping(value = "/user/produces",produces = "application/user.app-v1+json")
    public UserDetailV1 mimeV1(){
        return new UserDetailV1("Aman","aman.saini@tothenew.com");
    }


        @GetMapping(value = "/user/produces",produces = "application/user.app-v2+json")
        public UserDetailV2 mimeV2(){
            return new UserDetailV2(new UserDeatils("Aman","aman.saini@tothenew.com",1,29));
        }
    }
