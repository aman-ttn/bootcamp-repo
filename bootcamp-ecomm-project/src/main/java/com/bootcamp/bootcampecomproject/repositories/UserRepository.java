package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.User;
import com.sun.tools.javac.comp.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {


    User findByEmail(String email);
    @Query(value="update user set is_active=true where id = :userId",nativeQuery = true)
    public void doActivate(@Param("userId")Long userId);

}
