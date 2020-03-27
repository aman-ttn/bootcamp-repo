package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dto.CustomerRegister;
import com.bootcamp.bootcampecomproject.dto.SellerRegister;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.repositories.SellerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SellerDao {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    UserRepository userRepository;
    public void doRegister(SellerRegister sellerRegister) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(sellerRegister.getEmail());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(sellerRegister.getPassword()));
        Seller seller=new Seller();
        seller.setCompanyContact(sellerRegister.getContactNumber());
        seller.setGst(sellerRegister.getGst());
        seller.setCompanyName(sellerRegister.getCompanyName());
        seller.setUser(user);
        user.setRoles(Arrays.asList(new Role("ROLE_SELLER")));
        sellerRepository.save(seller);


    }
}
