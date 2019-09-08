package com.op.user;

import com.op.role.Role;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll();
        return list;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()) {
            optional.get().setPassword(null); // secure a password
        }
        return optional;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> optional = userRepository.findByEmail(email);
        if(optional.isPresent()) {
            optional.get().setPassword(null); // secure a password
        }
        return optional;
    }

    @Override
    public User create(User user) {
        String encodePassword = getPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateById(String id, User user) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User existedUser = optional.get();

            existedUser.setEmail(user.getEmail());
            existedUser.setFirstName(user.getFirstName());
            existedUser.setLastName(user.getLastName());
            existedUser.setPhoneNo(user.getPhoneNo());
            existedUser.setRole(user.getRole());

            userRepository.save(existedUser);
        }
        return optional;
    }

    @Override
    public Optional<User> deleteById(ObjectId id) {
        Optional<User> optional = userRepository.findBy_id(id);
        if (optional.isPresent()) {
            userRepository.delete(optional.get());
        }
        return optional;
    }
}
