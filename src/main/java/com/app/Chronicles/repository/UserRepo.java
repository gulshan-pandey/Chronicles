package com.app.Chronicles.repository;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {

  User findByUsername(String username);

  void deleteByUsername(String username);


}
