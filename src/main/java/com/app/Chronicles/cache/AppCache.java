package com.app.Chronicles.cache;

import com.app.Chronicles.entity.JournalConfigs;
import com.app.Chronicles.repository.ConfigRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum Keys {
        WEATHER_API
    }

    @Autowired
    private ConfigRepo configRepo;

    public Map<String, String> cache;


    @PostConstruct
    public void init(){
        cache = new HashMap<String, String>();
        List<JournalConfigs> all = configRepo.findAll();
        for (JournalConfigs journalConfigs : all) {
            cache.put(journalConfigs.getKey(),journalConfigs.getValue());
        }
    }
}
