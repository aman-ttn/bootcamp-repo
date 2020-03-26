package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.GrantAuthorityImpl;
import com.bootcamp.bootcampecomproject.entities.AppUser;
import com.bootcamp.bootcampecomproject.entities.Role;
import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    UserRepository userRepository;


    public AppUser loadUserByUsername(String username) {
        List<GrantAuthorityImpl> grantAuthorityList=new ArrayList<>();
        User user = userRepository.findByEmail(username);
        System.out.println("--------------------"+user);
        List<Role> roles=user.getRoles();
        roles.forEach(r->{
            grantAuthorityList.add(new GrantAuthorityImpl(r.getAuthority()));
                }
                );

        if (username != null) {
            return new AppUser(
                    user.getEmail(),
                    user.getPassword(),
                    grantAuthorityList
            );
        } else {
            throw new RuntimeException();
        }
    }
}
