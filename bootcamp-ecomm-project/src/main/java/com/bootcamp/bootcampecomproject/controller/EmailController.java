package com.bootcamp.bootcampecomproject.controller;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    public JavaMailSender javaMailSender;
    @GetMapping(value = "/sendEmail")
    public String sendEmail(){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo("amansaini6162@gmail.com");
        mailMessage.setSubject("Ecomm Project");
        mailMessage.setText("Hi Aman");
        javaMailSender.send(mailMessage);
        return "Mail Sent Succesfully";
    }
}
