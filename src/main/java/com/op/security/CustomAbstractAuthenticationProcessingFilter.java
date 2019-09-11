package com.op.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.op.request.LoginRequest;
import com.op.user.User;
import com.op.user.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Optional;

public class CustomAbstractAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private UserService userService;

    protected CustomAbstractAuthenticationProcessingFilter(
            String url,
            AuthenticationManager manager,
            ApplicationContext context
    ) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(manager);
        this.userService = context.getBean(UserService.class);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

        Authentication authentication = getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword(),
                        Collections.emptyList()
                )
        );

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        String email = ((UserDetails) authResult.getPrincipal()).getUsername();
        Optional<User> optional = userService.findByEmail(email);
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        if (optional.isPresent()) {
            writer.print(new ObjectMapper().writeValueAsString(optional.get()));
        } else {
            writer.print(new ObjectMapper().writeValueAsString(authResult.getPrincipal()));
        }
    }
}
