package com.bootcamp.bootcampecomproject.config;


import com.bootcamp.bootcampecomproject.dao.AppUserDetailsService;
import com.bootcamp.bootcampecomproject.filters.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    AppUserDetailsService userDetailsService;

    public ResourceServerConfiguration() {
        super();
    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register/seller").anonymous()
                .antMatchers("/reactivateUser").anonymous()
                .antMatchers("/registrationConfirm").anonymous()
                .antMatchers("/email/sendEmail").anonymous()
                .antMatchers("/address/user/{id}").anonymous()
                .antMatchers("/register/customer").anonymous()
                .antMatchers("/forgotPassword").anonymous()
                .antMatchers("/resetPassword").anonymous()
                .antMatchers("/uploadImage").hasAnyRole("CUSTOMER","SELLER")
                .antMatchers("/customer/profile").hasAnyRole("CUSTOMER")
                .antMatchers("/customer/address").hasAnyRole("CUSTOMER")
                .antMatchers("/customer/update").hasAnyRole("CUSTOMER")
                .antMatchers("/customer/deleteAddress").hasAnyRole("CUSTOMER")
                .antMatchers("/customer/addAddress").hasAnyRole("CUSTOMER")
                .antMatchers("/customer/updateAddress").hasAnyRole("CUSTOMER")
                .antMatchers("/customer/updatePassword").hasAnyRole("CUSTOMER")
                .antMatchers("/seller/profile").hasAnyRole("SELLER")
                .antMatchers("/seller/update").hasAnyRole("SELLER")
                .antMatchers("/seller/updatePassword").hasAnyRole("SELLER")
                .antMatchers("/seller/updateAddress").hasAnyRole("SELLER")
                .antMatchers("/admin/customers").hasAnyRole("ADMIN")
                .antMatchers("/admin/sellers").hasAnyRole("ADMIN")
                .antMatchers("/admin/activate/customer").hasAnyRole("ADMIN")
                .antMatchers("/admin/deactivate/customer").hasAnyRole("ADMIN")
                .antMatchers("/admin/activate/seller").hasAnyRole("ADMIN")
                .antMatchers("/admin/deactivate/seller").hasAnyRole("ADMIN")
                .antMatchers("/admin/home").hasAnyRole("ADMIN")
                .antMatchers("/user/home").hasAnyRole("USER")
                .antMatchers("/doLogout").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable();
                ;
    }

}