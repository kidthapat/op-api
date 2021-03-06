package com.op.role;

import com.op.permission.Permission;
import com.op.permission.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Override
    public List<Permission> findPermissionsByRole(Role role) {
        return getAuthoritiesByJava(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role create(Role role) {
        Optional<String> roleNameOptional = Optional.ofNullable(role.getName());
        if (!roleNameOptional.isPresent()) {
            role.setName("USER");
        }
        role.setName(role.getName().toUpperCase());
        Optional<Role> roleOptional = roleRepository.findByName(role.getName());
        if(roleOptional.isPresent()) {
            return roleOptional.get();
        }
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> updateById(String id, Role role) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            Role existedRole = optional.get();
            existedRole.setName(role.getName().toUpperCase());

            roleRepository.save(existedRole);
        }
        return optional;
    }

    private List<Permission> getAuthoritiesByJava(Role role) {
        List<Permission> authorities = new ArrayList<>();
        permissionRepository.findAll().forEach(permission -> {
            Set<Role> roles = Optional.ofNullable(permission.getRoles()).orElse(new HashSet<>());
            for (Role r : roles) {
                if (r.getName().equalsIgnoreCase(role.getName())) {
                    authorities.add(permission);
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