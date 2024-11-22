package com.app.Chronicles.entity;

import com.app.Chronicles.constants.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//@Getter
//@Setter
@Data           // is equivalent to @Getter and @Setter
@NoArgsConstructor
@Document(collection = "my_journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;        // every journal will have its unique id

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

    private Sentiment sentiment;

}