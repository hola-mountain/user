package com.holamountain.userdomain.service.mypage;

import com.holamountain.userdomain.dto.response.mypage.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MypageService {
    Mono<MypageInfoResponse> userInfo(ServerRequest serverRequest);

    Flux<MyBadgeInfoResponse> myBadges(ServerRequest serverRequest);

    Mono<MypageLeaveResponse> leave(ServerRequest serverRequest);

    Flux<MyFavoriteMountainResponse> myFavorite(ServerRequest serverRequest);

    Flux<MyMountainReviewResponse> myReview(ServerRequest serverRequest);
}
