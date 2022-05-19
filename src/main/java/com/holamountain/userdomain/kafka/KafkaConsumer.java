package com.holamountain.userdomain.kafka;

import com.google.gson.Gson;
import com.holamountain.userdomain.repository.AchievementRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    AchievementRepository achievementRepository;

    @KafkaListener(topics = "mountainBadge", groupId = "foo")
    public void consume(String message) throws IOException {
//        Gson gson = new Gson();
//
//        achievementRepository.findByUserIdAndBadgeId();
//
//        return
    }
}
