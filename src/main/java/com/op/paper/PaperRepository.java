package com.op.paper;

import com.op.order.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaperRepository extends MongoRepository<Paper, String> {
    Optional<Order> findBy_id(ObjectId id);
}
