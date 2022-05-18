package com.holamountain.userdomain.router;

import com.holamountain.userdomain.handler.MypageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class MypageRouter {

    @Bean
    public RouterFunction<ServerResponse> myRouter(MypageHandler mypageHandler) {
        return RouterFunctions.route()
                .GET("/mypage/myInfo", mypageHandler::myInfo)
                .GET("/mypage/badges", mypageHandler::myBadges)
                .PUT("/mypage/leave", mypageHandler::leave)
//            .GET("/mypage/mountain/favorite", mypageHandler::myFavorite)
//            .GET("/mypage/mountain/review", mypageHandler::myReview)
                .build();
    }
}
