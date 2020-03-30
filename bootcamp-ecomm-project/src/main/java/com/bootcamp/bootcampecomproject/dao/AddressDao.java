package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.entities.Address;
import com.bootcamp.bootcampecomproject.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class AddressDao {

    @Autowired
    AddressRepository addressRepository;

    public Address getAddress(Long userid){
        List<Object[]> addresslist=addressRepository.findAddressById(userid);
        Address address=new Address();
        for (Object[] objects:addresslist) {
//            address.setId((Long)objects[0]);
            address.setAddress(objects[1].toString());
            address.setCity(objects[2].toString());
            address.setCountry(objects[3].toString());
            address.setState(objects[4].toString());
            address.setZipCode((Integer)objects[5]);

        }

        return address;
    }
}
