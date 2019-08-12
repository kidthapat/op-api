package com.op.merchant;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
    Optional<Merchant> findBy_id(ObjectId id);
}
