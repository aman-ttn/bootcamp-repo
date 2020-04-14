package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Pattern;
import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {

    @Query(value = "select ad.id, ad.address, ad.city, ad.country, ad.state, ad.zip_code from address ad where ad.customer_user_id = :id",nativeQuery = true)
    public List<Object[]> findAddressById(@Param("id") Long id);

    @Query(value = "select * from address where customer_user_id = :Id",nativeQuery = true)
    public List<Address> getAddress(@Param("Id")Long id);

    @Query(value = "select * from address where customer_user_id = :CustomerId and id = :Id",nativeQuery = true)
    public Address getAddressByCustomerAndAddressId(@Param("CustomerId")Long customerId,@Param("Id")Long id);

    @Query(value = "select * from address where seller_user_id = :SellerId and id = :Id",nativeQuery = true)
    public Address getAddressBySellerAndAddressId(@Param("SellerId")Long customerId,@Param("Id")Long id);
}
