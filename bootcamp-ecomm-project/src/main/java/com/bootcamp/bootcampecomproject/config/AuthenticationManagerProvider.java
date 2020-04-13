package com.bootcamp.bootcampecomproject.config;

import com.bootcamp.bootcampecomproject.listeners.AuthenticationFailureEventListener;
import com.bootcamp.bootcampecomproject.listeners.AuthenticationSuccessEventListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
public class AuthenticationManagerProvider extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public ApplicationListener loginSuccessListener(){
        return new AuthenticationSuccessEventListener();
    }

    @Bean
    public ApplicationListener loginFailureListener(){
        return new AuthenticationFailureEventListener();
    }
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

}