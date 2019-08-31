package com.op.role;

import java.util.List;

public interface RoleService {
    List<String> findPermissionsByRole(Role role);
}
