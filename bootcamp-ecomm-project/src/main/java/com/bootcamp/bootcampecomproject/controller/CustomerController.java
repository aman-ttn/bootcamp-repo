package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dao.ForgotPasswordDao;
import com.bootcamp.bootcampecomproject.dtos.CustomerAddressDto;
import com.bootcamp.bootcampecomproject.dtos.CustomerProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ForgotPasswordDao forgotPasswordDao;
    @GetMapping(value="/customer/profile")
    public CustomerProfileDto viewProfile(HttpServletRequest httpServletRequest){
        return customerDao.getProfile(httpServletRequest);
    }
    @GetMapping(value = "/customer/address")
    public List<CustomerAddressDto> viewAddress(HttpServletRequest httpServletRequest){
        return customerDao.getAddress(httpServletRequest);
    }
    @PutMapping(value = "/customer/update")
    public ResponseEntity updateProfile(@Valid @RequestBody HashMap<String,Object> map, HttpServletRequest httpServletRequest){
        System.out.println(map+"^^^^^^^^^^^^^^^");
        return customerDao.updateProfile(map,httpServletRequest);
    }
    @PutMapping(value = "/customer/updatePassword")
    public String resetPassword(@RequestParam("password")String password, @RequestParam("confirmPassword") String confirmPassword, WebRequest webRequest,HttpServletRequest httpServletRequest){
        return customerDao.updatePassword(password,confirmPassword,webRequest,httpServletRequest);
    }
    @PostMapping(value = "/customer/addAddress")
    public String addAddress(@RequestBody CustomerAddressDto customerAddressDto,HttpServletRequest httpServletRequest){
        return customerDao.addAddress(customerAddressDto,httpServletRequest);
    }
    @DeleteMapping(value = "/customer/deleteAddress")
    public String deleteAddress(@RequestParam Long addressId,HttpServletRequest httpServletRequest){
        return customerDao.deleteAddress(addressId,httpServletRequest);
    }
    @PutMapping(value = "/customer/updateAddress")
    public String updateAddress(@RequestParam("addressId")Long id,@RequestBody HashMap<String,Object> map,HttpServletRequest httpServletRequest) throws Exception {
        return customerDao.updateAddress(map,id,httpServletRequest);
    }
}
