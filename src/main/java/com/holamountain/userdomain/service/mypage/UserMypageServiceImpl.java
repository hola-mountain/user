package com.holamountain.userdomain.service.mypage;

import com.holamountain.userdomain.dto.request.UserMyInfoRequest;
import com.holamountain.userdomain.dto.response.UserMyInfoResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public class UserMypageServiceImpl implements  UserMypageService {
    @Override
    public Mono<UserMyInfoResponse> myInfo(ServerRequest serverRequest) {
//        UserMyInfoRequest userMyInfoRequest = UserMyInfoRequest.builder()
//                                            .userId(serverRequest.)

        return null;
    }
}
