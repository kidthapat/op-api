package com.op.role;

import com.op.permission.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private PermissionRepository permissionRepository;
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Override
    public List<String> findPermissionsByRole(Role role) {
        return getAuthoritiesByJava(role);
    }

    private List<String> getAuthoritiesByJava(Role role) {
        List<String> authorities = new ArrayList<>();
        permissionRepository.findAll().forEach(permission -> {
            Set<Role> roles = Optional.ofNullable(permission.getRoles()).orElse(new HashSet<>());
            for (Role r : roles) {
                if (r.getName().equalsIgnoreCase(role.getName())) {
                    authorities.add(permission.getName());
                    break;
                }
            }
        });

        return authorities;
    }

//    private String[] getAuthoritiesByMongoTeamplate(User user) {
//        MatchOperation matchOperation = Aggregation.match(new Criteria("email").is(user.getEmail()));
//
//        LookupOperation lookupOperation = LookupOperation.newLookup()
//                .from("permissions")
//                .localField("role.name")
//                .foreignField("roles.name")
//                .as("authorities");
//
//        Aggregation aggregation = Aggregation.newAggregation(matchOperation, lookupOperation);
//
//        List<AuthorityResult> authorityResults = mongoTemplate.aggregate(aggregation, User.class, AuthorityResult.class).getMappedResults();
//        AuthorityResult authorityResult = authorityResults.get(0);
//
//        return authorityResult.getAuthorities().stream().map(a -> a.getName()).toArray(String[]::new);
//    }
}

//class AuthorityResult {
//    private List<Authority> authorities;
//
//    public List<Authority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(List<Authority> authorities) {
//        this.authorities = authorities;
//    }
//}
//
//class Authority {
//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}