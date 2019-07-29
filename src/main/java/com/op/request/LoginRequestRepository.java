package com.op.request;

import com.op.request.LoginRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRequestRepository extends MongoRepository<LoginRequest, String> {

}
