package com.app.Chronicles.scheduler;

import com.app.Chronicles.cache.AppCache;
import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.UserRepoImpl;
import com.app.Chronicles.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    UserRepoImpl userRepoImpl;


    @Autowired
    EmailService emailService;

    @Autowired
    AppCache appCache;


    @Scheduled(cron = "0 * * * * *")        // run every minute
//    @Scheduled(cron = "0 0 8 * * 0")      // run every Sunday at 8am
    public void fetchUserForSA(){
        List<User> users =userRepoImpl.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredMoods = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(100)))
                    .map( x -> x.getSentiment().toString()).toList();

            String mailBody = generateBody(filteredMoods);

            emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days", mailBody);

        }
    }

    private  String generateBody(List<String> filteredMoods) {
        HashMap<String,Integer> sentimentMap = new HashMap<>();
        for (String Mood : filteredMoods) {
            if(Mood!=null){
                sentimentMap.put(Mood, sentimentMap.getOrDefault(Mood, 0) + 1);
            }
        }

        String mostCommonMood = null;
        int maxcount =0;
        for (Map.Entry<String, Integer> entry : sentimentMap.entrySet()) {
            if (entry.getValue() > maxcount) {
                maxcount = entry.getValue();
                mostCommonMood = entry.getKey();
            }
        }
        return "hii your most common mood is : " + mostCommonMood;
    }



    @Scheduled(cron = "0 */10 * * * ?")        // run on every 10 minutes
    public void clearCache(){
        appCache.init();
    }
}
