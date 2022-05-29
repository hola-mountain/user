package com.holamountain.userdomain.kafka;

import com.google.gson.Gson;
import com.holamountain.userdomain.dto.response.kafka.ReviewCountResponse;
import com.holamountain.userdomain.model.AchievementEntity;
import com.holamountain.userdomain.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final AchievementRepository achievementRepository;

    @KafkaListener(topics = "mountainbadge", groupId = "mountainRating")
    public void consume(String message) throws IOException {
        Gson gson = new Gson();
        ReviewCountResponse reviewCountResponse = gson.fromJson(message, ReviewCountResponse.class);
        Long standardBadgeId = new Long(1);

        achievementRepository.findByUserIdAndBadgeId(reviewCountResponse.getUserId(), standardBadgeId).hasElement()
                .map(user -> {
                    if (!user)
                        return registrationArchievement(reviewCountResponse.getUserId(), standardBadgeId).subscribe();
                    return user;
                }).subscribe();

        System.out.println("Sucess");
    }

    private Mono<AchievementEntity> registrationArchievement(Long userId, Long badgeId) {
        AchievementEntity achievementEntity = AchievementEntity.builder()
                .achievementNum(new Long(1))
                .badgeId(badgeId)
                .userId(userId)
                .build();

        return achievementRepository.save(achievementEntity).log();
    }
}
