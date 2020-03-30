package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.AddressDao;
import com.bootcamp.bootcampecomproject.dao.CustomerDao;
import com.bootcamp.bootcampecomproject.dao.SellerDao;
import com.bootcamp.bootcampecomproject.dto.CustomerRegister;
import com.bootcamp.bootcampecomproject.dto.SellerRegister;
import com.bootcamp.bootcampecomproject.entities.Address;
import com.bootcamp.bootcampecomproject.entities.Customer;
import com.bootcamp.bootcampecomproject.entities.OauthAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

        return "Admin home";
    }

    @GetMapping("/user/home")
    public String userHome(){
        return "User home";
    }

    @PostMapping("/register/customer")
    public String customerRegister(@Valid  @RequestBody CustomerRegister customerRegister){
        customerDao.doRegister(customerRegister);
        return "Customer Registered Successfully";
    }

    @PostMapping("/register/seller")
    public String sellerRegister(@Valid  @RequestBody SellerRegister sellerRegister){
        sellerDao.doRegister(sellerRegister);
        return "Seller Registered Successfully";
    }

    @GetMapping("/address/user/{id}")
    public Address getAddress(@PathVariable Long id){
        return addressDao.getAddress(id);
    }
}
