package com.app.Chronicles.controller;

import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalController {


    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping("/getEntries")
    public List<JournalEntry> getAllEntries(){

        return journalEntryService.findAll();
    }


    @PostMapping("/addEntry")
    public JournalEntry addEntry(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return entry;
    }


    @GetMapping("getEntry/{id}")
    public JournalEntry getByid(@PathVariable ObjectId id){
        return journalEntryService.findById(id).orElse(null);
    }


    @DeleteMapping("deleteEntry/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id){
         journalEntryService.deleteById(id);
         return true;
    }

    @PutMapping("updateEntry/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }


}
