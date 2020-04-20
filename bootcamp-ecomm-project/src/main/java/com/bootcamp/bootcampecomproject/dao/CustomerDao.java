package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.CustomerAddressDto;
import com.bootcamp.bootcampecomproject.dtos.CustomerProfileDto;
import com.bootcamp.bootcampecomproject.dtos.CustomerRegister;
import com.bootcamp.bootcampecomproject.dtos.FindAllCustomerDto;
import com.bootcamp.bootcampecomproject.entities.*;
import com.bootcamp.bootcampecomproject.exception.*;
import com.bootcamp.bootcampecomproject.repositories.AddressRepository;
import com.bootcamp.bootcampecomproject.repositories.CustomerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import com.bootcamp.bootcampecomproject.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            if (customerRegister.getConfirmPassword().equals(customerRegister.getPassword())){
                String passwordMismatch=messageSource.getMessage("exception.passwordmismatch",null,locale);
                throw new CustomException(passwordMismatch);
            }
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
    public ResponseEntity updateProfile(HashMap<String,Object> customerDetails, HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String email=principal.getName();
        User user=userRepository.findByEmail(email);
        Customer customer=customerRepository.findByUserId(user.getId());
        Name name=user.getName();
        String firstName = (String) customerDetails.get("firstName");
        String middleName = (String) customerDetails.get("middleName");
        String lastName = (String) customerDetails.get("lastName");
        String contact=(String) customerDetails.get("contact");
        if(!isContactValid(contact))
            return new ResponseEntity("Your contact number is not valid!",HttpStatus.CONFLICT);
//            throw new EmailException("Your contact number is not valid");

        if(checkNull(firstName))
            name.setFirstName(firstName);
        if(checkNull(middleName))
            name.setMiddleName(middleName);
        if(checkNull(lastName))
            name.setLastName(lastName);
        if(checkNull(contact))
            customer.setContactNumber(contact);
        user.setName(name);
        customerRepository.save(customer);
        userRepository.save(user);
        return new ResponseEntity("Update Success", HttpStatus.OK);


    }
    private Boolean checkNull(String value){
        if(value!=null && !value.equals(""))
            return true;
        else
            return false;
    }
    private Boolean isContactValid(String contact){
        String reg1="(^$|[0-9]{10})";
        Pattern pattern = Pattern.compile(reg1);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }
    public String updatePassword(String password, String confirmPassword, WebRequest webRequest,HttpServletRequest httpServletRequest) {

        Locale locale = webRequest.getLocale();
//        String regex="(?=^.{8,15}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\\s).*$";
//        It will work without spl char
        Boolean isPassowrdValid=ispasswordValid(password);
        String email=getEmailbyToken(httpServletRequest);
        User user = userRepository.findByEmail(email);
        if(password.equals(confirmPassword)==false){
            String messagePasswordDiff=messageSource.getMessage("exception.passwordNotSame",null,locale);
            throw  new PasswordException("Password and confirm password do not match");
        }
        else if(isPassowrdValid==false){
            String messagePasswordInvalid=messageSource.getMessage("exception.password.invalid",null,locale);
            throw new PasswordException(messagePasswordInvalid);
        }
        else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String finalPassword=passwordEncoder.encode(password);
            userRepository.doUpdatePassword(finalPassword,user.getId());
            String messageActivationSuccess = messageSource.getMessage("reset.password.successful", null, locale);
            return messageActivationSuccess;
        }
    }
    private Boolean ispasswordValid(String password){
        String regex="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,25})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }
    private String getEmailbyToken(HttpServletRequest httpServletRequest){
        String email=httpServletRequest.getUserPrincipal().getName();
        return email;
    }
    public String addAddress(CustomerAddressDto customerAddressDto,HttpServletRequest httpServletRequest){
        String email=getEmailbyToken(httpServletRequest);
        Long id=userRepository.findByEmail(email).getId();
        Customer customer=customerRepository.findByUserId(id);
        Address address=new Address();
        address.setAddress(customerAddressDto.getAddress());
        address.setCity(customerAddressDto.getCity());
        address.setState(customerAddressDto.getState());
        address.setCountry(customerAddressDto.getCountry());
        address.setZipCode(customerAddressDto.getZipCode());
        address.setLabel(customerAddressDto.getLabel());
        address.setCustomer(customer);
        addressRepository.save(address);
        return "Address Saved Successfully";
    }
    public String deleteAddress(Long addressId,HttpServletRequest httpServletRequest){
        String email=getEmailbyToken(httpServletRequest);
        Long id=userRepository.findByEmail(email).getId();
        Address address=addressRepository.getAddressByCustomerAndAddressId(id,addressId);
        if(address!=null){
            addressRepository.deleteById(addressId);
            return "Address deleted Successfully";
        }
        else {
            throw new UserNotFoundException("Address not found!");
        }
    }
    public String updateAddress(HashMap<String,Object> addressDetails,Long addressId,HttpServletRequest httpServletRequest) throws Exception {
        String email=getEmailbyToken(httpServletRequest);
        Long id=userRepository.findByEmail(email).getId();
        Address address=addressRepository.getAddressByCustomerAndAddressId(id,addressId);
        try {
            if (address != null) {

                String address1 = (String) addressDetails.get("address");
                String city = (String) addressDetails.get("city");
                String state = (String) addressDetails.get("state");
                String country = (String) addressDetails.get("country");
                Integer zipCode = (Integer) addressDetails.get("zipCode");
                String stringLabel = (String) addressDetails.get("label");
                Label label = null;
                if (checkNull(stringLabel))
                {
                    label = Label.valueOf(stringLabel);
                    address.setLabel(label);
                }
                System.out.println(address.getLabel() + "Label-------");
                if (checkNull(address1))
                    address.setAddress(address1);
                if (checkNull(city))
                    address.setCity(city);
                if (checkNull(state))
                    address.setState(state);
                if (checkNull(country))
                    address.setCountry(country);
                if (zipCode != null)
                    address.setZipCode(zipCode);

                System.out.println(address.getLabel() + "Label-------");
                addressRepository.save(address);
                return "Address Updated Successfully";
            } else {
                throw new UserNotFoundException("Address Not Found Exception");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
