package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.AdminDao;
import com.bootcamp.bootcampecomproject.dtos.FindAllCustomerDto;
import com.bootcamp.bootcampecomproject.dtos.FindAllSellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;
    @GetMapping("/customers")
    public List<FindAllCustomerDto> getAllCustomers(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageOffset,
            @RequestParam(defaultValue ="id")String sortByField
    ){
        return adminDao.getAllCustomer(pageSize,pageOffset,sortByField);
    }
    @GetMapping("/sellers")
    public List<FindAllSellerDto> getAllSeller(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageOffset,
            @RequestParam(defaultValue ="id")String sortByField
    ){
        return adminDao.getAllSeller(pageSize,pageOffset,sortByField);
    }
    @PutMapping("/activate/customer")
    public String activateCustomer(@RequestParam("UserId")Long id, WebRequest webRequest){
        return adminDao.activateCustomer(id,webRequest);
    }
}
