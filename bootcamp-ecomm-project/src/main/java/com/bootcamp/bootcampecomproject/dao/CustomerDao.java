package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dto.CustomerRegister;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.repositories.CustomerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    public void doRegister(CustomerRegister customerRegister) {


        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(customerRegister.getEmail());
        Name name=new Name();
        name.setFirstName(customerRegister.getFirstName());
        name.setMiddleName(customerRegister.getMiddleName());
        name.setLastName(customerRegister.getLastName());
        user.setName(name);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(customerRegister.getPassword()));
        Customer customer=new Customer();
        customer.setContactNumber(customerRegister.getContactNumber());
        customer.setUser(user);
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        customerRepository.save(customer);


    }
}
