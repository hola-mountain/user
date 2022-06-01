
package com.holamountain.userdomain.service.users;

import com.holamountain.userdomain.Utils.MailSenderUtil;
import com.holamountain.userdomain.Utils.SHA256;
import com.holamountain.userdomain.common.Message.UsersExceptionMessage;
import com.holamountain.userdomain.common.UserEnums.UserType;
import com.holamountain.userdomain.dto.request.jwt.JwtTokenRequest;
import com.holamountain.userdomain.dto.request.users.UserLoginRequest;
import com.holamountain.userdomain.dto.request.users.UserLogoutRequest;
import com.holamountain.userdomain.dto.request.users.UserRegistrationRequest;
import com.holamountain.userdomain.dto.request.users.VerifyUserRegistrationRequest;
import com.holamountain.userdomain.dto.response.jwt.JwtTokenResponse;
import com.holamountain.userdomain.dto.response.users.UserLoginResponse;
import com.holamountain.userdomain.dto.response.users.UserLogoutResponse;
import com.holamountain.userdomain.dto.response.users.UserRegistrationResponse;
import com.holamountain.userdomain.dto.response.users.VerifyUserRegistrationResponse;
import com.holamountain.userdomain.exception.*;
import com.holamountain.userdomain.jwt.JwtProvider;
import com.holamountain.userdomain.jwt.JwtTokenInfo;
import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RedisTemplate redisTemplate;
    private final RedisTemplate<String, Object> userRedisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private final MailSenderUtil mailSenderUtil;

    @Override
    public Mono<UserLoginResponse> userLogin(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserLoginRequest.class).flatMap(
                request -> {
                    request.validCheck();
                    return getLoginUser(request);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(UsersExceptionMessage.EmptyRequestMessage.getMessage())));
    }

    private Mono<UserLoginResponse> getLoginUser(UserLoginRequest userLoginRequest) {

        Mono<UserEntity> loginUser = userRepository.findByNickNameAndPassword(userLoginRequest.getNickName(), SHA256.encrypt(userLoginRequest.getPassword()));

        return loginUser.hasElement()
                .flatMap(tryLoginUser -> {
                    if (!tryLoginUser)
                        return Mono.error(new UnAuthorizedException(UsersExceptionMessage.HasNoUserException.getMessage()));

                    return makeMemberLoginResponse(loginUser);
                });
    }

    private Mono<UserLoginResponse> makeMemberLoginResponse(Mono<UserEntity> userEntity) {
        return userEntity.flatMap(user -> {
            String accessToken = jwtProvider.createAccessJwtToken(user);
            JwtTokenInfo refreshTokenInfo = jwtProvider.createRefreshJwtToken(user);

            redisTemplate.opsForValue()
                    .set("RT:" + refreshTokenInfo.getUserId()
                            ,refreshTokenInfo.getRefreshToken()
                            ,refreshTokenInfo.getRefreshTokenExpirationTime().getTime()
                            ,TimeUnit.MILLISECONDS);

            return Mono.just(new UserLoginResponse(user.getUserId()
                    , accessToken
                    , refreshTokenInfo.getRefreshToken()));
        });
    }

    @Override
    public Mono<UserRegistrationResponse> userRegistration(ServerRequest serverRequest, UserType userType) {
        return serverRequest.bodyToMono(UserRegistrationRequest.class).flatMap(
                request -> {
                    request.validCheck();
                    return userValidCheckAndRegistration(request, userType);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(UsersExceptionMessage.EmptyRequestMessage.getMessage())));
    }

    private Mono<UserRegistrationResponse> userValidCheckAndRegistration(UserRegistrationRequest request, UserType userType) {
        return userRepository.findByNickNameAndUserType(request.getNickName(), userType)
                .hasElement()
                .flatMap(alreadyMember -> {
                            if (alreadyMember)
                                return Mono.error(new UserRegistrationException(UsersExceptionMessage.UserRegistrationDuplicationException.getMessage()));
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

        if (!StringUtils.isBlank(userRegistrationRequest.getEmail()))
            userEntity.setEmail(userRegistrationRequest.getEmail());

//        ValueOperations<String, Object> vop = userRedisTemplate.opsForValue();
//        vop.set("toverify-"+userEntity.getNickName(), userEntity);
//        mailSenderUtil.sendMail(userEntity.getEmail(), userEntity.getNickName(), 0);

        return userRepository.save(userEntity)
                .switchIfEmpty(Mono.error(new FailUserRegistrationException(UsersExceptionMessage.FailUserRegistrationMessage.getMessage())));
    }

    @Override
    public Mono<JwtTokenResponse> reIssueAccessJwtToken(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(JwtTokenRequest.class).flatMap(
                request -> {
                    request.validCheck();
                    return validCheckJwtTokenInfo(request);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(UsersExceptionMessage.EmptyRequestMessage.getMessage())));
    }

    public Mono<JwtTokenResponse> validCheckJwtTokenInfo(JwtTokenRequest jwtTokenRequest) {
        Long userId = Long.parseLong(jwtProvider.getUserIdFromAccessToken(jwtTokenRequest.getAccessToken()));
        Mono<UserEntity> loginedUser = userRepository.findById(userId);

        return loginedUser.hasElement()
                .flatMap(user -> {
                    if (!user)
                        return Mono.error(new UnAuthorizedException(UsersExceptionMessage.HasNoUserException.getMessage()));

                    String refreshToken = (String) redisTemplate.opsForValue().get("RT:" + userId);
                    if (!refreshToken.equals(jwtTokenRequest.getRefreshToken())) {
                        return Mono.error(new UnAuthorizedException(UsersExceptionMessage.UnAuthorizedException.getMessage()));
                    }

                    return getJwtTokenInfo(loginedUser);
                });
    }

    public Mono<JwtTokenResponse> getJwtTokenInfo(Mono<UserEntity> userEntityMono) {
        return userEntityMono.flatMap(user -> {
            String accessToken = jwtProvider.createAccessJwtToken(user);
            JwtTokenInfo refreshTokenInfo = jwtProvider.createRefreshJwtToken(user);

            redisTemplate.opsForValue()
                    .set("RT:" + refreshTokenInfo.getUserId()
                            , refreshTokenInfo.getRefreshToken()
                            , refreshTokenInfo.getRefreshTokenExpirationTime().getTime()
                            , TimeUnit.MILLISECONDS);

            return Mono.just(JwtTokenResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshTokenInfo.getRefreshToken())
                    .build());
        });
    }

    @Override
    public Mono<UserLogoutResponse> userLogout(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserLogoutRequest.class).flatMap(
                request -> {
                    if (!redisTemplate.delete("RT:" + request.getUserId())) {
                        return Mono.error(new ProcessingErrorException(UsersExceptionMessage.FailUserLogoutMessage.getMessage()));
                    }

                    UserLogoutResponse userLogoutResponse = UserLogoutResponse.builder().build();
                    userLogoutResponse.setLogoutSuccessMessage();
                    return Mono.just(userLogoutResponse);
                }
        ).switchIfEmpty(Mono.error(new EmptyRequestException(UsersExceptionMessage.EmptyRequestMessage.getMessage())));
    }

    @Override
    public Mono<VerifyUserRegistrationResponse> verifyUserRegistration(ServerRequest serverRequest) {
        String nickName = serverRequest.queryParam("nickname").get();
        String key = serverRequest.queryParam("key").get();

        VerifyUserRegistrationRequest verifyUserRegistrationRequest = VerifyUserRegistrationRequest.builder()
                                                                                            .nickName(nickName)
                                                                                            .key(key)
                                                                                            .build();

        return Mono.just(verifyUserRegistrationRequest).flatMap(user -> {
            if (stringRedisTemplate.opsForValue().get("email-"+nickName).equals(key)) {
                ValueOperations<String, Object> memvop = userRedisTemplate.opsForValue();
                UserEntity userEntity = (UserEntity) memvop.get("toverify-"+nickName);
                userRepository.save(userEntity);
                stringRedisTemplate.delete("email-"+nickName);
                userRedisTemplate.delete("toverify-"+nickName);
            }

            VerifyUserRegistrationResponse verifyUserRegistrationResponse = VerifyUserRegistrationResponse.builder().build();
            verifyUserRegistrationResponse.setVerifySuccessMessage();
            return Mono.just(verifyUserRegistrationResponse);
        }).switchIfEmpty(Mono.error(new EmptyRequestException(UsersExceptionMessage.EmptyRequestMessage.getMessage())));
    }
}
