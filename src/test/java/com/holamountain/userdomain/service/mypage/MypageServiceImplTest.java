package com.holamountain.userdomain.service.mypage;

import com.holamountain.userdomain.Utils.SHA256;
import com.holamountain.userdomain.model.UserEntity;
import com.holamountain.userdomain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MypageServiceImplTest {

    @Autowired
    private UserRepository userRepository;

//    @Test
//    public void test() {
//        Mono<UserEntity> dd =  userRepository.findByUserId(1L);
//
//        dd.flatMap(map -> {
//            System.out.println("123");
//
//            return null;
//        });
//    }
//
//    @Test
//    public void test2() {
//        Mono<UserEntity> loginUser = userRepository.findByNickNameAndPassword("MinJun", "dsadasdsad");
//
//        loginUser.flatMap(map -> {
//            System.out.println("123");
//
//            return null;
//        });
//    }

}