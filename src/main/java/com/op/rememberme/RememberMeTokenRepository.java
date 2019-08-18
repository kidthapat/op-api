package com.op.rememberme;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RememberMeTokenRepository extends MongoRepository<RememberMeToken, String> {

    RememberMeToken findBySeries(String series);

    RememberMeToken findByUsername(String username);
}
