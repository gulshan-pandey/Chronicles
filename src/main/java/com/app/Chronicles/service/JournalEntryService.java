package com.app.Chronicles.service;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Component
public class JournalEntryService {

    @Autowired
    private JournalRepo journalRepo;

    public void saveEntry(JournalEntry entry) {
        journalRepo.save(entry);
    }

    public List<JournalEntry> findAll(){
        return journalRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        journalRepo.deleteById(id);
    }

}
