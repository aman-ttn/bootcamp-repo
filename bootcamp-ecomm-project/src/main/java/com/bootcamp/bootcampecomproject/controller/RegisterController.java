package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dao.SellerDao;
import com.bootcamp.bootcampecomproject.dtos.CustomerRegister;
import com.bootcamp.bootcampecomproject.dtos.SellerRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private SellerDao sellerDao;


    @PostMapping("/customer")
    public String customerRegister(@Valid @RequestBody CustomerRegister customerRegister, WebRequest webRequest){
        customerDao.doRegister(customerRegister,webRequest);
        return "Customer Registered Successfully";
    }

    @PostMapping("/seller")
    public String sellerRegister(@Valid  @RequestBody SellerRegister sellerRegister){
        sellerDao.doRegister(sellerRegister);
        return "Seller Registered Successfully";
    }



}
