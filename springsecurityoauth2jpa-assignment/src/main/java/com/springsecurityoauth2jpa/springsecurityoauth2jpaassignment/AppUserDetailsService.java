package com.springsecurityoauth2jpa.springsecurityoauth2jpaassignment;

import com.springsecurityoauth2jpa.springsecurityoauth2jpaassignment.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String encryptedPassword = passwordEncoder.encode("pass");
        UserDetails userDetails = userDao.loadUserByUsername(username);
        return userDetails;
    }
}