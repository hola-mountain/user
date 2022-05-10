
package com.holamountain.userdomain.service;

import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Mono<UserEntity> loginService(Map<String, Object> param) {
        long id = Long.parseLong(param.get("userId").toString());
        String password = param.get("password").toString();
        System.out.println("=============" + id + "    " + password);
        Mono<UserEntity> results = userRepository.findByUserIdAndPassword(id, password);
        return results;
    }
}
