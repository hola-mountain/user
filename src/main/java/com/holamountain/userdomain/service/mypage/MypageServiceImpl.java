package com.holamountain.userdomain.service.mypage;


import com.holamountain.userdomain.common.Message.MypageExceptionMessage;
import com.holamountain.userdomain.dto.request.users.UserLeaveRequest;
import com.holamountain.userdomain.dto.response.mypage.*;
import com.holamountain.userdomain.exception.EmptyRequestException;
import com.holamountain.userdomain.exception.NoDataFounedException;
import com.holamountain.userdomain.exception.ProcessingErrorException;
import com.holamountain.userdomain.exception.UnAuthorizedException;
import com.holamountain.userdomain.model.BadgeEntity;
import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.AchievementRepository;
import com.holamountain.userdomain.repository.BadgeRepository;
import com.holamountain.userdomain.repository.MypageRepository;
import com.holamountain.userdomain.repository.UserRepository;
import com.holamountain.userdomain.webclient.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
    private final MypageRepository mypageRepository;
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final WebClientConfig webClientConfig;

    @Override
    public Mono<MypageInfoResponse> userInfo(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("userId")).flatMap(searchInfoUserId -> {
            if (StringUtils.isBlank(searchInfoUserId)) {
                throw new EmptyRequestException(MypageExceptionMessage.EmptyRequestMessage.getMessage());
            }

            return getUserInfo(searchInfoUserId);
        }).switchIfEmpty(Mono.error(new ProcessingErrorException(MypageExceptionMessage.ProcessingErrorException.getMessage())));
    }

    private Mono<MypageInfoResponse> getUserInfo(String userId) {
        return mypageRepository.findById(Long.parseLong(userId)).flatMap(userInfo -> {
            if (userInfo.getUserId() == 0 || userInfo.getUserId() < 0) {
                throw new NoDataFounedException(MypageExceptionMessage.NoDataFounedException.getMessage());
            }

            return Mono.just(new MypageInfoResponse(userInfo.getEmail(), userInfo.getNickName()));
        });
    }

    @Override
    public Flux<MyBadgeInfoResponse> myBadges(ServerRequest serverRequest) {
        return Flux.just(serverRequest.pathVariable("userId")).flatMap(searchInfoUserId -> {
                    if (StringUtils.isBlank(searchInfoUserId)) {
                        throw new EmptyRequestException(MypageExceptionMessage.EmptyRequestMessage.getMessage());
                    }

                    return getUserBadgeInfo(searchInfoUserId);
                }
        );
    }

    private Flux<MyBadgeInfoResponse> getUserBadgeInfo(String userId) {
        return achievementRepository.findByUserId(Long.parseLong(userId)).flatMap(tryUserInfo -> {
            if (tryUserInfo.getUserId() == null || tryUserInfo.getUserId() < 0) {
                return Mono.error(new UnAuthorizedException(MypageExceptionMessage.NoDataFounedException.getMessage()));
            }

            return Mono.zip(Mono.just(tryUserInfo), badgeRepository.findById(tryUserInfo.getBadgeId()))
                    .map(userBadgeInfo -> new MyBadgeInfoResponse(userBadgeInfo.getT1().getAchievementId()
                                                                ,userBadgeInfo.getT1().getAchievementNum()
                            ,userBadgeInfo.getT1().getBadgeId()
                            ,userBadgeInfo.getT1().getUserId()
                            ,userBadgeInfo.getT2().getBadgeType()
                    ));
        });
    }

    @Override
    public Mono<MypageLeaveResponse> leave(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserLeaveRequest.class).flatMap(user -> {
                    user.validCheck();
                    return leaveUser(user);
                }
        );
    }

    private Mono<MypageLeaveResponse> leaveUser(UserLeaveRequest userLeaveRequest) {
        Mono<UserEntity> userEntityMono = userRepository.findById(Long.parseLong(userLeaveRequest.getUserId()));

        return userEntityMono.flatMap(leaveRequestUser -> {
            if (leaveRequestUser.getUserId() == null || leaveRequestUser.getUserId() < 0) {
                throw new NoDataFounedException(MypageExceptionMessage.NoDataFounedException.getMessage());
            }

            leaveRequestUser.setStatusYn(true);
            return userRepository.save(leaveRequestUser);
        }).flatMap(updatedUser -> {
            return Mono.just(new MypageLeaveResponse(HttpStatus.OK.value()));
        });
    }

    @Override
    public Flux<MyFavoriteMountainResponse> myFavorite(ServerRequest serverRequest) {
        return Flux.just(serverRequest.pathVariable("userId")).flatMap(searchInfoUserId -> {
            if (StringUtils.isBlank(searchInfoUserId)) {
                throw new EmptyRequestException(MypageExceptionMessage.EmptyRequestMessage.getMessage());
            }

            return webClientConfig.getMountainWebClinet().get()
                    .uri(uriBuilder ->
                            uriBuilder.path("/mountain/favorite/me")
                                    .queryParam("userId", searchInfoUserId)
                                    .build()
                    )
                    .retrieve()
                    .bodyToFlux(MyFavoriteMountainResponse.class);
        });
    }

    @Override
    public Flux<MyMountainReviewResponse> myReview(ServerRequest serverRequest) {
        return Flux.just(serverRequest.pathVariable("userId")).flatMap(searchInfoUserId -> {
            if (StringUtils.isBlank(searchInfoUserId)) {
                throw new EmptyRequestException(MypageExceptionMessage.EmptyRequestMessage.getMessage());
            }

            return webClientConfig.getMountainWebClinet().get()
                    .uri(uriBuilder ->
                            uriBuilder.path("/mountain/review/me")
                                    .queryParam("userId", searchInfoUserId)
                                    .build()
                    )
                    .retrieve()
                    .bodyToFlux(MyMountainReviewResponse.class);
        });
    }
}
