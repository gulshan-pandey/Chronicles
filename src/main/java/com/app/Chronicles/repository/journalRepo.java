package com.app.Chronicles.repository;

import com.app.Chronicles.entity.journalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface journalRepo extends MongoRepository<journalEntry, String> {



}
