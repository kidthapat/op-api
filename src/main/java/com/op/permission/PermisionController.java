package com.op.permission;

import com.op.constant.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(Api.v1)
@RestController
public class PermisionController {
    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping("/permissions")
    public ResponseEntity findAll() {
        List list = permissionRepository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
