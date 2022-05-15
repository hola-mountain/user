
package com.holamountain.userdomain.handler;

import com.holamountain.userdomain.common.UserEnums.UserType;
import com.holamountain.userdomain.dto.response.UserLoginResponse;
import com.holamountain.userdomain.dto.response.UserRegistrationResponse;
import com.holamountain.userdomain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
@Component
public class UserHandler {
    private final UserService userService;

    public Mono<ServerResponse> login(ServerRequest serverRequest) {

        Mono<UserLoginResponse> response = userService.userLogin(serverRequest)
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
}
