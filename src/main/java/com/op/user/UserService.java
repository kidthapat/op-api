package com.op.user;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    User create(User user);

    Optional<User> updateById(ObjectId id, User user);

    Optional<User> deleteById(ObjectId id);
}
