package com.holamountain.userdomain.service.mypage;


import com.holamountain.userdomain.common.Message.MypageExceptionMessage;
import com.holamountain.userdomain.dto.request.MyBadgeInfoRequest;
import com.holamountain.userdomain.dto.request.MypageInfoRequest;
import com.holamountain.userdomain.dto.response.MyBadgeInfoResponse;
import com.holamountain.userdomain.dto.response.MypageInfoResponse;
import com.holamountain.userdomain.exception.EmptyRequestException;
import com.holamountain.userdomain.exception.UnAuthorizedException;
import com.holamountain.userdomain.model.AchievementEntity;
import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.AchievementRepository;
import com.holamountain.userdomain.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
    private final MypageRepository mypageRepository;
    private final AchievementRepository achievementRepository;

    @Override
    public Mono<MypageInfoResponse> userInfo(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MypageInfoRequest.class).flatMap(
                request -> {
                    request.verify();
                    return getUserInfo(request);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(MypageExceptionMessage.UnAuthorizedException.getMessage())));
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

    @Override
    public Flux<MyBadgeInfoResponse> myBadges(ServerRequest serverRequest) {
        return serverRequest.bodyToFlux(MyBadgeInfoRequest.class).flatMap(
                request -> {
                    request.verify();
                    return getUserBadgeInfo(request);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(MypageExceptionMessage.UnAuthorizedException.getMessage())));
    }

    private Flux<MyBadgeInfoResponse> getUserBadgeInfo(MyBadgeInfoRequest myBadgeInfoRequest) {
        Flux<AchievementEntity> loginUser = achievementRepository.findByUserId(myBadgeInfoRequest.getUserId());


//        dd
        return loginUser.flatMap(tryUserInfo -> {
            if (tryUserInfo.getUserId() == null || tryUserInfo.getUserId() < 0) {
                return Mono.error(new UnAuthorizedException(MypageExceptionMessage.NoDataFounedException.getMessage()));
            }

            return Flux.just(new MyBadgeInfoResponse(tryUserInfo.getAchievementId(), tryUserInfo.getAchievementNum(), tryUserInfo.getBadgeId(), tryUserInfo.getUserId()));
        });
    }
}
