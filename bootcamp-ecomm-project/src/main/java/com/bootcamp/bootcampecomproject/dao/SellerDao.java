package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.SellerRegister;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.exception.EmailException;
import com.bootcamp.bootcampecomproject.exception.GstException;
import com.bootcamp.bootcampecomproject.repositories.SellerRepository;
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
public class SellerDao {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    UserRepository userRepository;
    public String doRegister(SellerRegister sellerRegister, WebRequest webRequest) {
        Locale locale=webRequest.getLocale();
        if(userRepository.findByEmail(sellerRegister.getEmail())!=null){
            String messageEmailAlreadyExist=messageSource.getMessage("exception.emailAlreadyExist",null,locale);
            throw new EmailException(messageEmailAlreadyExist);
        }
        else if(sellerRepository.findByGst(sellerRegister.getGst())!=null){
            String messageGstAlreadyExist=messageSource.getMessage("exception.gstAlreadyExist",null,locale);
            throw new GstException(messageGstAlreadyExist);
        }
        else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setEmail(sellerRegister.getEmail());
            user.setActive(false);
            user.setPassword(passwordEncoder.encode(sellerRegister.getPassword()));
            Seller seller = new Seller();
            seller.setCompanyContact(sellerRegister.getContactNumber());
            seller.setGst(sellerRegister.getGst());
            seller.setCompanyName(sellerRegister.getCompanyName());
            seller.setUser(user);
            user.setRoles(Arrays.asList(new Role("ROLE_SELLER")));
            sellerRepository.save(seller);
            String token = UUID.randomUUID().toString();

            VerificationToken verificationToken = new VerificationToken(token, user, new VerificationToken().calculateExpiryDate(new VerificationToken().getEXPIRATION()));
            verificationTokenRepository.save(verificationToken);

            String recipientAddress = "amansaini6162@gmail.com";
            String subject = "Activation Confirmation for Seller";
            String confirmationUrl= webRequest.getContextPath() + "/registrationConfirm?token=" + token;
            String message = "Seller Company Name -\t" + sellerRegister.getCompanyName() + "\nClick on the url to activate the seller";
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
            javaMailSender.send(email);
            String messageRegSucc = messageSource.getMessage("seller.registration.successfull", null, locale);
            return messageRegSucc;
        }
    }
}
