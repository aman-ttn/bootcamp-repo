package com.bootcamp.bootcampecomproject.dao;

import com.bootcamp.bootcampecomproject.dao.UserDao;
import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AppUserDetailsService implements UserDetailsService {



    @Autowired
    private HttpServletRequest request;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String encryptedPassword = passwordEncoder.encode("pass");
        System.out.println("Trying to authenticate user ::" + username);
        System.out.println("Encrypted Password ::" + encryptedPassword);
        UserDetails userDetails = userDao.loadUserByUsername(username);
        return userDetails;
    }
}
