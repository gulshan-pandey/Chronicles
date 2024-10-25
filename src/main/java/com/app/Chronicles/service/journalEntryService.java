package com.app.Chronicles.service;

import com.app.Chronicles.entity.journalEntry;
import com.app.Chronicles.repository.journalRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class journalEntryService {

    @Autowired
    private journalRepo journalRepo;

    public void saveEntry(journalEntry entry) {
        journalRepo.save(entry);
    }


}
