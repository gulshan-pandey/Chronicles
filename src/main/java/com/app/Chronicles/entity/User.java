package com.app.Chronicles.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Document(collection = "user_entries")
@NoArgsConstructor
@AllArgsConstructor
public class User {

   @Id
   private ObjectId id;

   @Indexed(unique = true)
   @NonNull
   private String username;

   @NonNull
   private String password;

   private String email;
   private boolean sentimentAnalysis;


   @DBRef
   private List<JournalEntry> journalEntries = new ArrayList<>();

   @Builder.Default  // Default roles to an empty list in builder
   private List<String> roles = new ArrayList<>();
}
