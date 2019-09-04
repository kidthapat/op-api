package com.op.role;

import com.op.constant.Api;
import com.op.permission.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(Api.v1)
@RestController()
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity findAll() {
        List<Role> list = roleService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/roles/{roleName}/permissions")
    public ResponseEntity findPermissionsByRoleName(@PathVariable("roleName") String roleName) {
        Role role = new Role();
        role.setName(roleName);
        List<Permission> list = roleService.findPermissionsByRole(role);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
