package com.app.Chronicles.repository;

import com.app.Chronicles.entity.JournalConfigs;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ConfigRepo extends MongoRepository<JournalConfigs, ObjectId> {

}
