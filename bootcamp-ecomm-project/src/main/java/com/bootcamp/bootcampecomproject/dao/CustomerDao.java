package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.CustomerRegister;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.exception.EmailException;
import com.bootcamp.bootcampecomproject.repositories.CustomerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import com.bootcamp.bootcampecomproject.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

@Component
public class CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public String doRegister(CustomerRegister customerRegister, WebRequest webRequest)  {
        Locale locale=webRequest.getLocale();
        if(userRepository.findByEmail(customerRegister.getEmail())!=null){
            String messageEmailAlreadyExist=messageSource.getMessage("exception.emailAlreadyExist",null,locale);
            throw new EmailException(messageEmailAlreadyExist);
        }
        else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setEmail(customerRegister.getEmail());
            Name name = new Name();
            name.setFirstName(customerRegister.getFirstName());
            name.setMiddleName(customerRegister.getMiddleName());
            name.setLastName(customerRegister.getLastName());
            user.setName(name);
            user.setActive(false);
            user.setPassword(passwordEncoder.encode(customerRegister.getPassword()));
            user.setRoles(Arrays.asList(new Role("ROLE_CUSTOMER")));

            Customer customer = new Customer();
            customer.setContactNumber(customerRegister.getContactNumber());
            customer.setUser(user);
            customerRepository.save(customer);

            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken=new VerificationToken(token,user,new VerificationToken().calculateExpiryDate(new VerificationToken().getEXPIRATION()));
            verificationTokenRepository.save(verificationToken);

//            Code for sending the token to the customer.

            String recipientAddress = user.getEmail();
            String subject = "Registration Confirmation";
            String confirmationUrl
                    = webRequest.getContextPath() + "/registrationConfirm?token=" + token;
            String message="Registration Succesfull";

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
            javaMailSender.send(email);
            String messageRegSucc=messageSource.getMessage("customer.registration.successfull",null,locale);
            return messageRegSucc;
        }
    }
}
