package com.bootcamp.bootcampecomproject.controller;

import com.bootcamp.bootcampecomproject.dao.ActivationDao;
import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.entities.VerificationToken;
import com.bootcamp.bootcampecomproject.exception.EmailAlreadyExistException;
import com.bootcamp.bootcampecomproject.exception.VerificationTokenInvalid;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import com.bootcamp.bootcampecomproject.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@RestController
public class ActivationController {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private ActivationDao activationDao;

    @GetMapping("/registrationConfirm")
    public String confirmRegistration
            (WebRequest request, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
//            String message = messageSource.getMessage("auth.message.invalidToken", null, locale);
            throw new VerificationTokenInvalid("Token is invalid.");
        }

        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            String messageValue = messageSource.getMessage("auth.message.expired", null, locale);
//            return "Token is expired.";
//        }
        user.setActive(true);
        userRepository.save(user);
        return "Activation Successfull";
    }
}
