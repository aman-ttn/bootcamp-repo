package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.FindAllCustomerDto;
import com.bootcamp.bootcampecomproject.dtos.FindAllSellerDto;
import com.bootcamp.bootcampecomproject.entities.Customer;
import com.bootcamp.bootcampecomproject.entities.Seller;
import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.exception.UserNotFoundException;
import com.bootcamp.bootcampecomproject.repositories.CustomerRepository;
import com.bootcamp.bootcampecomproject.repositories.SellerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class AdminDao {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    public List<FindAllCustomerDto> getAllCustomer(String size, String offset, String field){
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        PageRequest pageRequest= PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);

        List<FindAllCustomerDto> findAllCustomerDtos =new ArrayList<>();
        List<Object[]> customers=userRepository.getCustomers(pageRequest);
        for (Object[] customer:customers) {
            findAllCustomerDtos.add(new FindAllCustomerDto((BigInteger)customer[0],(String)customer[1]+" "+(String)customer[2]+" "+(String)customer[3],(String)customer[4],(Boolean)customer[5]));
        }
        return findAllCustomerDtos;
    }
    public List<FindAllSellerDto> getAllSeller(String size, String offset, String field){
        Integer pageSize=Integer.parseInt(size);
        Integer pageOffset=Integer.parseInt(offset);
        PageRequest pageRequest= PageRequest.of(pageOffset,pageSize, Sort.Direction.ASC,field);
        List<FindAllSellerDto> findAllSellerDtos=new ArrayList<>();
        List<Object[]> sellers=userRepository.getSellers(pageRequest);
        for (Object[] seller:sellers) {
            findAllSellerDtos.add(new FindAllSellerDto((BigInteger)seller[0],(String)seller[1]+" "+(String)seller[2]+" "+(String)seller[3],(String)seller[4],(Boolean)seller[5],(String)seller[6],(String)seller[7],(String)seller[8]));
        }
        return findAllSellerDtos;
    }

    public String activateCustomer(Long id, WebRequest webRequest){
        Locale locale=webRequest.getLocale();
        Customer customer=customerRepository.findByUserId(id);
        if(customer==null){
            String messageCustomerNotFound=messageSource.getMessage("exception.customerNotFound",null,locale);
            throw new UserNotFoundException(messageCustomerNotFound);
        }
        User user=userRepository.getUserById(id);
        if(user.getActive()==false){
            user.setActive(true);
            String recipientAddress = user.getEmail();
            String messageSubject=messageSource.getMessage("activation.successful.subject",null,locale);
            String messageActivationSuccessful=messageSource.getMessage("activation.successful.message",null,locale);
            String messageSalutation=messageSource.getMessage("activation.successful.salutation",null,locale);
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(messageSubject);
            email.setText(messageSalutation+" "+user.getName().getFirstName() + "\r\n" + messageActivationSuccessful );
            javaMailSender.send(email);
        }
        userRepository.save(user);
        String messageCustomerActivationSucc=messageSource.getMessage("activation.successful",null,locale);
        return messageCustomerActivationSucc;
    }
    public String deactivateCustomer(Long id, WebRequest webRequest){
        Locale locale=webRequest.getLocale();
        Customer customer=customerRepository.findByUserId(id);
        if(customer==null){
            String messageCustomerNotFound=messageSource.getMessage("exception.customerNotFound",null,locale);
            throw new UserNotFoundException(messageCustomerNotFound);
        }
        User user=userRepository.getUserById(id);
        if(user.getActive()==true){
            user.setActive(false);
            String recipientAddress = user.getEmail();
            String messageSubject=messageSource.getMessage("deactivation.successful.subject",null,locale);
            String messageActivationSuccessful=messageSource.getMessage("deactivation.successful",null,locale);
            String messageSalutation=messageSource.getMessage("deactivation.successful.salutation",null,locale);
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(messageSubject);
            email.setText(messageSalutation+" "+user.getName().getFirstName() + "\r\n" + messageActivationSuccessful );
            javaMailSender.send(email);
        }
        userRepository.save(user);
        String messageCustomerActivationSucc=messageSource.getMessage("deactivation.successful",null,locale);
        return messageCustomerActivationSucc;
    }
    public String activateSeller(Long id, WebRequest webRequest){
        Locale locale=webRequest.getLocale();
        Seller seller=sellerRepository.findByUserId(id);
        if(seller==null){
            String messageCustomerNotFound=messageSource.getMessage("exception.sellerNotFound",null,locale);
            throw new UserNotFoundException(messageCustomerNotFound);
        }
        User user=userRepository.getUserById(id);
        if(user.getActive()==false){
            user.setActive(true);
            String recipientAddress = user.getEmail();
            String messageSubject=messageSource.getMessage("activation.successful.subject",null,locale);
            String messageActivationSuccessful=messageSource.getMessage("activation.successful.message",null,locale);
            String messageSalutation=messageSource.getMessage("activation.successful.salutation",null,locale);
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(messageSubject);
            email.setText(messageSalutation+" "+user.getName().getFirstName() + "\r\n" + messageActivationSuccessful );
            javaMailSender.send(email);
        }
        userRepository.save(user);
        String messageCustomerActivationSucc=messageSource.getMessage("activation.successful",null,locale);
        return messageCustomerActivationSucc;
    }
    public String deactivateSeller(Long id, WebRequest webRequest){
        Locale locale=webRequest.getLocale();
        Seller seller=sellerRepository.findByUserId(id);
        if(seller==null){
            String messageCustomerNotFound=messageSource.getMessage("exception.sellerNotFound",null,locale);
            throw new UserNotFoundException(messageCustomerNotFound);
        }
        User user=userRepository.getUserById(id);
        if(user.getActive()==true){
            user.setActive(false);
            String recipientAddress = user.getEmail();
            String messageSubject=messageSource.getMessage("deactivation.successful.subject",null,locale);
            String messageActivationSuccessful=messageSource.getMessage("deactivation.successful",null,locale);
            String messageSalutation=messageSource.getMessage("deactivation.successful.salutation",null,locale);
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(messageSubject);
            email.setText(messageSalutation+" "+user.getName().getFirstName() + "\r\n" + messageActivationSuccessful );
            javaMailSender.send(email);
        }
        userRepository.save(user);
        String messageCustomerActivationSucc=messageSource.getMessage("deactivation.successful",null,locale);
        return messageCustomerActivationSucc;
    }
}
