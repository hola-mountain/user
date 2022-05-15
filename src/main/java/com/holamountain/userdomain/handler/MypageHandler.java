package com.holamountain.userdomain.handler;

import com.holamountain.userdomain.dto.response.MypageInfoResponse;
import com.holamountain.userdomain.dto.response.UserLoginResponse;
import com.holamountain.userdomain.service.mypage.MypageService;
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
public class MypageHandler {
    private final MypageService mypageService;

    public Mono<ServerResponse> myInfo(ServerRequest serverRequest) {

        Mono<MypageInfoResponse> response = mypageService.userInfo(serverRequest)
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, UserLoginResponse.class);
    }
}
