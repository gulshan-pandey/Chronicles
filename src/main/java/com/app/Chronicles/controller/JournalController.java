package com.app.Chronicles.controller;

import com.app.Chronicles.entity.journalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalController {


    @GetMapping("/getEntries")
    public List<journalEntry> getAllEntries(){
        return null;
    }

    @PostMapping("/addEntry")
    public boolean addEntry(@RequestBody journalEntry entry){

        return true;
    }

    @GetMapping("getEntry/{id}")
    public journalEntry getByid(@PathVariable long id){
        return null;
    }

    @DeleteMapping("deleteEntry/{id}")
    public journalEntry deleteEntry(@PathVariable long id){
        return null;
    }

    @PutMapping("updateEntry/{id}")
    public journalEntry updateEntry(@PathVariable long id, @RequestBody journalEntry entry){
        return null;
    }
}
