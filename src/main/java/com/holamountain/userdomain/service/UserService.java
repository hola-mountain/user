package com.holamountain.userdomain.service;

import com.holamountain.userdomain.model.UserEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface UserService {
    Mono<UserEntity> loginService(Map<String, Object> param);
}
