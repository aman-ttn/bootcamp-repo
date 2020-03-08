package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.dao.UserFilterDao;
import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity.UserFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiModel(description = "This is controller used for static filtering.")

public class UserStaticFilteringController {

    @Autowired
    private UserFilterDao userFilterDao;

    @PostMapping(path="/filter/static")
    public UserFilter doStaticFilter(@RequestBody UserFilter userFilter){
        return userFilterDao.getStaticUser(userFilter);
    }

}
