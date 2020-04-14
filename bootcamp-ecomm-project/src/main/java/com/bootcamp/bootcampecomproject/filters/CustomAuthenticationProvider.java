package com.bootcamp.bootcampecomproject.filters;

import com.bootcamp.bootcampecomproject.dao.UserAttemptsDao;
import com.bootcamp.bootcampecomproject.entities.User;
import com.bootcamp.bootcampecomproject.entities.UserAttempts;
import com.bootcamp.bootcampecomproject.exception.UserNotFoundException;
import com.bootcamp.bootcampecomproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("Filter is running");
//        chain.doFilter(request,response);
//    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        return null;
//    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAttemptsDao userAttemptsDao;

    @Override
    public Authentication authenticate(Authentication authentication) {

        try {
            Authentication auth = super.authenticate(authentication);
            System.out.println(auth);
            String email=authentication.getName();
            User user=userRepository.findByEmail(email);

            if(user!=null)
            {
                UserAttempts userAttempts=userAttemptsDao.getUserAttemps(email);
                if(userAttemptsDao.checkLock(email) && userAttemptsDao.checkIsActive(email)){
                    userAttemptsDao.updateAttemptsToNull(email);
                    return auth;
                }
                else {
                    user.setAccountNonLocked(false);
                    userRepository.save(user);
                    throw new UserNotFoundException("User is Locked");
                }
            }
            System.out.println(authentication.getName());

        }
        catch (Exception e){
            String email=authentication.getName();
            userAttemptsDao.getUserAttemps(email);
            userAttemptsDao.updateAttempts(email);
            throw new UserNotFoundException("User is invalid");
        }
        return null;
    }

//    public CustomFilter(String url, AuthenticationManager authenticationManager){
//        super(new AntPathRequestMatcher(url,HttpMethod.POST.name()));
//    }
}
