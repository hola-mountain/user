
package com.holamountain.userdomain.service;

import com.holamountain.userdomain.common.Message.ExceptionMessage;
import com.holamountain.userdomain.dto.request.UserLoginRequest;
import com.holamountain.userdomain.dto.response.UserLoginResponse;
import com.holamountain.userdomain.exception.UnAuthorizedException;
import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Mono<UserLoginResponse> loginService(UserLoginRequest userLoginRequest) {
        Mono<UserLoginResponse> user = userRepository.findByNickNameAndPassword(userLoginRequest.getNickName(), userLoginRequest.getPassword());

        return user.hasElement()
                    .flatMap(tryLoginUser -> {
                        if (!tryLoginUser) return Mono.error(new UnAuthorizedException(ExceptionMessage.NotFoundLoginMember.getMessage()));
                    });
    }
}
