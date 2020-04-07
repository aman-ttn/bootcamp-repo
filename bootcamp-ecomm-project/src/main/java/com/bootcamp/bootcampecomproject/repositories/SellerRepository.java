package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.Customer;
import com.bootcamp.bootcampecomproject.entities.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    Seller findByGst(String gst);

    Seller findByUserId(Long id);
}
