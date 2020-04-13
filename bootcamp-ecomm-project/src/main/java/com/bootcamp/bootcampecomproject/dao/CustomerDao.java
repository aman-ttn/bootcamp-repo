package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.CustomerAddressDto;
import com.bootcamp.bootcampecomproject.dtos.CustomerProfileDto;
import com.bootcamp.bootcampecomproject.dtos.CustomerRegister;
import com.bootcamp.bootcampecomproject.dtos.FindAllCustomerDto;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.exception.EmailException;
import com.bootcamp.bootcampecomproject.repositories.AddressRepository;
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

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.Principal;
import java.util.*;

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

    @Autowired
    private AddressRepository addressRepository;

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
    public CustomerProfileDto getProfile(HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String email=principal.getName();
        CustomerProfileDto customerProfileDto=null;
        List<Object[]> customers=customerRepository.getCustomer(email);
        for (Object[] customer:customers) {
            customerProfileDto=new CustomerProfileDto((BigInteger)customer[0],(String)customer[1],(String)customer[2],(String)customer[3],(Boolean)customer[4],(String)customer[5],(String)customer[6]);
        }
        return customerProfileDto;
    }
    public List<CustomerAddressDto> getAddress(HttpServletRequest httpServletRequest){
        List<CustomerAddressDto> addressDtoList=new ArrayList<>();
        Long id=getUserId(httpServletRequest);
        List<Address> addresses=addressRepository.getAddress(id);
        addresses.forEach(address -> {
            CustomerAddressDto customerAddressDto=new CustomerAddressDto(address.getCity(),address.getState(),address.getCountry(),address.getAddress(),address.getLabel(),address.getZipCode());
            addressDtoList.add(customerAddressDto);
        });
        return addressDtoList;
    }
    private Long getUserId(HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String email=principal.getName();
        User user=userRepository.findByEmail(email);
        return user.getId();
    }
}
