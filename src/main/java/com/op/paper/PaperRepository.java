package com.op.paper;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaperRepository extends MongoRepository<Paper, String> {
//    @Override
    Optional<Paper> findById(String s);
}
