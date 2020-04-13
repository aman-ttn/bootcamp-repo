package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {


    User findByEmail(String email);

    User getUserById(Long id);

    @Query(value="select id,first_name,middle_name,last_name,email,is_active from user where id in (select user_id from customer)",nativeQuery = true)
    public List<Object[]> getCustomers(PageRequest pageRequest);

    @Query(value="select u.id,u.first_name,u.middle_name,u.last_name,u.email,u.is_active,s.company_contact,s.company_name,s.gst from user u inner join seller s on u.id=s.user_id",nativeQuery = true)
    public List<Object[]> getSellers(PageRequest pageRequest);

    @Transactional
    @Modifying
    @Query(value="update user set password = :Password where id= :Id",nativeQuery = true)
    public void doUpdatePassword(@Param("Password") String password, @Param("Id")Long id);

//    @Transactional
//    @Modifying
//    @Query(value="update user set first_name = :FirstName where id= :Id",nativeQuery = true)
//    public void doUpdateFirstName(@Param("Id")Long id,@Param("FirstName")String firstName);


}
