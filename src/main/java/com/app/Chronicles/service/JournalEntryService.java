package com.app.Chronicles.service;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class JournalEntryService {

    private static final Logger log = LoggerFactory.getLogger(JournalEntryService.class);


    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveNewEntry(JournalEntry entry, String username) {
        try {
            User user = userService.findByUsername(username);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalRepo.save(entry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);                    // this is for updating the user with the new journal entry
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
        }
    }


    public void saveExistingEntry(JournalEntry entry) {
        JournalEntry saved = journalRepo.save(entry);
    }


    public List<JournalEntry> findAll() {
        return journalRepo.findAll();
    }


    public Optional<JournalEntry> findById(ObjectId id) {
        return journalRepo.findById(id);
    }


    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
            removed = user.getJournalEntries().removeIf(journal -> journal.getId().equals(id));// basically we are removing the specific reference of the journal from the user table, bcz we are about to delete that journal form the database
//         this prevents from data inconsistency
            if (removed) {                     // remove if the entry is removed from the user table, this can prevent if someone mistakenly gives the another's journal Id
                userService.saveEntry(user);                    // this is for updating the user with the new journal entry.
                journalRepo.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occured while deleting the journal entry", e);
        }
        return removed;
    }


}
