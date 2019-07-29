package com.op.user;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public Optional<User> update(@PathVariable ObjectId id, @RequestBody User user){
        Optional<User> optional = userRepository.findBy_id(id);
        if (optional.isPresent()){
            User users = optional.get();
            users.setEmail(user.getEmail());
            userRepository.save(user);
        }
        return optional;
    }

//    public boolean delete(@PathVariable ObjectId id){
//        Optional<User> optional = userRepository.findBy_id(id);
//        if (optional.isPresent()){
//            userRepository.delete(optional.get());
//            return true;
//        }
//        return false;
//    }

    @DeleteMapping("/{id}")
    public List<User> delete(@PathVariable ObjectId id){
        Optional<User> optional = userRepository.findBy_id(id);
        if (optional.isPresent()){
            userRepository.delete(optional.get());
        }
        return findAll();
    }
}
