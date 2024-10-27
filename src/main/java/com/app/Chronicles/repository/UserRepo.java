package com.app.Chronicles.repository;

import com.app.Chronicles.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {

  User findByUsername(String username);

  void deleteByUsername(String username);
}