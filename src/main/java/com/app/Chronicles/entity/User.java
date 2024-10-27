package com.app.Chronicles.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_entries")
@Data
public class User {

   @Id
   private ObjectId id;

   @Indexed(unique = true)       // for doing it by default we need to configure in Application.props
   @NonNull                    // given by Lombok
   private  String username;

   @NonNull
   private String password;

   @DBRef            // use for referencing to somewhere as a ForeignKey in mongoDB
   private List<JournalEntry> journalEntries = new ArrayList<>();
   private List<String> roles;

}
