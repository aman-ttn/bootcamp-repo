package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.SellerProfileDto;
import com.bootcamp.bootcampecomproject.dtos.SellerRegisterDto;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.exception.EmailException;
import com.bootcamp.bootcampecomproject.exception.GstException;
import com.bootcamp.bootcampecomproject.exception.UserNotFoundException;
import com.bootcamp.bootcampecomproject.repositories.AddressRepository;
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

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.Principal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;
    public String doRegister(SellerRegisterDto sellerRegisterDto, WebRequest webRequest) {
        Locale locale=webRequest.getLocale();
        if(userRepository.findByEmail(sellerRegisterDto.getEmail())!=null){
            String messageEmailAlreadyExist=messageSource.getMessage("exception.emailAlreadyExist",null,locale);
            throw new EmailException(messageEmailAlreadyExist);
        }
        else if(sellerRepository.findByGst(sellerRegisterDto.getGst())!=null){
            String messageGstAlreadyExist=messageSource.getMessage("exception.gstAlreadyExist",null,locale);
            throw new GstException(messageGstAlreadyExist);
        }
        else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setEmail(sellerRegisterDto.getEmail());
            user.setActive(false);
            user.setPassword(passwordEncoder.encode(sellerRegisterDto.getPassword()));
            Seller seller = new Seller();
            seller.setCompanyContact(sellerRegisterDto.getContactNumber());
            seller.setGst(sellerRegisterDto.getGst());
            seller.setCompanyName(sellerRegisterDto.getCompanyName());
            seller.setUser(user);
            user.setRoles(Arrays.asList(new Role("ROLE_SELLER")));
            sellerRepository.save(seller);
            String token = UUID.randomUUID().toString();

            VerificationToken verificationToken = new VerificationToken(token, user, new VerificationToken().calculateExpiryDate(new VerificationToken().getEXPIRATION()));
            verificationTokenRepository.save(verificationToken);

            String recipientAddress = "amansaini6162@gmail.com";
            String subject = "Activation Confirmation for Seller";
            String confirmationUrl= webRequest.getContextPath() + "/registrationConfirm?token=" + token;
            String message = "Seller Company Name -\t" + sellerRegisterDto.getCompanyName() + "\nClick on the url to activate the seller";
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
            javaMailSender.send(email);
            String messageRegSucc = messageSource.getMessage("seller.registration.successfull", null, locale);
            return messageRegSucc;
        }
    }
    public SellerProfileDto getProfile(HttpServletRequest httpServletRequest){
        String email=getEmailbyToken(httpServletRequest);
        Long id=userRepository.findByEmail(email).getId();
        SellerProfileDto sellerProfileDto=null;
        List<Object[]> sellerDetails=sellerRepository.getProfile(id);
        for (Object[] seller:sellerDetails) {
            sellerProfileDto=new SellerProfileDto((BigInteger) seller[0],(String)seller[1],(String)seller[2],(String)seller[3],(Boolean) seller[4],(String)seller[5],(String)seller[6],(String)seller[7],(String)seller[8],(String)seller[9],(String)seller[10],(String)seller[11],(String)seller[12],(Integer)seller[13]);
        }
        return sellerProfileDto;
    }

    private String getEmailbyToken(HttpServletRequest httpServletRequest){
        String email=httpServletRequest.getUserPrincipal().getName();
        return email;
    }
    public String updateProfile(HashMap<String,Object> sellerDetails, HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String email=principal.getName();
        User user=userRepository.findByEmail(email);
        Seller seller=sellerRepository.findByUserId(user.getId());
        Name name=user.getName();
        String firstName = (String) sellerDetails.get("firstName");
        String middleName = (String) sellerDetails.get("middleName");
        String lastName = (String) sellerDetails.get("lastName");
        String companyContact=(String) sellerDetails.get("companyContact");
        String companyName=(String) sellerDetails.get("companyName");
        String gst=(String) sellerDetails.get("gst");

        if (checkNull(gst))
            if(!isGstValid(gst))
                throw new GstException("Your gst number is not valid");

        if(checkNull(firstName))
            name.setFirstName(firstName);
        if(checkNull(middleName))
            name.setMiddleName(middleName);
        if(checkNull(lastName))
            name.setLastName(lastName);
        if(checkNull(companyContact))
            seller.setCompanyContact(companyContact);
        if (checkNull(companyName))
            seller.setCompanyName(companyName);

        seller.setGst(gst);
        user.setName(name);
        sellerRepository.save(seller);
        userRepository.save(user);
        return "Update Success";


    }
    private Boolean checkNull(String value){
        if(value!=null && !value.equals(""))
            return true;
        else
            return false;
    }
    private Boolean isGstValid(String gst){
        String regex="(\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gst);
        return matcher.matches();
    }
    public String updateAddress(HashMap<String,Object> addressDetails,Long addressId,HttpServletRequest httpServletRequest){
        String email=getEmailbyToken(httpServletRequest);
        Long id=userRepository.findByEmail(email).getId();
        Address address=addressRepository.getAddressBySellerAndAddressId(id,addressId);
        if(address!=null){
            String address1 = (String) addressDetails.get("address");
            String city = (String) addressDetails.get("city");
            String state = (String) addressDetails.get("state");
            String country=(String) addressDetails.get("country");
            Integer zipCode=(Integer) addressDetails.get("zipCode");
            String stringLabel = (String) addressDetails.get("label");
            Label label = null;
            if (checkNull(stringLabel))
            {
                label = Label.valueOf(stringLabel);
                address.setLabel(label);
            }
            if(checkNull(address1))
                address.setAddress(address1);
            if (checkNull(city))
                address.setCity(city);
            if (checkNull(state))
                address.setState(state);
            if (checkNull(country))
                address.setCountry(country);
            if(zipCode!=null)
                address.setZipCode(zipCode);
            addressRepository.save(address);
            return "Address Updated Successfully";
        }
        else {
            throw new UserNotFoundException("Address Not Found Exception");
        }
    }
}
