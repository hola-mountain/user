
package com.holamountain.userdomain.router;

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
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> router(UserHandler userHandler) {
        return RouterFunctions.route()
                .path("/users", routerBuilder ->
                    routerBuilder.nest(accept(MediaType.APPLICATION_JSON), builder ->
                        builder
                            .POST("/join", userHandler::join)
                            .POST("/login", userHandler::login)
                    )
                )
                .build();
    }
}
