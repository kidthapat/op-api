package com.op.role;

import com.op.constant.Api;
import com.op.permission.Permission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(Api.v1)
@RestController()
public class RoleController {
    private static Log LOG = LogFactory.getLog(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

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

    @PostMapping("/roles")
    public ResponseEntity create(@RequestBody Role role) {
        LOG.info("Call Create Role Name: " + role.getName());

        Role createRole = roleRepository.save(role);
        return new ResponseEntity(createRole, HttpStatus.OK);
    }
}
