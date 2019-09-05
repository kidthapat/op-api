package com.op.role;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, String> {
    List<Role> findAll();

    Optional<Role> findByName(String roleName);
}
