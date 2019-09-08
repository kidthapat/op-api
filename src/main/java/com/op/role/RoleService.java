package com.op.role;

import com.op.permission.Permission;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Permission> findPermissionsByRole(Role role);

    List<Role> findAll();

    Role create(Role role);

    Optional<Role> updateById(String id, Role role);
}
