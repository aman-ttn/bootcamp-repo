package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.FindAllCustomerDto;
import com.bootcamp.bootcampecomproject.dtos.FindAllSellerDto;
import com.bootcamp.bootcampecomproject.entities.Customer;
import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.exception.UserNotFoundException;
import com.bootcamp.bootcampecomproject.repositories.CustomerRepository;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private MessageSource messageSource;

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
        user.setActive(true);
        userRepository.save(user);
        String messageCustomerActivationSucc=messageSource.getMessage("activation.successful",null,locale);
        return messageCustomerActivationSucc;
    }
}
