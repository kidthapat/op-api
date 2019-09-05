package com.op.role;

import com.op.permission.Permission;

import java.util.List;

public interface RoleService {
    List<Permission> findPermissionsByRole(Role role);

    List<Role> findAll();

    Role create(Role role);
}
