package com.op.security;

import com.op.permission.Permission;
import com.op.permission.PermissionRepository;
import com.op.user.Role;
import com.op.user.User;
import com.op.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static org.springframework.security.core.userdetails.User.withUsername;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByEmail(email);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        User user = optional.get();
        UserBuilder builder = withUsername(user.getEmail());
        builder.password(user.getPassword());

        List<String> authorities = new ArrayList();
        permissionRepository.findAll().forEach(permission -> {
            Set<Role> roles = Optional.ofNullable(permission.getRoles()).orElse(new HashSet<>());
            for (Role role : roles) {
                if (role.getName().equalsIgnoreCase(user.getRole().getName())) {
                    authorities.add(permission.getName());
                    break;
                }
            }
        });

        builder.authorities(authorities.toArray(new String[authorities.size()]));

        return builder.build();
    }
}
