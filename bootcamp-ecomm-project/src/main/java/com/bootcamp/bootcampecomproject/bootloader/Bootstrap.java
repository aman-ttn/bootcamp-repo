package com.bootcamp.bootcampecomproject.bootloader;


import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.repositories.CustomerRepository;
import com.bootcamp.bootcampecomproject.repositories.SellerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserAttemptsRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserAttemptsRepository userAttemptsRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userRepository.count()<1){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user1 = new User();
            user1.setEmail("amanboy192@gmail.com");
            Name name1=new Name();
            name1.setFirstName("Aman");
            name1.setMiddleName("");
            name1.setLastName("Saini");
            user1.setName(name1);
            user1.setActive(true);
            Customer customer1=new Customer();
            customer1.setContactNumber("9045249440");
            customer1.setUser(user1);
            List<Address> addresses=new ArrayList<>();
            Address address=new Address();
            address.setAddress("GB Nagar");
            address.setZipCode(201310);
            address.setState("UP");
            address.setCountry("India");
            address.setCity("Noida");
            address.setLabel(Label.home);
            addresses.add(address);
            address.setCustomer(customer1);



            customer1.setAddresses(addresses);
            user1.setPassword(passwordEncoder.encode("pass"));
            user1.setRoles(Arrays.asList(new Role("ROLE_CUSTOMER")));


            customerRepository.save(customer1);
//            userAttemptsRepository.save(userAttempts);

            User user2 = new User();
            user2.setEmail("amansaini6162@gmail.com");
            user2.setPassword(passwordEncoder.encode("pass"));
            user2.setRoles(Arrays.asList(new Role("ROLE_SELLER"),new Role("ROLE_ADMIN")));
            user2.setActive(true);
            Name name2=new Name();
            name2.setFirstName("Mohan");
            name2.setMiddleName("");
            name2.setLastName("Sharma");
            user2.setName(name2);
            user2.setActive(true);
            Seller seller=new Seller();
            seller.setCompanyName("TTN");
            seller.setGst("27AAPFU0939F1ZV");
            seller.setCompanyContact("Noida");

            Address address2=new Address();
            address2.setAddress("NCR");
            address2.setZipCode(201310);
            address2.setState("Delhi");
            address2.setCountry("India");
            address2.setCity("Delhi");
            address2.setLabel(Label.home);
            address2.setSeller(seller);




            seller.setUser(user2);
            seller.setAddress(address2);
            sellerRepository.save(seller);

            userRepository.save(user1);
            userRepository.save(user2);
            System.out.println("Total users saved::"+userRepository.count());
        }
    }
}
