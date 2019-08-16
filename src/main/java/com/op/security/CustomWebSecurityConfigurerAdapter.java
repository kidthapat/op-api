package com.op.security;

import com.op.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    private Config config;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
<<<<<<< HEAD
                .antMatchers(HttpMethod.POST, "/login","/users").permitAll()
                .anyRequest().authenticated().and().httpBasic();
=======
                .antMatchers(HttpMethod.POST, "/login", "/users").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().maximumSessions(1);
>>>>>>> 6bbf0fbd787bdc45e81ff219e814f5a2b6e8eb4f

        Filter filter = new CustomAbstractAuthenticationProcessingFilter("/login", authenticationManager());

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(config.getUserDetailsService());
    }
}
