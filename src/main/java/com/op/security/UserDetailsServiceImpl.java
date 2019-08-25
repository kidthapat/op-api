package com.op.security;

import com.op.permission.PermissionRepository;
import com.op.user.Role;
import com.op.user.User;
import com.op.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
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
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByEmail(email);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        User user = optional.get();
        UserBuilder builder = withUsername(user.getEmail());
        builder.password(user.getPassword());

        builder.authorities(getAuthoritiesByJava(user));
//        builder.authorities(getAuthoritiesByMongoTeamplate(user));

        return builder.build();
    }

    private String[] getAuthoritiesByJava(User user) {
        List<String> authorities = new ArrayList<>();
        permissionRepository.findAll().forEach(permission -> {
            Set<Role> roles = Optional.ofNullable(permission.getRoles()).orElse(new HashSet<>());
            for (Role role : roles) {
                if (role.getName().equalsIgnoreCase(user.getRole().getName())) {
                    authorities.add(permission.getName());
                    break;
                }
            }
        });

        return authorities.toArray(new String[authorities.size()]);
    }

    private String[] getAuthoritiesByMongoTeamplate(User user) {
        MatchOperation matchOperation = Aggregation.match(new Criteria("email").is(user.getEmail()));

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("permissions")
                .localField("role.name")
                .foreignField("roles.name")
                .as("authorities");

        Aggregation aggregation = Aggregation.newAggregation(matchOperation, lookupOperation);

        List<AuthorityResult> authorityResults = mongoTemplate.aggregate(aggregation, User.class, AuthorityResult.class).getMappedResults();
        AuthorityResult authorityResult = authorityResults.get(0);

        return authorityResult.getAuthorities().stream().map(a -> a.getName()).toArray(String[]::new);
    }
}

class AuthorityResult {
    private List<Authority> authorities;

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}

class  Authority {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}