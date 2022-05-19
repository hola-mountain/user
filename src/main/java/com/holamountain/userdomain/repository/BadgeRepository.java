package com.holamountain.userdomain.repository;

import com.holamountain.userdomain.model.AchievementEntity;
import com.holamountain.userdomain.model.BadgeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends ReactiveCrudRepository<BadgeEntity, Long> {
}
