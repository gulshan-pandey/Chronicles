package com.app.Chronicles.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "Journaldb_Configs")
public class JournalConfigs {

   private String key;
    private String value;

}
