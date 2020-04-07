package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.ForgotPasswordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class ForgotPasswordController {
    @Autowired
    private ForgotPasswordDao forgotPasswordDao;

    @PostMapping(value = "/forgotPassword")
    public String forgotPassword(@RequestParam("Email")String email, WebRequest webRequest){
        return forgotPasswordDao.sendForgotPaswordToken(email,webRequest);
    }
    @PutMapping(value = "/resetPassword")
    public String resetPassword(@RequestParam("token")String token,@RequestParam("password")String password,@RequestParam("confirmPassword") String confirmPassword,WebRequest webRequest){
        return forgotPasswordDao.resetPassword(token,password,confirmPassword,webRequest);
    }

}
