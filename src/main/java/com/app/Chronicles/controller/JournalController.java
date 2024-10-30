package com.app.Chronicles.controller;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.entity.User;
import com.app.Chronicles.service.JournalEntryService;
import com.app.Chronicles.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@RestController
@RequestMapping("/journal")
public class JournalController {


    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<?> findAllJournalEntriesOfUser() {

        //the security context holder will give us the current authenticated user from the SpringConfig file
        //whenever the user is authenticated, its details gets stored in security context holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PostMapping
    public ResponseEntity<JournalEntry> addEntry(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.saveNewEntry(entry, username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return user.getJournalEntries().stream()
                .filter(journalEntry -> journalEntry.getId().equals(myId))
                .findFirst()
                .map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


// the below code is also working and more xplanatroy

/*

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();     // getting the current authenticated user from SecurityContextHolder
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(journalEntry -> journalEntry.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntity = journalEntryService.findById(myId);
            if (journalEntity.isPresent()) {
                return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

*/


    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(id, username);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id,
                                         @RequestBody JournalEntry newEntry) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        boolean isOwner = user.getJournalEntries().stream().anyMatch(journalEntry -> journalEntry.getId().equals(id));

        if (isOwner) {
            journalEntryService.findById(id).ifPresentOrElse(oldEntry -> {
                oldEntry.setTitle(Optional.ofNullable(newEntry.getTitle()).filter(title -> !title.isEmpty()).orElse(oldEntry.getTitle()));
                oldEntry.setContent(Optional.ofNullable(newEntry.getContent()).filter(content -> !content.isEmpty()).orElse(oldEntry.getContent()));
                journalEntryService.saveExistingEntry(oldEntry);
            }, () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // more Simplified

/*

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id,
                                         @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> journals = user.getJournalEntries().stream().filter(journalEntry -> journalEntry.getId().equals(id)).collect(Collectors.toList());

        if (!journals.isEmpty()) {              // this check is to prevent if the authenticated person want to update someone else's entry
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            if (journalEntry.isPresent()) {
                JournalEntry oldEntry = journalEntry.get();
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
                journalEntryService.saveExistingEntry(oldEntry);
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

*/


}
