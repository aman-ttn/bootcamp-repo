package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.CustomerRegister;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.exception.EmailAlreadyExistException;
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
    VerificationTokenRepository verificationTokenRepository;



    public void doRegister(CustomerRegister customerRegister, WebRequest webRequest)  {

        if(userRepository.findByEmail(customerRegister.getEmail())!=null){
            throw new EmailAlreadyExistException("This email id already exist");
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
            Customer customer = new Customer();
            customer.setContactNumber(customerRegister.getContactNumber());
            customer.setUser(user);
            user.setRoles(Arrays.asList(new Role("ROLE_CUSTOMER")));

//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(customerRegister.getEmail());
//            mailMessage.setSubject("Registration Succesfull");
//            mailMessage.setText("Hi " + customerRegister.getFirstName() + "\nYou have registered succesfully in ecomm project.");
            customerRepository.save(customer);
//            javaMailSender.send(mailMessage);
//            User user = event.getUser();
            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken=new VerificationToken(token,user);
            verificationTokenRepository.save(verificationToken);

            String recipientAddress = user.getEmail();
            String subject = "Registration Confirmation";
            String confirmationUrl
                    = webRequest.getContextPath() + "/registrationConfirm?token=" + token;
//            String message = messageSource.getMessage("message.regSucc", null, event.getLocale());
            String message="Registration Succesfull";

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
            javaMailSender.send(email);
        }
    }
}
