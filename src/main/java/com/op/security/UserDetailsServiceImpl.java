package com.op.security;

import com.op.user.User;
import com.op.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optional = repository.findByEmail(email);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        User user = optional.get();
        UserBuilder builder = withUsername(user.getEmail());
        builder.password(user.getPassword());
        builder.authorities("ADMIN");

        return builder.build();
    }
}
