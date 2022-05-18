package com.holamountain.userdomain.repository;

import com.holamountain.userdomain.model.AchievementEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AchievementRepository extends ReactiveCrudRepository<AchievementEntity, Long> {
    Flux<AchievementEntity> findByUserId(Long userId);
}

