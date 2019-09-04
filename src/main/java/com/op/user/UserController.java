package com.op.user;

import com.op.constant.Api;
import com.op.role.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping(Api.v1)
@RestController()
public class UserController {
    private static Log LOG = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity findAll() {
        List list = userService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        Optional<User> optional = userService.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity findByEmail(@PathVariable String email) {
        Optional<User> optional = userService.findByEmail(email);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody User user) {
        Optional<Role> optional = Optional.ofNullable(user.getRole());
        if(!optional.isPresent()){
            Role role = new Role();
            role.setName("USER");
            user.setRole(role);
        }
        User createdUser = userService.create(user);
        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody User user) {
        LOG.info("Call Update User: " + id);
        Optional<User> optional = userService.updateById(id, user);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable ObjectId id) {
        Optional<User> optional = userService.deleteById(id);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
