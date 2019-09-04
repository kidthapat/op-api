package com.op.permission;

import com.op.constant.Api;
import com.op.user.UserController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(Api.v1)
@RestController
public class PermisionController {
    private static Log LOG = LogFactory.getLog(PermisionController.class);

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping("/permissions")
    public ResponseEntity findAll() {
        List<Permission> list = permissionRepository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/permissions")
    public ResponseEntity create(@RequestBody Permission permission) {
        LOG.info("Call Create Permission Name: " + permission.getName());

        Permission createPermission = permissionRepository.save(permission);
        return new ResponseEntity(createPermission, HttpStatus.OK);
    }
}
