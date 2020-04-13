package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByUserId(Long userId);
    @Query(value = "select u.id,u.first_name,u.middle_name,u.last_name,u.is_active,c.contact_number,u.image_path from user u inner join customer c on u.id=c.user_id where email= :Email ",nativeQuery = true)
    public List<Object[]> getCustomer(@Param("Email")String email);


}
