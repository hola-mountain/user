package com.holamountain.userdomain.handler;

import com.holamountain.userdomain.dto.response.mypage.*;
import com.holamountain.userdomain.dto.response.users.UserLoginResponse;
import com.holamountain.userdomain.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
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

    public Mono<ServerResponse> myBadges(ServerRequest serverRequest) {

        Flux<MyBadgeInfoResponse> response = mypageService.myBadges(serverRequest)
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, UserLoginResponse.class);
    }

    public Mono<ServerResponse> leave(ServerRequest serverRequest) {
        Mono<MypageLeaveResponse> leaveResponse = mypageService.leave(serverRequest)
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(leaveResponse, MypageLeaveResponse.class);
    }

    public Mono<ServerResponse> myFavorite(ServerRequest serverRequest) {
        Flux<MyFavoriteMountainResponse> leaveResponse = mypageService.myFavorite(serverRequest)
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(leaveResponse, MypageLeaveResponse.class);
    }

    public Mono<ServerResponse> myReview(ServerRequest serverRequest) {
        Flux<MyMountainReviewResponse> leaveResponse = mypageService.myReview(serverRequest)
                .subscribeOn(Schedulers.boundedElastic());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(leaveResponse, MypageLeaveResponse.class);
    }
}
