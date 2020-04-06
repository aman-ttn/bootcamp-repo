package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dtos.FindAllCustomerDto;
import com.bootcamp.bootcampecomproject.dtos.FindAllSellerDto;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminDao {
    @Autowired
    private UserRepository userRepository;

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
}
