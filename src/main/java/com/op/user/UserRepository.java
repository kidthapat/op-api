package com.op.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{}", fields = "{ 'password' : 0 }")
    List<User> findAll();

    Optional<User> findBy_id(ObjectId id);

    Optional<User> findByEmail(String email);
}
