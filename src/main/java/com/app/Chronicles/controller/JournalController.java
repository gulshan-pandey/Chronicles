package com.app.Chronicles.controller;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.service.JournalEntryService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalController {


    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping("/getEntries")
    public ResponseEntity<?> getAllEntries(){
        List<JournalEntry> all = journalEntryService.findAll();
        if(all!=null){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/addEntry")
    public ResponseEntity<JournalEntry> addEntry(@RequestBody JournalEntry entry){
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("getEntry/{id}")
    public ResponseEntity<JournalEntry> getByid(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntity = journalEntryService.findById(id);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(journalEntity.get(), HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("deleteEntry/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id){
         journalEntryService.deleteById(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("updateEntry/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
