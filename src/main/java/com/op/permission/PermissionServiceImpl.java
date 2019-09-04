package com.op.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Optional<Permission> updateById(String id, Permission permission) {
        Optional<Permission> optional = permissionRepository.findById(id);
        if (optional.isPresent()) {
            Permission existedPermission = optional.get();
            existedPermission.setName(permission.getName());

            permissionRepository.save(existedPermission);
        }
        return optional;
    }
}
