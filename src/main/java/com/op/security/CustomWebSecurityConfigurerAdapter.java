package com.op.security;

import com.op.constant.Api;
import com.op.rememberme.RememberMePersistentTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.Filter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private String login = Api.v1 + "/login";
    private String regiserUser = Api.v1 + "/users";
    private String findAllMerchants = Api.v1 + "/merchants";

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PersistentTokenRepository getRememberMeTokenService() {
        return new RememberMePersistentTokenRepository();
    }

    @Bean
    public BasicAuthenticationEntryPoint getBasicAuthenticationEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, login, regiserUser).permitAll()
                .antMatchers(HttpMethod.GET, findAllMerchants).permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic().authenticationEntryPoint(getBasicAuthenticationEntryPoint())
                .and().sessionManagement().maximumSessions(1);

        Filter filter = new CustomAbstractAuthenticationProcessingFilter(login, authenticationManager(), getApplicationContext());
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
