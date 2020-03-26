package com.bootcamp.bootcampecomproject;


import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.repositories.CustomerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userRepository.count()<1){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user1 = new User();
            user1.setEmail("user");
            Name name1=new Name();
            name1.setFirstName("Aman");
            name1.setMiddleName("");
            name1.setLastName("Saini");
            user1.setName(name1);
            user1.setActive(true);
            Customer customer1=new Customer();

            customer1.setContactNumber("9045249440");
            customer1.setUser(user1);
            user1.setPassword(passwordEncoder.encode("pass"));
            user1.setRoles(Arrays.asList(new Role("ROLE_USER")));
            customerRepository.save(customer1);

            User user2 = new User();
            user2.setEmail("admin");
            user2.setPassword(passwordEncoder.encode("pass"));
            user2.setRoles(Arrays.asList(new Role("ROLE_USER"),new Role("ROLE_ADMIN")));



            userRepository.save(user1);
            userRepository.save(user2);
            System.out.println("Total users saved::"+userRepository.count());
        }
    }
}
