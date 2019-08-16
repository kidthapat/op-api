package com.op.user;

import com.op.config.Config;
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
    private UserRepository userRepository;

    @Autowired
    private Config config;

    @GetMapping
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user){
        String encodePassword =config.getPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public Optional<User> update(@PathVariable ObjectId id, @RequestBody User user){
        Optional<User> optional = userRepository.findBy_id(id);
        if (optional.isPresent()){
            User existedUser = optional.get();
            existedUser.setEmail(user.getEmail());
            userRepository.save(existedUser);
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
