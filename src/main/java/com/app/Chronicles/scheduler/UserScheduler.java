package com.app.Chronicles.scheduler;

import com.app.Chronicles.cache.AppCache;
import com.app.Chronicles.entity.JournalEntry;
import com.app.Chronicles.entity.User;
import com.app.Chronicles.repository.UserRepoImpl;
import com.app.Chronicles.service.EmailService;
import com.app.Chronicles.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    UserRepoImpl userRepoImpl;

    @Autowired
    SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    EmailService emailService;

    @Autowired
    AppCache appCache;


//    @Scheduled(cron = "0 * * * * *")        // run every minute
    @Scheduled(cron = "0 0 8 * * 0")      // run every Sunday at 8am
    public void fetchUserForSA(){
        List<User> users =userRepoImpl.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredJournals = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(10)))
                    .map( x -> x.getContent()).toList();

            String totalEntries = filteredJournals.stream().collect(Collectors.joining("\n"));
            String sentiment = sentimentAnalysisService.getSentiment(totalEntries);

            emailService.sendEmail(user.getEmail(),"from Backend", sentiment);

        }
    }

    @Scheduled(cron = "0 */10 * * * ?")        // run on every 10 minutes
    public void clearCache(){
        appCache.init();
    }
}