
package com.holamountain.userdomain.repository;

import com.holamountain.userdomain.dto.response.UserLoginResponse;
import com.holamountain.userdomain.model.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
    Mono<UserLoginResponse> findByUserIdAndPassword(Long id, String password);

    Mono<UserLoginResponse> findByNickNameAndPassword(String nickName, String password);
}
