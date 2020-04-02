package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivationDao {
    @Autowired
    UserRepository userRepository;
    public void doActivate(User user){
        userRepository.doActivate(user.getId());
    }
}
