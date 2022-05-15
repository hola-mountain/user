package com.holamountain.userdomain.service.users;

import com.holamountain.userdomain.common.UserEnums.UserType;
import com.holamountain.userdomain.dto.response.UserLoginResponse;
import com.holamountain.userdomain.dto.response.UserRegistrationResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserLoginResponse> userLogin(ServerRequest serverRequest);

    Mono<UserRegistrationResponse> userRegistration(ServerRequest serverRequest, UserType userType);
}
