package com.op.permission;

import java.util.Optional;

public interface PermissionService {
    Optional<Permission> updateById(String id, Permission permission);
}
