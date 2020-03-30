package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {

    @Query(value = "select ad.id, ad.address, ad.city, ad.country, ad.state, ad.zip_code from address ad where ad.customer_user_id = :id",nativeQuery = true)
    public List<Object[]> findAddressById(@Param("id") Long id);

    }
