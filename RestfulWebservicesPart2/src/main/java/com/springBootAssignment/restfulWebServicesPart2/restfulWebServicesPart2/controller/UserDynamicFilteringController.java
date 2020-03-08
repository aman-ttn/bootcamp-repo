package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.dao.UserFilterDao;
import com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.entity.UserFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@ApiModel(description = "This is controller used for dynamic filtering.")
public class UserDynamicFilteringController {

    @Autowired
    private UserFilterDao userFilterDao;
    @ApiModelProperty(notes = "This is used to filter dynamically.")
    @PostMapping(path="/filter/dynamic")
    public MappingJacksonValue doStaticFilter(@RequestBody UserFilter userFilter){
        userFilterDao.getDynamicUser(userFilter);
        UserFilter userFilter1=new UserFilter(userFilter.getId(),userFilter.getName(),userFilter.getPassword());
        SimpleBeanPropertyFilter simpleBeanPropertyFilter=SimpleBeanPropertyFilter.filterOutAllExcept("id,name");
        FilterProvider filterProvider=new SimpleFilterProvider().addFilter("UserFilter",simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(userFilter1);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
