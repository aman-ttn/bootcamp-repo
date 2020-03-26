package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dto.CustomerRegister;
import com.bootcamp.bootcampecomproject.entities.Customer;
import com.bootcamp.bootcampecomproject.entities.OauthAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
public class AppController {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/doLogout")
    public String logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
        return "Logged out successfully";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/admin/home")
    public String adminHome() throws UnsupportedEncodingException {

//        String str = new String(new OauthAccessToken().getToken(), "UTF-8");
        return "Admin home";
    }

    @GetMapping("/user/home")
    public String userHome(){
        return "User home";
    }

    @PostMapping("/register/customer")
    public String customerRegister(@RequestBody CustomerRegister customerRegister){
        customerDao.doRegister(customerRegister);
        return "Customer Registered Successfully";
    }
}
