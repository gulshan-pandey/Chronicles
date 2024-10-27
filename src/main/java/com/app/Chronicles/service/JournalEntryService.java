package com.app.Chronicles.service;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    private static final Logger log = LoggerFactory.getLogger(JournalEntryService.class);
    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry entry, String username) {
        try {
            User user = userService.findByUsername(username);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalRepo.save(entry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);                    // this is for updating the user with the new journal entry
        } catch (Exception e) {
            log.error("Exception : " +  e.getMessage());
        }
    }

    public void saveEntry(JournalEntry entry) {
            JournalEntry saved = journalRepo.save(entry);
    }


    public List<JournalEntry> findAll(){
        return journalRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalRepo.findById(id);
    }

    public void deleteById(ObjectId id, String username){
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf( journal -> journal.getId().equals(id));          // basicallly we are removing the specific reference of the journal from the user table, bcz we are about to delete that journal form the database
//         this prevents from data inconsistency
        userService.saveEntry(user);                    // this is for updating the user with the new journal entry.
        journalRepo.deleteById(id);
    }

}
