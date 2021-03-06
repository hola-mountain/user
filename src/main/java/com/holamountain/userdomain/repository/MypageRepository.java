package com.holamountain.userdomain.repository;

import com.holamountain.userdomain.common.UserEnums.UserType;
import com.holamountain.userdomain.model.AchievementEntity;
import com.holamountain.userdomain.model.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MypageRepository extends ReactiveCrudRepository<UserEntity, Long> {
    Mono<UserEntity> findByUserId(String userId);
}
