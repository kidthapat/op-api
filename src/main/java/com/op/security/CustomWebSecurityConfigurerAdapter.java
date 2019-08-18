package com.op.security;

import com.op.login.RememberMePersistentTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.Filter;

@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PersistentTokenRepository getRememberMeTokenService() {
        return new RememberMePersistentTokenRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login", "/users").permitAll()
                .anyRequest().authenticated()
//                .and().rememberMe().tokenValiditySeconds(120).key("unique-and-secret")
//                .rememberMeCookieName("remember-me").tokenRepository(getRememberMeTokenService())
//                .rememberMeParameter("remember-me")
                .and().httpBasic()
                .and().sessionManagement().maximumSessions(1);

        Filter filter = new CustomAbstractAuthenticationProcessingFilter("/login", authenticationManager());
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService());
    }
}
