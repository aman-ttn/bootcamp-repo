package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dao.SellerDao;
import com.bootcamp.bootcampecomproject.dtos.SellerProfileDto;
import com.bootcamp.bootcampecomproject.entities.Customer;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public class SellerController {
    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private CustomerDao customerDao;
    @GetMapping(value = "/seller/profile")
    public List<SellerProfileDto> getProfile(HttpServletRequest httpServletRequest){
        return sellerDao.getProfile(httpServletRequest);
    }
    @PutMapping(value = "/seller/update")
    public String updateProfile(@RequestBody HashMap<String,Object> map, HttpServletRequest httpServletRequest){
        return sellerDao.updateProfile(map,httpServletRequest);
    }
    @PutMapping(value = "/seller/updatePassword")
    public String updatePassword(@RequestParam("password")String password, @RequestParam("confirmPassword") String confirmPassword, WebRequest webRequest, HttpServletRequest httpServletRequest){
        return customerDao.updatePassword(password,confirmPassword,webRequest,httpServletRequest);
    }
    @PutMapping(value = "/seller/updateAddress")
    public String updateAddress(@RequestParam("addressId")Long id,@RequestBody HashMap<String,Object> map,HttpServletRequest httpServletRequest){
        return sellerDao.updateAddress(map,id,httpServletRequest);
    }
}
