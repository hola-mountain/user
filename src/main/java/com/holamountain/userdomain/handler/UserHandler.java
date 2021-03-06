
package com.holamountain.userdomain.handler;

import com.holamountain.userdomain.common.UserEnums.UserType;
import com.holamountain.userdomain.dto.response.jwt.JwtTokenResponse;
import com.holamountain.userdomain.dto.response.users.UserLoginResponse;
import com.holamountain.userdomain.dto.response.users.UserLogoutResponse;
import com.holamountain.userdomain.dto.response.users.UserRegistrationResponse;
import com.holamountain.userdomain.dto.response.users.VerifyUserRegistrationResponse;
import com.holamountain.userdomain.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
@Component
public class UserHandler {
    private final UserService userService;

    public Mono<ServerResponse> login(ServerRequest serverRequest) {

        Mono<UserLoginResponse> response = userService.userLogin(serverRequest).log()
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, UserLoginResponse.class);
    }

    public Mono<ServerResponse> join(ServerRequest request) {
        Mono<UserRegistrationResponse> response = userService.userRegistration(request,
                request.path().contains(UserType.ADMIN.getName().toLowerCase()) ?
                        UserType.ADMIN
                        : UserType.CUSTOMER
        )
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, UserRegistrationResponse.class);
    }

    public Mono<ServerResponse> reIssue(ServerRequest request) {
        Mono<JwtTokenResponse> response = userService.reIssueAccessJwtToken(request).log()
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, JwtTokenResponse.class);
    }

    public Mono<ServerResponse> logout(ServerRequest request) {
        Mono<UserLogoutResponse> response = userService.userLogout(request).log()
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, JwtTokenResponse.class);
    }

    public Mono<ServerResponse> verify(ServerRequest request) {
        Mono<VerifyUserRegistrationResponse> replaceMessage = userService.verifyUserRegistration(request)
                                                    .subscribeOn(Schedulers.boundedElastic());


        return ServerResponse
                .ok()
                .contentType(MediaType.valueOf("text/html;charset=UTF-8"))
                .body(replaceMessage.map(VerifyUserRegistrationResponse::getResultMessage), String.class);
    }
}
