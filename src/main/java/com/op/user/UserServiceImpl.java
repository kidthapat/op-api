package com.op.user;

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
        list.forEach(user -> user.setPassword(""));
        return list;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> optional = userRepository.findByEmail(email);
        return optional;
    }

    @Override
    public User create(User user) {
        String encodePassword = getPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateById(ObjectId id, User user) {
        Optional<User> optional = userRepository.findBy_id(id);
        if (optional.isPresent()) {
            User existedUser = optional.get();
            existedUser.setEmail(user.getEmail());
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
