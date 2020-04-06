package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.entities.VerificationToken;
import com.bootcamp.bootcampecomproject.exception.EmailException;
import com.bootcamp.bootcampecomproject.exception.VerificationTokenInvalidException;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import com.bootcamp.bootcampecomproject.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

@Component
public class ActivationDao {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    public String doActivate(String token, WebRequest request){

        Locale locale = request.getLocale();

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            String messageTokenInvalid=messageSource.getMessage("exception.token.invalid",null,locale);
            throw new VerificationTokenInvalidException(messageTokenInvalid);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            String messageTokenExpired = messageSource.getMessage("exception.token.expired", null, locale);

            throw new VerificationTokenInvalidException(messageTokenExpired+"  "+verificationToken.getExpiryDate().getTime()+"   "+cal.getTime().getTime());
        }
        user.setActive(true);
        userRepository.save(user);
        verificationTokenRepository.doDelete(token);
        String messageActivationSuccess=messageSource.getMessage("activation.successful",null,locale);
        return messageActivationSuccess;
    }

    public String sendReactivationToken(String email,WebRequest webRequest){
        Locale locale=webRequest.getLocale();
        User user=userRepository.findByEmail(email);
        if(user==null){
            String messageEmailDoesNotExist=messageSource.getMessage("exception.emailDoesNotExist",null,locale);
            throw new EmailException(messageEmailDoesNotExist);
        }
        else if(user.getActive()==true){
            String messageAlreadyActive=messageSource.getMessage("exception.alreadyActive",null,locale);
            throw new EmailException("This user account is alreday active");
        }
        Long id=user.getId();

        String token = UUID.randomUUID().toString();
        Date expDate=new VerificationToken().calculateExpiryDate(new VerificationToken().getEXPIRATION());


        verificationTokenRepository.doUpdate(token,expDate,id);

        String recipientAddress = email;
        String subject = "Registration Re-Confirmation";
        String confirmationUrl = webRequest.getContextPath() + "/registrationConfirm?token=" + token;
        String message="Reactivation Mail";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        javaMailSender.send(simpleMailMessage);
        String messageMailSucc=messageSource.getMessage("email.reactivation.mailSentSuccessfully",null,locale);
        return messageMailSucc;
    }
}
