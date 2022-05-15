package com.holamountain.userdomain.router;

import com.holamountain.userdomain.handler.MypageHandler;
import com.holamountain.userdomain.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class MypageRouter {

    @Bean
    public RouterFunction<ServerResponse> myRouter(MypageHandler mypageHandler) {
        return RouterFunctions.route()
                .path("/mypage", routerBuilder ->
                                routerBuilder.nest(accept(MediaType.APPLICATION_JSON), builder ->
                                                builder
                                                        .POST("/myInfo", mypageHandler::myInfo)
                                )
                )
                .build();
    }
}
