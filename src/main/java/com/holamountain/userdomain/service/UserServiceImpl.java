
package com.holamountain.userdomain.service;

import com.holamountain.userdomain.Utils.SHA256;
import com.holamountain.userdomain.common.Message.ExceptionMessage;
import com.holamountain.userdomain.common.UserEnums.UserType;
import com.holamountain.userdomain.dto.request.UserLoginRequest;
import com.holamountain.userdomain.dto.request.UserMyInfoRequest;
import com.holamountain.userdomain.dto.request.UserRegistrationRequest;
import com.holamountain.userdomain.dto.response.UserLoginResponse;
import com.holamountain.userdomain.dto.response.UserMyInfoResponse;
import com.holamountain.userdomain.dto.response.UserRegistrationResponse;
import com.holamountain.userdomain.exception.EmptyRequestException;
import com.holamountain.userdomain.exception.FailUserRegistrationException;
import com.holamountain.userdomain.exception.UnAuthorizedException;
import com.holamountain.userdomain.exception.UserRegistrationException;
import com.holamountain.userdomain.jwt.JwtProvider;
import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public Mono<UserLoginResponse> userLogin(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserLoginRequest.class).flatMap(
                request -> {
                    request.verify();
                    return getLoginUser(request);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(ExceptionMessage.EmptyRequestMessage.getMessage())));
    }

    private Mono<UserLoginResponse> getLoginUser(UserLoginRequest userLoginRequest) {

        Mono<UserEntity> loginUser = userRepository.findByNickNameAndPassword(userLoginRequest.getNickName(), SHA256.encrypt(userLoginRequest.getPassword()));

        return loginUser.hasElement()
                .flatMap(tryLoginUser -> {
                    if (!tryLoginUser)
                        return Mono.error(new UnAuthorizedException(ExceptionMessage.HasNoUserException.getMessage()));

                    return makeMemberLoginResponse(loginUser);
                });
    }

    private Mono<UserLoginResponse> makeMemberLoginResponse(Mono<UserEntity> userEntity) {
        return userEntity.flatMap(user -> {
            String accessToken = jwtProvider.createAccessJwtToken(user);
            String refreshToken = jwtProvider.createRefreshJwtToken(user);
            return Mono.just(new UserLoginResponse(user.getUserId(), accessToken, refreshToken));
        });
    }

    @Override
    public Mono<UserRegistrationResponse> userRegistration(ServerRequest serverRequest, UserType userType) {
        return serverRequest.bodyToMono(UserRegistrationRequest.class).flatMap(
                request -> {
                    request.verify();
                    return userValidCheckAndRegistration(request, userType);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(ExceptionMessage.EmptyRequestMessage.getMessage())));
    }

    private Mono<UserRegistrationResponse> userValidCheckAndRegistration(UserRegistrationRequest request, UserType userType) {
        return userRepository.findByNickNameAndUserType(request.getNickName(), userType)
            .hasElement()
            .flatMap(alreadyMember -> {
                if (alreadyMember) return Mono.error(new UserRegistrationException(ExceptionMessage.UserRegistrationDuplicationException.getMessage()));
                    return this.saveUser(request, userType)
                        .flatMap(savedUser -> Mono.just(
                                UserRegistrationResponse.builder()
                                        .userId(savedUser.getUserId())
                                        .build()
                        ));
                }
        );
    }

    private Mono<UserEntity> saveUser(UserRegistrationRequest userRegistrationRequest, UserType userType) {
        UserEntity userEntity = UserEntity.builder()
                                    .nickName(userRegistrationRequest.getNickName())
                                    .password(SHA256.encrypt(userRegistrationRequest.getPassword()))
                                    .userType(userType)
                                    .build();

        if (!StringUtils.isBlank(userRegistrationRequest.getEmail())) userEntity.setEmail(userRegistrationRequest.getEmail());

        return userRepository.save(userEntity)
                .switchIfEmpty(Mono.error(new FailUserRegistrationException(ExceptionMessage.FailUserRegistrationMessage.getMessage())));
    }
}
