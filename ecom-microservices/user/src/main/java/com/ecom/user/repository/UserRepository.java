package com.ecom.user.repository;

import com.ecom.user.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, String> {

}
