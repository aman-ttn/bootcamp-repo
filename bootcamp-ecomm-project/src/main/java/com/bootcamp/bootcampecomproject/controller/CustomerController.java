package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dtos.CustomerAddressDto;
import com.bootcamp.bootcampecomproject.dtos.CustomerProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerDao customerDao;
    @GetMapping(value="/customer/profile")
    public CustomerProfileDto viewProfile(HttpServletRequest httpServletRequest){
        return customerDao.getProfile(httpServletRequest);
    }
    @GetMapping(value = "/customer/address")
    public List<CustomerAddressDto> viewAddress(HttpServletRequest httpServletRequest){
        return customerDao.getAddress(httpServletRequest);
    }
}
