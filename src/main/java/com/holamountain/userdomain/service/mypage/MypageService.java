package com.holamountain.userdomain.service.mypage;

import com.holamountain.userdomain.dto.response.MypageInfoResponse;
import com.holamountain.userdomain.dto.response.UserLoginResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface MypageService {
    Mono<MypageInfoResponse> userInfo(ServerRequest serverRequest);
}
