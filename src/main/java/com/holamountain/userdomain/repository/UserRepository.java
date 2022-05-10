
package com.holamountain.userdomain.repository;

import com.holamountain.userdomain.model.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
    Mono<UserEntity> findByUserIdAndPassword(Long id, String password);
}
