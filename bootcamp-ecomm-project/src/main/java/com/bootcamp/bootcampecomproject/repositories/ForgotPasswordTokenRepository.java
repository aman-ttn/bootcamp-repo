package com.bootcamp.bootcampecomproject.repositories;

import com.bootcamp.bootcampecomproject.entities.ForgotPasswordToken;
import com.bootcamp.bootcampecomproject.entities.VerificationToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ForgotPasswordTokenRepository extends CrudRepository<ForgotPasswordToken,Long> {
    ForgotPasswordToken findByToken(String token);
    @Modifying
    @Transactional
    @Query(value="delete from forgot_password_token where token = :Token",nativeQuery = true)
    public void doDeleteByToken(@Param("Token")String token);

    @Modifying
    @Transactional
    @Query(value="delete from forgot_password_token where user_id = :Id",nativeQuery = true)
    public void doDeleteById(@Param("Id")Long id);
}
