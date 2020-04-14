package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.Customer;
import com.bootcamp.bootcampecomproject.entities.Seller;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    Seller findByGst(String gst);

    Seller findByUserId(Long id);

    @Modifying
    @Transactional
    @Query(value="select u.id,u.first_name,u.middle_name,u.last_name,u.is_active,s.company_contact,s.company_name,u.image_path,s.gst,a.address,a.city,a.state,a.country,a.zip_code from user u inner join seller s on u.id=s.user_id inner join address a on a.seller_user_id=u.id where u.id=:Id",nativeQuery = true)
    public List<Object[]> getProfile(@Param("Id")Long id);
}
