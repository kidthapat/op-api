package com.op.role;

import com.op.constant.Api;
import com.op.permission.Permission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("roles/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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

        Role createdRole = roleService.create(role);
        return new ResponseEntity(roleService, HttpStatus.OK);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody Role role) {
        LOG.info("Call Update Role Id: " + role.get_id());

        Optional<Role> optional = roleService.updateById(id, role);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
