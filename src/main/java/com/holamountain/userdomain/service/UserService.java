package com.holamountain.userdomain.service;

import com.holamountain.userdomain.dto.request.UserLoginRequest;
import com.holamountain.userdomain.dto.response.UserLoginResponse;
import com.holamountain.userdomain.model.UserEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface UserService {
    Mono<UserLoginResponse> loginService(UserLoginRequest userLoginRequest);
}
