package com.op.permission;

import com.op.constant.Api;
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
@RestController
public class PermisionController {
    private static Log LOG = LogFactory.getLog(PermisionController.class);

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permissions")
    public ResponseEntity findAll() {
        List<Permission> list = permissionRepository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("permissions/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        Optional<Permission> optional = permissionRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/permissions")
    public ResponseEntity create(@RequestBody Permission permission) {
        LOG.info("Call Create Permission Name: " + permission.getName());

        Permission createPermission = permissionRepository.save(permission);
        return new ResponseEntity(createPermission, HttpStatus.OK);
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody Permission permission) {
        LOG.info("Call Update Permission Id: " + permission.get_id());

        Optional<Permission> optional = permissionService.updateById(id, permission);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
