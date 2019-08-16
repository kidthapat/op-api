package com.op.config;

import com.op.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
<<<<<<< HEAD
    public PasswordEncoder getPasswordEncoder(){
=======
    public PasswordEncoder getPasswordEncoder() {
>>>>>>> 6bbf0fbd787bdc45e81ff219e814f5a2b6e8eb4f
        return new BCryptPasswordEncoder();
    }
}
