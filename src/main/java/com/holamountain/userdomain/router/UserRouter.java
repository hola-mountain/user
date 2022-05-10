
package com.holamountain.userdomain.router;

import com.holamountain.userdomain.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> router(UserHandler userHandler) {
        return RouterFunctions.route()
                .POST("/users/join", userHandler::join)
                .POST("/users/login", userHandler::login)
                .build();
    }
}
