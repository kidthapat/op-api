package com.op.user;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    User create(User user);

    Optional<User> updateById(String id, User user);

    Optional<User> deleteById(ObjectId id);
}
