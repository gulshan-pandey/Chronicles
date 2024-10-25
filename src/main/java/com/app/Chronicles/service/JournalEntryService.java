package com.app.Chronicles.service;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.repository.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalEntryService {

    @Autowired
    private JournalRepo journalRepo;

    public void saveEntry(JournalEntry entry) {
        journalRepo.save(entry);
    }


}
