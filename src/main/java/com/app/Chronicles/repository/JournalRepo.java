package com.app.Chronicles.repository;

import com.app.Chronicles.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalRepo extends MongoRepository<JournalEntry, ObjectId> {



}
