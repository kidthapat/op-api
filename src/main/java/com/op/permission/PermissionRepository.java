package com.op.permission;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, String> {
    List<Permission> findAll();
}
