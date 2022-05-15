package com.holamountain.userdomain.service.mypage;


import com.holamountain.userdomain.common.Message.MypageExceptionMessage;
import com.holamountain.userdomain.common.Message.UsersExceptionMessage;
import com.holamountain.userdomain.dto.request.MypageInfoRequest;
import com.holamountain.userdomain.dto.response.MypageInfoResponse;
import com.holamountain.userdomain.exception.EmptyRequestException;
import com.holamountain.userdomain.exception.UnAuthorizedException;
import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
    private final MypageRepository mypageRepository;

    @Override
    public Mono<MypageInfoResponse> userInfo(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MypageInfoRequest.class).flatMap(
                request -> {
                    request.verify();
                    return getUserInfo(request);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(UsersExceptionMessage.EmptyRequestMessage.getMessage())));
    }

    private Mono<MypageInfoResponse> getUserInfo(MypageInfoRequest mypageInfoRequest) {

        Mono<UserEntity> loginUser = mypageRepository.findByUserId(mypageInfoRequest.getUserId());

        return loginUser.flatMap(tryUserInfo -> {
            if (tryUserInfo.getUserId() == null || tryUserInfo.getUserId() < 0) {
                return Mono.error(new UnAuthorizedException(MypageExceptionMessage.NoDataFounedException.getMessage()));
            }

            return Mono.just(new MypageInfoResponse(tryUserInfo.getEmail(), tryUserInfo.getNickName()));
        });
    }
}
