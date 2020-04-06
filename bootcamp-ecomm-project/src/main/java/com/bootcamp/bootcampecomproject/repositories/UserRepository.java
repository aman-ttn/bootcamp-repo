package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {


    User findByEmail(String email);

    User getUserById(Long id);

    @Query(value="select id,first_name,middle_name,last_name,email,is_active from user where id in (select user_id from customer)",nativeQuery = true)
    public List<Object[]> getCustomers(PageRequest pageable);

    @Query(value="select u.id,u.first_name,u.middle_name,u.last_name,u.email,u.is_active,s.company_contact,s.company_name,s.gst from user u inner join seller s on u.id=s.user_id",nativeQuery = true)
    public List<Object[]> getSellers(PageRequest pageable);

}
