package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.ActivationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@RestController
public class ActivationController {

     @Autowired
    private ActivationDao activationDao;

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(WebRequest request, @RequestParam("token") String token) {
        return activationDao.doActivate(token,request);
    }

    @PostMapping("/reactivateUser")
    public String reactivateUser(@RequestParam("email") String email,WebRequest webRequest){
        return activationDao.sendReactivationToken(email,webRequest);
    }
}
