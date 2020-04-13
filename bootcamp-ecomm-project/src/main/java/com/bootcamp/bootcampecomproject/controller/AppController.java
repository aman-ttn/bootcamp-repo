package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.AddressDao;
import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dao.SellerDao;
import com.bootcamp.bootcampecomproject.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@RestController
public class AppController {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    AddressDao addressDao;

    @GetMapping("/doLogout")
    public String logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String name="";
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            Principal principal = request.getUserPrincipal();
            name=principal.getName();
            tokenStore.removeAccessToken(accessToken);
        }
        return ("Hi , "+name+" You Logged out successfully");
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/admin/home")
    public String adminHome() throws UnsupportedEncodingException {

        return "Admin home";
    }

    @GetMapping("/user/home")
    public String userHome(){
        return "User home";
    }



    @GetMapping("/address/user/{id}")
    public Address getAddress(@PathVariable Long id){
        return addressDao.getAddress(id);
    }
}
