package com.springBootAssignment.restfulWebServicesPart2.restfulWebServicesPart2.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
@ApiModel(description = "This is controller used for internationalization of message.")
@RestController
public class InternationalizationController {

    @Autowired
    private MessageSource messageSource;

    @ApiModelProperty(notes = "This is used for printing the hello")
    @GetMapping(path="/international-messages")
    public String internationalMessage(@RequestHeader(value = "Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("international.message",null,locale);
    }

    @ApiModelProperty(notes = "This is used for printing the hello username")
    @GetMapping(path="/international-messages/{username}")
    public String userMessage(@RequestHeader(value="Accept-Language",required = false)Locale locale,@PathVariable String username){
        return messageSource.getMessage("user.international.message",new Object[]{username},locale);
    }
}


