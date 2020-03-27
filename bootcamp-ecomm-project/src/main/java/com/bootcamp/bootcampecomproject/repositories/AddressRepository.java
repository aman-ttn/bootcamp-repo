package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository {

    @Query("from Address where customer = :id")
    public List<Address> findAddressById(@Param("id") Long id);

    }
