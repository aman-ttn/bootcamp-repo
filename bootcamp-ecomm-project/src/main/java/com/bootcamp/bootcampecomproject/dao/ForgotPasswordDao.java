package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.entities.ForgotPasswordToken;
import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.entities.ValidPassword;
import com.bootcamp.bootcampecomproject.exception.EmailException;
import com.bootcamp.bootcampecomproject.exception.PasswordException;
import com.bootcamp.bootcampecomproject.exception.TokenInvalidException;
import com.bootcamp.bootcampecomproject.repositories.ForgotPasswordTokenRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ForgotPasswordDao {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordTokenRepository forgotPasswordTokenRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    @ValidPassword
    private String finalPassword;

    public String sendForgotPaswordToken(String email, WebRequest webRequest) {
        Locale locale = webRequest.getLocale();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        Boolean isEmailValid=matcher.matches();
        if(isEmailValid) {
            User user = userRepository.findByEmail(email);

            if (user == null) {
                String messageEmailDoesNotExist = messageSource.getMessage("exception.emailDoesNotExist", null, locale);
                throw new EmailException(messageEmailDoesNotExist);
            } else if (user.getActive() == false) {
                String messageAccountInactive = messageSource.getMessage("exception.userInactive", null, locale);
                throw new EmailException(messageAccountInactive);
            } else {
                forgotPasswordTokenRepository.doDeleteById(user.getId());
                String token = UUID.randomUUID().toString();
                ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token, user, new ForgotPasswordToken().calculateExpiryDate(new ForgotPasswordToken().getEXPIRATION()));
                forgotPasswordTokenRepository.save(forgotPasswordToken);

//                Code for sending the token to the user.

                String recipientAddress = user.getEmail();
                String messageSubject = messageSource.getMessage("forgot.password",null,locale);
                String confirmationUrl= webRequest.getContextPath() + "/resetPassword?token=" + token;
                String messageText =messageSource.getMessage("forgot.password.message",null,locale);

                SimpleMailMessage useremail = new SimpleMailMessage();
                useremail.setTo(recipientAddress);
                useremail.setSubject(messageSubject);
                useremail.setText(messageText + "\r\n" + "http://localhost:8080" + confirmationUrl);
                javaMailSender.send(useremail);
                String messageForgPass = messageSource.getMessage("forgot.passowrdMailSent.successfully", null, locale);
                return messageForgPass;
            }
        }
        else {
            String messageInvalidEmail=messageSource.getMessage("exception.invalidEmail",null,locale);
            throw new EmailException(messageInvalidEmail);
        }
    }
    public String resetPassword(String token, String password, String confirmPassword, WebRequest webRequest) {

        Locale locale = webRequest.getLocale();
//        String regex="(?=^.{8,15}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\\s).*$";
//        It will work without spl char
        String reg1="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,25})";
        Pattern pattern = Pattern.compile(reg1);
        Matcher matcher = pattern.matcher(password);
        Boolean isPassowrdValid=matcher.matches();
        ForgotPasswordToken forgotPasswordToken = forgotPasswordTokenRepository.findByToken(token);
        if (forgotPasswordToken != null) {
            User user = forgotPasswordToken.getUser();
            user.setPassword(password);
            System.out.println(user.getName().getFirstName());
            Calendar cal = Calendar.getInstance();
            if ((forgotPasswordToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                String messageTokenExpired = messageSource.getMessage("exception.token.expired", null, locale);
                throw new TokenInvalidException(messageTokenExpired + "  " + forgotPasswordToken.getExpiryDate().getTime() + "   " + cal.getTime().getTime());
            }
            else if(password.equals(confirmPassword)==false){
                String messagePasswordDiff=messageSource.getMessage("exception.passwordNotSame",null,locale);
                throw  new PasswordException("Password and confirm password do not match");
            }
            else if(isPassowrdValid==false){
                String messagePasswordInvalid=messageSource.getMessage("exception.password.invalid",null,locale);
                throw new PasswordException(messagePasswordInvalid);
            }
            else {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                finalPassword=passwordEncoder.encode(password);
                userRepository.doUpdatePassword(finalPassword,user.getId());
                forgotPasswordTokenRepository.doDeleteByToken(token);
                String messageActivationSuccess = messageSource.getMessage("reset.password.successful", null, locale);
                return messageActivationSuccess;
            }
        }
        else{

            String messageTokenInvalid = messageSource.getMessage("exception.token.invalid", null, locale);
            throw new TokenInvalidException(messageTokenInvalid);
        }
    }
}
