package com.op.role;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, String> {
    List<Role> findAll();
}
