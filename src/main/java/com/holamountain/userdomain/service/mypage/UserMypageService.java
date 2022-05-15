package com.holamountain.userdomain.service.mypage;

import com.holamountain.userdomain.dto.response.UserMyInfoResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface UserMypageService {
    Mono<UserMyInfoResponse> myInfo(ServerRequest serverRequest);
}
