package com.app.Chronicles.controller;

import com.app.Chronicles.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalController {


    @GetMapping("/getEntries")
    public List<JournalEntry> getAllEntries(){
        return null;
    }

    @PostMapping("/addEntry")
    public boolean addEntry(@RequestBody JournalEntry entry){

        return true;
    }

    @GetMapping("getEntry/{id}")
    public JournalEntry getByid(@PathVariable long id){
        return null;
    }

    @DeleteMapping("deleteEntry/{id}")
    public JournalEntry deleteEntry(@PathVariable long id){
        return null;
    }

    @PutMapping("updateEntry/{id}")
    public JournalEntry updateEntry(@PathVariable long id, @RequestBody JournalEntry entry){
        return null;
    }
}
