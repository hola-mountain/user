package com.holamountain.userdomain.service.users;

import com.holamountain.userdomain.common.UserEnums.UserType;
import com.holamountain.userdomain.dto.response.users.UserLoginResponse;
import com.holamountain.userdomain.dto.response.users.UserLogoutResponse;
import com.holamountain.userdomain.dto.response.users.UserRegistrationResponse;
import com.holamountain.userdomain.dto.response.jwt.JwtTokenResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserLoginResponse> userLogin(ServerRequest serverRequest);

    Mono<UserLogoutResponse> userLogout(ServerRequest serverRequest);

    Mono<UserRegistrationResponse> userRegistration(ServerRequest serverRequest, UserType userType);

    Mono<JwtTokenResponse> reIssueAccessJwtToken(ServerRequest serverRequest);
}
