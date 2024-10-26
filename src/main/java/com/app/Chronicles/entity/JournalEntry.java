package com.app.Chronicles.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//@Getter
//@Setter
@Data           // is equivalent to @Getter and @Setter
@Document(collection = "my_journal_entries" )
public class JournalEntry {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;


}
