package com.holamountain.userdomain.service.mypage;


import com.holamountain.userdomain.common.Message.MypageExceptionMessage;
import com.holamountain.userdomain.common.UserEnums.UserStatus;
import com.holamountain.userdomain.dto.request.MyBadgeInfoRequest;
import com.holamountain.userdomain.dto.request.MypageInfoRequest;
import com.holamountain.userdomain.dto.request.UserLeaveRequest;
import com.holamountain.userdomain.dto.response.MyBadgeInfoResponse;
import com.holamountain.userdomain.dto.response.MypageInfoResponse;
import com.holamountain.userdomain.dto.response.MypageLeaveResponse;
import com.holamountain.userdomain.exception.EmptyRequestException;
import com.holamountain.userdomain.exception.NoDataFounedException;
import com.holamountain.userdomain.exception.ProcessingErrorException;
import com.holamountain.userdomain.exception.UnAuthorizedException;
import com.holamountain.userdomain.model.AchievementEntity;
import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.AchievementRepository;
import com.holamountain.userdomain.repository.MypageRepository;
import com.holamountain.userdomain.repository.UserRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
    private final MypageRepository mypageRepository;
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

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

        return loginUser.flatMap(tryUserInfo -> {
            if (tryUserInfo.getUserId() == null || tryUserInfo.getUserId() < 0) {
                return Mono.error(new UnAuthorizedException(MypageExceptionMessage.NoDataFounedException.getMessage()));
            }

            return Flux.just(new MyBadgeInfoResponse(tryUserInfo.getAchievementId(), tryUserInfo.getAchievementNum(), tryUserInfo.getBadgeId(), tryUserInfo.getUserId()));
        });
    }

//    @Override
//    public Mono<MypageLeaveResponse> leave(ServerRequest serverRequest) {
//        return serverRequest.bodyToMono(UserLeaveRequest.class).flatMap(user -> {
//                    user.verify();
//                    return leaveUser(user);
//                }
//        ).switchIfEmpty(Mono.error(new EmptyRequestException(MypageExceptionMessage.EmptyRequestMessage.getMessage())));
//    }
//
//    private Mono<MypageLeaveResponse> leaveUser(UserLeaveRequest userLeaveRequest) {
//        Mono<UserEntity> userEntityMono = userRepository.findById(Long.parseLong(userLeaveRequest.getUserId()));
//
//        return userEntityMono.flatMap(leaveRequestUser -> {
//            if (leaveRequestUser.getUserId() == null || leaveRequestUser.getUserId() < 0) {
//                throw new NoDataFounedException(MypageExceptionMessage.NoDataFounedException.getMessage());
//            }
//
//            leaveRequestUser.setStatusYn(false);
//
//
//            return
//        });
//    }
}
