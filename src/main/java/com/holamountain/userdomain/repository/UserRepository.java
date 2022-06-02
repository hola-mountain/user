
package com.holamountain.userdomain.repository;

import com.holamountain.userdomain.common.UserEnums.UserType;
import com.holamountain.userdomain.model.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
    Mono<UserEntity> findByNickNameAndPassword(String nickName, String password);
    Mono<UserEntity> findByNickNameAndUserType(String nickName, UserType userType);
    Mono<UserEntity> findByNickName(String nickName);
}
